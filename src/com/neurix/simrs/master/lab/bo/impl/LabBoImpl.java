package com.neurix.simrs.master.lab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.lab.action.LabAction;
import com.neurix.simrs.master.lab.bo.LabBo;
import com.neurix.simrs.master.lab.dao.LabDao;
import com.neurix.simrs.master.lab.model.Lab;
import org.apache.log4j.Logger;

import java.util.List;

public class LabBoImpl implements LabBo {

    protected static transient Logger logger = Logger.getLogger(LabBoImpl.class);
    private LabDao labDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        LabBoImpl.logger = logger;
    }

    public LabDao getLabDao() {
        return labDao;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveDelete(Lab bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Lab bean) throws GeneralBOException {

    }

    @Override
    public Lab saveAdd(Lab bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Lab> getByCriteria(Lab searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Lab> getAll() throws GeneralBOException {
        return null;
    }
}