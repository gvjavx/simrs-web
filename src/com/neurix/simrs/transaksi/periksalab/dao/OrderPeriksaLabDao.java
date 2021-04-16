package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsOrderPeriksaLabEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.*;

public class OrderPeriksaLabDao extends GenericDao<ItSimrsOrderPeriksaLabEntity, String> {
    @Override
    protected Class<ItSimrsOrderPeriksaLabEntity> getEntityClass() {
        return ItSimrsOrderPeriksaLabEntity.class;
    }

    @Override
    public List<ItSimrsOrderPeriksaLabEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsOrderPeriksaLabEntity.class);
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_order_periksa")!= null) {
                criteria.add(Restrictions.eq("idOrderPeriksa", (String) mapCriteria.get("id_order_periksa")));
            }
            if (mapCriteria.get("id_detail_checkup")!= null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_lab")!= null) {
                criteria.add(Restrictions.eq("idPemeriksaan", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("id_lab_detail")!= null) {
                criteria.add(Restrictions.eq("idLabDetail", (String) mapCriteria.get("id_lab_detail")));
            }
            if (mapCriteria.get("is_pemeriksaan")!= null) {
                criteria.add(Restrictions.eq("isPemeriksaan", (String) mapCriteria.get("is_pemeriksaan")));
            }
            if (mapCriteria.get("keterangan")!= null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idDetailCheckup"));
        criteria.addOrder(Order.asc("idPemeriksaan"));
        criteria.addOrder(Order.asc("idLabDetail"));
        List<ItSimrsOrderPeriksaLabEntity> results = criteria.list();
        return results;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_order_periksa_lab')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}