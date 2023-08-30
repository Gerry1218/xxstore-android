package com.wanris.module.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.utils.FileHelper;
import com.wanris.module.home.R;
import com.wanris.module.home.adapter.TikTokListAdapter;
import com.wanris.module.home.adapter.TiktokBean;
import com.wanris.module.home.contract.ChatFragmentContract;
import com.wanris.module.home.contract.VideoFragmentContract;
import com.wanris.module.home.presenter.ChatFragmentPresenter;
import com.wanris.module.home.presenter.VideoFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.HomeVideoFragment)
public class VideoFragment extends BaseFragment<VideoFragmentContract.View, VideoFragmentContract.Presenter> implements VideoFragmentContract.View {

    private RecyclerView mRvTiktok;
    private TikTokListAdapter mAdapter;
    private List<TiktokBean> data = new ArrayList<>();
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

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        mRvTiktok = view.findViewById(R.id.rv_tiktok);

        mRvTiktok.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new TikTokListAdapter(data);
        mRvTiktok.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        new Thread(() -> {
            String jsonStr = FileHelper.readAssetsFile("tiktok_data");
            List<TiktokBean> list = JSONObject.parseArray(jsonStr, TiktokBean.class);
            data.addAll(list);
            mRvTiktok.post(() -> mAdapter.notifyDataSetChanged());
        }).start();
    }
}
