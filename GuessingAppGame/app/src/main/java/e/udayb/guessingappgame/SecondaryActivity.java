package e.udayb.guessingappgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    private SeekBar minSeek;
    private SeekBar maxSeek;
    private TextView minText;
    private TextView maxText;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        preferences = getSharedPreferences("value",MODE_PRIVATE);

        preferences.edit().putInt("min seek", 0).apply();
        preferences.edit().putInt("max seek", 10).apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        maxSeek = (SeekBar) findViewById(R.id.maxSeek);
        minSeek = (SeekBar) findViewById(R.id.minSeek);
        minText = (TextView) findViewById(R.id.minText);
        maxText = (TextView) findViewById(R.id.maxText);

        minSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                preferences.edit()
                        .putInt("min seek", seekBar.getProgress())
                        .apply();
                int value = preferences.getInt("min seek",0);
                String valueString = Integer.toString(value);
                minText.setText(valueString);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                preferences.edit()
                        .putInt("max seek", seekBar.getProgress())
                        .apply();
                int value = preferences.getInt("max seek", 10);
                String valueString = Integer.toString(value);
                maxText.setText(valueString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void buttonBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
