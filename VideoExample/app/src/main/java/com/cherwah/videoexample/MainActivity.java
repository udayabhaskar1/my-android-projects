package com.cherwah.videoexample;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    int CAPTURE_VIDEO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);

        //ActivityCompat.requestPermissions(this,
        //        new String[]{
        //                Manifest.permission.READ_EXTERNAL_STORAGE,
        //                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        File file = new File(getFilesDir() + "/mov_bbb.mp4");
        Uri uri = FileProvider.getUriForFile(this,
                "com.cherwah.videoexample.provider", file);
        intent.setDataAndType(uri, "video/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }


//        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        startActivityForResult(intent, CAPTURE_VIDEO_REQUEST);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_VIDEO_REQUEST) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(data.getData(), "video/*");
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                // Video capture cancelled
            } else {
                // Video capture failed
            }
        }
    }
}