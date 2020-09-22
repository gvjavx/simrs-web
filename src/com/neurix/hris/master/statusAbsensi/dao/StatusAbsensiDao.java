package com.neurix.hris.master.statusAbsensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.cuti.model.ImCutiHistoryEntity;
import com.neurix.hris.master.statusAbsensi.model.ImHrisStatusAbsensiEntity;
import com.neurix.hris.master.statusAbsensi.model.ImHrisStatusAbsensiHistoryEntity;
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
public class StatusAbsensiDao extends GenericDao<ImHrisStatusAbsensiEntity, String> {

    @Override
    protected Class<ImHrisStatusAbsensiEntity> getEntityClass() {
        return ImHrisStatusAbsensiEntity.class;
    }

    @Override
    public List<ImHrisStatusAbsensiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisStatusAbsensiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("status_absensi_id")!=null) {
                criteria.add(Restrictions.eq("statusAbsensiId", (String) mapCriteria.get("status_absensi_id")));
            }
            if (mapCriteria.get("status_absensi_name")!=null) {
                criteria.add(Restrictions.ilike("statusAbsensiName", "%" + (String)mapCriteria.get("status_absensi_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("statusAbsensiId"));
        List<ImHrisStatusAbsensiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStatusAbsensiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_status_absensi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "SA"+sId;
    }

    public String getNextStatusAbsensiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_status_absensi_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HS"+sId;
    }

    public void addAndSaveHistory(ImHrisStatusAbsensiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisStatusAbsensiEntity> checkData(String statusAbsensiName) throws HibernateException {

        List<ImHrisStatusAbsensiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisStatusAbsensiEntity.class)
                .add(Restrictions.eq("statusAbsensiName", statusAbsensiName))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("statusAbsensiId"))
                .list();

        return results;
    }
}
