package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.GyanListAdapter;
import com.adapters.NewsListAdapter;
import com.dstabase.OffLineDb;
import com.models.Gyan;
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
public class GyanFragment extends Fragment implements TnHtttpComCallBack {
    ArrayList<Gyan> gyanList;
    RecyclerView gyanRecycler;
    GyanListAdapter adapter;
    LinearLayoutManager manager;
    Gyan gyan;
    JSONObject jsonObject;
    private OffLineDb mydb;

    public GyanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gyan, container, false);
        init();
        //isNetworkAvailable();
        gyanRecycler = (RecyclerView) view.findViewById(R.id.gyan_list_recycler);
        manager = new LinearLayoutManager(getActivity());

        gyanRecycler.setLayoutManager(manager);


        return view;
    }

    private void init() {

        this.gyanList = new ArrayList<Gyan>();
        this.mydb = new OffLineDb(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();


        Utils.getInstance().displayLoading(getActivity());


            newtworkcall();
            this.adapter = new GyanListAdapter(getActivity(), gyanList);
            gyanRecycler.setAdapter(adapter);
            if (this.adapter.gyanList.size() > 0 && this.adapter != null) {
                this.adapter.gyanList.clear();
            }
        }


    private void newtworkcall() {
        TnHttpCom.getNewInstance(getActivity(), this).callGyanService("gyan");
    }

    @Override
    public void onSuccess(boolean status, int tag, JSONObject jsonResponse, int sequence) {
        Utils.getInstance().hideLoading();
        if (tag == TnHttpCom.GYAN_SERVICE) {
            if (jsonResponse != null) {
                if (status) {
                    JSONArray data = jsonResponse.optJSONArray("data");
                    if (data != null) {

                        for (int i = 0; i < data.length(); i++) {
                            this.jsonObject = data.optJSONObject(i);
                            this.gyan = new Gyan(jsonObject);
                            //insertIntoDb();
                            this.gyanList.add(gyan);


                        }

                        Collections.reverse(this.gyanList);

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
}
