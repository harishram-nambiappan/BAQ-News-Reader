package com.harishram.baq_news_reader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.NewsCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class NewsList extends AppCompatActivity {

    ScrollView sv;
    LinearLayout ll;
    Socket sock;
    String instruction;
    String command = "";
    String news_list = "";
    String news[];
    TextView tv[];
    Bundle news_bundle;
    Bundle next_bundle;
    String news_link = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        sv = (ScrollView) findViewById(R.id.sv1);
        ll = (LinearLayout) findViewById(R.id.ll1);
        news_bundle = getIntent().getBundleExtra("news");
        if(!news_bundle.getString("News_List").equals("")){
            news = news_bundle.getString("News_List").split("/");
            tv = new TextView[news.length];
            for(int i=0;i<news.length;i++){
                tv[i] = new TextView(this);
                tv[i].setText(news[i]);
                ll.addView(tv[i]);
            }
        }
        Thread news_receive = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        instruction = "";
                        sock = new Socket("192.168.43.100", 3000);
                        BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                        instruction = br.readLine();
                        command = instruction.split("#")[0];
                        if(command.equals("Get")){
                            System.out.println(instruction);
                            news_list = instruction.split("#")[1];
                            next_bundle = new Bundle();
                            next_bundle.putString("News_List",news_list);
                            Intent update_intent = new Intent(getApplicationContext(), Navigator.class);
                            update_intent.putExtra("news",next_bundle);
                            startActivity(update_intent);
                            break;
                        }
                        else if(command.equals("Select")){
                            System.out.println(instruction);
                            news_link = instruction.split("#")[1];
                            next_bundle = new Bundle();
                            next_bundle.putString("News_List",news_bundle.getString("News_List"));
                            next_bundle.putString("Url",news_link);
                            Intent display_intent = new Intent(getApplicationContext(),NewsRead.class);
                            display_intent.putExtra("news",next_bundle);
                            startActivity(display_intent);
                            break;
                        }
                        Thread.sleep(2000);
                        sock.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            news_receive.start();

        }catch(Exception e){
            e.printStackTrace();
        }
        /*Intent news_intent = new Intent(getApplicationContext(), NewsRead.class);
        startActivity(news_intent);*/

        /*while(true){
          try {
              news_receive.start();
              news_receive.join();
              System.out.println(instruction);
              if(instruction.equals("Hello")){
                  cnt++;
              }
              if(cnt==10){
                  Intent news_intent = new Intent(getApplicationContext(), NewsRead.class);
                  startActivity(news_intent);
              }
          }
          catch(Exception e){
              break;
          }
        }*/
    }
}