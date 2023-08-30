package com.wanris.business.common.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wanris.business.common.R;
import com.wanris.business.common.utils.ScreenHelper;

public class HorizontalScrollBarDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    public HorizontalScrollBarDecoration(Context context) {
        mContext = context;
    }
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int barHeight = ScreenHelper.dp2px(2f);
        int scrollWidth = ScreenHelper.dp2px(15f);
        int indicatorWidth = ScreenHelper.dp2px(11f);
        int paddingBottom = ScreenHelper.dp2px(0f);
        float barX = (parent.getWidth() / 2 - scrollWidth / 2);
        float barY = (parent.getHeight() - paddingBottom - barHeight);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mContext.getResources().getColor(R.color.color_d8));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(barHeight);
        c.drawLine(barX, barY, barX + scrollWidth, barY, paint);

        int extent = parent.computeHorizontalScrollExtent();
        int range = parent.computeHorizontalScrollRange();
        int offset = parent.computeHorizontalScrollOffset();
        float maxEndX = (range - extent);
        //可滑动
        if (maxEndX > 0) {
            float proportion = offset / maxEndX;

            float scrollableDistance = scrollWidth - indicatorWidth;

            float offsetX = scrollableDistance * proportion;
            paint.setColor(mContext.getResources().getColor(R.color.color_FF3730));
            c.drawLine(barX + offsetX, barY, barX + indicatorWidth + offsetX, barY, paint);
        } else {
            paint.setColor(mContext.getResources().getColor(R.color.color_FF3730));
            c.drawLine(barX, barY, barX + scrollWidth, barY, paint);
        }

    }
}
