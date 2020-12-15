package com.neurix.simrs.master.jenispersediaanobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class JenisPersediaanObatDao extends GenericDao<ImSimrsJenisPersediaanObatEntity, String>{

    @Override
    protected Class<ImSimrsJenisPersediaanObatEntity> getEntityClass() {
        return ImSimrsJenisPersediaanObatEntity.class;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisPersediaanObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));

        return criteria.list();
    }
}
