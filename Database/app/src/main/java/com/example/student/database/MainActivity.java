package com.example.student.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView tv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        tv = (TextView) findViewById(R.id.textView);
        et = (EditText) findViewById(R.id.editText);

        //使用繼承Application的方式使用，manifests要設定android:name=".A"
        A a = (A)getApplication();
        String aaa = a.test1;

        //直接定義成static可以直接使用
        String aaaa = A.test0;
    }


    public void onClickInput(View view) {

        // Write a message to the database
        myRef.setValue(et.getText().toString());

    }

    public void onClickOutput(View view) {

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                tv.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                tv.setText("##########");
            }
        });

    }
}
