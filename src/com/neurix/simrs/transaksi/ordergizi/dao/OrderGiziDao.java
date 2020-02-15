package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.ordergizi.model.ItSimrsOrderGiziEntity;
import com.neurix.simrs.transaksi.rawatinap.model.RawatInap;
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
        if (mapCriteria != null)
            if (mapCriteria.get("id_order_gizi") != null){
                criteria.add(Restrictions.eq("idOrderGizi", mapCriteria.get("id_order_gizi").toString()));
            }
            if (mapCriteria.get("id_rawat_inap") != null){
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        criteria.addOrder(Order.asc("idOrderGizi"));
        List<ItSimrsOrderGiziEntity> resilt = criteria.list();
        return resilt;
    }

    public String getNextId(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_order_gizi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<RawatInap> getSearchOrderGizi(RawatInap bean){
        List<RawatInap> rawatInapList = new ArrayList<>();
        if (bean != null){

            String idPasien     = "%";
            String nama         = "%";
            String idPelayanan  = "%";
            String statusPeriksa= "%";
            String jenisKelamin = "%";
            String idKelas      = "%";
            String idRuang      = "%";
            String idDetailCheckup = "%";
            String dateFrom     = "";
            String dateTo       = "";
            String branchId = "%";
            String jenisPeriksa = "%";

            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPasien = bean.getIdPasien();
            }

            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())){
                nama = "%"+bean.getNamaPasien()+"%";
            }

            if (bean.getIdPelayanan() != null && !"".equalsIgnoreCase(bean.getIdPasien())){
                idPelayanan = bean.getIdPelayanan();
            }

            if (bean.getStatusPeriksa() != null && !"".equalsIgnoreCase(bean.getStatusPeriksa())){
                statusPeriksa = bean.getStatusPeriksa();
            }

            if (bean.getJenisKelamin() != null && !"".equalsIgnoreCase(bean.getJenisKelamin())){
                jenisKelamin = bean.getJenisKelamin();
            }

            if (bean.getIdKelas() != null && !"".equalsIgnoreCase(bean.getIdKelas())){
                idKelas = bean.getIdKelas();
            }

            if (bean.getIdRuang() != null && !"".equalsIgnoreCase(bean.getIdRuang())){
                idRuang = bean.getIdRuang();
            }

            if (bean.getIdDetailCheckup() != null && !"".equalsIgnoreCase(bean.getIdDetailCheckup())){
                idDetailCheckup = bean.getIdDetailCheckup();
            }

            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())){
                dateFrom = bean.getStTglFrom();
            }

            if (bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())){
                dateTo = bean.getStTglTo();
            }

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())){
                branchId = bean.getBranchId();
            }

            if (bean.getIdJenisPeriksa() != null && !"".equalsIgnoreCase(bean.getIdJenisPeriksa())){
                jenisPeriksa = bean.getIdJenisPeriksa();
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
                    "a.no_sep, \n" +
                    "d.id_rawat_inap,\n" +
                    "g.id_order_gizi,\n" +
                    "g.approve_flag, g.diterima_flag\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN im_simrs_status_pasien c ON b.status_periksa = c.id_status_pasien\n" +
                    "INNER JOIN (SELECT * FROM it_simrs_rawat_inap WHERE flag = 'Y') d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "INNER JOIN mt_simrs_ruangan e ON d.id_ruangan = e.id_ruangan\n" +
                    "INNER JOIN im_simrs_kelas_ruangan f ON e.id_kelas_ruangan = f.id_kelas_ruangan\n" +
                    "INNER JOIN (SELECT * FROM (\n" +
                    "SELECT *, rank() OVER(PARTITION BY id_rawat_inap ORDER BY created_date DESC) as rank\n" +
                    "FROM it_simrs_order_gizi) a WHERE rank = 1\n" +
                    ") g ON d.id_rawat_inap = g.id_rawat_inap\n" +
                    "WHERE a.id_pasien LIKE :idPasien\n" +
                    "AND a.nama LIKE :nama\n" +
                    "AND b.id_pelayanan LIKE :idPelayanan\n" +
                    "AND b.status_periksa LIKE :status\n" +
                    "AND a.jenis_kelamin LIKE :jenisKelamin\n" +
                    "AND f.id_kelas_ruangan LIKE :idKelas\n" +
                    "AND e.id_ruangan LIKE :idRuang\n" +
                    "AND b.id_detail_checkup LIKE :idDetailCheckup\n" +
                    "AND a.branch_id LIKE :branchId\n" +
                    "AND a.id_jenis_periksa_pasien LIKE :jenisPeriksa\n" +
                    "AND a.flag = 'Y' ORDER BY g.created_date DESC";

            List<Object[]> results = new ArrayList<>();

            if (!"".equalsIgnoreCase(dateFrom) && !"".equalsIgnoreCase(dateTo)){

                SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy') AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                        "\n ORDER BY b.tgl_antrian ASC";

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
                        .list();

            } else {

                if(!"".equalsIgnoreCase(bean.getStTglFrom())){

                    SQL = SQL + "\n AND CAST(a.created_date AS date) >= to_date(:dateFrom, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

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
                            .list();
                }else if (!"".equalsIgnoreCase(bean.getStTglTo())){

                    SQL = SQL + "\n AND CAST(a.created_date AS date) <= to_date(:dateTo, 'dd-MM-yyyy')" +
                            "\n ORDER BY b.tgl_antrian ASC";

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
                            .list();
                }else{

                    SQL = SQL + "\n  ORDER BY b.tgl_antrian ASC";

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
                            .list();
                }
            }

            if (!results.isEmpty()){
                RawatInap rawatInap;
                for (Object[] obj : results){
                    rawatInap = new RawatInap();
                    rawatInap.setIdDetailCheckup(obj[0].toString());
                    rawatInap.setNoCheckup(obj[1].toString());
                    rawatInap.setIdPasien(obj[2] == null ? "" : obj[2].toString());
                    rawatInap.setNamaPasien(obj[3] == null ? "" : obj[3].toString());

                    String jalan = obj[4] == null ? "" : obj[4].toString();

                    rawatInap.setCreatedDate(obj[5] == null ? null : (Timestamp) obj[5]);
                    rawatInap.setDesaId(obj[6] == null ? "" : obj[6].toString());
                    rawatInap.setStatusPeriksa(obj[7].toString());
                    rawatInap.setStatusPeriksaName(obj[8].toString());
                    rawatInap.setKeteranganSelesai(obj[9] == null ? "" : obj[9].toString());
                    rawatInap.setIdRawatInap(obj[10].toString());
                    rawatInap.setIdRuangan(obj[11].toString());
                    rawatInap.setNoRuangan(obj[12].toString());
                    rawatInap.setNamaRangan(obj[13].toString());
                    rawatInap.setKelasRuanganName(obj[14].toString());
                    rawatInap.setIdKelas(obj[15].toString());
                    rawatInap.setNoSep(obj[16].toString());
                    rawatInap.setIdRawatInap(obj[17].toString());
                    rawatInap.setIdOrderGizi(obj[18].toString());
                    rawatInap.setApproveFlag(obj[19] == null ? "" : obj[19].toString());
                    rawatInap.setDiterimaFlag(obj[20] == null ? "" : obj[20].toString());

                    if (!"".equalsIgnoreCase(rawatInap.getDesaId())){
                        List<Object[]> objDesaList = getListAlamatByDesaId(rawatInap.getDesaId());
                        if (!objDesaList.isEmpty()){
                            for (Object[] objDesa : objDesaList){

                                String alamatLengkap =
                                        "Desa. "+ objDesa[0].toString() +
                                                " Kec. " + objDesa[1].toString() +
                                                " " + objDesa[2].toString() +
                                                " Prov. " + objDesa[3].toString();

                                if (!"".equalsIgnoreCase(jalan)){
                                    jalan = jalan + ", " + alamatLengkap;
                                } else {
                                    jalan = alamatLengkap;
                                }
                            }
                        }
                    }

//                    rawatInap.setCekApprove(cekApproveFlag(obj[0].toString()));
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
}
