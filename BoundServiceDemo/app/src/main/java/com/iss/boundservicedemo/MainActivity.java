package com.iss.boundservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, MyBoundService.ServiceCallback {

    MyBoundService svc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    protected void init() {
        Button bindServiceBtn = findViewById(R.id.bindServiceBtn);
        if (bindServiceBtn != null) {
            bindServiceBtn.setOnClickListener(this);
        }

        Button startTaskBtn = findViewById(R.id.startTaskBtn);
        if (startTaskBtn != null) {
            startTaskBtn.setOnClickListener(this);
        }

        Button stopTaskBtn = findViewById(R.id.stopTaskBtn);
        if (stopTaskBtn != null) {
            stopTaskBtn.setOnClickListener(this);
        }

        Button unbindServiceBtn = findViewById(R.id.unbindServiceBtn);
        if (unbindServiceBtn != null) {
            unbindServiceBtn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.bindServiceBtn:
                intent = new Intent(this, MyBoundService.class);
                bindService(intent, svcConn, BIND_AUTO_CREATE);
                break;

            case R.id.startTaskBtn:
                if (svc != null) {
                    svc.startTask();
                }
                break;

            case R.id.stopTaskBtn:
                if (svc != null) {
                    svc.stopTask();
                }
                break;

            case R.id.unbindServiceBtn:
                if (svc != null) {
                    unbindService(svcConn);
                    svc = null;
                }
                break;
        }
    }

    private ServiceConnection svcConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            if (binder != null) {
                svc = binder.getService();
                svc.setCallback(MainActivity.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            svc = null;
        }
    };

    @Override
    public void svcToActivity(String msg) {
        System.out.println("Received from BoundService: " + msg);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (svc != null) {
            unbindService(svcConn);
            svc = null;
        }
    }
}
