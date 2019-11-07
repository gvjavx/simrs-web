package com.neurix.simrs.master.lab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.model.Lab;
import org.apache.log4j.Logger;

public class LabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(LabAction.class);
    private LabBo labBoProxy;
    private Lab lab;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LabAction.logger = logger;
    }

    public LabBo getLabBoProxy() {
        return labBoProxy;
    }

    public void setLabBoProxy(LabBo labBoProxy) {
        this.labBoProxy = labBoProxy;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
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