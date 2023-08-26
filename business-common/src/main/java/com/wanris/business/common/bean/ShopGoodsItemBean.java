package com.wanris.business.common.bean;

import androidx.annotation.NonNull;

public class ShopGoodsItemBean implements Cloneable{
    private String goodsId;
    private String goodsName;
    private String goodsUrl;
    private String price;
    private Integer count;
    private boolean pitchOn;

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isPitchOn() {
        return pitchOn;
    }

    public void setPitchOn(boolean pitchOn) {
        this.pitchOn = pitchOn;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
