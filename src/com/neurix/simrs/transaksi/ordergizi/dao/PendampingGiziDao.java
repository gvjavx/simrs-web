package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsPendampingGiziEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class PendampingGiziDao extends GenericDao<ItSimrsPendampingGiziEntity, String> {
    @Override
    protected Class<ItSimrsPendampingGiziEntity> getEntityClass() {
        return ItSimrsPendampingGiziEntity.class;
    }

    @Override
    public List<ItSimrsPendampingGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPendampingGiziEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_pendamping_gizi") != null) {
                criteria.add(Restrictions.eq("idPendampingGizi", mapCriteria.get("id_pendamping_gizi").toString()));
            }
            if (mapCriteria.get("id_order_gizi") != null) {
                criteria.add(Restrictions.eq("idOrderGizi", mapCriteria.get("id_order_gizi").toString()));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.eq("nama", mapCriteria.get("nama").toString()));
            }
            if (mapCriteria.get("tipe") != null) {
                criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        criteria.addOrder(Order.asc("idPendampingGizi"));
        List<ItSimrsPendampingGiziEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_it_pendampinng_gizi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "IPZ"+sId;
    }
}
