package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.master.kodeRekening.model.ImKodeRekeningEntity;
import com.neurix.akuntansi.master.kodeRekening.model.KodeRekening;
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
        if (mapCriteria.get("approve_flag") != null){
            criteria.add(Restrictions.eq("approveFlag", mapCriteria.get("approve_flag").toString()));
        }
        if (mapCriteria.get("tipe") != null){
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }

        List<ItAkunBudgetingEntity> akunBudgetingEntities = criteria.list();
        return akunBudgetingEntities;
    }

    public List<ImKodeRekeningEntity> getCoaLastLevel(String id){
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImKodeRekeningEntity.class);
        criteria.add(Restrictions.eq("level", new Long(5)));
        criteria.add(Restrictions.ilike("kodeRekening", id));
        return criteria.list();
    }
}
