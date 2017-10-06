package com.example.student.listadapter;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static int REQUEST_CODE_ADD = 321;
    ListView lv;
    ArrayAdapter aa;
    //    String[] sarr = {"AAA", "BBB", "CCC", "DDD", "EEE"};
    ArrayList<String> als = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.ListViewShow);

        aa = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, als);
        lv.setAdapter(aa);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                als.remove(i);
                aa.notifyDataSetChanged();
                return true;
            }
        });
        lv.setAdapter(aa);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //                Snackbar.make(view, "Hello world !", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                Intent in = new Intent(MainActivity.this, InputActivity.class);
                startActivityForResult(in, REQUEST_CODE_ADD);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == RESULT_OK) {
                String s = data.getStringExtra("ADD_DATA");
                als.add(s);
                aa.notifyDataSetChanged();

            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent it = new Intent(MainActivity.this, InputActivity.class);
            startActivityForResult(it, REQUEST_CODE_ADD);
        }
        return super.onOptionsItemSelected(item);
    }


}
