package com.example.student.sqlitecreate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.student.sqlitecreate.SQL.book;
import com.example.student.sqlitecreate.SQL.bookDAO;
import com.example.student.sqlitecreate.SQL.bookDAO_DBimp;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adp;
    book[] b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);

    }


    @Override
    protected void onResume() {
        super.onResume();

        bookDAO dao = new bookDAO_DBimp(this);
        b = dao.getList();

        String str[] = new String[b.length];
        for (int i = 0; i < b.length; i++) {
            str[i] = b[i].name + "," + b[i].tel + "," + b[i].addr + "," + b[i].email;
        }
        adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int id = b[position].id;
                Intent it = new Intent(MainActivity.this, EditActivity.class);
                it.putExtra("id", id);
                startActivity(it);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("ADD");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent it = new Intent(this, InputActivity.class);
        startActivity(it);
        return super.onOptionsItemSelected(item);

    }


}
