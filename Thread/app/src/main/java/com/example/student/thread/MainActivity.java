package com.example.student.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvcd;
    TextView tvrou;
    TextView tvhan;
    TextView tvast;
    MyTask mt = null;
    Handler han;


    CountDownTimer cdt = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvcd = (TextView) findViewById(R.id.TextViewConutDown);
        tvrou = (TextView) findViewById(R.id.TextViewRunOnUi);
        tvhan = (TextView) findViewById(R.id.TextViewHandler);
        tvast = (TextView) findViewById(R.id.TextViewAsyncTask);


        han = new Handler();


    }

    public void onClickCountDown(View view) {

        if (cdt != null) {
            return;
        }

        cdt = new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {
                tvcd.setText("Sec:" + l / 1000);      // This Code Run On MainActivity Thread
            }

            @Override
            public void onFinish() {
                tvcd.setText("done!");                // This Code Run On MainActivity Thread
                cdt = null;
            }

        }.start();


    }

    public void onClickRunOnUi(View view) {

        new Thread() {

            int i = 10;

            @Override
            public void run() {
                super.run();

                do {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            tvrou.setText(String.valueOf(i));       // Ui Control need use "runOnUiThread"
                            i--;

                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } while (i > 0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        tvrou.setText("done!");       // Ui Control need use "runOnUiThread"

                    }
                });

            }
        }.start();

    }

    public void onClickHandler(View view) {

        new Thread() {

            int i = 10;

            @Override
            public void run() {
                super.run();

                do {

                    han.post(new Runnable() {       //Use Handler run to MainActivity Thread
                        @Override
                        public void run() {

                            tvhan.setText(String.valueOf(i));       // Ui Control run on MainActivity Thread
                            i--;

                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } while (i > 0);

                han.post(new Runnable() {    //Use Handler run to MainActivity Thread
                    @Override
                    public void run() {

                        tvhan.setText("done!");       // Ui Control run on MainActivity Thread

                    }
                });

            }
        }.start();

    }

    public void onClickAsyncTask(View view) {

        switch (view.getId()) {
            case R.id.buttonAsync:
                if (mt == null) {
                    mt = new MyTask(tvast);
                    mt.execute(10);
                }
                break;
            case R.id.buttonAsyncCancel:
                if (mt != null) {
                    mt.cancel(false);

                }
                break;
        }

    }

    class MyTask extends AsyncTask<Integer, Integer, String> {

        TextView tv;

        public MyTask(TextView tv) {
            this.tv = tv;
        }

        @Override
        protected String doInBackground(Integer... integers) {      //主要耗時工作執行
//            return null;

            for (int i = integers[0]; i >= 0; i--) {

                Log.d("LOG", "i=" + i);
                publishProgress(i);             //執行更新進度onProgressUpdate
                if (isCancelled() == true) {    //中途取消
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return "OK";

        }


        @Override
        protected void onProgressUpdate(Integer... values) {    //耗時工作執行中更新進度條
            super.onProgressUpdate(values);

            tv.setText(values[0].toString());

        }

        @Override
        protected void onPostExecute(String s) {    //結束主要耗時工作後執行，並接收結果，此方法會在主執行緒上執行
            super.onPostExecute(s);

            Log.d("LOG", s);
            tv.setText(s);
            mt = null;

        }

        @Override
        protected void onCancelled(String s) {      //取消後處理，並接收主耗時工作回傳值
            super.onCancelled(s);

            tv.setText("Cancelled!");
            mt = null;

        }



    }

}
