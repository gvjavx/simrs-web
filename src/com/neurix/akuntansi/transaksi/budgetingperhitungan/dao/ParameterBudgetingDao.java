package com.neurix.akuntansi.transaksi.budgetingperhitungan.dao;

import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ImAkunParameterBudgetingEntity;
import com.neurix.akuntansi.transaksi.budgetingperhitungan.model.ItAkunPerhitunganBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/08/20.
 */
public class ParameterBudgetingDao extends GenericDao<ImAkunParameterBudgetingEntity, String> {
    @Override
    protected Class<ImAkunParameterBudgetingEntity> getEntityClass() {
        return ImAkunParameterBudgetingEntity.class;
    }

    @Override
    public List<ImAkunParameterBudgetingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_jenis_budgeting") != null){
            criteria.add(Restrictions.eq("idJenisBudgeting", mapCriteria.get("id_jenis_budgeting").toString()));
        }
        return criteria.list();
    }
}
