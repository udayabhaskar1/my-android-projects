package com.example.udayb.flippers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by PRIMEPC on 24/5/2018.
 */

public class Question extends Activity {

    Integer score = 0;
    int s=0;
    int time=11000;
    String[] names = {"How many apples were there?","What is the color of orange","How many bananas were there?"};
    String[] answer = {"2","orange","2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.question);

        Bundle bundle = getIntent().getExtras();
        s = bundle.getInt("SESSION");


        int x = bundle.getInt("TIME");

        if(x==0){

            time = 10000;


        }

        else {
            time =x;
            System.out.println("I am herrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+time);
        }





        final TextView textView = (TextView)findViewById(R.id.question1);
        textView.setText(names[s]);




        new CountDownTimer(time, 1000) {
            TextView textView1= findViewById(R.id.countdown);

            public void onTick(long millisUntilFinished) {
                textView1.setText(""+millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

        public void onFinish() {
            textView1.setText("Times up!");
        }

}.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Question.this, MainActivity.class);
                intent.putExtra("MAX",-10);



                setResult(2,intent);
                finish();

            }
        }, time);




    }

    public void onMove(View view) {

        EditText editText = findViewById(R.id.edit_query);

        if(editText.getText().toString().equals(answer[s])){

            score=10;

        }else {
            score=-10;
        }

        Intent intent = new Intent(Question.this, MainActivity.class);
        intent.putExtra("MAX",score);



        setResult(2,intent);
        finish();

    }
}
