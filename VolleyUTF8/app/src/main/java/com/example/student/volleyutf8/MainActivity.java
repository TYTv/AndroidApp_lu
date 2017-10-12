package com.example.student.volleyutf8;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //強制使用UTF-8解
        StringRequest request = new UTF8StringRequest(
                "https://udn.com/rssfeed/news/2/6638?ch=news",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
        queue.start();

        //用Queue下載圖片
        ImageRequest request1 = new ImageRequest(
                "http://images.all-free-download.com/images/graphiclarge/butterfly_flower_01_hd_pictures_166973.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView img = (ImageView) findViewById(R.id.imageView);
                        img.setImageBitmap(response);

                    }
                },
                0,
                0,
                ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });
        queue.add(request1);
        queue.start();


        //用Google API Picasso 下載(有快取功能)
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(this)
                .load("http://images.all-free-download.com/images/graphiclarge/pink_flowers_with_bees_hd_picture_166824.jpg")
                .into(imageView);


    }
}
