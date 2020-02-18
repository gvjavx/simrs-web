package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao;

import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ImPembayaranUtangPiutangEntity;
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
public class PembayaranUtangPiutangDao extends GenericDao<ImPembayaranUtangPiutangEntity, String> {

    @Override
    protected Class<ImPembayaranUtangPiutangEntity> getEntityClass() {
        return ImPembayaranUtangPiutangEntity.class;
    }

    @Override
    public List<ImPembayaranUtangPiutangEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPembayaranUtangPiutangEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_jurnal_id")!=null) {
                criteria.add(Restrictions.eq("pembayaranUtangPiutangId", (String) mapCriteria.get("pembayaranUtangPiutang_id")));
            }
            if (mapCriteria.get("tipe_jurnal_name")!=null) {
                criteria.add(Restrictions.ilike("pembayaranUtangPiutangName", "%" + (String)mapCriteria.get("tipe_jurnal_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pembayaranUtangPiutangId"));
        List<ImPembayaranUtangPiutangEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPembayaranUtangPiutangId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_jurnal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TJ"+sId;
    }
}
