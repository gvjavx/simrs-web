
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollParamBpjsEntity;
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
public class PayrollParamBpjsDao extends GenericDao<ImPayrollParamBpjsEntity, String> {

    @Override
    protected Class<ImPayrollParamBpjsEntity> getEntityClass() {
        return ImPayrollParamBpjsEntity.class;
    }

    @Override
    public List<ImPayrollParamBpjsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollParamBpjsEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("bpjs_id")!=null) {
                criteria.add(Restrictions.eq("payrollParamBpjsId", (String) mapCriteria.get("bpjs_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("branchId"));

        List<ImPayrollParamBpjsEntity> results = criteria.list();

        return results;
    }

    public List<ImPayrollParamBpjsEntity> getBpjsById(String bpjsId) throws HibernateException {
        List<ImPayrollParamBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollParamBpjsEntity.class)
                .add(Restrictions.eq("bpjsId", bpjsId))
                .list();
        return results;
    }

    public List<ImPayrollParamBpjsEntity> getParams() throws HibernateException {
        List<ImPayrollParamBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollParamBpjsEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    public String getNextBpjs() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_bpjs')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "BPJS"+sId;
    }
    
}
