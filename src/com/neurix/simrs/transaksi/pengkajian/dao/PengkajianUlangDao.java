package com.neurix.simrs.transaksi.pengkajian.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.pengkajian.model.ItSimrsPengkajianEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/02/20.
 */
public class PengkajianUlangDao extends GenericDao<ItSimrsPengkajianEntity, String>{
    @Override
    protected Class<ItSimrsPengkajianEntity> getEntityClass() {
        return ItSimrsPengkajianEntity.class;
    }

    @Override
    public List<ItSimrsPengkajianEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPengkajianEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        List<ItSimrsPengkajianEntity> pengkajianEntities = criteria.list();
        return pengkajianEntities;
    }
}
