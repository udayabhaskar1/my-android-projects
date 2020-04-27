package com.example.udayb.flippers;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by PRIMEPC on 24/5/2018.
 */

public class FinishAct extends Activity {
    int idName;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.finish);


        Bundle bundle = getIntent().getExtras();
        score = bundle.getInt("SCORE");

        TextView  textView = findViewById(R.id.final_score);
        textView.setText(Integer.toString(score));
        TextView textView1 = findViewById(R.id.highscore);

        SharedPreferences prefs = getSharedPreferences("SCORE", MODE_PRIVATE);


        idName = prefs.getInt("score", 0); //0 is the default value.
        textView1.setText(String.valueOf(idName));



        if(idName<score){



            SharedPreferences.Editor editor = getSharedPreferences("SCORE", MODE_PRIVATE).edit();

            editor.putInt("score", score);
            editor.apply();}












    }

    public void retry(View view){

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }

    public void exit(View view){

        System.exit(0);

    }

    public void share(View view){

        Intent tweet = new Intent(Intent.ACTION_VIEW);
        tweet.setData(Uri.parse("http://twitter.com/?status=" + Uri.encode(""+score)));//where message is your string message
        startActivity(tweet);
    }
}