package com.neurix.akuntansi.transaksi.kas.dao;

import com.neurix.akuntansi.transaksi.kas.model.ItAkunKasDetailEntity;
import com.neurix.common.dao.GenericDao;
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
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class KasDetailDao extends GenericDao<ItAkunKasDetailEntity, String> {

    @Override
    protected Class<ItAkunKasDetailEntity> getEntityClass() {
        return ItAkunKasDetailEntity.class;
    }

    @Override
    public List<ItAkunKasDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunKasDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kasDetail_id")!=null) {
                criteria.add(Restrictions.eq("kasDetailId", (String) mapCriteria.get("kasDetail_id")));
            }
            if (mapCriteria.get("kasDetail_name")!=null) {
                criteria.add(Restrictions.ilike("kasDetailName", "%" + (String)mapCriteria.get("kasDetail_name") + "%"));
            }
            if (mapCriteria.get("kas_id")!=null) {
                criteria.add(Restrictions.ilike("kasId", mapCriteria.get("kas_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("kasDetailId"));

        List<ItAkunKasDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKasDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());
        return "PUPD"+sId;
    }

    public List<ItAkunKasDetailEntity> getListPembayaranUtangPiutangDetailByTipeJurnalId(String id) throws HibernateException {

        List<ItAkunKasDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunKasDetailEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kasDetailId"))
                .list();
        return results;
    }
    public List<ItAkunKasDetailEntity> getByAkunKasId(String id) throws HibernateException {
        List<ItAkunKasDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunKasDetailEntity.class)
                .add(Restrictions.eq("kasId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kasDetailId"))
                .list();
        return results;
    }
}
