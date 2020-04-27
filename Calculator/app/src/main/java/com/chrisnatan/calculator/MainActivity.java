package com.chrisnatan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    float sum;

    private Button button7;
    private Button button8;
    private Button button9;

    private NumberFormat decimalFormatter = NumberFormat.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

    }

    public void ButtonPressed(View view) {
        Button button = (Button) view;
        String buttonText = (String) button.getText();
        float value = Float.parseFloat(buttonText);
        sum += value;
        EditText editText = (EditText) findViewById(R.id.summation);
        editText.setText(decimalFormatter.format(sum));

        button7.setText(R.string.seven);
        button8.setText(R.string.eight);
        button9.setText(R.string.nine);
    }

    public void ButtonPressedClear(View view) {
        TextView textView = (TextView) findViewById(R.id.summation);
        textView.setText("");
        sum = 0;
    }

    public void ButtonPressedFraction(View view){
        button7.setText(R.string.half);
        button8.setText(R.string.one_third);
        button9.setText(R.string.one_fourth);
    }

}
