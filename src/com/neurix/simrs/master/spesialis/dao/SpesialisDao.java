package com.neurix.simrs.master.spesialis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPoliSpesialisEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class SpesialisDao extends GenericDao<ImSimrsPoliSpesialisEntity,String> {
    @Override
    protected Class<ImSimrsPoliSpesialisEntity> getEntityClass() {
        return ImSimrsPoliSpesialisEntity.class;
    }

    @Override
    public List<ImSimrsPoliSpesialisEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPoliSpesialisEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id_spesialis") != null){
                criteria.add(Restrictions.eq("idPesialis",mapCriteria.get("id_spesialis").toString()));
            }
        }

        List<ImSimrsPoliSpesialisEntity> result = criteria.list();
        return result;
    }
}
