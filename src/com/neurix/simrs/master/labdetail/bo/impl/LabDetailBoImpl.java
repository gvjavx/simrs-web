package com.neurix.simrs.master.labdetail.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.master.labdetail.bo.LabDetailBo;
import com.neurix.simrs.master.labdetail.dao.LabDetailDao;
import com.neurix.simrs.master.labdetail.model.LabDetail;
import org.apache.log4j.Logger;

import java.util.List;

public class LabDetailBoImpl implements LabDetailBo {

    protected static transient Logger logger = Logger.getLogger(LabDetailBoImpl.class);
    private LabDetailDao labDetailDao;

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }

    @Override
    public void saveDelete(LabDetail bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(LabDetail bean) throws GeneralBOException {

    }

    @Override
    public LabDetail saveAdd(LabDetail bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<LabDetail> getByCriteria(LabDetail searchBean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<LabDetail> getAll() throws GeneralBOException {
        return null;
    }
}