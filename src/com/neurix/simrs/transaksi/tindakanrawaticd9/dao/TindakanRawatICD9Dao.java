package com.neurix.simrs.transaksi.tindakanrawaticd9.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.ItSimrsTindakanRawatICD9Entity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TindakanRawatICD9Dao extends GenericDao<ItSimrsTindakanRawatICD9Entity, String> {
    @Override
    protected Class<ItSimrsTindakanRawatICD9Entity> getEntityClass() {
        return ItSimrsTindakanRawatICD9Entity.class;
    }

    @Override
    public List<ItSimrsTindakanRawatICD9Entity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTindakanRawatICD9Entity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_tindakan_rawat_icd9")!=null) {
                criteria.add(Restrictions.eq("idTindakanRawatIcd9", (String) mapCriteria.get("id_tindakan_rawat_icd9")));
            }
            if (mapCriteria.get("nama_icd9")!=null) {
                criteria.add(Restrictions.ilike("namaTindakan", "%" + (String)mapCriteria.get("nama_icd9") + "%"));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_icd9")!=null) {
                criteria.add(Restrictions.eq("idIcd9", (String) mapCriteria.get("id_icd9")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        criteria.addOrder(Order.desc("idTindakanRawatIcd9"));
        List<ItSimrsTindakanRawatICD9Entity> results = criteria.list();

        return results;
    }

    public String getNextTindakanRawatId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan_rawat_icd9')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}