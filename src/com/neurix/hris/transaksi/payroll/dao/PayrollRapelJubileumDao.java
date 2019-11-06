
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelJubileumEntity;
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
public class PayrollRapelJubileumDao extends GenericDao<ItPayrollRapelJubileumEntity, String> {

    @Override
    protected Class<ItPayrollRapelJubileumEntity> getEntityClass() {
        return ItPayrollRapelJubileumEntity.class;
    }

    @Override
    public List<ItPayrollRapelJubileumEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextRapelJubileumDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_rapel_jubileum')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "RJ" + sId;
    }

    public List<ItPayrollRapelJubileumEntity> getRapelJubileum(String rapelId) throws HibernateException {
        List<ItPayrollRapelJubileumEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelJubileumEntity.class)
                .add(Restrictions.eq("payrollRapelId", rapelId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
