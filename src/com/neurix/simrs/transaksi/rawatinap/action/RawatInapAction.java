package com.neurix.simrs.transaksi.rawatinap.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.transaksi.rawatinap.bo.RawatInapBo;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class RawatInapAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(RawatInapAction.class);
    private RawatInap rawatInap;
    private RawatInapBo rawatInapBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RawatInapAction.logger = logger;
    }

    public RawatInap getRawatInap() {
        return rawatInap;
    }

    public void setRawatInap(RawatInap rawatInap) {
        this.rawatInap = rawatInap;
    }

    public RawatInapBo getRawatInapBoProxy() {
        return rawatInapBoProxy;
    }

    public void setRawatInapBoProxy(RawatInapBo rawatInapBoProxy) {
        this.rawatInapBoProxy = rawatInapBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RawatInapAction.add] start process >>>");

        RawatInap rawatInap = new RawatInap();
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.add] end process <<<");

        return "init_add";
    }

    @Override
    public String edit() {
        return "init_edit";
    }

    @Override
    public String delete() {
        return "init_delete";
    }

    @Override
    public String view() {
        return "init_view";
    }

    @Override
    public String save() {
        return "init_save";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
        logger.info("[RawatInapAction.initForm] start process >>>");

        RawatInap rawatInap = new RawatInap();
        setRawatInap(rawatInap);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[RawatInapAction.initForm] end process <<<");

        return "search";
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