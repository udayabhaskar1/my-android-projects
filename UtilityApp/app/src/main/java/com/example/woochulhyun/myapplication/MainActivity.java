package com.example.woochulhyun.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void length(View view)
    {
        System.out.println("Length Clicked");
        Intent intent = new Intent(this,length.class);
        startActivity(intent);

    }

    void temperature(View view)
    {
        System.out.println("Temperature Clicked");
        Intent intent = new Intent(this,temperature.class);
        startActivity(intent);
    }

    void weight(View view)
    {
        System.out.println("Weight Clicked");
        Intent intent = new Intent(this,weight.class);
        startActivity(intent);
    }

}
