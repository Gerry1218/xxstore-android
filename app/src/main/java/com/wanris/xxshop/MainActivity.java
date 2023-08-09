package com.wanris.xxshop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.bean.ParamBean;
import com.wanris.business.common.bean.WebViewParam;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.common.Utils;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.X5WebView;

import org.w3c.dom.Text;

@Route(path = RouterPath.MainActivityPath)
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private final static String TAG = MainActivity.class.getSimpleName();
    private TextView btnRN;
    private TextView btnHome;
    private TextView btnWebView;
    private TextView btnX5WebView;
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
        btnWebView = findViewById(R.id.tv_webview);
        btnX5WebView = findViewById(R.id.tv_x5webview);
        ivLogo = findViewById(R.id.iv_logo);
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
        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewParam param = new WebViewParam();
                param.setUrl("https://www.baidu.com");
//                param.setUrl("http://192.168.42.210:8080");
                RouteManager.startWebViewActivity(param);
            }
        });
        btnX5WebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewParam param = new WebViewParam();
                param.setUrl("https://www.baidu.com");
                RouteManager.startX5WebViewActivity(param);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getPresenter().loadData();

        // 根据渠道加载对应方法
        loadImage();

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