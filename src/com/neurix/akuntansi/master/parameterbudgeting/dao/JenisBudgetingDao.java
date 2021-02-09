package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunJenisBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class JenisBudgetingDao extends GenericDao<ImAkunJenisBudgetingEntity, String>{

    @Override
    protected Class<ImAkunJenisBudgetingEntity> getEntityClass() {
        return ImAkunJenisBudgetingEntity.class;
    }

    @Override
    public List<ImAkunJenisBudgetingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunJenisBudgetingEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama_jenis") != null)
            criteria.add(Restrictions.eq("namaJenis", mapCriteria.get("nama_jenis").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }
}
