package com.example.asuspc.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingsScreen extends AppCompatActivity {

    private Button back;
    public static int timer = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);
        final RadioButton RadEasy = findViewById(R.id.radioButtonEasy);
        final RadioButton RadMedium = findViewById(R.id.radioButtonMedium);
        final RadioButton RadHard = findViewById(R.id.radioButtonHard);

        back = (Button)findViewById(R.id.buttonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RadEasy.isChecked())
                    timer = 45;
                else if (RadMedium.isChecked())
                    timer = 30;
                else if (RadHard.isChecked())
                    timer = 15;
                Intent intentMain = new Intent(SettingsScreen.this, MainScreen.class);
                startActivity(intentMain);
            }
        });

        }



    }

