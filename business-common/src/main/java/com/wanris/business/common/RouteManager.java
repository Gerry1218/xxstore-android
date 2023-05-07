package com.wanris.business.common;

import com.alibaba.android.arouter.launcher.ARouter;

public class RouteManager {
    public static void startHomeActivity() {
        ARouter.getInstance()
                .build("/home/HomeActivity")
                .navigation();
    }

    public static void startRNTestActivity() {
        ARouter.getInstance()
                .build("/rn/RNTestActivity")
                .navigation();
    }
}
