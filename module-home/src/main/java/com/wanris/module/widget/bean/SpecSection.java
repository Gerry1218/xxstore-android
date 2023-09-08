package com.wanris.module.widget.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class SpecSection extends SectionEntity<List<Spec>> {
    // 规格分组id
    public String id;

    public SpecSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SpecSection(List<Spec> specGroups) {
        super(specGroups);
    }
}
