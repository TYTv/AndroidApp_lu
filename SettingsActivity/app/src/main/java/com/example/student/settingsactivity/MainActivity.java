package com.example.student.settingsactivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        switch (view.getId()) {
            case R.id.buttonSetting:
                Intent in = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(in);
                break;
            case R.id.buttonRead:
                SharedPreferences sp = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
                String s = sp.getString("example_text", "");
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
