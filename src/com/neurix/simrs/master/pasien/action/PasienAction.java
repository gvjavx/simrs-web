package com.neurix.simrs.master.pasien.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.pasien.bo.PasienBo;
import com.neurix.simrs.master.pasien.model.Pasien;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class PasienAction extends BaseMasterAction {
    private static transient Logger logger = Logger.getLogger(PasienAction.class);

    private Pasien pasien;
    private PasienBo pasienBoProxy;

    @Override
    public String add() {
        return "add";
    }

    @Override
    public String edit() {
        return "edit";
    }

    @Override
    public String delete() {
        return "delete";
    }

    @Override
    public String view() {
        return "view";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String search() {
        return "search";
    }

    @Override
    public String initForm() {
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

    public List getListComboPasien(String query){
        logger.info("[PasienAction.getListComboPasien] start process >>>");

        List<Pasien> listOfPasien = new ArrayList();

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        PasienBo pasienBo = (PasienBo) ctx.getBean("pasienBoProxy");

        try {
            listOfPasien = pasienBo.getListComboPasien(query);
        } catch (GeneralBOException e) {
            logger.error("[PasienAction.getListComboPasien] Error when get combo pasien, please inform to your admin.", e);
        }

        logger.info("[PasienAction.getListComboPasien] end process <<<");
        return listOfPasien;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    public void setPasienBoProxy(PasienBo pasienBoProxy) {
        this.pasienBoProxy = pasienBoProxy;
    }
}