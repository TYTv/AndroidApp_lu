package com.example.student.sqlitecreate.SQL;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by student on 2017/10/23.
 */

public class bookDAO_FILEimp implements bookDAO {

    ArrayList<book> datalist = new ArrayList();
    String File_Name = null;

    public bookDAO_FILEimp(Context context) {
        Activity act = (Activity) context;
        File_Name = act.getFilesDir().getAbsolutePath() + File.separator + "mydata.json";
        FileReader fr = null;
        try {
            File f = new File(File_Name);
            if (f.exists()) {
                fr = new FileReader(File_Name);
                BufferedReader br = new BufferedReader(fr);
                String str = br.readLine();
                Gson gson = new Gson();
                datalist = gson.fromJson(str, new TypeToken<ArrayList<book>>() {
                }.getType());
                br.close();
                fr.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {

        Gson gson = new Gson();
        String str = gson.toJson(datalist);
        FileWriter fw = null;
        try {
            fw = new FileWriter(File_Name);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addOne(book b) {
        datalist.add(b);
        saveFile();
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
        saveFile();
    }

    @Override
    public book[] getList() {
        return datalist.toArray(new book[datalist.size()]);
    }

    @Override
    public void delete(book b) {
        datalist.remove(b);
        saveFile();
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
        saveFile();
    }
}
