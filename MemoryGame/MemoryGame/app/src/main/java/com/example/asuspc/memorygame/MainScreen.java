package com.example.asuspc.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    private Button start;
    private Button settings;
    private Button htp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        start = (Button)findViewById(R.id.buttonStart);
        settings = (Button)findViewById(R.id.buttonSettings);
        htp = (Button)findViewById(R.id.buttonHTP);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGame = new Intent(MainScreen.this, GameActivity.class);
                startActivity(intentGame);

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(MainScreen.this, SettingsScreen.class);
                startActivity(intentSettings);

            }
        });

        htp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSettings = new Intent(MainScreen.this, HowToPlayScreen.class);
                startActivity(intentSettings);

            }
        });

    }
}
