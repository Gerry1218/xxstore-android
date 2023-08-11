package com.wanris.business.response;

import java.io.Serializable;
import java.util.List;

public class AppraisePage implements Serializable {
    private long code;
    private long count;
    private List items;
    private String msg;
    private long pageNo;
    private long pageSize;
    private long total;
    private long totalPageNo;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(long totalPageNo) {
        this.totalPageNo = totalPageNo;
    }
}
