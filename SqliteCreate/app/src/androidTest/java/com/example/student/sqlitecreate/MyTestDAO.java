package com.example.student.sqlitecreate;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.student.sqlitecreate.SQL.book;
import com.example.student.sqlitecreate.SQL.bookDAO_DBimp;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 2017/10/18.
 */
@RunWith(AndroidJUnit4.class)
public class MyTestDAO {

    @Test
    public void MyTestDAO_addget() {

        //Get Context
        Context appContext = InstrumentationRegistry.getTargetContext();

        //Add
        bookDAO_DBimp dao = new bookDAO_DBimp(appContext);
        book b = new book();
        b.name = "CCC";
        b.tel = "123";
        b.addr = "aabb";
        b.email = "yahoo@yahoo.com";
        dao.addOne(b);

        //Get
        book p1 = dao.getOne(1);
        assertEquals("BBB", p1.name);
    }


    @Test
    public void MyTestDAO_delgetlist()
    {
        Context appContext = InstrumentationRegistry.getTargetContext();
        bookDAO_DBimp dao = new bookDAO_DBimp(appContext);
        book b = new book();
        b.name = "BBB";
        b.tel = "123";
        b.addr = "aabb";
        dao.clearAll();
        dao.addOne(b);

        book pArray[] = dao.getList();
        assertEquals("BBB", pArray[0].name);

    }

}
