package com.neurix.hris.master.statusMutasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.statusMutasi.model.ImHrisStatusMutasiEntity;
import com.neurix.hris.master.statusMutasi.model.ImHrisStatusMutasiHistoryEntity;
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
public class StatusMutasiDao extends GenericDao<ImHrisStatusMutasiEntity, String> {

    @Override
    protected Class<ImHrisStatusMutasiEntity> getEntityClass() {
        return ImHrisStatusMutasiEntity.class;
    }

    @Override
    public List<ImHrisStatusMutasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisStatusMutasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("status_mutasi_id")!=null) {
                criteria.add(Restrictions.eq("statusMutasiId", (String) mapCriteria.get("status_mutasi_id")));
            }
            if (mapCriteria.get("status_mutasi_name")!=null) {
                criteria.add(Restrictions.ilike("statusMutasiName", "%" + (String)mapCriteria.get("status_mutasi_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("createdDate"));
        List<ImHrisStatusMutasiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStatusMutasiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_status_mutasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "SM"+sId;
    }

    public String getNextStatusMutasiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_status_mutasi_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HS"+sId;
    }

    public void addAndSaveHistory(ImHrisStatusMutasiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisStatusMutasiEntity> checkData(String statusMutasiName) throws HibernateException {

        List<ImHrisStatusMutasiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisStatusMutasiEntity.class)
                .add(Restrictions.eq("statusMutasiName", statusMutasiName))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("statusMutasiId"))
                .list();

        return results;
    }
}
