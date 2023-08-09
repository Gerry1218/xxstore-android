package com.wanris.business.common.base.activity;

import android.os.Bundle;

import com.wanris.business.common.R;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;
import com.wanris.business.common.ui.widget.CommonToolbar;

public abstract class BaseToolbarActivity<V extends IBaseView, P extends IBasePresenter<V>> extends BaseActivity<V, P>{
    protected CommonToolbar mCommonToolbar;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        mCommonToolbar = findViewById(R.id.common_bar);
    }

    public void setTitle(String title) {
        mCommonToolbar.setTitle(title);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCommonToolbar.setCallBack(new CommonToolbar.CallBack() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }

            @Override
            public void onRightClick() {
                onRightButtonClick();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onRightButtonClick() {
    }
}
