package com.neurix.simrs.transaksi.asesmenicu.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenicu.model.ItSimrsAsesmenIcuEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenIcuDao extends GenericDao<ItSimrsAsesmenIcuEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenIcuEntity> getEntityClass() {
        return ItSimrsAsesmenIcuEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenIcuEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenIcuEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_icu")!=null) {
                criteria.add(Restrictions.eq("idAsesmenIcu", (String) mapCriteria.get("id_asesmen_icu")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.eq("createdDate", (Timestamp) mapCriteria.get("created_date")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenIcu"));

        List<ItSimrsAsesmenIcuEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_icu')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}