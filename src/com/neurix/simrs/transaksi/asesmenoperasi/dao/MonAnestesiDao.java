package com.neurix.simrs.transaksi.asesmenoperasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsMonitoringAnestesiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonAnestesiDao extends GenericDao<ItSimrsMonitoringAnestesiEntity, String> {

    @Override
    protected Class<ItSimrsMonitoringAnestesiEntity> getEntityClass() {
        return ItSimrsMonitoringAnestesiEntity.class;
    }

    @Override
    public List<ItSimrsMonitoringAnestesiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonitoringAnestesiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_monitoring_anestesi")!=null) {
                criteria.add(Restrictions.eq("idMonitoringAnestesi", (String) mapCriteria.get("id_monitoring_anestesi")));
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

            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }

            if(mapCriteria.get("is_desc") != null){
                criteria.addOrder(Order.desc("createdDate"));
            }else{
                criteria.addOrder(Order.asc("idMonitoringAnestesi"));
            }
        }
        List<ItSimrsMonitoringAnestesiEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_monitoring_anestesi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public Boolean cekDataAnestesi(String id, String jam, String ket){
        Boolean response = false;
        if(id != null && jam != null && ket != null){
            String SQL = "  SELECT \n" +
                    "id_monitoring_anestesi\n" +
                    "FROM it_simrs_monitoring_anestesi\n" +
                    "WHERE id_detail_checkup = :id\n" +
                    "AND waktu = :jam\n" +
                    "AND flag = 'Y' \n"+
                    "AND keterangan = :ket";

            List<Object[]> result = new ArrayList<>();

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("jam", jam)
                    .setParameter("ket", ket)
                    .list();
            if(result.size() > 0){
                response = true;
            }

        }

        return response;
    }
}