package com.example.uicontrols1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class P1Activity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    CheckBox checkbox1;
    public void onClick(View v) {
        checkbox1.toggle();
    }


    /* Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);
        button1 = (Button) findViewById(R.id.button1);
        checkbox1 = (CheckBox) findViewById(R.id.checkBox1);
        button1.setOnClickListener(this);
    }
}
