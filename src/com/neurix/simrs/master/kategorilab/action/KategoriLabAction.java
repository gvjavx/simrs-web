package com.neurix.simrs.master.kategorilab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class KategoriLabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriLabAction.class);
    private KategoriLabBo kategoriLabBoProxy;
    private KategoriLab kategoriLab;

    private List<KategoriLab> listOfKategoriLab = new ArrayList<>();

    public List<KategoriLab> getListOfKategoriLab() {
        return listOfKategoriLab;
    }

    public void setListOfKategoriLab(List<KategoriLab> listOfKategoriLab) {
        this.listOfKategoriLab = listOfKategoriLab;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setKategoriLabBoProxy(KategoriLabBo kategoriLabBoProxy) {
        this.kategoriLabBoProxy = kategoriLabBoProxy;
    }

    public KategoriLab getKategoriLab() {
        return kategoriLab;
    }

    public void setKategoriLab(KategoriLab kategoriLab) {
        this.kategoriLab = kategoriLab;
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

    public String getListKategoriLab(){

        logger.info("[KategoriLabAction.getListKategoriLab] start process >>>");

        List<KategoriLab> kategoriLabList = new ArrayList<>();
        KategoriLab kategoriLab = new KategoriLab();

        try {
            kategoriLabList = kategoriLabBoProxy.getByCriteria(kategoriLab);
        }catch (GeneralBOException e){
            logger.error("[KategoriLabAction.getListKategoriLab] Error when get kategori lab ," + "Found problem when saving add data, please inform to your admin.", e);
        }

        listOfKategoriLab.addAll(kategoriLabList);
        logger.info("[KategoriLabAction.getListKategoriLab] end process <<<");
        return SUCCESS;

    }
}