package com.example.student.sqliteui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void onClickAdd(View view) {

        EditText etname = (EditText) findViewById(R.id.editTextName);
        EditText ettel = (EditText) findViewById(R.id.editTextTel);
        EditText etaddr = (EditText) findViewById(R.id.editTextAddr);
        EditText etemail = (EditText) findViewById(R.id.editTextEmail);

        String[] strarr = new String[]={
                new String (etname.getText().toString());

        };



        Intent in = getIntent();
        in.putExtras(str);
        finishActivity(MainActivity.REQ_CODE);
//        finish();

    }
}
