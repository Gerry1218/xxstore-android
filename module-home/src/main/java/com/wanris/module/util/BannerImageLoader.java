package com.wanris.module.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.wanris.business.common.utils.GlideHelper;
import com.youth.banner.loader.ImageLoader;

public class BannerImageLoader extends ImageLoader  {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        try {
            String url = (String)path;
            GlideHelper.load(url, imageView, 0, 0);
        }
        catch (Exception e) {
            Log.d("BannerImageLoader", "displayImage: ");
        }
    }
}
