package com.iss.serviceworkshop;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyIntentService extends IntentService {
    protected final String ACTION_DOWNLOAD = "action_download";
    protected final String ACTION_PROGRESS = "action_progress";
    protected final String ACTION_DONE = "action_done";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long imageLen = 0;
        long totalSoFar = 0;
        int readLen = 0;
        String action = intent.getAction();

        if (action == null)
            return;

        if (action.compareTo(ACTION_DOWNLOAD) != 0)
            return;

        String src = intent.getStringExtra("src");
        String fpath = intent.getStringExtra("fpath");

        try {
            URL url = new URL(src);
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

                updateProgress((int)((totalSoFar * 100)/imageLen));
            }

            updateDone();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void updateProgress(int progress) {
        Intent intent = new Intent(ACTION_PROGRESS);
        intent.putExtra("percent", progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    protected void updateDone() {
        Intent intent = new Intent(ACTION_DONE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}






























