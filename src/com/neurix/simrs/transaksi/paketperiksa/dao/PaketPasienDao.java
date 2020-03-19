package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import com.neurix.simrs.transaksi.paketperiksa.model.PaketPeriksa;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

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
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id_paket", mapCriteria.get("id_paket").toString()));
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
                    "WHERE a.id_pasien = :id AND a.flag = 'Y'";

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
}
