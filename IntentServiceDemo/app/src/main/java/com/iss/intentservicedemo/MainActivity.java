package com.iss.intentservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    protected void init() {
        Button startTaskBtn1 = findViewById(R.id.startTaskBtn1);
        if (startTaskBtn1 != null) {
            startTaskBtn1.setOnClickListener(this);
        }

        Button startTaskBtn2 = findViewById(R.id.startTaskBtn2);
        if (startTaskBtn2 != null) {
            startTaskBtn2.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.startTaskBtn1:
                intent = new Intent(this, MyIntentService.class);
                intent.setAction(Consts.START_TASK1);
                startService(intent);
                break;

            case R.id.startTaskBtn2:
                intent = new Intent(this, MyIntentService.class);
                intent.setAction(Consts.START_TASK2);
                startService(intent);
                break;
        }
    }
}
