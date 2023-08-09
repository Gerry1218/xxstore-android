package com.wanris.business.common.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.wanris.business.common.R;
import com.wanris.business.common.constant.Globals;

import java.util.concurrent.TimeUnit;

public class CommonToolbar extends LinearLayout {
    private Context mContext;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private ImageView mBackImg;
    private TextView mTitle;
    private RelativeLayout mRightLayout;
    private TextView mRightBtn;
    private ImageView mRightImg;
    private CallBack mCallBack;

    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    public CommonToolbar(Context context) {
        this(context, null);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.common_title_view, this, true);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBackImg = (ImageView) findViewById(R.id.back_img);
        mTitle = (TextView) findViewById(R.id.toolbar_title_tv);
        mRightLayout = (RelativeLayout) findViewById(R.id.right_layout);
        mRightBtn = (TextView) findViewById(R.id.right_btn);
        mRightImg = (ImageView) findViewById(R.id.right_img);

        RxView.clicks(mBackImg).throttleFirst(Globals.BUTTON_THROTTLE_TIME, TimeUnit.SECONDS).subscribe(o -> {
            if (mCallBack != null) {
                mCallBack.onLeftClick();
            }
        });
        RxView.clicks(mRightLayout).throttleFirst(Globals.BUTTON_THROTTLE_TIME, TimeUnit.SECONDS).subscribe(o -> {
            if (mCallBack != null) {
                mCallBack.onRightClick();
            }
        });
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void showRightButton(boolean isShow) {
        mRightBtn.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    public void showRightIcon(boolean isShow) {
        mRightImg.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
    public void setRightButtonText(String text) {
        mRightLayout.setVisibility(View.VISIBLE);
        showRightIcon(false);
        showRightButton(true);
        mRightBtn.setText(text);
    }
    public void setRightBtnIcon(int resId) {
        mRightLayout.setVisibility(View.VISIBLE);
        showRightButton(false);
        showRightIcon(true);
        mRightImg.setImageResource(resId);
    }

    public void setAppBarLayoutVisibility(boolean visibility) {
        this.mAppBarLayout.setVisibility(visibility ? VISIBLE : GONE);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public interface CallBack {
        void onLeftClick();
        void onRightClick();
    }
}
