package com.example.student.sqlitecreate.SQL;

/**
 * Created by student on 2017/10/18.
 */

public class book {

    public int id;
    public String name;
    public String tel;
    public String addr;
    public String email;

    public book() {
        ;
    }

    public book(String name, String tel, String addr, String email) {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
        this.email = email;
    }

    public book(int id, String name, String tel, String addr, String email) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.addr = addr;
        this.email = email;
    }

}
