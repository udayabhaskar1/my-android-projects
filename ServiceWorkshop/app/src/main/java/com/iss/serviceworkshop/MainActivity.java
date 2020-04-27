package com.iss.serviceworkshop;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity
    implements MyBoundService.ServiceCallback {

    MyBoundService svc = null;
    ProgressBar bar = null;
    String url = "https://images.pexels.com/photos/1413683/pexels-photo-1413683.jpeg?cs=srgb&dl=4k-wallpaper-daylight-environment-1413683.jpg&fm=jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.progressBar);
        displayImage(getFilesDir() + "/camp.jpg");

        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, svcConn, BIND_AUTO_CREATE);
    }

    @Override
    public void svcToActivity(int percent) {
        System.out.println("percent: " + percent);
        if (percent < 100)
            bar.setProgress(percent);
        else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    bar.setVisibility(View.GONE);
                    displayImage(getFilesDir() + "/relax.jpg");
                }
            });
        }
    }

    protected void displayImage(String fpath) {
        try {
            File file = new File(fpath);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            ImageView img = findViewById(R.id.imageView);
            img.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ServiceConnection svcConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            if (binder != null) {
                svc = binder.getService();
                svc.setCallback(MainActivity.this);

                svc.downloadImage(url, getFilesDir() + "/relax.jpg");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            svc = null;
        }
    };

}
