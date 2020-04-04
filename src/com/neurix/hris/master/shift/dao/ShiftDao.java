package com.neurix.hris.master.shift.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.shift.model.ImHrisShiftEntity;
import com.neurix.hris.master.shift.model.ImHrisShiftHistory;
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
public class ShiftDao extends GenericDao<ImHrisShiftEntity,String> {
    @Override
    protected Class<ImHrisShiftEntity> getEntityClass() {
        return ImHrisShiftEntity.class;
    }

    @Override
    public List<ImHrisShiftEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisShiftEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("shift_id")!=null) {
                criteria.add(Restrictions.eq("shiftId", (String) mapCriteria.get("shift_id")));
            }
            if (mapCriteria.get("profesi_id")!=null) {
                criteria.add(Restrictions.eq("profesiId", (String) mapCriteria.get("profesi_id")));
            }
            if (mapCriteria.get("shift_name")!=null) {
                criteria.add(Restrictions.ilike("shiftName", "%" + (String)mapCriteria.get("shift_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("shiftId"));

        List<ImHrisShiftEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextShiftId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_shift')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    // Generate surrogate id from postgre
    public String getShift() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_shift')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    // Generate surrogate id from postgre
    public String getNextShiftHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_shift_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    public void addAndSaveHistory(ImHrisShiftHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
    public List<ImHrisShiftEntity> getJadwalShift() throws HibernateException {
        List<ImHrisShiftEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisShiftEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
