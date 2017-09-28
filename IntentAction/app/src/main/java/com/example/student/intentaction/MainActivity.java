package com.example.student.intentaction;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAll(View view) {
        Uri u;
        Intent in;
        switch (view.getId()) {
            case R.id.web:
                u = Uri.parse("http://www.pchome.com.tw");
                in = new Intent(Intent.ACTION_VIEW, u);
                startActivity(in);
                break;
            case R.id.dial:
                u = Uri.parse("tel:0800080090");
                in = new Intent(Intent.ACTION_DIAL, u);
                startActivity(in);
                break;
            case R.id.map:
                //u = Uri.parse("geo:38.899533,-77.036476");
                //u = Uri.parse("geo:25.0463884,121.5178677?z=20");
                u = Uri.parse("geo:0,0?q=台北轉運站");
                //u = Uri.parse("market:search?q=google");
                in = new Intent(Intent.ACTION_VIEW, u);
                startActivity(in);
                break;
            case R.id.send:
                in = new Intent();
                in.setAction(Intent.ACTION_SEND);
                in.putExtra(Intent.EXTRA_TEXT, "要分享的文字");
                in.setType("text/plain");
                startActivity(in);
                break;
            case R.id.filter:
                in = new Intent();
                in.setAction("myaction");
                String s = "asdfghjk";
                in.putExtra("DDD", s);
                startActivity(in);
                break;
        }
    }
}
