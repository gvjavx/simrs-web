package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HeaderCheckupDao extends GenericDao<ItSimrsHeaderChekupEntity, String> {
    @Override
    protected Class<ItSimrsHeaderChekupEntity> getEntityClass() {
        return ItSimrsHeaderChekupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderChekupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderChekupEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            }
            if (mapCriteria.get("id_pasien") != null) {
                criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("id_branch").toString()));
            }
            if (mapCriteria.get("desa_id") != null) {
                criteria.add(Restrictions.eq("desaId", mapCriteria.get("desa_id").toString()));
            }
            if (mapCriteria.get("no_ktp") != null) {
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("list_no_checkup") != null) {
                criteria.add(Restrictions.in("noCheckup", (List<String>) mapCriteria.get("list_no_checkup")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("tgl_keluar_not_null") != null) {
                criteria.add(Restrictions.isNotNull("tglKeluar"));
            }

        List<ItSimrsHeaderChekupEntity> result = criteria.list();
        return result;
    }

    public HeaderDetailCheckup getLastPoliAndStatus(String noCheckup){

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)){
            String SQL = "SELECT \n" +
                    "detail.no_checkup,\n" +
                    "detail.id_pelayanan,\n" +
                    "detail.status_periksa,\n" +
                    "status.keterangan as status_name,\n" +
                    "pel.nama_pelayanan,\n" +
                    "ranap.nama_ruangan,\n" +
                    "ranap.no_ruangan,\n" +
                    "detail.id_detail_checkup, detail.no_sep, detail.tarif_bpjs\n" +
                    "FROM \n" +
                    "it_simrs_header_detail_checkup detail\n" +
                    "INNER JOIN im_simrs_status_pasien status ON status.id_status_pasien = detail.status_periksa\n" +
                    "INNER JOIN im_simrs_pelayanan pel ON pel.id_pelayanan = detail.id_pelayanan \n" +
                    "LEFT OUTER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') ranap ON ranap.id_detail_checkup = detail.id_detail_checkup\n" +
                    "WHERE (detail.no_checkup, detail.created_date) = \n" +
                    "(\n" +
                    "\tSELECT\n" +
                    "\td.no_checkup,\n" +
                    "\tmax(d.created_date) as created_date\n" +
                    "\tFROM it_simrs_header_detail_checkup d\n" +
                    "\tWHERE d.no_checkup = :noCheckup \n" +
                    "\tGROUP BY d.no_checkup\n" +
                    ")";

            List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("noCheckup", noCheckup)
                    .list();

            if (!result.isEmpty()){
                Object[] obj = result.get(0);
                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                headerDetailCheckup.setNoCheckup(obj[0].toString());
                headerDetailCheckup.setIdPelayanan(obj[1].toString());
                headerDetailCheckup.setStatusPeriksa(obj[2].toString());
                headerDetailCheckup.setStatusPeriksaName(obj[3].toString());
                headerDetailCheckup.setNamaPelayanan(obj[4].toString());
                headerDetailCheckup.setNamaRuangan(obj[5] == null ? "" : obj[5].toString());
                headerDetailCheckup.setNoRuangan(obj[6] == null ? "" : obj[6].toString());
                headerDetailCheckup.setIdDetailCheckup(obj[7].toString());
                headerDetailCheckup.setNoSep(obj[8] == null ? "" : obj[8].toString());
                if(obj[9] != null){
                    headerDetailCheckup.setTarifBpjs(new BigDecimal(obj[9].toString()));
                }
                return headerDetailCheckup;
            }
        }
        return new HeaderDetailCheckup();
    }

    public List<String> getListNoCheckupByCriteria(Map mapCriteria, Boolean isPoli, Boolean isStatus){

        String idPasien = "%";
        String noKtp = "%";
        String nama = "%";
        String branchId = "%";
        String idPelayanan = "%";
        String statusPeriksa = "%";

        //sodiq, 17 Nov 2019, penambahan no checkup
        String noCheckup = "%";
        if(mapCriteria.get("no_checkup") != null){
            noCheckup = mapCriteria.get("no_checkup").toString();
        }

        if(mapCriteria.get("id_pasien") != null){
            idPasien = mapCriteria.get("id_pasien").toString();
        }
        if(mapCriteria.get("no_ktp") != null){
            noKtp = mapCriteria.get("no_ktp").toString();
        }
        if(mapCriteria.get("nama") != null){
            nama = "%"+ mapCriteria.get("nama").toString()+"%";
        }
        if(mapCriteria.get("branch_id") != null){
            branchId = mapCriteria.get("branch_id").toString();
        }
        if(mapCriteria.get("id_pelayanan") != null){
            idPelayanan = mapCriteria.get("id_pelayanan").toString();
        }
        if(mapCriteria.get("status_periksa") != null) {
            statusPeriksa = mapCriteria.get("status_periksa").toString();
        }

        String SQL = "SELECT\n" +
                "detail.no_checkup,\n" +
                "h.branch_id\n" +
                "FROM \n" +
                "it_simrs_header_detail_checkup detail\n" +
                "INNER JOIN it_simrs_header_checkup h ON h.no_checkup = detail.no_checkup\n" +
                "WHERE h.id_pasien LIKE :idPasien\n" +
                "AND h.no_ktp LIKE :noKtp\n" +
                "AND h.nama LIKE :nama\n" +
                "AND h.branch_id LIKE :branchId\n" +
                "AND detail.id_pelayanan LIKE :idPelayanan\n" +
                "AND detail.status_periksa LIKE :statusPeriksa\n" +
                "AND detail.flag = 'Y'\n" +
                "AND h.no_checkup LIKE :noCheckup\n" +
                "GROUP BY detail.no_checkup, h.branch_id";

        List<Object[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idPasien", idPasien)
                .setParameter("noKtp", noKtp)
                .setParameter("nama", nama)
                .setParameter("branchId", branchId)
                .setParameter("idPelayanan", idPelayanan)
                .setParameter("statusPeriksa", statusPeriksa)
                .setParameter("noCheckup", noCheckup)
                .list();

        List<String> listOfResult = new ArrayList<>();

        if (!result.isEmpty()){
            for (Object[] obj : result){
                listOfResult.add(obj[0].toString());
            }
        }

        return listOfResult;
    }

    public AlertPasien getAlertPasien(String idPasien, String branchId){

        if (branchId == null || "".equalsIgnoreCase(branchId)){
            branchId = "%";
        }

        String SQL = "SELECT ps.nama, diag.keterangan_diagnosa, ck.last_update, ck.no_checkup FROM it_simrs_header_checkup ck\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = ck.id_pasien\n" +
                "INNER JOIN (SELECT * FROM it_simrs_header_detail_checkup WHERE status_periksa = '3') hdc ON hdc.no_checkup = ck.no_checkup\n" +
                "INNER JOIN (\n" +
                "\tSELECT a.* FROM it_simrs_diagnosa_rawat a\n" +
                "\tINNER JOIN (\n" +
                "\tSELECT id_detail_checkup, \n" +
                "\tMAX(created_date) as created_date \n" +
                "\tFROM it_simrs_diagnosa_rawat\n" +
                "\tGROUP BY id_detail_checkup\n" +
                "\t) b ON b.id_detail_checkup = a.id_detail_checkup AND b.created_date = a.created_date\n" +
                ") diag ON diag.id_detail_checkup = hdc.id_detail_checkup\n" +
                "WHERE ck.id_pasien = :idPasien \n" +
                "AND ck.branch_id LIKE :branchId \n" +
                "ORDER BY hdc.last_update DESC\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("idPasien", idPasien)
                .list();

        AlertPasien alertPasien = new AlertPasien();
        for (Object[] obj : results){

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[3] == null ? "" : obj[3].toString());

            if (obj[2] != null){
                Timestamp lastUpdate = (Timestamp) obj[2];
                Long time = lastUpdate.getTime();
                Date date = new Date(time);
                alertPasien.setStTglKeluar(date.toString());
            }
        }

        return alertPasien;
    }

    public AlertPasien gelLastDiagnosa(String noCheckup, String branchId){

        if (branchId == null || "".equalsIgnoreCase(branchId)){
            branchId = "%";
        }

        String SQL = "SELECT ps.nama, diag.keterangan_diagnosa, ck.created_date, ck.tgl_keluar, ck.no_checkup FROM it_simrs_header_checkup ck\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = ck.id_pasien \n" +
                "INNER JOIN (SELECT * FROM it_simrs_header_detail_checkup WHERE status_periksa = '3') hdc ON hdc.no_checkup = ck.no_checkup \n" +
                "INNER JOIN ( \n" +
                "SELECT a.* FROM it_simrs_diagnosa_rawat a \n" +
                "INNER JOIN ( \n" +
                "SELECT id_detail_checkup,  \n" +
                "MAX(created_date) as created_date  \n" +
                "FROM it_simrs_diagnosa_rawat \n" +
                "GROUP BY id_detail_checkup \n" +
                ") b ON b.id_detail_checkup = a.id_detail_checkup AND b.created_date = a.created_date \n" +
                ") diag ON diag.id_detail_checkup = hdc.id_detail_checkup \n" +
                "WHERE ck.no_checkup LIKE :noCheckup \n" +
                "AND ck.branch_id LIKE :branchId \n" +
                "ORDER BY hdc.last_update DESC \n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("noCheckup", noCheckup)
                .list();

        AlertPasien alertPasien = new AlertPasien();
        for (Object[] obj : results){

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[4] == null ? "" : obj[4].toString());

            if (obj[2] != null){
                Timestamp createdDate = (Timestamp) obj[2];
                Long createdDateTime = createdDate.getTime();
                alertPasien.setStTglMasuk(timeToStringDate(createdDateTime));
            }
            if (obj[3] != null){
                Timestamp lastUpdate = (Timestamp) obj[3];
                Long lastUpdateTime = lastUpdate.getTime();
                alertPasien.setStTglKeluar(timeToStringDate(lastUpdateTime));
            } else {
                alertPasien.setStTglKeluar("");
            }
        }

        return alertPasien;
    }

    public List<HeaderCheckup> getListAntrianPasien(String branchId, String poli) {

        String branch = "%";
        String pelayanan = "%";

        List<HeaderCheckup> listOfResult = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            branch = branchId;
        }

        if (poli != null && !"".equalsIgnoreCase(poli)) {
            pelayanan = poli;
        }

        String SQL = "SELECT a.id_pasien, a.nama, a.desa_id, d.desa_name, b.id_pelayanan,\n" +
                "c.nama_pelayanan, d.kecamatan_id, e.kecamatan_name, b.tgl_antrian\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                "INNER JOIN im_hris_desa d ON CAST(a.desa_id AS character varying) = d.desa_id\n" +
                "INNER JOIN im_hris_kecamatan e ON d.kecamatan_id = e.kecamatan_id\n" +
                "WHERE b.status_periksa = '0'\n" +
                "AND a.branch_id LIKE :branchId \n" +
                "AND b.id_pelayanan LIKE :poliId \n" +
                "AND CAST(a.created_date AS date) = current_date\n" +
                "ORDER BY c.nama_pelayanan, b.tgl_antrian ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .setParameter("poliId", pelayanan)
                .list();

        if (!result.isEmpty()) {
            HeaderCheckup checkup;
            for (Object[] obj : result) {
                checkup = new HeaderCheckup();
                checkup.setIdPasien(obj[0].toString());
                checkup.setNama(obj[1].toString());
                checkup.setNamaDesa(obj[3].toString());
                checkup.setNamaPelayanan(obj[5].toString());
                checkup.setNamaKecamatan(obj[7].toString());
                listOfResult.add(checkup);
            }
        }

        return listOfResult;
    }

    public List<HeaderCheckup> getListPeriksaPasien(String branchId, String poli) {

        String branch = "%";
        String pelayanan = "%";

        List<HeaderCheckup> listOfResult = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            branch = branchId;
        }

        if (poli != null && !"".equalsIgnoreCase(poli)) {
            pelayanan = poli;
        }

        String SQL = "SELECT a.id_pasien, a.nama, a.desa_id, d.desa_name, b.id_pelayanan,\n" +
                "c.nama_pelayanan, d.kecamatan_id, e.kecamatan_name, b.tgl_antrian\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                "INNER JOIN im_hris_desa d ON CAST(a.desa_id AS character varying) = d.desa_id\n" +
                "INNER JOIN im_hris_kecamatan e ON d.kecamatan_id = e.kecamatan_id\n" +
                "WHERE b.status_periksa = '1'\n" +
                "AND a.branch_id LIKE :branchId \n" +
                "AND b.id_pelayanan LIKE :poliId AND c.tipe_pelayanan = 'rawat_jalan' \n" +
                "AND CAST(a.created_date AS date) = current_date\n" +
                "ORDER BY c.nama_pelayanan, b.tgl_antrian ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .setParameter("poliId", pelayanan)
                .list();

        if (!result.isEmpty()) {
            HeaderCheckup checkup;
            for (Object[] obj : result) {
                checkup = new HeaderCheckup();
                checkup.setIdPasien(obj[0].toString());
                checkup.setNama(obj[1].toString());
                checkup.setNamaDesa(obj[3].toString());
                checkup.setNamaPelayanan(obj[5].toString());
                checkup.setNamaKecamatan(obj[7].toString());
                listOfResult.add(checkup);
            }
        }

        return listOfResult;
    }

    private String timeToStringDate(Long time){
        Date date = new Date(time);
        return date.toString();
    }

    public RingkasanKeluarMasukRs getRingkasanMasukRs(String noCheckup){

        String SQL = "SELECT \n" +
                "pel.nama_pelayanan,\n" +
                "dck.created_date as tgl_masuk,\n" +
                "ri.created_date as tgl_pindah,\n" +
                "mri.nama_ruangan\n" +
                "FROM it_simrs_header_detail_checkup dck\n" +
                "INNER JOIN im_simrs_pelayanan pel ON pel.id_pelayanan = dck.id_pelayanan\n" +
                "LEFT JOIN (\n" +
                "\tSELECT inap.no_checkup, MIN(inap.created_date) as created_date\n" +
                "\tFROM it_simrs_rawat_inap inap\n" +
                "\tGROUP BY inap.no_checkup\n" +
                ") ri ON ri.no_checkup = dck.no_checkup\n" +
                "LEFT JOIN (\n" +
                "\tSELECT \n" +
                "\tno_checkup,\n" +
                "\tid_detail_checkup, \n" +
                "\tid_ruangan, \n" +
                "\tnama_ruangan,\n" +
                "\tcreated_date\n" +
                "\tFROM it_simrs_rawat_inap\n" +
                ") mri ON mri.no_checkup = ri.no_checkup AND mri.created_date = ri.created_date\n" +
                "WHERE dck.no_checkup = :no ORDER BY dck.created_date ASC LIMIT 1";

        RingkasanKeluarMasukRs ringkasan = new RingkasanKeluarMasukRs();
        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("no", noCheckup)
                .list();

        if (results.size() > 0){
            for (Object[] obj : results){
                ringkasan.setMasukMelalui(obj[0].toString());
                ringkasan.setTglMasukRs(obj[1].toString());
                ringkasan.setTglPindahRuang(obj[2] == null ? "" : obj[2].toString());
                ringkasan.setRuang(obj[3] == null ? "" : obj[3].toString());
            }
        }
        return ringkasan;
    }

    public RingkasanKeluarMasukRs getRingkasanKeluarRs(String noCheckup){

        String SQL = "SELECT \n" +
                "a.id_detail_checkup,\n" +
                "a.keterangan_selesai,\n" +
                "a.cara_pasien_pulang,\n" +
                "a.tempat_tujuan,\n" +
                "a.keterangan_cekup_ulang,\n" +
                "a.last_update\n" +
                "FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN\n" +
                "(\n" +
                "\tSELECT dck.no_checkup, MAX(dck.created_date) as created_date\n" +
                "\tFROM it_simrs_header_detail_checkup dck\n" +
                "\tWHERE dck.no_checkup = :no\n" +
                "\tAND dck.status_periksa = '3'" +
                "\tGROUP BY dck.no_checkup\n" +
                ") b ON b.no_checkup = a.no_checkup AND b.created_date = a.created_date";

        List<Object[]> resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("no", noCheckup)
                .list();

        RingkasanKeluarMasukRs ringkasan = new RingkasanKeluarMasukRs();
        if (resuts.size() > 0){
            for (Object[] obj : resuts){
                ringkasan.setIdDetailCheckup(obj[0].toString());
                ringkasan.setKeadaanKeluar(obj[1].toString());
                ringkasan.setCaraKeluar(obj[2] == null ? "" : obj[2].toString());
                ringkasan.setTujuanPulang(obj[3] == null ? "" : obj[3].toString());
                ringkasan.setKetCheckupUlang(obj[4] == null ? "" : obj[4].toString());
                ringkasan.setTglKeluarRs(obj[5] == null ? "" : obj[5].toString());
            }
        }
        return ringkasan;
    }

    public List<String> listOfIdDetailCheckupByTipePelayanan(String noCheckup, String kategori){
        String SQL = "SELECT \n" +
                "a.id_detail_checkup,\n" +
                "a.no_checkup\n" +
                "FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN im_simrs_pelayanan b ON b.id_pelayanan = a.id_pelayanan\n" +
                "WHERE a.no_checkup LIKE :no\n" +
                "AND b.tipe_pelayanan = :kat";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("no", noCheckup)
                .setParameter("kat", kategori)
                .list();

        List<String> detailCheckups = new ArrayList<>();
        if (results.size() > 0){
            for (Object[] obj : results){
                detailCheckups.add(obj[0].toString());
            }
        }
        return detailCheckups;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
