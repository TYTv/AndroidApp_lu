package com.example.student.savefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

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
        }
    }
}
