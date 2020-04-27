package com.example.uicontrols1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_list);

        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;
        String[] values = extras.getStringArray("list");
        if (values == null)
            return;

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,values);

        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
       String item = (String) getListAdapter().getItem(position);
        Toast.makeText(MyListActivity.this, "You selected: " + item, Toast.LENGTH_SHORT).show();
    }


}
