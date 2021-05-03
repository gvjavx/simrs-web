package com.neurix.authorization.user.dao;

import com.neurix.authorization.user.model.ItBusinessObjectLog;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Ferdi on 16/02/2015.
 */
public class ErrorLogDao extends GenericDao<ItBusinessObjectLog,Long> {
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return ItBusinessObjectLog.class;
    }

    @Override
    public List<ItBusinessObjectLog> getByCriteria(Map mapCriteria) throws HibernateException {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItBusinessObjectLog.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("error_id")!=null) {
                criteria.add(Restrictions.eq("id", (Long) mapCriteria.get("error_id")));
            }
            if (mapCriteria.get("module_method")!=null) {
                criteria.add(Restrictions.ilike("moduleMethod", "%" + (String)mapCriteria.get("module_method") + "%"));
            }
            if (mapCriteria.get("message")!=null) {
                criteria.add(Restrictions.ilike("message", "%" + (String) mapCriteria.get("message") + "%"));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.ilike("branchId", "%" + (String) mapCriteria.get("branch_id") + "%"));
            }
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.ilike("userId", "%" + (String) mapCriteria.get("user_id") + "%"));
            }

            if (mapCriteria.get("error_date_from")!=null && mapCriteria.get("error_date_to")!=null) {
                criteria.add(Restrictions.between("errorTimestamp", (Timestamp) mapCriteria.get("error_date_from"), (Timestamp) mapCriteria.get("error_date_to")));
            }
        }

        criteria.addOrder(Order.desc("id"));

        List<ItBusinessObjectLog> results = criteria.list();

        return results;
    }

}
