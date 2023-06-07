package com.wanris.module.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wanris.business.common.utils.GlideHelper;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.home.R;

public class HomeCategoryGoodsListAdapter extends BaseQuickAdapter<XXGoodsListData.XXGoodsItemBean, BaseViewHolder> {

    public HomeCategoryGoodsListAdapter() {
        super(R.layout.item_goods);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, XXGoodsListData.XXGoodsItemBean bean) {
        ImageView goodsImage = baseViewHolder.itemView.findViewById(R.id.iv_goods_image);
        TextView goodsName = baseViewHolder.itemView.findViewById(R.id.tv_goods_name);
        TextView goodsPrice = baseViewHolder.itemView.findViewById(R.id.tv_price);

        GlideHelper.loadTopRoundCorner(bean.getImg(), goodsImage, R.mipmap.icon_image_error, R.mipmap.icon_image_error,5);
        goodsName.setText(bean.getTitle());
        goodsPrice.setText("" + bean.getPrice());
    }
}
