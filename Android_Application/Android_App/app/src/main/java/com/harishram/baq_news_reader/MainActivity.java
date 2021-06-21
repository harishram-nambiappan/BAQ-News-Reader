package com.harishram.baq_news_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {
    Bundle news_bundle;
    TextView intro_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intro_text = (TextView) findViewById(R.id.click_text);
        Thread sleep_thread = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                news_bundle = new Bundle();
                news_bundle.putString("News_List","");
                Intent news_intent = new Intent(getApplicationContext(), NewsList.class);
                news_intent.putExtra("news",news_bundle);
                startActivity(news_intent);
            }
        });
        try {
            sleep_thread.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*try {
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }
}