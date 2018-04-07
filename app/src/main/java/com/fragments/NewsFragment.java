package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.NewsListAdapter;
import com.models.News;
import com.networks.TnHttpCom;
import com.networks.TnHtttpComCallBack;
import com.tripuranow.R;
import com.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

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
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news, container, false);
        this.newsList=new ArrayList<News>();

        newsRecycler = (RecyclerView) view.findViewById(R.id.news_list_recycler);
        manager=new LinearLayoutManager(getActivity());

        newsRecycler.setLayoutManager(manager);

        this.adapter=new NewsListAdapter(getActivity(),newsList);

        newsRecycler.setAdapter(adapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.getInstance().displayLoading(getActivity());
        newtworkcall();
        if(this.adapter.newsList.size()>0&&this.adapter!=null){
            this.adapter.newsList.clear();
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

                        for (int i=0;i<data.length();i++){
                            this.jsonObject = data.optJSONObject(i);
                            this. news=new News(jsonObject);
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

    private void newtworkcall(){
        TnHttpCom.getNewInstance(getActivity(),this).callNewsService();
    }

}
