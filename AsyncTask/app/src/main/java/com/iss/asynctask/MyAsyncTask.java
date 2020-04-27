package com.iss.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    private WeakReference<AppCompatActivity> caller;

    public MyAsyncTask(WeakReference<AppCompatActivity> caller) {
        this.caller = caller;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        long imageLen = 0;
        long totalSoFar = 0;
        int readLen = 0;
        Bitmap bitmap = null;

        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            imageLen = conn.getContentLength();
            byte[] data = new byte[1024];

            InputStream in = url.openStream();
            BufferedInputStream bufIn = new BufferedInputStream(in, 2048);
            OutputStream out = new FileOutputStream(params[1]);

            while ((readLen = bufIn.read(data)) != -1) {
                totalSoFar += readLen;
                out.write(data, 0, readLen);

                publishProgress((int)((totalSoFar * 100)/imageLen));
            }

            File file = new File(params[1]);
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

            /*
            // This has the same effect; just that BitmapFactory is doing
            // the fetching for us as well

            InputStream in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
            */

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        AppCompatActivity activity = caller.get();
        ProgressBar bar = activity.findViewById(R.id.progressBar);
        bar.setProgress(Math.round(values[0]));
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        AppCompatActivity activity = caller.get();

        ImageView imageView = activity.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

        ProgressBar bar = activity.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);
    }
}
