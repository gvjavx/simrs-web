package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class CheckupDetailDao extends GenericDao<ItSimrsHeaderDetailCheckupEntity, String> {
    @Override
    protected Class<ItSimrsHeaderDetailCheckupEntity> getEntityClass() {
        return ItSimrsHeaderDetailCheckupEntity.class;
    }

    @Override
    public List<ItSimrsHeaderDetailCheckupEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderDetailCheckupEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_checkup") != null)
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            if (mapCriteria.get("no_checkup") != null)
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            if (mapCriteria.get("id_pelayanan") != null)
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            if (mapCriteria.get("status_periksa") != null)
                criteria.add(Restrictions.eq("statusPeriksa", mapCriteria.get("status_periksa").toString()));
            if (mapCriteria.get("status_bayar") != null)
                criteria.add(Restrictions.eq("statusBayar", mapCriteria.get("status_bayar").toString()));
            if (mapCriteria.get("branch_id") != null)
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            if (mapCriteria.get("today") != null)
                criteria.add(Restrictions.eq("createdDate", (Date) mapCriteria.get("today")));
        }

        criteria.addOrder(Order.asc("tglAntrian"));
        List<ItSimrsHeaderDetailCheckupEntity> result = criteria.list();
        return result;
    }

    public List<HeaderDetailCheckup> getSearchRawatJalan(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();
        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String branchId = "%";

            String dateFrom = "";
            String dateTo = "";

            String jenisPasien = "%";
            String statusBayar = "";

            String notLike = "";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = bean.getNamaPasien();
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                jenisPasien = bean.getIdJenisPeriksaPasien();
            }

            if (bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())) {
                statusBayar = "\n AND dt.status_bayar = '" + bean.getStatusBayar() + "'\n";
            } else {
                statusBayar = "\n AND dt.status_bayar is null \n";
            }

//            if(bean.getNotLike() != null && !"".equalsIgnoreCase(bean.getNotLike())){
//                notLike = "\n AND hd.id_jenis_periksa_pasien NOT LIKE '"+bean.getNotLike()+"' \n";
//            }

            String uangMukaNotNull = "";
