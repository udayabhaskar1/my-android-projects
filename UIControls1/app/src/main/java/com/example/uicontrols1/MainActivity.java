package com.example.uicontrols1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

   String[] values = new String[] {
            "Apple",
            "Orange",
            "Pear",
            "Blueberry",
            "Banana",
            "Mango",
            "Strawberry",
            "Apricot",
            "Durian",
            "Lemon",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button long_press_btn = findViewById(R.id.long_press_btn);
        registerForContextMenu(long_press_btn);

        Button list_activity1_btn = findViewById(R.id.listActivityBtn1);
        list_activity1_btn.setOnClickListener(this);

        Button list_activity2_btn = findViewById(R.id.listActivityBtn2);
        list_activity2_btn.setOnClickListener(this);

        Button list_activity_btn3 = findViewById(R.id.listActivityBtn3);
        list_activity_btn3.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        /*int requestCode = 1;

        Intent intent = new Intent(this, MyListActivity.class);
        intent.putExtra("list", values);
        startActivityForResult(intent, requestCode);*/

        int requestCode;
        Intent intent = null;

        switch(v.getId()) {
            case R.id.listActivityBtn1:
                requestCode = 1;
                intent = new Intent(this, MyListActivity.class);
                intent.putExtra("list",values);
                startActivityForResult(intent, requestCode);
                break;

            case R.id.listActivityBtn2:
                requestCode = 2;
                intent = new Intent(this, MyListActivity1.class);
                intent.putExtra("list",values);
                startActivityForResult(intent, requestCode);
                break;

            case R.id.listActivityBtn3:
                requestCode = 3;
                intent = new Intent(this, MyListActivity2.class);
                startActivityForResult(intent, requestCode);
                break;

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menu,v,menuinfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public void showAlert(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Change Settings")
                .setMessage("Are you sure you want to change settings")
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                .setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.say_hello:
                String helloMsg = getString(R.string.hello_msg);
                Toast t = Toast.makeText(this, helloMsg, Toast.LENGTH_LONG);
                t.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String selected = null;

        switch(item.getItemId()){
            case R.id.menu_item_upload:
                selected = getString(R.string.upload);
                break;

            case R.id.menu_item_search:
                selected = getString(R.string.search);
                break;
        }

        if (selected != null) {
            Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
