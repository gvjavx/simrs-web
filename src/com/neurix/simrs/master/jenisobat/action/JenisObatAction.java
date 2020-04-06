package com.neurix.simrs.master.jenisobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class JenisObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(JenisObatAction.class);
    private JenisObatBo jenisObatBoProxy;
    private JenisObat jenisObat;
    private List<JenisObat> listOfJenisObat = new ArrayList<>();

    public List<JenisObat> getListOfJenisObat() {
        return listOfJenisObat;
    }

    public void setListOfJenisObat(List<JenisObat> listOfJenisObat) {
        this.listOfJenisObat = listOfJenisObat;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisObatAction.logger = logger;
    }

    public JenisObatBo getJenisObatBoProxy() {
        return jenisObatBoProxy;
    }

    public void setJenisObatBoProxy(JenisObatBo jenisObatBoProxy) {
        this.jenisObatBoProxy = jenisObatBoProxy;
    }

    public JenisObat getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(JenisObat jenisObat) {
        this.jenisObat = jenisObat;
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

    public String getListJenisObat(){

        logger.info("[CheckupDetailAction.getListJenisObat] start process >>>");

        List<JenisObat> jenisObatList = new ArrayList<>();
        JenisObat jenisObat = new JenisObat();

        try {
            jenisObatList = jenisObatBoProxy.getByCriteria(jenisObat);
        }catch (GeneralBOException e){
            logger.error("[CheckupDetailAction.getListJenisObat] Error when get jenis obat ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfJenisObat.addAll(jenisObatList);
        logger.info("[CheckupDetailAction.getListJenisObat] end process <<<");
        return SUCCESS;

    }
}