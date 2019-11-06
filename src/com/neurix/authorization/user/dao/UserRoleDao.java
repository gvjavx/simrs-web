package com.neurix.authorization.user.dao;

import com.neurix.authorization.user.model.ImUsersRoles;
import com.neurix.authorization.user.model.ImUsersRolesPK;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ferdi on 09/02/2015.
 */
public class UserRoleDao extends GenericDao<ImUsersRoles,ImUsersRolesPK> {
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return ImUsersRoles.class;
    }

    @Override
    public List<ImUsersRoles> getByCriteria(Map mapCriteria) throws HibernateException {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImUsersRoles.class);
        if (mapCriteria!=null) {
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.userId", (String) mapCriteria.get("user_id")));
            }
            if (mapCriteria.get("role_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.roleId", (Long) mapCriteria.get("role_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        List<ImUsersRoles> results = criteria.list();
        return results;
    }

    public ImUsersRoles getByCompositeKey(ImUsersRolesPK primaryKey, String activeFlag) {
        List<ImUsersRoles> results = new ArrayList<ImUsersRoles>();

        results = this.sessionFactory.getCurrentSession().createCriteria(getEntityClass())
                .add(Restrictions.eq("primaryKey.userId", primaryKey.getUserId()))
                .add(Restrictions.eq("primaryKey.roleId", primaryKey.getRoleId()))
                .add(Restrictions.ilike("flag", activeFlag))
                .list();

        ImUsersRoles resultItem;
        if (results.size() >= 1) {
            resultItem = (ImUsersRoles) results.get(0);
        } else {
            resultItem = null;
        }

        return resultItem;

    }
    public List<ImUsersRoles> getAdminUser() throws HibernateException {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImUsersRoles.class);
        criteria.add(Restrictions.eq("primaryKey.roleId", Long.valueOf(1)));
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImUsersRoles> results = criteria.list();
        return results;
    }
}
