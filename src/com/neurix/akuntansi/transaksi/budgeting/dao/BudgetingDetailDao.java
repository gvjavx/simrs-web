package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingDetailEntity;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 29/04/20.
 */
public class BudgetingDetailDao extends GenericDao<ItAkunBudgetingDetailEntity, String> {

    @Override
    protected Class<ItAkunBudgetingDetailEntity> getEntityClass() {
        return ItAkunBudgetingDetailEntity.class;
    }

    @Override
    public List<ItAkunBudgetingDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunBudgetingDetailEntity.class);
        if (mapCriteria.get("no_budgeting") != null){
            criteria.add(Restrictions.eq("noBudgeting", mapCriteria.get("no_budgeting").toString()));
        }
        if (mapCriteria.get("divisi_id") != null){
            criteria.add(Restrictions.eq("divisiId", mapCriteria.get("divisi_id").toString()));
        }
        if (mapCriteria.get("tipe") != null){
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }

        List<ItAkunBudgetingDetailEntity> results = criteria.list();
        return results;
    }
}
