package com.neurix.simrs.master.dokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterSpesialisEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DokterSpesialisDao extends GenericDao<ImSimrsDokterSpesialisEntity,String> {
    @Override
    protected Class<ImSimrsDokterSpesialisEntity> getEntityClass() {
        return ImSimrsDokterSpesialisEntity.class;
    }

    @Override
    public List<ImSimrsDokterSpesialisEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterSpesialisEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_dokter") != null)
                criteria.add(Restrictions.eq("primariKey.idDokter", mapCriteria.get("id_dokter").toString()));
            if (mapCriteria.get("id_spesialis") != null)
                criteria.add(Restrictions.eq("primariKey.idSpesialis", mapCriteria.get("id_spesialis").toString()));
            if (mapCriteria.get("flag") != null)
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        List<ImSimrsDokterSpesialisEntity> result = criteria.list();
        return result;
    }
}
