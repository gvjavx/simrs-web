package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;

import java.util.List;
import java.util.Map;

public class ObatDao extends GenericDao<ImSimrsObatEntity, String> {
    @Override
    protected Class<ImSimrsObatEntity> getEntityClass() {
        return ImSimrsObatEntity.class;
    }

    @Override
    public List<ImSimrsObatEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}