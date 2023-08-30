package com.wanris.module.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanris.business.common.bean.CommonItem;
import com.wanris.business.common.utils.GlideHelper;
import com.wanris.business.common.utils.ScreenHelper;
import com.wanris.module.home.R;

public class CommonAdapter extends BaseQuickAdapter<CommonItem, BaseViewHolder> {

    public CommonAdapter() {
        super(R.layout.common_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, CommonItem item) {
        viewHolder.itemView.getLayoutParams().width = (ScreenHelper.getScreenWidth(mContext) - ScreenHelper.dp2px(20)) / 4;
        if (item==null) {
            viewHolder.itemView.setVisibility(View.GONE);
            return;
        }
        viewHolder.itemView.setVisibility(View.VISIBLE);
        ImageView icon = viewHolder.getView(R.id.iv_icon);
        viewHolder.setText(R.id.tv_title, item.getTitle());
        TextView tvBadgeNum = viewHolder.getView(R.id.tv_badge_num);
        if (item.getNumber() > 0) {
            tvBadgeNum.setText(String.valueOf(item.getNumber()));
        }
        tvBadgeNum.setVisibility(item.getNumber() > 0 ? View.VISIBLE : View.GONE);

        GlideHelper.load(item.getImageUrl(), icon, 0, 0);
    }
}
