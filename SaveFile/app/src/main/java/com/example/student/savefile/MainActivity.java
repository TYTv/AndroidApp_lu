package com.example.student.savefile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    public final static int CODE_EXT_STO_W = 34;
    public final static int CODE_EXT_STO_R = 58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickAll(View view) {

        String fdata = getFilesDir().getAbsolutePath() + File.separator + "Hello.txt";
        String fcache = getCacheDir().getAbsolutePath() + File.separator + "Hello.txt";


        switch (view.getId()) {
            case R.id.buttonW:
                try {

                    FileWriter fw = null;
                    try {
                        fw = new FileWriter(fdata);
                        fw.write("Hello World !");
                    } finally {
                        fw.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonR:

                // 判斷是否有檔案
                if (!new File(fdata).exists()) {
                    Toast.makeText(MainActivity.this, "File not found !", Toast.LENGTH_LONG).show();
                    break;
                }

                FileReader fr = null;
                char[] ch = new char[100];

                try {

                    try {
                        fr = new FileReader(fdata);
                        fr.read(ch);
                    } finally {
                        fr.close();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String s = new String(ch);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();


                break;

            case R.id.buttonBufW:
                String pathW = getFilesDir().getAbsolutePath();
                File fileW = new File(pathW + File.separator + "data2.txt");
                try {
                    FileWriter fiw = new FileWriter(fileW);
                    BufferedWriter bw = new BufferedWriter(fiw);
                    bw.write("Hello world");
                    bw.newLine();
                    bw.write("Hello android");
                    bw.close();
                    fiw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.buttonBufR:
                String pathR = getFilesDir().getAbsolutePath();
                File fileR = new File(pathR + File.separator + "data2.txt");
                try {
                    FileReader fir = new FileReader(fileR);
                    BufferedReader br = new BufferedReader(fir);
                    String str;
                    while ((str = br.readLine()) != null) {
                        Log.d("F_READ", str);
                    }
                    br.close();
                    fir.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case R.id.buttonArrW:

                int[] data = {4, 1, 7, 3, 4, 8, 2, 1};      //整數陣列

                StringBuilder sb = new StringBuilder();
                for (int i : data) {
                    sb.append(i + ",");
                }

                String pathAW = getFilesDir().getAbsolutePath();
                File fileAW = new File(pathAW + File.separator + "data3.txt");
                try {
                    FileWriter fiaw = new FileWriter(fileAW);
                    BufferedWriter baw = new BufferedWriter(fiaw);
                    baw.write(sb.toString());
                    baw.close();
                    fiaw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.buttonArrR:

                ArrayList<Integer> als = new ArrayList<>();
                String pathAR = getFilesDir().getAbsolutePath();
                File fileAR = new File(pathAR + File.separator + "data3.txt");
                try {
                    FileReader fiar = new FileReader(fileAR);
                    BufferedReader bar = new BufferedReader(fiar);
                    String ss = bar.readLine();
                    String[] sarr = ss.split(",");

                    for (String se : sarr) {
                        if (se.length() > 0) {      //判定是否為空
                            als.add(Integer.valueOf(se));
                        }
                    }

                    bar.close();
                    fiar.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for (int i : als) {
                    Log.d("F_READ", "" + i);        //印出
                }

                break;


            case R.id.buttonExtW:

                String pathEW = getExternalFilesDir("MY").getAbsolutePath();
                File fileEW = new File(pathEW + File.separator + "data4.txt");
                try {
                    FileWriter few = new FileWriter(fileEW);
                    BufferedWriter bew = new BufferedWriter(few);
                    bew.write("Hello World");
                    bew.newLine();
                    bew.write("This is android");

                    bew.close();
                    few.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.buttonExtR:


                break;


            case R.id.buttonExtAdvW:


                int permissionW = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
                if (permissionW != PackageManager.PERMISSION_GRANTED) {
                    //未取得權限，向使用者要求允許權
                    ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, CODE_EXT_STO_W);
                } else {
                    //已有權限，可進行檔案存取
                    writeFile();
                }


                break;
            case R.id.buttonExtAdvR:

                int permissionR = ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
                if (permissionR != PackageManager.PERMISSION_GRANTED) {
                    //未取得權限，向使用者要求允許權
                    ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, CODE_EXT_STO_R);
                } else {
                    //已有權限，可進行檔案存取
                    readFile();
                }

                break;


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CODE_EXT_STO_W) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                writeFile();
            } else {
                //使用者拒絕權限，停用檔案存取功能
            }
        } else if (requestCode == CODE_EXT_STO_R) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                readFile();
            } else {
                //使用者拒絕權限，停用檔案存取功能
            }
        }

    }

    public void writeFile() {

        File fileEAW = new File(Environment.getExternalStorageDirectory() + File.separator + "MYDATA");    // storage/sdcard/MYDATA/
        fileEAW.mkdir();    //建立MYDATA資料夾

        File txtFile = new File(fileEAW.getAbsolutePath() + File.separator + "data5.txt");
        try {
            FileWriter feaw = new FileWriter(txtFile);
            BufferedWriter beaw = new BufferedWriter(feaw);
            beaw.write("Hello World");
            beaw.newLine();
            beaw.write("This is android");
            beaw.close();
            feaw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readFile() {

        File fileEAR = new File(Environment.getExternalStorageDirectory() + File.separator + "MYDATA");    // storage/sdcard/MYDATA/
        File txtFile = new File(fileEAR.getAbsolutePath() + File.separator + "data5.txt");
        try {
            FileReader fear = new FileReader(txtFile);
            BufferedReader bear = new BufferedReader(fear);
            String str;
            while ((str = bear.readLine()) != null) {
                Log.d("F_READ", str);
            }
            bear.close();
            fear.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



