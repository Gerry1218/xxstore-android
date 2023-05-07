package com.wanris.business.common.base.present;

import android.content.Context;

import io.reactivex.disposables.Disposable;

public interface IBasePresenter<V> {
    void setContext(Context context);
    Context getContext();

    void attachView(V view);
    void detachView();

    void addTask(Disposable disposable);
}
