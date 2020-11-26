package com.neurix.simrs.transaksi.tindakanrawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.Tindakan;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import com.neurix.simrs.transaksi.tindakanrawat.model.TindakanRawat;
import com.neurix.simrs.transaksi.tindakanrawaticd9.model.TindakanRawatICD9;
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
            if (mapCriteria.get("approve_flag")!=null) {
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
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

    public List<TindakanRawat> getListTindakanRawat(String noCheckup){
        List<TindakanRawat> rawatList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id_tindakan_rawat,\n" +
                "a.id_tindakan,\n" +
                "a.nama_tindakan,\n" +
                "a.tarif,\n" +
                "a.qty,\n" +
                "a.tarif_total,\n" +
                "a.id_detail_checkup,\n" +
                "d.id_pelayanan,\n" +
                "d.nama_pelayanan,\n" +
                "a.created_date\n" +
                "FROM it_simrs_tindakan_rawat a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE c.no_checkup = :id\n" +
                "ORDER BY a.id_detail_checkup ASC;";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
                TindakanRawat tindakanRawat = new TindakanRawat();
                tindakanRawat.setIdTindakanRawat(obj[0] != null ? obj[0].toString() : "");
                tindakanRawat.setIdTindakan(obj[1] != null ? obj[1].toString() : "");
                tindakanRawat.setNamaTindakan(obj[2] != null ? obj[2].toString() : "");
                tindakanRawat.setTarif(obj[3] != null ? (BigInteger) obj[3] : null);
                tindakanRawat.setQty(obj[4] != null ? (BigInteger) obj[4] : null);
                tindakanRawat.setTarifTotal(obj[5] != null ? (BigInteger) obj[5] : null);
                tindakanRawat.setIdDetailCheckup(obj[6] != null ? obj[6].toString() : "");
                tindakanRawat.setIdPelayanan(obj[7] != null ? obj[7].toString() : "");
                tindakanRawat.setNamaPelayanan(obj[8] != null ? obj[8].toString() : "");
                tindakanRawat.setCreatedDate(obj[9] != null ? (Timestamp) obj[9] : null);
                rawatList.add(tindakanRawat);
            }
        }
        return rawatList;
    }

    public String getNextTindakanRawatId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan_rawat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}