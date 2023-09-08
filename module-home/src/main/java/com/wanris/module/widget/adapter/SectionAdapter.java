package com.wanris.module.widget.adapter;

import android.text.TextUtils;
import android.util.Log;
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
import com.wanris.module.home.R;
import com.wanris.module.widget.bean.Sku;
import com.wanris.module.widget.bean.Spec;
import com.wanris.module.widget.bean.SpecSection;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends BaseSectionQuickAdapter<SpecSection, BaseViewHolder> {

    private List<Sku> skuList;
    public SectionAdapter(List<SpecSection> datas, List<Sku> skuList) {
        super(R.layout.spec_item, R.layout.spec_item_header, datas);
        this.skuList = skuList;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SpecSection item) {
        helper.setText(R.id.tv_name, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecSection item) {
        final TagFlowLayout tagFlowLayout = helper.getView(R.id.tagView);
        tagFlowLayout.setAdapter(new TagAdapter<Spec>(item.t) {
            @Override
            public View getView(FlowLayout parent, int position, Spec s) {
                View view = LayoutInflater.from(mContext)
                        .inflate(R.layout.spec_item_tag, tagFlowLayout, false);

                ImageView icon = view.findViewById(R.id.iv_icon);
                LinearLayout llAll = view.findViewById(R.id.ll_tag);
                TextView text = view.findViewById(R.id.tv_name);
                TextView goodsNone = view.findViewById(R.id.tv_sale_out);
                text.setText(s.getValue());
//                if (TextUtils.isEmpty(s.getUrl())) {
//                    icon.setVisibility(View.GONE);
//                } else {
//                    icon.setVisibility(View.VISIBLE);
//                    GlideHelper.loadCenterCrop(s.getUrl(), icon);
//                }

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
        tagFlowLayout.setOnTagClickListener((view, position, parent) -> {
            List<Spec> currentLine = item.t;
            Spec itemClicked = currentLine.get(position);
            List<SpecSection> beans = getData();

            if (!itemClicked.isClickable()) {
                return false;
            }

            // 设置点击项状态
            if (itemClicked.isSelected()) {
                itemClicked.setSelected(false);
            }
            else {
                for (int i = 0; i < currentLine.size(); i++) {
                    currentLine.get(i).setSelected(i == position);
                }
            }

            // 获取当前选中的项
            List<String> selectedNames = getAllSelectedItems(beans);

            for (SpecSection line : beans) {
                // 是header
                if (line.isHeader) {
                    continue;
                }
                // 当前点击行
                if (line.t == currentLine && itemClicked.isSelected()) {
                    continue;
                }

                Spec selectedOfLine = getSelectedSpecItemFrom(line.t);
                for (Spec si : line.t) {
                    if (si.isSelected()) {
                        continue;
                    }

                    List<String> tmpList = new ArrayList<>(selectedNames);
                    // 添加到selectedNames
                    tmpList.add(si.getValue());

                    String name = selectedOfLine != null ? selectedOfLine.getValue() : "";
                    boolean v = getCheckStatus(tmpList, name);
                    si.setClickable(v);
                }
            }


            notifyDataSetChanged();
            Log.d(TAG, "onTagClick: " + position + ",name:"+ itemClicked.getValue());
            return true;
        });
    }

    private List<String> getAllSelectedItems(List<SpecSection> beans) {
        List<String> selectedItemNames = new ArrayList<>();
        for (SpecSection data : beans) {
            if (data.isHeader) continue;

            Spec itemBean = getSelectedSpecItemFrom(data.t);
            if (itemBean != null) {
                selectedItemNames.add(itemBean.getValue());
            }
        }
        return selectedItemNames;
    }
    private boolean getCheckStatus(List<String> selectedNames, String toRemoveValue) {
        if (selectedNames == null || selectedNames.size() == 0) return false;

        if (!TextUtils.isEmpty(toRemoveValue)) {
            selectedNames.remove(toRemoveValue);
            if (selectedNames.size() == 0) return false;
        }

        for (int i = 0; i < skuList.size(); i++) {
            Sku sku = skuList.get(i);
            List<Spec> values = sku.getSpecificationValues();

            List<String> specNames = new ArrayList<>();
            for (Spec v : values) {
                String name = v.getValue();
                specNames.add(name);
            }
            boolean containAll = specNames.containsAll(selectedNames);
            if (!containAll) {
                continue;
            }
            if (containAll) {
                if (specNames.size() == selectedNames.size())
                    return sku.getStock() > 0;
                else
                    return true;
            }
        }
        return false;
    }

    private Spec getSelectedSpecItemFrom(List<Spec> line) {
        if (line == null || line.size() == 0) return null;
        for (Spec itemBean : line) {
            if (!itemBean.isEnable()) continue;

            if (itemBean.isSelected()) {
                return itemBean;
            }
        }
        return null;
    }
}
