
package com.neurix.hris.master.profesi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.profesi.model.ImProfesiEntity;
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
public class ProfesiDao extends GenericDao<ImProfesiEntity, String> {

    @Override
    protected Class<ImProfesiEntity> getEntityClass() {
        return ImProfesiEntity.class;
    }

    @Override
    public List<ImProfesiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImProfesiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("profesi_id")!=null) {
                criteria.add(Restrictions.eq("profesiId", (String) mapCriteria.get("profesi_id")));
            }
            if (mapCriteria.get("profesi_name")!=null) {
                criteria.add(Restrictions.ilike("profesiName", "%" + (String)mapCriteria.get("profesi_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("profesiId"));

        List<ImProfesiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextProfesiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_profesi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "PR"+sId;
    }

    public List<ImProfesiEntity> getListProfesi(String term) throws HibernateException {

        List<ImProfesiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImProfesiEntity.class)
                .add(Restrictions.ilike("profesiName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("profesiId"))
                .list();

        return results;
    }


}
