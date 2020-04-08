package com.neurix.akuntansi.master.tipeRekening.dao;

import com.neurix.akuntansi.master.tipeRekening.model.ImTipeRekeningEntity;
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
public class TipeRekeningDao extends GenericDao<ImTipeRekeningEntity, String> {

    @Override
    protected Class<ImTipeRekeningEntity> getEntityClass() {
        return ImTipeRekeningEntity.class;
    }

    @Override
    public List<ImTipeRekeningEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTipeRekeningEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_rekening_id")!=null) {
                criteria.add(Restrictions.eq("tipeRekeningId", (String) mapCriteria.get("tipe_rekening_id")));
            }
            if (mapCriteria.get("tipe_rekening_name")!=null) {
                criteria.add(Restrictions.ilike("tipeRekeningName", "%" + (String)mapCriteria.get("tipe_rekening_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tipeRekeningId"));
        List<ImTipeRekeningEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipeRekeningId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_rekening')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return sId;
    }
}
