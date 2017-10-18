package com.example.student.sqlitecreate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by student on 2017/10/18.
 */

public class MyTest {
    @Test
    public void myTest()
    {
        assertEquals(10, MyMath.cal(3,7));
        assertEquals(10, MyMath.cal(5,5));
        assertEquals(10, MyMath.cal(2,8));
        assertEquals(10, MyMath.cal(1,9));
    }

}
