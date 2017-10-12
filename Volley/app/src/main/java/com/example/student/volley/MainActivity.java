package com.example.student.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StringRequest request = new StringRequest(
                "http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("NET", response);

//                        try {
//                            JSONArray array = new JSONArray(response);
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject obj = array.getJSONObject(i);
//                                Log.d("NET", "district:" + obj.getString("district"));
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                        //用gson將json轉物件
                        Gson gson = new Gson();
                        gsonData[] ah;
                        ah = gson.fromJson(response, gsonData[].class);
                        for (gsonData a : ah) {
                            Log.d("NET", a.district);
                        }

                        //物件轉json
                        String str = gson.toJson(ah);
                        Log.d("NET", str);

                        //物件存至ArrayList
                        ArrayList<gsonData> list1 = new ArrayList<>();
                        for (gsonData a : ah) {
                            list1.add(a);
                        }
                        String str2 = gson.toJson(list1);
                        Log.d("NET", str2);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("NET", error.toString());

                    }
                });

        RequestQueue rq = Volley.newRequestQueue(MainActivity.this);
        rq.add(request);
        rq.start();


    }
}
