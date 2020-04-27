package e.udayb.guessingappgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText inputNum;
    private TextView statusText;
    private int secretNum;
    private SharedPreferences preferences;

    public void buttonHandler(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("value",MODE_PRIVATE);

        inputNum =  (EditText) findViewById(R.id.inputNum);
        statusText = (TextView) findViewById(R.id.statusText);

        inputNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence string, int i, int i1, int i2) {
                try {
                    int value = Integer.parseInt(string.toString());

                    if (value == secretNum) {
                        statusText.setText("Winner!");
                    } else if (value >= secretNum)
                        {
                            statusText.setText("Try Smaller");
                        }
                       else if (value <= secretNum){
                            statusText.setText("Try Bigger");
                        }
                    
                } catch (Exception e) {
                    statusText.setText("Enter num");
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Random rnd = new Random();
        secretNum = rnd.nextInt(preferences.getInt("max seek", 10)) + 1 + preferences.getInt("min seek", 0);
    }
}
