package com.wanris.business.common.bean;

import java.io.Serializable;

public class WebViewParam implements Serializable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "WebViewParam{" +
                "url='" + url + '\'' +
                '}';
    }
}
