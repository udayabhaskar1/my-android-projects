package com.iss.serviceworkshop;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyBoundService extends Service {
    private final IBinder binder = new LocalBinder();
    ServiceCallback callback;

    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }

    public void setCallback(ServiceCallback callback) {
        this.callback = callback;
    }

    public interface ServiceCallback {
        void svcToActivity(int percent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind... ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind... ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy... ");
        super.onDestroy();
        // remove any spawned threads here
    }

    public void downloadImage(final String imageURL, final String fpath) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                long imageLen = 0;
                long totalSoFar = 0;
                int readLen = 0;

                try {
                    URL url = new URL(imageURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    imageLen = conn.getContentLength();
                    byte[] data = new byte[1024];

                    InputStream in = url.openStream();
                    BufferedInputStream bufIn = new BufferedInputStream(in, 2048);
                    OutputStream out = new FileOutputStream(fpath);

                    while ((readLen = bufIn.read(data)) != -1) {
                        totalSoFar += readLen;
                        out.write(data, 0, readLen);

                        callback.svcToActivity((int) ((totalSoFar * 100) / imageLen));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
