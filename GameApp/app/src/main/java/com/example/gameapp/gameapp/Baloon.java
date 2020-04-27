package com.example.gameapp.gameapp;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.gameapp.gameapp.utils.PixelHelper;

/**
 * Created by Siddharth Vaio on 01-09-2017.
 */

public class Baloon extends ImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator mAnimator;

    private BaloonListener mListener;

    private boolean mPopped;

    public Baloon(Context context) {
        super(context);
    }

    //Constructor with different set of arguments
    public Baloon(Context context, int color, int rawHeight){
        super(context);

        mListener = (BaloonListener) context;

        //Ballon Drawable Resource
        this.setImageResource(R.drawable.balloon);

        //Change the color of baloon
        this.setColorFilter(color);

        //Baloon png's height is twice as it's width
        int rawWidth = rawHeight/2;

        //Device independent pixel for Height
        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        //Device independent pixel for Width
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);

        //Use the above two values and pass them into an instance of layout params object

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);

        //Plotting small size baloon on screen on click
        setLayoutParams(params);

    }

    public void releaseBaloon(int screenHeight, int duration){
        mAnimator = new ValueAnimator();
        //Set animation duration
        mAnimator.setDuration(duration);
        mAnimator.setFloatValues(screenHeight, 0f);

        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addListener(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();

    }


    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if(!mPopped){
            mListener.popBaloon(this, false);
        }

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        setY((float) valueAnimator.getAnimatedValue());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(!mPopped && event.getAction() == MotionEvent.ACTION_DOWN){
            mListener.popBaloon(this, true);
            mPopped = true;
            mAnimator.cancel();
        }
        return super.onTouchEvent(event);
    }

    public void setPopped(boolean popped) {
        mPopped = popped;
        if(popped){
            mAnimator.cancel();
        }
    }

    public interface BaloonListener{
        void popBaloon(Baloon baloon, boolean userTouch);
    }
}
