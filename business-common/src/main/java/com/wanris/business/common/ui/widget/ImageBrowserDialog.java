package com.wanris.business.common.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.PhotoView;
import com.wanris.business.common.R;

import java.util.ArrayList;
import java.util.List;

public class ImageBrowserDialog extends Dialog {
    private View mView;
    private Context mContext;
    private ImageBrowserViewPager mViewPager;
    private TextView mIndexText;
    private TextView mSaveLocal;
    private List<String> mImgUrls;
    private List<String> mTitles;
    private List<View> mViews;
    private ImageBrowserAdapter mAdapter;
    private int position;
    private boolean isClick;

    public ImageBrowserDialog(@NonNull Context context, List<String> imgUrls) {
        this(context, imgUrls, 0, false);
    }

    public ImageBrowserDialog(@NonNull Context context, List<String> imgUrls, int position, boolean isClick) {
        super(context, R.style.transparent_dialog_bg);
        this.mContext = context;
        this.mImgUrls = imgUrls;
        this.position = position;
        this.isClick = isClick;
        initView();
        initData();
    }

    private void initView() {
        mView = View.inflate(mContext, R.layout.image_browser_dialog, null);
        mViewPager = (ImageBrowserViewPager) mView.findViewById(R.id.vp_image_browser);
        mIndexText = (TextView) mView.findViewById(R.id.tv_image_index);
        mSaveLocal = (TextView) mView.findViewById(R.id.st_save_2_local);
        mSaveLocal.setVisibility(View.GONE);
        mTitles = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    private void initData() {
        for (int i = 0; i < mImgUrls.size(); i++) {
            final PhotoView photoView = new PhotoView(mContext);
            ViewGroup.LayoutParams layoutParams =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setOnPhotoTapListener((view, x, y) -> dismiss());
            // 点击图片外围（无图片处）监听
            /**
             photoView.setOnViewTapListener(new OnViewTapListener() {
            @Override public void onViewTap(View view, float x, float y){
            dismiss();
            }
            });
             **/
            Glide.with(mContext)
                    .load(mImgUrls.get(i))
                    .placeholder(R.mipmap.icon_image_error)
                    .error(R.mipmap.icon_image_error)
                    .into(new CustomTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            photoView.setImageDrawable(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
            mViews.add(photoView);
            mTitles.add(i + "");
            if (isClick) {
                photoView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int position = mViewPager.getCurrentItem();
                        String imgUrl = mImgUrls.get(position);
//                        final ActionSheetDialog mDialog = new ActionSheetDialog(getContext())
//                                .builder();
//                        mDialog.setCancelable(true);// 允许点击边框外销毁弹框
//                        mDialog.addSheetItem("保存到手机", ActionSheetDialog.SheetItemColor.Blue, which -> savePic2LocalAction(imgUrl))
//                                .show();
                        return true;
                    }
                });
            }

        }

        mAdapter = new ImageBrowserAdapter(mViews, mTitles);
        mViewPager.setAdapter(mAdapter);
        mIndexText.setText(1 + "/" + mImgUrls.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndexText.setText(position + 1 + "/" + mImgUrls.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.getDecorView().setBackgroundColor(Color.WHITE);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 延伸显示区域到刘海
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
            // 设置页面全屏显示
            final View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        window.setAttributes(layoutParams);
    }
}
