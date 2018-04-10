package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.DharmaListAdapter;
import com.adapters.GyanListAdapter;
import com.dstabase.OffLineDb;
import com.models.Dharma;
import com.models.Gyan;
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
public class DharmaFragment extends Fragment implements TnHtttpComCallBack {
    ArrayList<Dharma> dharmaList;
    RecyclerView dharmaRecycler;
    DharmaListAdapter adapter;
    LinearLayoutManager manager;
    Dharma dharma;
    JSONObject jsonObject;
    private OffLineDb mydb;


    public DharmaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dharma, container, false);
        init();
        //isNetworkAvailable();
        dharmaRecycler = (RecyclerView) view.findViewById(R.id.dharma_list_recycler);
        manager = new LinearLayoutManager(getActivity());

        dharmaRecycler.setLayoutManager(manager);


        return view;
    }
    private void init() {

        this.dharmaList = new ArrayList<Dharma>();
        this.mydb = new OffLineDb(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();


        Utils.getInstance().displayLoading(getActivity());


        newtworkcall();
        this.adapter = new DharmaListAdapter(getActivity(), dharmaList);
        dharmaRecycler.setAdapter(adapter);
        if (this.adapter.dharmaList.size() > 0 && this.adapter != null) {
            this.adapter.dharmaList.clear();
        }
    }

    private void newtworkcall() {
        TnHttpCom.getNewInstance(getActivity(), this).callDharmaService("dharma");
    }

    @Override
    public void onSuccess(boolean status, int tag, JSONObject jsonResponse, int sequence) {
        Utils.getInstance().hideLoading();
        if (tag == TnHttpCom.DHARMA_SERVICE) {
            if (jsonResponse != null) {
                if (status) {
                    JSONArray data = jsonResponse.optJSONArray("data");
                    if (data != null) {

                        for (int i = 0; i < data.length(); i++) {
                            this.jsonObject = data.optJSONObject(i);
                            this.dharma = new Dharma(jsonObject);
                            //insertIntoDb();
                            this.dharmaList.add(dharma);


                        }

                        Collections.reverse(this.dharmaList);

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
