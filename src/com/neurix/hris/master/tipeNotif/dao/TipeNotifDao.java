
package com.neurix.hris.master.tipeNotif.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.tipeNotif.model.ImTipeNotifEntity;

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
public class TipeNotifDao extends GenericDao<ImTipeNotifEntity, String> {

    @Override
    protected Class<ImTipeNotifEntity> getEntityClass() {
        return ImTipeNotifEntity.class;
    }

    @Override
    public List<ImTipeNotifEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTipeNotifEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipeNotif_id")!=null) {
                criteria.add(Restrictions.eq("tipeNotifId", (String) mapCriteria.get("tipeNotif_id")));
            }
            if (mapCriteria.get("tipeNotif_name")!=null) {
                criteria.add(Restrictions.ilike("tipeNotifName", "%" + (String)mapCriteria.get("tipeNotif_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tipeNotifId"));

        List<ImTipeNotifEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipeNotifId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_notif')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "B"+sId;
    }


    public List<ImTipeNotifEntity> getListTipeNotif(String term) throws HibernateException {

        List<ImTipeNotifEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImTipeNotifEntity.class)
                .add(Restrictions.ilike("tipeNotifName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tipeNotifId"))
                .list();

        return results;
    }


}
