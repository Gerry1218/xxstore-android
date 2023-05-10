package com.wanris.module.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.router.RouterPath;
import com.wanris.module.home.R;
import com.wanris.module.home.contract.HomeFragmentContract;
import com.wanris.module.home.presenter.HomeFragmentPresenter;

import io.reactivex.disposables.Disposable;

@Route(path = RouterPath.HomeHomeFragment)
public class HomeFragment extends BaseFragment<HomeFragmentContract.View, HomeFragmentContract.Presenter> implements HomeFragmentContract.View {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected HomeFragmentContract.Presenter initPresenter() {
        return new HomeFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

}
