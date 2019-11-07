package com.neurix.simrs.master.kategorilab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;

import java.util.List;
import java.util.Map;

public class KategoriLabDao extends GenericDao<ImSimrsKategoriLabEntity,String> {

    @Override
    protected Class<ImSimrsKategoriLabEntity> getEntityClass() {
        return ImSimrsKategoriLabEntity.class;
    }

    @Override
    public List<ImSimrsKategoriLabEntity> getByCriteria(Map mapCriteria) {
        return null;
    }
}