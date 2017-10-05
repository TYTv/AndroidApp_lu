package com.example.student.fileaccess;

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
        SharedPreferences sp = getSharedPreferences("SHARE",MODE_PRIVATE);
        switch (view.getId()) {
            case R.id.buttonW:
                SharedPreferences.Editor spd = sp.edit();
                spd.putString("SHARE_A","測試A");
                spd.putString("SHARE_B","測試B");
                spd.commit();
                break;
            case R.id.buttonR:
                String s = sp.getString("SHARE_A","def_A");
                s += sp.getString("SHARE_B","def_B");
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                break;
        }


    }

}
