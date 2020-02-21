package com.neurix.akuntansi.transaksi.jurnal.dao;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
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
public class JurnalDetailDao extends GenericDao<ItJurnalDetailEntity, String> {

    @Override
    protected Class<ItJurnalDetailEntity> getEntityClass() {
        return ItJurnalDetailEntity.class;
    }

    @Override
    public List<ItJurnalDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jurnalDetail_id")!=null) {
                criteria.add(Restrictions.eq("jurnalDetailId", (String) mapCriteria.get("jurnalDetail_id")));
            }
            if (mapCriteria.get("jurnalDetail_name")!=null) {
                criteria.add(Restrictions.ilike("jurnalDetailName", "%" + (String)mapCriteria.get("jurnalDetail_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("jurnalDetailId"));

        List<ItJurnalDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJurnalDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jurnal_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());
        return "JD"+sId;
    }

    public List<ItJurnalDetailEntity> getListJurnalDetailByTipeJurnalId(String id) throws HibernateException {

        List<ItJurnalDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jurnalDetailId"))
                .list();
        return results;
    }
}
