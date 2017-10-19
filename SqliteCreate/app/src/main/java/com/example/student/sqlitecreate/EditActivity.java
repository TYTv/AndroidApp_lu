package com.example.student.sqlitecreate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.sqlitecreate.SQL.book;
import com.example.student.sqlitecreate.SQL.bookDAO;
import com.example.student.sqlitecreate.SQL.bookDAO_DBimp;

public class EditActivity extends AppCompatActivity {

    EditText etId;
    EditText etName;
    EditText etTel;
    EditText etAddr;
    EditText etEmail;

    book b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etId = (EditText) findViewById(R.id.editTextId);
        etName = (EditText) findViewById(R.id.editTextName);
        etTel = (EditText) findViewById(R.id.editTextTel);
        etAddr = (EditText) findViewById(R.id.editTextAddr);
        etEmail = (EditText) findViewById(R.id.editTextEmail);

        Intent it = getIntent();
        int id = it.getIntExtra("id", 0);
        bookDAO dao = new bookDAO_DBimp(this);
        b = dao.getOne(id);
        etId.setText(String.valueOf(b.id));
        etName.setText(b.name);
        etTel.setText(b.tel);
        etAddr.setText(b.addr);
        etEmail.setText(b.email);

    }

    public void onClickDelete(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確認刪除");
        builder.setMessage("請確認是否刪除本筆資料?");

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                bookDAO dao = new bookDAO_DBimp(EditActivity.this);
                dao.delete(b);

                finish();

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }


    public void onClickEdit(View view) {

        bookDAO dao = new bookDAO_DBimp(EditActivity.this);
        b.name = etName.getText().toString();
        b.tel = etTel.getText().toString();
        b.addr = etAddr.getText().toString();
        b.email = etEmail.getText().toString();
        dao.update(b);

        finish();

    }
}
