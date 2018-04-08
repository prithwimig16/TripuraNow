package com.adapters;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.activities.NewsActivityDetails;
import com.bumptech.glide.Glide;
import com.models.News;
import com.tripuranow.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Prithwi on 03/04/18.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyviewHolder> {
    Context mContext;
    public ArrayList<News> newsList;


    public NewsListAdapter(Context context, ArrayList<News> newsList) {
        this.mContext = context;
        this.newsList = new ArrayList<>();
        this.newsList = newsList;
        //Collections.reverse(this.newsList);
    }


    @Override
    public NewsListAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_single_item, parent, false);

        return new NewsListAdapter.MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.MyviewHolder holder, final int position) {

        if (newsList.size() > 0) {
            final News news = newsList.get(position);

            holder.tvHeadline.setText(news.getNewsHeadLine() + " |");
            holder.tvNewsDate.setText(news.getNewsDate());
            if (news.getNewsImageUrl() != null) {
                Glide.with(mContext).load(news.getNewsImageUrl()).into(holder.newsImage);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsActivityDetails.class);
                    intent.putExtra("imageUrl", newsList.get(position).getNewsImageUrl());
                    intent.putExtra("newsContent", newsList.get(position).getNewsContent());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadline, tvNewsDate;
        ImageView newsImage;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvHeadline = (TextView) itemView.findViewById(R.id.tv_heading);
            tvNewsDate = (TextView) itemView.findViewById(R.id.tv_date);
            newsImage = (ImageView) itemView.findViewById(R.id.img_news_heading);


        }
    }
}