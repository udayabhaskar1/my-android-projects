package com.example.gameapp.gameapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Siddharth Vaio on 01-09-2017.
 */

public class HighScoreHelper {

    private static final String PREFS_GLOBAL = "prefs_global";
    private static final String PREF_TOP_SCORE = "pref_top_score";

    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences(PREFS_GLOBAL, context.MODE_PRIVATE);
    }

    //Setters and Getters for global preferences
    public static boolean isTopScore(Context context, int newScore){
        int topScore = getPreferences(context).getInt(PREF_TOP_SCORE,0);
        return newScore>topScore;
    }

    public static int getTopScore(Context context){
        return getPreferences(context).getInt(PREF_TOP_SCORE, 0);
    }

    public static void setTopScore(Context context, int score){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(PREF_TOP_SCORE,score);
        editor.apply();
    }
}
