package com.neurix.simrs.master.obat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.model.Obat;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class ObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(ObatAction.class);
    private ObatBo obatBoProxy;
    private Obat obat;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ObatAction.logger = logger;
    }

    public ObatBo getObatBoProxy() {
        return obatBoProxy;
    }

    public void setObatBoProxy(ObatBo obatBoProxy) {
        this.obatBoProxy = obatBoProxy;
    }

    public Obat getObat() {
        return obat;
    }

    public void setObat(Obat obat) {
        this.obat = obat;
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

    public List<Obat> listObat(String idJenisObat){

        logger.info("[ObatAction.listObat] start process >>>");
        List<Obat> obatList = new ArrayList<>();
        Obat obat = new Obat();
        obat.setIdJenisObat(idJenisObat);

        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        ObatBo obatBo = (ObatBo) ctx.getBean("obatBoProxy");

        try {
            obatList = obatBo.getByCriteria(obat);
        }catch (GeneralBOException e){
            logger.error("[ObatAction.listObat] Error when get data obat ," + "Found problem when searching data, please inform to your admin.", e);
            addActionError("Error Found problem when saving add data, please inform to your admin.\n" + e.getMessage());
        }

        logger.info("[ObatAction.listObat] end process >>>");
        return obatList;

    }

}