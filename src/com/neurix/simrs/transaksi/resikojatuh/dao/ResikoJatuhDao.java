package com.neurix.simrs.transaksi.resikojatuh.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.resikojatuh.model.ItSImrsResikoJatuhEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class ResikoJatuhDao extends GenericDao<ItSImrsResikoJatuhEntity, String> {

    @Override
    protected Class<ItSImrsResikoJatuhEntity> getEntityClass() {
        return ItSImrsResikoJatuhEntity.class;
    }

    @Override
    public List<ItSImrsResikoJatuhEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSImrsResikoJatuhEntity.class);

        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }

        List<ItSImrsResikoJatuhEntity> result = criteria.list();
        return result;
    }
}
