package com.neurix.simrs.master.keteranganobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.keteranganobat.model.ImSimrsKeteranganObatEntity;
import com.neurix.simrs.master.parameterketeranganobat.model.ImSimrsParemeterKeteranganObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class KeteranganObatDao extends GenericDao<ImSimrsKeteranganObatEntity, String>{

    @Override
    protected Class<ImSimrsKeteranganObatEntity> getEntityClass() {
        return ImSimrsKeteranganObatEntity.class;
    }

    @Override
    public List<ImSimrsKeteranganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKeteranganObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_sub_jenis") != null)
            criteria.add(Restrictions.eq("idSubJenis", mapCriteria.get("id_sub_jenis").toString()));
        if (mapCriteria.get("id_parameter_keterangan") != null)
            criteria.add(Restrictions.eq("idParameterKeterangan", mapCriteria.get("id_parameter_keterangan").toString()));
        if (mapCriteria.get("keterangan") != null)
            criteria.add(Restrictions.ilike("keterangan",  "%" + mapCriteria.get("keterangan").toString() + "%"));
        return criteria.list();

    }
}
