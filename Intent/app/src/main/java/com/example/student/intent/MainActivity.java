package com.example.student.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAll(View view) {

        Intent in = new Intent(MainActivity.this, SecActivity.class);
        startActivityForResult(in, P.LAB_INPUT);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case P.LAB_INPUT:
                    Toast.makeText(MainActivity.this, data.getStringExtra("INPUT"), Toast.LENGTH_SHORT).show();
                    break;
            }

        }

    }





}
