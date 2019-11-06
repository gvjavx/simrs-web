package com.neurix.authorization.role.dao;


import com.neurix.authorization.role.model.ImRoles;
import com.neurix.authorization.role.model.ImRolesHistory;
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
 * Date: 14/01/13
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class RoleDao extends GenericDao<ImRoles,Long> {

    @Override
    protected Class getEntityClass() {
        return ImRoles.class;
    }

    @Override
    public List<ImRoles> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImRoles.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("role_id")!=null) {
                criteria.add(Restrictions.ilike("roleId", (Long) mapCriteria.get("role_id")));
            }
            if (mapCriteria.get("role_name")!=null) {
                criteria.add(Restrictions.ilike("roleName", "%" + (String)mapCriteria.get("role_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("roleId"));

        List<ImRoles> results = criteria.list();

        return results;
    }

    public List<ImRoles> getListRole(String term) throws HibernateException {

        List<ImRoles> results = this.sessionFactory.getCurrentSession().createCriteria(ImRoles.class)
                .add(Restrictions.ilike("roleName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("roleId"))
                .list();

        return results;
    }


    public long getNextRole() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_roles')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next().longValue();
    }

    public void addAndSaveHistory(ImRolesHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }
}