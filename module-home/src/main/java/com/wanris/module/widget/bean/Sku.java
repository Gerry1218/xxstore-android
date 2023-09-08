package com.wanris.module.widget.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Sku implements Serializable {
    // sku的id
    String id;
    String price;
    // 是否可选
    boolean isEnabled;
    // 库存
    int stock;
    // 关联的规格 key 规格分组id， value 规格
    List<Spec> specificationValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Spec> getSpecificationValues() {
        return specificationValues;
    }

    public void setSpecificationValues(List<Spec> specificationValues) {
        this.specificationValues = specificationValues;
    }
}
