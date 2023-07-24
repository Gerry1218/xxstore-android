package com.wanris.business.common.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout {
    private float startX;
    private float startY;
    private boolean mViewPagerDragging;
    private final int mTouchSlop;

    public VerticalSwipeRefreshLayout(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                startX = ev.getX();
                mViewPagerDragging = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(mViewPagerDragging) {
                    return false;
                }

                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                if(distanceX > mTouchSlop && distanceX > distanceY) {
                    mViewPagerDragging = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mViewPagerDragging = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
