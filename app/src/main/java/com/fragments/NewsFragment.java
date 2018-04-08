package com.fragments;


import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adapters.NewsListAdapter;
import com.dstabase.OffLineDb;
import com.models.News;
import com.networks.TnHttpCom;
import com.networks.TnHtttpComCallBack;
import com.tripuranow.R;
import com.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements TnHtttpComCallBack {
    ArrayList<News> newsList;
    RecyclerView newsRecycler;
    NewsListAdapter adapter;
    LinearLayoutManager manager;
    News news;
    JSONObject jsonObject;
    private OffLineDb mydb;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init();
        isNetworkAvailable();
        newsRecycler = (RecyclerView) view.findViewById(R.id.news_list_recycler);
        manager = new LinearLayoutManager(getActivity());

        newsRecycler.setLayoutManager(manager);


        return view;
    }

    private void init() {

        this.newsList = new ArrayList<News>();
        this.mydb = new OffLineDb(getActivity());
        // this.adapter = new NewsListAdapter(getActivity(), newsList);
    }

    @Override
    public void onResume() {
        super.onResume();


        Utils.getInstance().displayLoading(getActivity());

        if (isNetworkAvailable()) {
            newtworkcall();
            this.adapter = new NewsListAdapter(getActivity(), newsList);
            newsRecycler.setAdapter(adapter);
            if (this.adapter.newsList.size() > 0 && this.adapter != null) {
                this.adapter.newsList.clear();
            }
        } else {

            if (this.adapter != null) {
                this.adapter.newsList.clear();
            }
            this.adapter = new NewsListAdapter(getActivity(), newsList);
            this.adapter.notifyDataSetChanged();
            Utils.getInstance().hideLoading();
             Toast.makeText(getActivity(),"Please check your Internet connection",Toast.LENGTH_LONG).show();
            if (this.adapter != null) {
                callDb();
                newsRecycler.setAdapter(adapter);
            }


        }


    }

    @Override
    public void onSuccess(boolean status, int tag, JSONObject jsonResponse, int sequence) {
        Utils.getInstance().hideLoading();
        if (tag == TnHttpCom.NEWS_SERVICE) {
            if (jsonResponse != null) {
                if (status) {
                    JSONArray data = jsonResponse.optJSONArray("news");
                    if (data != null) {

                        for (int i = 0; i < data.length(); i++) {
                            this.jsonObject = data.optJSONObject(i);
                            this.news = new News(jsonObject);
                            insertIntoDb();
                            this.newsList.add(news);


                        }

                        Collections.reverse(this.newsList);

                        this.adapter.notifyDataSetChanged();

                    }
                }
            }
        }
    }

    @Override
    public void onFailure(JSONObject error, int tag) {
        Utils.getInstance().hideLoading();
    }

    private void newtworkcall() {
        TnHttpCom.getNewInstance(getActivity(), this).callNewsService();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void insertIntoDb() {

        if (this.news != null) {
            boolean isInserted = mydb.insertData(this.news.getNewsId(), this.news.getNewsHeadLine(), this.news.getNewsContent(), this.news.getNewsDate(), this.news.getNewsImageUrl(), this.news.getVideoUrl());
            if (isInserted) {
                // Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void callDb() {

        if (this.adapter != null) {
            Cursor cursor = mydb.getallData();
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    JSONObject jobj = new JSONObject();
                    try {
                        jobj.put("newsId", cursor.getString(0));
                        jobj.put("headline", cursor.getString(1));
                        jobj.put("content", cursor.getString(2));
                        jobj.put("date", cursor.getString(3));
                        jobj.put("image_url", cursor.getString(4));
                        jobj.put("video_url", cursor.getString(5));
                        this.news = new News(jobj);
                        this.newsList.add(news);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                Collections.reverse(this.newsList);
                //this.adapter.newsList=this.newsList;
                this.adapter.notifyDataSetChanged();
            }

        }
    }

}
