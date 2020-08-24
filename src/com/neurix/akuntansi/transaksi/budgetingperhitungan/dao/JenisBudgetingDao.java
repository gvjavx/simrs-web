package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ImAkunJenisBudgetingEntity;
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
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingEntity.class);

        if (mapCriteria.get("id_budgeting") != null){
            criteria.add(Restrictions.eq("idBudgeting", mapCriteria.get("id_budgeting").toString()));
        }

        return criteria.list();
    }
}