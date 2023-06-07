package com.wanris.business.response;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private String errmsg;
    private long errno;
    private long timestamp;
    private T data;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public long getErrno() {
        return errno;
    }

    public void setErrno(long errno) {
        this.errno = errno;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
