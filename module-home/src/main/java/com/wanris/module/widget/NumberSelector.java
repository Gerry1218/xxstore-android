package com.wanris.module.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.coorchice.library.SuperTextView;
import com.wanris.module.home.R;

public class NumberSelector extends LinearLayout {
    private static final String TAG = NumberSelector.class.getSimpleName();
    private final Integer INFINITE = -1; // 无限制
    private SuperTextView stNumDec;
    private SuperTextView stNumInc;
    private EditText etNum;
    private Integer mCount = 1;
    private Integer mMaxCount = INFINITE;

    private OnChangeListener listener;

    private Context mContext;
    public NumberSelector(Context context) {
        this(context, null);
    }

    public NumberSelector(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberSelector(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        initView();
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public void setMaxCount(Integer maxCount) {
        mMaxCount = maxCount;
    }

    public void setCount(Integer count) {
        mCount = count;
        etNum.setText(count.toString());
    }
    public Integer getCount() {
        return mCount;
    }

    void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.NumberSelector);
        mMaxCount = typedArray.getInteger(R.styleable.NumberSelector_max_count, -1);
        typedArray.recycle();
    }
    void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.number_select_layout, this, true);
        stNumDec = findViewById(R.id.st_num_dec);
        stNumInc = findViewById(R.id.st_num_inc);
        etNum = findViewById(R.id.et_num);
        etNum.setText(mCount.toString());

        stNumDec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount == 1) {
                    return;
                }
                mCount--;
                etNum.setText(mCount.toString());
                if (listener != null) {
                    listener.countChange(mCount);
                    listener.decrement(mCount);
                }
            }
        });
        stNumInc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMaxCount != INFINITE && mCount >= mMaxCount) {
                    if (listener != null) {
                        listener.exceedMaxValue();
                    }
                    return;
                }
                mCount++;
                etNum.setText(mCount.toString());
                if (listener != null) {
                    listener.countChange(mCount);
                    listener.increment(mCount);
                }
            }
        });
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: " + s + "-" + start + "-" + before + "-" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    public interface OnChangeListener {
         void increment(Integer count);
         void decrement(Integer count);
         void countChange(Integer count);

         void exceedMaxValue();
    }
}
