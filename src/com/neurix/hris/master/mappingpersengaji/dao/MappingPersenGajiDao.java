package com.neurix.hris.master.mappingpersengaji.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGaji;

import java.util.List;
import java.util.Map;

public class MappingPersenGajiDao extends GenericDao<ImHrisMappingPersenGaji, String> {
    @Override
    protected Class<ImHrisMappingPersenGaji> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisMappingPersenGaji> getByCriteria(Map mapCriteria) {
        return null;
    }
}