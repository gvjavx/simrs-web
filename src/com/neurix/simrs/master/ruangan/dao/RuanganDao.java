package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganEntity;

import java.util.List;
import java.util.Map;

public class RuanganDao extends GenericDao<MtSimrsRuanganEntity, String> {
    @Override
    protected Class<MtSimrsRuanganEntity> getEntityClass() {
        return MtSimrsRuanganEntity.class;
    }

    @Override
    public List<MtSimrsRuanganEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}