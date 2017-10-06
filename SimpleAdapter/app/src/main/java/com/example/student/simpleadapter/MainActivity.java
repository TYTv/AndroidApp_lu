package com.example.student.simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    SimpleAdapter sa;
    ArrayList<Map<String, Object>> alm = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alm.add(new HashMap<String, Object>() {
            {
                put("CITY", "台北");
                put("CODE", "02");
                put("IMG", R.drawable.taipei);
            }
        });

        alm.add(new HashMap<String, Object>() {
            {
                put("CITY", "台中");
                put("CODE", "04");
                put("IMG", R.drawable.taichung);
            }
        });
        alm.add(new HashMap<String, Object>() {
            {
                put("CITY", "高雄");
                put("CODE", "07");
                put("IMG", R.drawable.kaohsing);
            }
        });

        sa = new SimpleAdapter(
                MainActivity.this, alm,
//                android.R.layout.simple_list_item_2,      // 內建
                R.layout.item_layout,   // 自訂
                new String[]{"CITY", "CODE", "IMG"},
//                new int[]{android.R.id.text1, android.R.id.text2}                            // 內建
                new int[]{R.id.textView, R.id.textView2, R.id.imageView}     // 自訂
        );
        lv = (ListView) findViewById(R.id.ListViewMain);
        lv.setAdapter(sa);


    }
}
