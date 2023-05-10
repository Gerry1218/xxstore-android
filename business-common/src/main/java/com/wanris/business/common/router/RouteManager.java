package com.wanris.business.common.router;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

public class RouteManager {
    public static void startHomeActivity() {
        startActivity(RouterPath.HomeActivityPath);
    }

    public static void startHomeActivity(Serializable obj) {
        startActivity(RouterPath.HomeActivityPath, obj);
    }

    public static void startRNTestActivity() {
        startActivity(RouterPath.RNTestActivityPath);
    }

    private static void startActivity(String routePath) {
        ARouter.getInstance()
                .build(routePath)
                .navigation();
    }

    private static void startActivity(String routePath, Serializable obj) {
        ARouter.getInstance()
                .build(routePath)
                .withSerializable(ARouteKey.PARAMS, obj)
                .navigation();
    }
}
