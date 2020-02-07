package com.neurix.akuntansi.master.tipeJurnal.dao;

import com.neurix.akuntansi.master.tipeJurnal.model.ImTipeJurnalEntity;
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
public class TipeJurnalDao extends GenericDao<ImTipeJurnalEntity, String> {

    @Override
    protected Class<ImTipeJurnalEntity> getEntityClass() {
        return ImTipeJurnalEntity.class;
    }

    @Override
    public List<ImTipeJurnalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImTipeJurnalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_jurnal_id")!=null) {
                criteria.add(Restrictions.eq("tipeJurnalId", (String) mapCriteria.get("tipeJurnal_id")));
            }
            if (mapCriteria.get("tipe_jurnal_name")!=null) {
                criteria.add(Restrictions.ilike("tipeJurnalName", "%" + (String)mapCriteria.get("tipe_jurnal_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tipeJurnalId"));
        List<ImTipeJurnalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipeJurnalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_jurnal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TJ"+sId;
    }
}
