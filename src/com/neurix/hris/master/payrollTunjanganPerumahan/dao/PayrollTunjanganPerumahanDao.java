package com.neurix.hris.master.payrollTunjanganPerumahan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollTunjanganPerumahan.model.ImPayrollTunjanganPerumahanEntity;
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
public class PayrollTunjanganPerumahanDao extends GenericDao<ImPayrollTunjanganPerumahanEntity, String> {


    @Override
    protected Class<ImPayrollTunjanganPerumahanEntity> getEntityClass() {
        return ImPayrollTunjanganPerumahanEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganPerumahanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganPerumahanEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("tunj_rumah_id") != null) {
                criteria.add(Restrictions.eq("tunjRumahId", (String) mapCriteria.get("tunj_rumah_id")));
            }

            if (mapCriteria.get("golongan_id") != null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            if (mapCriteria.get("kelompok_id") != null) {
                criteria.add(Restrictions.eq("kelompokId", (String) mapCriteria.get("kelompok_id")));
            }

            if (mapCriteria.get("nilai") != null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }

            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }


        }

        // Order by
        criteria.addOrder(Order.desc("tunjRumahId"));

        List<ImPayrollTunjanganPerumahanEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTunjRumah() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "TRH" + sId;
    }

    public List<ImPayrollTunjanganPerumahanEntity> getData2(String golonganId, String kelompokId) throws HibernateException {
        List<ImPayrollTunjanganPerumahanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganPerumahanEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("kelompokId", kelompokId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImPayrollTunjanganPerumahanEntity> dataPerumahan() throws HibernateException {
        List<ImPayrollTunjanganPerumahanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganPerumahanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
