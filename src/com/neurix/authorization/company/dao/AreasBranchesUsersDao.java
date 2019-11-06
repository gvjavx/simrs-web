package com.neurix.authorization.company.dao;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */

import com.neurix.authorization.company.model.ImAreasBranchesUsers;
import com.neurix.authorization.company.model.ImAreasBranchesUsersPK;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AreasBranchesUsersDao extends GenericDao<ImAreasBranchesUsers,ImAreasBranchesUsersPK> {

    @Override
    protected Class getEntityClass() {
        return ImAreasBranchesUsers.class;
    }

    @Override
    public List<ImAreasBranchesUsers> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAreasBranchesUsers.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("area_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.areaId", (String) mapCriteria.get("area_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.branchId", (String)mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("primaryKey.userId", (String)mapCriteria.get("user_id")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.asc("primaryKey.areaId"));
        criteria.addOrder(Order.asc("primaryKey.branchId"));
        criteria.addOrder(Order.asc("primaryKey.userId"));

        List<ImAreasBranchesUsers> results = criteria.list();

        return results;
    }

    public ImAreasBranchesUsers getAreasBranchesUsersByUserId(String userId, String activeFlag) {

        List<ImAreasBranchesUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImAreasBranchesUsers.class)
                .add(Restrictions.eq("primaryKey.userId", userId))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        ImAreasBranchesUsers resultItem;
        if (results.size() >= 1) {
            resultItem = (ImAreasBranchesUsers) results.get(0);
        } else {
            resultItem = null;
        }

        return resultItem;
    }

    public String getAreasByBranchId(String branchId, String activeFlag) {

        List<ImAreasBranchesUsers> results = this.sessionFactory.getCurrentSession().createCriteria(ImAreasBranchesUsers.class)
                .add(Restrictions.eq("primaryKey.branchId", branchId))
                .add(Restrictions.eq("flag", activeFlag))
                .list();

        String resultItem;
        if (results.size() >= 1) {
            ImAreasBranchesUsers item = (ImAreasBranchesUsers) results.get(0);
            resultItem = item.getPrimaryKey().getAreaId();

        } else {
            resultItem = null;
        }

        return resultItem;
    }


}