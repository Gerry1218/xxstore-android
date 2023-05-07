package com.wanris.xxshop;

import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;

public interface MainContract {
    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {
        void loadData();
    }
}
