package com.example.gameapp.gameapp.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Siddharth Vaio on 01-09-2017.
 */

public class PixelHelper {

    public static int pixelsToDp(int px, Context context){
        return(int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics());
    }
}
