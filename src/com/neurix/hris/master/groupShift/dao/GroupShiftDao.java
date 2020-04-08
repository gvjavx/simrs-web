package com.neurix.hris.master.groupShift.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.groupShift.model.ImHrisGroupShift;
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
 * Created by thinkpad on 19/03/2018.
 */
public class GroupShiftDao extends GenericDao<ImHrisGroupShift,String> {
    @Override
    protected Class<ImHrisGroupShift> getEntityClass() {
        return ImHrisGroupShift.class;
    }

    @Override
    public List<ImHrisGroupShift> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisGroupShift.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("group_shift_id")!=null) {
                criteria.add(Restrictions.eq("groupShiftId", (String) mapCriteria.get("group_shift_id")));
            }
            if (mapCriteria.get("group_id")!=null) {
                criteria.add(Restrictions.eq("groupId", (String) mapCriteria.get("group_id")));
            }
            if (mapCriteria.get("shift_id")!=null) {
                criteria.add(Restrictions.eq("shiftId", (String) mapCriteria.get("shift_id")));
            }
            if (mapCriteria.get("group_shift_name")!=null) {
                criteria.add(Restrictions.ilike("groupShiftName", "%" + (String)mapCriteria.get("group_shift_name") + "%"));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("shiftId"));
        List<ImHrisGroupShift> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGroupShifId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group_shift')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
}
