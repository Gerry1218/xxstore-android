package com.wanris.module.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.bean.CommonItem;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.HorizontalScrollBarDecoration;
import com.wanris.business.common.utils.FileHelper;
import com.wanris.module.home.R;
import com.wanris.module.home.adapter.CommonAdapter;
import com.wanris.module.home.contract.MineFragmentContract;
import com.wanris.module.home.presenter.MineFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.HomeMineFragment)
public class MineFragment extends BaseFragment<MineFragmentContract.View, MineFragmentContract.Presenter> implements MineFragmentContract.View {

    private RecyclerView mRvTools;
    private RecyclerView mRvAbout;
    private final int SpanCount = 4;

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
    protected MineFragmentContract.Presenter initPresenter() {
        return new MineFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        mRvTools = view.findViewById(R.id.rv_tools);
        mRvTools.setLayoutManager(new GridLayoutManager(getContext(), SpanCount));
        CommonAdapter adapter = new CommonAdapter();
        mRvTools.setAdapter(adapter);
        String jsonStr = FileHelper.readAssetsFile("tools.json");
        List<CommonItem> list = JSONObject.parseArray(jsonStr, CommonItem.class);
        list = pageSort(list);
        adapter.setNewData(list);
        // 固定2行显示，超过2行加滚动条
        HorizontalScrollBarDecoration horizontalScrollBarDecoration = new HorizontalScrollBarDecoration(getActivity());
        mRvTools.setLayoutManager(new GridLayoutManager(getActivity(), 2, RecyclerView.HORIZONTAL, false));
        mRvTools.addItemDecoration(horizontalScrollBarDecoration);

        mRvAbout = view.findViewById(R.id.rv_about);
        mRvAbout.setLayoutManager(new GridLayoutManager(getContext(), SpanCount));
        CommonAdapter aboutAdapter = new CommonAdapter();
        mRvAbout.setAdapter(aboutAdapter);
        String aboutStr = FileHelper.readAssetsFile("about.json");
        List<CommonItem> aboutList = JSONObject.parseArray(aboutStr, CommonItem.class);
        aboutAdapter.setNewData(aboutList);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    /**
     * 分页排序
     * 规格如下：
     * 1 3 5 7 9               1 2 3 4 9
     * 2 4 6 8       ===>>>    5 6 7 8
     *
     * @param list
     * @return 排序后数据
     */
    private List<CommonItem> pageSort(List<CommonItem> list) {
        List<CommonItem> items = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i % SpanCount == 0 && i > 0) {
                i += SpanCount;
            }
            if (i >= list.size()) {
                break;
            }
            items.add(list.get(i));
            if (i + SpanCount < list.size()) {
                items.add(list.get(i + SpanCount));
            } else {
                items.add(null);
            }
        }
        return items;
    }
}
