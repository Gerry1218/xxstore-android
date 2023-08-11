package com.wanris.business.common.utils;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;

public class MyJavaScriptInterface {
    private Activity mActivity;
    private ArrayList<String> images;

    public MyJavaScriptInterface(Activity context) {
        this.mActivity = context;
        this.images = new ArrayList<>();
    }

    @JavascriptInterface
    public void readImageUrl(String img) {
        if (!(img.toLowerCase()).endsWith("gif")){
            images.add(img);
        }
    }

    @JavascriptInterface
    public void showImage(String img) {
        mActivity.runOnUiThread(() -> {
            int position = images.indexOf(img);
            if (position < 0 || images.size() == 0) {
                return;
            }
//            new ShowImagesDialog(mActivity, images, position, false).show();
        });
    }
}
