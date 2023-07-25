package com.wanris.business.common.utils;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wanris.business.common.BaseApplication;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideHelper {

    public static void load(String url, ImageView imageView, int placeholderRes, int errorRes) {
        if (placeholderRes == -1) {
            if (errorRes == -1) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .transition(withCrossFade())
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext())
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .error(errorRes)
                        .transition(withCrossFade())
                        .into(imageView);
            }
        } else
            Glide.with(BaseApplication.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(placeholderRes).error(errorRes).into(imageView);
    }
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

    public static void loadRoundImage(String url, View view, int errorRes, int corner) {
        Glide.with(view.getContext()).
                load(url).
                placeholder(errorRes)
                .error(errorRes)
                .centerCrop()
                .transform(new RoundedCornersTransformation(corner, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if(view instanceof TextView) {
                                TextView tv = (TextView) view;
                                int width = (int) (resource.getMinimumWidth() * view.getContext().getResources().getDisplayMetrics().density / 2);
                                int height = (int) (resource.getMinimumHeight() * view.getContext().getResources().getDisplayMetrics().density / 2);
                                resource.setBounds(0, 0, width, height);
                                tv.setCompoundDrawables(resource, null, null, null);
                            }
                        }
                    }
                });
    }
}
