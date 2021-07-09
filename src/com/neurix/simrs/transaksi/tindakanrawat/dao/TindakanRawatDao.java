package com.neurix.simrs.transaksi.tindakanrawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
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

        //SYAMS 9JUL21 =>  Order by date
        criteria.addOrder(Order.desc("lastUpdate"));

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

    public List<TindakanRawat> getListTindakanRawat(String noCheckup, String jenis){
        List<TindakanRawat> rawatList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "a.id_tindakan_rawat,\n" +
                "a.id_tindakan,\n" +
                "a.nama_tindakan,\n" +
                "a.tarif,\n" +
                "a.qty,\n" +
                "a.tarif_total,\n" +
                "a.id_detail_checkup,\n" +
                "a.id_pelayanan,\n" +
                "d.nama_pelayanan,\n" +
                "a.created_date,\n" +
                "a.id_dokter\n" +
                "FROM it_simrs_tindakan_rawat a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id,"+
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                ") d ON b.id_pelayanan = d.id_pelayanan\n" +
                "WHERE c.no_checkup = :id AND b.id_jenis_periksa_pasien = :jen \n" +
                "ORDER BY a.id_detail_checkup ASC";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .setParameter("jen", jenis)
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
                tindakanRawat.setIdDokter(obj[10] != null ? (String) obj[10] : null);
                tindakanRawat.setIdKategoriTindakan(getIdKategoriTindakan(tindakanRawat.getIdTindakan()));
                tindakanRawat.setKategoriRuangan(getKategoriRuangan(tindakanRawat.getIdDetailCheckup()));
                rawatList.add(tindakanRawat);
            }
        }
        return rawatList;
    }

    private String getIdKategoriTindakan(String idTindakan) {
        String idKategoriTindakan = "";
        if(idTindakan != null && !"".equalsIgnoreCase(idTindakan)){
            Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class);
            criteria.add(Restrictions.ilike("idTindakan", idTindakan));
            criteria.add(Restrictions.eq("flag", "Y"));
            List<ImSimrsTindakanEntity> listOfResult = criteria.list();
            if(listOfResult.size() > 0){
                idKategoriTindakan = listOfResult.get(0).getIdKategoriTindakan();
            }
        }
        return idKategoriTindakan;
    }

    private String getKategoriRuangan(String idDetailCheckup){
        String kategoriRuangan = "";
        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            String SQL = "SELECT\n" +
                    "a.id_rawat_inap,\n" +
                    "d.kategori\n" +
                    "FROM it_simrs_rawat_inap a\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur b ON a.id_ruangan = b.id_tempat_tidur\n" +
                    "INNER JOIN mt_simrs_ruangan c ON b.id_ruangan = c.id_ruangan\n" +
                    "INNER JOIN im_simrs_kelas_ruangan d ON c.id_kelas_ruangan = d.id_kelas_ruangan\n" +
                    "WHERE a.id_detail_checkup = :id";
            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if(results.size() > 0){
                Object[] obj = results.get(0);
                kategoriRuangan = obj[1].toString();
            }
        }
        return kategoriRuangan;
    }

    public String getNextTindakanRawatId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tindakan_rawat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}