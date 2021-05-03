
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelDetailEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class PayrollRapelDetailDao extends GenericDao<ItPayrollRapelDetailEntity, String> {

    @Override
    protected Class<ItPayrollRapelDetailEntity> getEntityClass() {
        return ItPayrollRapelDetailEntity.class;
    }

    @Override
    public List<ItPayrollRapelDetailEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextRapelDetailDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_rapel_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "RD" + sId;
    }

    public List<ItPayrollRapelDetailEntity> getRapelDetail(String rapelId) throws HibernateException {
        List<ItPayrollRapelDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelDetailEntity.class)
                .add(Restrictions.eq("rapelId", rapelId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollRapelDetailEntity> getRapelDetail(String bulan, String tahun, String nip) throws HibernateException {
        List<ItPayrollRapelDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelDetailEntity.class)
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }



}
