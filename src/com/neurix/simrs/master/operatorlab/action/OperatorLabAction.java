package com.neurix.simrs.master.operatorlab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.master.operatorlab.bo.OperatorLabBo;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;
import org.apache.log4j.Logger;

public class OperatorLabAction  extends BaseMasterAction{

    protected static transient Logger logger = Logger.getLogger(OperatorLabAction.class);
    private OperatorLabBo operatorLabBoProxy;
    private OperatorLab operatorLab;

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