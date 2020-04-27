package com.example.uicontrols1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyListActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list2);

        List<Person> list = new ArrayList<Person>();
        list.add(new Person("Jeremy Tan", "Yogi Bear"));
        list.add(new Person("James Wong", "Incredible Hulk"));
        list.add(new Person("Lily Yoe", "Wonder woman"));

        ListView listView = findViewById(R.id.listView2);
        listView.setAdapter(new SimpleAdapter(this, list, R.layout.row2,
                new String[] {"name", "nickname"},
                new int[] {R.id.textView1, R.id.textView2}));
    }
}
