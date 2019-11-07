package com.neurix.simrs.master.lab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.lab.model.Lab;

import java.util.List;
import java.util.Map;

public class LabDao extends GenericDao<Lab, String> {
    @Override
    protected Class<Lab> getEntityClass() {
        return Lab.class;
    }

    @Override
    public List<Lab> getByCriteria(Map mapCriteria) {
        return null;
    }
}