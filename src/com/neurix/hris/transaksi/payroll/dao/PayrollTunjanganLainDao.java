
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollTunjanganLainEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class PayrollTunjanganLainDao extends GenericDao<ItPayrollTunjanganLainEntity, String> {

    @Override
    protected Class<ItPayrollTunjanganLainEntity> getEntityClass() {
        return ItPayrollTunjanganLainEntity.class;
    }

    @Override
    public List<ItPayrollTunjanganLainEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollTunjanganLainEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_id")!=null) {
                criteria.add(Restrictions.eq("payrollId", (String) mapCriteria.get("payroll_id")));
            }

            if (mapCriteria.get("bulan")!=null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("tunjLainId"));

        List<ItPayrollTunjanganLainEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollTunjanganLainId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunj_lain')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = bulan + per[2] + per[3];

        return "PTL" + hasil + sId;
    }

    public List<ItPayrollTunjanganLainEntity> getDataView(String payrollId) throws HibernateException {
        List<ItPayrollTunjanganLainEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollTunjanganLainEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
