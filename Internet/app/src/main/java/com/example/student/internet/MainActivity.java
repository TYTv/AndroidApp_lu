package com.example.student.internet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGoogle(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://rate.bot.com.tw/xrt?Lang=zh-TW");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();

                    String str = null;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
//                        Log.d("MYNET", str);
                    }

                    int idx = sb.indexOf("日圓", 0);
                    idx = sb.indexOf("本行現金買入", idx);
                    int idxBUYs = sb.indexOf(">", idx) + 1;
                    int idxBUYe = sb.indexOf("<", idxBUYs);
                    String JP_BUY = sb.substring(idxBUYs, idxBUYe);
                    Log.d("LOG", idxBUYs + "[JPN_BUY]" + JP_BUY);

                    idx = sb.indexOf("本行現金賣出", idx);
                    int idxSALEs = sb.indexOf(">", idx) + 1;
                    int idxSALEe = sb.indexOf("<", idxSALEs);
                    String JP_SALE = sb.substring(idxSALEs, idxSALEe);
                    Log.d("LOG", idxSALEs + "[JPN_SALE]" + JP_SALE);

                    br.close();
                    isr.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
