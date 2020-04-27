package com.iss.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class MyBoundService extends Service {
    private final IBinder binder = new LocalBinder();
    ServiceCallback callback;
    Thread runner = null;
    boolean carryOn = true;

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public void setCallback(ServiceCallback callback) {
        this.callback = callback;
        this.callback.svcToActivity("Hello from Service!");
    }

    public interface ServiceCallback {
        void svcToActivity(String msg);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind... ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind... ");
        carryOn = false;

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy... ");
        super.onDestroy();
        // remove any spawned threads here
    }

    public void startTask() {
        carryOn = true;

        runner = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; carryOn; i++) {
                    callback.svcToActivity("Completed item: " + i);
                }
            }
        });
        System.out.println("Thread " + runner.getId() + " has started ...");
        runner.start();
    }

    public void stopTask() {
        carryOn = false;
    }
}
