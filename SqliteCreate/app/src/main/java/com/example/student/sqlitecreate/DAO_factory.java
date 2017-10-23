package com.example.student.sqlitecreate;

import android.app.Activity;
import android.content.Context;

import com.example.student.sqlitecreate.SQL.bookDAO;
import com.example.student.sqlitecreate.SQL.bookDAO_DBimp;
import com.example.student.sqlitecreate.SQL.bookDAO_FILEimp;

/**
 * Created by student on 2017/10/23.
 */

public class DAO_factory {

    public static bookDAO getDAO(Context context, APL.DAOType dt) {

        Activity act = (Activity) context;
        APL apl = (APL) act.getApplication();

        bookDAO dao = null;
        switch (dt) {
            case MEM:
                dao = apl.dao;
                break;
            case DB:
                dao = new bookDAO_DBimp(context);
                break;
            case FILE:
                dao = new bookDAO_FILEimp(context);
                break;
        }

        return dao;

    }

}
