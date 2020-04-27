package com.iss.startedservicedemo;

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
        Button hogThreadBtn = findViewById(R.id.hogUIThread);
        if (hogThreadBtn != null) {
            hogThreadBtn.setOnClickListener(this);
        }

        Button startServiceOnThread1 = findViewById(R.id.startServiceOnThread1);
        if (startServiceOnThread1 != null) {
            startServiceOnThread1.setOnClickListener(this);
        }

        Button startServiceOnThread2 = findViewById(R.id.startServiceOnThread2);
        if (startServiceOnThread2 != null) {
            startServiceOnThread2.setOnClickListener(this);
        }

        Button stopService = findViewById(R.id.stopService);
        if (stopService != null) {
            stopService.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.hogUIThread:
                intent = new Intent(this, MyStartedService.class);
                intent.setAction(Consts.HOG_UI_THREAD);
                startService(intent);
                break;

            case R.id.startServiceOnThread1:
                intent = new Intent(this, MyStartedService.class);
                intent.setAction(Consts.START_TASK1);
                startService(intent);
                break;

            case R.id.startServiceOnThread2:
                intent = new Intent(this, MyStartedService.class);
                intent.setAction(Consts.START_TASK2);
                startService(intent);
                break;

            case R.id.stopService:
                intent = new Intent(this, MyStartedService.class);
                stopService(intent);
                break;
        }
    }
}
