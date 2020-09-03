package com.neurix.simrs.transaksi.kandungan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsPartografEntity;
import com.neurix.simrs.transaksi.kandungan.model.Partograf;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PartografDao extends GenericDao<ItSimrsPartografEntity, String> {

    @Override
    protected Class<ItSimrsPartografEntity> getEntityClass() {
        return ItSimrsPartografEntity.class;
    }

    @Override
    public List<ItSimrsPartografEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPartografEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_partograf") != null) {
                criteria.add(Restrictions.eq("idPartograf", (String) mapCriteria.get("id_partograf")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idPartograf"));

        List<ItSimrsPartografEntity> results = criteria.list();
        return results;
    }

    public Boolean cekData(String idDetailCheckup, String waktu){
        Boolean res = false;
        String SQL = "SELECT id_partograf\n" +
                "FROM  it_simrs_partograf \n" +
                "WHERE id_detail_checkup = :id\n" +
                "AND waktu = :waktu\n" +
                "AND CAST(created_date AS DATE) = CURRENT_DATE\n";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .setParameter("waktu", waktu)
                .list();
        if(result.size() > 0){
            res = true;
        }
        return res;
    }

    public List<Partograf> getListByDate(String idDetailCheckup, String tanggal){
        List<Partograf> partografList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "waktu,\n" +
                "djj,\n" +
                "air_ketuban,\n" +
                "molase,\n" +
                "pembukaan,\n" +
                "kontraksi,\n" +
                "oksitosin,\n" +
                "tetes,\n" +
                "obat_cairan,\n" +
                "nadi,\n" +
                "tensi,\n" +
                "suhu,\n" +
                "rr\n" +
                "FROM it_simrs_partograf\n" +
                "WHERE id_detail_checkup = :id\n" +
                "WHERE flag = 'Y'\n" +
                "AND CAST(created_date AS DATE) = to_date(:tgl, 'dd-MM-yyyy')";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .setParameter("tgl", tanggal)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                Partograf partograf = new Partograf();
                partograf.setWaktu(obj[0] != null ? obj[0].toString() : null);
                partograf.setDjj(obj[1] != null ? obj[1].toString() : null);
                partograf.setAirKetuban(obj[2] != null ? obj[2].toString() : null);
                partograf.setMolase(obj[3] != null ? obj[3].toString() : null);
                partograf.setPembukaan(obj[4] != null ? obj[4].toString() : null);
                partograf.setKontraksi(obj[5] != null ? obj[5].toString() : null);
                partograf.setOksitosin(obj[6] != null ? obj[6].toString() : null);
                partograf.setTetes(obj[7] != null ? obj[7].toString() : null);
                partograf.setObatCairan(obj[8] != null ? obj[8].toString() : null);
                partograf.setNadi(obj[9] != null ? obj[9].toString() : null);
                partograf.setTensi(obj[10] != null ? obj[10].toString() : null);
                partograf.setSuhu(obj[11] != null ? obj[11].toString() : null);
                partograf.setRr(obj[12] != null ? obj[12].toString() : null);
                partografList.add(partograf);
            }
        }
        return partografList;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_partograf')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}