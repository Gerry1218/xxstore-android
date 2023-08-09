package com.wanris.module.goods.presenter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.present.BasePresenter;
import com.wanris.module.goods.contract.GoodsDetailContract;
import com.wanris.business.provider.IGoodsProvider;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter {

    @Autowired(name = "/http/goods")
    public IGoodsProvider goodsProvider;

    public GoodsDetailPresenter() {
        ARouter.getInstance().inject(this);
    }

}
