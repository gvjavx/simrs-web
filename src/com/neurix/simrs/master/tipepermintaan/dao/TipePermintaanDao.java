package com.neurix.simrs.master.tipepermintaan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tipepermintaan.model.ImSimrsTipePermintaanEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 10/12/2019.
 */
public class TipePermintaanDao extends GenericDao<ImSimrsTipePermintaanEntity,String> {

    @Override
    protected Class<ImSimrsTipePermintaanEntity> getEntityClass() {
        return ImSimrsTipePermintaanEntity.class;
    }

    @Override
    public List<ImSimrsTipePermintaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTipePermintaanEntity.class);

        if (mapCriteria.get("id_permintaan") != null)
            criteria.add(Restrictions.eq("idPermintaan", mapCriteria.get("id_permintaan").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        List<ImSimrsTipePermintaanEntity> results = criteria.list();
        return results;
    }
}
