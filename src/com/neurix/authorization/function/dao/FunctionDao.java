package com.neurix.authorization.function.dao;

import com.neurix.authorization.function.model.ImFunctions;
import com.neurix.authorization.function.model.ImFunctionsHistory;
import com.neurix.common.dao.GenericDao;
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
 * User: Thunderbird
 * Date: 19/01/13
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 */
public class FunctionDao extends GenericDao<ImFunctions,Long> {
    @Override
    protected Class getEntityClass() {
        return ImFunctions.class;
    }

    @Override
    public List<ImFunctions> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("func_id")!=null) {
                criteria.add(Restrictions.eq("funcId", (Long)mapCriteria.get("func_id")));
            }
            if (mapCriteria.get("func_name")!=null) {
                criteria.add(Restrictions.ilike("funcName", "%" + (String)mapCriteria.get("func_name") + "%"));
            }
            if (mapCriteria.get("url")!=null) {
                criteria.add(Restrictions.ilike("url", "%" + (String)mapCriteria.get("url") + "%"));
            }
            if (mapCriteria.get("parent")!=null) {
                criteria.add(Restrictions.eq("parent", (Long)mapCriteria.get("parent")));
            }
            if (mapCriteria.get("func_level")!=null) {
                criteria.add(Restrictions.eq("funcLevel", (Long)mapCriteria.get("func_level")));
            }
            if (mapCriteria.get("menu")!=null) {
                criteria.add(Restrictions.eq("menu", (Long)mapCriteria.get("menu")));
            }
        }

        criteria.add(Restrictions.ilike("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("funcId"));

        List<ImFunctions> results = criteria.list();

        return results;
    }

    public boolean isExistFunction(long funcId, String activeFlag) throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.eq("funcId", funcId))
                .add(Restrictions.eq("flag", activeFlag))
                .list();


        return results.size() > 0 ? true : false;
    }

    public ImFunctions getFunctionByURL(String url, String activeFlag) throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.eq("url", url))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        return results.size() > 0 ? (ImFunctions) results.get(0) : null;
    }

    public ImFunctions getParentLevel(Long parentId, String activeFlag) throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.eq("menu", parentId))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        return results.size() > 0 ? (ImFunctions) results.get(0) : null;
    }

    public List<ImFunctions> getListParent() throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.isNotNull("menu"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("funcId"))
                .list();

        return results;
    }

    public List<ImFunctions> getListParent(String term) throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.isNotNull("menu"))
                .add(Restrictions.ilike("funcName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("funcId"))
                .list();

        return results;
    }

    public List<ImFunctions> getListFunction(String term) throws HibernateException {

        List<ImFunctions> results = this.sessionFactory.getCurrentSession().createCriteria(ImFunctions.class)
                .add(Restrictions.ilike("funcName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("funcId"))
                .list();

        return results;
    }

    public long getNextMenu() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_menu')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();
    }

    public long getNextFunction() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_functions')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();
    }

    public void addAndSaveHistory(ImFunctionsHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }


}
