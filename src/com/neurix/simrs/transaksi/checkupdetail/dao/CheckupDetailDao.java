package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.transaksi.asesmenugd.model.ItSimrsAsesmenUgdEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsHeaderDetailCheckupEntity;
import com.neurix.simrs.transaksi.checkupdetail.model.KlaimFpkDTO;
import com.neurix.simrs.transaksi.checkupdetail.model.RiwayatTindakanDTO;
import com.neurix.simrs.transaksi.permintaanresep.model.PermintaanResep;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import com.neurix.simrs.transaksi.riwayattindakan.model.RiwayatTindakan;
import com.neurix.simrs.transaksi.tindakanrawat.model.ItSimrsTindakanRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
//mengurukan data berdasarkan tglantrian

        criteria.addOrder(Order.asc("tglAntrian"));
        List<ItSimrsHeaderDetailCheckupEntity> result = criteria.list();
        return result;
    }

    public List<KlaimFpkDTO> getSearchCheckupBySep(String noSep) {
        List<KlaimFpkDTO> listOfResult = new ArrayList<KlaimFpkDTO>();
        List<Object[]> result = new ArrayList<Object[]>();

        String query1 = "SELECT\n" +
                "\tdc.id_detail_checkup AS id,\n" +
                "\tdc.no_sep AS sep,\n" +
                "\tc.id_pasien AS idpasien,\n" +
                "\tc.nama AS nama,\n" +
                "\tSUM(rt.total_tarif) AS total,\n" +
                "\tf.status_bayar AS status\n" +
                "FROM \n" +
                "\tit_simrs_header_detail_checkup dc \n" +
                "\tLEFT JOIN it_simrs_header_checkup c ON dc.no_checkup=c.no_checkup\n" +
                "\tLEFT JOIN it_simrs_riwayat_tindakan rt ON dc.id_detail_checkup=rt.id_detail_checkup\n" +
                "\tLEFT JOIN it_simrs_fpk f ON f.no_sep=dc.no_sep\n" +
                "WHERE\n" +
                "\tdc.no_sep='" + noSep + "'\n" +
                "\tAND dc.flag='Y'\n" +
                "GROUP BY\n" +
                "\tdc.id_detail_checkup,\n" +
                "\tdc.no_sep,\n" +
                "\tc.id_pasien,\n" +
                "\tc.nama,\n" +
                "\tf.status_bayar";
        result = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query1)
                .list();

        for (Object[] row : result) {
            KlaimFpkDTO klaimFpkDTO = new KlaimFpkDTO();
            klaimFpkDTO.setIdDetailCheckup((String) row[0]);
            klaimFpkDTO.setNoSep((String) row[1]);
            klaimFpkDTO.setIdPasien((String) row[2]);
            klaimFpkDTO.setNamaPasien((String) row[3]);
            if (row[4] != null) {
                klaimFpkDTO.setTotalBiaya(BigInteger.valueOf(Long.parseLong((row[4].toString()))));
            }
            if (row[5] != null) {
                klaimFpkDTO.setStatusBayar((String) row[5]);
            }

            listOfResult.add(klaimFpkDTO);
        }
        return listOfResult;
    }

    public List<RiwayatTindakanDTO> getRiwayatTindakanDanDokter(String id) {
        List<RiwayatTindakanDTO> listOfResult = new ArrayList<>();

        String query1 = "select \n" +
                "\tdt.id_dokter as iddokter,\n" +
                "\td.nama_dokter as namadokter,\n" +
                "\trt.id_tindakan as idtindakan,\n" +
                "\trt.nama_tindakan as namatindakan,\n" +
                "\trt.keterangan as kettindakan,\n" +
                "\trt.total_tarif as total,\n" +
                "\tdc.id_pelayanan as idpoli,\n" +
                "\tp.nama_pelayanan as namapelayanan,\n" +
                "\tp.kodering\n" +
                "from \n" +
                "\tit_simrs_riwayat_tindakan rt\n" +
                "\tLEFT JOIN it_simrs_dokter_team dt ON rt.id_detail_checkup = dt.id_detail_checkup\n" +
                "\tLEFT JOIN im_simrs_dokter d ON d.id_dokter = dt.id_dokter\n" +
                "\tLEFT JOIN it_simrs_header_detail_checkup dc ON dc.id_detail_checkup=rt.id_detail_checkup\n" +
                "\tLEFT JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id," +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) p ON p.id_pelayanan = dc.id_pelayanan\n" +
                "WHERE\n" +
                "\trt.id_detail_checkup='" + id + "'\n" +
                "\tAND rt.flag='Y'\n" +
                "\tAND dt.flag='Y'";
        List<Object[]> result = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query1)
                .list();

        for (Object[] row : result) {
            RiwayatTindakanDTO riwayatTindakanDTO = new RiwayatTindakanDTO();
            riwayatTindakanDTO.setIdDokter((String) row[0]);
            riwayatTindakanDTO.setNamaDokter((String) row[1]);
            riwayatTindakanDTO.setIdTindakan((String) row[2]);
            riwayatTindakanDTO.setNamaTindakan((String) row[3]);
            riwayatTindakanDTO.setKetTindakan((String) row[4]);
            riwayatTindakanDTO.setTotalTarif(BigInteger.valueOf(Long.parseLong((row[5].toString()))));
            riwayatTindakanDTO.setStTotalTarif(CommonUtil.numbericFormat(new BigDecimal(riwayatTindakanDTO.getTotalTarif()), "###,###"));
            riwayatTindakanDTO.setIdPoli((String) row[6]);
            riwayatTindakanDTO.setNamaPoli((String) row[7]);
            riwayatTindakanDTO.setKoderingPoli((String) row[8]);


            listOfResult.add(riwayatTindakanDTO);
        }
        return listOfResult;
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

            String tipePelayanan = "";

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

            if ("kasir".equalsIgnoreCase(bean.getTypeTransaction())) {
                if (bean.getStatusBayar() != null && !"".equalsIgnoreCase(bean.getStatusBayar())) {
                    statusBayar = "\n AND dt.status_bayar = '" + bean.getStatusBayar() + "'\n";
                } else {
                    statusBayar = "\n AND dt.status_bayar IS NULL AND dt.flag_close_traksaksi = 'Y' AND dt.flag_cover = 'Y'\n";
                }
            }

            String forRekanan = "";
            if ("asuransi".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) ||
                    "rekanan".equalsIgnoreCase(bean.getIdJenisPeriksaPasien()) ||
                    "bpjs".equalsIgnoreCase(bean.getIdJenisPeriksaPasien())) {

                forRekanan = "\n AND dt.flag_sisa = 'Y' \n";

            }

            if ("rawat_jalan".equalsIgnoreCase(bean.getTipePelayanan())) {
                tipePelayanan = "AND ply.tipe_pelayanan = 'rawat_jalan' \n";
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
                    "dt.klaim_bpjs_flag, \n" +
                    "dt.status_bayar, \n" +
                    "um.status_bayar AS status_bayar_uang_muka, \n" +
                    "dt.id_jenis_periksa_pasien, \n" +
                    "um.id, \n" +
                    "um.id_detail_checkup, \n" +
                    "dt.tgl_cekup, \n" +
                    "jp.keterangan as jenis_pasien,\n" +
                    "hd.tgl_lahir,\n"+
                    "dt.tindak_lanjut \n"+
                    "FROM it_simrs_header_checkup hd\n" +
                    "INNER JOIN (" +
                    "SELECT a.* FROM(\n" +
                    "SELECT *, rank() OVER (PARTITION BY no_checkup ORDER BY created_date DESC) as rank \n" +
                    "FROM it_simrs_header_detail_checkup\n" +
                    ") a WHERE a.rank = 1 " +
                    ")dt ON dt.no_checkup = hd.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                    "INNER JOIN im_simrs_jenis_periksa_pasien jp ON dt.id_jenis_periksa_pasien = jp.id_jenis_periksa_pasien \n" +
                    "INNER JOIN (SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "a.branch_id," +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) ply ON dt.id_pelayanan = ply.id_pelayanan \n" +
                    "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                    "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = dt.id_detail_checkup\n" +
                    "WHERE ri.id_detail_checkup is null\n" +
                    "AND hd.branch_id LIKE :branchId \n" +
                    "AND hd.id_pasien LIKE :idPasien \n" +
                    "AND hd.nama LIKE :nama \n" +
                    "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                    "AND dt.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                    "AND dt.is_kronis IS NULL \n" +
                    "AND dt.id_transaksi_online IS NULL \n" +
                    "AND dt.status_periksa LIKE :status \n" + statusBayar + forRekanan + tipePelayanan;

            String order = "\n ORDER BY dt.tgl_antrian ASC";
            if ("kasir".equalsIgnoreCase(bean.getTypeTransaction())) {
                order = "\n ORDER BY dt.last_update DESC";
            }
            if ("mobile".equalsIgnoreCase(bean.getTypeTransaction())) {
                order = "\n ORDER BY dt.created_date DESC";
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

                    HeaderDetailCheckup headerDetailCheckup = new HeaderDetailCheckup();
                    if (obj[14] != null) {
                        if ("umum".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                            if ("Y".equalsIgnoreCase(detailCheckup.getStatusBayar())) {
                                headerDetailCheckup.setIsBayar("Y");
                            } else {
                                headerDetailCheckup.setIsBayar("N");
                            }
                        }
                    } else {
                        headerDetailCheckup.setIsBayar("Y");
                    }

                    headerDetailCheckup.setIdDetailCheckup(obj[0].toString());
                    headerDetailCheckup.setNoCheckup(obj[1].toString());
                    headerDetailCheckup.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    headerDetailCheckup.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                    String jalan = obj[4] == null ? "" : obj[4].toString();
                    if(obj[5] != null){
                        headerDetailCheckup.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Timestamp)obj[5]);
                        headerDetailCheckup.setFormatTglMasuk(formatDate);
                    }
                    headerDetailCheckup.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    headerDetailCheckup.setStatusPeriksa(obj[7].toString());
                    headerDetailCheckup.setStatusPeriksaName(obj[8].toString());
                    headerDetailCheckup.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    headerDetailCheckup.setKlaimBpjsFlag(obj[10] == null ? "" : obj[10].toString());
                    headerDetailCheckup.setStatusBayar(obj[11] == null ? "" : obj[11].toString());
                    headerDetailCheckup.setIdJenisPeriksaPasien(obj[13] == null ? "" : obj[13].toString());
                    headerDetailCheckup.setTglCekup(obj[16] != null ? java.sql.Date.valueOf(obj[16].toString()) : null);
                    headerDetailCheckup.setJenisPeriksaPasien(obj[17] != null ? obj[17].toString() : null);
                    headerDetailCheckup.setTanggalLahir(obj[18] != null ? (java.sql.Date) obj[18]: null);
                    if(headerDetailCheckup.getTanggalLahir() != null){
                        headerDetailCheckup.setUmur(CommonUtil.calculateAge(headerDetailCheckup.getTanggalLahir(), true)+" Tahun");
                    }
                    headerDetailCheckup.setTindakLanjut(obj[19] != null ? (String) obj[19]: null);

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
                    headerDetailCheckup.setIsTindakan(isTindakanRawat(obj[0].toString()));
                    headerDetailCheckup.setTriase(triase(obj[0].toString()));
                    checkupList.add(headerDetailCheckup);
                }
            }
        }
        return checkupList;
    }

    public String isTindakanRawat(String id){
        String res = "N";
        if(id != null && !"".equalsIgnoreCase(id)){
            Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTindakanRawatEntity.class);
            criteria.add(Restrictions.eq("idDetailCheckup", id));
            criteria.add(Restrictions.eq("flag", "Y"));
            List<ItSimrsTindakanRawatEntity> listOfResult = criteria.list();
            if(listOfResult.size() > 0){
                res = "Y";
            }
        }
        return res;
    }

    public String triase(String id){
        String res = "";
        if(id != null && !"".equalsIgnoreCase(id)){
            Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenUgdEntity.class);
            criteria.add(Restrictions.eq("idDetailCheckup", id));
            criteria.add(Restrictions.eq("tipe", "triase"));
            criteria.add(Restrictions.eq("flag", "Y"));
            List<ItSimrsAsesmenUgdEntity> listOfResult = criteria.list();
            if(listOfResult.size() > 0){
                ItSimrsAsesmenUgdEntity entity = listOfResult.get(0);
                res = entity.getJawaban();
            }
        }
        return res;
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
                    "AND dt.id_jenis_periksa_pasien IN ('bpjs', 'bpjs_rekanan') \n" +
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
                        .setParameter("branchId", branchId)
                        .list();

            } else {

                SQL = SQL + "\n  ORDER BY dt.tgl_antrian ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
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
                    "dt.id_jenis_periksa_pasien,\n" +
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
                    "AND dt.id_jenis_periksa_pasien LIKE 'bpjs'\n" +
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

        String SQL = "SELECT id_detail_checkup, approve_bpjs_flag, flag_update_klaim\n" +
                "FROM it_simrs_riwayat_tindakan\n" +
                "WHERE id_detail_checkup LIKE :id ";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetail)
                .list();

        if (results != null) {
            for (Object[] obj : results) {
                if (obj[2] == null || "".equalsIgnoreCase(obj[1].toString())) {
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

        String idPasien = "%";
        String namaPasien = "%";

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

        if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
            idPasien = bean.getIdPasien();
        }

        if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
            namaPasien = "%" + bean.getNamaPasien() + "%";
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
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id," +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) pel ON pel.id_pelayanan = dt.id_pelayanan\n" +
                "WHERE dt.id_detail_checkup LIKE :idDetail\n" +
                "AND dt.id_pelayanan LIKE :idPoli\n" +
                "AND ck.branch_id LIKE :branchId\n" +
                "AND ck.id_pasien LIKE :idPasien\n" +
                "AND ck.nama LIKE :nama\n" +
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
                    .setParameter("idPasien", idPasien)
                    .setParameter("nama", namaPasien)
                    .list();

        } else {
            SQL = SQL + order;

            resuts = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idDetail", idDetail)
                    .setParameter("idPoli", idPelayanan)
                    .setParameter("idUang", idUangMuka)
                    .setParameter("branchId", branchId)
                    .setParameter("idPasien", idPasien)
                    .setParameter("nama", namaPasien)
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

    public BigDecimal getSumAllTarifTindakan(String idDetail, String jenis, String ket) {

        String keterangan = "%";
        if (!"".equalsIgnoreCase(ket)) {
            keterangan = ket;
        }

        String jenisPasien = "%";
        if (!"".equalsIgnoreCase(jenis)) {
            jenisPasien = jenis;
        } else if ("ptpn".equalsIgnoreCase(jenis)) {
            jenisPasien = "bpjs";
        }

        String SQL = "SELECT \n" +
                "id_detail_checkup,\n" +
                "SUM(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "it_simrs_riwayat_tindakan\n" +
                "WHERE \n" +
                "id_detail_checkup = :idDetail\n" +
                "AND keterangan LIKE :ket\n" +
                "AND jenis_pasien LIKE :jenis\n" +
                "AND id_riwayat_tindakan NOT IN (\n" +
                "\tSELECT id_riwayat_tindakan \n" +
                "\tFROM it_simrs_tindakan_transitoris\n" +
                "\tWHERE id_detail_checkup = :idDetail\n" +
                ")\n" +
                "GROUP BY id_detail_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .setParameter("ket", keterangan)
                .setParameter("jenis", jenisPasien)
                .list();

        BigDecimal jumlah = new BigDecimal(0);
        if (results.size() > 0) {
            for (Object[] obj : results) {
                jumlah = (BigDecimal) obj[1];
            }
        }
        return jumlah;
    }

    public BigDecimal getSumAllTarifTindakanRI(String idDetail, String jenis, String ket, String idRuangan) {

        String keterangan = "%";
        if (!"".equalsIgnoreCase(ket)) {
            keterangan = ket;
        }

        String jenisPasien = "%";
        if (!"".equalsIgnoreCase(jenis)) {
            jenisPasien = jenis;
        } else if ("ptpn".equalsIgnoreCase(jenis)) {
            jenisPasien = "bpjs";
        }

        String SQL = "SELECT \n" +
                "id_detail_checkup,\n" +
                "SUM(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "it_simrs_riwayat_tindakan\n" +
                "WHERE \n" +
                "id_detail_checkup = :idDetail\n" +
                "AND keterangan LIKE :ket\n" +
                "AND jenis_pasien LIKE :jenis\n" +
                "AND id_ruangan LIKE :ruangan\n" +
                "AND id_riwayat_tindakan NOT IN (\n" +
                "\tSELECT id_riwayat_tindakan \n" +
                "\tFROM it_simrs_tindakan_transitoris\n" +
                "\tWHERE id_detail_checkup = :idDetail\n" +
                ")\n" +
                "GROUP BY id_detail_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .setParameter("ket", keterangan)
                .setParameter("jenis", jenisPasien)
                .setParameter("ruangan", idRuangan)
                .list();

        BigDecimal jumlah = new BigDecimal(0);
        if (results.size() > 0) {
            for (Object[] obj : results) {
                jumlah = (BigDecimal) obj[1];
            }
        }
        return jumlah;
    }

    public BigDecimal getSumAllTarifTransitoris(String idDetail, String ket) {

        String keterangan = "%";
        if (!"".equalsIgnoreCase(ket)) {
            keterangan = ket;
        }
        String SQL = "SELECT \n" +
                "id_detail_checkup,\n" +
                "SUM(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "it_simrs_tindakan_transitoris\n" +
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

    public BigDecimal getSumAllTarifTransitorisByJenis(String idDetail, String jenis, String ket) {

        String keterangan = "%";
        if (!"".equalsIgnoreCase(ket)) {
            keterangan = ket;
        }
        String SQL = "SELECT \n" +
                "id_detail_checkup,\n" +
                "SUM(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "it_simrs_tindakan_transitoris\n" +
                "WHERE \n" +
                "id_detail_checkup = :idDetail \n" +
                "AND keterangan LIKE :ket \n" +
                "AND jenis_pasien LIKE :jenis \n" +
                "GROUP BY id_detail_checkup";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetail)
                .setParameter("ket", keterangan)
                .setParameter("jenis", jenis)
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
                    "b.id_jenis_periksa_pasien\n" +
                    "FROM it_simrs_riwayat_tindakan a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                    "WHERE a.id_detail_checkup = :idDetail\n" +
                    "AND a.jenis_pasien = 'bpjs'\n" +
                    "GROUP BY \n" +
                    "a.id_detail_checkup, \n" +
                    "b.tarif_bpjs, \n" +
                    "b.kode_cbg,\n" +
                    "c.id_pasien,\n" +
                    "b.id_jenis_periksa_pasien";

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
                        "dt.id_jenis_periksa_pasien,\n" +
                        "jp.keterangan AS jenis_periksa,\n" +
                        "um.status_bayar\n" +
                        "FROM \n" +
                        "it_simrs_header_checkup hd\n" +
                        "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                        "INNER JOIN im_simrs_jenis_periksa_pasien jp ON jp.id_jenis_periksa_pasien = dt.id_jenis_periksa_pasien\n" +
                        "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                        "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = dt.id_detail_checkup\n" +
                        "WHERE ri.id_detail_checkup is null\n" +
                        "AND hd.branch_id LIKE :branchId \n" +
                        "AND hd.id_pasien LIKE :idPasien \n" +
                        "AND hd.nama LIKE :nama \n" +
                        "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                        "AND dt.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
                        "AND dt.id_jenis_periksa_pasien NOT IN ('paket_perusahaan', 'paket_individu') \n" +
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
                        if ("umum".equalsIgnoreCase(obj[10].toString())) {
                            if (obj[12] != null) {
                                if ("Y".equalsIgnoreCase(obj[12].toString())) {
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
                                    headerDetailCheckup.setJenisPeriksaPasien(obj[11] == null ? "" : obj[11].toString());

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
                            headerDetailCheckup.setIdJenisPeriksaPasien(obj[10] == null ? "" : obj[10].toString());
                            headerDetailCheckup.setJenisPeriksaPasien(obj[11] == null ? "" : obj[11].toString());

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
                        "b.id_jenis_periksa_pasien,\n" +
                        "jp.keterangan AS jenis_periksa,\n" +
                        "um.status_bayar\n" +
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                        "INNER JOIN im_simrs_jenis_periksa_pasien jp ON jp.id_jenis_periksa_pasien = b.id_jenis_periksa_pasien\n" +
                        "INNER JOIN (" +
                        "SELECT * FROM (\n" +
                        "SELECT *, rank() OVER (PARTITION BY id_detail_checkup ORDER BY created_date ASC) as rank\n" +
                        "FROM it_simrs_rawat_inap \n" +
                        "WHERE flag = 'Y'\n" +
                        "AND status = '1'\n" +
                        ") a WHERE a.rank = 1 \n" +
                        ") d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                        "INNER JOIN mt_simrs_ruangan e ON d.id_ruangan = e.id_ruangan\n" +
                        "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                        "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = b.id_detail_checkup\n" +
                        "WHERE a.id_pasien LIKE :idPasien\n" +
                        "AND a.nama LIKE :nama\n" +
                        "AND b.status_periksa LIKE :status\n" +
                        "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                        "AND e.id_ruangan LIKE :idRuang\n" +
                        "AND b.is_kronis IS NULL\n" +
                        "AND a.branch_id LIKE :branchId\n" +
                        "AND b.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
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
                        if ("umum".equalsIgnoreCase(obj[16].toString())) {
                            if (obj[18] != null) {
                                if ("Y".equalsIgnoreCase(obj[18].toString())) {
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
                                    headerDetailCheckup.setJenisPeriksaPasien(obj[17] == null ? "" : obj[17].toString());

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
                            headerDetailCheckup.setIdRawatInap(obj[10].toString());
                            headerDetailCheckup.setIdRuangan(obj[11].toString());
                            headerDetailCheckup.setNoRuangan(obj[12].toString());
                            headerDetailCheckup.setNamaRuangan(obj[13].toString());
                            headerDetailCheckup.setNamaKelasRuangan(obj[14].toString());
                            headerDetailCheckup.setIdKelas(obj[15].toString());
                            headerDetailCheckup.setIdJenisPeriksaPasien(obj[16] == null ? "" : obj[16].toString());
                            headerDetailCheckup.setJenisPeriksaPasien(obj[17] == null ? "" : obj[17].toString());

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
        }
        return checkupList;
    }

    public PermintaanResep getDataDokterWithResep(String id) {
        PermintaanResep resep = new PermintaanResep();
        if (id != null && !"".equalsIgnoreCase(id)) {
            String SQL = "SELECT \n" +
                    "a.id_permintaan_resep,\n" +
                    "a.id_approval_obat,\n" +
                    "b.nama_dokter, \n" +
                    "a.ttd_dokter, \n" +
                    "a.ttd_pasien,\n" +
                    "a.ttd_apoteker,\n" +
                    "a.id_apoteker,\n" +
                    "c.user_name,\n" +
                    "b.id_dokter \n" +
                    "FROM mt_simrs_permintaan_resep a \n" +
                    "LEFT JOIN im_simrs_dokter b ON a.id_dokter = b.id_dokter\n" +
                    "LEFT JOIN im_users c ON a.id_apoteker = c.user_id\n" +
                    "WHERE a.id_permintaan_resep = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();
            if (result.size() > 0) {
                Object[] obj = result.get(0);
                if (obj != null) {
                    resep.setIdPermintaanResep(obj[0] == null ? "" : obj[0].toString());
                    resep.setIdApprovalObat(obj[1] == null ? "" : obj[1].toString());
                    resep.setNamaDokter(obj[2] == null ? "" : obj[2].toString());
                    resep.setTtdDokter(obj[3] == null ? "" : obj[3].toString());
                    resep.setTtdPasien(obj[4] == null ? "" : obj[4].toString());
                    resep.setTtdApoteker(obj[5] == null ? "" : obj[5].toString());
                    resep.setNamaApoteker(obj[7] == null ? "" : obj[7].toString());
                    resep.setIdDokter(obj[8] == null ? "" : obj[8].toString());
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
                        "dt.id_jenis_periksa_pasien, \n" +
                        "jp.keterangan AS jenis_periksa\n" +
                        "FROM \n" +
                        "it_simrs_header_checkup hd\n" +
                        "INNER JOIN it_simrs_header_detail_checkup dt ON dt.no_checkup = hd.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien st ON st.id_status_pasien = dt.status_periksa\n" +
                        "INNER JOIN im_simrs_jenis_periksa_pasien jp ON jp.id_jenis_periksa_pasien = dt.id_jenis_periksa_pasien\n" +
                        "LEFT JOIN it_simrs_rawat_inap ri ON ri.id_detail_checkup = dt.id_detail_checkup\n" +
                        "WHERE ri.id_detail_checkup is null\n" +
                        "AND hd.branch_id LIKE :branchId \n" +
                        "AND hd.id_pasien LIKE :idPasien \n" +
                        "AND hd.nama LIKE :nama \n" +
                        "AND dt.id_pelayanan LIKE :idPelayanan \n" +
                        "AND dt.id_jenis_periksa_pasien LIKE :jenisPasien \n" +
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
                        headerDetailCheckup.setJenisPeriksaPasien(obj[11] == null ? "" : obj[11].toString());

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
                        "b.id_jenis_periksa_pasien,\n" +
                        "jp.keterangan AS jenis_periksa\n" +
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                        "INNER JOIN im_simrs_jenis_periksa_pasien jp ON jp.id_jenis_periksa_pasien = b.id_jenis_periksa_pasien\n" +
                        "INNER JOIN it_simrs_rawat_inap d ON b.id_detail_checkup = d.id_detail_checkup\n" +
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
                        "AND b.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
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
                        headerDetailCheckup.setJenisPeriksaPasien(obj[17] == null ? "" : obj[17].toString());

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

    public HeaderDetailCheckup getCoverBiayaAsuransi(String idDetailCheckup) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
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
                    "LEFT JOIN ( \n" +
                    "\tSELECT \n" +
                    "\ta.id_detail_checkup, (c.qty * d.harga_jual) as total_resep FROM mt_simrs_permintaan_resep a \n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat \n" +
                    "\tINNER JOIN (\n" +
                    "\t\tSELECT id_transaksi_obat_detail, SUM(qty_approve) as qty \n" +
                    "\t\tFROM mt_simrs_transaksi_obat_detail_batch  \n" +
                    "\t\tWHERE approve_flag = 'Y' \n" +
                    "\t\tGROUP BY id_transaksi_obat_detail\n" +
                    "\t) c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail \n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat \n" +
                    "\tWHERE a.jenis_resep != 'umum'\n" +
                    ") e ON a.id_detail_checkup = e.id_detail_checkup  \n" +
                    "WHERE a.id_detail_checkup = :id";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if (result.size() > 0) {
                Object[] objects = result.get(0);
                if (objects != null) {
                    BigDecimal coverBiaya = new BigDecimal(String.valueOf("0"));
                    BigDecimal tindakan = new BigDecimal(String.valueOf("0"));
                    BigDecimal lab = new BigDecimal(String.valueOf("0"));
                    BigDecimal radiologi = new BigDecimal(String.valueOf("0"));
                    BigDecimal resep = new BigDecimal(String.valueOf("0"));
                    BigDecimal totalTindakan = new BigDecimal(String.valueOf("0"));

                    if (objects[3] != null && !"".equalsIgnoreCase(objects[3].toString())) {
                        coverBiaya = new BigDecimal(objects[3].toString());

                        if (objects[4] != null && !"".equalsIgnoreCase(objects[4].toString())) {
                            tindakan = new BigDecimal(objects[4].toString());
                        }
                        if (objects[5] != null && !"".equalsIgnoreCase(objects[5].toString())) {
                            lab = new BigDecimal(objects[5].toString());
                        }
                        if (objects[6] != null && !"".equalsIgnoreCase(objects[6].toString())) {
                            radiologi = new BigDecimal(objects[6].toString());
                        }
                        if (objects[7] != null && !"".equalsIgnoreCase(objects[7].toString())) {
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

    public HeaderDetailCheckup getTotalBiayaTindakanBpjs(String idDetailCheckup) {

        HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();

        if (idDetailCheckup != null && !"".equalsIgnoreCase(idDetailCheckup)) {
            String SQL = "SELECT\n" +
                    "a.id_detail_checkup,\n" +
                    "a.no_sep,\n" +
                    "a.tarif_bpjs,\n" +
                    "b.total_tindakan,\n" +
                    "c.total_pemeriksaan,\n" +
                    "d.total_resep, \n" +
                    "a.id_jenis_periksa_pasien, \n" +
                    "a.kode_cbg\n" +
                    "FROM it_simrs_header_detail_checkup a\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT \n" +
                    "\tid_detail_checkup, \n" +
                    "\tSUM(tarif_total) as total_tindakan\n" +
                    "\tFROM it_simrs_tindakan_rawat \n" +
                    "\tGROUP BY id_detail_checkup\n" +
                    ") b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                    "LEFT JOIN(\n" +
                    "\tSELECT \n" +
                    "\taa.id_detail_checkup,\n" +
                    "\tSUM(aa.total_pemeriksaan) as total_pemeriksaan\n" +
                    "\tFROM (\n" +
                    "\t\tSELECT \n" +
                    "\t\ta.id_detail_checkup,\n" +
                    "\t\tCASE \n" +
                    "\t\tWHEN a.is_periksa_luar = 'Y' THEN a.tarif_lab_luar\n" +
                    "\t\tELSE c.tarif END as total_pemeriksaan\n" +
                    "\t\tFROM it_simrs_header_pemeriksaan a\n" +
                    "\t\tINNER JOIN it_simrs_periksa_lab b ON a.id_header_pemeriksaan = b.id_header_pemeriksaan\n" +
                    "\t\tINNER JOIN it_simrs_periksa_lab_detail c ON b.id_periksa_lab = c.id_periksa_lab\n" +
                    "\t)aa\n" +
                    "\tGROUP BY aa.id_detail_checkup\n" +
                    ") c ON a.id_detail_checkup = c.id_detail_checkup\n" +
                    "LEFT JOIN (\n" +
                    "\tSELECT \n" +
                    "\ta.id_detail_checkup, \n" +
                    "\t(c.qty * d.harga_jual) as total_resep \n" +
                    "\tFROM mt_simrs_permintaan_resep a\n" +
                    "\tINNER JOIN mt_simrs_transaksi_obat_detail b ON a.id_approval_obat = b.id_approval_obat\n" +
                    "\tINNER JOIN (SELECT id_transaksi_obat_detail, SUM(qty_approve) as qty FROM mt_simrs_transaksi_obat_detail_batch\n" +
                    "\tWHERE approve_flag = 'Y'\n" +
                    "\tGROUP BY id_transaksi_obat_detail) c ON b.id_transaksi_obat_detail = c.id_transaksi_obat_detail\n" +
                    "\tINNER JOIN mt_simrs_harga_obat d ON b.id_obat = d.id_obat\n" +
                    ") d ON a.id_detail_checkup = d.id_detail_checkup\n" +
                    "WHERE a.id_detail_checkup = :id ";

            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idDetailCheckup)
                    .list();
            if (result.size() > 0) {
                Object[] objects = result.get(0);
                if (objects != null) {
                    BigDecimal tarifBpjs = new BigDecimal(String.valueOf("0"));
                    BigDecimal tindakan = new BigDecimal(String.valueOf("0"));
                    BigDecimal penunjangmedis = new BigDecimal(String.valueOf("0"));
                    BigDecimal resep = new BigDecimal(String.valueOf("0"));
                    BigDecimal totalTindakan = new BigDecimal(String.valueOf("0"));

                    detailCheckup.setIdDetailCheckup(objects[0] == null ? "" : objects[0].toString());
                    detailCheckup.setNoSep(objects[1] == null ? "" : objects[1].toString());

                    if (objects[2] != null && !"".equalsIgnoreCase(objects[2].toString())) {
                        tarifBpjs = new BigDecimal(objects[2].toString());

                        if (objects[3] != null && !"".equalsIgnoreCase(objects[3].toString())) {
                            tindakan = new BigDecimal(objects[3].toString());
                        }
                        if (objects[4] != null && !"".equalsIgnoreCase(objects[4].toString())) {
                            penunjangmedis = new BigDecimal(objects[4].toString());
                        }
                        if (objects[5] != null && !"".equalsIgnoreCase(objects[5].toString())) {
                            resep = new BigDecimal(objects[5].toString());
                        }

                        totalTindakan = tindakan.add(penunjangmedis).add(resep);
                    }

                    detailCheckup.setTarifBpjs(tarifBpjs);
                    detailCheckup.setTarifTindakan(totalTindakan);
                    detailCheckup.setIdJenisPeriksaPasien(objects[6] == null ? "" : objects[6].toString());
                    detailCheckup.setKodeCbg(objects[7] == null ? "" : objects[7].toString());
                }
            }
        }
        return detailCheckup;
    }

    public List<HeaderDetailCheckup> getListRawatInapExisting(String branchId) {

        String SQL = "SELECT \n" +
                "a.id_detail_checkup,\n" +
                "a.id_jenis_periksa_pasien,\n" +
                "a.id_pelayanan,\n" +
                "b.tipe_pelayanan\n" +
                "FROM it_simrs_header_detail_checkup a\n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id," +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) b ON b.id_pelayanan = a.id_pelayanan\n" +
                "WHERE b.tipe_pelayanan = 'rawat_inap'\n" +
                "AND a.status_periksa = '1'\n" +
//                "AND a.id_jenis_periksa_pasien = 'bpjs' \n" +
                "AND a.branch_id = :branchId ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .list();

        List<HeaderDetailCheckup> headerDetailCheckups = new ArrayList<>();
        if (results.size() > 0) {
            HeaderDetailCheckup detailCheckup;
            for (Object[] obj : results) {
                detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setIdDetailCheckup(obj[0].toString());
                detailCheckup.setIdJenisPeriksaPasien(obj[1].toString());
                detailCheckup.setIdPelayanan(obj[2].toString());
                detailCheckup.setTipePelayanan(obj[3].toString());
                headerDetailCheckups.add(detailCheckup);
            }
        }

        return headerDetailCheckups;
    }

    public List<HeaderDetailCheckup> getListPasienRekamMedik(HeaderDetailCheckup bean) {

        List<HeaderDetailCheckup> headerDetailCheckups = new ArrayList<>();
        String idPasien = "%";
        String nama = "%";
        String jenisKelamin = "%";
        String branchId = "%";

        if (bean != null) {
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = "%" + bean.getIdPasien() + "%";
            }
            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = "%" + bean.getNamaPasien() + "%";
            }
            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())) {
                jenisKelamin = bean.getJenisKelamin();
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            String SQL = "SELECT \n" +
                    "b.id_pasien,\n" +
                    "b.nama,\n" +
                    "b.jenis_kelamin,\n" +
                    "b.tempat_lahir,\n" +
                    "b.tgl_lahir,\n" +
                    "c.desa_name,\n" +
                    "a.tgl_keluar,\n" +
                    "a.created_date,\n" +
                    "a.nama_pelayanan,\n" +
                    "a.id_detail_checkup,\n" +
                    "a.no_checkup\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT * FROM (\n" +
                    "SELECT\n" +
                    "b.id_detail_checkup,\n" +
                    "a.no_checkup,\n" +
                    "a.id_pasien, \n" +
                    "c.nama_pelayanan, \n" +
                    "rank() OVER (PARTITION BY a.id_pasien ORDER BY b.created_date DESC) as rank,\n" +
                    "a.tgl_keluar, \n" +
                    "b.created_date\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN (SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "a.branch_id," +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE b.status_periksa = '3' \n" +
                    "AND a.tgl_keluar IS NOT NULL \n" +
                    "AND a.branch_id LIKE :branchId\n" +
                    ") aa WHERE aa.rank = '1' \n" +
                    ") a \n" +
                    "INNER JOIN im_simrs_pasien b ON a.id_pasien = b.id_pasien\n" +
                    "INNER JOIN im_hris_desa c ON CAST(b.desa_id AS VARCHAR) = c.desa_id\n" +
                    "WHERE b.id_pasien LIKE :idPasien\n" +
                    "AND b.nama ILIKE :nama\n" +
                    "AND b.jenis_kelamin LIKE :jenisKelamin";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("branchId", branchId)
                    .setParameter("idPasien", idPasien)
                    .setParameter("nama", nama)
                    .setParameter("jenisKelamin", jenisKelamin)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    String jk = "";
                    detailCheckup.setIdPasien(obj[0] != null ? obj[0].toString() : "");
                    detailCheckup.setNamaPasien(obj[1] != null ? obj[1].toString() : "");
                    if (obj[2] != null) {
                        if ("L".equalsIgnoreCase(obj[2].toString())) {
                            jk = "Laki-Laki";
                        }
                        if ("P".equalsIgnoreCase(obj[2].toString())) {
                            jk = "Perempuan";
                        }

                        detailCheckup.setJenisKelamin(jk);
                    }
                    detailCheckup.setTempatLahir(obj[3] != null ? obj[3].toString() : "");
                    detailCheckup.setTglLahir(obj[4] != null ? obj[4].toString() : "");
                    detailCheckup.setDesa(obj[5] != null ? obj[5].toString() : "");
                    if (!"".equalsIgnoreCase(detailCheckup.getTempatLahir()) && !"".equalsIgnoreCase(detailCheckup.getTglLahir())) {
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy").format(java.sql.Date.valueOf(detailCheckup.getTglLahir()));
                        detailCheckup.setTempatTglLahir(detailCheckup.getTempatLahir() + ", " + formatDate);
                    }

                    if (obj[7] != null && obj[8] != null) {
                        String tglPeriksa = new SimpleDateFormat("dd-MM-yyyy").format(Timestamp.valueOf(obj[7].toString()));
                        detailCheckup.setPemeriksaanTerakhir(tglPeriksa + ", " + obj[8].toString());
                    }
                    detailCheckup.setIdDetailCheckup(obj[9] != null ? obj[9].toString() : "");
                    detailCheckup.setNoCheckup(obj[10] != null ? obj[10].toString() : "");
                    headerDetailCheckups.add(detailCheckup);
                }
            }
        }

        return headerDetailCheckups;
    }

    public List<HeaderDetailCheckup> getDetailListRekamMedis(String idPasien) {

        List<HeaderDetailCheckup> headerDetailCheckups = new ArrayList<>();
        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {

            String SQL = "SELECT \n" +
                    "a.no_checkup,\n" +
                    "a.created_date,\n" +
                    "a.tgl_keluar,\n" +
                    "b.id_detail_checkup,\n" +
                    "b.keterangan_selesai,\n" +
                    "c.nama_pelayanan\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN (SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "a.branch_id," +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE a.id_pasien = :idPasien AND b.status_periksa = '3'";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPasien", idPasien)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                    detailCheckup.setNoCheckup(obj[0] != null ? obj[0].toString() : "");
                    if (obj[1] != null) {
                        String tglMasuk = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Timestamp.valueOf(obj[1].toString()));
                        detailCheckup.setStTanggalMasuk(tglMasuk);
                    }
                    if (obj[2] != null) {
                        String tglKeluar = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(Timestamp.valueOf(obj[2].toString()));
                        detailCheckup.setStTanggalKeluar(tglKeluar);
                    }
                    detailCheckup.setIdDetailCheckup(obj[3] != null ? obj[3].toString() : "");
                    detailCheckup.setKeteranganSelesai(obj[4] != null ? obj[4].toString() : "");
                    detailCheckup.setNamaPelayanan(obj[5] != null ? obj[5].toString() : "");
                    headerDetailCheckups.add(detailCheckup);
                }
            }
        }
        return headerDetailCheckups;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_checkup')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public BigDecimal getBiayaByNoSep(String nosep) {
        BigDecimal total = new BigDecimal(0);
        String query = "SELECT\n" +
                "\tsum(total_tarif) as total_tarif\n" +
                "FROM\n" +
                "\tit_simrs_header_detail_checkup dc\n" +
                "\tLEFT JOIN it_simrs_riwayat_tindakan rt ON dc.id_detail_checkup=rt.id_detail_checkup\n" +
                "WHERE\n" +
                "\tdc.no_sep='" + nosep + "'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results != null) {
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        } else {
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public List<HeaderDetailCheckup> getListVerifTransaksi(HeaderDetailCheckup detailCheckup) {
        List<HeaderDetailCheckup> response = new ArrayList<>();
        if (detailCheckup != null) {
            String idPasien = "";
            String idPelayanan = "";
            String nama = "";
            String idDetailCheckup = "";
            String flagCloseTransaksi = "";
            String branchId = "";
            String tipePelayanan = "";
            String isNull = "";
            String idJenisPeriksaPasien = "";
            String tanggal = "";

            if (detailCheckup.getIdPasien() != null && !"".equalsIgnoreCase(detailCheckup.getIdPasien())) {
                idPasien = "AND a.id_pasien LIKE '%" + detailCheckup.getIdPasien() + "%' \n";
            }
            if (detailCheckup.getIdPelayanan() != null && !"".equalsIgnoreCase(detailCheckup.getIdPelayanan())) {
                idPelayanan = "AND b.id_pelayanan = '" + detailCheckup.getIdPelayanan() + "' \n";
            }
            if (detailCheckup.getNamaPasien() != null && !"".equalsIgnoreCase(detailCheckup.getNamaPasien())) {
                nama = "AND a.nama ILIKE '%" + detailCheckup.getNamaPasien() + "%' \n";
            }
            if (detailCheckup.getIdDetailCheckup() != null && !"".equalsIgnoreCase(detailCheckup.getIdDetailCheckup())) {
                idDetailCheckup = "AND b.id_detail_checkup LIKE '%" + detailCheckup.getIdDetailCheckup() + "%' \n";
            }

            if ("asuransi".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                idJenisPeriksaPasien = "AND b.id_jenis_periksa_pasien = 'asuransi' \n";
                if (detailCheckup.getFlagCover() != null && !"".equalsIgnoreCase(detailCheckup.getFlagCover())) {
                    flagCloseTransaksi = "AND b.flag_cover IS NOT NULL \n";
                } else {
                    flagCloseTransaksi = "AND b.flag_cover IS NULL \n";
                }
            } else if ("rekanan".equalsIgnoreCase(detailCheckup.getIdJenisPeriksaPasien())) {
                idJenisPeriksaPasien = "AND b.id_jenis_periksa_pasien = 'rekanan' \n";
            } else {
                idJenisPeriksaPasien = "AND b.id_jenis_periksa_pasien NOT IN ('bpjs', 'bpjs_rekanan','asuransi', 'rekanan')\n";
                if (detailCheckup.getFlagCloseTraksaksi() != null && !"".equalsIgnoreCase(detailCheckup.getFlagCloseTraksaksi())) {
                    flagCloseTransaksi = "AND b.flag_close_traksaksi IS NOT NULL \n";
                } else {
                    flagCloseTransaksi = "AND b.flag_close_traksaksi IS NULL \n";
                }
            }

            if (detailCheckup.getTipePelayanan().equalsIgnoreCase("rawat_jalan")) {
                tipePelayanan = "LEFT JOIN it_simrs_rawat_inap d ON b.id_detail_checkup = d.id_detail_checkup \n";
                isNull = "AND d.id_detail_checkup IS NULL AND b.tindak_lanjut IN ('selesai', 'kontrol_ulang')\n";
            } else {
                tipePelayanan = "INNER JOIN " +
                        "(SELECT * FROM (SELECT *, \n" +
                        "rank() OVER (PARTITION BY id_detail_checkup ORDER BY created_date ASC) \n" +
                        "FROM it_simrs_rawat_inap\n" +
                        ") a WHERE a.rank = 1) d ON b.id_detail_checkup = d.id_detail_checkup \n";
            }

            if (detailCheckup.getBranchId() != null && !"".equalsIgnoreCase(detailCheckup.getBranchId())) {
                branchId = "AND a.branch_id LIKE '" + detailCheckup.getBranchId() + "'";
            } else {
                branchId = "AND a.branch_id LIKE '%'";
            }

            if (detailCheckup.getStDateFrom() != null && !"".equalsIgnoreCase(detailCheckup.getStDateFrom()) &&
                    detailCheckup.getStDateTo() != null && !"".equalsIgnoreCase(detailCheckup.getStDateTo())) {
                tanggal = "AND CAST(b.created_date AS DATE) >= to_date('" + detailCheckup.getStDateFrom() + "','dd-MM-yyyy') AND CAST(b.created_date AS DATE) <= to_date('" + detailCheckup.getStDateTo() + "','dd-MM-yyyy') \n";
            } else if (detailCheckup.getStDateFrom() != null && !"".equalsIgnoreCase(detailCheckup.getStDateFrom())) {
                tanggal = "AND CAST(b.created_date AS DATE) >= to_date('" + detailCheckup.getStDateFrom() + "','dd-MM-yyyy') \n";
            } else if (detailCheckup.getStDateTo() != null && !"".equalsIgnoreCase(detailCheckup.getStDateTo())) {
                tanggal = "AND CAST(b.created_date AS DATE) <= to_date('" + detailCheckup.getStDateTo() + "','dd-MM-yyyy') \n";
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
                    "b.flag_close_traksaksi, \n" +
                    "b.metode_pembayaran, \n" +
                    "b.id_jenis_periksa_pasien,\n" +
                    "b.flag_cover,\n" +
                    "a.created_date,\n" +
                    "b.nama_pelayanan\n"+
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN (" +
                    "SELECT a.* FROM(\n" +
                    "SELECT\n" +
                    "\t\ta.no_checkup,\n" +
                    "\t\ta.id_detail_checkup,\n" +
                    "\t\ta.keterangan_selesai,\n" +
                    "\t\ta.status_periksa,\n" +
                    "\t\ta.tindak_lanjut,\n" +
                    "\t\ta.catatan,\n" +
                    "\t\ta.flag_close_traksaksi, \n" +
                    "\t\ta.metode_pembayaran, \n" +
                    "\t\ta.id_jenis_periksa_pasien,\n" +
                    "\t\ta.flag_cover,\n" +
                    "\t\ta.id_pelayanan,\n" +
                    "\t\tc.nama_pelayanan,\n" +
                    "\t\ta.created_date,\n"+
                    "\t\trank() OVER (PARTITION BY a.no_checkup, a.id_jenis_periksa_pasien ORDER BY a.created_date DESC) as rank \n" +
                    "\t\tFROM it_simrs_header_detail_checkup a\n" +
                    "\t\tINNER JOIN im_simrs_pelayanan b ON a.id_pelayanan = b.id_pelayanan\n" +
                    "\t\tINNER JOIN im_simrs_header_pelayanan c ON b.id_header_pelayanan = c.id_header_pelayanan\n"+
                    ") a WHERE a.rank = 1" +
                    ") b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_jenis_periksa_pasien c ON b.id_jenis_periksa_pasien = c.id_jenis_periksa_pasien\n"
                    + tipePelayanan +
                    "WHERE b.status_periksa = '3' \n" + idJenisPeriksaPasien
                    + isNull + idPasien + idPelayanan +
                    nama + idDetailCheckup +
                    flagCloseTransaksi + branchId + tanggal;
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .list();
            if (result.size() > 0) {
                for (Object[] obj : result) {
                    HeaderDetailCheckup detail = new HeaderDetailCheckup();
                    detail.setNoCheckup(obj[0] == null ? null : obj[0].toString());
                    detail.setIdPasien(obj[1] == null ? null : obj[1].toString());
                    detail.setNamaPasien(obj[2] == null ? null : obj[2].toString());
                    detail.setIdDetailCheckup(obj[3] == null ? null : obj[3].toString());
                    detail.setKeteranganSelesai(obj[4] == null ? null : obj[4].toString());
                    detail.setTindakLanjut(obj[5] == null ? null : obj[5].toString());
                    detail.setCatatan(obj[6] == null ? null : obj[6].toString());
                    detail.setJenisPeriksaPasien(obj[8] == null ? null : obj[8].toString());
                    detail.setFlagCloseTraksaksi(obj[9] == null ? null : obj[9].toString());
                    detail.setMetodePembayaran(obj[10] == null ? null : obj[10].toString());
                    detail.setIdJenisPeriksaPasien(obj[11] == null ? null : obj[11].toString());
                    detail.setFlagCover(obj[12] == null ? null : obj[12].toString());
                    detail.setCreatedDate(obj[13] == null ? null : (Timestamp) obj[13]);
                    if(detail.getCreatedDate() != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(detail.getCreatedDate());
                        detail.setFormatTglMasuk(formatDate);
                    }
                    detail.setNamaPelayanan(obj[14] == null ? null : obj[14].toString());
                    response.add(detail);
                }
            }
        }
        return response;
    }

    public List<HeaderDetailCheckup> getIDDetailCheckup(String noCheckup, String status, String jenisPasien) {
        String statusPeriksa = "";
        String jenisPs = "";
        if (status != null && !"".equalsIgnoreCase(status)) {
            statusPeriksa = "AND b.status_periksa = '" + status + "'\n";
        }
        if (jenisPasien != null && !"".equalsIgnoreCase(jenisPasien)) {
            jenisPs = "AND b.id_jenis_periksa_pasien = '" + jenisPasien + "' \n";
        }
        List<HeaderDetailCheckup> detailCheckupList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.no_checkup,\n" +
                "b.id_detail_checkup,\n" +
                "b.id_pelayanan\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "WHERE a.no_checkup = :id \n" + statusPeriksa + jenisPs;
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                HeaderDetailCheckup detailCheckup = new HeaderDetailCheckup();
                detailCheckup.setNoCheckup(obj[0] != null ? obj[0].toString() : "");
                detailCheckup.setIdDetailCheckup(obj[1] != null ? obj[1].toString() : "");
                detailCheckup.setIdPelayanan(obj[2] != null ? obj[2].toString() : "");
                detailCheckupList.add(detailCheckup);
            }
        }
        return detailCheckupList;
    }
}
