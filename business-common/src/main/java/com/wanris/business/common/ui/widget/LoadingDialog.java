package com.wanris.business.common.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanris.business.common.R;

public class LoadingDialog {
    private static final String TAG = LoadingDialog.class.getSimpleName();
    private static Dialog loadingDialog;
    private static ImageView ivLoading;
    private static Animation animation;
    private static TextView tv_upload;

    public static void show(Context context) {
        if (loadingDialog != null) {
            close();
        }
        create(context);
    }

    public static void showUpLoad(Context context) {
        if (loadingDialog != null) {
            close();
        }
        create(context);
        tv_upload.setVisibility(View.VISIBLE);
    }

    private static void create(Context context) {
        try {
            loadingDialog = new Dialog(context, R.style.TransparentDialog);
            View loadView = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
            ivLoading = loadView.findViewById(R.id.iv_loading);
            tv_upload = loadView.findViewById(R.id.tv_upload);
            animation = AnimationUtils.loadAnimation(context, R.anim.progress_drawable_white);
            ivLoading.startAnimation(animation);//开始动画
            loadingDialog.setContentView(loadView);
            loadingDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            Log.e(TAG, "create: error");
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (loadingDialog != null) {
                ivLoading.clearAnimation();
                loadingDialog.dismiss();
                loadingDialog = null;
                ivLoading = null;
                animation = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
