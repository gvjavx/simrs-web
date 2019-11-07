package com.neurix.simrs.master.obat.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.obat.bo.ObatBo;
import com.neurix.simrs.master.obat.dao.ObatDao;
import com.neurix.simrs.master.obat.model.Obat;
import org.apache.log4j.Logger;

import java.util.List;

public class ObatBoImpl implements ObatBo {

    protected static transient Logger logger = Logger.getLogger(ObatBoImpl.class);
    private ObatDao obatDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ObatBoImpl.logger = logger;
    }

    public ObatDao getObatDao() {
        return obatDao;
    }

    public void setObatDao(ObatDao obatDao) {
        this.obatDao = obatDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
return null;
    }

    @Override
    public void saveDelete(Obat bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Obat bean) throws GeneralBOException {

    }

    @Override
    public Obat saveAdd(Obat bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Obat> getByCriteria(Obat searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Obat> getAll() throws GeneralBOException {
        return null;
    }
}