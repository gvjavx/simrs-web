package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.AlertPasien;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderChekupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.pengkajian.model.RingkasanKeluarMasukRs;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
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

    public HeaderDetailCheckup getLastPoliAndStatus(String noCheckup) {

        if (noCheckup != null && !"".equalsIgnoreCase(noCheckup)) {
            String SQL = "SELECT \n" +
                    "detail.no_checkup,\n" +
                    "detail.id_pelayanan,\n" +
                    "detail.status_periksa,\n" +
                    "status.keterangan as status_name,\n" +
                    "pel.nama_pelayanan,\n" +
                    "ranap.nama_ruangan,\n" +
                    "ranap.no_ruangan,\n" +
                    "detail.id_detail_checkup, \n" +
                    "detail.no_sep, \n" +
                    "detail.tarif_bpjs, \n" +
                    "detail.is_kronis, \n" +
                    "detail.kode_cbg\n" +
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

            if (!result.isEmpty()) {
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
                if (obj[9] != null) {
                    headerDetailCheckup.setTarifBpjs(new BigDecimal(obj[9].toString()));
                }
                headerDetailCheckup.setIsKronis(obj[10] == null ? "" : obj[10].toString());
                headerDetailCheckup.setKodeCbg(obj[11] == null ? "" : obj[11].toString());
                return headerDetailCheckup;
            }
        }
        return new HeaderDetailCheckup();
    }

    public List<String> getListNoCheckupByCriteria(Map mapCriteria, Boolean isPoli, Boolean isStatus) {

        String idPasien = "%";
        String noKtp = "%";
        String nama = "%";
        String branchId = "%";
        String idPelayanan = "%";
        String statusPeriksa = "%";

        String dateFrom = "";
        String dateTo = "";

        //sodiq, 17 Nov 2019, penambahan no checkup
        String noCheckup = "%";
        if (mapCriteria.get("no_checkup") != null) {
            noCheckup = mapCriteria.get("no_checkup").toString();
        }

        if (mapCriteria.get("id_pasien") != null) {
            idPasien = mapCriteria.get("id_pasien").toString();
        }
        if (mapCriteria.get("no_ktp") != null) {
            noKtp = mapCriteria.get("no_ktp").toString();
        }
        if (mapCriteria.get("nama") != null) {
            nama = "%" + mapCriteria.get("nama").toString() + "%";
        }
        if (mapCriteria.get("branch_id") != null) {
            branchId = mapCriteria.get("branch_id").toString();
        }
        if (mapCriteria.get("id_pelayanan") != null) {
            idPelayanan = mapCriteria.get("id_pelayanan").toString();
        }
        if (mapCriteria.get("status_periksa") != null) {
            statusPeriksa = mapCriteria.get("status_periksa").toString();
        }

        if (mapCriteria.get("date_from") != null && !"".equalsIgnoreCase(mapCriteria.get("date_from").toString())) {
            dateFrom = mapCriteria.get("date_from").toString();
        }

        if (mapCriteria.get("date_to") != null && !"".equalsIgnoreCase(mapCriteria.get("date_to").toString())) {
            dateTo = mapCriteria.get("date_to").toString();
        }

        String SQL = "SELECT\n" +
                "detail.no_checkup,\n" +
                "h.branch_id\n" +
                "FROM \n" +
//                "it_simrs_header_detail_checkup detail\n" +
                "(SELECT * FROM(\n" +
                "SELECT no_checkup, id_pelayanan,status_periksa, flag, rank() OVER (PARTITION BY no_checkup ORDER BY created_date DESC) as rank\n" +
                "FROM it_simrs_header_detail_checkup\n" +
                ") a WHERE a.rank = 1\n" +
                ") detail\n" +
                "INNER JOIN it_simrs_header_checkup h ON h.no_checkup = detail.no_checkup\n" +
                "WHERE h.id_pasien LIKE :idPasien\n" +
                "AND h.no_ktp LIKE :noKtp\n" +
                "AND h.nama LIKE :nama\n" +
                "AND h.branch_id LIKE :branchId\n" +
                "AND detail.id_pelayanan LIKE :idPelayanan\n" +
                "AND detail.status_periksa LIKE :statusPeriksa\n" +
                "AND detail.flag = 'Y'\n" +
                "AND h.no_checkup LIKE :noCheckup\n";

//        String group = "\n GROUP BY detail.no_checkup, h.branch_id";
        String order = "\n ORDER BY h.created_date DESC";

        List<Object[]> result = new ArrayList<>();

        if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

            SQL = SQL + "\n AND CAST(h.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(h.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" + order;

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPasien", idPasien)
                    .setParameter("noKtp", noKtp)
                    .setParameter("nama", nama)
                    .setParameter("branchId", branchId)
                    .setParameter("idPelayanan", idPelayanan)
                    .setParameter("statusPeriksa", statusPeriksa)
                    .setParameter("noCheckup", noCheckup)
                    .setParameter("dateFrom", dateFrom)
                    .setParameter("dateTo", dateTo)
                    .list();

        } else {

            SQL = SQL + order;

            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPasien", idPasien)
                    .setParameter("noKtp", noKtp)
                    .setParameter("nama", nama)
                    .setParameter("branchId", branchId)
                    .setParameter("idPelayanan", idPelayanan)
                    .setParameter("statusPeriksa", statusPeriksa)
                    .setParameter("noCheckup", noCheckup)
                    .list();

        }

        List<String> listOfResult = new ArrayList<>();

        if (!result.isEmpty()) {
            for (Object[] obj : result) {
                listOfResult.add(obj[0].toString());
            }
        }

        return listOfResult;
    }

    public AlertPasien getAlertPasien(String idPasien, String branchId) {

        if (branchId == null || "".equalsIgnoreCase(branchId)) {
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
        for (Object[] obj : results) {

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[3] == null ? "" : obj[3].toString());

            if (obj[2] != null) {
                Timestamp lastUpdate = (Timestamp) obj[2];
                Long time = lastUpdate.getTime();
                Date date = new Date(time);
                alertPasien.setStTglKeluar(date.toString());
            }
        }

        return alertPasien;
    }

    public AlertPasien gelLastDiagnosa(String noCheckup, String branchId) {

        if (branchId == null || "".equalsIgnoreCase(branchId)) {
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
        for (Object[] obj : results) {

            alertPasien.setNamaPasien(obj[0] == null ? "" : obj[0].toString());
            alertPasien.setDiagnosa(obj[1] == null ? "" : obj[1].toString());
            alertPasien.setNoCheckup(obj[4] == null ? "" : obj[4].toString());

            if (obj[2] != null) {
                Timestamp createdDate = (Timestamp) obj[2];
                Long createdDateTime = createdDate.getTime();
                alertPasien.setStTglMasuk(timeToStringDate(createdDateTime));
            }
            if (obj[3] != null) {
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
        String pelayanan = "";

        List<HeaderCheckup> listOfResult = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            branch = branchId;
        }

        if (poli != null && !"".equalsIgnoreCase(poli)) {
            pelayanan = "\n AND b.id_pelayanan IN (" + poli + ") \n";
        }

        String SQL = "SELECT \n" +
                "a.id_pasien, \n" +
                "a.nama,\n" +
                "a.desa_id, \n" +
                "d.desa_name, \n" +
                "b.id_pelayanan, \n" +
                "c.nama_pelayanan, \n" +
                "d.kecamatan_id, \n" +
                "e.kecamatan_name, \n" +
                "b.tgl_antrian,  \n" +
                "b.no_checkup, \n" +
                "b.id_detail_checkup,\n" +
                "a.id_jenis_periksa_pasien,\n" +
                "f.id,\n" +
                "f.id_detail_checkup,\n" +
                "f.status_bayar\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                "INNER JOIN im_hris_desa d ON CAST(a.desa_id AS character varying) = d.desa_id\n" +
                "INNER JOIN im_hris_kecamatan e ON d.kecamatan_id = e.kecamatan_id\n" +
                "LEFT JOIN it_simrs_uang_muka_pendaftaran f ON b.id_detail_checkup = f.id_detail_checkup\n" +
                "WHERE b.status_periksa = '0'\n" +
                "AND a.branch_id LIKE :branchId \n" +
                pelayanan +
                "AND CAST(a.created_date AS date) = current_date\n" +
                "ORDER BY c.nama_pelayanan, b.tgl_antrian ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
                .list();

        if (!result.isEmpty()) {

            for (Object[] obj : result) {

                HeaderCheckup headerCheckup = new HeaderCheckup();
                headerCheckup.setIdJenisPeriksaPasien(obj[11] == null ? "" : obj[11].toString());
                headerCheckup.setStatusBayar(obj[14] == null ? "" : obj[14].toString());

                if ("bpjs".equalsIgnoreCase(headerCheckup.getIdJenisPeriksaPasien())) {
                    HeaderCheckup checkup = new HeaderCheckup();
                    checkup.setIdPasien(obj[0].toString());
                    checkup.setNama(obj[1].toString());
                    checkup.setNamaDesa(obj[3].toString());
                    checkup.setNamaPelayanan(obj[5].toString());
                    checkup.setNamaKecamatan(obj[7].toString());
                    checkup.setNoCheckup(obj[9].toString());
                    checkup.setIdDetailCheckup(obj[10].toString());
                    listOfResult.add(checkup);
                } else {
                    if ("Y".equalsIgnoreCase(headerCheckup.getStatusBayar())) {
                        HeaderCheckup checkup = new HeaderCheckup();
                        checkup.setIdPasien(obj[0].toString());
                        checkup.setNama(obj[1].toString());
                        checkup.setNamaDesa(obj[3].toString());
                        checkup.setNamaPelayanan(obj[5].toString());
                        checkup.setNamaKecamatan(obj[7].toString());
                        checkup.setNoCheckup(obj[9].toString());
                        checkup.setIdDetailCheckup(obj[10].toString());
                        listOfResult.add(checkup);
                    }
                }
            }
        }

        return listOfResult;
    }

    public List<HeaderCheckup> getListPeriksaPasien(String branchId, String poli) {

        String branch = "%";
        String pelayanan = "";

        List<HeaderCheckup> listOfResult = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            branch = branchId;
        }

        if (poli != null && !"".equalsIgnoreCase(poli)) {
            pelayanan = "\n AND b.id_pelayanan IN (" + poli + ") \n";
            ;
        }

        String SQL = "SELECT a.id_pasien, a.nama, a.desa_id, d.desa_name, b.id_pelayanan,\n" +
                "c.nama_pelayanan, d.kecamatan_id, e.kecamatan_name, b.tgl_antrian\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                "INNER JOIN im_hris_desa d ON CAST(a.desa_id AS character varying) = d.desa_id\n" +
                "INNER JOIN im_hris_kecamatan e ON d.kecamatan_id = e.kecamatan_id\n" +
                "LEFT JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = b.id_detail_checkup \n" +
                "WHERE b.status_periksa = '1'\n" +
                "AND a.branch_id LIKE :branchId \n" + pelayanan +
                "AND c.tipe_pelayanan = 'rawat_jalan' \n" +
                "AND CAST(a.created_date AS date) = current_date\n" +
                "AND pr.id_detail_checkup IS NULL \n" +
                "AND b.is_kronis IS NULL \n" +
                "ORDER BY c.nama_pelayanan, b.tgl_antrian ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
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

    public List<HeaderCheckup> getListAntrianObat(String branchId, String poli) {

        String branch = "%";
        String pelayanan = "";

        List<HeaderCheckup> listOfResult = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {
            branch = branchId;
        }

        if (poli != null && !"".equalsIgnoreCase(poli)) {
            pelayanan = "\n AND b.id_pelayanan IN (" + poli + ") \n";
        }

        String SQL = "SELECT a.id_pasien, a.nama, a.desa_id, d.desa_name, b.id_pelayanan,\n" +
                "c.nama_pelayanan, d.kecamatan_id, e.kecamatan_name, b.tgl_antrian, rc.flag_racik,\n" +
                "pr.id_permintaan_resep\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN im_hris_desa d ON CAST(a.desa_id AS character varying) = d.desa_id\n" +
                "INNER JOIN im_hris_kecamatan e ON d.kecamatan_id = e.kecamatan_id\n" +
                "INNER JOIN mt_simrs_permintaan_resep pr ON pr.id_detail_checkup = b.id_detail_checkup \n" +
                "INNER JOIN im_simrs_pelayanan c ON c.id_pelayanan = pr.tujuan_pelayanan\n" +
                "INNER JOIN im_simrs_pelayanan pl ON pl.id_pelayanan = b.id_pelayanan\n" +
                "LEFT JOIN \n" +
                "(\n" +
                "\tSELECT \n" +
                "\ta.flag_racik,\n" +
                "\tb.id_approval_obat,\n" +
                "\tb.created_date\n" +
                "\tFROM mt_simrs_transaksi_obat_detail a\n" +
                "\tINNER JOIN\n" +
                "\t(\n" +
                "\t\tSELECT \n" +
                "\t\ta.id_approval_obat, \n" +
                "\t\tMAX(a.created_date) as created_date \n" +
                "\t\tFROM mt_simrs_transaksi_obat_detail a\n" +
                "\t\tWHERE a.flag_racik = 'Y'\n" +
                "\t\tGROUP BY\n" +
                "\t\ta.id_approval_obat\n" +
                "\t) b ON b.id_approval_obat = a.id_approval_obat AND b.created_date = a.created_date\n" +
                ") rc ON rc.id_approval_obat = pr.id_approval_obat\n" +
                "WHERE b.status_periksa = '1'\n" +
                "AND pr.status IS NOT NULL\n" +
                "AND pr.flag = 'Y'\n" +
                "AND a.branch_id LIKE :branchId \n" + pelayanan +
                "AND pl.tipe_pelayanan = 'rawat_jalan' OR  pl.tipe_pelayanan = 'igd' \n" +
                "AND CAST(a.created_date AS date) = current_date\n" +
                "ORDER BY c.nama_pelayanan, pr.tgl_antrian ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branch)
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
                checkup.setTglAntian((Timestamp) obj[8]);
                if (obj[9] != null && "Y".equalsIgnoreCase(obj[9].toString())) {
                    checkup.setKetRacik("Obat Racik, Harap Menunggu");
                }
                checkup.setIdPermintaanResep(obj[10].toString());
                listOfResult.add(checkup);
            }
        }

        return listOfResult;
    }

    private String timeToStringDate(Long time) {
        Date date = new Date(time);
        return date.toString();
    }

    public RingkasanKeluarMasukRs getRingkasanMasukRs(String noCheckup) {

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

        if (results.size() > 0) {
            for (Object[] obj : results) {
                ringkasan.setMasukMelalui(obj[0].toString());
                ringkasan.setTglMasukRs(obj[1].toString());
                ringkasan.setTglPindahRuang(obj[2] == null ? "" : obj[2].toString());
                ringkasan.setRuang(obj[3] == null ? "" : obj[3].toString());
            }
        }
        return ringkasan;
    }

    public RingkasanKeluarMasukRs getRingkasanKeluarRs(String noCheckup) {

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
        if (resuts.size() > 0) {
            for (Object[] obj : resuts) {
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

    public List<String> listOfIdDetailCheckupByTipePelayanan(String noCheckup, String kategori) {
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
        if (results.size() > 0) {
            for (Object[] obj : results) {
                detailCheckups.add(obj[0].toString());
            }
        }
        return detailCheckups;
    }

    public HeaderCheckup getDataDetailPasien(String idDetailCheckup) {

        HeaderCheckup checkup = new HeaderCheckup();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            String SQL = "SELECT\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien,\n" +
                    "a.nama,\n" +
                    "a.jenis_kelamin,\n" +
                    "a.no_ktp,\n" +
                    "a.tempat_lahir,\n" +
                    "a.tgl_lahir,\n" +
                    "a.desa_id,\n" +
                    "a.jalan,\n" +
                    "a.suku,\n" +
                    "a.profesi,\n" +
                    "a.no_telp,\n" +
                    "a.agama,\n" +
                    "j.url_ktp,\n" +
                    "a.id_jenis_periksa_pasien,\n" +
                    "b.id_detail_checkup,\n" +
                    "b.id_pelayanan,\n" +
                    "b.no_sep,\n" +
                    "b.tarif_bpjs,\n" +
                    "c.nama_pelayanan,\n" +
                    "d.keterangan,\n" +
                    "e.desa_name,\n" +
                    "f.kecamatan_name,\n" +
                    "g.kota_name,\n" +
                    "h.provinsi_name,\n" +
                    "i.id_rawat_inap,\n" +
                    "i.id_ruangan,\n" +
                    "i.nama_ruangan,\n" +
                    "b.metode_pembayaran,\n" +
                    "b.no_jurnal,\n" +
                    "a.url_doc_rujuk,\n" +
                    "b.url_ttd, \n" +
                    "a.tinggi, \n" +
                    "a.berat_badan, \n" +
                    "j.no_bpjs, \n" +
                    "a.no_rujukan, \n" +
                    "a.tgl_rujukan, \n" +
                    "a.url_doc_rujuk, \n" +
                    "k.id_asuransi, \n" +
                    "k.nama_asuransi, \n" +
                    "b.no_kartu_asuransi\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "INNER JOIN im_simrs_jenis_periksa_pasien d ON a.id_jenis_periksa_pasien = d.id_jenis_periksa_pasien\n" +
                    "INNER JOIN im_hris_desa e ON CAST(a.desa_id AS VARCHAR) = e.desa_id \n" +
                    "INNER JOIN im_hris_kecamatan f ON e.kecamatan_id = f.kecamatan_id\n" +
                    "INNER JOIN im_hris_kota g ON f.kota_id = g.kota_id\n" +
                    "INNER JOIN im_hris_provinsi h ON g.provinsi_id = h.provinsi_id\n" +
                    "INNER JOIN im_simrs_pasien j ON a.id_pasien = j.id_pasien\n" +
                    "LEFT JOIN it_simrs_rawat_inap i ON b.id_detail_checkup = i.id_detail_checkup\n" +
                    "LEFT JOIN im_simrs_asuransi k ON b.id_asuransi = k.id_asuransi\n" +
                    "WHERE b.id_detail_checkup = :id";

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();

            if (results.size() > 0) {
                Object[] obj = results.get(0);
                if (obj != null) {

                    checkup.setNoCheckup(obj[0] == null ? "" : obj[0].toString());
                    checkup.setIdPasien(obj[1] == null ? "" : obj[1].toString());
                    checkup.setNama(obj[2] == null ? "" : obj[2].toString());
                    checkup.setJenisKelamin(obj[3] == null ? "" : obj[3].toString());
                    checkup.setNoKtp(obj[4] == null ? "" : obj[4].toString());
                    checkup.setTempatLahir(obj[5] == null ? "" : obj[5].toString());
                    if (obj[6] != null) {
                        checkup.setTglLahir((Date) obj[6]);
                    }
                    if (obj[7] != null) {
                        checkup.setDesaId(new BigInteger(obj[7].toString()));
                    }
                    checkup.setJalan(obj[8] == null ? "" : obj[8].toString());
                    checkup.setSuku(obj[9] == null ? "" : obj[9].toString());
                    checkup.setProfesi(obj[10] == null ? "" : obj[10].toString());
                    checkup.setNoTelp(obj[11] == null ? "" : obj[11].toString());
                    checkup.setAgama(obj[12] == null ? "" : obj[12].toString());
                    checkup.setUrlKtp(obj[13] == null ? "" : CommonConstant.EXTERNAL_IMG_URI + CommonConstant.RESOURCE_PATH_KTP_PASIEN + obj[13].toString());
                    checkup.setIdJenisPeriksaPasien(obj[14] == null ? "" : obj[14].toString());
                    checkup.setIdDetailCheckup(obj[15] == null ? "" : obj[15].toString());
                    checkup.setIdPelayanan(obj[16] == null ? "" : obj[16].toString());
                    checkup.setNoSep(obj[17] == null ? "" : obj[17].toString());
                    if (obj[18] != null) {
                        checkup.setTarifBpjs(new BigDecimal(obj[18].toString()));
                    }
                    checkup.setNamaPelayanan(obj[19] == null ? "" : obj[19].toString());
                    checkup.setStatusPeriksaName(obj[20] == null ? "" : obj[20].toString());
                    checkup.setNamaDesa(obj[21] == null ? "" : obj[21].toString());
                    checkup.setNamaKecamatan(obj[22] == null ? "" : obj[22].toString());
                    checkup.setNamaKota(obj[23] == null ? "" : obj[23].toString());
                    checkup.setNamaProvinsi(obj[24] == null ? "" : obj[24].toString());
                    checkup.setIdRawatInap(obj[25] == null ? "" : obj[25].toString());
                    checkup.setIdRuangan(obj[26] == null ? "" : obj[26].toString());
                    checkup.setNamaRuangan(obj[27] == null ? "" : obj[27].toString());
                    checkup.setMetodePembayaran(obj[28] == null ? "" : obj[28].toString());
                    checkup.setInvoice(obj[29] == null ? "" : obj[29].toString());
                    checkup.setUrlDocRujuk(obj[30] == null ? "" : CommonConstant.EXTERNAL_IMG_URI+CommonConstant.RESOURCE_PATH_DOC_RUJUK_PASIEN+obj[30].toString());
                    checkup.setUrlTtd(obj[31] == null ? "" : obj[31].toString());
                    checkup.setTinggi(obj[32] == null ? "" : obj[32].toString());
                    checkup.setBerat(obj[33] == null ? "" : obj[33].toString());
                    checkup.setNoBpjs(obj[34] == null ? "" : obj[34].toString());
                    checkup.setNoRujukan(obj[35] == null ? "" : obj[35].toString());
                    checkup.setTglRujukan(obj[36] == null ? "" : obj[36].toString());
                    checkup.setUrlDocRujuk(obj[37] == null ? "" : obj[37].toString());
                    checkup.setIdAsuransi(obj[38] == null ? "" : obj[38].toString());
                    checkup.setNamaAsuransi(obj[39] == null ? "" : obj[39].toString());
                    checkup.setNoKartuAsuransi(obj[40] == null ? "" : obj[40].toString());
                }
            }
        }

        return checkup;
    }

    public List<TransaksiObatDetail> getListObatkronis(String idDetailCheckup, String idApproval) {

        List<TransaksiObatDetail> transaksiObatDetailList = new ArrayList<>();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup) && idApproval != null && !"".equalsIgnoreCase(idApproval)) {

            String SQL = "SELECT\n" +
                    "a.id_permintaan_resep,\n" +
                    "b.id_obat,\n" +
                    "d.nama_obat,\n" +
                    "c.box,\n" +
                    "c.lembar,\n" +
                    "c.biji,\n" +
                    "d.lembar_per_box,\n" +
                    "d.biji_per_lembar,\n" +
                    "b.hari_kronis,\n" +
                    "c.id_pelayanan,\n" +
                    "b.id_transaksi_obat_detail\n" +
                    "FROM mt_simrs_permintaan_resep a\n" +
                    "INNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "INNER JOIN (SELECT id_pelayanan, id_obat, SUM(qty_box) box, SUM(qty_lembar) lembar, SUM(qty_biji) biji\n" +
                    "FROM mt_simrs_obat_poli GROUP BY id_pelayanan, id_obat) c ON a.tujuan_pelayanan = c.id_pelayanan\n" +
                    "INNER JOIN (SELECT id_obat, nama_obat, lembar_per_box, biji_per_lembar\n" +
                    "FROM im_simrs_obat WHERE flag_kronis = 'Y' AND flag = 'Y' GROUP BY id_obat, nama_obat, lembar_per_box, biji_per_lembar) d ON c.id_obat = d.id_obat\n" +
                    "WHERE a.id_detail_checkup = :idDetail\n" +
                    "AND a.id_approval_obat = :idApproval\n";

            List<Object[]> results = new ArrayList<>();

            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetailCheckup)
                    .setParameter("idApproval", idApproval)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    TransaksiObatDetail detail = new TransaksiObatDetail();
                    detail.setIdPermintaanResep(obj[0] == null ? "" : obj[0].toString());
                    detail.setIdObat(obj[1] == null ? "" : obj[1].toString());
                    detail.setNamaObat(obj[2] == null ? "" : obj[2].toString());
                    detail.setQtyBox(obj[3] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[3].toString())));
                    detail.setQtyLembar(obj[4] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[4].toString())));
                    detail.setQtyBiji(obj[5] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[5].toString())));
                    detail.setLembarPerBox(obj[6] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[6].toString())));
                    detail.setBijiPerLembar(obj[7] == null ? new BigInteger(String.valueOf("0")) : new BigInteger(String.valueOf(obj[7].toString())));
                    detail.setHariKronis(obj[8] == null ? 0 : (Integer) obj[8]);
                    detail.setIdPelayanan(obj[9] == null ? "" : obj[9].toString());
                    detail.setIdTransaksiObatDetail(obj[10] == null ? "" : obj[10].toString());
                    transaksiObatDetailList.add(detail);
                }

            }
        }

        return transaksiObatDetailList;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
