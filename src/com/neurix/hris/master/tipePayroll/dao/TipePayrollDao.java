package com.neurix.hris.master.tipePayroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.statusAbsensi.model.ImHrisStatusAbsensiHistoryEntity;
import com.neurix.hris.master.tipePayroll.model.ImHrisTipePayrollEntity;
import com.neurix.hris.master.tipePayroll.model.ImHrisTipePayrollHistoryEntity;
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
public class TipePayrollDao extends GenericDao<ImHrisTipePayrollEntity, String> {

    @Override
    protected Class<ImHrisTipePayrollEntity> getEntityClass() {
        return ImHrisTipePayrollEntity.class;
    }

    @Override
    public List<ImHrisTipePayrollEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipePayrollEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_payroll_id")!=null) {
                criteria.add(Restrictions.eq("tipePayrollId", (String) mapCriteria.get("tipe_payroll_id")));
            }
            if (mapCriteria.get("tipe_payroll_name")!=null) {
                criteria.add(Restrictions.ilike("tipePayrollName", "%" + (String)mapCriteria.get("tipe_payroll_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tipePayrollId"));
        List<ImHrisTipePayrollEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipePayrollId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_payroll')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "TP"+sId;
    }

    public String getNextTipePayrollHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_payroll_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HT"+sId;
    }

    public void addAndSaveHistory(ImHrisTipePayrollHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisTipePayrollEntity> checkData(String tipePayrollName) throws HibernateException {

        List<ImHrisTipePayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipePayrollEntity.class)
                .add(Restrictions.eq("tipePayrollName", tipePayrollName))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tipePayrollId"))
                .list();

        return results;
    }
}
