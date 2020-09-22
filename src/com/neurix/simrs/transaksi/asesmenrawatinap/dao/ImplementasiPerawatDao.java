package com.neurix.simrs.transaksi.asesmenrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenrawatinap.model.ItSimrsImplementasiPerawatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ImplementasiPerawatDao extends GenericDao<ItSimrsImplementasiPerawatEntity, String> {

    @Override
    protected Class<ItSimrsImplementasiPerawatEntity> getEntityClass() {
        return ItSimrsImplementasiPerawatEntity.class;
    }

    @Override
    public List<ItSimrsImplementasiPerawatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsImplementasiPerawatEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_implementasi_perawat") != null) {
                criteria.add(Restrictions.eq("idImplementasiPerawat", (String) mapCriteria.get("id_implementasi_perawat")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("waktu") != null) {
                criteria.add(Restrictions.eq("waktu", (String) mapCriteria.get("waktu")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idImplementasiPerawat"));

        List<ItSimrsImplementasiPerawatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_implementasi_perawat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}