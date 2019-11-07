package com.neurix.simrs.master.labdetail.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;

public class LabDetailAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(LabDetailAction.class);
    private LabDetailBo labDetailBoProxy;
    private LabDetail labDetail;

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