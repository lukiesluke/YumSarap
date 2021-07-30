package com.yumsarap;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private Typeface typefaceBold;
    private Typeface typefaceSemiBold;
    private Typeface typefaceRegular;
    private Typeface typefaceLight;

    public static Typeface typefaceBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Bold.ttf");
    }

    public static Typeface typefaceSemiBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf");
    }

    public static Typeface typefaceRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    public static Typeface typefaceLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
    }

}
