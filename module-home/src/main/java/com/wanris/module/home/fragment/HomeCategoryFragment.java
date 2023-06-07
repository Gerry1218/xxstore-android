package com.wanris.module.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.wanris.business.ICallback;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.home.R;
import com.wanris.module.home.adapter.HomeCategoryGoodsListAdapter;
import com.wanris.module.home.contract.HomeCategoryContract;
import com.wanris.module.home.presenter.HomeCategoryPresenter;
import com.wanris.module.home.provider.IGoodsProvider;
import com.wanris.module.home.provider.bean.XXGoodsListRequest;

public class HomeCategoryFragment extends BaseFragment<HomeCategoryContract.View, HomeCategoryContract.Presenter> implements HomeCategoryContract.View {
    private static final String TAG = HomeCategoryFragment.class.getSimpleName();
    private static final String CATEGORY_ID = "categoryId";
    private String categoryId;
    private RecyclerView rvGoodsList;
    private HomeCategoryGoodsListAdapter goodsListAdapter;

    public static HomeCategoryFragment getInstance(String id) {
        HomeCategoryFragment fragment = new HomeCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        categoryId = getArguments().getString(CATEGORY_ID);
        rvGoodsList = view.findViewById(R.id.rv_goods_list);
        goodsListAdapter = new HomeCategoryGoodsListAdapter();
        rvGoodsList.setAdapter(goodsListAdapter);
        rvGoodsList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void initData() {
        super.initData();
        requestGoodsList();
    }

    @Override
    protected HomeCategoryContract.Presenter initPresenter() {
        return new HomeCategoryPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_category_fragment;
    }

    @Override
    protected boolean applyImmersionBar() {
        return false;
    }

    private void requestGoodsList() {
        if (this.categoryId != "1") {
            return;
        }
        XXGoodsListRequest request = new XXGoodsListRequest();
        request.setPageNo(1);
        request.setPageSize(20);
        getPresenter().getGoodsList(request, new ICallback<XXGoodsListData>() {
            @Override
            public void success(XXGoodsListData data) {
                goodsListAdapter.setNewData(data.getItems());
            }

            @Override
            public void failure() {
                Log.d(TAG, "failure: ");
            }
        });
    }
}
