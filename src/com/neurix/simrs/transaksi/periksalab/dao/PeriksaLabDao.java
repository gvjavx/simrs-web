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
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_periksa_lab")!=null) {
                criteria.add(Restrictions.eq("idPeriksaLab", (String) mapCriteria.get("id_periksa_lab")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("tanggal_masuk_lab")!=null) {
                criteria.add(Restrictions.eq("tanggalMasukLab", (Date) mapCriteria.get("tanggal_masuk_lab")));
            }
            if (mapCriteria.get("tanggal_selesai_periksa")!=null) {
                criteria.add(Restrictions.eq("tanggalSelesaiPeriksa", (Timestamp) mapCriteria.get("tanggal_selesai_periksa")));
            }
            if (mapCriteria.get("id_dokter_pengirim")!=null) {
                criteria.add(Restrictions.eq("idDokterPengirim", (String) mapCriteria.get("id_dokter_pengirim")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_pemeriksa")!=null) {
                criteria.add(Restrictions.eq("idPemeriksa", (String) mapCriteria.get("id_pemeriksa")));
            }
            if (mapCriteria.get("id_lab")!=null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("status")!=null) {
                criteria.add(Restrictions.eq("statusPeriksa", (String) mapCriteria.get("status")));
            }
            if (mapCriteria.get("approve_flag")!=null) {
                criteria.add(Restrictions.eq("approveFlag", (String) mapCriteria.get("approve_flag")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idPeriksaLab"));

        List<ItSimrsPeriksaLabEntity> results = criteria.list();
        return results;
    }

    public List<PeriksaLab> getSearchLab(PeriksaLab bean){
        List<PeriksaLab> checkupList = new ArrayList<>();
        if (bean != null){

            String idPasien = "%";
            String nama = "%";
            String statusPeriksa = "%";
            String idDetailCheckup = "%";
            String idLab = "%";

            String dateFrom = "";
            String dateTo = "";

            String kategori = "%";
            String branchId = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
                nama = bean.getNamaPasien();
            }

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                idDetailCheckup = bean.getIdPelayanan();
            }

            if (bean.getIdLab() != null && !"".equalsIgnoreCase(bean.getIdLab())){
                idLab = bean.getIdLab();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())){
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getIdKategoriLab() != null && !"".equalsIgnoreCase(bean.getIdKategoriLab())){
                kategori = bean.getIdKategoriLab();
            }

            if (bean.getStDateFrom() != null && !"".equalsIgnoreCase(bean.getStDateFrom())){
                dateFrom = bean.getStDateFrom();
            }

            if (bean.getStDateTo() != null && !"".equalsIgnoreCase(bean.getStDateTo())){
                dateTo = bean.getStDateTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                branchId = bean.getBranchId();
            }

            String SQL = "SELECT\n" +
                    "pl.id_periksa_lab,\n" +
                    "pl.id_detail_checkup,\n" +
                    "pl.id_lab,\n" +
                    "c.id_pasien,\n" +
                    "c.nama,\n" +
                    "lab.nama_lab,\n" +
                    "pl.created_date,\n" +
                    "pl.approve_flag,\n" +
                    "pl.keterangan\n" +
                    "FROM it_simrs_periksa_lab pl\n" +
                    "INNER JOIN it_simrs_header_detail_checkup dc ON dc.id_detail_checkup = pl.id_detail_checkup\n" +
                    "INNER JOIN it_simrs_header_checkup c ON c.no_checkup = dc.no_checkup\n" +
                    "INNER JOIN im_simrs_lab lab ON lab.id_lab = pl.id_lab\n" +
                    "WHERE lab.id_kategori_lab LIKE :kategori\n" +
                    "AND c.id_pasien LIKE :idPasien \n" +
                    "AND c.nama LIKE :nama \n" +
                    "AND lab.id_lab LIKE :idLab \n" +
                    "AND pl.status_periksa LIKE :status \n" +
                    "AND c.branch_id LIKE :branchId \n" +
                    "AND dc.id_detail_checkup LIKE :idDetailCheckup";

            List<Object[]> results = new ArrayList<>();
            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)){

                SQL = SQL + "\n AND CAST(pl.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(pl.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy') " +
                        "\n ORDER BY pl.created_date ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idDetailCheckup", idDetailCheckup)
                        .setParameter("idLab", idLab)
                        .setParameter("status", statusPeriksa)
                        .setParameter("kategori", kategori)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .list();

            } else {

                SQL = SQL + "\n ORDER BY pl.created_date ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idDetailCheckup", idDetailCheckup)
                        .setParameter("idLab", idLab)
                        .setParameter("status", statusPeriksa)
                        .setParameter("kategori", kategori)
                        .setParameter("branchId", branchId)
                        .list();
            }

            if (!results.isEmpty()){
                PeriksaLab dataLab;
                for (Object[] obj : results){
                    dataLab = new PeriksaLab();
                    dataLab.setIdPeriksaLab(obj[0].toString());
                    dataLab.setIdDetailCheckup(obj[1].toString());
                    dataLab.setIdLab(obj[2].toString());
                    dataLab.setIdPasien(obj[3].toString());
                    dataLab.setNamaPasien(obj[4].toString());
                    dataLab.setLabName(obj[5].toString());
                    dataLab.setCreatedDate((Timestamp) obj[6]);
                    String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(dataLab.getCreatedDate());
                    dataLab.setStCreatedDate(formatDate);
                    dataLab.setApproveFlag(obj[7] == null ? "" : obj[7].toString());
                    dataLab.setKeterangan(obj[8] == null ? "" : obj[8].toString());
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

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_periksa_lab')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public PeriksaLab getTotalTarif(String idLab, String idPeriksa){
        PeriksaLab periksaLab = new PeriksaLab();

        if(!"".equalsIgnoreCase(idLab) && idLab != null && !"".equalsIgnoreCase(idPeriksa) && idPeriksa != null){
            String SQLCEK = "SELECT \n" +
                    "b.id_kategori_lab, \n" +
                    "b.nama_kategori, \n" +
                    "a.id_lab \n" +
                    "FROM im_simrs_lab a\n" +
                    "INNER JOIN im_simrs_kategori_lab b ON a.id_kategori_lab = b.id_kategori_lab\n" +
                    "WHERE a.id_lab = :id";
            List<Object[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQLCEK)
                    .setParameter("id", idLab)
                    .list();

            if(result.size() > 0){
                Object[] objLab = result.get(0);
                String namaKategori = objLab[1].toString();
                if(namaKategori != null && !"".equalsIgnoreCase(namaKategori)){
                    periksaLab.setKategoriLabName(namaKategori.toLowerCase());

                    if("Radiologi".equalsIgnoreCase(namaKategori)){
                        String SQL = "SELECT \n" +
                                "a.id_periksa_lab,  \n" +
                                "SUM(c.tarif) as total,\n" +
                                "d.nama_lab \n" +
                                "FROM it_simrs_periksa_lab a\n" +
                                "INNER JOIN it_simrs_periksa_radiologi b ON a.id_periksa_lab = b.id_periksa_lab\n" +
                                "INNER JOIN im_simrs_lab_detail c ON b.id_lab_detail = c.id_lab_detail\n" +
                                "INNER JOIN im_simrs_lab d ON a.id_lab = d.id_lab\n" +
                                "WHERE a.id_periksa_lab = :idPeriksa AND b.flag = 'Y'\n" +
                                "GROUP BY a.id_periksa_lab, d.nama_lab";

                        List<Object[]> results = new ArrayList<>();
                        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                                .setParameter("idPeriksa", idPeriksa)
                                .list();

                        if(results.size() > 0){
                            Object[] objects = results.get(0);
                            periksaLab.setIdPeriksaLab(objects[0] == null ? "" : objects[0].toString());
                            periksaLab.setTarif(objects[1] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(objects[1].toString()));
                            periksaLab.setLabName(objects[2] == null ? "" : objects[2].toString());
                        }

                    }else{
                        String SQL = "SELECT \n" +
                                "a.id_periksa_lab,  \n" +
                                "SUM(c.tarif) as total, \n" +
                                "d.nama_lab \n" +
                                "FROM it_simrs_periksa_lab a\n" +
                                "INNER JOIN it_simrs_periksa_lab_detail b ON a.id_periksa_lab = b.id_periksa_lab\n" +
                                "INNER JOIN im_simrs_lab_detail c ON b.id_lab_detail = c.id_lab_detail\n" +
                                "INNER JOIN im_simrs_lab d ON a.id_lab = d.id_lab\n" +
                                "WHERE a.id_periksa_lab = :idPeriksa AND b.flag = 'Y'\n" +
                                "GROUP BY a.id_periksa_lab, d.nama_lab\n";

                        List<Object[]> results = new ArrayList<>();
                        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                                .setParameter("idPeriksa", idPeriksa)
                                .list();

                        if(results.size() > 0){
                            Object[] objects = results.get(0);
                            periksaLab.setIdPeriksaLab(objects[0] == null ? "" : objects[0].toString());
                            periksaLab.setTarif(objects[1] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(objects[1].toString()));
                            periksaLab.setLabName(objects[2] == null ? "" : objects[2].toString());
                        }
                    }
                }
            }
        }

        return periksaLab;
    }

    public String getDivisiIdLabTransaction(String idDetailCheckup, String tipe){

        String SQL = "SELECT c.id_kategori_lab, a.id_detail_checkup, d.kodering\n" +
                "FROM it_simrs_periksa_lab a \n" +
                "INNER JOIN im_simrs_lab b ON b.id_lab = a.id_lab\n" +
                "INNER JOIN im_simrs_kategori_lab c ON c.id_kategori_lab = b.id_kategori_lab\n" +
                "INNER JOIN im_position d ON d.position_id = c.divisi_id\n" +
                "WHERE c.nama_kategori ILIKE :tipe\n" +
                "AND a.id_detail_checkup ILIKE :idDetail\n" +
                "ORDER BY a.last_update DESC LIMIT 1";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("idDetail", idDetailCheckup)
                .setParameter("tipe", tipe)
                .list();

        String divisId = "";
        if (results.size() > 0){
            for (Object[] obj : results){
                divisId = obj[2] == null ? "" : obj[2].toString();
            }
        }
        return divisId;
    }

    public List<Dokter> getListDokterLabRadiologi(String tipe){
        List<Dokter> dokterList = new ArrayList<>();
        if(tipe != null && !"".equalsIgnoreCase(tipe)){
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

            if(result.size() > 0){
                for (Object[] obj: result){
                    Dokter dokter = new Dokter();
                    dokter.setIdDokter(obj[0] == null ? "" : obj[0].toString());
                    dokter.setNamaDokter(obj[1] == null ? "" : obj[1].toString());
                    dokterList.add(dokter);
                }
            }
        }
        return dokterList;
    }

    public PeriksaLab getNamaLab(String idPeriksa){
        PeriksaLab lab = new PeriksaLab();
        if(idPeriksa != null && !"".equalsIgnoreCase(idPeriksa)){
            String SQL = "SELECT\n" +
                    "a.id_periksa_lab, \n" +
                    "b.nama_lab,\n" +
                    "a.id_dokter,\n" +
                    "c.nama_dokter,\n" +
                    "a.ttd_dokter,\n" +
                    "a.ttd_petugas,\n" +
                    "a.id_pemeriksa,\n" +
                    "d.user_name,\n" +
                    "a.id_lab \n"+
                    "FROM it_simrs_periksa_lab a\n" +
                    "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                    "LEFT JOIN im_simrs_dokter c ON a.id_dokter = c.id_dokter\n" +
                    "LEFT JOIN im_users d ON a.id_pemeriksa = d.user_id\n" +
                    "WHERE id_periksa_lab = :idPeriksaLab";
            List<Objects[]> result = new ArrayList<>();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPeriksaLab", idPeriksa)
                    .list();
            if(result.size() > 0){
                Object[] objects = result.get(0);
                lab.setIdPeriksaLab(objects[0] == null ? "" : objects[0].toString());
                lab.setKategoriLabName(objects[1] == null ? "" : objects[1].toString());
                lab.setIdDokter(objects[2] == null ? "" : objects[2].toString());
                lab.setNamaDokter(objects[3] == null ? "" : objects[3].toString());
                lab.setTtdDokter(objects[4] == null ? "" : CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_DOKTER+objects[4].toString());
                lab.setTtdPetugas(objects[5] == null ? "" : CommonConstant.RESOURCE_PATH_SAVED_UPLOAD_EXTRERNAL_DIRECTORY+CommonConstant.RESOURCE_PATH_TTD_PETUGAS+objects[5].toString());
                lab.setIdPemeriksa(objects[6] == null ? "" : objects[6].toString());
                lab.setNamaPetugas(objects[7] == null ? "" : objects[7].toString());
                lab.setIdLab(objects[8] == null ? "" : objects[8].toString());
            }
        }
        return lab;
    }

    public List<PeriksaLab> getListLab(String noCheckup){
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
                "f.nama_pelayanan\n" +
                "FROM it_simrs_periksa_lab a\n" +
                "INNER JOIN it_simrs_header_detail_checkup b ON a.id_detail_checkup = b.id_detail_checkup\n" +
                "INNER JOIN it_simrs_header_checkup c ON b.no_checkup = c.no_checkup\n" +
                "INNER JOIN im_simrs_lab d ON a.id_lab = d.id_lab\n" +
                "INNER JOIN im_simrs_kategori_lab e ON d.id_kategori_lab = e.id_kategori_lab\n" +
                "LEFT JOIN im_simrs_pelayanan f ON b.id_pelayanan = f.id_pelayanan\n" +
                "WHERE c.no_checkup = :id \n" +
                "ORDER BY a.id_detail_checkup ASC";

        List<Objects[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", noCheckup)
                .list();
        if(result.size() > 0){
            for (Object[] obj: result){
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
                labList.add(lab);
            }
        }
        return labList;
    }
}