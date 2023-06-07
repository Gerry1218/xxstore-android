package com.wanris.business.common.base.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.wanris.business.common.R;
import com.wanris.business.common.base.present.IBasePresenter;
import com.wanris.business.common.base.view.IBaseView;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<V extends IBaseView, P extends IBasePresenter> extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    public BaseActivity instance;

    /**
     * 沉浸式状态栏
     */
    protected ImmersionBar immersionBar;

    private P presenter;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        instance = this;
        if (applyFullScreen()) {
            setFullScreenModel();
        }
        else if (applyImmersionBar()) {
            setImmersionBar(getStatusBarColor());
        }
        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }

        presenter = initPresenter();
        if (presenter != null) {
            presenter.setContext(this);
            presenter.attachView((V) this);
        }
        compositeDisposable = new CompositeDisposable();
        init(savedInstanceState);
        initViews(savedInstanceState);
        initToolbar(savedInstanceState);
        initListener();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
        if (presenter != null) {
            presenter.detachView();
        }

        if (applyImmersionBar() || applyFullScreen()) {
//            if (immersionBar != null) immersionBar.destroy(this);
            immersionBar = null;
        }

        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish: ");
    }

    /**
     * 防止内存泄露
     * @param disposable
     */
    public void addTask(Disposable disposable) {
        if (compositeDisposable == null || disposable == null) {
            return;
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 是否设置全屏
     * @return
     */
    protected boolean applyFullScreen() {
        return false;
    }

    /**
     * 是否设置沉浸式状态栏
     * @return
     */
    protected boolean applyImmersionBar() {
        return true;
    }

    /**
     * 注册EventBus
     * @return
     */
    protected boolean applyEventBus() {
        return false;
    }

    /**
     * 全屏App内容填充状态栏
     */
    protected void setFullScreenModel() {
        immersionBar = ImmersionBar.with(this);
        immersionBar.keyboardEnable(false)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 系统StatusBar颜色
     *
     * @return
     */
    protected int getStatusBarColor() {
        return R.color.colorPrimary;
    }


    /**
     * 设置系统statusBar颜色
     *
     * @param statusBarColor 状态栏颜色
     */
    protected void setImmersionBar(int statusBarColor) {
        setImmersionBar(statusBarColor, false);
    }

    /**
     * 设置系统statusBar颜色
     *
     * @param statusBarColor 状态栏颜色
     */
    protected void setImmersionBar(int statusBarColor, boolean keyboardEnable) {
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(statusBarColor)
                .keyboardEnable(keyboardEnable)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    /**
     * 设置系统statusBar颜色
     * @param isDark
     */
    protected void setImmersionBar(boolean isDark) {
        immersionBar = ImmersionBar.with(this);
        immersionBar.keyboardEnable(false)
                .statusBarDarkFont(isDark)
                .init();
    }
    /**
     * 初始化
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState) {}

    /**
     * 初始化toolBar
     */
    protected void initToolbar(Bundle savedInstanceState) {}

    /**
     * 初始化view
     */
    protected void initViews(Bundle savedInstanceState) {}

    /**
     * 初始化数据
     */
    protected void initData() {}

    /**
     * 设置监听器
     */
    protected void initListener() {}

    /**
     * 布局layout id
     * @return
     */
    protected abstract int getLayoutId();
    /**
     * 初始化Presenter
     * @return
     */
    protected abstract P initPresenter();
    /**
     * 返回Presenter
     * @return
     */
    protected P getPresenter() { return presenter; }

}
