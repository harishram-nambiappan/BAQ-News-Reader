package com.harishram.baq_news_reader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class AudioRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        /*if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, Integer.parseInt(Manifest.permission.RECORD_AUDIO));
        }*/
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, Integer.parseInt(Manifest.permission.RECORD_AUDIO));


    }
}