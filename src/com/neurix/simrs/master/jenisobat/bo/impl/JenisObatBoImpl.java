package com.neurix.simrs.master.jenisobat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.jenisobat.bo.JenisObatBo;
import com.neurix.simrs.master.jenisobat.dao.JenisObatDao;
import com.neurix.simrs.master.jenisobat.model.JenisObat;
import org.apache.log4j.Logger;

import java.util.List;

public class JenisObatBoImpl implements JenisObatBo {

    protected static transient Logger logger = Logger.getLogger(JenisObatBoImpl.class);
    private JenisObatDao jenisObatDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JenisObatBoImpl.logger = logger;
    }

    public JenisObatDao getJenisObatDao() {
        return jenisObatDao;
    }

    public void setJenisObatDao(JenisObatDao jenisObatDao) {
        this.jenisObatDao = jenisObatDao;
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