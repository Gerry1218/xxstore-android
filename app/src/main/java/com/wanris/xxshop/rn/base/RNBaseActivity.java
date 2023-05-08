package com.wanris.xxshop.rn.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.react.PackageList;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.wanris.xxshop.MainApplication;


import java.util.ArrayList;
import java.util.List;

public class RNBaseActivity extends Activity implements DefaultHardwareBackBtnHandler, IRNBaseActivity {
    private ReactRootView mReactRootView;

    private final int OVERLAY_PERMISSION_REQ_CODE = 1;
    public static  List<RNBaseActivity> instances = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted
                }
            }
        }
        getReactInstanceManager().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注入参数
        ARouter.getInstance().inject(this);
        instances.add(this);

        mReactRootView = new ReactRootView(this);
        List<ReactPackage> packages = new PackageList(getApplication()).getPackages();
        // 有一些第三方可能不能自动链接，对于这些包我们可以用下面的方式手动添加进来：
        // packages.add(new MyReactNativePackage());
        // 同时需要手动把他们添加到`settings.gradle`和 `app/build.gradle`配置文件中。

        ReactInstanceManager reactInstanceManager = getReactInstanceManager();
        // 注意这里的MyReactNativeApp 必须对应"index.js"中的
        // "AppRegistry.registerComponent()"的第一个参数
        Bundle param = getRNParams();
        mReactRootView.startReactApplication(reactInstanceManager , "RNMain", param);

        setContentView(mReactRootView);
    }

    /**
     * 获取ReactInstanceManger实例
     *
     * @return 该对象
     */
    ReactInstanceManager getReactInstanceManager ( ) {
        return ((MainApplication)getApplication()).getReactNativeHost().getReactInstanceManager();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (getReactInstanceManager() != null) {
            getReactInstanceManager().onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getReactInstanceManager() != null) {
            getReactInstanceManager().onHostResume(this, this);
        }
    }

    @Override
    public void finish() {
        for (RNBaseActivity activity : RNBaseActivity.instances) {
            if (activity == this) {
                RNBaseActivity.instances.remove(this);
                Log.e("RNBaseActivity finish:", this.getLocalClassName());
            }
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy: ", this.getLocalClassName());
        if (getReactInstanceManager() != null) {
            getReactInstanceManager().onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }

    @Override
    public void onBackPressed() {
//        if (getReactInstanceManager() != null) {
//            getReactInstanceManager().onBackPressed();
//        } else {
            super.onBackPressed();
//        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && getReactInstanceManager() != null) {
            getReactInstanceManager().showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        finish();
    }

    @Override
    public Bundle getRNParams() {
        Bundle param = new Bundle();
        param.putString("pageName", "TestView");
        return param;
    }

    @Override
    public Boolean showSplashScreen() {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.e("RNBaseActivity:", "onPointerCaptureChanged");
    }

    public static RNBaseActivity getActivityByName(String className) {
        for (RNBaseActivity activity : RNBaseActivity.instances) {
            if (activity.getLocalClassName().contains(className)) {
                Log.e("getActivityByName:", activity.getLocalClassName());
                return activity;
            }
        }
        return null;
    }

}
