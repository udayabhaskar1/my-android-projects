package e.udayb.celebapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private GridView gridView;
    private ArrayAdapter<String> names;
    private ImageView imageView;
    private String[] celebNames;
    private String[] option;
    private int answer;
    private ArrayList<Integer> uniqueNum = new ArrayList<Integer>();
    private Random rnd = new Random();
    private SharedPreferences pref;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        pref = this.getActivity().getSharedPreferences("value", Context.MODE_PRIVATE);

        celebNames = new String[10];
        celebNames[0] = "Michael Jackson";
        celebNames[1] = "Emma Stone";
        celebNames[2] = "Matt Damon";
        celebNames[3] = "Gal Gadot";
        celebNames[4] = "Kristen Stewart";
        celebNames[5] = "Chris Patt";
        celebNames[6] = "Jennifer Lawrence";
        celebNames[7] = "Daniel Radcliff";
        celebNames[8] = "Emma Stone";
        celebNames[9] = "Ed Sheeran";


        imageView = (ImageView) v.findViewById(R.id.celebImage);

        names = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1);

        gridView = (GridView) v.findViewById(R.id.gridOption);
        gridView.setAdapter(names);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (answer == i) {
                    Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    gameSet();
                } else {
                    Toast.makeText(getActivity(), "Wrong", Toast.LENGTH_SHORT).show();
                    gameSet();
                }
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        gameSet();
    }

    public void gameSet() {
        names.clear();
        uniqueNum.clear();
        Resources resources = getResources();
        int prefInt = pref.getInt("choice",4);
        option = new String[prefInt];

        int rndQuestion = rnd.nextInt(5);
        String imageName = "img" + rndQuestion;

        int x = resources.getIdentifier(imageName, "drawable", getActivity().getPackageName());
        imageView.setImageResource(x);

        for (int i = 0; i < 10; i++) {
            if (i != rndQuestion) {
                uniqueNum.add(i);
            }
        }
        uniqueNum.remove(rndQuestion);
        Collections.shuffle(uniqueNum);

        answer = rnd.nextInt(prefInt);

        for (int i = 0; i < prefInt; i++) {
            if (i == answer) {
                option[i] = celebNames[rndQuestion];
            } else {
                option[i] = celebNames[uniqueNum.get(i)];
            }
            names.add(option[i]);
        }
    }
}
