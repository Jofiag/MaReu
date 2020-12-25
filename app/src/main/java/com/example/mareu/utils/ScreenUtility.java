package com.example.mareu.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtility {
    private static float dpWidth;
    private static float dpHeight;

    public ScreenUtility(Activity activity) {
        setWidthAndHeight(activity);
    }

    private void setWidthAndHeight(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        //Display display = activity.getDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        display.getMetrics(displayMetrics);

        float density = activity.getResources().getDisplayMetrics().density;

        dpWidth = displayMetrics.widthPixels / density;
        dpHeight = displayMetrics.heightPixels / density;
    }

    public static float getDpWidth() {
        return dpWidth;
    }

    public static float getDpHeight() {
        return dpHeight;
    }
}

