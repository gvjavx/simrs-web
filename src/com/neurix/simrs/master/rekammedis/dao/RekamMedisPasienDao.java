package com.neurix.simrs.master.rekammedis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekammedis.model.ImSimrsRekamMedisPasienEntity;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RekamMedisPasienDao extends GenericDao<ImSimrsRekamMedisPasienEntity, String> {

    @Override
    protected Class<ImSimrsRekamMedisPasienEntity> getEntityClass() {
        return ImSimrsRekamMedisPasienEntity.class;
    }

    @Override
    public List<ImSimrsRekamMedisPasienEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekamMedisPasienEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_rekam_medis_pasien") != null) {
                criteria.add(Restrictions.eq("idRekamMedisPasien", (String) mapCriteria.get("id_rekam_medis_pasien")));
            }
            if (mapCriteria.get("kode_rm") != null) {
                criteria.add(Restrictions.eq("kodeRm", (String) mapCriteria.get("kode_rm")));
            }
            if (mapCriteria.get("keterangan") != null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis") != null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }

        // Order by
        criteria.addOrder(Order.asc("idRekamMedisPasien"));

        List<ImSimrsRekamMedisPasienEntity> results = criteria.list();
        return results;
    }

    public List<RekamMedisPasien> getListRekamMedisByPelayanan(String idPelayanan) {
        List<RekamMedisPasien> res = new ArrayList<>();
        if (idPelayanan != null) {
            String SQL = "";

            if ("rawat_jalan" == idPelayanan) {
                SQL = "SELECT * FROM (\n" +
                        "\tSELECT \n" +
                        "\ta.id_rekam_medis_pasien,\n" +
                        "\ta.kode_rm,\n" +
                        "\ta.jenis,\n" +
                        "\ta.nama_rm,\n" +
                        "\tb.urutan,\n" +
                        "\tb.tipe_pelayanan\n" +
                        "\tFROM im_simrs_rekam_medis_pasien a\n" +
                        "\tINNER JOIN im_simrs_rekam_medis_pelayanan b\n" +
                        "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                        "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                        "\tAND a.jenis = 'hemodialisa'\n" +
                        "\tUNION ALL\n" +
                        "\tSELECT \n" +
                        "\ta.id_rekam_medis_pasien,\n" +
                        "\ta.kode_rm,\n" +
                        "\ta.jenis,\n" +
                        "\ta.nama_rm,\n" +
                        "\tb.urutan,\n" +
                        "\tb.tipe_pelayanan\n" +
                        "\tFROM im_simrs_rekam_medis_pasien a\n" +
                        "\tINNER JOIN im_simrs_rekam_medis_pelayanan b\n" +
                        "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                        "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                        "\tAND a.jenis = 'profil'\n" +
                        "\tUNION ALL\n" +
                        "\tSELECT \n" +
                        "\ta.id_rekam_medis_pasien,\n" +
                        "\ta.kode_rm,\n" +
                        "\ta.jenis,\n" +
                        "\ta.nama_rm,\n" +
                        "\tb.urutan,\n" +
                        "\tb.tipe_pelayanan\n" +
                        "\tFROM im_simrs_rekam_medis_pasien a\n" +
                        "\tINNER JOIN im_simrs_rekam_medis_pelayanan b\n" +
                        "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                        "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                        "\tAND a.jenis = 'surat'\n" +
                        ")a ORDER BY a.urutan ASC";
            } else {

            }


            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("idPelayanan", idPelayanan)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    RekamMedisPasien rekamMedisPasien = new RekamMedisPasien();
                    rekamMedisPasien.setIdRekamMedisPasien(obj[0] != null ? obj[0].toString() : "");
                    rekamMedisPasien.setKodeRm(obj[1] != null ? obj[1].toString() : "");
                    rekamMedisPasien.setNamaRm(obj[2] != null ? obj[2].toString() : "");
                    rekamMedisPasien.setKeterangan(obj[3] != null ? obj[3].toString() : "");
                    rekamMedisPasien.setJenis(obj[4] != null ? obj[4].toString() : "");
                    res.add(rekamMedisPasien);
                }
            }
        }
        return res;
    }

    public String getNextIdSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rm_pasien')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}