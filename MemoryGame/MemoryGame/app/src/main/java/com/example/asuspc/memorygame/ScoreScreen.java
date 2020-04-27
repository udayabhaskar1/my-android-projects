package com.example.asuspc.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private Button retry;
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        TextView fScore = findViewById(R.id.textViewFinalScore);

        fScore.setText(getIntent().getExtras().getString("FinalScore"));

        retry = (Button)findViewById(R.id.buttonRetry);
        exit = (Button)findViewById(R.id.buttonExit);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGame = new Intent(ScoreScreen.this, GameActivity.class);
                startActivity(intentGame);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGame = new Intent(ScoreScreen.this, MainScreen.class);
                startActivity(intentGame);

            }
        });
    }
}
