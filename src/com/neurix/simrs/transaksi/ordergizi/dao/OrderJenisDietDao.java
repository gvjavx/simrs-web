package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsDetailJenisDietEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrderJenisDietDao extends GenericDao<ItSimrsDetailJenisDietEntity, String> {
    @Override
    protected Class<ItSimrsDetailJenisDietEntity> getEntityClass() {
        return ItSimrsDetailJenisDietEntity.class;
    }

    @Override
    public List<ItSimrsDetailJenisDietEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDetailJenisDietEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_jenis_diet") != null) {
                criteria.add(Restrictions.eq("idDetailJenisDiet", mapCriteria.get("id_detail_jenis_diet").toString()));
            }
            if (mapCriteria.get("id_order_gizi") != null) {
                criteria.add(Restrictions.eq("idOrderGizi", mapCriteria.get("id_order_gizi").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        criteria.addOrder(Order.asc("idDetailJenisDiet"));
        List<ItSimrsDetailJenisDietEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_jenis_diet')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
