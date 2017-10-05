package com.example.student.progressbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    static AtomicInteger ai;

    ProgressBar pb;
    ProgressBar pbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pbh = (ProgressBar) findViewById(R.id.progressBarH);

    }


    public void onClickAll(View view) {
        switch (view.getId()) {
            case R.id.buttonStart:
                pb.setVisibility(View.VISIBLE);
                break;
            case R.id.buttonEnd:
                pb.setVisibility(View.INVISIBLE);
                break;
            case R.id.buttonAuto:
                pb.setVisibility(View.VISIBLE);

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                pb.setVisibility(View.INVISIBLE);

                            }
                        });

                    }
                }.start();
                break;
            case R.id.buttonH:

                pbh.setVisibility(View.VISIBLE);
                pbh.setProgress(pbh.getProgress() + 10);

                break;

            case R.id.buttonProgDial:

                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("這個不能取消，一定要等哦!!");
                pd.setMessage("執行中，請稍候...");
                pd.setCancelable(false);
                pd.show();

                new Thread(){
                    @Override
                    public void run() {
//                        super.run();

                        try {
                            Thread.sleep(3000);     // 執行耗時工作中...
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                pd.dismiss();       // 關閉 dialog

                            }
                        });

                    }
                }.start();

                break;


        }


    }


}
