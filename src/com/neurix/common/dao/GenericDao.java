package com.neurix.common.dao;

import com.neurix.authorization.user.model.ItBusinessObjectLog;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 19/01/13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericDao<T, U> implements Serializable {
    protected SessionFactory sessionFactory;

    protected abstract Class<T> getEntityClass();
    public abstract List<T> getByCriteria(Map mapCriteria);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<T> getAll() throws HibernateException {
        return this.sessionFactory.getCurrentSession().createCriteria(getEntityClass()).list();
    }

    public T getById(String columnNameId, U id) throws HibernateException {

        List<T> results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq(columnNameId, (U) id))
                .list();

        T resultItem;
        if (results.size() >= 1) {
            resultItem = (T) results.get(0);
        } else {
            resultItem = null;
        }

        return resultItem;
    }

    public T getById(String columnNameId, U id, String activeFlag) throws HibernateException {
        System.out.println(getEntityClass());
        List<T> results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq(columnNameId, (U) id))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        T resultItem;
        if (results.size() >= 1) {
            resultItem = (T) results.get(0);
        } else {
            resultItem = null;
        }

        return resultItem;
    }

    public int getCount(String columnNameId, U id, String activeFlag) throws HibernateException {

        List<T> results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq(columnNameId, (U) id))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        return results.size();
    }

    public T getById(CompositeKey pk, String activeFlag) throws HibernateException {

        List<T> results = new ArrayList<T>();

        results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("primaryKey.id", pk.getId()))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        T resultItem;
        if (results.size() >= 1) {
            resultItem = (T) results.get(0);
        } else {
            resultItem = null;
        }

        return resultItem;
    }

    public int getCount(CompositeKey pk, String activeFlag) throws HibernateException {
        List<T> results = new ArrayList<T>();
        results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("primaryKey.id", pk.getId()))
                .add(Restrictions.ilike("flag", activeFlag))
                .list();

        return results.size();
    }

    public void addAndSave(T entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }

    public void updateAndSave(T entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    public void deleteAndSave(T entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    public long getNextBoLogging() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_bo_log')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();

    }

    public void addAndSaveBoLog(ItBusinessObjectLog entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }


}
