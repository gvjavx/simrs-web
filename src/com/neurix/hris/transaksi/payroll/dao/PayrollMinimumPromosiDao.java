
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.ItPayrollMinimumPromosiEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollMinimumPromosiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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
public class PayrollMinimumPromosiDao extends GenericDao<ItPayrollMinimumPromosiEntity, String> {

    @Override
    protected Class<ItPayrollMinimumPromosiEntity> getEntityClass() {
        return ItPayrollMinimumPromosiEntity.class;
    }

    @Override
    public List<ItPayrollMinimumPromosiEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextMinimumPromosi() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_minimum_promosi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "PMP" + sId;
    }

    public List<ItPayrollMinimumPromosiEntity> getAllData() throws HibernateException {
        List<ItPayrollMinimumPromosiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollMinimumPromosiEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
