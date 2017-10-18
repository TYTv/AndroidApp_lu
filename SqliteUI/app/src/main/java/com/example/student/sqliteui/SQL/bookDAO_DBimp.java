package com.example.student.sqliteui.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by student on 2017/10/18.
 */

public class bookDAO_DBimp implements bookDAO {

    private MySqlite helper;

    public bookDAO_DBimp(Context ctx) {

        helper = new MySqlite(ctx);

    }

    @Override
    public void addOne(book b) {

        ContentValues cv = new ContentValues();
        cv.put("name", b.name);
        cv.put("tel", b.tel);
        cv.put("addr", b.addr);
        cv.put("email", b.email);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.insert("telbook", null, cv);
        db.close();

    }

    @Override
    public book getOne(int id) {

        book b = new book();

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.query("telbook", new String[]{"id", "name", "tel", "addr", "email"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        c.moveToFirst();
        b.id = c.getInt(0);
        b.name = c.getString(1);
        b.tel = c.getString(2);
        b.addr = c.getString(3);
        b.email = c.getString(4);
        db.close();

        return b;

    }

    @Override
    public void clearAll() {

        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from telbook");
        db.close();

    }

    @Override
    public book[] getList() {

        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<book> mylist = new ArrayList();

        Cursor c = db.query("telbook", new String[]{"id", "name", "tel", "addr", "email"}, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                book b = new book();
                b.id = c.getInt(0);
                b.name = c.getString(1);
                b.tel = c.getString(2);
                b.addr = c.getString(3);
                mylist.add(b);
            } while (c.moveToNext());
        }

        return mylist.toArray(new book[mylist.size()]);

    }

}
