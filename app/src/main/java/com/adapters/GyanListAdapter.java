package com.adapters;

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
import com.models.Gyan;
import com.tripuranow.R;

import java.util.ArrayList;

/**
 * Created by Prithwi on 11/04/18.
 */

public class GyanListAdapter extends RecyclerView.Adapter<GyanListAdapter.MyGyanviewHolder> {
    Context mContext;
    public ArrayList<Gyan> gyanList;


    public GyanListAdapter(Context context, ArrayList<Gyan> gyanList) {
        this.mContext = context;
        this.gyanList = new ArrayList<>();
        this.gyanList = gyanList;
        //Collections.reverse(this.newsList);
    }




    @Override
    public MyGyanviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_single_item, parent, false);

        return new GyanListAdapter.MyGyanviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyGyanviewHolder holder, final int position) {

        if (gyanList.size() > 0) {
            final Gyan gyan = gyanList.get(position);

            holder.tvHeadline.setText(gyan.getGyan_headline() + " |");
            holder.tvNewsDate.setText(gyan.getGyan_date());
            if (gyan.getGyan_image_url() != null) {
                Glide.with(mContext).load(gyan.getGyan_image_url()).into(holder.newsImage);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsActivityDetails.class);
                    intent.putExtra("imageUrl", gyanList.get(position).getGyan_image_url());
                    intent.putExtra("content", gyanList.get(position).getGyan_content());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.gyanList.size();
    }

    public class MyGyanviewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadline, tvNewsDate;
        ImageView newsImage;

        public MyGyanviewHolder(View itemView) {
            super(itemView);
            tvHeadline = (TextView) itemView.findViewById(R.id.tv_heading);
            tvNewsDate = (TextView) itemView.findViewById(R.id.tv_date);
            newsImage = (ImageView) itemView.findViewById(R.id.img_news_heading);


        }
    }
}

