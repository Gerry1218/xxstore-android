package com.wanris.xxshop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.RouteManager;
import com.wanris.business.common.base.activity.BaseActivity;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private final static String TAG = MainActivity.class.getSimpleName();
    private TextView btnRN;
    private TextView btnHome;
    private ImageView ivLogo;

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        btnRN = findViewById(R.id.tv_rn_btn);
        btnHome = findViewById(R.id.tv_home_btn);
        ivLogo = findViewById(R.id.iv_logo);

        loadImage();
    }

    @Override
    protected void initListener() {
        super.initListener();
        btnRN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                RouteManager.startRNTestActivity();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RouteManager.startHomeActivity();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().loadData();
    }

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    private void loadImage() {
        ImageLoader.load("https://puui.qpic.cn/qqvideo_ori/0/z3316b8w95y_1280_720/0", ivLogo);
    }
}