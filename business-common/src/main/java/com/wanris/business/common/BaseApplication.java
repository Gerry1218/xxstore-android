package com.wanris.business.common;

import android.app.Application;

/**
 * Description: 基类Application
 *
 * @Author: Gerry
 * @Date: 2022/12/12
 */
public class BaseApplication extends Application {
    protected static BaseApplication instance;

    public static BaseApplication getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
