
package com.neurix.authorization.role.dao;

import com.neurix.authorization.role.model.ImFuncRoles;
import com.neurix.authorization.role.model.ImFuncRolesPK;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Ferdi on 26/01/2015.
 */
public class RoleFuncDao extends GenericDao<ImFuncRoles,ImFuncRolesPK> {

    @Override
    protected Class getEntityClass() {
        return ImFuncRoles.class;
    }

    @Override
    public List<ImFuncRoles> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImFuncRoles.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("role_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.roleId", (Long) mapCriteria.get("role_id")));
            }
            if (mapCriteria.get("func_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.funcId", (Long)mapCriteria.get("func_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.roleId"));
        criteria.addOrder(Order.asc("primaryKey.funcId"));

        List<ImFuncRoles> results = criteria.list();

        return results;
    }

    public List<ImFuncRoles> getRoleFuncByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImFuncRoles.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("role_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.roleId", (Long) mapCriteria.get("role_id")));
            }
            if (mapCriteria.get("func_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.funcId", (Long)mapCriteria.get("func_id")));
            }
        }

//        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.roleId"));
        criteria.addOrder(Order.asc("primaryKey.funcId"));

        List<ImFuncRoles> results = criteria.list();

        return results;
    }

    public boolean checkIfAvailableFuncIdInRoleFunc(Long funcId, Long roleId) throws HibernateException{

        String SQL = "SELECT func_id, role_id, flag FROM im_func_roles WHERE func_id = :id AND role_id = :role LIMIT 1\n";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", BigInteger.valueOf(funcId))
                .setParameter("role", BigInteger.valueOf(roleId))
                .list();
        // return true jika size > 0 / ditemukan data;
        return results.size() > 0;
    }


}