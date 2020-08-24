package com.neurix.simrs.transaksi.diagnosarawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.diagnosarawat.model.ItSimrsDiagnosaRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DiagnosaRawatDao extends GenericDao<ItSimrsDiagnosaRawatEntity, String> {

    @Override
    protected Class<ItSimrsDiagnosaRawatEntity> getEntityClass() {
        return ItSimrsDiagnosaRawatEntity.class;
    }

    @Override
    public List<ItSimrsDiagnosaRawatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDiagnosaRawatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_diagnosa_rawat")!=null) {
                criteria.add(Restrictions.eq("idDiagnosaRawat", (String) mapCriteria.get("id_diagnosa_rawat")));
            }
            if (mapCriteria.get("id_diagnosa")!=null) {
                criteria.add(Restrictions.eq("idDiagnosa", (String) mapCriteria.get("id_diagnosa")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("jenis_diagnosa")!=null) {
                criteria.add(Restrictions.eq("jenisDiagnosa", (String) mapCriteria.get("jenis_diagnosa")));
            }
            if (mapCriteria.get("tipe")!=null) {
                criteria.add(Restrictions.eq("tipe", (String) mapCriteria.get("tipe")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        if (mapCriteria.get("order_last") != null){
            criteria.addOrder(Order.desc("lastUpdate"));
        } else if (mapCriteria.get("order_created") != null){
            criteria.addOrder(Order.desc("createdDate"));
        } else {
            criteria.addOrder(Order.desc("idDiagnosa"));
        }

        List<ItSimrsDiagnosaRawatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_diagnosa_rawat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}