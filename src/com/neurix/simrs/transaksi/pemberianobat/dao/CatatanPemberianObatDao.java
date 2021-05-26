package com.neurix.simrs.transaksi.pemberianobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.pemberianobat.model.ItSimrsCatatanPemberianObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CatatanPemberianObatDao extends GenericDao<ItSimrsCatatanPemberianObatEntity, String> {

    @Override
    protected Class<ItSimrsCatatanPemberianObatEntity> getEntityClass() {
        return ItSimrsCatatanPemberianObatEntity.class;
    }

    @Override
    public List<ItSimrsCatatanPemberianObatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsCatatanPemberianObatEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_catatan_pemberian_obat")!=null) {
                criteria.add(Restrictions.eq("idCatatanPemberianObat", (String) mapCriteria.get("id_catatan_pemberian_obat")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.eq("createdDate", (Timestamp) mapCriteria.get("created_date")));
            }
            if (mapCriteria.get("waktu")!=null) {
                criteria.add(Restrictions.eq("waktu", (String) mapCriteria.get("waktu")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("createdDate"));
        criteria.addOrder(Order.asc("waktu"));
        criteria.addOrder(Order.asc("jenis"));
        List<ItSimrsCatatanPemberianObatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_catatan_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}