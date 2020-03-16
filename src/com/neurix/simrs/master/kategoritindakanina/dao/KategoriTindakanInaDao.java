package com.neurix.simrs.master.kategoritindakanina.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategoritindakanina.model.ImSimrsKategoriTindakanInaEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 13/03/20.
 */
public class KategoriTindakanInaDao extends GenericDao<ImSimrsKategoriTindakanInaEntity, String> {

    @Override
    protected Class<ImSimrsKategoriTindakanInaEntity> getEntityClass() {
        return ImSimrsKategoriTindakanInaEntity.class;
    }

    @Override
    public List<ImSimrsKategoriTindakanInaEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanInaEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("nama") != null){
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        }
        return criteria.list();
    }
}