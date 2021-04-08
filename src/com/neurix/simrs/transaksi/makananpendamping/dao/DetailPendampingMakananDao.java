package com.neurix.simrs.transaksi.makananpendamping.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.makananpendamping.model.ItSimrsDetailPendampingMakananEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailPendampingMakananDao extends GenericDao<ItSimrsDetailPendampingMakananEntity, String> {
    @Override
    protected Class<ItSimrsDetailPendampingMakananEntity> getEntityClass() {
        return ItSimrsDetailPendampingMakananEntity.class;
    }

    @Override
    public List<ItSimrsDetailPendampingMakananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDetailPendampingMakananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_pendamping_makanan") != null) {
                criteria.add(Restrictions.eq("idDetailPendampingMakanan", mapCriteria.get("id_detail_pendamping_makanan").toString()));
            }
            if (mapCriteria.get("id_header_pendamping_makanan") != null) {
                criteria.add(Restrictions.eq("idHeaderPendampingMakanan", mapCriteria.get("id_header_pendamping_makanan").toString()));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.eq("nama", mapCriteria.get("nama").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        criteria.addOrder(Order.asc("idDetailPendampingMakanan"));
        List<ItSimrsDetailPendampingMakananEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_pendamping_makanan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "DPM"+sId;
    }
}
