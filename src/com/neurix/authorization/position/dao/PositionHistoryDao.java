package com.neurix.authorization.position.dao;

import com.neurix.authorization.position.model.ImPosition;
import com.neurix.authorization.position.model.ImPositionHistory;
import com.neurix.authorization.position.model.Position;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 20/01/13
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public class PositionHistoryDao extends GenericDao<ImPositionHistory,String> {

    @Override
    protected Class getEntityClass() {
        return ImPositionHistory.class;
    }

    @Override
    public List<ImPositionHistory> getByCriteria(Map mapCriteria) throws HibernateException {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPosition.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("kelompok_id")!=null) {
                criteria.add(Restrictions.eq("kelompokId", (String) mapCriteria.get("kelompok_id")));
            }
            if (mapCriteria.get("bagian_id")!=null) {
                criteria.add(Restrictions.eq("bagianId", (String) mapCriteria.get("bagian_id")));
            }
            if (mapCriteria.get("department_id")!=null) {
                criteria.add(Restrictions.eq("departmentId", (String) mapCriteria.get("department_id")));
            }
            if (mapCriteria.get("position_name")!=null) {
                criteria.add(Restrictions.ilike("positionName", "%" + (String)mapCriteria.get("position_name") + "%" ));
            }
            if (mapCriteria.get("kodering")!=null) {
                criteria.add(Restrictions.eq("kodering", (String) mapCriteria.get("kodering")));
            }
            if (mapCriteria.get("kategori")!=null) {
                criteria.add(Restrictions.eq("kategori", (String) mapCriteria.get("kategori")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        criteria.addOrder(Order.desc("positionId"));
//        criteria.addOrder(Order.asc("departmentId"));
//        criteria.addOrder(Order.asc("bagianId"));
//        criteria.addOrder(Order.asc("kelompokId"));

        List<ImPositionHistory> results = criteria.list();

        return results;
    }

    public String getNextPositionHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_position_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return iter.next() + "";
    }

}