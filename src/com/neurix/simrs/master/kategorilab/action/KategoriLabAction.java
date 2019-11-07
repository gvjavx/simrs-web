package com.neurix.simrs.master.kategorilab.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.master.kategorilab.bo.KategoriLabBo;
import com.neurix.simrs.master.kategorilab.model.KategoriLab;
import org.apache.log4j.Logger;

public class KategoriLabAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(KategoriLabAction.class);
    private KategoriLabBo kategoriLabBoProxy;
    private KategoriLab kategoriLab;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KategoriLabAction.logger = logger;
    }

    public KategoriLabBo getKategoriLabBoProxy() {
        return kategoriLabBoProxy;
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
}