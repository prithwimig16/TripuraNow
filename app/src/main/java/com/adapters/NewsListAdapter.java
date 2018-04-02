package com.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activities.NewsActivityDetails;
import com.tripuranow.R;

/**
 * Created by Prithwi on 03/04/18.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyviewHolder> {
    Context mContext;
    public NewsListAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public NewsListAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_single_item, parent, false);

        return new NewsListAdapter.MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.MyviewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsActivityDetails.class);
                intent.putExtra("no",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{


        public MyviewHolder(View itemView) {
            super(itemView);
        }
    }
}