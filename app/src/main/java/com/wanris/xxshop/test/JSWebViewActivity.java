package com.wanris.xxshop.test;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.wanris.business.common.base.activity.BaseWebViewActivity;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.bean.WebViewParam;
import com.wanris.business.common.router.RouterPath;

@Route(path = RouterPath.test_JSWebViewActivity)
public class JSWebViewActivity extends BaseWebViewActivity {

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
            try {
                JSONObject obj = JSONObject.parseObject(data);
                String action = obj.getString("action");
                JSONObject params = obj.getJSONObject("params");
                if (action == null) {
                    Toast.makeText(this, "非法action", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "handleJs: action=" + action + ",params=" + params.toJSONString());
                JSONObject cbData = new JSONObject();
                cbData.put("platform", "android");
                cb.onCallBack(cbData.toJSONString());
            }
            catch (Exception e) {
                Log.d(TAG, "handleJs: " + e.getLocalizedMessage());
            }
        });
    }

    protected void callJS() {
        JSONObject obj = new JSONObject();
        obj.put("platform", "android call js");
        getBridgeWebView().callHandler("callJS", obj.toJSONString(), (data) -> {
            Log.d(TAG, "callJS: " + data);
            Toast.makeText(this, "收到JS数据:"+data, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onRightButtonClick() {
        callJS();
    }
}
