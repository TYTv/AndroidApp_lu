package com.example.student.sqlitecreate;

import android.app.Application;

import com.example.student.sqlitecreate.SQL.bookDAO;
import com.example.student.sqlitecreate.SQL.bookDAO_MEMimp;

/**
 * Created by student on 2017/10/23.
 */

public class APL extends Application {

    public bookDAO dao = new bookDAO_MEMimp();

    public enum DAOType {
        MEM,
        DB,
        FILE
    }
    public static DAOType daotyp = DAOType.FILE;  //設定操作模式

}
