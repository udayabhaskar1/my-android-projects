package com.example.asuspc.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlayScreen extends AppCompatActivity {

    private Button back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_screen);

        back2 = (Button)findViewById(R.id.buttonBack2);

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(HowToPlayScreen.this, MainScreen.class);
                startActivity(intentMain);
            }
        });

    }
}
