package com.wanris.xxshop;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.wanris.business.common.BaseApplication;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends BaseApplication implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage()
            );
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }

        /**
         * 加载本地文件
         * @return
         */
//        @Nullable
//        @Override
//        protected String getJSBundleFile() {
//            return "assets://index.bundle";
//        }

        /**
         * @brief 加载localhost的bundle，需要启动本地metro服务
         * @return
         * @note
         *  1、如果需要加载远程bundle，需要在调试窗口 Setting->Debugging->输入host&port地址->确定后reload或重启app；
         *  2、如果无法连接本地localhost，看下AndroidManifest.xml中的<application />节点是否配置android:usesCleartextTraffic="true"
         */
        @Nullable
        @Override
        protected String getBundleAssetName() {
            return "index.bundle";
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        SoLoader.init(this, false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
