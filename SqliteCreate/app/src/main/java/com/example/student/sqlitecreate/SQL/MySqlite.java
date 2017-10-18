package com.example.student.sqlitecreate.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 2017/10/18.
 */

public class MySqlite extends SQLiteOpenHelper {

    final static String DB_Name = "telbook.sqlite";
//    final static int VERSION = 1;
    final static int VERSION = 2;   //改版

    public MySqlite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlite(Context context) {
        super(context, DB_Name, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//        String cmd = "CREATE TABLE \"telbook\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"name\" VARCHAR, \"tel\" VARCHAR, \"addr\" VARCHAR)";      // Ver 1 init
        String cmd = "CREATE TABLE \"telbook\" (\"id\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"name\" VARCHAR, \"tel\" VARCHAR, \"addr\" VARCHAR, \"email\" VARCHAR)";     //Ver 2 init
        sqLiteDatabase.execSQL(cmd);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i == 1 && i1 == 2) {
            String upgradeSQL = "ALTER TABLE \"main\".\"telbook\" ADD COLUMN \"email\" VARCHAR";
            sqLiteDatabase.execSQL(upgradeSQL);
        }

    }
}
