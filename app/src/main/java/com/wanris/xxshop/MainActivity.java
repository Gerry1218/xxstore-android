package com.wanris.xxshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.wanris.xxshop.adapter.MyAdapter;
import com.wanris.xxshop.test.TestActivity;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.MainActivityPath)
public class MainActivity extends BaseActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    private final static String TAG = MainActivity.class.getSimpleName();

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
        super.initViews(savedInstanceState);;

        ivLogo = findViewById(R.id.iv_logo);
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("进入RN页面");
        dataList.add("打开actionSheetDialog");
        dataList.add("进入WebView页面JSBridge");
        dataList.add("进入Home页面");
        dataList.add("进入x5WebView页面");
        dataList.add("商品规格");
        dataList.add("kotlin页面");
        ListView listView = findViewById(R.id.list_view);
        MyAdapter adapter = new MyAdapter(dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            handleEvent(position);
        });
    }

    private void handleEvent(int position) {
        switch (position) {
            case 0 : {
                Log.d(TAG, "onClick: ");
                RouteManager.startRNTestActivity();
                break;
            }
            case 1: {
                showActionSheetDialog();
                break;
            }
            case 2: {
                WebViewParam param = new WebViewParam();
                param.setUrl("https://api.wanris.com");
                RouteManager.startJSWebViewActivity(param);
                break;
            }
            case 3: {
                ParamBean bean = new ParamBean();
                bean.setName("Gerry");
                bean.setNumber(1218);
                RouteManager.startHomeActivity(bean);
                break;
            }
            case 4: {
                WebViewParam param = new WebViewParam();
                param.setUrl("https://www.baidu.com");
                RouteManager.startX5WebViewActivity(param);
                break;
            }
            case 5: {
                showSpecDialog();
                break;
            }
            case 6 :{
                Intent intent = new Intent();
                intent.setClass(this, TestActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
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