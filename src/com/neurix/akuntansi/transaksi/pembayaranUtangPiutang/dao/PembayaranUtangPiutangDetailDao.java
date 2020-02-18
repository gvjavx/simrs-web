package com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.dao;

import com.neurix.akuntansi.transaksi.pembayaranUtangPiutang.model.ImPembayaranUtangPiutangDetailEntity;
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
public class PembayaranUtangPiutangDetailDao extends GenericDao<ImPembayaranUtangPiutangDetailEntity, String> {

    @Override
    protected Class<ImPembayaranUtangPiutangDetailEntity> getEntityClass() {
        return ImPembayaranUtangPiutangDetailEntity.class;
    }

    @Override
    public List<ImPembayaranUtangPiutangDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPembayaranUtangPiutangDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pembayaranUtangPiutangDetail_id")!=null) {
                criteria.add(Restrictions.eq("pembayaranUtangPiutangDetailId", (String) mapCriteria.get("pembayaranUtangPiutangDetail_id")));
            }
            if (mapCriteria.get("pembayaranUtangPiutangDetail_name")!=null) {
                criteria.add(Restrictions.ilike("pembayaranUtangPiutangDetailName", "%" + (String)mapCriteria.get("pembayaranUtangPiutangDetail_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pembayaranUtangPiutangDetailId"));

        List<ImPembayaranUtangPiutangDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPembayaranUtangPiutangDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mapping_jurnal')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "IJ"+sId;
    }

    public List<ImPembayaranUtangPiutangDetailEntity> getListPembayaranUtangPiutangDetailByTipeJurnalId(String id) throws HibernateException {

        List<ImPembayaranUtangPiutangDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPembayaranUtangPiutangDetailEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pembayaranUtangPiutangDetailId"))
                .list();
        return results;
    }
}
