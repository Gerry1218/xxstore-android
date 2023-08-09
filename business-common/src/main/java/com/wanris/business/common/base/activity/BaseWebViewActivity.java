package com.wanris.business.common.base.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.PermissionInterceptor;
import com.just.library.WebDefaultSettingsManager;
import com.wanris.business.common.R;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;

public abstract class BaseWebViewActivity<V extends IBaseView, P extends IBasePresenter<V>> extends BaseToolbarActivity<V, P>{

    LinearLayout mWebViewLayout;
    BridgeWebView mBridgeWebView;
    AgentWeb mAgentWeb;

    protected abstract String getUrl();

    protected void handleJs() {}

    public BridgeWebView getBridgeWebView() {
        return mBridgeWebView;
    }
    public AgentWeb getAgentWeb() {
        return mAgentWeb;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_webview_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mWebViewLayout = findViewById(R.id.layout_web_view);
        createWebView();
    }

    @Override
    protected void initData() {
        super.initData();
        handleJs();
    }

    private void createWebView() {
        mBridgeWebView = new BridgeWebView(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebViewLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))//
                .useDefaultIndicator()
                .setIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setReceivedTitleCallback(receivedTitleCallback)
                .setPermissionInterceptor(permissionInterceptor) //权限拦截
                .setWebViewClient(new BaseWebViewClient(mBridgeWebView))
                .setWebView(mBridgeWebView)
                .setAgentWebSettings(getSettings())
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go(getUrl());
    }

    private ChromeClientCallbackManager.ReceivedTitleCallback receivedTitleCallback = (view, title) -> setTitle(title);


    private PermissionInterceptor permissionInterceptor = new PermissionInterceptor() {
        // AgentWeb 在触发某些敏感的 Action 时候会回调该方法， 比如定位触发 。
        // 例如 https//:www.baidu.com 该 Url 需要定位权限， 返回false ，如果版本大于等于23 ， agentWeb 会动态申请权限 ，true 该Url对应页面请求定位失败。
        // 该方法是每次都会优先触发的 ， 开发者可以做一些敏感权限拦截 。
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            return false;
        }
    };

    public AgentWebSettings getSettings() {
        return new BaseSettings();
    }

    class BaseSettings extends WebDefaultSettingsManager {
        @Override
        public String toString() {
            getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            return super.toString();
        }
    }

    class BaseWebViewClient extends BridgeWebViewClient {
        public BaseWebViewClient(BridgeWebView webView) {
            super(webView);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                // 识别Apk，直接跳转外部浏览器下载
                if (url.endsWith(".apk")) {
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                    return true;
                }
                if (url.startsWith("yy://")) {
                    return super.shouldOverrideUrlLoading(view, url);
                } else if (url.startsWith("http:") || url.startsWith("https:")) {// http和https链接内部浏览器打开
                    return false;
                } else {
                    /*
                    其余链接外部浏览器打开比如tbopen://、alipay://、baidumap://等
                    外部协议，暂时屏蔽不做任何处理
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    */
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
