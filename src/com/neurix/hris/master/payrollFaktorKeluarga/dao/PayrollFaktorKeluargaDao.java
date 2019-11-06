package com.neurix.hris.master.payrollFaktorKeluarga.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollFaktorKeluarga.model.ImPayrollFaktorKeluargaEntity;
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
public class PayrollFaktorKeluargaDao extends GenericDao<ImPayrollFaktorKeluargaEntity, String> {
    @Override
    protected Class<ImPayrollFaktorKeluargaEntity> getEntityClass() {
        return ImPayrollFaktorKeluargaEntity.class;
    }

    @Override
    public List<ImPayrollFaktorKeluargaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollFaktorKeluargaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("faktor_id")!=null) {
                criteria.add(Restrictions.eq("faktorKeluargaId", (String) mapCriteria.get("faktor_id")));
            }
            if (mapCriteria.get("status_id")!=null) {
                criteria.add(Restrictions.eq("statusKeluarga", (String) mapCriteria.get("status_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }
        // Order by
        criteria.addOrder(Order.desc("faktorKeluargaId"));

        List<ImPayrollFaktorKeluargaEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextFaktorKeluargaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_faktor_keluarga')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "FK"+sId;
    }

    public String getNextFaktorKeluargaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_faktor_keluarga_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImPayrollFaktorKeluargaEntity> getListFaktorKeluarga(String term) throws HibernateException {

        List<ImPayrollFaktorKeluargaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollFaktorKeluargaEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("faktorKeluargaId"))
                .list();

        return results;
    }

    public List<ImPayrollFaktorKeluargaEntity> getData2(String statusKeluarga, int jumlahAnak) throws HibernateException {
        List<ImPayrollFaktorKeluargaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollFaktorKeluargaEntity.class)
                .add(Restrictions.eq("statusKeluarga", statusKeluarga))
                .add(Restrictions.eq("jumlahAnak", jumlahAnak))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
