package com.neurix.hris.master.group.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.group.model.ImHrisGroupEntity;
import com.neurix.hris.master.group.model.ImHrisGroupHistory;
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
public class GroupDao extends GenericDao<ImHrisGroupEntity,String> {
    @Override
    protected Class<ImHrisGroupEntity> getEntityClass() {
        return ImHrisGroupEntity.class;
    }

    @Override
    public List<ImHrisGroupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisGroupEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("group_id")!=null) {
                criteria.add(Restrictions.eq("groupId", (String) mapCriteria.get("group_id")));
            }
            if (mapCriteria.get("group_name")!=null) {
                criteria.add(Restrictions.ilike("groupName", "%" + (String)mapCriteria.get("group_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("groupId"));

        List<ImHrisGroupEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGroupId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    // Generate surrogate id from postgre
    public String getNextGoupHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }


    public void addAndSaveHistory(ImHrisGroupHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
