package com.neurix.simrs.master.operatorlab.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.operatorlab.bo.OperatorLabBo;
import com.neurix.simrs.master.operatorlab.dao.OperatorLabDao;
import com.neurix.simrs.master.operatorlab.model.OperatorLab;
import org.apache.log4j.Logger;

import java.util.List;

public class OperatorLabBoImpl implements OperatorLabBo {

    protected static transient Logger logger = Logger.getLogger(OperatorLabBoImpl.class);
    private OperatorLabDao operatorLabDao;

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        OperatorLabBoImpl.logger = logger;
    }

    public OperatorLabDao getOperatorLabDao() {
        return operatorLabDao;
    }

    public void setOperatorLabDao(OperatorLabDao operatorLabDao) {
        this.operatorLabDao = operatorLabDao;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
return null;
    }

    @Override
    public void saveDelete(OperatorLab bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(OperatorLab bean) throws GeneralBOException {

    }

    @Override
    public OperatorLab saveAdd(OperatorLab bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<OperatorLab> getByCriteria(OperatorLab searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<OperatorLab> getAll() throws GeneralBOException {
        return null;
    }
}