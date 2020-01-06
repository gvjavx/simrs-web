package com.neurix.simrs.bpjs.tindakan.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.bpjs.tindakan.bo.TindakanBpjsBo;
import com.neurix.simrs.bpjs.tindakan.bo.impl.TindakanBpjsBoImpl;
import com.neurix.simrs.bpjs.tindakan.model.TindakanBpjs;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TindakanBpjsAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(TindakanBpjsAction.class);
    private TindakanBpjsBoImpl tindakanBoProxy;
    private TindakanBpjs tindakanBpjs;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TindakanBpjsAction.logger = logger;
    }

    public TindakanBpjsBoImpl getTindakanBoProxy() {
        return tindakanBoProxy;
    }

    public void setTindakanBoProxy(TindakanBpjsBoImpl tindakanBoProxy) {
        this.tindakanBoProxy = tindakanBoProxy;
    }

    public TindakanBpjs getTindakanBpjs() {
        return tindakanBpjs;
    }

    public void setTindakanBpjs(TindakanBpjs tindakanBpjs) {
        this.tindakanBpjs = tindakanBpjs;
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