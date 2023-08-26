package com.wanris.module.home.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanris.business.common.bean.ShopGoodsItemBean;
import com.wanris.business.common.bean.ShopSectionBean;
import com.wanris.business.common.utils.GlideHelper;
import com.wanris.module.home.R;
import com.wanris.module.widget.NumberSelector;

import java.util.List;

public class CartAdapter extends BaseExpandableListAdapter {
    private static final String TAG = CartAdapter.class.getSimpleName();
    private List<ShopSectionBean> list;
    private Context mContext;
    private boolean isEdit;
    private OnDataChangeListener mListener;

    public CartAdapter(Context context, List<ShopSectionBean> list) {
        this.mContext = context;
        this.list = list;
        Log.d(TAG, "CartAdapter: ");
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }

    public void setListener(OnDataChangeListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getGoodsList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getGoodsList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holderGroup;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.cart_goods_section_header, null);
            holderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(holderGroup);
        }
        else {
            holderGroup = (ViewHolderGroup) convertView.getTag();
        }

        ShopSectionBean bean = list.get(groupPosition);
        holderGroup.tvShopName.setText(bean.getShopName());
        GlideHelper.load(bean.getShopLogo(),
                holderGroup.ivShopIcon,
                R.mipmap.icon_image_error,
                R.mipmap.icon_image_error);
        holderGroup.ivSelect.setImageResource(bean.isPitchOn() ? R.mipmap.check_selected : R.mipmap.check_unselect);

        holderGroup.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: group select");
                bean.setPitchOn(!bean.isPitchOn());
                for (int i = 0; i < bean.getGoodsList().size(); i++) {
                    ShopGoodsItemBean itemBean = bean.getGoodsList().get(i);
                    itemBean.setPitchOn(bean.isPitchOn());
                }
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.dataChanged(list);
                }
            }
        });
        holderGroup.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: group header");
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChildren holderChildren;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.cart_goods_item_layout, null);
            holderChildren = new ViewHolderChildren(convertView);
            convertView.setTag(holderChildren);
        }
        else {
            holderChildren = (ViewHolderChildren)convertView.getTag();
        }

        ShopGoodsItemBean itemBean = list.get(groupPosition).getGoodsList().get(childPosition);
        holderChildren.tvPrice.setText(itemBean.getPrice());
        holderChildren.tvGoodsName.setText(itemBean.getGoodsName());
        holderChildren.ivSelect.setImageResource(itemBean.isPitchOn() ? R.mipmap.check_selected : R.mipmap.check_unselect);
        holderChildren.numberSelector.setCount(itemBean.getCount());
        holderChildren.sectionFooter.setVisibility(isLastChild ? View.VISIBLE : View.GONE);
        GlideHelper.loadRoundCorners(itemBean.getGoodsUrl(),
                holderChildren.ivGoodsIcon,
                R.mipmap.icon_image_error,
                R.mipmap.icon_image_error,
                20,
                0);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: children");
            }
        });
        holderChildren.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemBean.setPitchOn(!itemBean.isPitchOn());
                ShopSectionBean bean = list.get(groupPosition);
                for (int i = 0; i < bean.getGoodsList().size(); i++) {
                    ShopGoodsItemBean ib = bean.getGoodsList().get(i);
                    if (!ib.isPitchOn()) {
                        bean.setPitchOn(false);
                        break;
                    }
                    else {
                        bean.setPitchOn(true);
                    }
                }
                notifyDataSetChanged();
                if (mListener != null) {
                    mListener.dataChanged(list);
                }
                Log.d(TAG, "onClick: children select");
            }
        });
        holderChildren.numberSelector.setOnChangeListener(new NumberSelector.OnChangeListener() {
            @Override
            public void increment(Integer count) {
                Log.d(TAG, "increment: ");
            }

            @Override
            public void decrement(Integer count) {
                Log.d(TAG, "decrement: ");
            }

            @Override
            public void countChange(Integer count) {
                Log.d(TAG, "countChange: ");
                itemBean.setCount(count);
            }

            @Override
            public void exceedMaxValue() {
                Log.d(TAG, "exceedMaxValue: ");
            }
        });

        holderChildren.llContent.setBackgroundResource(isLastChild ?
                R.drawable.bg_white_r10_bottom : R.drawable.bg_white_none);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolderGroup {
        ImageView ivSelect;
        TextView tvShopName;
        ImageView ivShopIcon;
        LinearLayout ll;
        ViewHolderGroup(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            tvShopName = view.findViewById(R.id.tv_shop_name);
            ivShopIcon = view.findViewById(R.id.iv_shop_icon);
            ll = view.findViewById(R.id.ll_section_header);
        }
    }

    static class ViewHolderChildren {
        ImageView ivSelect;
        ImageView ivGoodsIcon;
        TextView tvGoodsName;
        TextView tvSpec;
        TextView tvPrice;
        LinearLayout llContent;
        NumberSelector numberSelector;
        View sectionFooter;

        ViewHolderChildren(View view) {
            ivSelect = view.findViewById(R.id.iv_select);
            ivGoodsIcon = view.findViewById(R.id.iv_goods_icon);
            tvGoodsName = view.findViewById(R.id.tv_goods_name);
            numberSelector = view.findViewById(R.id.nsl_number_select);
            tvSpec = view.findViewById(R.id.tv_goods_spec);
            tvPrice = view.findViewById(R.id.tv_price);
            llContent = view.findViewById(R.id.ll_content);
            sectionFooter = view.findViewById(R.id.v_section_footer);
        }
    }

    public interface OnDataChangeListener {
        void dataChanged(List list);
    }
}
