package com.wanris.xxshop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.bean.ParamBean;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.common.Utils;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.X5WebView;

@Route(path = RouterPath.MainActivityPath)
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private final static String TAG = MainActivity.class.getSimpleName();
    private TextView btnRN;
    private TextView btnHome;
    private ImageView ivLogo;

    private X5WebView webview;

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
        webview = findViewById(R.id.x5Webview);

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
                ParamBean bean = new ParamBean();
                bean.setName("Gerry");
                bean.setNumber(1218);
                RouteManager.startHomeActivity(bean);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().loadData();

        // 根据渠道加载对应方法
        loadImage();

        // x5Webview测试
        webview.loadUrl("https://www.baidu.com");

        // 子模块多渠道打包支持
        Utils.logText();
    }

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    private void loadImage() {
        ImageLoader.load("https://puui.qpic.cn/qqvideo_ori/0/z3316b8w95y_1280_720/0", ivLogo);
    }
}