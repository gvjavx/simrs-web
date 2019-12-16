package com.neurix.simrs.master.dokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DokterDao extends GenericDao<ImSimrsDokterEntity, String> {

    @Override
    protected Class<ImSimrsDokterEntity> getEntityClass() {
        return ImSimrsDokterEntity.class;
    }

    @Override
    public List<ImSimrsDokterEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_dokter") != null){
                criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
            }

        List<ImSimrsDokterEntity> result = criteria.list();
        return result;
    }
}
