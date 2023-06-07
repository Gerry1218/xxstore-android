package com.wanris.module.home.presenter;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.ICallback;
import com.wanris.business.RxConsumer;
import com.wanris.business.RxThrowableConsumer;
import com.wanris.business.common.base.present.BasePresenter;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.home.contract.HomeCategoryContract;
import com.wanris.module.home.provider.IGoodsProvider;
import com.wanris.module.home.provider.bean.XXGoodsListRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeCategoryPresenter extends BasePresenter<HomeCategoryContract.View> implements HomeCategoryContract.Presenter {
    @Autowired(name = "/http/goods")
    public IGoodsProvider goodsProvider;

    public HomeCategoryPresenter() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public void getGoodsList(XXGoodsListRequest request, ICallback<XXGoodsListData> callback) {
        Disposable disposable = goodsProvider.getGoodsList(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer<XXGoodsListData>() {

                    @Override
                    public void _accept(XXGoodsListData data) {
                        Log.i("", "");
                        callback.success(data);
                    }

                    @Override
                    public void _handleMsg(String message, String sub_code, String sub_message) {
                        Log.i("", "");
                        callback.failure();
                    }
                }, new RxThrowableConsumer());
        addTask(disposable);
    }
}
