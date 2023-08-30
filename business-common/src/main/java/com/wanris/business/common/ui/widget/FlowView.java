package com.wanris.business.common.ui.widget;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.wanris.business.common.R;

public class FlowView extends LinearLayout {
    private Context mContext;
    private int width;
    private int height;
    private int icon;
    private String text;
    private int textColor;
    private float textSize;
    private ImageView mIvIcon;
    private TextView mTvTitle;

    public FlowView(Context context) {
        this(context, null);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        initView();
    }

    private void initAttrs(@Nullable AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CommonFlowLayout);
        width = (int)typedArray.getDimension(R.styleable.CommonFlowLayout_cfl_icon_width_height, mContext.getResources().getDimension(R.dimen.dp_24));
        height = width;
        icon = typedArray.getResourceId(R.styleable.CommonFlowLayout_cfl_icon, R.mipmap.icon_image_error);
        text = typedArray.getString(R.styleable.CommonFlowLayout_cfl_text);
        textSize = typedArray.getDimension(R.styleable.CommonFlowLayout_cfl_textSize, mContext.getResources().getDimension(R.dimen.dp_13));
        textColor = typedArray.getColor(R.styleable.CommonFlowLayout_cfl_textColor, ContextCompat.getColor(mContext, R.color.color_111));
        typedArray.recycle();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.common_flow_layout, this, true);
        mIvIcon = findViewById(R.id.iv_icon);
        mTvTitle = findViewById(R.id.tv_title);

        ViewGroup.LayoutParams params = mIvIcon.getLayoutParams();
        params.width = width;
        params.height = height;
        mIvIcon.setLayoutParams(params);
        mIvIcon.setImageResource(icon);

        mTvTitle.setText(text);
        mTvTitle.setTextColor(textColor);
        mTvTitle.setTextSize(COMPLEX_UNIT_PX, textSize);
    }

}
