
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsHistoryEntity;
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
public class PayrollBpjsHistoryDao extends GenericDao<ImPayrollBpjsHistoryEntity, String> {

    @Override
    protected Class<ImPayrollBpjsHistoryEntity> getEntityClass() {
        return ImPayrollBpjsHistoryEntity.class;
    }

    @Override
    public List<ImPayrollBpjsHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsHistoryEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("bpjs_id")!=null) {
                criteria.add(Restrictions.eq("bpjsId", (String) mapCriteria.get("bpjs_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            
        }

        // Order by
        criteria.addOrder(Order.asc("branchId"));

        List<ImPayrollBpjsHistoryEntity> results = criteria.list();

        return results;
    }

    public String getNextBpjsHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_bpjs_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "BPJS"+sId;
    }
    
}
