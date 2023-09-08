package com.wanris.module.widget.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GoodsSpec implements Serializable {
    @SerializedName("")
    List<SpecGroup> spec_group;
    List<Sku> sku_list;

    public List<SpecGroup> getSpec_group() {
        return spec_group;
    }

    public void setSpec_group(List<SpecGroup> spec_group) {
        this.spec_group = spec_group;
    }

    public List<Sku> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<Sku> sku_list) {
        this.sku_list = sku_list;
    }
}
