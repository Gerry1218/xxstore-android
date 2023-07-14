package com.wanris.business.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.wanris.business.common.CenterImageSpan;

import java.util.ArrayList;

public class WidgetHelper {
    public static void setDrawableText(Context context, TextView tv, String str, ArrayList<Integer> drawables) {
        tv.setText("");
        if (drawables.isEmpty()) {
            tv.append(str);
            return;
        }
        for (Integer i : drawables) {
            Drawable drawable = ContextCompat.getDrawable(context, i);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            CenterImageSpan span = new CenterImageSpan(drawable);
            // 不能为空字符串
            SpannableString ss = new SpannableString(" ");
            ss.setSpan(span,0, ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            tv.append(ss);
            tv.append(" ");
        }
        tv.append(str);
    }
}
