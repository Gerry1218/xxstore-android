package com.wanris.business.common.base.present;

import android.content.Context;

import com.wanris.business.common.base.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    private Context context;
    private V view;
    private CompositeDisposable compositeDisposable;

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    @Override
    public void addTask(Disposable disposable) {
        if (compositeDisposable == null || disposable == null) {
            return;
        }
        compositeDisposable.add(disposable);
    }

    public V getView() {
        return view;
    }
}
