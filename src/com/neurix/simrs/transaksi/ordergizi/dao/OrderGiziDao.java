package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class OrderGiziDao extends GenericDao<ItSimrsOrderGiziEntity, String> {
    @Override
    protected Class<ItSimrsOrderGiziEntity> getEntityClass() {
        return ItSimrsOrderGiziEntity.class;
    }

    @Override
    public List<ItSimrsOrderGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsOrderGiziEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_order_gizi") != null){
                criteria.add(Restrictions.eq("idOrderGizi", mapCriteria.get("id_order_gizi").toString()));
            }
            if (mapCriteria.get("id_rawat_inap") != null){
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        List<ItSimrsOrderGiziEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_order_gizi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
