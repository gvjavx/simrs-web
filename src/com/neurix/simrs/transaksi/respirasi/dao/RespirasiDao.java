package com.neurix.simrs.transaksi.respirasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.respirasi.model.ItSimrsRespirasiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RespirasiDao extends GenericDao<ItSimrsRespirasiEntity, String> {

    @Override
    protected Class<ItSimrsRespirasiEntity> getEntityClass() {
        return ItSimrsRespirasiEntity.class;
    }

    @Override
    public List<ItSimrsRespirasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRespirasiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_respirasi")!=null) {
                criteria.add(Restrictions.eq("idRespirasi", (String) mapCriteria.get("id_respirasi")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal", (Date) mapCriteria.get("tanggal")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("tanggal"));
        criteria.addOrder(Order.asc("waktu"));

        List<ItSimrsRespirasiEntity> results = criteria.list();
        return results;
    }

    public Boolean cekData(String id, String waktu){
        Boolean response = false;
        if (id != null && waktu != null){
            String SQL = "SELECT id_respirasi FROM it_simrs_respirasi\n" +
                    "WHERE id_detail_checkup = :id\n" +
                    "AND waktu = :wkt\n" +
                    "AND flag = 'Y'\n" +
                    "AND CAST(created_date AS date) = CURRENT_DATE";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("wkt", waktu)
                    .list();
            if(result.size() > 0){
                response = true;
            }
        }
        return response;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_respirasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}