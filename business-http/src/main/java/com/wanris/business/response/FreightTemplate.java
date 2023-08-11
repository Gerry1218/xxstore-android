package com.wanris.business.response;

import java.io.Serializable;
import java.util.List;

public class FreightTemplate implements Serializable {
    private List<FreightTemplateCarriageDOList> freightTemplateCarriageDOList;
    private FreightTemplateDO freightTemplateDO;

    public List<FreightTemplateCarriageDOList> getFreightTemplateCarriageDOList() {
        return freightTemplateCarriageDOList;
    }

    public void setFreightTemplateCarriageDOList(List<FreightTemplateCarriageDOList> freightTemplateCarriageDOList) {
        this.freightTemplateCarriageDOList = freightTemplateCarriageDOList;
    }

    public FreightTemplateDO getFreightTemplateDO() {
        return freightTemplateDO;
    }

    public void setFreightTemplateDO(FreightTemplateDO freightTemplateDO) {
        this.freightTemplateDO = freightTemplateDO;
    }
}
