package com.wanris.business.response;

import java.io.Serializable;

public class FreightTemplateDO implements Serializable {
    private long defaultContinueMoney;
    private long defaultContinueNum;
    private long defaultFirstMoney;
    private long defaultFirstNum;
    private long defaultFreePrice;
    private long deliveryDeadline;
    private long gmtCreate;
    private long gmtUpdate;
    private long id;
    private String spuLocation;
    private String templateName;

    public long getDefaultContinueMoney() {
        return defaultContinueMoney;
    }

    public void setDefaultContinueMoney(long defaultContinueMoney) {
        this.defaultContinueMoney = defaultContinueMoney;
    }

    public long getDefaultContinueNum() {
        return defaultContinueNum;
    }

    public void setDefaultContinueNum(long defaultContinueNum) {
        this.defaultContinueNum = defaultContinueNum;
    }

    public long getDefaultFirstMoney() {
        return defaultFirstMoney;
    }

    public void setDefaultFirstMoney(long defaultFirstMoney) {
        this.defaultFirstMoney = defaultFirstMoney;
    }

    public long getDefaultFirstNum() {
        return defaultFirstNum;
    }

    public void setDefaultFirstNum(long defaultFirstNum) {
        this.defaultFirstNum = defaultFirstNum;
    }

    public long getDefaultFreePrice() {
        return defaultFreePrice;
    }

    public void setDefaultFreePrice(long defaultFreePrice) {
        this.defaultFreePrice = defaultFreePrice;
    }

    public long getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(long deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public long getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(long gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpuLocation() {
        return spuLocation;
    }

    public void setSpuLocation(String spuLocation) {
        this.spuLocation = spuLocation;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
