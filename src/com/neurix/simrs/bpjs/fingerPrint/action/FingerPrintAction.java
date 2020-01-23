package com.neurix.simrs.bpjs.fingerPrint.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.bpjs.fingerPrint.bo.impl.FingerPrintBoImpl;
import com.neurix.simrs.bpjs.fingerPrint.model.FingerPrint;
import org.apache.log4j.Logger;

public class FingerPrintAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(FingerPrintAction.class);
    private FingerPrintBoImpl tindakanBoProxy;
    private FingerPrint fingerPrint;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        FingerPrintAction.logger = logger;
    }

    public FingerPrintBoImpl getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(FingerPrintBoImpl tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public FingerPrint getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(FingerPrint fingerPrint) {
        this.fingerPrint = fingerPrint;
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

    private String loginFinger(){
        logger.info("[FingerPrintAction.getByCriteria] Start >>>>>>>");



        logger.info("[FingerPrintAction.getByCriteria] Stop <<<<<<<<");
        return null;
    }
}