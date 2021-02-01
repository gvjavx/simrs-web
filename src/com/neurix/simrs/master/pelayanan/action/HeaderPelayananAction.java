package com.neurix.simrs.master.pelayanan.action;

import com.neurix.common.action.BaseTransactionAction;
import com.neurix.simrs.master.pelayanan.bo.HeaderPelayananBo;
import org.apache.log4j.Logger;

public class HeaderPelayananAction extends BaseTransactionAction {
    protected static transient Logger logger = Logger.getLogger(HeaderPelayananAction.class);
    private HeaderPelayananBo headerPelayananBoProxy;

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        return "search";
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setHeaderPelayananBoProxy(HeaderPelayananBo headerPelayananBoProxy) {
        this.headerPelayananBoProxy = headerPelayananBoProxy;
    }
}