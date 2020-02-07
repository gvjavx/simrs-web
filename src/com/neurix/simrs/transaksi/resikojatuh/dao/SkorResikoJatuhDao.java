package com.neurix.simrs.transaksi.resikojatuh.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.resikojatuh.model.ImSimrsSkorResikoJatuhEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class SkorResikoJatuhDao extends GenericDao<ImSimrsSkorResikoJatuhEntity, String> {

    @Override
    protected Class<ImSimrsSkorResikoJatuhEntity> getEntityClass() {
        return ImSimrsSkorResikoJatuhEntity.class;
    }

    @Override
    public List<ImSimrsSkorResikoJatuhEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsSkorResikoJatuhEntity.class);

        if (mapCriteria.get("id_skor") != null){
            criteria.add(Restrictions.eq("idSkor", mapCriteria.get("id_skor").toString()));
        }
        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }

        List<ImSimrsSkorResikoJatuhEntity> result = criteria.list();
        return result;
    }
}
