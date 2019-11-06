
package com.neurix.hris.master.belajar.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.belajar.model.ImBelajarEntity;

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
public class BelajarDao extends GenericDao<ImBelajarEntity, String> {

    @Override
    protected Class<ImBelajarEntity> getEntityClass() {
        return ImBelajarEntity.class;
    }

    @Override
    public List<ImBelajarEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBelajarEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("belajar_id")!=null) {
                criteria.add(Restrictions.eq("belajarId", (String) mapCriteria.get("belajar_id")));
            }
            if (mapCriteria.get("belajar_name")!=null) {
                criteria.add(Restrictions.ilike("belajarName", "%" + (String)mapCriteria.get("belajar_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("belajarId"));

        List<ImBelajarEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBelajarId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_belajar')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "B"+sId;
    }


    public List<ImBelajarEntity> getListBelajar(String term) throws HibernateException {

        List<ImBelajarEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBelajarEntity.class)
                .add(Restrictions.ilike("belajarName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("belajarId"))
                .list();

        return results;
    }


}
