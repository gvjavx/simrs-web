
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPotonganLainEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPotonganLainEntity;
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
public class PayrollPotonganLainDao extends GenericDao<ItPayrollPotonganLainEntity, String> {

    @Override
    protected Class<ItPayrollPotonganLainEntity> getEntityClass() {
        return ItPayrollPotonganLainEntity.class;
    }

    @Override
    public List<ItPayrollPotonganLainEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPotonganLainEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
        }
        // Order by
        criteria.addOrder(Order.desc("payroll_id"));
        List<ItPayrollPotonganLainEntity> results = criteria.list();
        return results;
    }

    public String getNextPotonganLainId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_potongan_lain')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "POTLN" + sId;
    }

    public List<ItPayrollPotonganLainEntity> getDataPotongan(String payrollId) throws HibernateException {
        List<ItPayrollPotonganLainEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPotonganLainEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
