package com.example.student.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickCopy(View view) {

        InputStream is = getResources().openRawResource(R.raw.user);
        try {

            OutputStream os = new FileOutputStream(getFilesDir() + File.separator + "user.sqlite");
            int i = 0;
            while (i != -1) {
                i = is.read();
                os.write(i);
            }

            is.close();
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onClickNioCopy(View view) {

        File f = new File(getFilesDir() + File.separator + "user_nio.sqlite");
        if (!f.exists()) {

            InputStream is = getResources().openRawResource(R.raw.user);
            URI uri = URI.create("file://" + getFilesDir().getAbsolutePath() + File.separator + "user_nio.sqlite");
            Path p = Paths.get(uri);
            try {
                Files.copy(is, p, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void onClickReadDB(View view) {

        String path = getFilesDir().getAbsolutePath() + File.separator + "user_nio.sqlite";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, 0);
        Cursor c = db.query("telbook", new String[]{"id", "name", "tel", "addr"}, null, null, null, null, null);

        StringBuilder sb = new StringBuilder();
        c.moveToFirst();
        do {
            Log.d("DB", c.getString(0) + "," + c.getString(1) + "," + c.getString(2) + "," + c.getString(3));
        } while (c.moveToNext());

        for (int i = 0; i < c.getCount(); i++) {
            c.moveToPosition(i);
            String s = c.getString(0) + "," + c.getString(1) + "," + c.getString(2) + "," + c.getString(3);
            Log.d("DB", s);
            sb.append(s + "\n");
        }

        c.close();
        db.close();

        TextView tv = (TextView) findViewById(R.id.textViewReadDB);
        tv.setText(sb);


    }


    public void onClickAppendDB(View view) {

        String path = getFilesDir().getAbsolutePath() + File.separator + "user_nio.sqlite";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, 0);
        ContentValues cv = new ContentValues();

        EditText etname = (EditText) findViewById(R.id.editTextAppendName);
        EditText ettel = (EditText) findViewById(R.id.editTextAppendTel);
        EditText etaddr = (EditText) findViewById(R.id.editTextAppendAddr);

        cv.put("name", etname.getText().toString());
        cv.put("tel", ettel.getText().toString());
        cv.put("addr", etaddr.getText().toString());

        db.insert("telbook", null, cv);
        db.close();

    }


    public void onClickDelete(View view) {

        EditText et = (EditText) findViewById(R.id.editTextId);

        String path = getFilesDir().getAbsolutePath() + File.separator + "user_nio.sqlite";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        db.delete("telbook", "id=?", new String[]{et.getText().toString()});
        db.close();

    }

    public void onClickUpdate(View view) {

        EditText etname = (EditText) findViewById(R.id.editTextAppendName);
        EditText ettel = (EditText) findViewById(R.id.editTextAppendTel);
        EditText etaddr = (EditText) findViewById(R.id.editTextAppendAddr);
        EditText etid = (EditText) findViewById(R.id.editTextId);

        String path = getFilesDir().getAbsolutePath() + File.separator + "user_nio.sqlite";
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues cv = new ContentValues();

        cv.put("name", etname.getText().toString());
        cv.put("tel", ettel.getText().toString());
        cv.put("addr", etaddr.getText().toString());

        db.update("telbook", cv, "id=?", new String[]{etid.getText().toString()});
        db.close();

    }
}
