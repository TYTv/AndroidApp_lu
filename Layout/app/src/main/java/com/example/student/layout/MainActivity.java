package com.example.student.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    int cnt = 0;
    LinearLayout.LayoutParams par;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        ll = new LinearLayout(this);


        ll.setOrientation(LinearLayout.VERTICAL);
        Button b = new Button(MainActivity.this);

        par = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        b.setLayoutParams(par);

        b.setText(String.valueOf(cnt));

        ll.addView(b);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cnt++;
                Button bx = new Button(MainActivity.this);
                bx.setLayoutParams(par);
                bx.setText(String.valueOf(cnt));

                ll.addView(bx);
                setContentView(ll);

            }
        });


        setContentView(ll);


    }
}
