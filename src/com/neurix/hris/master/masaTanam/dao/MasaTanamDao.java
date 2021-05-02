package com.neurix.hris.master.masaTanam.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.masaTanam.model.ImMasaTanamEntity;
import com.neurix.hris.master.masaTanam.model.ImMasaTanamHistoryEntity;
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
public class MasaTanamDao extends GenericDao<ImMasaTanamEntity, String> {

    @Override
    protected Class<ImMasaTanamEntity> getEntityClass() {
        return ImMasaTanamEntity.class;
    }

    @Override
    public List<ImMasaTanamEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMasaTanamEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {

            if (mapCriteria.get("masa_tanam_id")!=null) {
                criteria.add(Restrictions.eq("masaTanamId", (String) mapCriteria.get("masa_tanam_id")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.le("awalGiling",mapCriteria.get("tanggal")));
                criteria.add(Restrictions.ge("akhirGiling",mapCriteria.get("tanggal")));
            }

            if (mapCriteria.get("masaTanam_id")!=null) {
                criteria.add(Restrictions.eq("masaTanamId", (String) mapCriteria.get("masaTanam_id")));
            }
            if (mapCriteria.get("masaTanam_name")!=null) {
                criteria.add(Restrictions.ilike("masaTanamName", "%" + (String)mapCriteria.get("masaTanam_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("masaTanamId"));

        List<ImMasaTanamEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextMasaTanamId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_masaTanam')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "D"+sId;
    }

    public List<ImMasaTanamEntity> getMasaTanam(String id) throws HibernateException {
        List<ImMasaTanamEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMasaTanamEntity.class)
                .add(Restrictions.eq("masaTanamId", id))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
