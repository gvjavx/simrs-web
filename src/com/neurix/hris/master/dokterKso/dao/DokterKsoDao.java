package com.neurix.hris.master.dokterKso.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.dokterKso.model.ImSimrsDokterKso;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class DokterKsoDao extends GenericDao<ImSimrsDokterKso, String> {

    @Override
    protected Class<ImSimrsDokterKso> getEntityClass() {
        return ImSimrsDokterKso.class;
    }

    @Override
    public List<ImSimrsDokterKso> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterKso.class);

        if (mapCriteria != null){
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }

        criteria.addOrder(Order.asc("branchId"));
        List<ImSimrsDokterKso> results = criteria.list();
        return results;
    }
}