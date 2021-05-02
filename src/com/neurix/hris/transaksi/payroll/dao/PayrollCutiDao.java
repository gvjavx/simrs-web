
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollCutiEntity;
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
public class PayrollCutiDao extends GenericDao<ItPayrollCutiEntity, String> {

    @Override
    protected Class<ItPayrollCutiEntity> getEntityClass() {
        return ItPayrollCutiEntity.class;
    }

    @Override
    public List<ItPayrollCutiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollCutiEntity.class);

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

        List<ItPayrollCutiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextCutiId(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_cuti')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "CPR" + per[2] + per[3] + sId;
    }

    public List<ItPayrollCutiEntity> getCuti(String payrollId) throws HibernateException {
        List<ItPayrollCutiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollCutiEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
    public List<ItPayrollCutiEntity> cekIfCutiPanjangExist(String nip, String tahun, String keterangan) throws HibernateException {
        List<ItPayrollCutiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollCutiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("keterangan", keterangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
    public List<ItPayrollCutiEntity> cekTahunCutiPanjang(String nip, String tahunCutiPanjang, String keterangan) throws HibernateException {
        List<ItPayrollCutiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollCutiEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahunCutiPanjang", tahunCutiPanjang))
                .add(Restrictions.eq("keterangan", keterangan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
