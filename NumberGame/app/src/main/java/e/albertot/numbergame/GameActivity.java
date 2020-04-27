package e.albertot.numbergame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private Button[] buttonList;
    private ImageView[] lives;
    private TextView timerText;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private Timer myTimer;
    private TimerTask myTask;

    private SharedPreferences preferences;

    private ArrayList<Integer> wholeNum;
    private ArrayList<Integer> answer;

    private MediaPlayer mp;

    private int limit;
    private int counter;
    private int mistake;
    private int score;
    private int secondsPassed;
    private int multiplier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        preferences = getSharedPreferences("value", MODE_PRIVATE);

        limit = 16;
        counter = 0;
        mistake = 0;
        multiplier = preferences.getInt("difficulty", 1);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.background);
        mp.start();
        mp.setLooping(true);

        buttonList = new Button[16];
        buttonList[0] = (Button) findViewById(R.id.button1);
        buttonList[1] = (Button) findViewById(R.id.button2);
        buttonList[2] = (Button) findViewById(R.id.button3);
        buttonList[3] = (Button) findViewById(R.id.button4);
        buttonList[4] = (Button) findViewById(R.id.button5);
        buttonList[5] = (Button) findViewById(R.id.button6);
        buttonList[6] = (Button) findViewById(R.id.button7);
        buttonList[7] = (Button) findViewById(R.id.button8);
        buttonList[8] = (Button) findViewById(R.id.button9);
        buttonList[9] = (Button) findViewById(R.id.button10);
        buttonList[10] = (Button) findViewById(R.id.button11);
        buttonList[11] = (Button) findViewById(R.id.button12);
        buttonList[12] = (Button) findViewById(R.id.button13);
        buttonList[13] = (Button) findViewById(R.id.button14);
        buttonList[14] = (Button) findViewById(R.id.button15);
        buttonList[15] = (Button) findViewById(R.id.button16);

        lives = new ImageView[3];
        lives[0] = (ImageView) findViewById(R.id.life3);
        lives[1] = (ImageView) findViewById(R.id.life2);
        lives[2] = (ImageView) findViewById(R.id.life1);

        timerText = (TextView) findViewById(R.id.timerText);

        wholeNum = new ArrayList<Integer>();
        answer = new ArrayList<Integer>();

        myTimer = new Timer();

        //shake sensor detector
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				newGrid();
            }
        });
        onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

        score = 0;
        secondsPassed = 0;
        newGrid();

    }

    public void playButton(View view) {
        Button button = (Button) view;
        String btnText = button.getText().toString();
        int btnNum = Integer.parseInt(btnText);
        checker(btnNum);
    }

    public void checker(int num) {
        //check whether the choosed number is smaller than the others or not
        if (num == answer.get(counter)) {
            //if it's correct, set the button to invisible and add the score based on the multiplier
            for (int x = 0; x<16; x++) {
                String btnText = buttonList[x].getText().toString();
                if (btnText.equals(String.valueOf(num))) {
                    buttonList[x].setVisibility(View.INVISIBLE);
                }
            }
            score+=multiplier;
            counter+=1;
            if (counter == 16) {
                counter = 0;
                limit+=5;
                myTimer.cancel();
                newGrid();
            }
        } else {
            //if it's wrong, add the mistake
            lives[mistake].setVisibility(View.INVISIBLE);
            mistake+=1;
            if (mistake == 3){
                preferences.edit().putInt("score", score).apply();
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                mp.stop();
                startActivity(intent);
            }
        }
    }

    public void newGrid() {
        //create a new grid
        wholeNum.clear();
        answer.clear();

        timerText.setText(String.valueOf(preferences.getInt("diffTime", 30)));

        if (secondsPassed < preferences.getInt("diffTime", 30)) {
            //to check if this is the first timer or the second timer
            resetTimer();
        } else {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    secondsPassed--;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timerText.setText(String.valueOf(secondsPassed));
                        }
                    });
                    if (secondsPassed == 0) {
                        //if the timer runs out, move to GameOverActivity
                        myTimer.cancel();
                        preferences.edit().putInt("score", score).apply();
                        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                        mp.stop();
                        startActivity(intent);
                    }
                }
            };
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }

        //shuffle some numbers from 1 to certain limit
        for (int x = 1; x <= limit; x++) {
            wholeNum.add(x);
        }
        Collections.shuffle(wholeNum);

        //assign the numbers to the buttons
        for (int x = 0; x < 16; x++) {
            String num = wholeNum.get(x).toString();
            answer.add(wholeNum.get(x));
            buttonList[x].setText(num);
            buttonList[x].setVisibility(View.VISIBLE);
        }

        //set to lose lives if the players make mistakes
        for (int x = mistake; x < 3; x++) {
            lives[x].setVisibility(View.VISIBLE);
        }
        Collections.sort(answer);
    }

    public void resetTimer() {
        //to reset the timer
        secondsPassed = preferences.getInt("diffTime", 30);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerText.setText(String.valueOf(secondsPassed));
                    }
                });
                if (secondsPassed == 0) {
                    myTimer.cancel();
                    preferences.edit().putInt("score", score).apply();
                    Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                    mp.stop();
                    startActivity(intent);
                }
            }
        };
        myTimer.cancel();
        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
}
