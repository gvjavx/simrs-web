
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollInsentifEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollPendidikanEntity;
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
public class PayrollInsentifDao extends GenericDao<ItPayrollInsentifEntity, String> {

    @Override
    protected Class<ItPayrollInsentifEntity> getEntityClass() {
        return ItPayrollInsentifEntity.class;
    }

    @Override
    public List<ItPayrollInsentifEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollInsentifEntity.class);

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

        List<ItPayrollInsentifEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextInsentifDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_insentif')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "INS" + per[2] + per[3] + sId;
    }

    public List<ItPayrollInsentifEntity> getInsentifByNip(String nip){
        List<ItPayrollInsentifEntity> listOfResult = new ArrayList<ItPayrollInsentifEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\t*\n" +
                "from\n" +
                "\tit_hris_payroll_insentif\n" +
                "where\n" +
                "\tnip = '"+nip+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollInsentifEntity result  = new ItPayrollInsentifEntity();
            result.setInsentifId((String) row[0]);
            result.setJumlahInsentif(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollInsentifEntity> getInsentif(String payrollId) throws HibernateException {
        List<ItPayrollInsentifEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollInsentifEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollInsentifEntity> getInsentifByNipAndTahun(String nip,String tahun) throws HibernateException {
        List<ItPayrollInsentifEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollInsentifEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
