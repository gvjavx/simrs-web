package com.neurix.simrs.master.spesialis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import com.neurix.simrs.master.spesialis.model.ImSimrsSpesialisEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class SpesialisDao extends GenericDao<ImSimrsSpesialisEntity,String> {
    @Override
    protected Class<ImSimrsSpesialisEntity> getEntityClass() {
        return ImSimrsSpesialisEntity.class;
    }

    @Override
    public List<ImSimrsSpesialisEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsSpesialisEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id_spesialis") != null){
                criteria.add(Restrictions.eq("idSpesialis",mapCriteria.get("id_spesialis").toString()));
            }
        }

        List<ImSimrsSpesialisEntity> result = criteria.list();
        return result;
    }
}
