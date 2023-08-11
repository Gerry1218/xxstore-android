package com.wanris.business.response;

import java.io.Serializable;

public class SkuList implements Serializable {
    private String barCode;
    private long freezeStock;
    private long gmtCreate;
    private long gmtUpdate;
    private long id;
    private long originalPrice;
    private long price;
    private long spuID;
    private long stock;
    private String title;
    private long vipPrice;

    public String getBarCode() { return barCode; }
    public void setBarCode(String value) { this.barCode = value; }

    public long getFreezeStock() { return freezeStock; }
    public void setFreezeStock(long value) { this.freezeStock = value; }

    public long getGmtCreate() { return gmtCreate; }
    public void setGmtCreate(long value) { this.gmtCreate = value; }

    public long getGmtUpdate() { return gmtUpdate; }
    public void setGmtUpdate(long value) { this.gmtUpdate = value; }

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public long getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(long value) { this.originalPrice = value; }

    public long getPrice() { return price; }
    public void setPrice(long value) { this.price = value; }

    public long getSpuID() { return spuID; }
    public void setSpuID(long value) { this.spuID = value; }

    public long getStock() { return stock; }
    public void setStock(long value) { this.stock = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public long getVipPrice() { return vipPrice; }
    public void setVipPrice(long value) { this.vipPrice = value; }
}
