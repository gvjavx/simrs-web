package com.neurix.simrs.transaksi.skorrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsSkorRanapEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/02/20.
 */
public class MasterSkorRanapDao extends GenericDao<ImSimrsSkorRanapEntity, String> {

    @Override
    protected Class<ImSimrsSkorRanapEntity> getEntityClass() {
        return ImSimrsSkorRanapEntity.class;
    }

    @Override
    public List<ImSimrsSkorRanapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsSkorRanapEntity.class);
        if (mapCriteria.get("id_parameter") != null)
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        if (mapCriteria.get("id_skor") != null)
            criteria.add(Restrictions.eq("idSkor", mapCriteria.get("id_skor").toString()));

        List<ImSimrsSkorRanapEntity> results = criteria.list();
        return results;
    }
}
