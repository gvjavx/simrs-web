package com.neurix.simrs.transaksi.rencanarawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rencanarawat.model.ImSimrsKategoriRencanaRawatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ImSimrsParameterRencanaRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/02/20.
 */
public class ParameterRencanaRawatDao extends GenericDao<ImSimrsParameterRencanaRawatEntity, String> {

    @Override
    protected Class<ImSimrsParameterRencanaRawatEntity> getEntityClass() {
        return ImSimrsParameterRencanaRawatEntity.class;
    }

    @Override
    public List<ImSimrsParameterRencanaRawatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterRencanaRawatEntity.class);
        if (mapCriteria.get("id_kategori") != null){
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        }
        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }

        List<ImSimrsParameterRencanaRawatEntity> results = criteria.list();
        return results;
    }
}
