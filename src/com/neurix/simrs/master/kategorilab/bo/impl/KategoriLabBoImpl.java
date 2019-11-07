package com.neurix.simrs.master.kategorilab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import com.neurix.simrs.master.kategorilab.dao.KategoriLabDao;
import org.apache.log4j.Logger;

import java.util.List;

public class KategoriLabBoImpl implements JenisObatBo {

    protected static transient Logger logger = Logger.getLogger(KategoriLabBoImpl.class);
    private KategoriLabDao kategoriLabDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KategoriLabBoImpl.logger = logger;
    }

    public KategoriLabDao getKategoriLabDao() {
        return kategoriLabDao;
    }

    public void setKategoriLabDao(KategoriLabDao kategoriLabDao) {
        this.kategoriLabDao = kategoriLabDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveDelete(JenisObat bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(JenisObat bean) throws GeneralBOException {

    }

    @Override
    public JenisObat saveAdd(JenisObat bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<JenisObat> getByCriteria(JenisObat searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<JenisObat> getAll() throws GeneralBOException {
        return null;
    }
}