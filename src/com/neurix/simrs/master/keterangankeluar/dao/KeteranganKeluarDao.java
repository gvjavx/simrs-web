package com.neurix.simrs.master.keterangankeluar.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keterangankeluar.model.ImSimrsKeteranganKeluarEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class KeteranganKeluarDao extends GenericDao<ImSimrsKeteranganKeluarEntity,String> {
    @Override
    protected Class<ImSimrsKeteranganKeluarEntity> getEntityClass() {
        return ImSimrsKeteranganKeluarEntity.class;
    }

    @Override
    public List<ImSimrsKeteranganKeluarEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKeteranganKeluarEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_keterangan") != null) {
                criteria.add(Restrictions.eq("idKeterangan", mapCriteria.get("id_keterangan").toString()));
            }
        List<ImSimrsKeteranganKeluarEntity> result = criteria.list();
        return result;
    }
}
