package com.example.student.sqlitecreate;

import com.example.student.sqlitecreate.SQL.book;
import com.example.student.sqlitecreate.SQL.bookDAO_MEMimp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 2017/10/23.
 */

public class MyMEMDAOTest {

    @Test
    public void myTest() {
        assertEquals(10, MyMath.cal(3, 7));
    }

    @Test
    public void clearAndAddOneDataAndGetTest() {
        bookDAO_MEMimp dao = new bookDAO_MEMimp();
        book p = new book();
        p.name = "BBB";
        p.tel = "123";
        p.addr = "aabb";
        dao.clearAll();
        dao.addOne(p);

        book pArray[] = dao.getList();
        assertEquals("BBB", pArray[0].name);
    }

    @Test
    public void testDelete() {
        bookDAO_MEMimp dao = new bookDAO_MEMimp();
        book p1 = new book("CCC", "333", "CC33", "EEE");
        book p2 = new book("DDD", "444", "DD44", "MMM");

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

        bookDAO_MEMimp dao = new bookDAO_MEMimp();
        book p1 = new book("CCC", "333", "CC33", "III");

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
