package com.neurix.hris.master.tunjangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjangan;
import com.neurix.hris.master.tunjangan.model.ImHrisTunjanganHistory;
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
public class TunjanganDao extends GenericDao<ImHrisTunjangan, String> {
    @Override
    protected Class<ImHrisTunjangan> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisTunjangan> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTunjangan.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tunjangan_id")!=null) {
                criteria.add(Restrictions.eq("tunjanganId", (String) mapCriteria.get("tunjangan_id")));
            }
            if (mapCriteria.get("tunjangan_name")!=null) {
                criteria.add(Restrictions.ilike("tunjanganName", "%" + (String)mapCriteria.get("tunjangan_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tunjanganId"));

        List<ImHrisTunjangan> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getTunjanganId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tunjangan')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    // Generate surrogate id from postgre
    public String getTunjanganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tunjangan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    public void addAndSaveHistory(ImHrisTunjanganHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

}
