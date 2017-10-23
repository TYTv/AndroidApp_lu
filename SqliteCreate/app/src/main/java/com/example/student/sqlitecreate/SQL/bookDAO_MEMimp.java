package com.example.student.sqlitecreate.SQL;

import java.util.ArrayList;

/**
 * Created by student on 2017/10/23.
 */

public class bookDAO_MEMimp implements bookDAO {

    ArrayList<book> datalist = new ArrayList();
    int id = 1;

    @Override
    public void addOne(book p) {
        p.id = this.id;
        this.id++;
        datalist.add(p);
    }

    @Override
    public book getOne(int id) {
        book rtValue = null;
        for (book tmp : datalist) {
            if (tmp.id == id) {
                rtValue = tmp;
                break;
            }
        }
        return rtValue;
    }

    @Override
    public void clearAll() {
        datalist.clear();
    }

    @Override
    public book[] getList() {
        return datalist.toArray(new book[datalist.size()]);
    }

    @Override
    public void delete(book b) {
        datalist.remove(b);
    }

    @Override
    public void update(book b) {
        for (book tmp : datalist) {
            if (tmp.id == b.id) {
                tmp.name = b.name;
                tmp.addr = b.addr;
                tmp.tel = b.tel;
                tmp.email = b.email;
                break;
            }
        }
    }
}
