package com.neurix.simrs.transaksi.tindakanrawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TindakanRawatDao extends GenericDao<ItSimrsTindakanRawatEntity, String> {
    @Override
    protected Class<ItSimrsTindakanRawatEntity> getEntityClass() {
        return ItSimrsTindakanRawatEntity.class;
    }

    @Override
    public List<ItSimrsTindakanRawatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTindakanRawatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_tindakan_rawat")!=null) {
                criteria.add(Restrictions.eq("idTindakanRawat", (String) mapCriteria.get("id_tindakan_rawat")));
            }
            if (mapCriteria.get("nama_tindakan")!=null) {
                criteria.add(Restrictions.ilike("namaTindakan", "%" + (String)mapCriteria.get("nama_tindakan") + "%"));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_tindakan")!=null) {
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_perawat")!=null) {
                criteria.add(Restrictions.eq("idPerawat", (String) mapCriteria.get("id_perawat")));
            }
            if (mapCriteria.get("tarif")!=null) {
                criteria.add(Restrictions.eq("tarif", (Long) mapCriteria.get("tarif")));
            }
            if (mapCriteria.get("qty")!=null) {
                criteria.add(Restrictions.eq("qty", (Long) mapCriteria.get("qty")));
            }
            if (mapCriteria.get("tarif_total")!=null) {
                criteria.add(Restrictions.eq("tarifTotal", (Long) mapCriteria.get("tarif_total")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("approve_bpjs_flag")!=null) {
                criteria.add(Restrictions.eq("approveBpjsFlag", (String) mapCriteria.get("approve_bpjs_flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.asc("idTindakanRawat"));

        List<ItSimrsTindakanRawatEntity> results = criteria.list();

        return results;
    }

    public BigInteger getSumOfTarifTindakanByIdDetailCheckup(String idDetailCheckup){

        String SQL = "SELECT id_detail_checkup, SUM(tarif_total) as total_biaya FROM it_simrs_tindakan_rawat\n" +
                "WHERE id_detail_checkup = :id \n" +
                "GROUP BY id_detail_checkup";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .list();

        BigInteger jumlah = new BigInteger(String.valueOf(0));
        if (!result.isEmpty()){
            for (Object[] obj : result){
                jumlah = new BigInteger(obj[1].toString());
            }
        }
        return jumlah;
    }

    public List<TindakanRawat> cekTodayTindakanTarifKamar(String idDetail, String tanggal){

        List<TindakanRawat> tindakanRawatList = new ArrayList<>();

        String SQL = "SELECT id_tindakan, id_detail_checkup FROM it_simrs_tindakan_rawat \n" +
                "WHERE CAST(created_date AS date) = to_date(:tanggal, 'dd-MM-yyyy') AND id_detail_checkup = :idDetail";

        List<Object[]> result = new ArrayList<>();

        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("tanggal", tanggal)
                .setParameter("idDetail", idDetail)
                .list();

        TindakanRawat rawat;
        if (!result.isEmpty()){
            for (Object[] obj : result){
                rawat = new TindakanRawat();
                rawat.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                rawat.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                tindakanRawatList.add(rawat);
            }
        }
        return tindakanRawatList;
    }

    public String getNextTindakanRawatId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan_rawat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}