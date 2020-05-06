package com.neurix.akuntansi.transaksi.budgeting.dao;

import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingDetailEntity;
import com.neurix.akuntansi.transaksi.budgeting.model.ItAkunBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
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
        if (mapCriteria.get("id_budgeting_detail") != null){
            criteria.add(Restrictions.eq("idBudgetingDetail", mapCriteria.get("id_budgeting_detail").toString()));
        }
        if (mapCriteria.get("no_budgeting") != null){
            criteria.add(Restrictions.eq("noBudgeting", mapCriteria.get("no_budgeting").toString()));
        }

        if (mapCriteria.get("id_budgeting") != null){
            criteria.add(Restrictions.eq("idBudgeting", mapCriteria.get("id_budgeting").toString()));
        }

        if (mapCriteria.get("divisi_id") != null){
            criteria.add(Restrictions.eq("divisiId", mapCriteria.get("divisi_id").toString()));
        }
        if (mapCriteria.get("position_id") != null){
            criteria.add(Restrictions.eq("positionId", mapCriteria.get("divisi_id").toString()));
        }
        if (mapCriteria.get("tipe") != null){
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        }

        List<ItAkunBudgetingDetailEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_budgeting_detail')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
