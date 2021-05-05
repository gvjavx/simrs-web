package com.neurix.simrs.transaksi.rawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.checkup.model.HeaderCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.UangMuka;
import com.neurix.simrs.transaksi.rawatinap.model.ItSimrsRawatInapEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class RawatInapDao extends GenericDao<ItSimrsRawatInapEntity, String> {
    @Override
    protected Class<ItSimrsRawatInapEntity> getEntityClass() {
        return ItSimrsRawatInapEntity.class;
    }

    @Override
    public List<ItSimrsRawatInapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRawatInapEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_rawat_inap") != null) {
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            }
        if (mapCriteria.get("id_detail_checkup") != null) {
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("no_checkup") != null) {
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }
        if (mapCriteria.get("id_ruangan") != null) {
            criteria.add(Restrictions.eq("idRuangan", mapCriteria.get("id_ruangan").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<ItSimrsRawatInapEntity> result = criteria.list();
        return result;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rawat_inap')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<RawatInap> getSearchRawatInap(RawatInap bean) {
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String jenisKelamin = "%";
            String idKelas = "%";
            String idRuang = "%";
            String idDetailCheckup = "%";
            String dateFrom = "";
            String dateTo = "";
            String branchId = "%";
            String jenisPeriksa = "%";
            String statusBayar = "";
            String kategoriInap = "";
            String status = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = "%" + bean.getNamaPasien() + "%";
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())) {
                jenisKelamin = bean.getJenisKelamin();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())) {
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())) {
                idRuang = bean.getIdRuang();
            }

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                idDetailCheckup = bean.getIdDetailCheckup();
            }

            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                dateFrom = bean.getStTglFrom();
            }

            if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())) {
                dateTo = bean.getStTglTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksa() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksa())) {
                jenisPeriksa = bean.getIdJenisPeriksa();
            }

            if (bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())) {
                statusBayar = "\n AND b.status_bayar = '" + bean.getStatusBayar() + "'\n";
            } else {
                statusBayar = "\n AND b.status_bayar is null \n";
            }

            if (bean.getTindakLanjut() != null && !"".equalsIgnoreCase(bean.getTindakLanjut())) {
                kategoriInap = "AND f.kategori = '" + bean.getTindakLanjut() + "' \n";
            }

            if (bean.getStatus() != null && !"".equalsIgnoreCase(bean.getStatus())) {
                status = bean.getStatus();
            }


            String SQL = "SELECT\n" +
                    "b.id_detail_checkup,\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien,\n" +
                    "a.nama,\n" +
                    "a.jalan,\n" +
                    "a.created_date,\n" +
                    "a.desa_id,\n" +
                    "b.status_periksa,\n" +
                    "c.keterangan,\n" +
                    "b.keterangan_selesai,\n" +
                    "d.id_rawat_inap,\n" +
                    "d.id_ruangan,\n" +
                    "e.no_ruangan,\n" +
                    "e.nama_ruangan,\n" +
                    "f.nama_kelas_ruangan,\n" +
                    "f.id_kelas_ruangan,\n" +
                    "b.no_sep,\n" +
                    "b.klaim_bpjs_flag, " +
                    "b.status_bayar, " +
                    "b.id_jenis_periksa_pasien,\n" +
                    "um.status_bayar AS status_bayar_uang_muka, \n" +
                    "um.id, " +
                    "um.id_detail_checkup, \n" +
                    "f.kategori, \n" +
                    "jenis.keterangan as jenis_pasien,\n" +
                    "a.tgl_lahir,\n"+
                    "b.tindak_lanjut \n"+
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_jenis_periksa_pasien jenis ON b.id_jenis_periksa_pasien = jenis.id_jenis_periksa_pasien\n" +
                    "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                    "INNER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON d.id_ruangan = tt.id_tempat_tidur \n"+
                    "INNER JOIN mt_simrs_ruangan e ON tt.id_ruangan = e.id_ruangan\n" +
                    "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                    "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = b.id_detail_checkup\n" +
                    "WHERE a.id_pasien LIKE :idPasien\n" +
                    "AND a.nama LIKE :nama\n" +
                    "AND b.id_pelayanan LIKE :idPelayanan\n" +
                    "AND b.status_periksa LIKE :status\n" +
                    "AND a.jenis_kelamin LIKE :jenisKelamin\n" +
                    "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND e.id_ruangan LIKE :idRuang\n" +
                    "AND b.id_detail_checkup LIKE :idDetailCheckup\n" +
                    "AND b.is_kronis IS NULL\n" +
                    "AND a.branch_id LIKE :branchId\n" +
                    "AND b.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
                    "AND d.status LIKE :status\n" +
                    "AND a.flag = 'Y'\n " + statusBayar + kategoriInap;

            List<Object[]> results = new ArrayList<>();

            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "\n AND CAST(d.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(d.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                        "\n ORDER BY b.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("jenisKelamin", jenisKelamin)
                        .setParameter("idKelas", idKelas)
                        .setParameter("idRuang", idRuang)
                        .setParameter("idDetailCheckup", idDetailCheckup)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .setParameter("jenisPeriksa", jenisPeriksa)
                        .setParameter("status", status)
                        .list();

            } else {

                if (!"".equalsIgnoreCase(bean.getStTglFrom())) {

                    SQL = SQL + "\n AND CAST(d.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("status", status)
                            .list();
                } else if (!"".equalsIgnoreCase(bean.getStTglTo())) {

                    SQL = SQL + "\n AND CAST(d.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("status", status)
                            .list();
                } else {

                    SQL = SQL + "\n  ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("status", status)
                            .list();
                }
            }

            if (!results.isEmpty()) {
                for (Object[] obj : results) {

                    RawatInap inap = new RawatInap();
                    inap.setStatusPeriksa(obj[7] == null ? "" : obj[7].toString());
                    inap.setStatusBayar(obj[20] == null ? "" : obj[20].toString());
                    inap.setIdJenisPeriksa(obj[19] == null ? "" : obj[19].toString());

                    RawatInap rawatInap = new RawatInap();

                    if(obj[21] != null){
                        if ("umum".equalsIgnoreCase(inap.getIdJenisPeriksa())) {
                            if ("Y".equalsIgnoreCase(inap.getStatusBayar())) {
                                rawatInap.setIsBayar("Y");
                            } else {
                                rawatInap.setIsBayar("N");
                            }
                        }
                    }else{
                        rawatInap.setIsBayar("Y");
                    }

                    rawatInap.setIdDetailCheckup(obj[0].toString());
                    rawatInap.setNoCheckup(obj[1].toString());
                    rawatInap.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    rawatInap.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                    String jalan = obj[4] == null ? "" : obj[4].toString();
                    if(obj[5] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[5]);
                        rawatInap.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        rawatInap.setFormatTglMasuk(formatDate);

                    }
                    rawatInap.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    rawatInap.setStatusPeriksa(obj[7].toString());
                    rawatInap.setStatusPeriksaName(obj[8].toString());
                    rawatInap.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    rawatInap.setIdRawatInap(obj[10].toString());
                    rawatInap.setIdRuangan(obj[11].toString());
                    rawatInap.setNoRuangan(obj[12].toString());
                    rawatInap.setNamaRangan(obj[13].toString());
                    rawatInap.setKelasRuanganName(obj[14].toString());
                    rawatInap.setIdKelas(obj[15].toString());
                    rawatInap.setNoSep(obj[16] == null ? "" : obj[16].toString());
                    rawatInap.setKlaimBpjsFlag(obj[17] == null ? "" : obj[17].toString());
                    rawatInap.setStatusBayar(obj[18] == null ? "" : obj[18].toString());
                    rawatInap.setIdJenisPeriksa(obj[19] == null ? "" : obj[19].toString());
                    rawatInap.setKategoriRuangan(obj[23] == null ? "" : obj[23].toString());
                    rawatInap.setJenisPeriksaPasien(obj[24] == null ? "" : obj[24].toString());
                    rawatInap.setTanggalLahir(obj[25] == null ? null : (java.sql.Date) obj[25]);
                    if(rawatInap.getTanggalLahir() != null){
                        rawatInap.setUmur(CommonUtil.calculateAge(rawatInap.getTanggalLahir(), true)+ " Tahun");
                    }
                    rawatInap.setTindakLanjut(obj[26] == null ? null : (String) obj[26]);

                    if (!"".equalsIgnoreCase(rawatInap.getDesaId())) {
                        List<Object[]> objDesaList = getListAlamatByDesaId(rawatInap.getDesaId());
                        if (!objDesaList.isEmpty()) {
                            for (Object[] objDesa : objDesaList) {

                                String alamatLengkap =
                                        "Desa. " + objDesa[0].toString() +
                                                " Kec. " + objDesa[1].toString() +
                                                " " + objDesa[2].toString() +
                                                " Prov. " + objDesa[3].toString();

                                if (!"".equalsIgnoreCase(jalan)) {
                                    jalan = jalan + ", " + alamatLengkap;
                                } else {
                                    jalan = alamatLengkap;
                                }

                                rawatInap.setDesa(objDesa[0].toString());
                                rawatInap.setKecamatan(objDesa[1].toString());
                            }
                        }
                    }
                    rawatInap.setAlamat(jalan);
                    rawatInapList.add(rawatInap);
                }
            }
        }
        return rawatInapList;
    }

    public List<RawatInap> getSearchVerifikasiRawatInap(RawatInap bean, String type) {
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String jenisKelamin = "%";
            String idKelas = "%";
            String idRuang = "%";
            String idDetailCheckup = "%";
            String dateFrom = "";
            String dateTo = "";
            String branchId = "%";
            String isKasir = "";
            String jenisPasien = "";

            if (!"".equalsIgnoreCase(type)) {
                jenisPasien = "AND b.id_jenis_periksa_pasien = '" + type + "' \n";
            } else {
                jenisPasien = "AND b.id_jenis_periksa_pasien IN ('bpjs', 'bpjs_rekanan') \n";
            }

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = "%" + bean.getNamaPasien() + "%";
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())) {
                jenisKelamin = bean.getJenisKelamin();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())) {
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())) {
                idRuang = bean.getIdRuang();
            }

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                idDetailCheckup = bean.getIdDetailCheckup();
            }

            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                dateFrom = bean.getStTglFrom();
            }

            if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())) {
                dateTo = bean.getStTglTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if ("Y".equalsIgnoreCase(bean.getIsKasir())) {
                if (bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())) {
                    isKasir = "AND b.status_bayar = '" + bean.getStatusBayar() + "' \n";
                } else {
                    isKasir = "AND b.status_bayar IS NULL \n" +
                            "AND b.flag_close_traksaksi = 'Y' \n" +
                            "AND b.flag_cover = 'Y' \n";
                }
            }

            String forSisaRekanan = "";

            if ("asuransi".equalsIgnoreCase(type) || "bpjs".equalsIgnoreCase(type)) {
                forSisaRekanan = "\n AND b.flag_sisa = 'Y' \n";
            }

            String SQL = "SELECT\n" +
                    "b.id_detail_checkup,\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien,\n" +
                    "a.nama,\n" +
                    "a.jalan,\n" +
                    "a.created_date,\n" +
                    "a.desa_id,\n" +
                    "b.status_periksa,\n" +
                    "c.keterangan,\n" +
                    "b.keterangan_selesai,\n" +
                    "d.id_rawat_inap,\n" +
                    "d.id_ruangan,\n" +
                    "e.no_ruangan,\n" +
                    "e.nama_ruangan,\n" +
                    "f.nama_kelas_ruangan,\n" +
                    "f.id_kelas_ruangan,\n" +
                    "b.no_sep, " +
                    "b.klaim_bpjs_flag, " +
                    "b.status_bayar, " +
                    "b.id_jenis_periksa_pasien\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                    "INNER JOIN (" +
                    "SELECT a.* FROM (\n" +
                    "SELECT *, \n" +
                    " rank() OVER (PARTITION BY id_detail_checkup ORDER BY created_date ASC)\n" +
                    " FROM it_simrs_rawat_inap\n" +
                    ")a WHERE a.rank = 1" +
                    ") d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON d.id_ruangan = tt.id_tempat_tidur \n"+
                    "INNER JOIN mt_simrs_ruangan e ON tt.id_ruangan = e.id_ruangan\n" +
                    "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                    "WHERE a.id_pasien LIKE :idPasien\n" +
                    "AND a.nama LIKE :nama\n" +
                    "AND b.id_pelayanan LIKE :idPelayanan\n" +
                    "AND b.status_periksa LIKE :status\n" +
                    "AND a.jenis_kelamin LIKE :jenisKelamin\n" +
                    "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND e.id_ruangan LIKE :idRuang\n" +
                    "AND b.id_detail_checkup LIKE :idDetailCheckup\n" +
                    "AND b.is_kronis IS NULL\n" +
                    "AND a.branch_id LIKE :branchId\n" + jenisPasien + isKasir + forSisaRekanan +
                    "AND a.flag = 'Y'\n";

            List<Object[]> results = new ArrayList<>();

            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                        "\n ORDER BY b.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("jenisKelamin", jenisKelamin)
                        .setParameter("idKelas", idKelas)
                        .setParameter("idRuang", idRuang)
                        .setParameter("idDetailCheckup", idDetailCheckup)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .list();

            } else {

                if (!"".equalsIgnoreCase(bean.getStTglFrom())) {

                    SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("branchId", branchId)
                            .list();
                } else if (!"".equalsIgnoreCase(bean.getStTglTo())) {

                    SQL = SQL + "\n AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .list();
                } else {

                    SQL = SQL + "\n  ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("branchId", branchId)
                            .list();
                }
            }

            if (!results.isEmpty()) {
                for (Object[] obj : results) {

                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setIdDetailCheckup(obj[0].toString());
                    rawatInap.setNoCheckup(obj[1].toString());
                    rawatInap.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    rawatInap.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                    String jalan = obj[4] == null ? "" : obj[4].toString();

                    rawatInap.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                    rawatInap.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    rawatInap.setStatusPeriksa(obj[7].toString());
                    rawatInap.setStatusPeriksaName(obj[8].toString());
                    rawatInap.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    rawatInap.setIdRawatInap(obj[10].toString());
                    rawatInap.setIdRuangan(obj[11].toString());
                    rawatInap.setNoRuangan(obj[12].toString());
                    rawatInap.setNamaRangan(obj[13].toString());
                    rawatInap.setKelasRuanganName(obj[14].toString());
                    rawatInap.setIdKelas(obj[15].toString());
                    rawatInap.setNoSep(obj[16] == null ? "" : obj[16].toString());
                    rawatInap.setKlaimBpjsFlag(obj[17] == null ? "" : obj[17].toString());
                    rawatInap.setStatusBayar(obj[18] == null ? "" : obj[18].toString());
                    rawatInap.setIdJenisPeriksa(obj[19] == null ? "" : obj[19].toString());

                    if (!"".equalsIgnoreCase(rawatInap.getDesaId())) {
                        List<Object[]> objDesaList = getListAlamatByDesaId(rawatInap.getDesaId());
                        if (!objDesaList.isEmpty()) {
                            for (Object[] objDesa : objDesaList) {

                                String alamatLengkap =
                                        "Desa. " + objDesa[0].toString() +
                                                " Kec. " + objDesa[1].toString() +
                                                " " + objDesa[2].toString() +
                                                " Prov. " + objDesa[3].toString();

                                if (!"".equalsIgnoreCase(jalan)) {
                                    jalan = jalan + ", " + alamatLengkap;
                                } else {
                                    jalan = alamatLengkap;
                                }

                                rawatInap.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                rawatInap.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());
                            }
                        }
                    }

                    rawatInap.setCekApprove(cekApproveFlag(obj[0].toString()));
                    rawatInap.setAlamat(jalan);
                    rawatInapList.add(rawatInap);
                }
            }
        }

        return rawatInapList;
    }

    public Boolean cekApproveFlag(String idDetail) {

        Boolean cek = false;

        String SQL = "SELECT id_detail_checkup, approve_bpjs_flag\n" +
                "FROM it_simrs_riwayat_tindakan\n" +
                "WHERE id_detail_checkup LIKE :id ";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetail)
                .list();

        if (results != null) {
            for (Object[] obj : results) {
                if (obj[1] == null || "".equalsIgnoreCase(obj[1].toString())) {
                    cek = true;
                }
            }
        } else {
            cek = null;
        }

        return cek;
    }

    public List<Object[]> getListAlamatByDesaId(String desaId) {
        String SQL = "SELECT \n" +
                "ds.desa_name, \n" +
                "kec.kecamatan_name,\n" +
                "kot.kota_name,\n" +
                "prov.provinsi_name\n" +
                "FROM \n" +
                "im_hris_desa ds\n" +
                "INNER JOIN im_hris_kecamatan kec ON kec.kecamatan_id = ds.kecamatan_id\n" +
                "INNER JOIN im_hris_kota kot ON kot.kota_id = kec.kota_id\n" +
                "INNER JOIN im_hris_provinsi prov ON prov.provinsi_id = kot.provinsi_id\n" +
                "WHERE ds.desa_id = :id ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", desaId)
                .list();

        return results;
    }

    public RawatInap getLastRuanganById(String idDetail) {

        String SQL = "SELECT\n" +
                "id_detail_checkup,\n" +
                "id_ruangan\n" +
                "FROM\n" +
                "it_simrs_rawat_inap\n" +
                "WHERE id_detail_checkup = :idDetail \n" +
                "ORDER BY created_date LIMIT 1";

        List<Object[]> objects = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .list();

        RawatInap rawatInap = new RawatInap();
        if (objects.size() > 0) {
            for (Object[] obj : objects) {
                rawatInap.setIdDetailCheckup(obj[0].toString());
                rawatInap.setIdRuang(obj[1].toString());
            }
            return rawatInap;
        }

        return null;
    }

    public List<RawatInap> getListTppri(RawatInap bean) {
        List<RawatInap> response = new ArrayList<>();
        if (bean != null) {
            String idPasien = "";
            String nama = "";
            String idDetailCheckup = "";
            String branchId = "";
            String tgl = "";
            String flag = "";

            if (bean.getFlagTppri() != null && !"".equalsIgnoreCase(bean.getFlagTppri())) {
                flag = "AND b.flag_tppri = '" + bean.getFlagTppri() + "' \n";
            }else{
                flag = "AND b.flag_tppri IS NULL \n";
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = "AND a.id_pasien LIKE '%" + bean.getIdPasien() + "%' \n";
            }
            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = "AND a.nama ILIKE '%" + bean.getNamaPasien() + "%' \n";
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                idDetailCheckup = "AND b.id_detail_checkup LIKE '%" + bean.getIdDetailCheckup() + "%' \n";
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = "AND a.branch_id LIKE '" + bean.getBranchId() + "'";
            } else {
                branchId = "AND a.branch_id LIKE '%'";
            }

            if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo()) &&
                    bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                tgl = "AND CAST(a.created_date AS date) >= to_date('" + bean.getStTglFrom() + "', 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date('" + bean.getStTglTo() + "', 'dd-MM-yyyy') \n";
            } else if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())) {
                tgl = "AND CAST(a.created_date AS date) <= to_date('" + bean.getStTglTo() + "', 'dd-MM-yyyy') \n";
            } else if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                tgl = "AND CAST(a.created_date AS date) >= to_date('" + bean.getStTglFrom() + "', 'dd-MM-yyyy') \n";
            }

            String SQL = "SELECT\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien,\n" +
                    "a.nama,\n" +
                    "b.id_detail_checkup,\n" +
                    "b.keterangan_selesai,\n" +
                    "b.tindak_lanjut,\n" +
                    "b.catatan,\n" +
                    "a.tgl_keluar,\n" +
                    "c.keterangan,\n" +
                    "b.metode_pembayaran, \n" +
                    "b.id_jenis_periksa_pasien,\n" +
                    "a.created_date,\n" +
                    "b.flag_tppri\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_jenis_periksa_pasien c ON b.id_jenis_periksa_pasien = c.id_jenis_periksa_pasien\n" +
                    "LEFT JOIN it_simrs_rawat_inap d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "WHERE b.status_periksa = '3'\n" +
                    "AND b.tindak_lanjut IN ('rawat_inap','rawat_intensif','rawat_isolasi','kamar_operasi','ruang_bersalin')\n" +
                    "AND d.id_detail_checkup IS NULL \n" +
                    flag + branchId + idPasien + nama + idDetailCheckup + tgl;

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();
            if (result.size() > 0) {
                for (Object[] obj : result) {
                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setNoCheckup(obj[0] == null ? null : obj[0].toString());
                    rawatInap.setIdPasien(obj[1] == null ? null : obj[1].toString());
                    rawatInap.setNamaPasien(obj[2] == null ? null : obj[2].toString());
                    rawatInap.setIdDetailCheckup(obj[3] == null ? null : obj[3].toString());
                    rawatInap.setKeteranganSelesai(obj[4] == null ? null : obj[4].toString());
                    rawatInap.setTindakLanjut(obj[5] == null ? null : obj[5].toString());
                    rawatInap.setCatatan(obj[6] == null ? null : obj[6].toString());
                    rawatInap.setTglKeluar(obj[7] == null ? null : (Timestamp) obj[7]);
                    rawatInap.setJenisPeriksaPasien(obj[8] == null ? null : obj[8].toString());
                    rawatInap.setMetodePembayaran(obj[9] == null ? null : obj[9].toString());
                    rawatInap.setIdJenisPeriksa(obj[10] == null ? null : obj[10].toString());
                    rawatInap.setCreatedDate(obj[11] == null ? null : (Timestamp) obj[11]);
                    if(obj[11] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp) obj[11]);
                        rawatInap.setFormatTglMasuk(formatDate);

                    }
                    rawatInap.setFlagTppri(obj[12] == null ? null : (String) obj[12]);
                    response.add(rawatInap);
                }
            }
        }
        return response;
    }

    public List<RawatInap> getRuanganRawatInap(RawatInap bean) {
        List<RawatInap> rawatInapList = new ArrayList<>();
        String idRawatInap = "";
        String idDetilCheckup = "";
        if (bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())) {
            idRawatInap = "AND a.id_rawat_inap = '" + bean.getIdRawatInap() + "'";
        }
        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            idDetilCheckup = "AND a.id_detail_checkup = '" + bean.getIdDetailCheckup() + "'";
        }
        String SQL = "SELECT \n" +
                "a.id_rawat_inap,\n" +
                "a.id_detail_checkup,\n" +
                "b.id_ruangan,\n" +
                "b.no_ruangan,\n" +
                "b.nama_ruangan,\n" +
                "c.kategori,\n" +
                "c.id_kelas_ruangan,\n" +
                "c.nama_kelas_ruangan,\n" +
                "a.status,\n" +
                "a.tgl_masuk,\n" +
                "a.tgl_keluar,\n" +
                "a.keterangan,\n" +
                "tt.id_tempat_tidur, \n" +
                "tt.nama_tempat_tidur\n" +
                "FROM it_simrs_rawat_inap a\n" +
                "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON a.id_ruangan = tt.id_tempat_tidur\n" +
                "INNER JOIN mt_simrs_ruangan b ON tt.id_ruangan = b.id_ruangan\n" +
                "INNER JOIN im_simrs_kelas_ruangan c ON b.id_kelas_ruangan = c.id_kelas_ruangan\n" +
                "WHERE a.flag = 'Y' \n" + idRawatInap + "\n" +idDetilCheckup +
                "ORDER BY a.created_date ASC";

        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();
        if (result.size() > 0) {
            for (Object[] objects : result) {
                RawatInap rawatInap = new RawatInap();
                rawatInap.setIdRawatInap(objects[0] == null ? null : objects[0].toString());
                rawatInap.setIdDetailCheckup(objects[1] == null ? null : objects[1].toString());
                rawatInap.setIdRuangan(objects[2] == null ? null : objects[2].toString());
                rawatInap.setNoRuangan(objects[3] == null ? null : objects[3].toString());
                rawatInap.setNamaRangan(objects[4] == null ? null : objects[4].toString());
                rawatInap.setKategoriRuangan(objects[5] == null ? null : objects[5].toString());
                rawatInap.setIdKelasRuangan(objects[6] == null ? null : objects[6].toString());
                rawatInap.setKelasRuanganName(objects[7] == null ? null : objects[7].toString());
                rawatInap.setStatus(objects[8] == null ? null : objects[8].toString());
                rawatInap.setTglMasuk(objects[9] == null ? null : (Timestamp) objects[9]);
                rawatInap.setTglKeluar(objects[10] == null ? null : (Timestamp) objects[10]);
                rawatInap.setKeterangan(objects[11] == null ? null : objects[11].toString());
                rawatInap.setIdTempatTidur(objects[12] == null ? null : objects[12].toString());
                rawatInap.setNamaTempatTidur(objects[13] == null ? null : objects[13].toString());
                rawatInapList.add(rawatInap);
            }
        }
        return rawatInapList;
    }

    public List<RawatInap> getRuanganRawatInap(String idDetailCheckup) {
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            String SQL = "SELECT \n" +
                    "a.id_rawat_inap,\n" +
                    "a.id_detail_checkup,\n" +
                    "a.id_ruangan,\n" +
                    "a.nama_ruangan,\n" +
                    "a.created_date,\n" +
                    "a.tgl_masuk, \n" +
                    "a.tgl_keluar,\n" +
                    "CAST(DATE_PART('day', a.tgl_keluar - a.tgl_masuk) + 1 AS BIGINT) as hari, \n" +
                    "b.tarif\n" +
                    "FROM it_simrs_rawat_inap a\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON a.id_ruangan = tt.id_tempat_tidur\n" +
                    "INNER JOIN mt_simrs_ruangan b ON tt.id_ruangan = b.id_ruangan\n" +
                    "WHERE a.status = '3'\n" +
                    "AND a.tgl_keluar IS NOT NULL\n" +
                    "AND a.id_detail_checkup = :id ";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if (result.size() > 0) {
                for (Object[] obj : result) {
                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setIdRawatInap(obj[0] == null ? null : obj[0].toString());
                    rawatInap.setIdDetailCheckup(obj[1] == null ? null : obj[1].toString());
                    rawatInap.setIdRuangan(obj[2] == null ? null : obj[2].toString());
                    rawatInap.setNamaRangan(obj[3] == null ? null : obj[3].toString());
                    rawatInap.setCreatedDate(obj[4] == null ? null : (Timestamp) obj[4]);
                    rawatInap.setTglMasuk(obj[5] == null ? null : (Timestamp) obj[5]);
                    rawatInap.setTglKeluar(obj[6] == null ? null : (Timestamp) obj[6]);
                    rawatInap.setLamakamar(obj[7] == null ? null : (BigInteger) obj[7]);
                    rawatInap.setTarif(obj[8] == null ? null : (BigInteger) obj[8]);
                    rawatInapList.add(rawatInap);
                }
            }
        }
        return rawatInapList;
    }

    public List<UangMuka> getAllListUangMuka(UangMuka bean) {
        List<UangMuka> uangMukaArrayList = new ArrayList<>();
        if(bean != null){
            String condition = "";
            if(bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                condition = "AND b.id_detail_checkup = '"+bean.getIdDetailCheckup()+"' \n";
            }
            if(bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())){
                condition = condition + " AND a.no_checkup = '"+bean.getNoCheckup()+"' \n";
            }
            if (!"".equalsIgnoreCase(condition)) {
                String SQL = "SELECT\n" +
                        "a.no_checkup,\n" +
                        "b.id_detail_checkup,\n" +
                        "c.id,\n" +
                        "c.created_date,\n" +
                        "c.status_bayar,\n" +
                        "d.nama_pelayanan,\n" +
                        "c.jumlah,\n" +
                        "c.jumlah_dibayar \n"+
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN it_simrs_uang_muka_pendaftaran c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                        "INNER JOIN (SELECT\n" +
                        "a.id_pelayanan,\n" +
                        "a.branch_id,\n" +
                        "b.nama_pelayanan,\n" +
                        "b.tipe_pelayanan,\n" +
                        "b.kategori_pelayanan,\n" +
                        "b.divisi_id,\n" +
                        "b.kode_vclaim\n" +
                        "FROM im_simrs_pelayanan a\n" +
                        "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) d ON b.id_pelayanan = d.id_pelayanan\n" +
                        "WHERE c.flag = 'Y' " + condition +
                        "ORDER BY c.created_date ASC";

                List<Object[]> result = new ArrayList<>();
                result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .list();
                if (result.size() > 0) {
                    for (Object[] obj : result) {
                        UangMuka uangMuka = new UangMuka();
                        uangMuka.setNoCheckup(obj[0] == null ? null : obj[0].toString());
                        uangMuka.setIdDetailCheckup(obj[1] == null ? null : obj[1].toString());
                        uangMuka.setId(obj[2] == null ? null : obj[2].toString());
                        uangMuka.setCreatedDate(obj[3] == null ? null : (Timestamp) obj[3]);
                        uangMuka.setStatusBayar(obj[4] == null ? null : (String) obj[4]);
                        uangMuka.setNamaPelayanan(obj[5] == null ? null : (String) obj[5]);
                        uangMuka.setJumlah(obj[6] == null ? null : (BigInteger) obj[6]);
                        uangMuka.setDibayar(obj[7] == null ? null : (BigInteger) obj[7]);
                        uangMukaArrayList.add(uangMuka);
                    }
                }
            }
        }
        return uangMukaArrayList;
    }

    public HeaderCheckup getDetailGelang(String noCheckup) {
        HeaderCheckup headerCheckup = new HeaderCheckup();
        if(noCheckup != null){
            String SQL = "SELECT\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien,\n" +
                    "b.id_detail_checkup,\n" +
                    "a.nama,\n" +
                    "a.tgl_lahir\n" +
                    "FROM it_simrs_header_checkup a \n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN it_simrs_rawat_inap c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                    "WHERE a.no_checkup = :id \n";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", noCheckup)
                    .list();
            if (result.size() > 0) {
                Object[] obj = result.get(0);
                headerCheckup.setNoCheckup(obj[0] != null ? obj[0].toString() : null);
                headerCheckup.setIdPasien(obj[1] != null ? obj[1].toString() : null);
                headerCheckup.setIdDetailCheckup(obj[2] != null ? obj[2].toString() : null);
                headerCheckup.setNama(obj[3] != null ? obj[3].toString() : null);
                if(obj[4] != null){
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy").format((Date) obj[4]);
                    headerCheckup.setStTglLahir(formatDate);
                }
            }
        }
        return headerCheckup;
    }
}
