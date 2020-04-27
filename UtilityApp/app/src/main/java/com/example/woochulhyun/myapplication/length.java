package com.example.woochulhyun.myapplication;

/**
 * Created by WOOCHUL HYUN on 2017-12-09.
 */

        import android.icu.text.DecimalFormat;
        import android.os.SystemClock;
        import android.renderscript.Double2;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.EditText;

public class length extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        final EditText mm_input = (EditText) findViewById(R.id.mm_input);
        final EditText cm_input = (EditText) findViewById(R.id.cm_input);
        final EditText m_input = (EditText) findViewById(R.id.m_input);
        final EditText km_input = (EditText) findViewById(R.id.km_input);


        mm_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (getCurrentFocus() == mm_input) {
                    try {
                        double length = Double.parseDouble(s.toString());
                        cm_input.setText(String.format("%.2f", (length / 10)));
                        m_input.setText(String.format("%.2f", (length / 1000)));
                        km_input.setText(String.format("%.2f", (length / 1000000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        cm_input.setText("");
                        m_input.setText("");
                        km_input.setText("");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //cm_input.setFocusable(true);

            }
        });

        cm_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getCurrentFocus() == cm_input) {
                    try {

                        double length = Double.parseDouble(s.toString());
                        mm_input.setText(String.format("%.2f", (length * 10)));
                        m_input.setText(String.format("%.2f", (length / 100)));
                        km_input.setText(String.format("%.2f", (length / 100000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        mm_input.setText("");
                        m_input.setText("");
                        km_input.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        m_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getCurrentFocus() == m_input) {
                    try {
                        double length = Double.parseDouble(s.toString());

                        cm_input.setText(String.format("%.2f", (length * 100)));
                        mm_input.setText(String.format("%.2f", (length * 1000)));
                        km_input.setText(String.format("%.2f", (length / 1000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        cm_input.setText("");
                        mm_input.setText("");
                        km_input.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        km_input.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println("Inside KM");
                if(getCurrentFocus() == km_input) {
                    System.out.println("Inside KM1");
                    try {
                        double length = Double.parseDouble(s.toString());

                        cm_input.setText(String.format("%.2f", (length * 100000)));
                        mm_input.setText(String.format("%.2f", (length * 1000000)));
                        m_input.setText(String.format("%.2f", (length * 1000)));
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                        cm_input.setText("");
                        mm_input.setText("");
                        m_input.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}


