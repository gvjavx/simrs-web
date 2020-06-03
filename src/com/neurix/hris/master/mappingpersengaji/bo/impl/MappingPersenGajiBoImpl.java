package com.neurix.hris.master.mappingpersengaji.bo.impl;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.hris.master.mappingpersengaji.bo.MappingPersenGajiBo;
import com.neurix.hris.master.mappingpersengaji.dao.MappingPersenGajiDao;
import com.neurix.simrs.master.dokter.model.Dokter;
import org.apache.log4j.Logger;

import java.util.List;

public class MappingPersenGajiBoImpl implements MappingPersenGajiBo {
    private static transient Logger logger = Logger.getLogger(MappingPersenGajiBoImpl.class);
    private MappingPersenGajiDao mappingPersenGajiDao;

    public MappingPersenGajiDao getMappingPersenGajiDao() {
        return mappingPersenGajiDao;
    }

    public void setMappingPersenGajiDao(MappingPersenGajiDao mappingPersenGajiDao) {
        this.mappingPersenGajiDao = mappingPersenGajiDao;
    }

    @Override
    public void saveDelete(Dokter bean) throws GeneralBOException {

    }

    @Override
    public void saveEdit(Dokter bean) throws GeneralBOException {

    }

    @Override
    public Dokter saveAdd(Dokter bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Dokter> getByCriteria(Dokter bean) throws GeneralBOException {
        return null;
    }

    @Override
    public List<Dokter> getAll() throws GeneralBOException {
        return null;
    }

    @Override
    public Long saveErrorMessage(String message, String moduleMethod) throws GeneralBOException {
        return null;
    }
}