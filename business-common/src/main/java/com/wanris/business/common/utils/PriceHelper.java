package com.wanris.business.common.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceHelper {
    /**
     * 分转元，格式化字符串，小数点后字体小
     * @param cent
     * @return
     */
    public static CharSequence priceString(Long cent) {
        BigDecimal yuanBD = BigDecimal.valueOf(cent).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        BigDecimal stripZerosBD = yuanBD.stripTrailingZeros();

        String price = stripZerosBD.toPlainString();
        if (price.contains(".")) {
            int index = price.indexOf(".");
            SpannableString spannableString = new SpannableString(price);
            spannableString.setSpan(new AbsoluteSizeSpan(DensityHelper.sp2px(12)), index + 1, price.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
        else {
            return price;
        }
    }
}
