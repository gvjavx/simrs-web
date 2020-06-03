package com.neurix.hris.master.mappingpersengaji.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.mappingpersengaji.model.ImHrisMappingPersenGaji;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class MappingPersenGajiDao extends GenericDao<ImHrisMappingPersenGaji, String> {
    @Override
    protected Class<ImHrisMappingPersenGaji> getEntityClass() {
        return ImHrisMappingPersenGaji.class;
    }

    @Override
    public List<ImHrisMappingPersenGaji> getByCriteria(Map mapCriteria) {
        return null;
    }

    public List<ImHrisMappingPersenGaji> getListMappingPersenGaji(String namaMapping) throws HibernateException {
        List<ImHrisMappingPersenGaji> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisMappingPersenGaji.class)
                .add(Restrictions.eq("namaMappingPersenGaji", namaMapping))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}