package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingDao extends GenericDao<ItAkunBudgetingEntity, String> {

    @Override
    protected Class<ItAkunBudgetingEntity> getEntityClass() {
        return ItAkunBudgetingEntity.class;
    }

    @Override
    public List<ItAkunBudgetingEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingEntity.class);
        if (mapCriteria.get("no_budgeting") != null){
            criteria.add(Restrictions.eq("noBudgeting", mapCriteria.get("no_budgeting").toString()));
        }
        if (mapCriteria.get("tahun") != null){
            criteria.add(Restrictions.eq("tahun", mapCriteria.get("tahun").toString()));
        }
        if (mapCriteria.get("branch_id") != null){
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        }

        List<ItAkunBudgetingEntity> akunBudgetingEntities = criteria.list();
        return akunBudgetingEntities;
    }
}
