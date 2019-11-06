
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelLemburEntity;
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
public class PayrollRapelLemburDao extends GenericDao<ItPayrollRapelLemburEntity, String> {

    @Override
    protected Class<ItPayrollRapelLemburEntity> getEntityClass() {
        return ItPayrollRapelLemburEntity.class;
    }

    @Override
    public List<ItPayrollRapelLemburEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextRapelLemburDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_rapel_lembur')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "RL" + sId;
    }

    public List<ItPayrollRapelLemburEntity> getRapelLembur(String rapelId) throws HibernateException {
        List<ItPayrollRapelLemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelLemburEntity.class)
                .add(Restrictions.eq("rapelId", rapelId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
