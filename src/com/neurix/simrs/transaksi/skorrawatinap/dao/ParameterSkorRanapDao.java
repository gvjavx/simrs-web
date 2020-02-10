package com.neurix.simrs.transaksi.skorrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsParameterSkorRanapEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/02/20.
 */
public class ParameterSkorRanapDao extends GenericDao <ImSimrsParameterSkorRanapEntity, String> {

    @Override
    protected Class<ImSimrsParameterSkorRanapEntity> getEntityClass() {
        return ImSimrsParameterSkorRanapEntity.class;
    }

    @Override
    public List<ImSimrsParameterSkorRanapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterSkorRanapEntity.class);

        if (mapCriteria.get("id_kategori") != null)
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        if (mapCriteria.get("id_parameter") != null)
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));

        List<ImSimrsParameterSkorRanapEntity> results = criteria.list();
        return results;
    }
}
