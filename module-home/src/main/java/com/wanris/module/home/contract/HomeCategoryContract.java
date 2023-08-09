package com.wanris.module.home.contract;

import com.wanris.business.ICallback;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.business.provider.bean.XXGoodsListRequest;

public interface HomeCategoryContract {
    interface View extends IBaseView {

    }
    interface Presenter extends IBasePresenter<View> {
        void getGoodsList(XXGoodsListRequest request, ICallback<XXGoodsListData> callback);
    }
}
