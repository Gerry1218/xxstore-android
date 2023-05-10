package com.wanris.module.home.fragment;

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
import com.wanris.module.home.contract.MineFragmentContract;
import com.wanris.module.home.presenter.MineFragmentPresenter;

@Route(path = RouterPath.HomeMineFragment)
public class MineFragment extends BaseFragment<MineFragmentContract.View, MineFragmentContract.Presenter> implements MineFragmentContract.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected MineFragmentContract.Presenter initPresenter() {
        return new MineFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }
}
