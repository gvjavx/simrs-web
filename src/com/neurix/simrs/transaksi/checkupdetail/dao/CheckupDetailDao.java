package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
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

                    if ("0".equalsIgnoreCase(detailCheckup.getStatusPeriksa())) {
                        if ("bpjs".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {

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
                "um.jumlah_dibayar\n" +
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

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
