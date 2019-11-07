package com.neurix.simrs.master.labdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.labdetail.model.ImSimrsLabDetailEntity;

import java.util.List;
import java.util.Map;

public class LabDetailDao extends GenericDao<ImSimrsLabDetailEntity, String> {

    @Override
    protected Class<ImSimrsLabDetailEntity> getEntityClass() {
        return ImSimrsLabDetailEntity.class;
    }

    @Override
    public List<ImSimrsLabDetailEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}