package com.wanris.module.web;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.activity.BaseWebViewActivity;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.bean.WebViewParam;
import com.wanris.business.common.router.RouterPath;

@Route(path = RouterPath.WebViewActivity)
public class WebViewActivity extends BaseWebViewActivity {

    @Autowired
    public WebViewParam params;

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
        mCommonToolbar.setRightButtonText("帮助");
    }

    @Override
    protected String getUrl() {
        return params.getUrl();
    }

    @Override
    protected void handleJs() {
        super.handleJs();
        getBridgeWebView().registerHandler("callNative", (data, cb)->{
            Log.d(TAG, "handleJs: "+ data);
            cb.onCallBack("");
        });
    }

    protected void callJS() {
        getBridgeWebView().callHandler("callJS", null, (data) -> {
            Log.d(TAG, "callJS: " + data);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onRightButtonClick() {
        Toast.makeText(instance, "点击帮助按钮", Toast.LENGTH_SHORT).show();
    }
}
