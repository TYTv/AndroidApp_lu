package com.example.student.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
    }

    public void onClickAll(View view) {

        EditText et = (EditText) findViewById(R.id.editText);
        String s = et.getText().toString();

        Intent in = new Intent();
        in.putExtra("INPUT", s);

        setResult(RESULT_OK, in);
        finish();

    }
}
