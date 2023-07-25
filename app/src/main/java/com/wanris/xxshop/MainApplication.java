package com.wanris.xxshop;

import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
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
        _initSDK();
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

    private void _initSDK() {
        /* 设置允许移动网络下进行内核下载。默认不下载，会导致部分一直用移动网络的用户无法使用x5内核 */
        QbSdk.setDownloadWithoutWifi(true);

        QbSdk.setCoreMinVersion(QbSdk.CORE_VER_ENABLE_202112);
        /* SDK内核初始化周期回调，包括 下载、安装、加载 */

        QbSdk.setTbsListener(new TbsListener() {

            /**
             * @param stateCode 用户可处理错误码请参考{@link com.tencent.smtt.sdk.TbsCommonCode}
             */
            @Override
            public void onDownloadFinish(int stateCode) {
                Log.i("MainApplication", "onDownloadFinished: " + stateCode);
            }

            /**
             * @param stateCode 用户可处理错误码请参考{@link com.tencent.smtt.sdk.TbsCommonCode}
             */
            @Override
            public void onInstallFinish(int stateCode) {
                Log.i("MainApplication", "onInstallFinished: " + stateCode);
            }

            /**
             * 首次安装应用，会触发内核下载，此时会有内核下载的进度回调。
             * @param progress 0 - 100
             */
            @Override
            public void onDownloadProgress(int progress) {
                Log.i("MainApplication", "Core Downloading: " + progress);
            }
        });

        /* 此过程包括X5内核的下载、预初始化，接入方不需要接管处理x5的初始化流程，希望无感接入 */
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖wifi网络下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * 内核下发请求发起有24小时间隔，卸载重装、调整系统时间24小时后都可重置
             * 调试阶段建议通过 WebView 访问 debugtbs.qq.com -> 安装线上内核 解决
             * @param isX5 是否使用X5内核
             */
            @Override
            public void onViewInitFinished(boolean isX5) {
                Log.i("MainApplication", "onViewInitFinished: " + isX5);
                // hint: you can use QbSdk.getX5CoreLoadHelp(context) anytime to get help.
            }
        });
    }
}
