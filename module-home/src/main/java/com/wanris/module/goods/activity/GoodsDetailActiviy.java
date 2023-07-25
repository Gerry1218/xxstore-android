package com.wanris.module.goods.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.activity.BaseActivity;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.utils.PriceHelper;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.goods.contract.GoodsDetailContract;
import com.wanris.module.goods.presenter.GoodsDetailPresenter;
import com.wanris.module.home.R;
import com.wanris.module.util.BannerImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

@Route(path = RouterPath.GoodsDetailActivity)
public class GoodsDetailActiviy extends BaseActivity<GoodsDetailContract.View, GoodsDetailContract.Presenter> implements GoodsDetailContract.View, OnBannerListener {

    @Autowired
    XXGoodsListData.XXGoodsItemBean params;
    Banner banner;
    TextView tvGoodsPrice;
    TextView tvGoodsName;

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
    protected int getLayoutId() {
        return R.layout.goods_detail_activity;
    }

    @Override
    protected GoodsDetailContract.Presenter initPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        banner = findViewById(R.id.banner);
        tvGoodsPrice = findViewById(R.id.tv_price);
        tvGoodsName = findViewById(R.id.goods_name);

        Log.d(TAG, "initViews: ");
    }

    @Override
    protected void initData() {
        super.initData();

        ArrayList list = new ArrayList();
        list.add(params.getImg());
        banner.setImages(list)
                .setImageLoader(new BannerImageLoader())
                .setOnBannerListener(this)
                .start();

        tvGoodsName.setText(params.getTitle());
        tvGoodsPrice.setText(PriceHelper.priceString(params.getPrice()));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public void OnBannerClick(int position) {
        Log.d(TAG, "OnBannerClick: ");
    }
}
