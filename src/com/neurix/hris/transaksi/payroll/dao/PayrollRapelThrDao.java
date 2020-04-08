
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollRapelThrEntity;
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
public class PayrollRapelThrDao extends GenericDao<ItPayrollRapelThrEntity, String> {

    @Override
    protected Class<ItPayrollRapelThrEntity> getEntityClass() {
        return ItPayrollRapelThrEntity.class;
    }

    @Override
    public List<ItPayrollRapelThrEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextRapelThrDao(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_rapel_thr')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "RT" + per[2] + per[3] + sId;
    }

    public List<ItPayrollRapelThrEntity> getRapelThr(String rapelId) throws HibernateException {
        List<ItPayrollRapelThrEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollRapelThrEntity.class)
                .add(Restrictions.eq("payrollRapelId", rapelId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
