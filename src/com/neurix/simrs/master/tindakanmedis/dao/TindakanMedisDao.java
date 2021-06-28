package com.neurix.simrs.master.tindakanmedis.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.tindakanmedis.model.ImSimrsTindakanMedisEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;
/** Created by fahmi, for handling Tindakan Medis transaction between data and database  */
public class TindakanMedisDao extends GenericDao<ImSimrsTindakanMedisEntity, String> {
    @Override
    protected Class<ImSimrsTindakanMedisEntity> getEntityClass() {
        return ImSimrsTindakanMedisEntity.class;
    }

    @Override
    public List<ImSimrsTindakanMedisEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ImSimrsTindakanMedisEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }

        if (mapCriteria.get("name") != null){
            criteria.add(Restrictions.ilike("name", "%"+mapCriteria.get("name").toString()+"%"));
        }

        if (mapCriteria.get("type") != null){
            criteria.add(Restrictions.eq("type", mapCriteria.get("type").toString()));
        }

        if (mapCriteria.get("action") != null){
            criteria.add(Restrictions.eq("action", mapCriteria.get("action").toString()));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }else{
            criteria.add(Restrictions.eq("flag", "Y"));
        }

        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
