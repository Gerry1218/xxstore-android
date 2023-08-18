package com.wanris.module.widget.bean;

public class SpecItem {
    String sId;
    String name;
    String url;

    boolean enable;
    boolean clickable;
    boolean selected;
    boolean saleOut;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSaleOut() {
        return saleOut;
    }

    public void setSaleOut(boolean saleOut) {
        this.saleOut = saleOut;
    }
}
