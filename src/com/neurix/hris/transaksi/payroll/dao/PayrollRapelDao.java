
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelEntity;
import com.neurix.hris.transaksi.payroll.model.PayrollRapel;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollRapelDao extends GenericDao<ItPayrollRapelEntity, String> {

    @Override
    protected Class<ItPayrollRapelEntity> getEntityClass() {
        return ItPayrollRapelEntity.class;
    }

    @Override
    public List<ItPayrollRapelEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelEntity.class);

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

        List<ItPayrollRapelEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRapelDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_rapel')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "RPL" + per[2] + per[3] + sId;
    }

    public List<ItPayrollRapelEntity> getRapel(String payrollId) throws HibernateException {
        List<ItPayrollRapelEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public PayrollRapel getJumlahBulan(String bulan, String tahun, String unit) throws HibernateException {
        PayrollRapel payrollRapel = new PayrollRapel();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\trapel.jumlah_bulan_rapel,\n" +
                "\trapel.tanggal_awal,\n" +
                "\trapel.tanggal_akhir\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id and rapel.payroll_id is not null\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.flag_rapel = 'Y'\n" +
                "limit 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        int hasil = 0;
        for (Object[] row : results) {
            PayrollRapel result  = new PayrollRapel();

            payrollRapel.setJumlahBulan((int) row[1]);
            payrollRapel.setTanggalAwal((Date) row[2]);
            payrollRapel.setTanggalAkhir((Date) row[3]);

        }
        return payrollRapel;
    }
}
