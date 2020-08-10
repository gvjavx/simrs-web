package com.neurix.akuntansi.transaksi.budgetingnilaidasar.dao;

import com.neurix.akuntansi.transaksi.budgetingnilaidasar.model.ImAkunBudgetingNilaiDasarEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/08/20.
 */
public class MasterBudgetingNilaiDasarDao extends GenericDao<ImAkunBudgetingNilaiDasarEntity, String> {
    @Override
    protected Class<ImAkunBudgetingNilaiDasarEntity> getEntityClass() {
        return ImAkunBudgetingNilaiDasarEntity.class;
    }

    @Override
    public List<ImAkunBudgetingNilaiDasarEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunBudgetingNilaiDasarEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        criteria.addOrder(Order.asc("urutan"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_budgeting_nilai_dasar')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
