package com.wanris.business.common.router;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

public class RouteManager {
    public static Object startHomeActivity() {
        return startActivity(RouterPath.HomeActivityPath);
    }

    public static Object startHomeActivity(Serializable obj) {
        return startActivity(RouterPath.HomeActivityPath, obj);
    }

    public static Object startGoodsDetailActivity(Serializable obj) {
        return startActivity(RouterPath.GoodsDetailActivity, obj);
    }

    public static Object startRNTestActivity() {
        return startActivity(RouterPath.RNTestActivityPath);
    }

    public static Object startHomeFragment() {
        return startFragment(RouterPath.HomeHomeFragment);
    }

    public static Object startHomeChatFragment() {
        return startFragment(RouterPath.HomeChatFragment);
    }

    public static Object startHomeCartFragment() {
        return startFragment(RouterPath.HomeCartFragment);
    }

    public static Object startHomeVideoFragment() {
        return startFragment(RouterPath.HomeVideoFragment);
    }

    public static Object startHomeMineFragment() {
        return startFragment(RouterPath.HomeMineFragment);
    }

    public static Object startWebViewActivity(Serializable obj) {
        return startActivity(RouterPath.WebViewActivity, obj);
    }

    public static Object startX5WebViewActivity(Serializable obj) {
        return startActivity(RouterPath.test_X5WebViewActivity, obj);
    }

    private static Object startActivity(String routePath) {
        return ARouter.getInstance()
                .build(routePath)
                .navigation();
    }

    private static Object startFragment(String routePath) {
        return ARouter.getInstance()
                .build(routePath)
                .navigation();
    }

    private static Object startActivity(String routePath, Serializable obj) {
        return ARouter.getInstance()
                .build(routePath)
                .withSerializable(ARouteKey.PARAMS, obj)
                .navigation();
    }
}
