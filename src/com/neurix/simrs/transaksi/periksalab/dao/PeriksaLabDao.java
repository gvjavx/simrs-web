package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.periksalab.model.ItSimrsPeriksaLabEntity;
import com.neurix.simrs.transaksi.periksalab.model.PeriksaLab;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class PeriksaLabDao extends GenericDao<ItSimrsPeriksaLabEntity, String> {
    @Override
    protected Class<ItSimrsPeriksaLabEntity> getEntityClass() {
        return ItSimrsPeriksaLabEntity.class;
    }

    @Override
    public List<ItSimrsPeriksaLabEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaLabEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_periksa_lab") != null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("id_header_pemeriksaan") != null) {
                criteria.add(Restrictions.eq("idHeaderPemeriksaan", (String) mapCriteria.get("id_header_pemeriksaan")));
            }
            if (mapCriteria.get("id_pemeriksaan") != null) {
                criteria.add(Restrictions.eq("idPemeriksaan", (String) mapCriteria.get("id_pemeriksaan")));
            }
            if (mapCriteria.get("nama_pemeriksaan") != null) {
                criteria.add(Restrictions.eq("namaPemeriksaan", (String) mapCriteria.get("nama_pemeriksaan")));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }

        criteria.addOrder(Order.asc("idPeriksaLab"));
        List<ItSimrsPeriksaLabEntity> results = criteria.list();
        return results;
    }

    public List<PeriksaLab> getSearchLab(PeriksaLab bean) {
        List<PeriksaLab> checkupList = new ArrayList<>();
        if (bean != null) {
            String flag = "Y";
            String condition = "";
            if (bean.getFlag() != null && !"".equalsIgnoreCase(bean.getFlag())) {
                flag = bean.getFlag();
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                condition = "AND e.id_pasien = '" + bean.getIdPasien() + "' \n";
            }
            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                condition = condition + "AND d.nama ILIKE '%" + bean.getIdPasien() + "%' \n";
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                condition = condition + "AND a.id_detail_checkup = '" + bean.getIdDetailCheckup() + "' \n";
            }
            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                condition = condition + "AND a.status_periksa = '" + bean.getStatusPeriksa() + "' \n";
            }
            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                condition = condition + "AND b.kategori = '" + bean.getIdKategoriLab() + "' \n";
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom()) && bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                condition = condition + "AND CAST(a.created_date AS date) >= to_date('" + bean.getStDateFrom() + "', 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date('" + bean.getStDateTo() + "', 'dd-MM-yyyy') \n";
            }else if(bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())){
                condition = condition + "AND CAST(a.created_date AS date) >= to_date('" + bean.getStDateFrom() + "', 'dd-MM-yyyy') \n";
            }else if(bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())){
                condition = condition + "AND CAST(a.created_date AS date) <= to_date('" + bean.getStDateTo() + "', 'dd-MM-yyyy') \n";
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                condition = condition + "AND d.branch_id = '" + bean.getBranchId() + "' \n";
            }
            if (bean.getIdHeaderPemeriksaan() != null && !"".equalsIgnoreCase(bean.getIdHeaderPemeriksaan())) {
                condition = condition + "AND a.id_header_pemeriksaan = '" + bean.getIdHeaderPemeriksaan() + "' \n";
            }

            String SQL = "SELECT\n" +
                    "a.id_header_pemeriksaan,\n" +
                    "a.id_kategori_lab,\n" +
                    "b.nama_kategori,\n" +
                    "a.created_date,\n" +
                    "a.status_periksa,\n" +
                    "a.id_detail_checkup,\n" +
                    "d.no_checkup,\n" +
                    "d.nama,\n" +
                    "d.id_pasien,\n" +
                    "a.approve_flag, \n" +
                    "a.catatan,\n" +
                    "c.id_jenis_periksa_pasien,\n" +
                    "um.id, \n" +
                    "um.status_bayar, \n" +
                    "a.is_pending,\n" +
                    "a.is_periksa_luar,\n" +
                    "a.is_just_lab\n" +
                    "FROM it_simrs_header_pemeriksaan a\n" +
                    "INNER JOIN im_simrs_kategori_lab b ON a.id_kategori_lab = b.id_kategori_lab\n" +
                    "INNER JOIN it_simrs_header_detail_checkup c ON a.id_detail_checkup = c.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup d ON c.no_checkup = d.no_checkup\n" +
                    "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = a.id_detail_checkup\n" +
                    "WHERE a.flag = :flag \n"+condition+
                    "ORDER BY a.created_date ASC";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PeriksaLab dataLab = new PeriksaLab();
                    dataLab.setIdHeaderPemeriksaan(obj[0] != null ? obj[0].toString() : null);
                    dataLab.setIdKategoriLab(obj[1] != null ? obj[1].toString() : null);
                    dataLab.setKategoriLabName(obj[2] != null ? obj[2].toString() : null);
                    dataLab.setCreatedDate(obj[3] != null ? (Timestamp) obj[3] : null);
                    if(obj[3] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(dataLab.getCreatedDate());
                        dataLab.setStCreatedDate(formatDate);
                    }
                    dataLab.setStatusPeriksa(obj[4] != null ? obj[4].toString() : null);
                    dataLab.setIdDetailCheckup(obj[5] != null ? obj[5].toString() : null);
                    dataLab.setNoCheckup(obj[6] != null ? obj[6].toString() : null);
                    dataLab.setNamaPasien(obj[7] != null ? obj[7].toString() : null);
                    dataLab.setIdPasien(obj[8] != null ? obj[8].toString() : null);
                    dataLab.setApproveFlag(obj[9] != null ? obj[9].toString() : null);
                    dataLab.setCatatan(obj[10] != null ? obj[10].toString() : null);
                    dataLab.setIdJenisPeriksa(obj[11] != null ? obj[11].toString() : "");
                    if(obj[12] != null){
                        if("umum".equalsIgnoreCase(dataLab.getIdJenisPeriksa())){
                            String bayar = (obj[13] != null ? obj[13].toString() : "");
                            if ("Y".equalsIgnoreCase(bayar)) {
                                dataLab.setStatusBayar("Y");
                            } else {
                                dataLab.setStatusBayar("N");
                            }
                        }
                    }else{
                        dataLab.setStatusBayar("Y");
                    }
                    dataLab.setIsPending(obj[14] != null ? obj[14].toString() : "");
                    dataLab.setIsLuar(obj[15] != null ? obj[15].toString() : "");
                    dataLab.setIsJustLab(obj[16] != null ? obj[16].toString() : "");
                    checkupList.add(dataLab);
                }
            }
        }
        return checkupList;
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

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_periksa_lab')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "PRL"+sId;
    }

    public BigDecimal getTotalTarif(String idPeriksa) {
        BigDecimal res = null;
        if(idPeriksa != null && !"".equalsIgnoreCase(idPeriksa)){
            String SQL = "SELECT\n" +
                    "a.id_header_pemeriksaan,\n" +
                    "SUM(c.tarif) as total\n" +
                    "FROM it_simrs_header_pemeriksaan a\n" +
                    "INNER JOIN it_simrs_periksa_lab b ON a.id_header_pemeriksaan = b.id_header_pemeriksaan\n" +
                    "INNER JOIN it_simrs_periksa_lab_detail c ON b.id_periksa_lab = c.id_periksa_lab\n" +
                    "WHERE a.id_header_pemeriksaan = :id\n" +
                    "AND a.flag = 'Y'\n" +
                    "GROUP BY a.id_header_pemeriksaan";
            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPeriksa)
                    .list();
            if(results.size() > 0){
                Object[] obj = results.get(0);
                if(obj[1] != null){
                    res = (BigDecimal) obj[1];
                }
            }
        }
        return res;
    }

    public String getDivisiIdLabTransaction(String idDetailCheckup, String tipe) {

        String type = tipe;
        if("laboratorium".equalsIgnoreCase(tipe)){
            type = "lab";
        }
        String SQL = "SELECT\n" +
                "a.branch_id,\n" +
                "a.id_detail_checkup,\n" +
                "b.divisi_id,\n" +
                "c.kodering\n" +
                "FROM it_simrs_header_detail_checkup a \n" +
                "INNER JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "a.branch_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) b ON b.branch_id = a.branch_id \n" +
                "INNER JOIN im_position c ON b.divisi_id = c.position_id\n" +
                "WHERE a.id_detail_checkup = :idDetail \n" +
                "AND b.tipe_pelayanan ILIKE :tipe ";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetailCheckup)
                .setParameter("tipe", type)
                .list();

        String divisId = "";
        if (results.size() > 0) {
            for (Object[] obj : results) {
                divisId = obj[3] == null ? "" : obj[3].toString();
            }
        }
        return divisId;
    }

    public List<Dokter> getListDokterLabRadiologi(String tipe) {
        List<Dokter> dokterList = new ArrayList<>();
        if (tipe != null && !"".equalsIgnoreCase(tipe)) {
            String SQL = "SELECT \n" +
                    "a.id_dokter,\n" +
                    "a.nama_dokter\n" +
                    "FROM im_simrs_dokter a\n" +
                    "INNER JOIN im_simrs_dokter_pelayanan b ON a.id_dokter = b.id_dokter\n" +
                    "INNER JOIN (SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "a.branch_id, \n"+
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan,\n" +
                    "b.kategori_pelayanan,\n" +
                    "b.divisi_id,\n" +
                    "b.kode_vclaim\n" +
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan) c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "WHERE c.branch_id = :branchId\n" +
                    "AND c.tipe_pelayanan LIKE :tipe";

            List<Objects[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("tipe", tipe)
                    .setParameter("branchId", CommonUtil.userBranchLogin())
                    .list();

            if (result.size() > 0) {
                for (Object[] obj : result) {
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                    dokter.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                    dokterList.add(dokter);
                }
            }
        }
        return dokterList;
    }

    public List<PeriksaLab> getListLab(String noCheckup, String jenis) {
        List<PeriksaLab> labList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.id_header_pemeriksaan,\n" +
                "a.status_periksa,\n" +
                "a.id_detail_checkup,\n" +
                "e.id_kategori_lab,\n" +
                "e.nama_kategori,\n" +
                "f.id_pelayanan,\n" +
                "f.nama_pelayanan,\n" +
                "a.created_date,\n" +
                "e.kategori\n" +
                "FROM it_simrs_header_pemeriksaan a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_kategori_lab e ON a.id_kategori_lab = e.id_kategori_lab\n" +
                "LEFT JOIN (SELECT\n" +
                "a.id_pelayanan,\n" +
                "a.branch_id,\n" +
                "b.nama_pelayanan,\n" +
                "b.tipe_pelayanan,\n" +
                "b.kategori_pelayanan,\n" +
                "b.divisi_id,\n" +
                "b.kode_vclaim\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                ") f ON b.id_pelayanan = f.id_pelayanan\n" +
                "WHERE c.no_checkup = :id \n" +
                "AND b.id_jenis_periksa_pasien = :jen \n" +
                "AND a.flag = 'Y'\n" +
                "ORDER BY a.id_detail_checkup ASC";

        List<Objects[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .setParameter("jen", jenis)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                PeriksaLab lab = new PeriksaLab();
                lab.setIdPeriksaLab(obj[0] == null ? "" : obj[0].toString());
                lab.setStatusPeriksa(obj[1] == null ? "" : obj[1].toString());
                lab.setIdDetailCheckup(obj[2] == null ? "" : obj[2].toString());
                lab.setIdKategoriLab(obj[3] == null ? "" : obj[3].toString());
                lab.setKategoriLabName(obj[4] == null ? "" : obj[4].toString());
                lab.setIdPelayanan(obj[5] == null ? "" : obj[5].toString());
                lab.setNamaPelayanan(obj[6] == null ? "" : obj[6].toString());
                lab.setCreatedDate(obj[7] == null ? null : (Timestamp) obj[7]);
                lab.setKategori(obj[8] == null ? null : (String) obj[8]);
                labList.add(lab);
            }
        }
        return labList;
    }

    public List<PeriksaLab> pushNotifLab(String kategori, String branchId) {
        List<PeriksaLab> labList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.no_checkup,\n" +
                "b.id_detail_checkup,\n" +
                "c.id_header_pemeriksaan,\n" +
                "c.is_read,\n" +
                "d.kategori,\n" +
                "a.nama\n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN it_simrs_header_pemeriksaan c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN im_simrs_kategori_lab d ON c.id_kategori_lab = d.id_kategori_lab\n" +
                "WHERE a.branch_id = :branchId \n" +
                "AND c.is_read IS NULL\n" +
                "AND c.status_periksa = '0'\n" +
                "AND d.kategori = :kategori \n" +
                "AND CAST(c.created_date AS DATE) = CURRENT_DATE \n" +
                "ORDER BY c.created_date ASC";

        List<Objects[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("branchId", branchId)
                .setParameter("kategori", kategori)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                PeriksaLab lab = new PeriksaLab();
                lab.setNoCheckup(obj[0] == null ? "" : obj[0].toString());
                lab.setIdDetailCheckup(obj[1] == null ? "" : obj[1].toString());
                lab.setIdHeaderPemeriksaan(obj[2] == null ? "" : obj[2].toString());
                lab.setIsRead(obj[3] == null ? "" : obj[3].toString());
                lab.setKategori(obj[4] == null ? "" : obj[4].toString());
                lab.setNamaPasien(obj[5] == null ? "" : obj[5].toString());
                labList.add(lab);
            }
        }
        return labList;
    }

    public List<PeriksaLab> getHistoryLabRadiologi(String idPasien) {
        List<PeriksaLab> labList = new ArrayList<>();
        String SQL = "SELECT\n" +
                "c.tanggal_masuk_periksa,\n" +
                "a.id_pasien,\n" +
                "b.id_detail_checkup,\n" +
                "b.id_pelayanan,\n" +
                "e.nama_pelayanan,\n" +
                "c.id_header_pemeriksaan,\n" +
                "f.nama_kategori,\n" +
                "f.kategori,\n" +
                "a.created_date \n" +
                "FROM it_simrs_header_checkup a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                "INNER JOIN it_simrs_header_pemeriksaan c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                "INNER JOIN (\n" +
                "SELECT\n" +
                "a.id_pelayanan,\n" +
                "a.nama_pelayanan\n" +
                "FROM im_simrs_pelayanan a\n" +
                "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                ") e ON b.id_pelayanan = e.id_pelayanan\n" +
                "INNER JOIN im_simrs_kategori_lab f ON c.id_kategori_lab = f.id_kategori_lab\n" +
                "WHERE a.id_pasien = '"+idPasien+"'\n" +
                "ORDER BY c.tanggal_masuk_periksa,\n" +
                "b.id_pelayanan DESC";

        List<Objects[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .list();

        if (result.size() > 0) {
            String namaPelayanan = "";
            for (Object[] obj : result) {
                PeriksaLab lab = new PeriksaLab();
                if(!namaPelayanan.equalsIgnoreCase(obj[4].toString())){
                    namaPelayanan = obj[4].toString();
                    lab.setCreatedDate(obj[8] == null ? null : (Timestamp) obj[8]);
                }

                lab.setTanggalMasukLab(obj[0] == null ? null : (Timestamp) obj[0]);
                lab.setIdPasien(obj[1] == null ? "" : obj[1].toString());
                lab.setIdDetailCheckup(obj[2] == null ? "" : obj[2].toString());
                lab.setIdPelayanan(obj[3] == null ? "" : obj[3].toString());
                lab.setNamaPelayanan(namaPelayanan);
                lab.setIdPeriksaLab(obj[5] == null ? "" : obj[5].toString());
                lab.setKategoriLabName(obj[6] == null ? "" : obj[6].toString());
                lab.setKeterangan(obj[7] == null ? "" : obj[7].toString());
                labList.add(lab);
            }
        }
        return labList;
    }

    public List<PeriksaLab> getListParameterPemeriksaan(String idHeader) {
        List<PeriksaLab> labList = new ArrayList<>();
        if(idHeader != null && !"".equalsIgnoreCase(idHeader)){
            String SQL = "SELECT\n" +
                    "a.id_header_pemeriksaan,\n" +
                    "d.nama_kategori,\n" +
                    "b.id_pemeriksaan,\n" +
                    "b.nama_pemeriksaan,\n" +
                    "c.id_lab_detail,\n" +
                    "c.nama_detail_periksa,\n" +
                    "b.id_periksa_lab,\n" +
                    "c.id_periksa_lab_detail,\n" +
                    "c.keterangan_acuan_l,\n" +
                    "c.keterangan_acuan_p,\n" +
                    "c.satuan,\n" +
                    "c.hasil,\n" +
                    "c.keterangan_periksa\n" +
                    "FROM it_simrs_header_pemeriksaan a\n" +
                    "INNER JOIN it_simrs_periksa_lab b ON a.id_header_pemeriksaan = b.id_header_pemeriksaan\n" +
                    "INNER JOIN it_simrs_periksa_lab_detail c ON b.id_periksa_lab = c.id_periksa_lab\n" +
                    "INNER JOIN im_simrs_kategori_lab d ON a.id_kategori_lab = d.id_kategori_lab\n" +
                    "WHERE a.id_header_pemeriksaan = :id\n" +
                    "ORDER BY b.id_periksa_lab, c.id_lab_detail ASC\n";

            List<Objects[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idHeader)
                    .list();

            if (result.size() > 0) {
                for (Object[] obj : result) {
                    PeriksaLab lab = new PeriksaLab();
                    lab.setIdHeaderPemeriksaan(obj[0] == null ? null : (String) obj[0]);
                    lab.setKategoriLabName(obj[1] == null ? null : (String) obj[1]);
                    lab.setIdPemeriksaan(obj[2] == null ? null : (String) obj[2]);
                    lab.setNamaPemeriksaan(obj[3] == null ? null : (String) obj[3]);
                    lab.setIdDetailPemeriksaan(obj[4] == null ? null : (String) obj[4]);
                    lab.setNamaDetailPemeriksaan(obj[5] == null ? null : (String) obj[5]);
                    lab.setIdPeriksaLab(obj[6] == null ? null : (String) obj[6]);
                    lab.setIdPeriksaLabDetail(obj[7] == null ? null : (String) obj[7]);
                    lab.setKeteranganAcuanL(obj[8] == null ? null : (String) obj[8]);
                    lab.setKeteranganAcuanP(obj[9] == null ? null : (String) obj[9]);
                    lab.setSatuan(obj[10] == null ? null : (String) obj[10]);
                    lab.setHasil(obj[11] == null ? null : (String) obj[11]);
                    lab.setKeteranganHasil(obj[12] == null ? null : (String) obj[12]);
                    lab.setIsCatatan(cekDefultCatatan(obj[0].toString()));
                    labList.add(lab);
                }
            }
        }
        return labList;
    }

    private String cekDefultCatatan(String id){
        String res = "N";
        if(id != null && !"".equalsIgnoreCase(id)){
            String SQL = "SELECT \n" +
                    "a.id_header_pemeriksaan,\n" +
                    "b.id_pemeriksaan,\n" +
                    "c.is_catatan\n" +
                    "FROM it_simrs_header_pemeriksaan a\n" +
                    "INNER JOIN it_simrs_periksa_lab b ON a.id_header_pemeriksaan = b.id_header_pemeriksaan\n" +
                    "INNER JOIN im_simrs_lab c ON b.id_pemeriksaan = c.id_lab\n" +
                    "WHERE a.id_header_pemeriksaan = :id";
            List<Objects[]> result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .list();
            if(result.size() > 0){
                for (Object[] obj: result){
                    if(obj[2] != null){
                        if("Y".equalsIgnoreCase(obj[2].toString())){
                            res = "Y";
                        }
                    }
                }
            }
        }
        return res;
    }
}