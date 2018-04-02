package com.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tripuranow.R;

public class NewsActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        int i=getIntent().getIntExtra("no",0);
    }
}
