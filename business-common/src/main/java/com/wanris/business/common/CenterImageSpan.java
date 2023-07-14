package com.wanris.business.common;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

public class CenterImageSpan extends ImageSpan {
    public CenterImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        super.draw(canvas, text, start, end, x, top, y, bottom, paint);
        Drawable d = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent)/ 2 - d.getBounds().bottom/2;
        canvas.save();
        canvas.translate(x,transY);
        d.draw(canvas);
        canvas.restore();
    }
}
