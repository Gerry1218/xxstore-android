package com.wanris.business.response;

import java.io.Serializable;

public class FreightTemplateCarriageDOList implements Serializable {
    private long continueMoney;
    private long continueNum;
    private String designatedArea;
    private long firstMoney;
    private long firstNum;
    private long freePrice;
    private long gmtCreate;
    private long gmtUpdate;
    private long id;
    private long templateID;

    public long getContinueMoney() {
        return continueMoney;
    }

    public void setContinueMoney(long continueMoney) {
        this.continueMoney = continueMoney;
    }

    public long getContinueNum() {
        return continueNum;
    }

    public void setContinueNum(long continueNum) {
        this.continueNum = continueNum;
    }

    public String getDesignatedArea() {
        return designatedArea;
    }

    public void setDesignatedArea(String designatedArea) {
        this.designatedArea = designatedArea;
    }

    public long getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(long firstMoney) {
        this.firstMoney = firstMoney;
    }

    public long getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(long firstNum) {
        this.firstNum = firstNum;
    }

    public long getFreePrice() {
        return freePrice;
    }

    public void setFreePrice(long freePrice) {
        this.freePrice = freePrice;
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

    public long getTemplateID() {
        return templateID;
    }

    public void setTemplateID(long templateID) {
        this.templateID = templateID;
    }
}
