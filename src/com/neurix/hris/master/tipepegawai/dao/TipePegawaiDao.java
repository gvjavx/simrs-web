package com.neurix.hris.master.tipepegawai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.tipelibur.model.ImHrisTipeLibur;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawai;
import com.neurix.hris.master.tipepegawai.model.ImHrisTipePegawaiHistory;
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
public class TipePegawaiDao extends GenericDao<ImHrisTipePegawai, String> {
    @Override
    protected Class<ImHrisTipePegawai> getEntityClass() {
        return null;
    }

    @Override
    public List<ImHrisTipePegawai> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipePegawai.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_pegawai_id")!=null) {
                criteria.add(Restrictions.eq("tipePegawaiId", (String) mapCriteria.get("tipe_pegawai_id")));
            }
            if (mapCriteria.get("tipe_pegawai_name")!=null) {
                criteria.add(Restrictions.ilike("tipePegawaiName", "%" + (String)mapCriteria.get("tipe_pegawai_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("tipePegawaiId"));

        List<ImHrisTipePegawai> results = criteria.list();

        return results;
    }

    public List<ImHrisTipePegawai> getTipePegawaiByUnit(String unit) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipePegawai.class);
        criteria.add(Restrictions.eq("flag","Y"));
        criteria.add(Restrictions.eq("branchId",unit));
        criteria.addOrder(Order.asc("tipePegawaiId"));
        List<ImHrisTipePegawai> results = criteria.list();

        return results;
    }
    public List<ImHrisTipePegawai> getAllTipePegawai() {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisTipePegawai.class);
        criteria.add(Restrictions.eq("flag","Y"));
        criteria.addOrder(Order.asc("tipePegawaiId"));
        List<ImHrisTipePegawai> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getTipePegawaiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_pegawai')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }

    // Generate surrogate id from postgre
    public String getTipePegawaiHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_pegawai_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        return String.valueOf(iter.next().longValue());
    }


    public void addAndSaveHistory(ImHrisTipePegawaiHistory entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
