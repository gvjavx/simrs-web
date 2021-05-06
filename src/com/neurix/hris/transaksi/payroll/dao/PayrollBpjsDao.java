
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollBpjsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class PayrollBpjsDao extends GenericDao<ImPayrollBpjsEntity, String> {

    @Override
    protected Class<ImPayrollBpjsEntity> getEntityClass() {
        return ImPayrollBpjsEntity.class;
    }

    @Override
    public List<ImPayrollBpjsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class);

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

        List<ImPayrollBpjsEntity> results = criteria.list();

        return results;
    }

    public List<ImPayrollBpjsEntity> getPersenBpjs() throws HibernateException {
        List<ImPayrollBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    public List<ImPayrollBpjsEntity> getBpjsById(String bpjsId) throws HibernateException {
        List<ImPayrollBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class)
                .add(Restrictions.eq("bpjsId", bpjsId))
                .list();
        return results;
    }
    public List<ImPayrollBpjsEntity> getBpjsFilter(String branchId) throws HibernateException {
        List<ImPayrollBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBpjsEntity.class)
                .add(Restrictions.eq("branchId", branchId))
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
