package com.neurix.simrs.master.operatorlab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.operatorlab.model.ImSimrsOperatorLabEntity;

import java.util.List;
import java.util.Map;

public class OperatorLabDao extends GenericDao<ImSimrsOperatorLabEntity, String> {
    @Override
    protected Class<ImSimrsOperatorLabEntity> getEntityClass() {
        return ImSimrsOperatorLabEntity.class;
    }

    @Override
    public List<ImSimrsOperatorLabEntity> getByCriteria(Map mapCriteria) {
return null;
    }
}