//            if ("0".equalsIgnoreCase(statusPeriksa)){
//                uangMukaNotNull = "\n AND um.id_detail_checkup is not null AND um.status_bayar = 'Y'";
//            }

            String SQL = "\n" +
                    "SELECT \n" +
                    "dt.id_detail_checkup,\n" +
                    "hd.no_checkup,\n" +
                    "hd.id_pasien,\n" +
                    "hd.nama,\n" +
                    "hd.jalan,\n" +
                    "hd.created_date,\n" +
                    "hd.desa_id,\n" +
                    "dt.status_periksa,\n" +
                    "st.keterangan,\n" +
                    "dt.keterangan_selesai,\n" +
                    "dt.klaim_bpjs_flag, \n" +
                    "dt.status_bayar, \n" +
                    "um.status_bayar AS status_bayar_uang_muka, \n" +
                    "hd.id_jenis_periksa_pasien, um.id, um.id_detail_checkup\n" +
                    "FROM \n" +
                    "it_simrs_header_checkup hd\n" +
                    "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                    "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                    "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = dt.id_detail_checkup\n" +
                    "WHERE ri.id_detail_checkup is null\n" +
                    "AND hd.branch_id LIKE :branchId \n" +
                    "AND hd.id_pasien LIKE :idPasien \n" +
                    "AND hd.nama LIKE :nama \n" +
                    "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                    "AND hd.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                    "AND dt.is_kronis IS NULL \n" +
                    "AND dt.status_periksa LIKE :status " + statusBayar;


            String order = "\n ORDER BY dt.tgl_antrian ASC";
            if ("kasir".equalsIgnoreCase(bean.getTypeTransaction())) {
                order = "\n ORDER BY dt.last_update DESC";
            }

            List<Object[]> results = new ArrayList<>();
            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "\n AND CAST(hd.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(hd.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" + order;


                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .setParameter("jenisPasien", jenisPasien)
                        .list();

            } else {

                SQL = SQL + order;

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("jenisPasien", jenisPasien)
                        .setParameter("branchId", branchId)
                        .setParameter("status", statusPeriksa)
                        .list();
            }

            if (!results.isEmpty()) {
                for (Object[] obj : results) {

                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

                    detailCheckup.setStatusPeriksa(obj[7] == null ? "" : obj[7].toString());
                    detailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());
                    detailCheckup.setStatusBayar(obj[12] == null ? "" : obj[12].toString());

                    if ("0".equalsIgnoreCase(detailCheckup.getStatusPeriksa()) || "1".equalsIgnoreCase(detailCheckup.getStatusPeriksa())) {

                        if ("umum".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

                            if (bean.getStatusBayar() != null) {
                                HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                                headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                                headerDetailCheckup.setNoCheckup(obj[1].toString());
                                headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                                headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                                String jalan = obj[4] == null ? "" : obj[4].toString();

                                headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                                headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                                headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                                headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                                headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                                headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                                headerDetailCheckup.setStatusBayar(obj[11] == null ? "" : obj[11].toString());
                                headerDetailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());

                                if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                                    List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                            headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                            headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                        }
                                    }
                                }

                                headerDetailCheckup.setAlamat(jalan);
                                checkupList.add(headerDetailCheckup);

                            } else {

                                if ("Y".equalsIgnoreCase(detailCheckup.getStatusBayar())) {

                                    HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                                    headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                                    headerDetailCheckup.setNoCheckup(obj[1].toString());
                                    headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                                    headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                                    String jalan = obj[4] == null ? "" : obj[4].toString();

                                    headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                                    headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                                    headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                                    headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                                    headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                                    headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                                    headerDetailCheckup.setStatusBayar(obj[11] == null ? "" : obj[11].toString());
                                    headerDetailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());

                                    if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                                        List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                                headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                                headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                            }
                                        }
                                    }

                                    headerDetailCheckup.setAlamat(jalan);
                                    checkupList.add(headerDetailCheckup);
                                }
                            }
                        } else {
                            HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                            headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                            headerDetailCheckup.setNoCheckup(obj[1].toString());
                            headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                            headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                            String jalan = obj[4] == null ? "" : obj[4].toString();

                            headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                            headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                            headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                            headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                            headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                            headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                            headerDetailCheckup.setStatusBayar(obj[11] == null ? "" : obj[11].toString());
                            headerDetailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());

                            if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                                List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                        headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                        headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                    }
                                }
                            }

                            headerDetailCheckup.setAlamat(jalan);
                            checkupList.add(headerDetailCheckup);
                        }
                    } else {

                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                        headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                        headerDetailCheckup.setNoCheckup(obj[1].toString());
                        headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                        headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                        String jalan = obj[4] == null ? "" : obj[4].toString();

                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                        headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                        headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                        headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                        headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                        headerDetailCheckup.setStatusBayar(obj[11] == null ? "" : obj[11].toString());
                        headerDetailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());

                        if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                            List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                    headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                    headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                }
                            }
                        }

                        headerDetailCheckup.setAlamat(jalan);
                        checkupList.add(headerDetailCheckup);
                    }
                }
            }
        }
        return checkupList;
    }

    public List<HeaderDetailCheckup> getSearchVerifikasiRawatJalan(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();
        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String branchId = "%";

            String dateFrom = "";
            String dateTo = "";

            String jenisPasien = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = bean.getNamaPasien();
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                jenisPasien = bean.getIdJenisPeriksaPasien();
            }

            String SQL = "\n" +
                    "SELECT \n" +
                    "dt.id_detail_checkup,\n" +
                    "hd.no_checkup,\n" +
                    "hd.id_pasien,\n" +
                    "hd.nama,\n" +
                    "hd.jalan,\n" +
                    "hd.created_date,\n" +
                    "hd.desa_id,\n" +
                    "dt.status_periksa,\n" +
                    "st.keterangan,\n" +
                    "dt.keterangan_selesai,\n" +
                    "dt.klaim_bpjs_flag, dt.status_bayar, dt.no_sep\n" +
                    "FROM \n" +
                    "it_simrs_header_checkup hd\n" +
                    "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                    "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                    "WHERE ri.id_detail_checkup is null\n" +
                    "AND hd.branch_id LIKE :branchId \n" +
                    "AND hd.id_pasien LIKE :idPasien \n" +
                    "AND hd.nama LIKE :nama \n" +
                    "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                    "AND dt.is_kronis IS NULL \n" +
                    "AND hd.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                    "AND dt.status_periksa LIKE :status";

            List<Object[]> results = new ArrayList<>();
            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "\n AND CAST(hd.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(hd.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                        "\n ORDER BY dt.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("jenisPasien", jenisPasien)
                        .setParameter("branchId", branchId)
                        .list();

            } else {

                SQL = SQL + "\n  ORDER BY dt.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("jenisPasien", jenisPasien)
                        .setParameter("branchId", branchId)
                        .list();
            }

            if (!results.isEmpty()) {
                HeaderDetailCheckup headerDetailCheckup;
                for (Object[] obj : results) {
                    headerDetailCheckup = new HeaderDetailCheckup();
                    headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                    headerDetailCheckup.setNoCheckup(obj[1].toString());
                    headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                    String jalan = obj[4] == null ? "" : obj[4].toString();

                    headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                    headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                    headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                    headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                    headerDetailCheckup.setNoSep(obj[12] == null ? "" : obj[12].toString());

                    if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                        List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                            }
                        }
                    }

                    headerDetailCheckup.setCekApprove(cekApproveFlag(obj[0].toString()));
                    headerDetailCheckup.setAlamat(jalan);
                    checkupList.add(headerDetailCheckup);
                }
            }
        }
        return checkupList;
    }

    public List<HeaderDetailCheckup> getSearchFPK(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();
        if (bean != null) {

            String idPelayanan = "%";
            String branchId = "%";
            String dateFrom = "";
            String dateTo = "";
            String statusBayar = "";
            String noFpk = "";

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())) {
                statusBayar = "\n AND dt.status_bayar = '" + bean.getStatusBayar() + "'\n";
            } else {
                statusBayar = "\n AND dt.status_bayar is null \n";
            }

            if (bean.getNoFpk() != null && !"".equalsIgnoreCase(bean.getNoFpk())) {
                noFpk = "\n AND fp.no_fpk = '" + bean.getNoFpk() + "'\n";
            } else {
                noFpk = "\n AND fp.no_fpk is null \n";
            }

            String SQL = "SELECT\n" +
                    "dt.id_detail_checkup,\n" +
                    "hd.no_checkup,\n" +
                    "hd.id_pasien,\n" +
                    "hd.nama,\n" +
                    "hd.created_date,\n" +
                    "hd.desa_id,\n" +
                    "dt.status_periksa,\n" +
                    "st.keterangan,\n" +
                    "dt.keterangan_selesai,\n" +
                    "dt.klaim_bpjs_flag,\n" +
                    "dt.status_bayar,\n" +
                    "hd.id_jenis_periksa_pasien,\n" +
                    "dt.no_sep,\n" +
                    "fp.no_fpk,\n" +
                    "fp.id_fpk,\n" +
                    "fp.status_bayar AS bayar_bpjs\n" +
                    "FROM\n" +
                    "it_simrs_header_checkup hd\n" +
                    "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                    "LEFT JOIN it_simrs_fpk fp ON dt.id_detail_checkup = fp.id_detail_checkup\n" +
                    "WHERE hd.branch_id LIKE :branchId\n" +
                    "AND dt.id_pelayanan LIKE :idPelayanan\n" +
                    "AND hd.id_jenis_periksa_pasien LIKE 'bpjs'\n" +
                    "AND dt.status_periksa LIKE '3'\n" +
                    "AND dt.klaim_bpjs_flag = 'Y'" + statusBayar + noFpk;

            List<Object[]> results = new ArrayList<>();
            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "\n AND CAST(hd.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(hd.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')";


                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .list();

            } else {

                SQL = SQL;

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("branchId", branchId)
                        .list();
            }

            if (!results.isEmpty()) {
                for (Object[] obj : results) {

                    HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                    headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                    headerDetailCheckup.setNoCheckup(obj[1].toString());
                    headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                    headerDetailCheckup.setCreatedDate(obj[4] == null ? null : (Timestamp) obj[4]);
                    headerDetailCheckup.setDesaId(obj[5] == null ? "" : obj[5].toString());
                    headerDetailCheckup.setStatusPeriksa(obj[6].toString());
                    headerDetailCheckup.setStatusPeriksaName(obj[7].toString());
                    headerDetailCheckup.setKeteranganSelesai(obj[8] == null ? "" : obj[8].toString());
                    headerDetailCheckup.setKlaimBpjsFlag(obj[9] == null ? "" : obj[9].toString());
                    headerDetailCheckup.setStatusBayar(obj[10] == null ? "" : obj[10].toString());
                    headerDetailCheckup.setIdJenisPeriksaPasien(obj[11] == null ? "" : obj[11].toString());
                    headerDetailCheckup.setNoSep(obj[12] == null ? "" : obj[12].toString());
                    headerDetailCheckup.setNoFpk(obj[13] == null ? "" : obj[13].toString());
                    headerDetailCheckup.setIdFpk(obj[14] == null ? "" : obj[14].toString());
                    headerDetailCheckup.setStatusFPK(obj[15] == null ? "" : obj[15].toString());

                    if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                        List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
                        if (!objDesaList.isEmpty()) {
                            for (Object[] objDesa : objDesaList) {

                                headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());
                                headerDetailCheckup.setKota(objDesa[1] == null ? "" : objDesa[1].toString());
                                headerDetailCheckup.setProvinsi(objDesa[1] == null ? "" : objDesa[1].toString());

                            }
                        }
                    }

                    checkupList.add(headerDetailCheckup);
                }
            }
        }
        return checkupList;
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

