package com.example.woochulhyun.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class weight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        final EditText milligram = (EditText) findViewById(R.id.mg_input);
        final EditText gram = (EditText) findViewById(R.id.g_input);
        final EditText kilogram = (EditText) findViewById(R.id.kg_input);

        milligram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == milligram) {
                    try {
                        double weight = Double.parseDouble(s.toString());
                        gram.setText(String.format("%.2f", (weight / 1000)));
                        kilogram.setText(String.format("%.2f", (weight / 1000000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        gram.setText("");
                        kilogram.setText("");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //cm_input.setFocusable(true);

            }
        });

        gram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getCurrentFocus() == gram) {
                    try {

                        double weight = Double.parseDouble(s.toString());
                        milligram.setText(String.format("%.2f", (weight / 0.001)));
                        kilogram.setText(String.format("%.2f", (weight / 1000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        milligram.setText("");
                        kilogram.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        kilogram.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getCurrentFocus() == kilogram) {
                    try {
                        double weight = Double.parseDouble(s.toString());
                        gram.setText(String.format("%.2f", (weight * 1000)));
                        milligram.setText(String.format("%.2f", (weight * 1000000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        gram.setText("");
                        milligram.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
