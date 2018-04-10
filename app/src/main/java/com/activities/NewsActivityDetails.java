package com.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.models.News;
import com.tripuranow.R;

import java.util.ArrayList;

public class NewsActivityDetails extends AppCompatActivity {
    String imageUrl,newsContent;
    ImageView image;
    TextView tvNewsContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        this.imageUrl=getIntent().getStringExtra("imageUrl");
        this.newsContent=getIntent().getStringExtra("content");

        init();


    }

    private void init(){
        this.image=(ImageView)findViewById(R.id.img_news);
        this.tvNewsContent=(TextView)findViewById(R.id.tv_newscontent);

        if (this.imageUrl!= null&&this.imageUrl.length()>6) {
            Glide.with(this).load(imageUrl).into(image);
        }

        if(this.newsContent.length()>2&&this.newsContent!=null){

        this.tvNewsContent.setText(this.newsContent);
        }

    }
}
