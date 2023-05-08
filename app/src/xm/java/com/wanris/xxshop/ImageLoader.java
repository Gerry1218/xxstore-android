package com.wanris.xxshop;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wanris.business.common.BaseApplication;

public class ImageLoader {
    private static String TAG = ImageLoader.class.getSimpleName();
    static void load(String url, ImageView imageView) {
        Log.d(TAG, "ImageLoader: xm");
        Glide.with(BaseApplication.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(0)
                .error(0)
                .into(imageView);
    }
}
