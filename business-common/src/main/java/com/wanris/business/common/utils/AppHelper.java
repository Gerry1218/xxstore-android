package com.wanris.business.common.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by Gerry on 2020/12/12 12:00
 */
public class AppHelper {

    public static final String TAG = "AppHelper";

    public static boolean runTimeDebug = false;

    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值 ， 或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context) {
        if (context == null) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }

    private static String channel = null;

    public static String getChannel(Context context) {
        if (context == null) {
            return "hw";
        }
        if (channel != null) {
            return channel;
        }

        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo.metaData != null) {
                    channel = applicationInfo.metaData.getString("UMENG_CHANNEL");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "getChannel: ",e );
        }
        if (channel == null) {
            return "hw";
        }
        return channel;

    }
}