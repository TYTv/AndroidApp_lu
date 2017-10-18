package com.example.student.sqliteui.SQL;

/**
 * Created by student on 2017/10/18.
 */

public interface bookDAO {

    public void addOne(book p);

    public book getOne(int id);

    public void clearAll();

    public book[] getList();

}
