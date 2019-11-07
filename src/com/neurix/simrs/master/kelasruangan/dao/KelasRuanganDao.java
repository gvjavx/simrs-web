package com.neurix.simrs.master.kelasruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;

import java.util.List;
import java.util.Map;

public class KelasRuanganDao extends GenericDao<ImSimrsKelasRuanganEntity, String> {
    @Override
    protected Class<ImSimrsKelasRuanganEntity> getEntityClass() {
        return ImSimrsKelasRuanganEntity.class;
    }

    @Override
    public List<ImSimrsKelasRuanganEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}