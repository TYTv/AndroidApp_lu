package com.example.student.xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.ListViewRSS);

    }


    public void onClickRSS(View view) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                URL url = null;
                try {
                    url = new URL("https://udn.com/rssfeed/news/2/6638?ch=news");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream;
                    inputStream = conn.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder sb = new StringBuilder();
                    String str;
                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    String result = sb.toString();
                    final MyDataHandler mdh = new MyDataHandler();

                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(mdh);
                    xr.parse(new InputSource(new StringReader(result)));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ArrayList<String> tmp = new ArrayList<String>();
                            for (objRSS i : mdh.dataRSS) {
                                tmp.add(i.title);
                            }
                            aa = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tmp);
                            lv.setAdapter(aa);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    Intent in = new Intent(MainActivity.this, WebActivity.class);
                                    in.putExtra("LINK", mdh.dataRSS.get(i).link);
                                    startActivity(in);

                                }
                            });

                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }
        }.start();


    }
}
