package com.neurix.simrs.master.rekammedis.dao;

import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekammedis.model.ImSimrsRekamMedisPasienEntity;
import com.neurix.simrs.master.rekammedis.model.RekamMedisPasien;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
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
        criteria.addOrder(Order.asc("idRekamMedisPasien"));
        List<ImSimrsRekamMedisPasienEntity> results = criteria.list();
        return results;
    }

    public List<RekamMedisPasien> getListRekamMedisByPelayanan(RekamMedisPasien bean) {
        List<RekamMedisPasien> res = new ArrayList<>();
        if (bean.getTipePelayanan() != null) {
            String SQL = "";
            String id = "WHERE id_detail_checkup = '" + bean.getIdDetailCheckup() + "'\n";
            if (bean.getNoCheckup() != null && !"".equalsIgnoreCase(bean.getNoCheckup())) {
                id = "WHERE no_checkup = '" + bean.getNoCheckup() + "'\n";
            }
            List<Object[]> results = new ArrayList<>();
            if ("rawat_jalan".equalsIgnoreCase(bean.getTipePelayanan())) {
                if (bean.getJenis() != null) {
                    String jenis = "poli_spesialis";
                    if("spesialis_anak".equalsIgnoreCase(bean.getJenis()) ||
                       "spesialis_mata".equalsIgnoreCase(bean.getJenis()) ||
                       "rehab_medik".equalsIgnoreCase(bean.getJenis()) ||
                       "spesialis_tht".equalsIgnoreCase(bean.getJenis()) ||
                       "spesialis_obstetri".equalsIgnoreCase(bean.getJenis())){
                        jenis = bean.getJenis();
                    }

                    SQL = "SELECT \n" +
                            "a.*,  \n" +
                            "b.jumlah_kategori as terisi,\n" +
                            "b.is_pengisian,\n" +
                            "b.created_date FROM ( \n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan\n" +
                            "\tAND a.jenis = :jenis \n" +
                            "\tUNION ALL \n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan \n" +
                            "\tAND a.jenis = 'ringkasan_rj' \n" +
                            "\tUNION ALL \n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan \n" +
                            "\tAND a.jenis = 'keperawatan_rawat_jalan' \n" +
                            "\tUNION ALL \n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan\n" +
                            "\tAND a.keterangan = 'surat'\n" +
                            ")a \n" +
                            "LEFT JOIN (\n" +
                            "SELECT * FROM (\n" +
                            "SELECT\n" +
                            "id_rekam_medis_pasien,\n" +
                            "is_pengisian, \n" +
                            "created_date,\n" +
                            "jumlah_kategori,\n" +
                            "rank() OVER (PARTITION BY id_rekam_medis_pasien ORDER BY created_date DESC)\n" +
                            "FROM it_simrs_status_pengisian_rekam_medis\n" + id +
                            ") bb WHERE bb.rank = 1\n" +
                            ") b\n" +
                            "ON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                            "WHERE a.flag = 'Y'\n" +
                            "ORDER BY CAST(urutan AS INTEGER) ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("tipePelayanan", bean.getTipePelayanan())
                            .setParameter("jenis", jenis)
                            .list();
                }
            } else if ("igd".equalsIgnoreCase(bean.getTipePelayanan())) {
                if (bean.getJenis() != null) {
                    String notIn = "";
                    if ("ugd_anak".equalsIgnoreCase(bean.getJenis())) {
                        notIn = "AND a.jenis NOT IN ('ugd_dewasa', 'ugd_geriatri') \n";
                    } else if ("ugd_dewasa".equalsIgnoreCase(bean.getJenis())) {
                        notIn = "AND a.jenis NOT IN ('ugd_anak', 'ugd_geriatri') \n";
                    } else if ("ugd_geriatri".equalsIgnoreCase(bean.getJenis())) {
                        notIn = "AND a.jenis NOT IN ('ugd_dewasa', 'ugd_anak') \n";
                    }

                    SQL = "SELECT \n" +
                            "a.*,  \n" +
                            "b.jumlah_kategori as terisi,\n" +
                            "b.is_pengisian,\n" +
                            "b.created_date FROM ( \n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan\n" + notIn +
                            "\tAND a.keterangan = 'form' \n" +
                            "\tUNION ALL\n" +
                            "\tSELECT  \n" +
                            "\ta.id_rekam_medis_pasien, \n" +
                            "\ta.kode_rm, \n" +
                            "\ta.jenis, \n" +
                            "\ta.keterangan,\n" +
                            "\ta.nama_rm, \n" +
                            "\tb.urutan, \n" +
                            "\tb.tipe_pelayanan, \n" +
                            "\ta.function,\n" +
                            "\ta.jumlah_kategori,\n" +
                            "\ta.parameter, \n" +
                            "\tb.flag\n" +
                            "\tFROM im_simrs_rekam_medis_pasien a \n" +
                            "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                            "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                            "\tWHERE b.tipe_pelayanan = :tipePelayanan\n" +
                            "\tAND a.keterangan = 'surat'\n" +
                            ")a \n" +
                            "LEFT JOIN (\n" +
                            "SELECT * FROM (\n" +
                            "SELECT\n" +
                            "id_rekam_medis_pasien,\n" +
                            "is_pengisian, \n" +
                            "created_date,\n" +
                            "jumlah_kategori,\n" +
                            "rank() OVER (PARTITION BY id_rekam_medis_pasien ORDER BY created_date DESC)\n" +
                            "FROM it_simrs_status_pengisian_rekam_medis\n" + id +
                            ") bb WHERE bb.rank = 1\n" +
                            ") b\n" +
                            "ON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                            "\tWHERE a.flag = 'Y'\n" +
                            "ORDER BY CAST(urutan AS INTEGER) ASC";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("tipePelayanan", bean.getTipePelayanan())
                            .list();
                }
            } else {
                SQL = "SELECT \n" +
                        "a.*,  \n" +
                        "b.jumlah_kategori as terisi,\n" +
                        "b.is_pengisian,\n" +
                        "b.created_date FROM ( \n" +
                        "\tSELECT  \n" +
                        "\ta.id_rekam_medis_pasien, \n" +
                        "\ta.kode_rm, \n" +
                        "\ta.jenis, \n" +
                        "\ta.keterangan,\n" +
                        "\ta.nama_rm, \n" +
                        "\tb.urutan, \n" +
                        "\tb.tipe_pelayanan, \n" +
                        "\ta.function,\n" +
                        "\ta.jumlah_kategori,\n" +
                        "\ta.parameter, \n" +
                        "\tb.flag\n" +
                        "\tFROM im_simrs_rekam_medis_pasien a \n" +
                        "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                        "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                        "\tWHERE b.tipe_pelayanan = :tipePelayanan\n" +
                        ")a \n" +
                        "LEFT JOIN (\n" +
                        "SELECT * FROM (\n" +
                        "SELECT\n" +
                        "id_rekam_medis_pasien,\n" +
                        "is_pengisian, \n" +
                        "created_date,\n" +
                        "jumlah_kategori,\n" +
                        "rank() OVER (PARTITION BY id_rekam_medis_pasien ORDER BY created_date DESC)\n" +
                        "FROM it_simrs_status_pengisian_rekam_medis\n" + id +
                        ") bb WHERE bb.rank = 1\n" +
                        ") b\n" +
                        "ON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                        "\tWHERE a.flag = 'Y'\n" +
                        "ORDER BY CAST(urutan AS INTEGER) ASC";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("tipePelayanan", bean.getTipePelayanan())
                        .list();
            }

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    RekamMedisPasien rekamMedisPasien = new RekamMedisPasien();
                    rekamMedisPasien.setIdRekamMedisPasien(obj[0] != null ? obj[0].toString() : "");
                    rekamMedisPasien.setKodeRm(obj[1] != null ? obj[1].toString() : "");
                    rekamMedisPasien.setJenis(obj[2] != null ? obj[2].toString() : "");
                    rekamMedisPasien.setKeterangan(obj[3] != null ? obj[3].toString() : "");
                    rekamMedisPasien.setNamaRm(obj[4] != null ? obj[4].toString() : "");
                    rekamMedisPasien.setFunction(obj[7] != null ? obj[7].toString() : "");
                    rekamMedisPasien.setJumlahKategori(obj[8] != null ? obj[8].toString() : "");
                    rekamMedisPasien.setParameter(obj[9] != null ? obj[9].toString() : "");
                    rekamMedisPasien.setTerisi(obj[11] != null ? obj[11].toString() : "");
                    rekamMedisPasien.setIsPengisian(obj[12] != null ? obj[12].toString() : "");
                    rekamMedisPasien.setCreatedDate(obj[13] != null ? (Timestamp) obj[13] : null);
                    res.add(rekamMedisPasien);
                }
            }
        }
        return res;
    }

    public List<RekamMedisPasien> getRiwayatRekamMedis(String id, String tipePelayanan, String jenis) {
        List<RekamMedisPasien> rekamMedisPasienList = new ArrayList<>();
        String spesialis = "";
        if ("rawat_jalan".equalsIgnoreCase(tipePelayanan)) {
            if (jenis != null && !"".equalsIgnoreCase(jenis) && !"hemodialisa".equalsIgnoreCase(jenis) && !"fisioterapi".equalsIgnoreCase(jenis)) {
                String tempJenis = "poli_spesialis";
                if("spesialis_anak".equalsIgnoreCase(jenis) ||
                        "spesialis_mata".equalsIgnoreCase(jenis) ||
                        "rehab_medik".equalsIgnoreCase(jenis) ||
                        "spesialis_tht".equalsIgnoreCase(jenis) ||
                        "spesialis_obstetri".equalsIgnoreCase(jenis)){
                    tempJenis = jenis;
                }
                spesialis = "('" + tempJenis + "', 'keperawatan_rawat_jalan', 'ringkasan_rj')";
            } else {
                spesialis = "('keperawatan_rawat_jalan', 'ringkasan_rj')";
            }
        } else {
            spesialis = "('keperawatan_rawat_jalan', 'ringkasan_rj')";
        }
        String SQL = "SELECT * FROM (SELECT \n" +
                "a.*,  \n" +
                "b.jumlah_kategori as terisi,\n" +
                "b.is_pengisian,\n" +
                "b.created_date FROM ( \n" +
                "\tSELECT\n" +
                "\t1 as urut,\n" +
                "\tCAST('spesialis' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tAND a.jenis NOT IN ('fisioterapi', 'hemodialisa')\n" +
                "\tAND a.jenis IN " + spesialis + "\n" +
                "\tUNION ALL\n" +
                "\tSELECT \n" +
                "\t2 as urut,\n" +
                "\tCAST('hemodialisa' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tAND a.jenis = 'hemodialisa'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t3 as urut,\n" +
                "\tCAST('fisioterapi' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'rawat_jalan'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tAND a.jenis = 'fisioterapi'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t4 as urut,\n" +
                "\tCAST('igd' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'igd'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tAND a.jenis LIKE '%'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t5 as urut,\n" +
                "\tCAST('tppri' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'tppri'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t6 as urut,\n" +
                "\tCAST('rawat_inap' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'rawat_inap'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t7 as urut,\n" +
                "\tCAST('rawat_intensif' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'rawat_intensif'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t8 as urut,\n" +
                "\tCAST('kamar_operasi' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'kamar_operasi'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                "\tUNION ALL\n" +
                "\tSELECT\n" +
                "\t9 as urut,\n" +
                "\tCAST('ruang_bersalin' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm, \n" +
                "\tb.urutan, \n" +
                "\tb.tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tb.flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a \n" +
                "\tINNER JOIN im_simrs_rekam_medis_pelayanan b \n" +
                "\tON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien \n" +
                "\tWHERE b.tipe_pelayanan = 'ruang_bersalin'\n" +
                "\tAND a.keterangan NOT LIKE 'surat'\n" +
                ")a \n" +
                "LEFT JOIN (\n" +
                "SELECT * FROM (\n" +
                "SELECT\n" +
                "id_rekam_medis_pasien,\n" +
                "is_pengisian, \n" +
                "created_date,\n" +
                "jumlah_kategori,\n" +
                "rank() OVER (PARTITION BY id_rekam_medis_pasien ORDER BY created_date DESC)\n" +
                "FROM it_simrs_status_pengisian_rekam_medis\n" +
                "WHERE id_detail_checkup = :id\n" +
                ") bb WHERE bb.rank = 1\n" +
                ") b\n" +
                "ON a.id_rekam_medis_pasien = b.id_rekam_medis_pasien\n" +
                "WHERE a.flag = 'Y'\n" +
                "ORDER BY a.urut ASC, CAST(a.urutan AS INTEGER) ASC\n" +
                ") a\n" +
                "UNION ALL\n" +
                "SELECT \n" +
                "surata.*,  \n" +
                "suratb.jumlah_kategori as terisi,\n" +
                "suratb.is_pengisian,\n" +
                "suratb.created_date FROM ( \n" +
                "\tSELECT\n" +
                "\t10 as urut,\n" +
                "\tCAST('surat' AS VARCHAR) as tipe,\n" +
                "\ta.id_rekam_medis_pasien, \n" +
                "\ta.kode_rm, \n" +
                "\ta.jenis, \n" +
                "\ta.keterangan,\n" +
                "\ta.nama_rm,\n" +
                "\tCAST(null AS VARCHAR) as urutan, \n" +
                "\tCAST(null AS VARCHAR) as tipe_pelayanan, \n" +
                "\ta.function,\n" +
                "\ta.jumlah_kategori,\n" +
                "\ta.parameter, \n" +
                "\tCAST(null AS VARCHAR) flag\n" +
                "\tFROM im_simrs_rekam_medis_pasien a\n" +
                "\tWHERE a.keterangan = 'surat'\n" +
                ")surata\n" +
                "INNER JOIN (\n" +
                "SELECT * FROM (\n" +
                "SELECT\n" +
                "id_rekam_medis_pasien,\n" +
                "is_pengisian, \n" +
                "created_date,\n" +
                "jumlah_kategori,\n" +
                "rank() OVER (PARTITION BY id_rekam_medis_pasien ORDER BY created_date DESC)\n" +
                "FROM it_simrs_status_pengisian_rekam_medis\n" +
                "WHERE id_detail_checkup = :id\n" +
                ") bb WHERE bb.rank = 1\n" +
                ") suratb\n" +
                "ON surata.id_rekam_medis_pasien = suratb.id_rekam_medis_pasien";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();
        if (results.size() > 0) {
            for (Object[] obj : results) {
                RekamMedisPasien rekamMedisPasien = new RekamMedisPasien();
                rekamMedisPasien.setTipeRM(obj[1] != null ? obj[1].toString() : "");
                rekamMedisPasien.setIdRekamMedisPasien(obj[2] != null ? obj[2].toString() : "");
                rekamMedisPasien.setKodeRm(obj[3] != null ? obj[3].toString() : "");
                rekamMedisPasien.setJenis(obj[4] != null ? obj[4].toString() : "");
                rekamMedisPasien.setKeterangan(obj[5] != null ? obj[5].toString() : "");
                rekamMedisPasien.setNamaRm(obj[6] != null ? obj[6].toString() : "");
                rekamMedisPasien.setFunction(obj[9] != null ? obj[9].toString() : "");
                rekamMedisPasien.setJumlahKategori(obj[10] != null ? obj[10].toString() : "");
                rekamMedisPasien.setParameter(obj[11] != null ? obj[11].toString() : "");
                rekamMedisPasien.setTerisi(obj[13] != null ? obj[13].toString() : "");
                rekamMedisPasien.setIsPengisian(obj[14] != null ? obj[14].toString() : "");
                rekamMedisPasien.setCreatedDate(obj[15] != null ? (Timestamp) obj[15] : null);
                rekamMedisPasienList.add(rekamMedisPasien);
            }
        }

        return rekamMedisPasienList;
    }

    public List<RekamMedisPasien> getRiwayatRekamMedisLama(String id, String branchId) {
        List<RekamMedisPasien> rekamMedisPasienList = new ArrayList<>();
        if (id != null && !"".equalsIgnoreCase(id)) {
            String SQL = "SELECT\n" +
                    "a.id,\n" +
                    "a.id_pasien,\n" +
                    "b.url_img,\n" +
                    "a.branch_id,\n" +
                    "a.no_rm_lama\n" +
                    "FROM im_simrs_rekam_medic_lama a\n" +
                    "INNER JOIN im_simrs_upload_rekam_medic_lama b ON a.id = b.head_id\n" +
                    "WHERE a.id_pasien = :id AND a.branch_id = :branch";
            List<Object[]> results = new ArrayList<>();
            results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("id", id)
                    .setParameter("branch", branchId)
                    .list();
            if (results.size() > 0) {
                for (Object[] obj : results) {
                    RekamMedisPasien rekamMedisPasien = new RekamMedisPasien();
                    rekamMedisPasien.setIdImg(obj[0] != null ? obj[0].toString() : "");
                    rekamMedisPasien.setIdPasien(obj[1] != null ? obj[1].toString() : "");
                    rekamMedisPasien.setBranchId(obj[3] != null ? obj[3].toString() : "");
                    rekamMedisPasien.setNoRmLama(obj[4] != null ? obj[4].toString() : "");
                    if (obj[2] != null) {
                        if (rekamMedisPasien.getNoRmLama() != null && !"".equalsIgnoreCase(rekamMedisPasien.getNoRmLama())) {
                            rekamMedisPasien.setUrlImg(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.URL_RM_LAMA + rekamMedisPasien.getBranchId() + "/" + rekamMedisPasien.getNoRmLama() + "/" + obj[2].toString());
                        } else {
                            rekamMedisPasien.setUrlImg(CommonConstant.EXTERNAL_IMG_URI + CommonConstant.URL_RM_LAMA + rekamMedisPasien.getBranchId() + "/" + rekamMedisPasien.getIdPasien() + "/" + obj[2].toString());
                        }
                    }
                    rekamMedisPasienList.add(rekamMedisPasien);
                }
            }
        }
        return rekamMedisPasienList;
    }

    public String getNextIdSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rm_pasien')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}