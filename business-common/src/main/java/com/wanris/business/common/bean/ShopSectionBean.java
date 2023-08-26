package com.wanris.business.common.bean;

import androidx.annotation.NonNull;

import java.util.List;

public class ShopSectionBean implements Cloneable {
    private String shopId;
    private String shopName;
    private String shopLogo;
    private boolean pitchOn;
    private List<ShopGoodsItemBean> goodsList;

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public boolean isPitchOn() {
        return pitchOn;
    }

    public void setPitchOn(boolean pitchOn) {
        this.pitchOn = pitchOn;
    }

    public List<ShopGoodsItemBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ShopGoodsItemBean> goodsList) {
        this.goodsList = goodsList;
    }
}
