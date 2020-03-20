package com.neurix.hris.master.biayapengobatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biayapengobatan.model.ImHrisBiayaPengobatan;
import com.neurix.hris.master.biayapengobatan.model.ImHrisBiayaPengobatanHistory;
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
public class BiayaPengobatanDao extends GenericDao<ImHrisBiayaPengobatan,String> {
    @Override
    protected Class<ImHrisBiayaPengobatan> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisBiayaPengobatan> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisBiayaPengobatan.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("biaya_pengobatan_id")!=null) {
                criteria.add(Restrictions.eq("biayaPengobatanId", (String) mapCriteria.get("biaya_pengobatan_id")));
            }
            if (mapCriteria.get("biaya_pengobatan_name")!=null) {
                criteria.add(Restrictions.ilike("biayaPengobatanName", "%" + (String)mapCriteria.get("biaya_pengobatan_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("biayaPengobatanId"));

        List<ImHrisBiayaPengobatan> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBiayaPengobatanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_biaya_pengobatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

    // Generate surrogate id from postgre
    public String getNextBiayaPengobatanHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_biaya_pengobatan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }


    public void addAndSaveHistory(ImHrisBiayaPengobatanHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
