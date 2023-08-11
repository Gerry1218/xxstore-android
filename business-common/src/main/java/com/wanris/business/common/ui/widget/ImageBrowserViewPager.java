package com.wanris.business.common.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 解决photoview嵌套在部分父控件时闪退的bug，github上提供的解决方案
 */
public class ImageBrowserViewPager extends ViewPager {
    public ImageBrowserViewPager(Context context) {
        this(context,null);
    }

    public ImageBrowserViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // uncomment if you really want to see these errors
            // e.printStackTrace();
            return false;
        }
    }
}
