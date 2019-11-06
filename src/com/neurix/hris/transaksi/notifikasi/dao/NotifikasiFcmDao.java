package com.neurix.hris.transaksi.notifikasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;

import java.util.List;
import java.util.Map;

public class NotifikasiFcmDao extends GenericDao<ItNotifikasiFcmEntity, String> {


    @Override
    protected Class<ItNotifikasiFcmEntity> getEntityClass() {
        return ItNotifikasiFcmEntity.class;
    }

    @Override
    public List<ItNotifikasiFcmEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}