//    public BigInteger sumOfTindakanByNoCheckup(String noCheckup){
//
//        String SQL = "SELECT ck.no_checkup, SUM(tin.tarif_total) as total_tarif\n" +
//                "FROM it_simrs_header_checkup ck\n" +
//                "INNER JOIN it_simrs_header_detail_checkup dc ON dc.no_checkup = ck.no_checkup\n" +
//                "INNER JOIN (SELECT * FROM it_simrs_tindakan_rawat WHERE flag = 'Y') tin ON tin.id_detail_checkup = dc.id_detail_checkup\n" +
//                "WHERE ck.no_checkup = :noCheckup\n" +
//                "GROUP BY ck.no_checkup";
//
//        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
//                .setParameter("noCheckup", noCheckup)
//                .list();
//
//        BigInteger result = new BigInteger(String.valueOf(0));
//        if (results.size() > 0){
//            for (Object[] obj : results){
//                if (obj[1] != null){
//                    result = new BigInteger(obj[1].toString());
//                }
//            }
//        }
//
//        return result;
//    }

    public BigInteger sumOfTindakanByNoCheckup(String idDetailCheckup) {

        String SQL = "SELECT b.id_detail_checkup, SUM(a.tarif_total) as total_tarif \n" +
                "FROM it_simrs_tindakan_rawat a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "WHERE b.id_detail_checkup LIKE :idDetail\n" +
                "GROUP BY b.id_detail_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetailCheckup)
                .list();

        BigInteger result = new BigInteger(String.valueOf(0));
        if (results.size() > 0) {
            for (Object[] obj : results) {
                if (obj[1] != null) {
                    result = new BigInteger(obj[1].toString());
                }
            }
        }

        return result;
    }

    public List<HeaderDetailCheckup> getListPembayaranUangMuka(HeaderDetailCheckup bean) {

        String idUangMuka = "%";
        String idDetail = "%";
        String idPelayanan = "%";
        String statusBayar = "";
        String branchId = "%";

        String dateFrom = "";
        String dateTo = "";

        if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
            idDetail = bean.getIdDetailCheckup();
        }

        if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
            idPelayanan = bean.getIdPelayanan();
        }

        if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
            branchId = bean.getBranchId();
        }

        if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
            dateFrom = bean.getStDateFrom();
        }

        if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
            dateTo = bean.getStDateTo();
        }


        if ("Y".equalsIgnoreCase(bean.getStatusBayar())) {
            statusBayar = "\n AND um.status_bayar = '" + bean.getStatusBayar() + "'";
        } else {
            statusBayar = "\n AND um.status_bayar is null";
        }

        String SQL = "SELECT\n" +
                "um.id,\n" +
                "dt.id_detail_checkup,\n" +
                "ps.nama,\n" +
                "pel.nama_pelayanan,\n" +
                "um.jumlah,\n" +
                "um.status_bayar,\n" +
                "um.created_date, ps.id_pasien,\n" +
                "um.jumlah_dibayar,\n" +
                "um.flag_refund\n" +
                "FROM it_simrs_header_detail_checkup dt\n" +
                "INNER JOIN it_simrs_header_checkup ck ON ck.no_checkup = dt.no_checkup\n" +
                "INNER JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = dt.id_detail_checkup\n" +
                "INNER JOIN im_simrs_pasien ps ON ps.id_pasien = ck.id_pasien\n" +
                "INNER JOIN im_simrs_pelayanan pel ON pel.id_pelayanan = dt.id_pelayanan\n" +
                "WHERE dt.id_detail_checkup LIKE :idDetail\n" +
                "AND dt.id_pelayanan LIKE :idPoli\n" +
                "AND ck.branch_id LIKE :branchId\n" +
                "AND um.id LIKE :idUang" + statusBayar;

        List<Object[]> resuts = new ArrayList<>();

        String order = "\n ORDER BY um.created_date DESC";

        if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

            SQL = SQL + "\n AND CAST(ck.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(ck.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" + order;


            resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetail)
                    .setParameter("idPoli", idPelayanan)
                    .setParameter("idUang", idUangMuka)
                    .setParameter("dateTo", dateTo)
                    .setParameter("dateFrom", dateFrom)
                    .setParameter("branchId", branchId)
                    .list();

        } else {
            SQL = SQL + order;

            resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetail)
                    .setParameter("idPoli", idPelayanan)
                    .setParameter("idUang", idUangMuka)
                    .setParameter("branchId", branchId)
                    .list();
        }

        List<HeaderDetailCheckup> detailCheckups = new ArrayList<>();
        HeaderDetailCheckup detailCheckup;
        if (resuts.size() > 0) {
            for (Object[] obj : resuts) {
                detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setNoUangMuka(obj[0].toString());
                detailCheckup.setIdDetailCheckup(obj[1].toString());
                detailCheckup.setNamaPasien(obj[2].toString());
                detailCheckup.setNamaPelayanan(obj[3].toString());
                detailCheckup.setJumlahUangMuka(obj[4] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[4]);
                detailCheckup.setStatusBayar(obj[5] == null ? "" : obj[5].toString());
                detailCheckup.setCreatedDate((Timestamp) obj[6]);
                detailCheckup.setIdPasien(obj[7] == null ? "" : obj[7].toString());
                detailCheckup.setJumlahUangMukaDibayar(obj[8] == null ? new BigInteger(String.valueOf(0)) : (BigInteger) obj[8]);
                detailCheckup.setFlagRefund(obj[9] == null ? "" : obj[9].toString());
                detailCheckups.add(detailCheckup);
            }
        }
        return detailCheckups;
    }

    public BigDecimal getSumAllTarifTindakan(String idDetail, String ket) {

        String keterangan = "%";
        if (!"".equalsIgnoreCase(ket)) {
            keterangan = ket;
        }
        String SQL = "SELECT \n" +
                "id_detail_checkup,\n" +
                "SUM(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "it_simrs_riwayat_tindakan\n" +
                "WHERE \n" +
                "id_detail_checkup = :idDetail\n" +
                "AND keterangan LIKE :ket\n" +
                "GROUP BY id_detail_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .setParameter("ket", keterangan)
                .list();

        BigDecimal jumlah = new BigDecimal(0);
        if (results.size() > 0) {
            for (Object[] obj : results) {
                jumlah = (BigDecimal) obj[1];
            }
        }
        return jumlah;
    }

    public String getFindResepInRiwayatTrans(String idDetail) {

        String SQL = "SELECT id_detail_checkup, keterangan\n" +
                "FROM it_simrs_riwayat_tindakan\n" +
                "WHERE id_detail_checkup = :idDetail\n" +
                "AND keterangan = 'resep'\n" +
                "LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .list();

        String id = "N";
        BigDecimal jumlah = new BigDecimal(0);
        if (results.size() > 0) {
            for (Object[] obj : results) {
                id = "Y";
            }
        }
        return id;
    }

    public HeaderDetailCheckup getBiayaTindakan(String idDetail) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetail != null && !"".equalsIgnoreCase(idDetail)) {

            String SQL = "SELECT \n" +
                    "a.id_detail_checkup, \n" +
                    "SUM(a.total_tarif) as total,\n" +
                    "b.tarif_bpjs,\n" +
                    "b.kode_cbg,\n" +
                    "c.id_pasien,\n" +
                    "c.id_jenis_periksa_pasien\n" +
                    "FROM it_simrs_riwayat_tindakan a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                    "WHERE a.id_detail_checkup = :idDetail\n" +
                    "GROUP BY \n" +
                    "a.id_detail_checkup, \n" +
                    "b.tarif_bpjs, \n" +
                    "b.kode_cbg,\n" +
                    "c.id_pasien,\n" +
                    "c.id_jenis_periksa_pasien";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetail)
                    .list();

            if (results.size() > 0) {

                Object[] objects = results.get(0);
                detailCheckup.setIdDetailCheckup(objects[0] == null ? "" : objects[0].toString());
                detailCheckup.setTarifTindakan(objects[1] == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(objects[1].toString()));
                detailCheckup.setTarifBpjs(objects[2] == null ? new BigDecimal(String.valueOf(0)) : new BigDecimal(objects[2].toString()));
                detailCheckup.setKodeCbg(objects[3] == null ? "" : objects[3].toString());
                detailCheckup.setIdPasien(objects[4] == null ? "" : objects[4].toString());
                detailCheckup.setIdJenisPeriksaPasien(objects[5] == null ? "" : objects[5].toString());
            }

        }

        return detailCheckup;
    }

    public List<HeaderDetailCheckup> getStatusPeriksa(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();

        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String branchId = "%";

            String dateFrom = "";
            String dateTo = "";

            String jenisPasien = "%";
            String idKelas = "%";
            String idRuang = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = bean.getNamaPasien();
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                jenisPasien = bean.getIdJenisPeriksaPasien();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())) {
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())) {
                idRuang = bean.getIdRuang();
            }

            if ("RJ".equalsIgnoreCase(bean.getTipePelayanan())) {
                String SQL = "\n" +
                        "SELECT \n" +
                        "dt.id_detail_checkup,\n" +
                        "hd.no_checkup,\n" +
                        "hd.id_pasien,\n" +
                        "hd.nama,\n" +
                        "hd.jalan,\n" +
                        "hd.created_date,\n" +
                        "hd.desa_id,\n" +
                        "dt.status_periksa,\n" +
                        "st.keterangan,\n" +
                        "dt.keterangan_selesai,\n" +
                        "hd.id_jenis_periksa_pasien\n" +
                        "FROM \n" +
                        "it_simrs_header_checkup hd\n" +
                        "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                        "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                        "WHERE ri.id_detail_checkup is null\n" +
                        "AND hd.branch_id LIKE :branchId \n" +
                        "AND hd.id_pasien LIKE :idPasien \n" +
                        "AND hd.nama LIKE :nama \n" +
                        "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                        "AND hd.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                        "AND dt.is_kronis IS NULL \n" +
                        "AND dt.status_periksa LIKE :status ";


                String order = "\n ORDER BY dt.tgl_antrian ASC";

                List<Object[]> results = new ArrayList<>();
                if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                    SQL = SQL + "\n AND CAST(hd.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(hd.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" + order;


                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPasien", jenisPasien)
                            .list();

                } else {

                    SQL = SQL + order;

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("jenisPasien", jenisPasien)
                            .setParameter("branchId", branchId)
                            .setParameter("status", statusPeriksa)
                            .list();
                }

                if (!results.isEmpty()) {
                    for (Object[] obj : results) {

                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                        headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                        headerDetailCheckup.setNoCheckup(obj[1].toString());
                        headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                        headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                        String jalan = obj[4] == null ? "" : obj[4].toString();

                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                        headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                        headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                        headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                        headerDetailCheckup.setIdJenisPeriksaPasien(obj[10] == null ? "" : obj[10].toString());

                        if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                            List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                    headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                    headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                }
                            }
                        }

                        headerDetailCheckup.setAlamat(jalan);
                        checkupList.add(headerDetailCheckup);
                    }
                }
            } else {
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
                        "a.id_jenis_periksa_pasien\n" +
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                        "INNER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                        "INNER JOIN mt_simrs_ruangan e ON d.id_ruangan = e.id_ruangan\n" +
                        "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                        "WHERE a.id_pasien LIKE :idPasien\n" +
                        "AND a.nama LIKE :nama\n" +
                        "AND b.status_periksa LIKE :status\n" +
                        "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                        "AND e.id_ruangan LIKE :idRuang\n" +
                        "AND b.is_kronis IS NULL\n" +
                        "AND a.branch_id LIKE :branchId\n" +
                        "AND a.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
                        "AND a.flag = 'Y'\n ";

                List<Object[]> results = new ArrayList<>();

                if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                    SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("status", statusPeriksa)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPasien)
                            .list();

                } else {

                    SQL = SQL + "\n  ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("status", statusPeriksa)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPasien)
                            .list();
                }

                if (!results.isEmpty()) {
                    for (Object[] obj : results) {
                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                        headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                        headerDetailCheckup.setNoCheckup(obj[1].toString());
                        headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                        headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                        String jalan = obj[4] == null ? "" : obj[4].toString();
                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                        headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                        headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                        headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                        headerDetailCheckup.setIdRawatInap(obj[10].toString());
                        headerDetailCheckup.setIdRuangan(obj[11].toString());
                        headerDetailCheckup.setNoRuangan(obj[12].toString());
                        headerDetailCheckup.setNamaRuangan(obj[13].toString());
                        headerDetailCheckup.setNamaKelasRuangan(obj[14].toString());
                        headerDetailCheckup.setIdKelas(obj[15].toString());
                        headerDetailCheckup.setIdJenisPeriksaPasien(obj[16] == null ? "" : obj[16].toString());

                        if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                            List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                    headerDetailCheckup.setDesa(objDesa[0].toString());
                                    headerDetailCheckup.setKecamatan(objDesa[1].toString());
                                }
                            }
                        }

                        headerDetailCheckup.setAlamat(jalan);
                        checkupList.add(headerDetailCheckup);
                    }
                }
            }
        }
        return checkupList;
    }

    public PermintaanResep getDataDokterWithResep(String id){
        PermintaanResep resep = new PermintaanResep();
        if(id != null && !"".equalsIgnoreCase(id)){
            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep,\n" +
                    "a.id_approval_obat,\n" +
                    "b.nama_dokter, \n" +
                    "a.ttd_dokter, \n" +
                    "a.ttd_pasien\n" +
                    "FROM mt_simrs_permintaan_resep a \n" +
                    "INNER JOIN im_simrs_dokter b ON a.id_dokter = b.id_dokter\n" +
                    "WHERE a.id_permintaan_resep = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id",id)
                    .list();
            if(result.size() > 0){
                Object[] obj = result.get(0);
                if(obj != null){
                    resep.setIdPermintaanResep(obj[0] == null ? "" : obj[0].toString());
                    resep.setIdApprovalObat(obj[1] == null ? "" : obj[1].toString());
                    resep.setNamaDokter(obj[2] == null ? "" : obj[2].toString());
                    resep.setTtdDokter(obj[3] == null ? "" : obj[3].toString());
                    resep.setTtdPasien(obj[4] == null ? "" : obj[4].toString());
                }
            }
        }
        return resep;
    }

    public List<HeaderDetailCheckup> getDaftarUlangPasien(HeaderDetailCheckup bean) {
        List<HeaderDetailCheckup> checkupList = new ArrayList<>();

        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String branchId = "%";

            String dateFrom = "";
            String dateTo = "";

            String jenisPasien = "%";
            String idKelas = "%";
            String idRuang = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = bean.getNamaPasien();
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPelayanan())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())) {
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksaPasien() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {
                jenisPasien = bean.getIdJenisPeriksaPasien();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())) {
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())) {
                idRuang = bean.getIdRuang();
            }

            if ("RJ".equalsIgnoreCase(bean.getTipePelayanan())) {
                String SQL = "\n" +
                        "SELECT \n" +
                        "dt.id_detail_checkup,\n" +
                        "hd.no_checkup,\n" +
                        "hd.id_pasien,\n" +
                        "hd.nama,\n" +
                        "hd.jalan,\n" +
                        "hd.created_date,\n" +
                        "hd.desa_id,\n" +
                        "dt.status_periksa,\n" +
                        "st.keterangan,\n" +
                        "dt.keterangan_selesai,\n" +
                        "hd.id_jenis_periksa_pasien\n" +
                        "FROM \n" +
                        "it_simrs_header_checkup hd\n" +
                        "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                        "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                        "WHERE ri.id_detail_checkup is null\n" +
                        "AND hd.branch_id LIKE :branchId \n" +
                        "AND hd.id_pasien LIKE :idPasien \n" +
                        "AND hd.nama LIKE :nama \n" +
                        "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                        "AND hd.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                        "AND dt.is_kronis IS NULL \n" +
                        "AND dt.keterangan_selesai = 'Lanjut Biaya'\n" +
                        "AND dt.status_periksa LIKE :status ";

                String order = "\n ORDER BY dt.tgl_antrian ASC";

                List<Object[]> results = new ArrayList<>();
                if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                    SQL = SQL + "\n AND CAST(hd.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(hd.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" + order;


                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPasien", jenisPasien)
                            .list();

                } else {

                    SQL = SQL + order;

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("jenisPasien", jenisPasien)
                            .setParameter("branchId", branchId)
                            .setParameter("status", statusPeriksa)
                            .list();
                }

                if (!results.isEmpty()) {
                    for (Object[] obj : results) {

                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                        headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                        headerDetailCheckup.setNoCheckup(obj[1].toString());
                        headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                        headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                        String jalan = obj[4] == null ? "" : obj[4].toString();

                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                        headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                        headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                        headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                        headerDetailCheckup.setIdJenisPeriksaPasien(obj[10] == null ? "" : obj[10].toString());

                        if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                            List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                    headerDetailCheckup.setDesa(objDesa[0] == null ? "" : objDesa[0].toString());
                                    headerDetailCheckup.setKecamatan(objDesa[1] == null ? "" : objDesa[1].toString());

                                }
                            }
                        }

                        headerDetailCheckup.setAlamat(jalan);
                        checkupList.add(headerDetailCheckup);
                    }
                }
            } else {
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
                        "a.id_jenis_periksa_pasien\n" +
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                        "INNER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                        "INNER JOIN mt_simrs_ruangan e ON d.id_ruangan = e.id_ruangan\n" +
                        "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                        "WHERE a.id_pasien LIKE :idPasien\n" +
                        "AND a.nama LIKE :nama\n" +
                        "AND b.status_periksa LIKE :status\n" +
                        "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                        "AND e.id_ruangan LIKE :idRuang\n" +
                        "AND b.is_kronis IS NULL\n" +
                        "AND b.keterangan_selesai = 'Lanjut Biaya'\n" +
                        "AND a.branch_id LIKE :branchId\n" +
                        "AND a.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
                        "AND a.flag = 'Y'\n ";

                List<Object[]> results = new ArrayList<>();

                if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                    SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("status", statusPeriksa)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPasien)
                            .list();

                } else {

                    SQL = SQL + "\n  ORDER BY b.tgl_antrian ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("status", statusPeriksa)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPasien)
                            .list();
                }

                if (!results.isEmpty()) {
                    for (Object[] obj : results) {
                        HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                        headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                        headerDetailCheckup.setNoCheckup(obj[1].toString());
                        headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                        headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                        String jalan = obj[4] == null ? "" : obj[4].toString();
                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                        headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                        headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                        headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                        headerDetailCheckup.setIdRawatInap(obj[10].toString());
                        headerDetailCheckup.setIdRuangan(obj[11].toString());
                        headerDetailCheckup.setNoRuangan(obj[12].toString());
                        headerDetailCheckup.setNamaRuangan(obj[13].toString());
                        headerDetailCheckup.setNamaKelasRuangan(obj[14].toString());
                        headerDetailCheckup.setIdKelas(obj[15].toString());
                        headerDetailCheckup.setIdJenisPeriksaPasien(obj[16] == null ? "" : obj[16].toString());

                        if (!"".equalsIgnoreCase(headerDetailCheckup.getDesaId())) {
                            List<Object[]> objDesaList = getListAlamatByDesaId(headerDetailCheckup.getDesaId());
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

                                    headerDetailCheckup.setDesa(objDesa[0].toString());
                                    headerDetailCheckup.setKecamatan(objDesa[1].toString());
                                }
                            }
                        }

                        headerDetailCheckup.setAlamat(jalan);
                        checkupList.add(headerDetailCheckup);
                    }
                }
            }
        }
        return checkupList;
    }

    public HeaderDetailCheckup getCoverBiayaAsuransi(String idDetailCheckup){

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if(idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)){
            String SQL = "SELECT \n" +
                    "a.id_detail_checkup, \n" +
                    "a.id_asuransi, \n" +
                    "a.no_kartu_asuransi,\n" +
                    "a.cover_biaya,\n" +
                    "b.total_tindakan,\n" +
                    "c.total_lab,\n" +
                    "d.total_radiologi,\n" +
                    "e.total_resep\n" +
                    "FROM it_simrs_header_detail_checkup a\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT id_detail_checkup, SUM(tarif_total) as total_tindakan \n" +
                    "\tFROM it_simrs_tindakan_rawat GROUP BY id_detail_checkup\n" +
                    "\t) b ON a.id_detail_checkup = b.id_detail_checkup \n" +
                    "LEFT JOIN(\n" +
                    "\tSELECT a.id_detail_checkup, SUM(c.tarif) as total_lab FROM it_simrs_periksa_lab a\n" +
                    "\tINNER JOIN it_simrs_periksa_lab_detail b ON a.id_periksa_lab = b.id_periksa_lab\n" +
                    "\tINNER JOIN im_simrs_lab_detail c ON b.id_lab_detail = c.id_lab_detail\n" +
                    "\tGROUP BY a.id_detail_checkup\n" +
                    "\t) c ON a.id_detail_checkup = c.id_detail_checkup \n" +
                    "LEFT JOIN(\n" +
                    "\tSELECT a.id_detail_checkup, SUM(c.tarif) as total_radiologi FROM it_simrs_periksa_lab a\n" +
                    "\tINNER JOIN it_simrs_periksa_radiologi b ON a.id_periksa_lab = b.id_periksa_lab\n" +
                    "\tINNER JOIN im_simrs_lab_detail c ON b.id_lab_detail = c.id_lab_detail\n" +
                    "\tGROUP BY a.id_detail_checkup\n" +
                    "\t) d ON a.id_detail_checkup = d.id_detail_checkup \n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT a.id_detail_checkup, (c.qty * d.harga_jual) as total_resep FROM mt_simrs_permintaan_resep a\n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch \n" +
                    "\tWHERE approve_flag = 'Y'\n" +
                    "\tGROUP BY id_transaksi_obat_detail) c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    "\t) e ON a.id_detail_checkup = e.id_detail_checkup \n" +
                    "WHERE a.id_detail_checkup = :id";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if(result.size() > 0){
                Object[] objects = result.get(0);
                if(objects != null){
                    BigDecimal coverBiaya = new BigDecimal(String.valueOf("0"));
                    BigDecimal tindakan = new BigDecimal(String.valueOf("0"));
                    BigDecimal lab = new BigDecimal(String.valueOf("0"));
                    BigDecimal radiologi = new BigDecimal(String.valueOf("0"));
                    BigDecimal resep = new BigDecimal(String.valueOf("0"));
                    BigDecimal totalTindakan = new BigDecimal(String.valueOf("0"));

                    if(objects[3] != null && !"".equalsIgnoreCase(objects[3].toString())){
                        coverBiaya = new BigDecimal(objects[3].toString());

                        if(objects[4] != null && !"".equalsIgnoreCase(objects[4].toString())){
                            tindakan = new BigDecimal(objects[4].toString());
                        }
                        if(objects[5] != null && !"".equalsIgnoreCase(objects[5].toString())){
                            lab = new BigDecimal(objects[5].toString());
                        }
                        if(objects[6] != null && !"".equalsIgnoreCase(objects[6].toString())){
                            radiologi = new BigDecimal(objects[6].toString());
                        }
                        if(objects[7] != null && !"".equalsIgnoreCase(objects[7].toString())){
                            resep = new BigDecimal(objects[7].toString());
                        }

                        totalTindakan = tindakan.add(lab).add(radiologi).add(resep);
                    }
                    detailCheckup.setIdDetailCheckup(objects[0] == null ? "" : objects[0].toString());
                    detailCheckup.setIdAsuransi(objects[1] == null ? "" : objects[1].toString());
                    detailCheckup.setNoKartuAsuransi(objects[2] == null ? "" : objects[2].toString());
                    detailCheckup.setCoverBiaya(coverBiaya);
                    detailCheckup.setTarifTindakan(totalTindakan);
                }
            }
        }
        return detailCheckup;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
