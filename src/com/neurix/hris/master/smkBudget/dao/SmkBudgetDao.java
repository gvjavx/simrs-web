
package com.neurix.hris.master.smkBudget.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkBudget.model.ImSmkBudgetEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class SmkBudgetDao extends GenericDao<ImSmkBudgetEntity, String> {

    @Override
    protected Class<ImSmkBudgetEntity> getEntityClass() {
        return ImSmkBudgetEntity.class;
    }

    @Override
    public List<ImSmkBudgetEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkBudgetEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("budget_id")!=null) {
                criteria.add(Restrictions.eq("budgetId", (String) mapCriteria.get("budget_id")));
            }
            if (mapCriteria.get("periode")!=null) {
                criteria.add(Restrictions.eq("periode",(String)mapCriteria.get("periode")));
            }
            if (mapCriteria.get("unit_id")!=null) {
                criteria.add(Restrictions.eq("unitId",(String)mapCriteria.get("unit_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("budgetId"));

        List<ImSmkBudgetEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkBudgetId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_budget')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "BD"+sId;
    }


    public List<ImSmkBudgetEntity> getListSmkBudget(String term) throws HibernateException {

        List<ImSmkBudgetEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkBudgetEntity.class)
                .add(Restrictions.eq("strukturJabatanId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("periode"))
                .setMaxResults(1)
                .list();

        return results;
    }


}
