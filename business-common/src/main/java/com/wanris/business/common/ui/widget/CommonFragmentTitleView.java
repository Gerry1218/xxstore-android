package com.wanris.business.common.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wanris.business.common.R;

public class CommonFragmentTitleView extends LinearLayout {
    private Context mContext;
    private boolean showBackImg;
    private String title;
    private String content;
    private int rightImg;
    private int rightImg2;
    private int rightImg3;

    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvContent;
    protected ImageView mIvRightImage1;
    protected ImageView mIvRightImage2;
    protected ImageView mIvRightImage3;
    public CommonFragmentTitleView(Context context) {
        this(context, null);
    }

    public CommonFragmentTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonFragmentTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(attrs);
        initView();
    }

//    public CommonFragmentTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.CommonFragmentTitleView);
        showBackImg = typedArray.getBoolean(R.styleable.CommonFragmentTitleView_show_back, false);
        title = typedArray.getString(R.styleable.CommonFragmentTitleView_title_text);
        content = typedArray.getString(R.styleable.CommonFragmentTitleView_right_text);
        rightImg = typedArray.getResourceId(R.styleable.CommonFragmentTitleView_right_img, 0);
        rightImg2 = typedArray.getResourceId(R.styleable.CommonFragmentTitleView_right_img2, 0);
        rightImg3 = typedArray.getResourceId(R.styleable.CommonFragmentTitleView_right_img3, 0);
//        middleImg = typedArray.getResourceId(R.styleable.CommonFragmentTitleView_middle_img, 0);
//        isShowMiddleImg = typedArray.getBoolean(R.styleable.CommonFragmentTitleView_show_middle_img, false);
        typedArray.recycle();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.common_fragment_title_view, this, true);
        mIvBack = findViewById(R.id.iv_back);
        mTvTitle = findViewById(R.id.tv_title);
        mIvRightImage1 = findViewById(R.id.iv_right_img1);
        mIvRightImage2 = findViewById(R.id.iv_right_img2);
        mIvRightImage3 = findViewById(R.id.iv_right_img3);
        mTvContent = findViewById(R.id.tv_content_right);

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }

        if (!TextUtils.isEmpty(content)) {
            mTvContent.setText(content);
        }

        if (rightImg != 0) {
            mIvRightImage1.setImageResource(rightImg);
        }
        if (rightImg2 != 0) {
            mIvRightImage1.setImageResource(rightImg2);
        }
        if (rightImg3 != 0) {
            mIvRightImage1.setImageResource(rightImg3);
        }

        mIvBack.setVisibility(showBackImg ? VISIBLE : GONE);
    }
}
