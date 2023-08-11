package com.wanris.business.common.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wanris.business.common.R;

import java.util.ArrayList;
import java.util.List;

public class ActionSheetDialog {
    private Context mContext;
    private Dialog mDialog;
    private TextView mTitle;
    private TextView mCancel;
    private LinearLayout ll_layout_content;
    private ScrollView sv_layout_content;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;
    private CustomTextView customTextView;

    public ActionSheetDialog(Context context) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ActionSheetDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.action_sheet_dialog, null);
        view.setMinimumWidth(display.getWidth());

        sv_layout_content = view.findViewById(R.id.sv_content);
        ll_layout_content = view.findViewById(R.id.ll_content);
        mTitle = view.findViewById(R.id.tv_title);
        mCancel = view.findViewById(R.id.tv_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        Window window = mDialog.getWindow();
        window.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = 0;
        window.setAttributes(lp);
        return this;
    }

    public ActionSheetDialog setTitle(String text) {
        showTitle = true;
        mTitle.setText(text);
        mTitle.setVisibility(View.VISIBLE);
        return this;
    }

    public ActionSheetDialog setBottomButtonText(String text) {
        mCancel.setText(text);
        return this;
    }

    public void setBottomBtn(String text, View.OnClickListener listener) {
        mCancel.setText(text);
        mCancel.setOnClickListener(listener);
    }

    public ActionSheetDialog setCancelBtnGone() {
        mCancel.setVisibility(View.GONE);
        return this;
    }

    public ActionSheetDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public ActionSheetDialog addSheetItem(String strItem, SheetItemColor color, OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() == 0) {
            return;
        }

        int size = sheetItemList.size();

        // 添加条目过多的时候控制高度
        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) sv_layout_content.getLayoutParams();
            params.height = display.getHeight() / 2;
            sv_layout_content.setLayoutParams(params);
        }

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;
            TextView textView;
            if (customTextView != null) {
                textView = customTextView.getTextView();
            }
            else {
                textView = new TextView(mContext);
                textView.setTextSize(16);
                textView.setGravity(Gravity.CENTER);

                // 字体颜色
                if (color == null) {
                    textView.setTextColor(Color.parseColor(SheetItemColor.GRAY.getName()));
                } else {
                    textView.setTextColor(Color.parseColor(color.getName()));
                }

                // 高度
                float scale = mContext.getResources().getDisplayMetrics().density;
                int height = (int) (45 * scale + 0.5f);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));
            }
            // 如果是定制的textview，可能已经设置过字体和颜色
            if (TextUtils.isEmpty(textView.getText().toString())) {
                textView.setText(strItem);
            }

            // 背景图片
            if (size == 1) {
                if (showTitle) {
                    textView.setBackgroundResource(R.drawable.action_sheet_bottom_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.action_sheet_single_selector);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        textView.setBackgroundResource(R.drawable.action_sheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.action_sheet_bottom_selector);
                    }
                } else {
                    if (i == 1) {
                        textView.setBackgroundResource(R.drawable.action_sheet_top_selector);
                    } else if (i < size) {
                        textView.setBackgroundResource(R.drawable.action_sheet_middle_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.action_sheet_bottom_selector);
                    }
                }
            }

            // 点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(index);
                    }
                    mDialog.dismiss();
                }
            });

            ll_layout_content.addView(textView);

            boolean is_line = false;
            if (size >= 1) {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        is_line = true;
                    }
                } else {
                    if (i < size) {
                        is_line = true;
                    }
                }
            }
            if (is_line) {
                View view = new View(mContext);
                view.setBackgroundResource(R.drawable.action_sheet_middle_line);
                int height2 = (int) mContext.getResources().getDimension(R.dimen.dp_1);
                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height2));
                ll_layout_content.addView(view);
            }
        }
    }

    public void show() {
        setSheetItems();
        mDialog.show();
    }

    public class SheetItem {
        String name;
        SheetItemColor color;
        OnSheetItemClickListener itemClickListener;
        SheetItem(String name, SheetItemColor color, OnSheetItemClickListener listener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = listener;
        }
    }

    public enum SheetItemColor {
        RED("#FF3730"),
        GRAY("#626262")
        ;

        private String name;
        private SheetItemColor(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public interface OnSheetItemClickListener {
        void onClick(int index);
    }

    public interface CustomTextView {
        TextView getTextView();
    }
}
