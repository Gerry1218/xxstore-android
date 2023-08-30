package com.wanris.business.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenHelper {

    private static DisplayMetrics metrics = new DisplayMetrics();

    private static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static int getScreenWidth(Context context) {
        getWindowManager(context).getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
//        return getWindowManager(context).getDefaultDisplay().getWidth();

    }

    public static int getScreenHeight(Context context) {
        getWindowManager(context).getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
//        return getWindowManager(context).getDefaultDisplay().getHeight();
    }

    public static int dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
