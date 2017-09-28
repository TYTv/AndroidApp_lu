package com.example.student.intentaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActionIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_intent);

        TextView tv = (TextView) findViewById(R.id.test);

        Intent in = getIntent();
//        String s = in.getStringExtra("DDD");
        String s = in.getStringExtra(Intent.EXTRA_TEXT);
        tv.setText(s);

    }
}
