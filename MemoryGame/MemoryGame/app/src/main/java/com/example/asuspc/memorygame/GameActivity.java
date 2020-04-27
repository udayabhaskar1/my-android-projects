package com.example.asuspc.memorygame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;

    private MemoryButton[] buttons;

    private int[] buttonGraphicLocations;

    private int[] buttonGraphics;

    private MemoryButton selectedButton1;

    private MemoryButton selectedButton2;

    private boolean isBusy = false;

    private TextView screenScore;

    private TextView screenTimer;

    public int score = 0;

    private CountDownTimer cdTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.cardlayout);
        int numRows = gridLayout.getRowCount();
        int numColumn = gridLayout.getColumnCount();
        int time = SettingsScreen.timer;

        numberOfElements = numColumn * numRows;

        buttons = new MemoryButton[numberOfElements];
        buttonGraphics = new int[numberOfElements];

        screenScore = (TextView)findViewById(R.id.textViewScore);
        screenTimer = (TextView)findViewById(R.id.textViewTimer);


        screenScore.setText("Score: "+score);
        start(time);


        buttonGraphics[0] = R.drawable.e_hex;
        buttonGraphics[1] = R.drawable.e_hex;
        buttonGraphics[2] = R.drawable.e_hex;
        buttonGraphics[3] = R.drawable.e_hex;
        buttonGraphics[4] = R.drawable.e_hex;
        buttonGraphics[5] = R.drawable.e_hex;
        buttonGraphics[6] = R.drawable.e_hex;
        buttonGraphics[7] = R.drawable.e_hex;
        buttonGraphics[8] = R.drawable.e_hex;
        buttonGraphics[9] = R.drawable.e_hex;
        buttonGraphics[10] = R.drawable.e_hex;
        buttonGraphics[11] = R.drawable.e_hex;
        buttonGraphics[12] = R.drawable.e_hex;
        buttonGraphics[13] = R.drawable.e_hex;
        buttonGraphics[14] = R.drawable.e_hex;
        buttonGraphics[15] = R.drawable.e_hex;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();

        for (int r = 0; r < numRows; r++)
        {
            for(int c = 0; c < numColumn; c++)
            {
                MemoryButton tempButton = new MemoryButton(this,r,c,buttonGraphics[buttonGraphicLocations[r * numColumn + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r*numColumn+c] = tempButton;
                gridLayout.addView(tempButton);

            }
        }


    }

    private void start(int t){
        screenTimer.setText("Time: "+ t);
        cdTimer = new CountDownTimer(t * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                screenTimer.setText("Timer: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Intent intentGameEnd1 = new Intent(GameActivity.this, ScoreScreen.class);
                intentGameEnd1.putExtra("FinalScore", score+"");
                startActivity(intentGameEnd1);

            }
        }.start();
    }


    protected void shuffleButtonGraphics()
    {
        Random rand = new Random();

        for(int i = 0; i < numberOfElements; i++)
        {
            buttonGraphicLocations[i] = i % numberOfElements;

        }

        for (int i = 0; i < numberOfElements; i++)
        {
            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(16);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];

            buttonGraphicLocations[swapIndex] = temp;
        }
    }


    @Override
    public void onClick(View view) {

        if(isBusy){
            return;
        }

        MemoryButton button = (MemoryButton) view;

        if(button.isMatched)
            return;

        if(selectedButton1 == null)
        {
            selectedButton1 = button;
            selectedButton1.flip();
            return;
        }

        if(selectedButton1.getId() == button.getId())
            return;



        if(button.getFrontDrawableId() == 2131099741 && selectedButton1.getFrontDrawableId() == 2131099749)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099749  && selectedButton1.getFrontDrawableId() == 2131099741)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099750  && selectedButton1.getFrontDrawableId() == 2131099743)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099743  && selectedButton1.getFrontDrawableId() == 2131099750)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099742  && selectedButton1.getFrontDrawableId() == 2131099649)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099649  && selectedButton1.getFrontDrawableId() == 2131099742)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099744  && selectedButton1.getFrontDrawableId() == 2131099733)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099733  && selectedButton1.getFrontDrawableId() == 2131099744)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099745  && selectedButton1.getFrontDrawableId() == 2131099735)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099735  && selectedButton1.getFrontDrawableId() == 2131099745)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099746  && selectedButton1.getFrontDrawableId() == 2131099736)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099736  && selectedButton1.getFrontDrawableId() == 2131099746)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099747  && selectedButton1.getFrontDrawableId() == 2131099737)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099737  && selectedButton1.getFrontDrawableId() == 2131099747)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }

        if(button.getFrontDrawableId() == 2131099748  && selectedButton1.getFrontDrawableId() == 2131099738)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }


        if(button.getFrontDrawableId() == 2131099738  && selectedButton1.getFrontDrawableId() == 2131099748)
        {
            button.flip();
            button.setMatched(true);
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            selectedButton2.setEnabled(false);
            selectedButton1 = null;
            score = score + 1;
            screenScore.setText("Score: "+score);
            return;
        }



        else
        {
            selectedButton2 = button;
            selectedButton2.flip();
            score = score + 1;
            screenScore.setText("Score: "+score);
            isBusy = true;
            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;

                }
            },500);
        }
    }
}

