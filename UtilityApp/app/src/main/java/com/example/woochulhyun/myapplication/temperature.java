package com.example.woochulhyun.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class temperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        final EditText celcius = (EditText) findViewById(R.id.celcius_input);
        final EditText fahrenheit = (EditText) findViewById(R.id.fahrenheit_input);

        celcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == celcius) {
                    try {
                        double temperature = Double.parseDouble(s.toString());
                        fahrenheit.setText(String.format("%.2f", ((temperature * (1.8))+ 32)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        fahrenheit.setText("");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //cm_input.setFocusable(true);

            }
        });

        fahrenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getCurrentFocus() == fahrenheit) {
                    try {

                        double temperature = Double.parseDouble(s.toString());
                        celcius.setText(String.format("%.2f", ((temperature - 32)/1.8)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        celcius.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}

