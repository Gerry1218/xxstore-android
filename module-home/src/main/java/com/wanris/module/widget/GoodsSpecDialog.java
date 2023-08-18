package com.wanris.module.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wanris.business.common.utils.ScreenHelper;
import com.wanris.module.goods.contract.GoodsDetailContract;
import com.wanris.module.home.R;
import com.wanris.module.widget.adapter.SectionAdapter;
import com.wanris.module.widget.bean.GoodsSpecSectionBean;

import java.util.List;

public class GoodsSpecDialog extends Dialog {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private SectionAdapter mSectionAdapter;
    private List<GoodsSpecSectionBean> beans;

    public void setBeans(List<GoodsSpecSectionBean> beans) {
        this.beans = beans;
    }

    public GoodsSpecDialog(@NonNull Context context) {
        super(context, R.style.BottomDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_spec_dialog_layout);
        getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        initView();
        initListener();
        updateUI();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        int h = (int)(ScreenHelper.getScreenHeight(mContext) * 0.7);
        View view = findViewById(R.id.goods_spec_dialog);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)view.getLayoutParams();
        layoutParams.height = h;
    }

    private void initListener() {

    }

    private void updateUI() {
        mSectionAdapter = new SectionAdapter(beans, null);
        mRecyclerView.setAdapter(mSectionAdapter);
    }

    @Override
    public void show() {
        super.show();

        // 设置宽度全屏，要设置在show的后面
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }
}
