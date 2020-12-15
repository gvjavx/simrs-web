package com.neurix.simrs.master.parameterketeranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParameterKeteranganObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class ParameterKeteranganObatDao extends GenericDao<ImSimrsParameterKeteranganObatEntity, String>{
    @Override
    protected Class<ImSimrsParameterKeteranganObatEntity> getEntityClass() {
        return ImSimrsParameterKeteranganObatEntity.class;
    }

    @Override
    public List<ImSimrsParameterKeteranganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterKeteranganObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        return criteria.list();
    }
}
