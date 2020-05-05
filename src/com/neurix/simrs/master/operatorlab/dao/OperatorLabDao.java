package com.neurix.simrs.master.operatorlab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.operatorlab.model.ImSimrsOperatorLabEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class OperatorLabDao extends GenericDao<ImSimrsOperatorLabEntity, String> {
    @Override
    protected Class<ImSimrsOperatorLabEntity> getEntityClass() {
        return ImSimrsOperatorLabEntity.class;
    }

    @Override
    public List<ImSimrsOperatorLabEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsOperatorLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_operator_lab")!=null) {
                criteria.add(Restrictions.eq("idOperatorLab", (String) mapCriteria.get("id_operator_lab")));
            }
            if (mapCriteria.get("nama_operator")!=null) {
                criteria.add(Restrictions.ilike("namaOperator", "%" + (String)mapCriteria.get("nama_operator") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idOperatorLab"));

        List<ImSimrsOperatorLabEntity> results = criteria.list();

        return results;
    }
}