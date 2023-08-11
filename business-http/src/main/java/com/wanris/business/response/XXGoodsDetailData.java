package com.wanris.business.response;

import java.io.Serializable;
import java.util.List;

public class XXGoodsDetailData implements Serializable {
    private AppraisePage appraisePage;
    private List attributeList;
    private Long categoryID;
    private List<Long> categoryIDS;
    private String description;
    private String detail;
    private FreightTemplate freightTemplate;
    private long freightTemplateID;
    private long gmtCreate;
    private long gmtUpdate;
    private long id;
    private String img;
    private List<String> imgList;
    private long originalPrice;
    private long price;
    private long sales;
    private List<SkuList> skuList;
    private long status;
    private long stock;
    private String title;
    private String unit;
    private long vipPrice;

    public AppraisePage getAppraisePage() {
        return appraisePage;
    }

    public void setAppraisePage(AppraisePage appraisePage) {
        this.appraisePage = appraisePage;
    }

    public List getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List attributeList) {
        this.attributeList = attributeList;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public List<Long> getCategoryIDS() {
        return categoryIDS;
    }

    public void setCategoryIDS(List<Long> categoryIDS) {
        this.categoryIDS = categoryIDS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public FreightTemplate getFreightTemplate() {
        return freightTemplate;
    }

    public void setFreightTemplate(FreightTemplate freightTemplate) {
        this.freightTemplate = freightTemplate;
    }

    public long getFreightTemplateID() {
        return freightTemplateID;
    }

    public void setFreightTemplateID(long freightTemplateID) {
        this.freightTemplateID = freightTemplateID;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public List<SkuList> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuList> skuList) {
        this.skuList = skuList;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(long vipPrice) {
        this.vipPrice = vipPrice;
    }
}
