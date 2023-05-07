package com.wanris.module.home.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.module.home.R;
import com.wanris.module.home.contract.HomeContract;
import com.wanris.module.home.presenter.HomePresenter;

@Route(path = "/home/HomeActivity")
public class HomeActivity extends BaseActivity<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected boolean applyImmersionBar() {
        return true;
    }
}
