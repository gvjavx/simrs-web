package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.ordergizi.model.OrderGizi;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class OrderGiziDao extends GenericDao<ItSimrsOrderGiziEntity, String> {
    @Override
    protected Class<ItSimrsOrderGiziEntity> getEntityClass() {
        return ItSimrsOrderGiziEntity.class;
    }

    @Override
    public List<ItSimrsOrderGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsOrderGiziEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_order_gizi") != null) {
                criteria.add(Restrictions.eq("idOrderGizi", mapCriteria.get("id_order_gizi").toString()));
            }
            if (mapCriteria.get("id_rawat_inap") != null) {
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("diterima_flag") != null) {
                criteria.add(Restrictions.eq("diterimaFlag", mapCriteria.get("diterima_flag").toString()));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }
        }
        criteria.addOrder(Order.asc("idOrderGizi"));
        List<ItSimrsOrderGiziEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_order_gizi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<RawatInap> getSearchOrderGizi(RawatInap bean) {
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (bean != null) {

            String idPasien = "%";
            String nama = "%";
            String idPelayanan = "%";
            String statusPeriksa = "%";
            String jenisKelamin = "%";
            String idKelas = "%";
            String idRuang = "%";
            String idDetailCheckup = "%";
            String dateFrom = "";
            String dateTo = "";
            String branchId = "%";
            String jenisPeriksa = "%";
            String keterangan = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                nama = "%" + bean.getNamaPasien() + "%";
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())) {
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())) {
                jenisKelamin = bean.getJenisKelamin();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())) {
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())) {
                idRuang = bean.getIdRuang();
            }

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())) {
                idDetailCheckup = bean.getIdDetailCheckup();
            }

            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())) {
                dateFrom = bean.getStTglFrom();
            }

            if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())) {
                dateTo = bean.getStTglTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksa() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksa())) {
                jenisPeriksa = bean.getIdJenisPeriksa();
            }

            if (bean.getKeterangan() != null && !"".equalsIgnoreCase(bean.getKeterangan())) {
                keterangan = bean.getKeterangan();
            }


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
                    "b.no_sep,\n" +
                    "d.id_rawat_inap,\n" +
                    "g.id_order_gizi,\n" +
                    "g.keterangan,\n" +
                    "g.bentuk_diet,\n" +
                    "g.approve_flag,\n" +
                    "g.diterima_flag\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                    "INNER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "INNER JOIN mt_simrs_ruangan_tempat_tidur tt ON d.id_ruangan = tt.id_tempat_tidur \n" +
                    "INNER JOIN mt_simrs_ruangan e ON tt.id_ruangan = e.id_ruangan\n" +
                    "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                    "INNER JOIN it_simrs_order_gizi g ON d.id_rawat_inap = g.id_rawat_inap\n" +
                    "WHERE a.id_pasien LIKE :idPasien\n" +
                    "AND a.nama LIKE :nama\n" +
                    "AND b.id_pelayanan LIKE :idPelayanan\n" +
                    "AND b.status_periksa LIKE :status\n" +
                    "AND a.jenis_kelamin LIKE :jenisKelamin\n" +
                    "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND e.id_ruangan LIKE :idRuang\n" +
                    "AND b.id_detail_checkup LIKE :idDetailCheckup\n" +
                    "AND a.branch_id LIKE :branchId\n" +
                    "AND b.id_jenis_periksa_pasien LIKE :jenisPeriksa \n" +
                    "AND g.flag = 'Y' \n" +
                    "AND g.waktu LIKE :ket \n";

            List<Object[]> results = new ArrayList<>();

            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)) {

                SQL = SQL + "AND CAST(g.tgl_order AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(g.tgl_order AS date) <= to_date(:dateTo, 'dd-MM-yyyy') \n";

                results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                        .setParameter("idPasien", idPasien)
                        .setParameter("nama", nama)
                        .setParameter("idPelayanan", idPelayanan)
                        .setParameter("status", statusPeriksa)
                        .setParameter("jenisKelamin", jenisKelamin)
                        .setParameter("idKelas", idKelas)
                        .setParameter("idRuang", idRuang)
                        .setParameter("idDetailCheckup", idDetailCheckup)
                        .setParameter("dateFrom", dateFrom)
                        .setParameter("dateTo", dateTo)
                        .setParameter("branchId", branchId)
                        .setParameter("jenisPeriksa", jenisPeriksa)
                        .setParameter("ket", keterangan)
                        .list();

            } else {

                if (!"".equalsIgnoreCase(bean.getStTglFrom())) {

                    SQL = SQL + "AND CAST(g.tgl_order AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') \n";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateFrom", dateFrom)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("ket", keterangan)
                            .list();
                } else if (!"".equalsIgnoreCase(bean.getStTglTo())) {

                    SQL = SQL + "AND CAST(g.tgl_order AS date) <= to_date(:dateTo, 'dd-MM-yyyy') \n";

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("dateTo", dateTo)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("ket", keterangan)
                            .list();
                } else {

                    SQL = SQL;

                    results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                            .setParameter("idPasien", idPasien)
                            .setParameter("nama", nama)
                            .setParameter("idPelayanan", idPelayanan)
                            .setParameter("status", statusPeriksa)
                            .setParameter("jenisKelamin", jenisKelamin)
                            .setParameter("idKelas", idKelas)
                            .setParameter("idRuang", idRuang)
                            .setParameter("idDetailCheckup", idDetailCheckup)
                            .setParameter("branchId", branchId)
                            .setParameter("jenisPeriksa", jenisPeriksa)
                            .setParameter("ket", keterangan)
                            .list();
                }
            }

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setIdDetailCheckup(obj[0] == null ? "" : obj[0].toString());
                    rawatInap.setNoCheckup(obj[1] == null ? "" : obj[1].toString());
                    rawatInap.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    rawatInap.setNamaPasien(obj[3] == null ? "" : obj[3].toString());
                    String jalan = obj[4] == null ? "" : obj[4].toString();
                    rawatInap.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                    rawatInap.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    rawatInap.setStatusPeriksa(obj[7] == null ? "" : obj[7].toString());
                    rawatInap.setStatusPeriksaName(obj[8] == null ? "" : obj[8].toString());
                    rawatInap.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    rawatInap.setIdRawatInap(obj[10] == null ? "" : obj[10].toString());
                    rawatInap.setIdRuangan(obj[11] == null ? "" : obj[11].toString());
                    rawatInap.setNoRuangan(obj[12] == null ? "" : obj[12].toString());
                    rawatInap.setNamaRangan(obj[13] == null ? "" : obj[13].toString());
                    rawatInap.setKelasRuanganName(obj[14] == null ? "" : obj[14].toString());
                    rawatInap.setIdKelas(obj[15] == null ? "" : obj[15].toString());
                    rawatInap.setNoSep(obj[16] == null ? "" : obj[16].toString());
                    rawatInap.setIdRawatInap(obj[17] == null ? "" : obj[17].toString());
                    rawatInap.setIdOrderGizi(obj[18] == null ? "" : obj[18].toString());
                    rawatInap.setKeterangan(obj[19] == null ? "" : obj[19].toString());
                    rawatInap.setBentukGizi(obj[20] == null ? "" : obj[20].toString());
                    rawatInap.setApproveFlag(obj[21] == null ? "" : obj[21].toString());
                    rawatInap.setDiterimaFlag(obj[22] == null ? "" : obj[22].toString());
                    if(obj[18] != null){
                        rawatInap.setJenisDiet(getJenisDiet(obj[18].toString()));
                    }
                    if(obj[2] != null){
                        rawatInap.setAlergi(getAlergi(obj[2].toString()));
                    }

                    if (!"".equalsIgnoreCase(rawatInap.getDesaId())) {
                        List<Object[]> objDesaList = getListAlamatByDesaId(rawatInap.getDesaId());
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
                            }
                        }
                    }
                    rawatInap.setAlamat(jalan);
                    rawatInapList.add(rawatInap);
                }
            }
        }
        return rawatInapList;
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

    public String getJenisDiet(String id) {
        String res = "";
        String SQL = "SELECT\n" +
                "id_detail_jenis_diet, \n"+
                "nama_jenis_diet\n" +
                "FROM it_simrs_detail_jenis_diet\n" +
                "WHERE id_order_gizi = :id\n" +
                "AND flag = 'Y'";

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();

        if (results.size() > 0) {
            for (Object[] obj : results) {
                if(!"".equalsIgnoreCase(res)){
                    if(obj[1] != null){
                        res = res +", "+obj[1].toString();
                    }
                }else{
                    if(obj[1] != null){
                        res = obj[1].toString();
                    }
                }
            }
        }
        return res;
    }

    public String getAlergi(String id) {
        String res = "";
        String SQL = "SELECT \n" +
                "id_alergi, \n"+
                "alergi\n" +
                "FROM it_simrs_checkup_alergi\n" +
                "WHERE id_pasien = :id \n" +
                "AND flag = 'Y' \n";
        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();
        if (results.size() > 0) {
            for (Object[] obj : results) {
                if(!"".equalsIgnoreCase(res)){
                    if(obj[1] != null){
                        res = res +", "+obj[1].toString();
                    }
                }else{
                    if(obj[1] != null){
                        res = obj[1].toString();
                    }
                }
            }
        }
        return res;
    }

    public boolean cekStatusOrderGizi(String idRawatInap) {

        Boolean cek = false;

        String SQL = "SELECT approve_flag, id_order_gizi FROM it_simrs_order_gizi WHERE id_rawat_inap = :id";

        List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idRawatInap)
                .list();

        if (results != null) {
            for (Object[] obj : results) {
                if (obj[0] == null || "".equalsIgnoreCase(obj[0].toString())) {
                    cek = true;
                }
            }
        } else {
            cek = null;
        }

        return cek;
    }

    public List<OrderGizi> cekOrderGiziToday(String id, String waktu, String type, String when) {
        List<OrderGizi> orderGizis = new ArrayList<>();
        String condi = "";
        if("RJ".equalsIgnoreCase(type)){
            condi = "AND id_detail_checkup = '"+id+"' \n";
        }else{
            condi = "AND id_rawat_inap = '"+id+"' \n";
        }

        if("1".equalsIgnoreCase(when)){
            condi += "AND CAST(tgl_order AS DATE) = CURRENT_DATE";
        }
        if("2".equalsIgnoreCase(when)){
            condi += "AND CAST(tgl_order AS DATE) = (SELECT CURRENT_DATE + 1 AS DATE)";
        }
        if("12".equalsIgnoreCase(when)){
            condi += "AND CAST(tgl_order AS DATE) = CURRENT_DATE OR CAST(tgl_order AS DATE) = CURRENT_DATE + 1";
        }
        String SQL = "SELECT \n" +
                "id_order_gizi,\n" +
                "id_rawat_inap,\n" +
                "waktu,\n" +
                "created_date\n" +
                "FROM it_simrs_order_gizi\n" +
                "WHERE waktu LIKE :ket \n" + condi;

        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("ket", waktu)
                .list();

        if (results.size() > 0) {
            for (Object[] obj : results) {
                OrderGizi orderGizi = new OrderGizi();
                orderGizi.setIdOrderGizi(obj[0] == null ? "" : obj[0].toString());
                orderGizi.setIdRawatInap(obj[1] == null ? "" : obj[1].toString());
                orderGizi.setWaktu(obj[2] == null ? "" : obj[2].toString());
                orderGizi.setCreatedDate(obj[3] == null ? null : (Timestamp) obj[3]);
                orderGizis.add(orderGizi);
            }
        }

        return orderGizis;
    }
}
