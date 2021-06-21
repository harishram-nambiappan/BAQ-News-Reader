package com.harishram.baq_news_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class NewsRequest {
    Socket recv;
    String news;
    String news_receive(String msg){
      try {
          recv = new Socket();
          BufferedWriter req = new BufferedWriter(new OutputStreamWriter(recv.getOutputStream()));
          req.write(msg);
          req.flush();
          BufferedReader resp = new BufferedReader(new InputStreamReader(recv.getInputStream()));
          news = resp.readLine();
          recv.close();
      }catch(Exception e){
          e.printStackTrace();
      }
      return news;
    }
    
}
