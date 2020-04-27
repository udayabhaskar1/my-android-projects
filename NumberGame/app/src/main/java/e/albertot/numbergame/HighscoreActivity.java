package e.albertot.numbergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreActivity extends AppCompatActivity {
    private ArrayAdapter<String> scores;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        scores = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(scores);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<Integer> sorted = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(this);
        String[] score = db.getAllScores();

        //get the scores from DataHelper
        for (int x = 0; x < score.length;x++) {
            sorted.add(Integer.parseInt(score[x]));
        }

        //sort the data from the highest to lowest
        Collections.sort(sorted);
        Collections.reverse(sorted);

        //show the top 10 of the scores
        for (int x = 0; x < 10; x++) {
            scores.add(String.valueOf(sorted.get(x)));
        }
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
