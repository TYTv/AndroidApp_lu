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

//    @Test
//    public void MyTestDAO_addget() {
//
//        //Get Context
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        //Add
//        bookDAO_DBimp dao = new bookDAO_DBimp(appContext);
//        book b = new book();
//        b.name = "CCC";
//        b.tel = "123";
//        b.addr = "aabb";
//        b.email = "yahoo@yahoo.com";
//        dao.addOne(b);
//
//        //Get
//        book p1 = dao.getOne(1);
//        assertEquals("BBB", p1.name);
//    }


    @Test
    public void MyTestDAO_delgetlist() {
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

    @Test
    public void testDelete() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        bookDAO_DBimp dao = new bookDAO_DBimp(appContext);
        book p1 = new book("CCC", "333", "CC33", "Google");
        book p2 = new book("DDD", "444", "DD44", "Yahoo");

        dao.clearAll();
        dao.addOne(p1);
        dao.addOne(p2);
        book pArray[] = dao.getList();
        p1.id = pArray[0].id;
        dao.delete(p1);
        book pArray2[] = dao.getList();
        assertEquals("DDD", pArray2[0].name);
    }

    @Test
    public void testUpdate() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        bookDAO_DBimp dao = new bookDAO_DBimp(appContext);
        book p1 = new book("CCC", "333", "CC33","Google");

        dao.clearAll();
        dao.addOne(p1);
        book pArray[] = dao.getList();
        p1.id = pArray[0].id;
        p1.name = "CDE";
        dao.update(p1);
        book pArray2[] = dao.getList();
        assertEquals("CDE", pArray2[0].name);
    }

}
