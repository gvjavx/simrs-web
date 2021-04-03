package com.neurix.simrs.transaksi.kandungan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsAsesmenKandunganEntity;
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

public class KandunganDao extends GenericDao<ItSimrsAsesmenKandunganEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenKandunganEntity> getEntityClass() {
        return ItSimrsAsesmenKandunganEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenKandunganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenKandunganEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_kandungan")!=null) {
                criteria.add(Restrictions.eq("idAsesmenKandungan", (String) mapCriteria.get("id_asesmen_kandungan")));
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
        criteria.addOrder(Order.asc("idAsesmenKandungan"));

        List<ItSimrsAsesmenKandunganEntity> results = criteria.list();
        return results;
    }

    public Boolean cekData(String idDetailCheckup, String keterangan, String waktu){
        Boolean res = false;
        String SQL = "SELECT id_asesmen_kandungan\n" +
                "FROM it_simrs_asesmen_kandungan\n" +
                "WHERE id_detail_checkup = :id\n" +
                "AND keterangan = :ket \n"+
                "AND jawaban = :waktu";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .setParameter("ket", keterangan)
                .setParameter("waktu", waktu)
                .list();
        if(result.size() > 0){
            res = true;
        }
        return res;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_kandungan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}