package com.wanris.module.goods.contract;

import com.wanris.business.ICallback;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;
import com.wanris.business.provider.bean.XXGoodsDetailRequest;

public interface GoodsDetailContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodsDetail(XXGoodsDetailRequest request, ICallback callback);
    }
}
