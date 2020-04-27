package com.cherwah.videoexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    int CAPTURE_VIDEO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, CAPTURE_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == CAPTURE_VIDEO_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri videoUri = intent.getData();
                VideoView videoView = findViewById(R.id.videoView1);
                videoView.setVideoURI(videoUri);

                MediaController mc = new MediaController(this);
                mc.setAnchorView(videoView);
                videoView.setMediaController(mc);

                videoView.start();
            }
        }
    }
}
