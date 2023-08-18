package com.wanris.module.widget.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class GoodsSpecSectionBean extends SectionEntity<List<SpecItem>> {
    public GoodsSpecSectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GoodsSpecSectionBean(List<SpecItem> specItems) {
        super(specItems);
    }
}
