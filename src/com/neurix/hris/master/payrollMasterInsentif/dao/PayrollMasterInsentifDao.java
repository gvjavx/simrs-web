package com.neurix.hris.master.payrollMasterInsentif.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollMasterInsentif.model.ImPayrollMasterInsentifEntity;
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
public class PayrollMasterInsentifDao extends GenericDao<ImPayrollMasterInsentifEntity, String> {

    @Override
    protected Class<ImPayrollMasterInsentifEntity>  getEntityClass() {
        return ImPayrollMasterInsentifEntity.class;
    }

    @Override
    public List<ImPayrollMasterInsentifEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollMasterInsentifEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_insentif_id")!=null) {
                criteria.add(Restrictions.eq("payrollInsentifId", (String) mapCriteria.get("payroll_insentif_id")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollInsentifId"));
        List<ImPayrollMasterInsentifEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollInsentif() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_master_insentif')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "PINS" + sId;
    }

    public List<ImPayrollMasterInsentifEntity> getDataMasterInsentif(String branchId) throws HibernateException {
        List<ImPayrollMasterInsentifEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollMasterInsentifEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}