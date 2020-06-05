package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPasienDao extends GenericDao<ItSimrsPaketPasienEntity, String> {
    @Override
    protected Class<ItSimrsPaketPasienEntity> getEntityClass() {
        return ItSimrsPaketPasienEntity.class;
    }

    @Override
    public List<ItSimrsPaketPasienEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPaketPasienEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_paket") != null)
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_paket_pasien')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<PaketPeriksa> getListDaftarPaketPasien(PaketPeriksa bean) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (bean != null) {
            String namaPerusahaan = "%";
            String namaPaket = "%";
            String branch = "";

            if (bean.getNamaPerusahaan() != null && !"".equalsIgnoreCase(bean.getNamaPerusahaan())) {
                namaPerusahaan = "%" + bean.getNamaPerusahaan() + "%";
            }

            if (bean.getNamaPaket() != null && !"".equalsIgnoreCase(bean.getNamaPaket())) {
                namaPaket = "%" + bean.getNamaPaket() + "%";
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branch = bean.getBranchId();
            }

            String SQL = "SELECT \n" +
                    "a.id_paket, \n" +
                    "a.id_perusahaan,\n" +
                    "b.nama_paket,\n" +
                    "c.nama,\n" +
                    "a.jumlah\n" +
                    "FROM (SELECT id_paket, id_perusahaan, COUNT(id_pasien) as jumlah FROM it_simrs_paket_pasien GROUP BY id_paket, id_perusahaan) a\n" +
                    "INNER JOIN mt_simrs_paket b ON a.id_paket = b.id_paket\n" +
                    "INNER JOIN im_akun_master c ON a.id_perusahaan = c.nomor_master\n" +
                    "WHERE b.branch_id = :branch \n" +
                    "AND b.nama_paket ILIKE :namaPak \n" +
                    "AND c.nama ILIKE :namaPes";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("namaPes", namaPerusahaan)
                    .setParameter("namaPak", namaPaket)
                    .setParameter("branch", branch)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPaket(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setIdPerusahaan(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setNamaPaket(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setNamaPerusahaan(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setJumlah(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }


        return paketPeriksaList;
    }

    public List<PaketPeriksa> getDetailDaftarPaketPasien(String idPaket, String idPerusahan, String branchId) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (idPaket != null && !"".equalsIgnoreCase(idPaket)
                && idPerusahan != null && !"".equalsIgnoreCase(idPerusahan)
                && branchId != null && !"".equalsIgnoreCase(branchId)) {

            String SQL = "SELECT \n" +
                    "a.id_pasien, \n" +
                    "b.nama, \n" +
                    "a.flag, \n" +
                    "a.id_paket, \n" +
                    "a.id_perusahaan\n" +
                    "FROM it_simrs_paket_pasien a\n" +
                    "INNER JOIN im_simrs_pasien b ON a.id_pasien = b.id_pasien\n" +
                    "INNER JOIN mt_simrs_paket c ON a.id_paket = c.id_paket\n" +
                    "WHERE a.id_paket = :idPak\n" +
                    "AND a.id_perusahaan = :idPes\n" +
                    "AND c.branch_id = :branch";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPes", idPerusahan)
                    .setParameter("idPak", idPaket)
                    .setParameter("branch", branchId)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPasien(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setNamaPasien(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setFlag(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdPaket(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setIdPerusahaan(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public List<PaketPeriksa> getItemPaket(String idPasien) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (idPasien != null && !"".equalsIgnoreCase(idPasien)) {

            String SQL = "SELECT \n" +
                    "a.id_pasien, \n" +
                    "b.id_paket, \n" +
                    "b.id_kategori_item, \n" +
                    "b.id_item, \n" +
                    "b.jenis_item, \n" +
                    "a.id\n" +
                    "FROM it_simrs_paket_pasien a\n" +
                    "INNER JOIN mt_simrs_item_paket_periksa b ON a.id_paket = b.id_paket\n" +
                    "WHERE a.id_pasien = :id AND a.flag = 'Y' ORDER BY b.id_kategori_item ASC";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPasien)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPasien(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setIdPaket(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setIdKategoriItem(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdItem(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setJenisItem(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksa.setIdPaketPasien(obj[5] == null ? "" : obj[5].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public List<PaketPeriksa> getItemPaketWithIdPaket(String idPaket) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (idPaket != null && !"".equalsIgnoreCase(idPaket)) {

            String SQL = "SELECT \n" +
                    "id_item_paket,\n" +
                    "id_paket,\n" +
                    "id_kategori_item,\n" +
                    "id_item,\n" +
                    "jenis_item\n" +
                    "FROM mt_simrs_item_paket_periksa\n" +
                    "WHERE id_paket = :id ORDER BY id_kategori_item ASC";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPaket)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdItemPaket(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setIdPaket(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setIdKategoriItem(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdItem(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setJenisItem(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public List<PaketPeriksa> getDetailPaket(String idPaket) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (idPaket != null && !"".equalsIgnoreCase(idPaket)) {

            String SQL = "SELECT\n" +
                    "b.id_item_paket,\n" +
                    "b.id_kategori_item,\n" +
                    "b.id_item,\n" +
                    "b.jenis_item,\n" +
                    "CASE\n" +
                    "WHEN b.jenis_item = 'tindakan' THEN c.tindakan\n" +
                    "WHEN b.jenis_item = 'radiologi' THEN d.nama_detail_periksa\n" +
                    "WHEN b.jenis_item = 'laboratorium' THEN d.nama_detail_periksa\n" +
                    "ELSE null\n" +
                    "END as keterangan,\n" +
                    "d.nama_lab,\n" +
                    "d.id_lab, \n" +
                    "a.id_paket\n" +
                    "FROM mt_simrs_paket a\n" +
                    "INNER JOIN mt_simrs_item_paket_periksa b ON a.id_paket = b.id_paket\n" +
                    "LEFT JOIN im_simrs_tindakan c ON b.id_item = c.id_tindakan\n" +
                    "LEFT JOIN (SELECT a.id_lab_detail, a.nama_detail_periksa, b.id_lab, b.nama_lab\n" +
                    "FROM im_simrs_lab_detail a\n" +
                    "INNER JOIN im_simrs_lab b ON a.id_lab = b.id_lab\n" +
                    ") d ON b.id_item = d.id_lab_detail\n" +
                    "WHERE a.id_paket = :id AND b.flag = 'Y' ORDER BY d.id_lab ASC";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPaket)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPaketPasien(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setIdKategoriItem(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setIdItem(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setJenisItem(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setKeterangan(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksa.setNamaLab(obj[5] == null ? "" : obj[5].toString());
                    paketPeriksa.setIdLab(obj[6] == null ? "" : obj[6].toString());
                    paketPeriksa.setIdPaket(obj[7] == null ? "" : obj[7].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public Boolean cekPaketWithIdPasien(String idPasien){
        Boolean response = false;

        if(idPasien != null && !"".equalsIgnoreCase(idPasien)){

            String SQl = "SELECT id, id_pasien FROM it_simrs_paket_pasien\n" +
                         "WHERE id_pasien = :id AND flag = 'Y'";

            List<Object[]> result = new ArrayList();
            result = this.sessionFactory.getCurrentSession().createSQLQuery(SQl)
                    .setParameter("id", idPasien)
                    .list();

            if(result.size() > 0){
                response = true;
            }
        }
        return response;
    }

    public List<PaketPeriksa> getDetailItemPaket(String idLab, String idPaket) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (idPaket != null && !"".equalsIgnoreCase(idPaket) && idLab != null && !"".equalsIgnoreCase(idLab)) {

            String SQL = "SELECT\n" +
                    "b.id_item_paket,\n" +
                    "a.id_paket,\n" +
                    "b.id_kategori_item,\n" +
                    "b.id_item,\n" +
                    "d.nama_detail_periksa\n" +
                    "FROM mt_simrs_paket a\n" +
                    "INNER JOIN mt_simrs_item_paket_periksa b ON a.id_paket = b.id_paket\n" +
                    "INNER JOIN im_simrs_lab_detail d ON b.id_item = d.id_lab_detail\n" +
                    "WHERE a.id_paket = :id \n" +
                    "AND b.id_kategori_item = :idLab \n" +
                    "AND b.flag = 'Y'\n" +
                    "ORDER BY d.id_lab ASC\n";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", idPaket)
                    .setParameter("idLab", idLab)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPaketPasien(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setIdPaket(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setIdKategoriItem(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdItem(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setKeterangan(obj[4] == null ? "" : obj[4].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public List<PaketPeriksa> getPaketPeriksaRawatJalan(String branchId) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {

            String SQL = "SELECT \n" +
                    "a.id_paket,\n" +
                    "a.nama_paket,\n" +
                    "a.flag,\n" +
                    "a.id_pelayanan,\n" +
                    "a.tarif,\n" +
                    "b.nama_pelayanan\n" +
                    "FROM mt_simrs_paket a\n" +
                    "INNER JOIN im_simrs_pelayanan b ON a.id_pelayanan = b.id_pelayanan\n" +
                    "WHERE a.branch_id = :id AND b.tipe_pelayanan = 'rawat_jalan'\n";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", branchId)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPaket(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setNamaPaket(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setFlag(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdPelayanan(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setTarif(obj[4] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[4].toString()));
                    paketPeriksa.setNamaPelayanan(obj[5] == null ? "" : obj[5].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }

    public List<PaketPeriksa> getPaketPeriksaIgd(String branchId) {

        List<PaketPeriksa> paketPeriksaList = new ArrayList<>();

        if (branchId != null && !"".equalsIgnoreCase(branchId)) {

            String SQL = "SELECT \n" +
                    "a.id_paket,\n" +
                    "a.nama_paket,\n" +
                    "a.flag,\n" +
                    "a.id_pelayanan,\n" +
                    "a.tarif,\n" +
                    "b.nama_pelayanan\n" +
                    "FROM mt_simrs_paket a\n" +
                    "INNER JOIN im_simrs_pelayanan b ON a.id_pelayanan = b.id_pelayanan\n" +
                    "WHERE a.branch_id = :id AND b.tipe_pelayanan = 'igd'";

            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", branchId)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    PaketPeriksa paketPeriksa = new PaketPeriksa();
                    paketPeriksa.setIdPaket(obj[0] == null ? "" : obj[0].toString());
                    paketPeriksa.setNamaPaket(obj[1] == null ? "" : obj[1].toString());
                    paketPeriksa.setFlag(obj[2] == null ? "" : obj[2].toString());
                    paketPeriksa.setIdPelayanan(obj[3] == null ? "" : obj[3].toString());
                    paketPeriksa.setTarif(obj[4] == null ? new BigDecimal(String.valueOf("0")) : new BigDecimal(obj[4].toString()));
                    paketPeriksa.setNamaPelayanan(obj[5] == null ? "" : obj[5].toString());
                    paketPeriksaList.add(paketPeriksa);
                }
            }
        }

        return paketPeriksaList;
    }
}
