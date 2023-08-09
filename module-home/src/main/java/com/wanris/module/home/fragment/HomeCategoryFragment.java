package com.wanris.module.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wanris.business.ICallback;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.router.RouteManager;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.home.R;
import com.wanris.module.home.adapter.HomeCategoryGoodsListAdapter;
import com.wanris.module.home.contract.HomeCategoryContract;
import com.wanris.module.home.presenter.HomeCategoryPresenter;
import com.wanris.business.provider.bean.XXGoodsListRequest;

import java.util.List;

public class HomeCategoryFragment extends BaseFragment<HomeCategoryContract.View, HomeCategoryContract.Presenter> implements HomeCategoryContract.View {
    private static final String TAG = HomeCategoryFragment.class.getSimpleName();
    private static final String CATEGORY_ID = "categoryId";
    private String categoryId;
    private RecyclerView rvGoodsList;
    private int pageNo = 1;
    private final int pageSize = 20;
    private HomeCategoryGoodsListAdapter goodsListAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

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
        goodsListAdapter.setEnableLoadMore(true);
        goodsListAdapter.setPreLoadNumber(5);
        rvGoodsList.setAdapter(goodsListAdapter);
        rvGoodsList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    protected void initListener() {
        super.initListener();
        goodsListAdapter.setOnLoadMoreListener(() -> {
            loadNextPage();
        }, rvGoodsList);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.d(TAG, "setOnRefreshListener: ");
            requestGoodsList();
        });

        goodsListAdapter.setOnItemClickListener((adapter, view, position) -> {
            List<XXGoodsListData.XXGoodsItemBean> data = goodsListAdapter.getData();
            RouteManager.startGoodsDetailActivity(data.get(position));
        });
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
        pageNo = 1;
        doRequestGoodsList();
    }

    public void loadNextPage() {
        pageNo++;
        doRequestGoodsList();
    }

    private void doRequestGoodsList() {
        XXGoodsListRequest request = new XXGoodsListRequest();
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        getPresenter().getGoodsList(request, new ICallback<XXGoodsListData>() {
            @Override
            public void success(XXGoodsListData data) {
                if (data == null || data.getItems() == null) {
                    Log.d(TAG, "success: item is null");
                    return;
                }
                if (request.getPageNo() == 1) {
                    goodsListAdapter.setNewData(data.getItems());
                }
                else {
                    goodsListAdapter.addData(data.getItems());
                }

                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                goodsListAdapter.loadMoreComplete();
                if (data.getItems().size() < pageSize) {
                    goodsListAdapter.loadMoreEnd();
                }
            }

            @Override
            public void failure() {
                Log.d(TAG, "failure: ");
            }
        });
    }
}
