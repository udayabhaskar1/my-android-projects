package com.iss.startedservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyStartedService extends Service {
    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String action = intent.getAction();
        if (action != null) {
            if (action.compareTo(Consts.HOG_UI_THREAD) == 0) {
                hogUIThread();
            }
            else if (action.compareTo(Consts.START_TASK1) == 0) {
                startTask1();
            }
            else if (action.compareTo(Consts.START_TASK2) == 0) {
                startTask2();
            }
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyStartedService::onBind()");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void hogUIThread() {
        for (int i=0; i<50000; i++) {
            System.out.println("hogging: i = " + i);
        }
    }

    protected void startTask1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<1000; i++) {
                    System.out.println("Task1 : " + i);
                }
            }
        }).start();
    }

    protected void startTask2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<1000; i++) {
                    System.out.println("Task2 : " + i);
                }
            }
        }).start();
    }
}
