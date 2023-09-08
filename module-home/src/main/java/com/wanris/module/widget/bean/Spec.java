package com.wanris.module.widget.bean;

import java.io.Serializable;

public class Spec implements Serializable {
    // 规格id
    String id;
    // 规格名
    String value;

    // 扩展字段
    boolean clickable = true;
    boolean selected;
    boolean enable = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enabled) {
        this.enable = enabled;
    }
}
