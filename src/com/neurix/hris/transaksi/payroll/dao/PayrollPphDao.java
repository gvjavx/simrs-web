
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPphEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPphEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class PayrollPphDao extends GenericDao<ItPayrollPphEntity, String> {

    @Override
    protected Class<ItPayrollPphEntity> getEntityClass() {
        return ItPayrollPphEntity.class;
    }

    @Override
    public List<ItPayrollPphEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPphEntity.class);

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
        criteria.addOrder(Order.desc("pph_id"));

        List<ItPayrollPphEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollPphId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_pph')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = bulan + per[2] + per[3];

        return "PPH" + hasil + sId;
    }

    public List<ItPayrollPphEntity> getDataView(String payrollId) throws HibernateException {
        List<ItPayrollPphEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPphEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
