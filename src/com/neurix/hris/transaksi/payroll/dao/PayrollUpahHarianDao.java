
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollUpahHarianEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollUpahHarianEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
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
public class PayrollUpahHarianDao extends GenericDao<ItPayrollUpahHarianEntity, String> {

    @Override
    protected Class<ItPayrollUpahHarianEntity> getEntityClass() {
        return ItPayrollUpahHarianEntity.class;
    }

    @Override
    public List<ItPayrollUpahHarianEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollUpahHarianEntity.class);

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
        criteria.addOrder(Order.desc("payroll_id"));

        List<ItPayrollUpahHarianEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextUpahHarianDao() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_upah_harian')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "UH" + sId;
    }

    public List<ItPayrollUpahHarianEntity> getUpahHarian(String payrollId) throws HibernateException {
        List<ItPayrollUpahHarianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollUpahHarianEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollUpahHarianEntity> getUpahHarianTanggal(String payrollId, Date tanggal) throws HibernateException {
        List<ItPayrollUpahHarianEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollUpahHarianEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("tanggal", tanggal))
                .list();

        return results;
    }
}
