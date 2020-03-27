
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollPtkpEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPttEntity;
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
public class PayrollPttDao extends GenericDao<ItPayrollPttEntity, String> {

    @Override
    protected Class<ItPayrollPttEntity>  getEntityClass() {
        return ItPayrollPttEntity.class;
    }

    @Override
    public List<ItPayrollPttEntity> getByCriteria(Map mapCriteria) {
        List<ItPayrollPttEntity> results = new ArrayList<>();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_ptt')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "PPT" + sId;
    }

    public List<ItPayrollPttEntity> getDataPtt(String payrollId) throws HibernateException {
        List<ItPayrollPttEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollPttEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
    public List<ItPayrollPttEntity> getDataPttByNipAndTahun(String nip,String tahun) throws HibernateException {
        List<ItPayrollPttEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollPtkpEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.ne("bulan", "12"))
                .list();
        return results;
    }
}
