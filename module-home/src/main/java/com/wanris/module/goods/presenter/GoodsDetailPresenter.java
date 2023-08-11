package com.wanris.module.goods.presenter;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.ICallback;
import com.wanris.business.RxConsumer;
import com.wanris.business.RxThrowableConsumer;
import com.wanris.business.common.base.present.BasePresenter;
import com.wanris.business.common.rxjava.RxSchedulers;
import com.wanris.business.provider.bean.XXGoodsDetailRequest;
import com.wanris.business.response.XXGoodsDetailData;
import com.wanris.module.goods.contract.GoodsDetailContract;
import com.wanris.business.provider.IGoodsProvider;

import io.reactivex.disposables.Disposable;

public class GoodsDetailPresenter extends BasePresenter<GoodsDetailContract.View> implements GoodsDetailContract.Presenter {
    private static final String TAG = GoodsDetailPresenter.class.getSimpleName();
    @Autowired(name = "/http/goods")
    public IGoodsProvider goodsProvider;

    public GoodsDetailPresenter() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getGoodsDetail(XXGoodsDetailRequest request, ICallback callback) {
        Disposable disposable = goodsProvider.getGoodsDetail(request)
                .compose(RxSchedulers.inIoMainLoading(getContext()))
                .subscribe(new RxConsumer<XXGoodsDetailData>() {
                    @Override
                    public void _accept(XXGoodsDetailData data) {
                        Log.d(TAG, "_accept: ");
                        callback.success(data);
                    }

                    @Override
                    public void _handleMsg(String message, String subCode, String subMessage) {
                        Log.d(TAG, "_handleMsg: ");
                        callback.failure();
                    }
                }, new RxThrowableConsumer());
        addTask(disposable);
    }
}
