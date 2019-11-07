package com.neurix.simrs.master.jenisobat.action;

import com.neurix.common.action.BaseMasterAction;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import org.apache.log4j.Logger;

public class JenisObatAction extends BaseMasterAction {

    protected static transient Logger logger = Logger.getLogger(JenisObatAction.class);
    private JenisObatBo jenisObatBoProxy;
    private JenisObat jenisObat;

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
}