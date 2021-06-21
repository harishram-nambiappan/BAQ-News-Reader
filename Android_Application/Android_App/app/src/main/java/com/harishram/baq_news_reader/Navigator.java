package com.harishram.baq_news_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Navigator extends AppCompatActivity {

    Bundle news_bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        news_bundle = getIntent().getBundleExtra("news");
        try{
            Thread.sleep(2000);
        }catch(Exception e){
            e.printStackTrace();
        }
        Intent list_intent = new Intent(getApplicationContext(), NewsList.class);
        list_intent.putExtra("news",news_bundle);
        startActivity(list_intent);
    }
}