
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollInsentifNilaiSmkEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollInsentifEntity;
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
public class PayrollInsentifNilaiSmkDao extends GenericDao<ImPayrollInsentifNilaiSmkEntity, String> {

    @Override
    protected Class<ImPayrollInsentifNilaiSmkEntity> getEntityClass() {
        return ImPayrollInsentifNilaiSmkEntity.class;
    }

    @Override
    public List<ImPayrollInsentifNilaiSmkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollInsentifNilaiSmkEntity.class);

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

        List<ImPayrollInsentifNilaiSmkEntity> results = criteria.list();

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

    public List<ImPayrollInsentifNilaiSmkEntity> getNilaiByNip(String nip){
        List<ImPayrollInsentifNilaiSmkEntity> listOfResult = new ArrayList<ImPayrollInsentifNilaiSmkEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tnilai_angka,\n" +
                "\tnilai_huruf,\n" +
                "\tjumlah_bulan\n" +
                "from\n" +
                "\tim_hris_payroll_nilai_smk_insentif\n" +
                "where\n" +
                "\tnip = '"+nip+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ImPayrollInsentifNilaiSmkEntity result  = new ImPayrollInsentifNilaiSmkEntity();
            result.setNilaiSmkInsentif(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            result.setNilaiHuruf((String) row[1]);
            result.setJumlahBulan((int) row[2]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

}
