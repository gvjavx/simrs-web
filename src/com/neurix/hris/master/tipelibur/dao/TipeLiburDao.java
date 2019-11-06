package com.neurix.hris.master.tipelibur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLiburHistory;
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
public class TipeLiburDao extends GenericDao<ImHrisTipeLibur, String> {
    @Override
    protected Class<ImHrisTipeLibur> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisTipeLibur> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipeLibur.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_libur_id")!=null) {
                criteria.add(Restrictions.eq("tipeLiburId", (String) mapCriteria.get("tipe_libur_id")));
            }
            if (mapCriteria.get("tipe_libur_name")!=null) {
                criteria.add(Restrictions.ilike("tipeLiburName", "%" + (String)mapCriteria.get("tipe_libur_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tipeLiburId"));

        List<ImHrisTipeLibur> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextLiburId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_libur')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    // Generate surrogate id from postgre
    public String getNextLiburHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_libur_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }
    public void addAndSaveHistory(ImHrisTipeLiburHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
