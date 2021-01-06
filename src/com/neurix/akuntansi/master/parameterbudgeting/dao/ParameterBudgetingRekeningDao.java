package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunParameterBudgetingRekeningEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParameterBudgetingRekeningDao extends GenericDao<ImAkunParameterBudgetingRekeningEntity, String> {
    @Override
    protected Class<ImAkunParameterBudgetingRekeningEntity> getEntityClass() {
        return ImAkunParameterBudgetingRekeningEntity.class;
    }

    @Override
    public List<ImAkunParameterBudgetingRekeningEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunParameterBudgetingRekeningEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_parameter_budgeting_rekening')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "PBN"+sId;
    }

    public List<ImAkunParameterBudgetingRekeningEntity> getParameterBudgetingRekening(String nama ) throws HibernateException {
        List<ImAkunParameterBudgetingRekeningEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImAkunParameterBudgetingRekeningEntity.class)
                .add(Restrictions.ilike("nama", nama))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }
}
