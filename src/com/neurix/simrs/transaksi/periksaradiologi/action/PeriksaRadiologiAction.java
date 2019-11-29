package com.neurix.simrs.transaksi.periksaradiologi.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.transaksi.periksaradiologi.bo.PeriksaRadiologiBo;
import com.neurix.simrs.transaksi.periksaradiologi.model.PeriksaRadiologi;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class PeriksaRadiologiAction extends BaseMasterAction {
    protected static transient Logger logger = Logger.getLogger(PeriksaRadiologiAction.class);
    private PeriksaRadiologi periksaRadiologi;
    private PeriksaRadiologiBo periksaRadiologiBoProxy;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        PeriksaRadiologiAction.logger = logger;
    }

    public PeriksaRadiologi getPeriksaRadiologi() {
        return periksaRadiologi;
    }

    public void setPeriksaRadiologi(PeriksaRadiologi periksaRadiologi) {
        this.periksaRadiologi = periksaRadiologi;
    }

    public void setPeriksaRadiologiBoProxy(PeriksaRadiologiBo periksaRadiologiBoProxy) {
        this.periksaRadiologiBoProxy = periksaRadiologiBoProxy;
    }

    @Override
    public String add() {
        logger.info("[RawatInapAction.add] start process >>>");

        PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
        setPeriksaRadiologi(periksaRadiologi);

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

        PeriksaRadiologi periksaRadiologi = new PeriksaRadiologi();
        setPeriksaRadiologi(periksaRadiologi);

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