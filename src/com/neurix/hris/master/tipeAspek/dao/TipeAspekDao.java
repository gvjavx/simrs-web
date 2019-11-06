
package com.neurix.hris.master.tipeAspek.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.tipeAspek.model.ImTipeAspekEntity;
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
public class TipeAspekDao extends GenericDao<ImTipeAspekEntity, String> {

    @Override
    protected Class<ImTipeAspekEntity> getEntityClass() {
        return ImTipeAspekEntity.class;
    }

    @Override
    public List<ImTipeAspekEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTipeAspekEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_aspek_id")!=null) {
                criteria.add(Restrictions.eq("tipeAspekId", (String) mapCriteria.get("tipe_aspek_id")));
            }
            if (mapCriteria.get("tipe_aspek_name")!=null) {
                criteria.add(Restrictions.ilike("tipeAspekName", "%" + (String)mapCriteria.get("tipe_aspek_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tipeAspekId"));

        List<ImTipeAspekEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipeAspekId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipeAspek')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "B"+sId;
    }


    public List<ImTipeAspekEntity> getListTipeAspek(String term) throws HibernateException {

        List<ImTipeAspekEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImTipeAspekEntity.class)
                .add(Restrictions.ilike("tipeAspekName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tipeAspekId"))
                .list();

        return results;
    }


}
