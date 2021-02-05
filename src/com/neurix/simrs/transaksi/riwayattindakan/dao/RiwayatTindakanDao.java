package com.neurix.simrs.transaksi.riwayattindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.Pelayanan;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.riwayattindakan.model.ItSimrsRiwayatTindakanEntity;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RiwayatTindakanDao extends GenericDao<ItSimrsRiwayatTindakanEntity, String> {
    @Override
    protected Class<ItSimrsRiwayatTindakanEntity> getEntityClass() {
        return ItSimrsRiwayatTindakanEntity.class;
    }

    @Override
    public List<ItSimrsRiwayatTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRiwayatTindakanEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_riwayat_tindakan") != null) {
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("id_riwayat_tindakan")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_tindakan") != null) {
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
            if (mapCriteria.get("keterangan") != null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("kategori_tindakan_bpjs") != null) {
                criteria.add(Restrictions.eq("kategoriTindakanBpjs", (String) mapCriteria.get("kategori_tindakan_bpjs")));
            }
            if (mapCriteria.get("approve_bpjs_flag") != null) {
                criteria.add(Restrictions.eq("idRiwayatTindakan", (String) mapCriteria.get("approve_bpjs_flag")));
            }
            if (mapCriteria.get("jenis_pasien") != null) {
                criteria.add(Restrictions.eq("jenisPasien", (String) mapCriteria.get("jenis_pasien")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("not_resep") != null) {
                criteria.add(Restrictions.ne("keterangan", "resep"));
            }
            if (mapCriteria.get("id_ruangan") != null) {
                criteria.add(Restrictions.eq("idRuangan", (String) mapCriteria.get("id_ruangan")));
            }
        }

        criteria.addOrder(Order.asc("idRiwayatTindakan"));
        List<ItSimrsRiwayatTindakanEntity> result = criteria.list();
        return result;
    }

    public List<RiwayatTindakan> cekTodayTindakanTarifKamar(String idDetail){

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        String SQL = "SELECT id_tindakan, id_detail_checkup FROM it_simrs_riwayat_tindakan \n" +
                "WHERE CAST(created_date AS date) = CURRENT_DATE AND id_detail_checkup = :idDetail AND is_kamar = 'Y'";

        List<Object[]> result = new ArrayList<>();

        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .list();

        RiwayatTindakan tindakan;
        if (!result.isEmpty()){
            for (Object[] obj : result){
                tindakan = new RiwayatTindakan();
                tindakan.setIdTindakan(obj[0] == null ? "" : obj[0].toString());
                tindakan.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                riwayatTindakanList.add(tindakan);
            }
        }
        return riwayatTindakanList;
    }

    public List<RiwayatTindakan> getListTindakan(RiwayatTindakan bean) {

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();
        if (bean != null) {

            String branchId = "%";
            String noCheckup = "%";
            String idDetail = "%";
            String jenis = "";

            if(bean.getBranchId() != null){
                branchId = bean.getBranchId();
            }

            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                noCheckup = bean.getNoCheckup();
            }

            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                idDetail = bean.getIdDetailCheckup();
            }

            if(bean.getJenisPasien() != null && !"".equalsIgnoreCase(bean.getJenisPasien())){
                jenis = "AND c.jenis_pasien = 'umum'";
            }

            String SQL = "SELECT\n" +
                    "a.no_checkup,\n" +
                    "b.id_detail_checkup, \n" +
                    "c.id_riwayat_tindakan, \n" +
                    "c.id_tindakan, \n" +
                    "c.nama_tindakan, \n" +
                    "c.keterangan, \n" +
                    "c.jenis_pasien, \n" +
                    "c.total_tarif, \n" +
                    "c.kategori_tindakan_bpjs, \n" +
                    "c.approve_bpjs_flag,\n" +
                    "c.tanggal_tindakan,\n" +
                    "d.kategori_ina_bpjs,\n" +
                    "c.flag_update_klaim, \n" +
                    "e.nama_pelayanan, \n" +
                    "e.tipe_pelayanan \n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN it_simrs_riwayat_tindakan c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                    "LEFT JOIN (\n" +
                    "SELECT\n" +
                    "a.id_tindakan_rawat,\n" +
                    "a.id_tindakan,\n" +
                    "c.kategori_ina_bpjs\n" +
                    "FROM it_simrs_tindakan_rawat a\n" +
                    "INNER JOIN im_simrs_tindakan b ON a.id_tindakan = b.id_tindakan\n" +
                    "INNER JOIN im_simrs_header_tindakan c ON b.id_header_tindakan = c.id_header_tindakan\n" +
                    ") d ON c.id_tindakan = d.id_tindakan_rawat\n" +
                    "INNER JOIN (SELECT\n" +
                    "a.nama_pelayanan,\n" +
                    "a.tipe_pelayanan,\n" +
                    "a.divisi_id,\n" +
                    "a.kode_vclaim,\n" +
                    "b.id_pelayanan,\n" +
                    "b.branch_id\n" +
                    "FROM im_simrs_header_pelayanan a\n" +
                    "INNER JOIN im_simrs_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan \n" +
                    ") e ON b.id_pelayanan = e.id_pelayanan \n"+
                    "WHERE a.branch_id LIKE :branchId \n" +
                    "AND a.no_checkup LIKE :noCheckup \n" +
                    "AND b.id_detail_checkup LIKE :idDetail \n" + jenis +
                    "ORDER BY b.id_detail_checkup ASC, c.tanggal_tindakan ASC";

            List<Object[]> result = new ArrayList<>();

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .setParameter("noCheckup", noCheckup)
                    .setParameter("idDetail", idDetail)
                    .list();

            RiwayatTindakan tindakan;
            if (!result.isEmpty()) {
                for (Object[] obj : result) {
                    tindakan = new RiwayatTindakan();
                    tindakan.setNoCheckup(obj[0] == null ? "" : obj[0].toString());
                    tindakan.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                    tindakan.setIdRiwayatTindakan(obj[2] == null ? "" : obj[2].toString());
                    tindakan.setIdTindakan(obj[3] == null ? "" : obj[3].toString());
                    tindakan.setNamaTindakan(obj[4] == null ? "" : obj[4].toString());
                    tindakan.setKeterangan(obj[5] == null ? "" : obj[5].toString());
                    tindakan.setJenisPasien(obj[6] == null ? "" : obj[6].toString());
                    if(obj[7] != null){
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(obj[7].toString())));
                    }
                    tindakan.setKategoriTindakanBpjs(obj[8] == null ? "" : obj[8].toString());
                    tindakan.setApproveBpjsFlag(obj[9] == null ? "" : obj[9].toString());

                    if(obj[10] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[10]);
                        tindakan.setTanggalTindakan((Timestamp) obj[10]);
                        tindakan.setStTglTindakan(formatDate);
                    }
                    tindakan.setKategoriInaBpjs(obj[11] == null ? "" : obj[11].toString());
                    tindakan.setFlagUpdateKlaim((obj[12] == null ? "" : obj[12].toString()));
                    tindakan.setNamaPelayanan((obj[13] == null ? "" : obj[13].toString()));
                    tindakan.setTipePelayanan((obj[14] == null ? "" : obj[14].toString()));
                    if(obj[14] == null){
                        if("rawat_jalan".equalsIgnoreCase(obj[14].toString())){
                            tindakan.setJenisPelayanan("RJ");
                        }
                        if("rawat_inap".equalsIgnoreCase(obj[14].toString())){
                            tindakan.setJenisPelayanan("RI");
                        }
                        if("igd".equalsIgnoreCase(obj[14].toString()) || "ugd".equalsIgnoreCase(obj[14].toString())){
                            tindakan.setJenisPelayanan("IGD");
                        }
                    }
                    riwayatTindakanList.add(tindakan);
                }
            }
        }

        return riwayatTindakanList;

    }

    public List<RiwayatTindakan> getListTindakanApprove(String idDetail) {

        List<RiwayatTindakan> riwayatTindakanList = new ArrayList<>();

        if(idDetail != null && !"".equalsIgnoreCase(idDetail)){

            String SQL = "SELECT \n" +
                    "a.tanggal_tindakan, \n" +
                    "a.nama_tindakan,  \n" +
                    "a.total_tarif, \n" +
                    "a.keterangan \n" +
                    "FROM it_simrs_riwayat_tindakan a\n" +
                    "WHERE a.id_detail_checkup = :idDet\n" +
                    "AND flag_update_klaim = 'Y'\n" +
                    "AND a.jenis_pasien = 'bpjs' \n" +
                    "ORDER BY a.tanggal_tindakan ASC";

            List<Object[]> result = new ArrayList<>();

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDet", idDetail)
                    .list();

            RiwayatTindakan tindakan;
            if (!result.isEmpty()) {
                for (Object[] obj : result) {
                    tindakan = new RiwayatTindakan();

                    if(obj[0] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[0]);
                        tindakan.setTanggalTindakan((Timestamp) obj[0]);
                        tindakan.setStTglTindakan(formatDate);
                    }else{
                        tindakan.setStTglTindakan("");
                    }

                    tindakan.setNamaTindakan(obj[1] == null ? "" : obj[1].toString());

                    if(obj[2] != null){
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(obj[2].toString())));
                    }else{
                        tindakan.setTotalTarif(new BigDecimal(String.valueOf(0)));
                    }

                    tindakan.setKeterangan(obj[3] == null ? "" : obj[3].toString());

                    riwayatTindakanList.add(tindakan);
                }
            }
        }
        return riwayatTindakanList;
    }

    public Boolean checkIsTransitoris(String id){
        Boolean found = false;

        String SQL = "SELECT id_riwayat_tindakan, id_detail_checkup, id_tindakan \n" +
                "FROM it_simrs_tindakan_transitoris \n" +
                "WHERE id_detail_checkup = :id \n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        if (results.size() > 0){
            found = true;
        }
        return found;
    }

    public List<String> listOfKeteranganExistByIdDetailCheckup(String id){
        String SQL = "SELECT keterangan, id_detail_checkup FROM it_simrs_riwayat_tindakan\n" +
                "WHERE id_detail_checkup = :id \n" +
                "GROUP BY keterangan, id_detail_checkup";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        List<String> listKeterangan = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                listKeterangan.add(obj[0].toString());
            }
        }

        return listKeterangan;
    }

    public List<String> listOfRuanganRiwayatTindakan(String id, String keterangan){

        String SQL = "SELECT keterangan, id_detail_checkup, id_ruangan FROM it_simrs_riwayat_tindakan\n" +
                "    WHERE id_detail_checkup = :id \n" +
                "    AND keterangan = :keterangan \n" +
                "    AND id_ruangan is not null\n" +
                "    GROUP BY keterangan, id_detail_checkup, id_ruangan";
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .setParameter("keterangan", keterangan)
                .list();

        List<String> listKeterangan = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                // idRuangan
                listKeterangan.add(obj[2].toString());
            }
        }
        return listKeterangan;
    }


    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_riwayat_tindakan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    //for typeahead
    public List<ItSimrsRiwayatTindakanEntity> getRiwayatTindakanListByLike(String riwayatTindakanName) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRiwayatTindakanEntity.class);
        criteria.add(
                Restrictions.or(
                        Restrictions.ilike("idTindakan", riwayatTindakanName + "%"),
                        Restrictions.ilike("namaTindakan", "%"+riwayatTindakanName+"%")
                )
        );
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idTindakan"));

        List<ItSimrsRiwayatTindakanEntity> results = criteria.list();
        return results;
    }

    public List<String> getListDetailCheckupByNoCheckup(String noCheckup) {

        String SQL = "SELECT \n" +
                "id_detail_checkup, \n" +
                "no_checkup \n" +
                "FROM it_simrs_header_detail_checkup\n" +
                "WHERE no_checkup = :noCheckup ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("noCheckup", noCheckup)
                .list();

        List<String> listResults = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                listResults.add(obj[0].toString());
            }
        }

        return listResults;
    }

    public List<UangMuka> getListUangMukaByNoCheckup(String noCheckup) {

        String SQL = "SELECT \n" +
                "a.id,\n" +
                "a.jumlah_dibayar,\n" +
                "a.flag_refund,\n" +
                "a.id_detail_checkup\n" +
                "FROM it_simrs_uang_muka_pendaftaran a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON b.id_detail_checkup = a.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON c.no_checkup = b.no_checkup\n" +
                "WHERE c.no_checkup = :noCheckup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("noCheckup", noCheckup)
                .list();

        List<UangMuka> listResults = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                UangMuka uangMuka = new UangMuka();
                uangMuka.setId(obj[0].toString());
                uangMuka.setDibayar(obj[1] == null ? new BigInteger(String.valueOf(0))  : (BigInteger) obj[1]);
                uangMuka.setFlagRefund(obj[2] == null ? "" : obj[2].toString());
                uangMuka.setIdDetailCheckup(obj[3] == null ? "" : obj[3].toString());
                listResults.add(uangMuka);
            }
        }

        return listResults;
    }

    public Boolean checkIsPelayananRawatJalan(String idDetailCheckup){

        String SQL = "SELECT \n" +
                "c.id_pelayanan, \n" +
                "c.divisi_id, \n" +
                "c.tipe_pelayanan \n" +
                "FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN (\n" +
                "SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id,"+
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n"+
                ") c ON c.id_pelayanan = a.id_pelayanan\n" +
                "WHERE a.id_detail_checkup = :idDetail \n" +
                "AND tipe_pelayanan IN ('rawat_jalan','igd')";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetailCheckup)
                .list();

        if (objects.size() > 0){
            return true;
        }
        return false;
    }
}