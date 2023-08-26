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
import com.wanris.module.home.contract.VideoFragmentContract;
import com.wanris.module.home.presenter.VideoFragmentPresenter;

@Route(path = RouterPath.HomeVideoFragment)
public class VideoFragment extends BaseFragment<VideoFragmentContract.View, VideoFragmentContract.Presenter> implements VideoFragmentContract.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }

    @Override
    protected boolean applyImmersionBar() {
        return false;
    }
    @Override
    protected VideoFragmentContract.Presenter initPresenter() {
        return new VideoFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_fragment;
    }
}
