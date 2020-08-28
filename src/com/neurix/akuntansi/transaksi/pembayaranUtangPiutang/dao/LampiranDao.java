package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao;

import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ItAkunLampiranEntity;
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
public class LampiranDao extends GenericDao<ItAkunLampiranEntity, String> {

    @Override
    protected Class<ItAkunLampiranEntity> getEntityClass() {
        return ItAkunLampiranEntity.class;
    }

    @Override
    public List<ItAkunLampiranEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunLampiranEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("lampiran_id")!=null) {
                criteria.add(Restrictions.eq("lampiranId", (String) mapCriteria.get("lampiran_id")));
            }
            if (mapCriteria.get("lampiran_name")!=null) {
                criteria.add(Restrictions.ilike("lampiranName", "%" + (String)mapCriteria.get("lampiran_name") + "%"));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("lampiranId"));

        List<ItAkunLampiranEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextLampiranId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lampiran')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());
        return "L"+sId;
    }
}
