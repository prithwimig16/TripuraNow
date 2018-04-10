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
import com.models.Dharma;
import com.tripuranow.R;

import java.util.ArrayList;

/**
 * Created by Prithwi on 11/04/18.
 */

public class DharmaListAdapter extends RecyclerView.Adapter<DharmaListAdapter.MyDharmaviewHolder> {
    Context mContext;
    public ArrayList<Dharma> dharmaList;


    public DharmaListAdapter(Context context, ArrayList<Dharma> gyanList) {
        this.mContext = context;
        this.dharmaList = new ArrayList<>();
        this.dharmaList = gyanList;
        //Collections.reverse(this.newsList);
    }




    @Override
    public MyDharmaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_single_item, parent, false);

        return new DharmaListAdapter.MyDharmaviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyDharmaviewHolder holder, final int position) {

        if (dharmaList.size() > 0) {
            final Dharma dharma = dharmaList.get(position);

            holder.tvHeadline.setText(dharma.getDharma_headline() + " |");
            holder.tvNewsDate.setText(dharma.getDharma_date());
            if (dharma.getDharma_image_url() != null) {
                Glide.with(mContext).load(dharma.getDharma_image_url()).into(holder.newsImage);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsActivityDetails.class);
                    intent.putExtra("imageUrl", dharmaList.get(position).getDharma_image_url());
                    intent.putExtra("content", dharmaList.get(position).getDharma_content());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.dharmaList.size();
    }

    public class MyDharmaviewHolder extends RecyclerView.ViewHolder {

        TextView tvHeadline, tvNewsDate;
        ImageView newsImage;

        public MyDharmaviewHolder(View itemView) {
            super(itemView);
            tvHeadline = (TextView) itemView.findViewById(R.id.tv_heading);
            tvNewsDate = (TextView) itemView.findViewById(R.id.tv_date);
            newsImage = (ImageView) itemView.findViewById(R.id.img_news_heading);


        }
    }
}

