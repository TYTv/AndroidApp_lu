package com.example.student.listadapter;

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


    public void onClickAll(View view) {
        switch (view.getId()) {
            case R.id.ButtonOk:
                EditText et = (EditText) findViewById(R.id.EditTextInput);
                Intent in = new Intent();
                in.putExtra("ADD_DATA", et.getText().toString());
                setResult(RESULT_OK, in);
            case R.id.ButtonBack:
                finish();
                break;
        }
    }
}
