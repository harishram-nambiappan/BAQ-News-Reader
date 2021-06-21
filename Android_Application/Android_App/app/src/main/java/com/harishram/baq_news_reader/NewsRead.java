package com.harishram.baq_news_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class NewsRead extends AppCompatActivity {
    Bundle news_bundle;
    Bundle back_bundle;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_read);
        WebView wv = (WebView) findViewById(R.id.wv1);
        news_bundle = getIntent().getBundleExtra("news");
        url = news_bundle.getString("Url");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient());
        Thread command_thread = new Thread(new Runnable(){

            @Override
            public void run() {
                while(true){
                    try{
                        Socket sock = new Socket("192.168.43.100", 3000);
                        BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        String command_string = br.readLine();
                        if(command_string.equals("Back")){
                            back_bundle = new Bundle();
                            back_bundle.putString("News_List",news_bundle.getString("News_List"));
                            Intent back_intent = new Intent(getApplicationContext(), NewsList.class);
                            back_intent.putExtra("news",back_bundle);
                            startActivity(back_intent);
                            break;
                        }
                        System.out.println(command_string);
                        sock.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        command_thread.start();

    }
}