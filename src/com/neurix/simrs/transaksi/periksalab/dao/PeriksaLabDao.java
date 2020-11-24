package com.neurix.simrs.transaksi.periksalab.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.simrs.master.dokter.model.Dokter;
import com.neurix.simrs.transaksi.checkupdetail.model.HeaderDetailCheckup;
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

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_periksa_lab") != null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("tanggal_masuk_lab") != null) {
                criteria.add(Restrictions.eq("tanggalMasukLab", (Date) mapCriteria.get("tanggal_masuk_lab")));
            }
            if (mapCriteria.get("tanggal_selesai_periksa") != null) {
                criteria.add(Restrictions.eq("tanggalSelesaiPeriksa", (Timestamp) mapCriteria.get("tanggal_selesai_periksa")));
            }
            if (mapCriteria.get("id_dokter_pengirim") != null) {
                criteria.add(Restrictions.eq("idDokterPengirim", (String) mapCriteria.get("id_dokter_pengirim")));
            }
            if (mapCriteria.get("id_dokter") != null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_pemeriksa") != null) {
                criteria.add(Restrictions.eq("idPemeriksa", (String) mapCriteria.get("id_pemeriksa")));
            }
            if (mapCriteria.get("id_lab") != null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("status") != null) {
                criteria.add(Restrictions.eq("statusPeriksa", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("approve_flag") != null) {
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
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
                condition = condition + "AND e.nama ILIKE '%" + bean.getIdPasien() + "%' \n";
            }
            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                condition = condition + "AND a.id_detail_checkup = '" + bean.getIdDetailCheckup() + "' \n";
            }
            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                condition = condition + "AND a.status_periksa = '" + bean.getStatusPeriksa() + "' \n";
            }
            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())) {
                condition = condition + "AND c.kategori = '" + bean.getIdKategoriLab() + "' \n";
            }
            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom()) && bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())) {
                condition = condition + "AND CAST(a.created_date AS date) >= to_date('" + bean.getStDateFrom() + "', 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date('" + bean.getStDateTo() + "', 'dd-MM-yyyy') \n";
            }else if(bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())){
                condition = condition + "AND CAST(a.created_date AS date) >= to_date('" + bean.getStDateFrom() + "', 'dd-MM-yyyy') \n";
            }else if(bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())){
                condition = condition + "AND CAST(a.created_date AS date) <= to_date('" + bean.getStDateTo() + "', 'dd-MM-yyyy') \n";
            }
            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                condition = condition + "AND e.branch_id = '" + bean.getBranchId() + "' \n";
            }
            if (bean.getIdPeriksaLab() != null && !"".equalsIgnoreCase(bean.getIdPeriksaLab())) {
                condition = condition + "AND a.id_periksa_lab = '" + bean.getIdPeriksaLab() + "' \n";
            }

            String SQL = "SELECT\n" +
                    "a.id_periksa_lab,\n" +
                    "a.id_lab,\n" +
                    "b.nama_lab,\n" +
                    "a.id_kategori_lab,\n" +
                    "c.nama_kategori,\n" +
                    "a.created_date,\n" +
                    "a.status_periksa,\n" +
                    "a.id_detail_checkup,\n" +
                    "d.no_checkup,\n" +
                    "e.nama,\n" +
                    "e.id_pasien,\n" +
                    "a.approve_flag, \n" +
                    "a.keterangan,\n" +
                    "d.id_jenis_periksa_pasien,\n"+
                    "um.id, \n"+
                    "um.status_bayar \n"+
                    "FROM it_simrs_periksa_lab a\n" +
                    "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                    "INNER JOIN im_simrs_kategori_lab c ON a.id_kategori_lab = c.id_kategori_lab\n" +
                    "INNER JOIN it_simrs_header_detail_checkup d ON a.id_detail_checkup = d.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup e ON d.no_checkup = e.no_checkup\n" +
                    "LEFT JOIN it_simrs_uang_muka_pendaftaran um ON um.id_detail_checkup = a.id_detail_checkup\n" +
                    "WHERE a.flag = :flag \n" + condition +
                    "ORDER BY a.created_date ASC";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PeriksaLab dataLab = new PeriksaLab();
                    dataLab.setIdPeriksaLab(obj[0] != null ? obj[0].toString() : null);
                    dataLab.setIdLab(obj[1] != null ? obj[1].toString() : null);
                    dataLab.setLabName(obj[2] != null ? obj[2].toString() : null);
                    dataLab.setIdKategoriLab(obj[3] != null ? obj[3].toString() : null);
                    dataLab.setKategoriLabName(obj[4] != null ? obj[4].toString() : null);
                    dataLab.setCreatedDate(obj[5] != null ? (Timestamp) obj[5] : null);
                    if(obj[5] != null){
                        String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(dataLab.getCreatedDate());
                        dataLab.setStCreatedDate(formatDate);
                    }
                    dataLab.setStatusPeriksa(obj[6] != null ? obj[6].toString() : null);
                    dataLab.setIdDetailCheckup(obj[7] != null ? obj[7].toString() : null);
                    dataLab.setNoCheckup(obj[8] != null ? obj[8].toString() : null);
                    dataLab.setNamaPasien(obj[9] != null ? obj[9].toString() : null);
                    dataLab.setIdPasien(obj[10] != null ? obj[10].toString() : null);
                    dataLab.setApproveFlag(obj[11] != null ? obj[11].toString() : null);
                    dataLab.setKeterangan(obj[12] != null ? obj[12].toString() : null);
                    dataLab.setIdJenisPeriksa(obj[13] != null ? obj[13].toString() : "");
                    if(obj[14] != null){
                        if("umum".equalsIgnoreCase(dataLab.getIdJenisPeriksa())){
                            String bayar = (obj[15] != null ? obj[15].toString() : "");
                            if ("Y".equalsIgnoreCase(bayar)) {
                                dataLab.setStatusBayar("Y");
                            } else {
                                dataLab.setStatusBayar("N");
                            }
                        }
                    }else{
                        dataLab.setStatusBayar("Y");
                    }
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
        return sId;
    }

    public BigDecimal getTotalTarif(String idPeriksa) {
        BigDecimal res = null;
        if(idPeriksa != null && !"".equalsIgnoreCase(idPeriksa)){
            String SQL = "SELECT\n" +
                    "id_periksa_lab,\n" +
                    "SUM(tarif) as total\n" +
                    "FROM it_simrs_periksa_lab_detail\n" +
                    "WHERE id_periksa_lab = :id \n" +
                    "AND flag = 'Y' \n" +
                    "GROUP BY id_periksa_lab";
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
//        String SQL = "SELECT c.id_kategori_lab, a.id_detail_checkup, d.kodering\n" +
//                "FROM it_simrs_periksa_lab a \n" +
//                "INNER JOIN im_simrs_lab b ON b.id_lab = a.id_lab\n" +
//                "INNER JOIN im_simrs_kategori_lab c ON c.id_kategori_lab = b.id_kategori_lab\n" +
//                "INNER JOIN im_position d ON d.position_id = c.divisi_id\n" +
//                "WHERE c.nama_kategori ILIKE :tipe\n" +
//                "AND a.id_detail_checkup ILIKE :idDetail\n" +
//                "ORDER BY a.last_update DESC LIMIT 1";
        String type = tipe;
        if("laboratorium".equalsIgnoreCase(tipe)){
            type = "lab";
        }
        String SQL = "SELECT\n" +
                "c.id_kategori_lab,\n" +
                "a.id_detail_checkup,\n" +
                "d.kodering\n" +
                "FROM it_simrs_periksa_lab a\n" +
                "INNER JOIN im_simrs_lab_detail lb ON a.id_lab = lb.id_lab\n" +
                "INNER JOIN im_simrs_parameter_pemeriksaan pp ON lb.id_parameter_pemeriksaan = pp.id_parameter_pemeriksaan\n" +
                "INNER JOIN im_simrs_kategori_lab c ON pp.id_kategori_lab = c.id_kategori_lab\n" +
                "INNER JOIN im_position d ON d.position_id = c.divisi_id\n" +
                "WHERE c.kategori = :tipe \n" +
                "AND a.id_detail_checkup ILIKE :idDetail \n" +
                "ORDER BY a.last_update DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetailCheckup)
                .setParameter("tipe", type)
                .list();

        String divisId = "";
        if (results.size() > 0) {
            for (Object[] obj : results) {
                divisId = obj[2] == null ? "" : obj[2].toString();
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
                    "INNER JOIN im_simrs_pelayanan c ON b.id_pelayanan = c.id_pelayanan\n" +
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

    public PeriksaLab getNamaLab(String idPeriksa) {
        PeriksaLab lab = new PeriksaLab();
        if (idPeriksa != null && !"".equalsIgnoreCase(idPeriksa)) {
            String SQL = "SELECT\n" +
                    "a.id_periksa_lab, \n" +
                    "a.id_lab,\n" +
                    "b.nama_lab,\n" +
                    "c.sip,\n" +
                    "c.id_dokter,\n" +
                    "c.nama_dokter,\n" +
                    "a.ttd_dokter,\n" +
                    "a.id_pemeriksa,\n" +
                    "d.user_name,\n" +
                    "a.ttd_petugas,\n" +
                    "e.sip as sip_pengirim,\n" +
                    "a.id_dokter_pengirim,\n" +
                    "e.nama_dokter as pengirim,\n" +
                    "a.ttd_pengirim, \n" +
                    "a.created_date\n" +
                    "FROM it_simrs_periksa_lab a\n" +
                    "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                    "LEFT JOIN im_simrs_dokter c ON a.id_dokter = c.id_dokter\n" +
                    "LEFT JOIN im_users d ON a.id_pemeriksa = d.user_id\n" +
                    "LEFT JOIN im_simrs_dokter e ON a.id_dokter_pengirim = e.id_dokter\n" +
                    "WHERE id_periksa_lab = :idPeriksaLab";
            List<Objects[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPeriksaLab", idPeriksa)
                    .list();
            if (result.size() > 0) {
                Object[] objects = result.get(0);
                lab.setIdPeriksaLab(objects[0] == null ? "" : objects[0].toString());
                lab.setIdLab(objects[1] == null ? "" : objects[1].toString());
                lab.setLabName(objects[2] == null ? "" : objects[2].toString());
                lab.setSipDokter(objects[3] == null ? "" : objects[3].toString());
                lab.setIdDokter(objects[4] == null ? "" : objects[4].toString());
                lab.setNamaDokter(objects[5] == null ? "" : objects[5].toString());
                lab.setTtdDokter(objects[6] == null ? "" : CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + objects[6].toString());
                lab.setIdPemeriksa(objects[7] == null ? "" : objects[7].toString());
                lab.setNamaPetugas(objects[8] == null ? "" : objects[8].toString());
                lab.setTtdPetugas(objects[9] == null ? "" : CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + objects[9].toString());
                lab.setSipPengirim(objects[10] == null ? "" : objects[10].toString());
                lab.setIdPengirim(objects[11] == null ? "" : objects[11].toString());
                lab.setDokterPengirim(objects[12] == null ? "" : objects[12].toString());
                lab.setTtdPengirim(objects[13] == null ? "" : CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY + CommonConstant.RESOURCE_PATH_TTD_DOKTER + objects[13].toString());
                lab.setCreatedDate(objects[14] == null ? null : (Timestamp) objects[14]);
            }
        }
        return lab;
    }

    public List<PeriksaLab> getListLab(String noCheckup) {
        List<PeriksaLab> labList = new ArrayList<>();
        String SQL = "SELECT \n" +
                "a.id_periksa_lab,\n" +
                "a.id_lab,\n" +
                "d.nama_lab,\n" +
                "a.status_periksa,\n" +
                "a.id_detail_checkup,\n" +
                "e.id_kategori_lab,\n" +
                "e.nama_kategori,\n" +
                "f.id_pelayanan,\n" +
                "f.nama_pelayanan,\n" +
                "a.created_date,\n" +
                "e.kategori\n" +
                "FROM it_simrs_periksa_lab a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_lab d ON a.id_lab = d.id_lab\n" +
                "INNER JOIN im_simrs_kategori_lab e ON a.id_kategori_lab = e.id_kategori_lab\n" +
                "LEFT JOIN im_simrs_pelayanan f ON b.id_pelayanan = f.id_pelayanan\n" +
                "WHERE c.no_checkup = :id \n" +
                "AND a.flag = 'Y'\n" +
                "ORDER BY a.id_detail_checkup ASC";

        List<Objects[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .list();
        if (result.size() > 0) {
            for (Object[] obj : result) {
                PeriksaLab lab = new PeriksaLab();
                lab.setIdPeriksaLab(obj[0] == null ? "" : obj[0].toString());
                lab.setIdLab(obj[1] == null ? "" : obj[1].toString());
                lab.setLabName(obj[2] == null ? "" : obj[2].toString());
                lab.setStatusPeriksa(obj[3] == null ? "" : obj[3].toString());
                lab.setIdDetailCheckup(obj[4] == null ? "" : obj[4].toString());
                lab.setIdKategoriLab(obj[5] == null ? "" : obj[5].toString());
                lab.setKategoriLabName(obj[6] == null ? "" : obj[6].toString());
                lab.setIdPelayanan(obj[7] == null ? "" : obj[7].toString());
                lab.setNamaPelayanan(obj[8] == null ? "" : obj[8].toString());
                lab.setCreatedDate(obj[9] == null ? null : (Timestamp) obj[9]);
                lab.setKategori(obj[10] == null ? null : (String) obj[10]);
                labList.add(lab);
            }
        }
        return labList;
    }
}