package com.wanris.module.widget.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanris.business.common.ui.widget.flowLayout.FlowLayout;
import com.wanris.business.common.ui.widget.flowLayout.TagAdapter;
import com.wanris.business.common.ui.widget.flowLayout.TagFlowLayout;
import com.wanris.business.common.utils.GlideHelper;
import com.wanris.module.home.R;
import com.wanris.module.widget.bean.GoodsSpecSectionBean;
import com.wanris.module.widget.bean.SpecItem;

import java.util.List;

public class SectionAdapter extends BaseSectionQuickAdapter<GoodsSpecSectionBean, BaseViewHolder> {

    public SectionAdapter(List<GoodsSpecSectionBean> datas, List<List<Object>> listProperties) {
        super(R.layout.spec_item, R.layout.spec_item_header, datas);
//        this.listProperties = listProperties;
//        checkedBean = getCheckBeanInfo(getCheckedIds());
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GoodsSpecSectionBean item) {
        helper.setText(R.id.tv_name, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSpecSectionBean item) {
        final TagFlowLayout tagFlowLayout = helper.getView(R.id.tagView);
        tagFlowLayout.setAdapter(new TagAdapter<SpecItem>(item.t) {
            @Override
            public View getView(FlowLayout parent, int position, SpecItem s) {
                View view = LayoutInflater.from(mContext)
                        .inflate(R.layout.spec_item_tag, tagFlowLayout, false);

                ImageView icon = view.findViewById(R.id.iv_icon);
                LinearLayout llAll = view.findViewById(R.id.ll_tag);
                TextView text = view.findViewById(R.id.tv_name);
                TextView goodsNone = view.findViewById(R.id.tv_sale_out);
                text.setText(s.getName());
                if (TextUtils.isEmpty(s.getUrl())) {
                    icon.setVisibility(View.GONE);
                } else {
                    icon.setVisibility(View.VISIBLE);
                    GlideHelper.loadCenterCrop(s.getUrl(), icon);
                }

                if (!s.isEnable() || !s.isClickable()) {
                    goodsNone.setVisibility(View.VISIBLE);
                    llAll.setBackgroundResource(R.drawable.spec_item_tag_unselected);
                    text.setTextColor(ContextCompat.getColor(mContext, R.color.color_80333333));
                } else {
                    int resId = s.isSelected() ? R.drawable.spec_item_tag_selected : R.drawable.spec_item_tag_unselected;
                    int colorId = s.isSelected() ? R.color.color_80333333 :  R.color.color_333333;
                    llAll.setBackgroundResource(resId);
                    text.setTextColor(ContextCompat.getColor(mContext, colorId));
                }

                return view;
            }
        });
    }
}
