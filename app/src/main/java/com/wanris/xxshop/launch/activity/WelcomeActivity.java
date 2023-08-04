package com.wanris.xxshop.launch.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.constant.Globals;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.common.router.RouterPath;
import com.wanris.xxshop.R;
import com.wanris.xxshop.launch.contract.WelcomeContract;
import com.wanris.xxshop.launch.presenter.WelcomePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.WelcomeActivityPath)
public class WelcomeActivity extends BaseActivity<WelcomeContract.View, WelcomeContract.Presenter> implements WelcomeContract.View {

    private Disposable timerDisposable;
    private TextView skipBtn;

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected WelcomeContract.Presenter initPresenter() {
        return new WelcomePresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        skipBtn = findViewById(R.id.tv_skip_button);
        skipBtn.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        final int take = 3;
        timerDisposable = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .take(take)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    long skipTime = take - o;
                    skipBtn.setText((Html.fromHtml(getResources().getString(R.string.skip_button_text, skipTime))));
                    if (skipTime == 1) {
                        go2Home();
                    }
                });
        getPresenter().addTask(timerDisposable);
    }

    @Override
    protected void initListener() {
        super.initListener();

        RxView.clicks(skipBtn).throttleFirst(Globals.BUTTON_THROTTLE_TIME, TimeUnit.SECONDS).subscribe(o -> {
            go2Home();
        });
    }

    private void go2Home() {
        RouteManager.startHomeActivity();
    }

}
