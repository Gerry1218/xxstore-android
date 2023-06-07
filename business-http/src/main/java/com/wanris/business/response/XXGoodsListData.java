package com.wanris.business.response;

import java.util.List;

public class XXGoodsListData {
    private Integer code;
    private Integer count;
    private String msg;
    private Integer pageNo;
    private Integer pageSize;
    private Integer total;
    private Integer totalPageNo;
    private List<XXGoodsItemBean> items;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(Integer totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public List<XXGoodsItemBean> getItems() {
        return items;
    }

    public void setItems(List<XXGoodsItemBean> items) {
        this.items = items;
    }

    static public class XXGoodsItemBean {
    private long categoryID;
    private long deliveryTime;
    private String description;
    private long freightTemplateID;
    private long id;
    private String img;
    private Long limitBuyNum;
    private long originalPrice;
    private long price;
    private long sales;
    private SkuList[] skuList;
    private long status;
    private Long stock;
    private String title;
    private String unit;
    private long vipPrice;

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFreightTemplateID() {
        return freightTemplateID;
    }

    public void setFreightTemplateID(long freightTemplateID) {
        this.freightTemplateID = freightTemplateID;
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

    public Long getLimitBuyNum() {
        return limitBuyNum;
    }

    public void setLimitBuyNum(Long limitBuyNum) {
        this.limitBuyNum = limitBuyNum;
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

    public SkuList[] getSkuList() {
        return skuList;
    }

    public void setSkuList(SkuList[] skuList) {
        this.skuList = skuList;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
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
    static public class SkuList {
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

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public long getFreezeStock() {
            return freezeStock;
        }

        public void setFreezeStock(long freezeStock) {
            this.freezeStock = freezeStock;
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

        public long getSpuID() {
            return spuID;
        }

        public void setSpuID(long spuID) {
            this.spuID = spuID;
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

        public long getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(long vipPrice) {
            this.vipPrice = vipPrice;
        }
    }
}
