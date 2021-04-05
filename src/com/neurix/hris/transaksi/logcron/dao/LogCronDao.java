package com.neurix.hris.transaksi.logcron.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.logcron.model.ItLogCronEntity;

import java.util.List;
import java.util.Map;

public class LogCronDao extends GenericDao<ItLogCronEntity, String> {
    @Override
    protected Class<ItLogCronEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItLogCronEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}
