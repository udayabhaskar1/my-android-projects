package com.example.udayb.flippers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private int total = 0;
    int id = 0;
    int i = 0;
    int time;
    private ViewFlipper simpleViewFlipper;
    int[] images = {R.drawable.apple, R.drawable.banana, R.drawable.oranges};
    int[] images2 = {R.drawable.jackfruit, R.drawable.raspberry, R.drawable.mango};
    int[] images3 = {R.drawable.guava, R.drawable.grapes, R.drawable.chikoo};// array of images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.slide);


        Bundle bundle = getIntent().getExtras();
        time = bundle.getInt("time");

        System.out.print(time+"i amm herrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrre");

        TextView textView = findViewById(R.id.highscore);
        SharedPreferences prefs = getSharedPreferences("SCORE", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {

            int idName = prefs.getInt("score", 0); //0 is the default value.
            textView.setText(idName);

        }


        // get The references of ViewFlipper
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

        // loop for creating ImageView's
        for (i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);

            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView);
            System.out.print("it comes here");

        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(5000);
        // set auto start for flipping between views
        simpleViewFlipper.startFlipping();


        simpleViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {

                int displayedChild = simpleViewFlipper.getDisplayedChild();
                int childCount = simpleViewFlipper.getChildCount();

                if (displayedChild == childCount - 1) {
                    simpleViewFlipper.stopFlipping();
                    activity();


                }


            }
        });

    }


    public void activity() {
        Intent intent = new Intent(this, Question.class);
        intent.putExtra("SESSION", id);
        intent.putExtra("TIME",time);
        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            Integer x = data.getExtras().getInt("MAX");
            total += x;
            TextView textView = findViewById(R.id.score);
            textView.setText("SCORE: " + String.valueOf(total));
            id++;
            if (id > 2) {
                simpleViewFlipper.stopFlipping();
                Intent intent = new Intent(this,FinishAct.class);
                intent.putExtra("SCORE",total);
                intent.putExtra("TIME",time);
                startActivity(intent);
            }
            else if (id == 2) {
                flipper2();

            } else {
                flipper();

            }
        }

    }

    public void flipper() {
        for (i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images2[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView);
            System.out.print("it comes here");

        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(5000);
        // set auto start for flipping between views
        simpleViewFlipper.startFlipping();


        simpleViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {

                int displayedChild = simpleViewFlipper.getDisplayedChild();
                int childCount = simpleViewFlipper.getChildCount();

                if (displayedChild == childCount - 1) {
                    simpleViewFlipper.stopFlipping();
                    activity();


                }


            }
        });

    }

    public void flipper2() {
        for (i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images3[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView);
            System.out.print("it comes here");

        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(5000);
        // set auto start for flipping between views
        simpleViewFlipper.startFlipping();


        simpleViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {

                int displayedChild = simpleViewFlipper.getDisplayedChild();
                int childCount = simpleViewFlipper.getChildCount();

                if (displayedChild == childCount - 1) {
                    simpleViewFlipper.stopFlipping();
                    activity();


                }


            }
        });
    }



}



