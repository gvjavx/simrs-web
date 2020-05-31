package com.neurix.hris.master.mappingpersengaji.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.hris.master.mappingpersengaji.bo.MappingPersenGajiBo;
import com.neurix.hris.master.mappingpersengaji.model.MappingPersenGaji;
import org.apache.log4j.Logger;

public class MappingPersenGajiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(MappingPersenGajiAction.class);
    MappingPersenGaji mappingPersenGaji;
    private MappingPersenGajiBo mappingPersenGajiBoProxy;

    public MappingPersenGaji getMappingPersenGaji() {
        return mappingPersenGaji;
    }

    public void setMappingPersenGaji(MappingPersenGaji mappingPersenGaji) {
        this.mappingPersenGaji = mappingPersenGaji;
    }

    public MappingPersenGajiBo getMappingPersenGajiBoProxy() {
        return mappingPersenGajiBoProxy;
    }

    public void setMappingPersenGajiBoProxy(MappingPersenGajiBo mappingPersenGajiBoProxy) {
        this.mappingPersenGajiBoProxy = mappingPersenGajiBoProxy;
    }

    @Override
    public String add() {
        return null;
    }

    @Override
    public String edit() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String view() {
        return null;
    }

    @Override
    public String save() {
        return null;
    }

    @Override
    public String search() {
        return null;
    }

    @Override
    public String initForm() {
        return null;
    }

    @Override
    public String downloadPdf() {
        return null;
    }

    @Override
    public String downloadXls() {
        return null;
    }
}