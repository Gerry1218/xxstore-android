package com.wanris.xxshop.test;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.bean.WebViewParam;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.X5WebView;
import com.wanris.xxshop.R;

@Route(path = RouterPath.test_X5WebViewActivity)
public class X5WebViewActivity extends BaseActivity {

    @Autowired
    WebViewParam params;
    private X5WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.x5_webview_activity;
    }

    @Override
    protected IBasePresenter initPresenter() {
        return null;
    }

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        webView = findViewById(R.id.x5Webview);
        if (params.getUrl() != null) {
            webView.loadUrl(params.getUrl());
        }
    }

}
