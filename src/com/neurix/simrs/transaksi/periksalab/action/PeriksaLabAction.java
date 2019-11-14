package com.neurix.simrs.transaksi.periksalab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class PeriksaLabAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PeriksaLabAction.class);
    private PeriksaLab periksaLab;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaLabAction.logger = logger;
    }

    public PeriksaLab getPeriksaLab() {
        return periksaLab;
    }

    public void setPeriksaLab(PeriksaLab periksaLab) {
        this.periksaLab = periksaLab;
    }

    @Override
    public String add() {
        logger.info("[PeriksaLabAction.add] start process >>>");

        PeriksaLab periksaLab = new PeriksaLab();
        setPeriksaLab(periksaLab);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.removeAttribute("listOfResult");

        logger.info("[PeriksaLabAction.add] end process <<<");

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

        PeriksaLab periksaLab = new PeriksaLab();
        setPeriksaLab(periksaLab);

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