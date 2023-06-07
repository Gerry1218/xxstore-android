package com.wanris.business;

public interface ICallback<T> {
    void success(T t);
    void failure();
}
