package com.wanris.module.widget.bean;

import java.io.Serializable;
import java.util.List;

public class SpecGroup implements Serializable {
    // 规格分组id
    String id;
    // 规格分组名
    String name;
    // 规格列表
    List<Spec> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spec> getItems() {
        return items;
    }

    public void setItems(List<Spec> items) {
        this.items = items;
    }
}
