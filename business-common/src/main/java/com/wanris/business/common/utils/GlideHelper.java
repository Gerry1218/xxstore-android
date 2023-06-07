package com.wanris.business.common.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideHelper {

    public static void loadRoundCorners(String url, ImageView imageView, int placeholderRes, int errorRes, int roundRadius, int margin) {
        Glide.with(imageView.getContext())
                .load(url)
                .transform(new RoundedCornersTransformation(roundRadius, margin, RoundedCornersTransformation.CornerType.ALL))
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop()
                .into(imageView);
    }

    public static void loadTopRoundCorner(String url, ImageView imageView, int placeholderRes, int errorRes, int corner) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop()
                .transform(new RoundedCornersTransformation(corner, 0, RoundedCornersTransformation.CornerType.TOP))
                .into(imageView);
    }
}
