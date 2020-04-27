package com.example.gameapp.gameapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.gameapp.utils.HighScoreHelper;
import com.example.gameapp.gameapp.utils.SimpleAlertDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Baloon.BaloonListener{

    private static final int MIN_ANIMATION_DELAY = 500;
    private static final int MAX_ANIMATION_DELAY = 1500;
    private static final int MIN_ANIMATION_DURATION = 1000;
    private static final int MAX_ANIMATION_DURATION = 8000;
    private static final int NUMBER_OF_PINS = 5;
    private static final int BALLOONS_PER_LEVEL = 4;

    //ViewGroup is the super class of Layouts i.e Linear Layout, Relative Layout, Constraint Layout
    private ViewGroup mContentView;

    private int[] mBaloonColors = new int[3];

    //Keeping track of baloon color
    private int mNextColor;

    //Variables for storing screen width and height
    private int mScreenWidth, mScreenHeight;

    private int mLevel,mScore, mPinsUsed;
    TextView mScoreDisplay, mLevelDisplay;
    private List<ImageView> mPinImages = new ArrayList<>();
    private List<Baloon> mBalloons = new ArrayList<>();
    private Button mGoButton;
    private boolean mPlaying;
    private boolean mGameStopped = true;
    private int mBalloonsPopped;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting colors in array index
        mBaloonColors[0]= Color.argb(255, 255, 0, 0); //(opacity, red, green, blue)
        mBaloonColors[1]= Color.argb(255, 0, 255, 0);
        mBaloonColors[2]= Color.argb(255, 0, 0, 255);

        //Setting our own background to the game
        getWindow().setBackgroundDrawableResource(R.drawable.modern_background);

        //Reference to activity_main
        mContentView = (ViewGroup) findViewById(R.id.activity_main);

        setToFullScreen();
        //Code to get height and width of screen
        ViewTreeObserver viewTreeObserver = mContentView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    mScreenWidth=mContentView.getWidth();
                    mScreenHeight=mContentView.getHeight();
                }
            });
        }


        //Clicking on screen will make screen full screen
        mContentView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setToFullScreen();
          }
        });

        mScoreDisplay = (TextView) findViewById(R.id.score_display);
        mLevelDisplay = (TextView) findViewById(R.id.level_display);
        
        //Adding images to List
        mPinImages.add((ImageView) findViewById(R.id.pushpin1));
        mPinImages.add((ImageView) findViewById(R.id.pushpin2));
        mPinImages.add((ImageView) findViewById(R.id.pushpin3));
        mPinImages.add((ImageView) findViewById(R.id.pushpin4));
        mPinImages.add((ImageView) findViewById(R.id.pushpin5));

        mGoButton= (Button) findViewById(R.id.go_button);

        updateDisplay();

        //On Screen Touch making plotting the baloon
        /*mContentView.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_UP) {
                    Baloon b = new Baloon(MainActivity.this, mBaloonColors[mNextColor], 100);//0xFF(transparency) FF(Red) 00(Green) 00(Blue)
                    //Set baloons location on screen based on where user touches the screen
                    b.setX(motionEvent.getX());
                    b.setY(mScreenHeight);

                    //add baloon to screen
                    mContentView.addView(b);

                    b.releaseBaloon(mScreenHeight, 3000);

                    //condition asking if mNextColor is equal to lenght of array than text color will be of array index 0
                    if(mNextColor+1==mBaloonColors.length){
                        mNextColor=0;
                    }
                    else{
                        mNextColor++;
                    }
                }

                return false;
            }
        });*/
    }
    //Method to make a full screen
    private void setToFullScreen(){

        //Making the activity_main always go full screen by making object of the ViewGroup class (i.e rootLayout) and passing the id to it.
        ViewGroup rootLayout = (ViewGroup) findViewById(R.id.activity_main);
        rootLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }

    private void startGame(){
        setToFullScreen();
        mScore = 0;
        mLevel = 0;
        mPinsUsed = 0;
        for(ImageView pin:
                mPinImages){
            pin.setImageResource(R.drawable.pin);
        }
        mGameStopped=false;
        startLevel();
    }

    private void startLevel(){
        mLevel++;
        updateDisplay();
        BaloonLauncher launcher = new BaloonLauncher();
        launcher.execute(mLevel);
        mPlaying=true;
        mBalloonsPopped = 0;
        mGoButton.setText("Stop Game");
    }

    private void finishLevel(){
        Toast.makeText(this, String.format("You Finished level %d", mLevel), Toast.LENGTH_SHORT).show();
        mPlaying=false;
        Toast.makeText(MainActivity.this, ""+"Baloon speed will increase in next level.", Toast.LENGTH_SHORT).show();
        mGoButton.setBackgroundColor(0xFF00FF00);
        mGoButton.setText(String.format("Start Level %d", mLevel+1));
    }
    public void goButtonClickHandler(View view) {
        if(mPlaying){
            gameOver(false);
        }else if(mGameStopped){
            startGame();
        }else{
            startLevel();
        }

    }

    @Override
    public void popBaloon(Baloon baloon, boolean userTouch) {

        mBalloonsPopped++;
        mContentView.removeView(baloon);
        //Adding the balloon on creation to list
        mBalloons.remove(baloon);
        if(userTouch){
            mScore++;
        }else{
            mPinsUsed++;
            if(mPinsUsed <= mPinImages.size()){
                
                //setting strike off image on current pin image
                mPinImages.get(mPinsUsed - 1).setImageResource(R.drawable.pin_off);
            }
            
            if(mPinsUsed == NUMBER_OF_PINS){
                gameOver(true);
                return;
            }else{
                Toast.makeText(this, "Missed that One!!", Toast.LENGTH_SHORT).show();
            }
        }
        updateDisplay();

        if(mBalloonsPopped == BALLOONS_PER_LEVEL){
            finishLevel();
        };
    }

    private void gameOver(boolean allPinsUsed) {

        Toast.makeText(this, "Game Over!!", Toast.LENGTH_SHORT).show();
        for(Baloon balloon: mBalloons){
            mContentView.removeView(balloon);
            balloon.setPopped(true);
        }
        mBalloons.clear();
        mPlaying=false;
        mGameStopped = true;
        mGoButton.setText("Start Game");

        if(allPinsUsed){
            if(HighScoreHelper.isTopScore(this,mScore)){
                HighScoreHelper.setTopScore(this, mScore);
                SimpleAlertDialog dialog = SimpleAlertDialog.newInstance("New high Score",String.format("Your new high score is %d",mScore));
                dialog.show(getSupportFragmentManager(),null);
            }
        }
    }

    private void updateDisplay() {
        mScoreDisplay.setText(String.valueOf(mScore));
        mLevelDisplay.setText(String.valueOf(mLevel));

    }

    private class BaloonLauncher extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            if(params.length!=1){
                throw new AssertionError("Expected 1 param for current level");
            }
            int level = params[0];
            int maxDelay = Math.max(MIN_ANIMATION_DELAY, MAX_ANIMATION_DELAY - ((level - 1)*500));
            int minDelay = maxDelay/2;

            int baloonsLaunched = 0;
            while(mPlaying && baloonsLaunched<BALLOONS_PER_LEVEL){

                //Get a random horizontal position fot the next baloon
                Random random = new Random(new Date().getTime());
                int xPosition =random.nextInt(mScreenWidth - 200);
                publishProgress(xPosition);
                baloonsLaunched++;

                //wait a random number of miliseconds before looping
                int delay = random.nextInt(minDelay) + minDelay;
                try{
                    Thread.sleep(delay);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int xPosition = values[0];
            launchBalloon(xPosition);
        }
    }

    private void launchBalloon(int x){
        Baloon baloon = new Baloon(this, mBaloonColors[mNextColor],150);
        mBalloons.add(baloon);
        if(mNextColor +1 == mBaloonColors.length){
            mNextColor = 0;
        }else{
            mNextColor++;
        }

        //Set baloon vertical position and dimensions, add to container
        baloon.setX(x);
        baloon.setY(mScreenHeight + baloon.getHeight());
        mContentView.addView(baloon);

        //Let baloon fly
        int duration = Math.max(MIN_ANIMATION_DURATION, MAX_ANIMATION_DURATION - (mLevel * 1000));
        baloon.releaseBaloon(mScreenHeight, duration);
    }
}
