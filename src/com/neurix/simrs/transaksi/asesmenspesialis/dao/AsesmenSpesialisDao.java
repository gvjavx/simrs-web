package com.neurix.simrs.transaksi.asesmenspesialis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenspesialis.model.ItSimrsAsesmenSpesialisEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenSpesialisDao extends GenericDao<ItSimrsAsesmenSpesialisEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenSpesialisEntity> getEntityClass() {
        return ItSimrsAsesmenSpesialisEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenSpesialisEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenSpesialisEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_poli_spesialis")!=null) {
                criteria.add(Restrictions.eq("idAsesmenPoliSpesialis", (String) mapCriteria.get("id_asesmen_poli_spesialis")));
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
            if (mapCriteria.get("tipe")!=null) {
                criteria.add(Restrictions.eq("tipe", (String) mapCriteria.get("tipe")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idAsesmenPoliSpesialis"));

        List<ItSimrsAsesmenSpesialisEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_spesialis')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}