package com.wanris.module.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSONObject;
import com.wanris.business.common.base.fragment.BaseFragment;
import com.wanris.business.common.bean.ShopGoodsItemBean;
import com.wanris.business.common.bean.ShopSectionBean;
import com.wanris.business.common.router.RouterPath;
import com.wanris.business.common.ui.widget.CommonFragmentTitleView;
import com.wanris.business.common.utils.FileHelper;
import com.wanris.module.home.R;
import com.wanris.module.home.adapter.CartAdapter;
import com.wanris.module.home.contract.CartFragmentContract;
import com.wanris.module.home.presenter.CartFragmentPresenter;

import java.text.DecimalFormat;
import java.util.List;

@Route(path = RouterPath.HomeCartFragment)
public class CartFragment extends BaseFragment<CartFragmentContract.View, CartFragmentContract.Presenter> implements CartFragmentContract.View {
    private static final String TAG = CartAdapter.class.getSimpleName();
    private ExpandableListView cartExpandableView;
    private ImageView ivSelectAll;
    private TextView tvTotalPrice;
    private TextView btnDelete;
    private CommonFragmentTitleView titleView;
    private List<ShopSectionBean> datas;
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
    protected CartFragmentContract.Presenter initPresenter() {
        return new CartFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cart_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        super.initViews(view, savedInstanceState);
        cartExpandableView = view.findViewById(R.id.elv_cart_list);
        titleView = view.findViewById(R.id.ctv_title_view);
        ivSelectAll = view.findViewById(R.id.iv_select_all);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);
        btnDelete = view.findViewById(R.id.btn_delete);

    }


    @Override
    protected void initData() {
        super.initData();
        String jsonStr = FileHelper.readAssetsFile("cart.json");
        List<ShopSectionBean> list = JSONObject.parseArray(jsonStr, ShopSectionBean.class);
        datas = list;

        CartAdapter cartAdapter = new CartAdapter(getContext(), datas);
        cartExpandableView.setAdapter(cartAdapter);
        for (int i = 0; i < list.size(); i++) {
            cartExpandableView.expandGroup(i);
        }
        cartExpandableView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        cartAdapter.setListener(new CartAdapter.OnDataChangeListener() {
            @Override
            public void dataChanged(List newList) {
                Log.d(TAG, "dataChanged: ");
                double price = .0;
                for (int i = 0; i < datas.size(); i++) {
                    ShopSectionBean sectionBean = (ShopSectionBean)datas.get(i);
                    for (int j = 0; j < sectionBean.getGoodsList().size(); j++) {
                        ShopGoodsItemBean bean = sectionBean.getGoodsList().get(j);
                        if (bean.isPitchOn()) {
                            price += Double.parseDouble(bean.getPrice());
                        }
                    }
                }
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String priceStr = decimalFormat.format(price);
                Log.d(TAG, "dataChanged: total price - " + priceStr);
                tvTotalPrice.setText(priceStr);
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
    }
}
