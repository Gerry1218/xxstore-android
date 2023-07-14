package com.wanris.module.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanris.business.common.utils.GlideHelper;
import com.wanris.business.common.utils.PriceHelper;
import com.wanris.business.common.utils.WidgetHelper;
import com.wanris.business.response.XXGoodsListData;
import com.wanris.module.home.R;

import java.util.ArrayList;

public class HomeCategoryGoodsListAdapter extends BaseQuickAdapter<XXGoodsListData.XXGoodsItemBean, BaseViewHolder> {

    public HomeCategoryGoodsListAdapter() {
        super(R.layout.item_goods);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, XXGoodsListData.XXGoodsItemBean bean) {
        int position = baseViewHolder.getAdapterPosition();
        if (position == 0) {
            baseViewHolder.itemView.setBackgroundResource(R.drawable.shape_f0f0f0_top_left_cornor15);
        }
        else if (position == 1) {
            baseViewHolder.itemView.setBackgroundResource(R.drawable.shape_f0f0f0_top_right_cornor15);
        }
        else {
            baseViewHolder.itemView.setBackgroundResource(R.drawable.shape_f0f0f0_left_nor_cornor15);
        }

        ImageView goodsImage = baseViewHolder.itemView.findViewById(R.id.iv_goods_image);
        TextView goodsName = baseViewHolder.itemView.findViewById(R.id.tv_goods_name);
        TextView goodsPrice = baseViewHolder.itemView.findViewById(R.id.tv_price);

        GlideHelper.loadTopRoundCorner(bean.getImg(), goodsImage, R.mipmap.icon_image_error, R.mipmap.icon_image_error,5);
        ArrayList<Integer> list = new ArrayList();
        list.add(R.mipmap.tag_holiday);
        WidgetHelper.setDrawableText(mContext, goodsName, bean.getTitle(), list);
        CharSequence price = PriceHelper.priceString(bean.getPrice());
        goodsPrice.setText(price);
    }
}
