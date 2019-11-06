package com.neurix.authorization.company.dao;

import com.neurix.authorization.company.model.ImAreas;
import com.neurix.authorization.company.model.ImAreasHistory;
import com.neurix.authorization.company.model.ImAreasPK;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class AreaDao extends GenericDao<ImAreas,ImAreasPK> {

    @Override
    protected Class getEntityClass() {
        return ImAreas.class;
    }

    @Override
    public List<ImAreas> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAreas.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("area_id")!=null) {
                criteria.add(Restrictions.ilike("primaryKey.id", (String) mapCriteria.get("area_id")));
            }
            if (mapCriteria.get("area_name")!=null) {
                criteria.add(Restrictions.ilike("areaName", "%" + (String)mapCriteria.get("area_name")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.id"));

        List<ImAreas> results = criteria.list();

        return results;
    }

    public List<ImAreas> getListArea(String term) throws HibernateException {

        List<ImAreas> results = this.sessionFactory.getCurrentSession().createCriteria(ImAreas.class)
                .add(Restrictions.ilike("areaName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("primaryKey.id"))
                .list();

        return results;
    }


    public long getNextArea() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_area')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();
    }

    public void addAndSaveHistory(ImAreasHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
}