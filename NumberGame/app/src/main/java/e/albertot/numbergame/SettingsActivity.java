package e.albertot.numbergame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("value", MODE_PRIVATE);
    }

    //set the difficulty timer for the game
    public void easyButton(View view) {
        preferences.edit().putInt("diffTime", 30).apply();
        preferences.edit().putInt("difficulty", 1).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void normalButton(View view) {
        preferences.edit().putInt("diffTime", 25).apply();
        preferences.edit().putInt("difficulty", 2).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void hardButton(View view) {
        preferences.edit().putInt("diffTime", 17).apply();
        preferences.edit().putInt("difficulty", 3).apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
