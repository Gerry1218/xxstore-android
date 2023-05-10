package com.wanris.xxshop.rn.test;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.wanris.business.common.router.RouterPath;
import com.wanris.xxshop.rn.base.RNBaseActivity;

@Route(path = RouterPath.RNTestActivityPath)
public class RNTestActivity extends RNBaseActivity implements DefaultHardwareBackBtnHandler {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public void invokeDefaultOnBackPressed() {

    }
}
