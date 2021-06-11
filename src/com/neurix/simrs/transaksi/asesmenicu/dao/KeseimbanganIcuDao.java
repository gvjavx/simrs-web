package com.neurix.simrs.transaksi.asesmenicu.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenicu.model.ItSimrsKeseimbanganIcuEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KeseimbanganIcuDao extends GenericDao<ItSimrsKeseimbanganIcuEntity, String> {

    @Override
    protected Class<ItSimrsKeseimbanganIcuEntity> getEntityClass() {
        return ItSimrsKeseimbanganIcuEntity.class;
    }

    @Override
    public List<ItSimrsKeseimbanganIcuEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsKeseimbanganIcuEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_keseimbangan_icu")!=null) {
                criteria.add(Restrictions.eq("idKeseimbanganIcu", (String) mapCriteria.get("id_keseimbangan_icu")));
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
        criteria.addOrder(Order.asc("idKeseimbanganIcu"));

        List<ItSimrsKeseimbanganIcuEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_keseimbangan_icu')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "KCU"+sId;
    }
}