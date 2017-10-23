package com.example.student.sqlitecreate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.sqlitecreate.SQL.book;
import com.example.student.sqlitecreate.SQL.bookDAO;
import com.example.student.sqlitecreate.SQL.bookDAO_DBimp;

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

//        bookDAO dao = new bookDAO_DBimp(this);
        bookDAO dao = DAO_factory.getDAO(this, APL.daotyp);     //Run on memory
        book b = new book();

        b.name = etname.getText().toString();
        b.tel = ettel.getText().toString();
        b.addr = etaddr.getText().toString();
        b.email = etemail.getText().toString();
        dao.addOne(b);
        finish();

    }

}