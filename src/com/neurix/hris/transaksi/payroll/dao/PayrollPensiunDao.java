
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPensiunEntity;
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
public class PayrollPensiunDao extends GenericDao<ItPayrollPensiunEntity, String> {

    @Override
    protected Class<ItPayrollPensiunEntity> getEntityClass() {
        return ItPayrollPensiunEntity.class;
    }

    @Override
    public List<ItPayrollPensiunEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollPensiunId(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_pensiun')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "PNS" + per[2] + per[3] + sId;
    }

    public List<ItPayrollPensiunEntity> getPensiun(String payrollId) throws HibernateException {
        List<ItPayrollPensiunEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPensiunEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .list();

        return results;
    }
    
}
