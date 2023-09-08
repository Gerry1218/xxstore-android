package com.wanris.xxshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.wanris.business.common.Utils;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.bean.ParamBean;
import com.wanris.business.common.bean.WebViewParam;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.ActionSheetDialog;
import com.wanris.business.common.utils.FileHelper;
import com.wanris.module.widget.GoodsSpecDialog;
import com.wanris.module.widget.bean.GoodsSpec;
import com.wanris.module.widget.bean.Sku;
import com.wanris.module.widget.bean.SpecGroup;
import com.wanris.module.widget.bean.SpecSection;
import com.wanris.xxshop.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.MainActivityPath)
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private final static String TAG = MainActivity.class.getSimpleName();
    private TextView btnRN;
    private TextView btnHome;
    private TextView btnWebView;
    private TextView btnX5WebView;
    private ImageView ivLogo;
    private TextView btnActionSheet;
    private TextView btnGoodsSpec;
    private TextView btnKotlinActivity;

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
        btnActionSheet = findViewById(R.id.tv_action_sheet_dialog);
        btnGoodsSpec = findViewById(R.id.tv_spec_dialog);
        btnKotlinActivity = findViewById(R.id.tv_kotlin);
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
//                param.setUrl("https://www.baidu.com");
                param.setUrl("http://192.168.42.210:8080");
                RouteManager.startJSWebViewActivity(param);
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
        btnActionSheet.setOnClickListener(v -> {
            showActionSheetDialog();
        });
        btnGoodsSpec.setOnClickListener(v -> {
            showSpecDialog();
        });
        btnKotlinActivity.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(this, TestActivity.class);
            startActivity(intent);
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

    private void showActionSheetDialog() {
        final ActionSheetDialog mDialog = new ActionSheetDialog(MainActivity.this)
                .builder();
        mDialog.setCancelable(true)
                .setTitle("选择")
                .addSheetItem("保存到相册", ActionSheetDialog.SheetItemColor.GRAY, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int index) {
                        Log.d(TAG, "onClick: 保存到相册" + index);
                    }
                })
                .addSheetItem("保存到手机", ActionSheetDialog.SheetItemColor.GRAY, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int index) {
                        Log.d(TAG, "onClick: 保存到手机" + index);
                    }
                })
                .show();
    }

    private void showSpecDialog() {
        String jsonStr0 = FileHelper.readAssetsFile("spec.json");
        GoodsSpec gs = JSONObject.parseObject(jsonStr0, GoodsSpec.class);
         List<Sku> skuList = gs.getSku_list();
         List<SpecGroup> specGroups = gs.getSpec_group();

         List<SpecSection> specList = new ArrayList<>();
         for (SpecGroup sg : specGroups) {
             SpecSection ss = new SpecSection(true, sg.getName());
             specList.add(ss);

             SpecSection ss1 = new SpecSection( sg.getItems());
             specList.add(ss1);
         }
        GoodsSpecDialog dialog = new GoodsSpecDialog(this);
        dialog.setBeans(specList, skuList);
        dialog.show();
    }

    @Override
    protected boolean applyFullScreen() {
        return true;
    }

    private void loadImage() {
        ImageLoader.load("https://puui.qpic.cn/qqvideo_ori/0/z3316b8w95y_1280_720/0", ivLogo);
    }
}