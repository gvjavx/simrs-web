package com.neurix.simrs.transaksi.asesmenoperasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmenoperasi.model.ItSimrsAsesmenOperasiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenOperasiDao extends GenericDao<ItSimrsAsesmenOperasiEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenOperasiEntity> getEntityClass() {
        return ItSimrsAsesmenOperasiEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenOperasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenOperasiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_asesmen_operasi")!=null) {
                criteria.add(Restrictions.eq("idAsesmenOperasi", (String) mapCriteria.get("id_asesmen_operasi")));
            }
            if (mapCriteria.get("no_checkup")!=null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
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
        criteria.addOrder(Order.asc("idAsesmenOperasi"));

        List<ItSimrsAsesmenOperasiEntity> results = criteria.list();
        return results;
    }

    public String getPraInduksiDataByKey(String id, String params){
        String res = "";
        String SQL = "SELECT\n" +
                "id_detail_checkup,\n" +
                "jawaban1\n" +
                "FROM it_simrs_asesmen_operasi\n" +
                "WHERE keterangan = 'pra_induksi'\n" +
                "AND flag = 'Y'\n" +
                "AND parameter = '"+params+"'\n" +
                "AND id_detail_checkup = '"+id+"'";
        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL).list();
        if(result.size() > 0){
            Object[] objects = result.get(0);
            res = objects[1].toString();
        }
        return res;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_operasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}