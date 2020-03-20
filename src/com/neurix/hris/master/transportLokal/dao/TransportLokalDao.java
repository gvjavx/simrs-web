package com.neurix.hris.master.transportLokal.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.transportLokal.model.ImTransportLokalEntity;
import com.neurix.hris.master.transportLokal.model.ImTransportLokalHistoryEntity;
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
public class TransportLokalDao extends GenericDao<ImTransportLokalEntity, String> {

    @Override
    protected Class<ImTransportLokalEntity> getEntityClass() {
        return ImTransportLokalEntity.class;
    }

    @Override
    public List<ImTransportLokalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTransportLokalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("transport_lokal_id")!=null) {
                criteria.add(Restrictions.eq("transportLokalId", (String) mapCriteria.get("transport_lokal_id")));
            }

            if (mapCriteria.get("transport_lokal_name")!=null) {
                criteria.add(Restrictions.ilike("transportLokalName", "%" + (String)mapCriteria.get("transport_lokal_name") + "%"));
            }

            if (mapCriteria.get("transport_lokal_ke")!=null) {
                criteria.add(Restrictions.ilike("transportLokalKe", "%" + (String)mapCriteria.get("transport_lokal_ke") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("transportLokalId"));

        List<ImTransportLokalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTransportLokalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transport_lokal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());
        return "TL"+sId;
    }

    public String getNextTransportLokalHistooryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_transport_lokal_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());
        return "HTR"+sId;
    }

    public List<ImTransportLokalEntity> getListTransportLokal(String term) throws HibernateException {

        List<ImTransportLokalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImTransportLokalEntity.class)
                .add(Restrictions.ilike("transportLokalName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("transportLokalId"))
                .list();

        return results;
    }

    public List<ImTransportLokalEntity> getListTransportLokalSppd(String id) throws HibernateException {
        List<ImTransportLokalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImTransportLokalEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("transportLokalId", id))
                .addOrder(Order.asc("transportLokalId"))
                .list();
        return results;
    }

    public List<ImTransportLokalEntity> getListTransportLokalLokasiSppd(String id) throws HibernateException {
        List<ImTransportLokalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImTransportLokalEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("transportLokalName", id))
                .addOrder(Order.asc("transportLokalId"))
                .list();
        return results;
    }

    public void addAndSaveHistory(ImTransportLokalHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
