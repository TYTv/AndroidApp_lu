package com.example.student.optionsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 1, 0, "選單1");
//        menu.add(0, 2, 0, "選單2");
//        menu.add(0, 3, 0, "選單3");

        MenuInflater mif = getMenuInflater();
        mif.inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (item.getItemId() == 1) {
//            Toast.makeText(MainActivity.this, "選單1被按下", Toast.LENGTH_LONG).show();
//        } else if (item.getItemId() == 2) {
//            Toast.makeText(MainActivity.this, "選單2被按下", Toast.LENGTH_LONG).show();
//        } else if (item.getItemId() == 3) {
//            Toast.makeText(MainActivity.this, "選單3被按下", Toast.LENGTH_LONG).show();
//        }

        if (item.getItemId() == R.id.ver) {
            Toast.makeText(MainActivity.this, "ver被按下", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.about) {
            Toast.makeText(MainActivity.this, "about被按下", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
