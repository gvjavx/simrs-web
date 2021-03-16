package com.neurix.simrs.transaksi.ordergizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
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
import java.text.SimpleDateFormat;
import java.util.*;

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

            String flag = "Y";
            String condition = "";
            String khsusus = "";

            if (bean.getBranchId() != null && !"".equalsIgnoreCase(bean.getBranchId())) {
                condition += "AND a.branch_id = '"+bean.getBranchId()+"' \n";
            }
            if (bean.getIdPasien() != null && !"".equalsIgnoreCase(bean.getIdPasien())) {
                condition += "AND a.id_pasien LIKE '%"+bean.getIdPasien()+"%' \n";
            }
            if (bean.getNamaPasien() != null && !"".equalsIgnoreCase(bean.getNamaPasien())) {
                condition += "AND a.nama ILIKE '%"+bean.getNamaPasien()+"%' \n";
            }
            if (bean.getWaktu() != null && !"".equalsIgnoreCase(bean.getWaktu())) {
                condition += "AND d.waktu = '"+bean.getWaktu()+"' \n";
            }
            if (bean.getIdKelasRuangan() != null && !"".equalsIgnoreCase(bean.getIdKelasRuangan())) {
                condition += "AND c.id_kelas_ruangan = '"+bean.getIdKelasRuangan()+"' \n";
            }
            if (bean.getIdRuangan() != null && !"".equalsIgnoreCase(bean.getIdRuangan())) {
                condition += "AND c.id_ruangan = '"+bean.getIdRuangan()+"' \n";
            }
            if(bean.getIdRawatInap() != null && !"".equalsIgnoreCase(bean.getIdRawatInap())){
                khsusus = "WHERE a.id_rawat_inap = '"+bean.getIdRawatInap()+"'\n";
            }

            if (bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom()) &&
                    bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())) {
                condition += "AND CAST(d.tgl_order AS date) >= to_date('"+bean.getStTglFrom()+"', 'dd-MM-yyyy')\n" +
                        "AND CAST(d.tgl_order AS date) <= to_date('"+bean.getStTglTo()+"', 'dd-MM-yyyy')\n";
            }else if(bean.getStTglFrom() != null && !"".equalsIgnoreCase(bean.getStTglFrom())){
                condition += "AND CAST(d.tgl_order AS date) >= to_date('"+bean.getStTglFrom()+"', 'dd-MM-yyyy')\n";
            }else if(bean.getStTglTo() != null && !"".equalsIgnoreCase(bean.getStTglTo())){
                condition += "AND CAST(d.tgl_order AS date) <= to_date('"+bean.getStTglTo()+"', 'dd-MM-yyyy')\n";
            }

            String SQL = "SELECT\n" +
                    "a.no_checkup,\n" +
                    "a.nama,\n" +
                    "a.id_pasien,\n" +
                    "a.jenis_kelamin,\n" +
                    "a.tgl_lahir,\n" +
                    "b.id_detail_checkup,\n" +
                    "c.id_pelayanan,\n" +
                    "c.nama_pelayanan,\n" +
                    "d.bentuk_diet,\n" +
                    "d.waktu,\n" +
                    "d.keterangan,\n" +
                    "d.id_order_gizi,\n" +
                    "d.tgl_order,\n" +
                    "d.approve_flag,\n" +
                    "d.diterima_flag\n" +
                    "FROM it_simrs_header_checkup a\n" +
                    "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                    "INNER JOIN (\n" +
                    "SELECT\n" +
                    "a.id_pelayanan,\n" +
                    "b.nama_pelayanan,\n" +
                    "b.tipe_pelayanan\n"+
                    "FROM im_simrs_pelayanan a\n" +
                    "INNER JOIN im_simrs_header_pelayanan b ON a.id_header_pelayanan = b.id_header_pelayanan\n" +
                    ") c ON b.id_pelayanan = c.id_pelayanan\n" +
                    "INNER JOIN it_simrs_order_gizi d ON b.id_detail_checkup = d.id_detail_checkup\n" +
                    "WHERE d.flag = :flag AND c.tipe_pelayanan IN ('rawat_jalan', 'igd')\n" +condition;

            if("RI".equalsIgnoreCase(bean.getTipePelayanan())){
                SQL = "SELECT\n" +
                        "a.no_checkup,\n" +
                        "a.nama,\n" +
                        "a.id_pasien,\n" +
                        "a.jenis_kelamin,\n" +
                        "a.tgl_lahir,\n" +
                        "b.id_detail_checkup,\n" +
                        "c.no_ruangan,\n" +
                        "c.nama_ruangan,\n" +
                        "d.bentuk_diet,\n" +
                        "d.waktu,\n" +
                        "d.keterangan,\n" +
                        "d.id_order_gizi,\n" +
                        "d.tgl_order,\n" +
                        "d.approve_flag,\n" +
                        "d.diterima_flag,\n" +
                        "c.no_ruangan\n" +
                        "FROM it_simrs_header_checkup a\n" +
                        "INNER JOIN it_simrs_header_detail_checkup b ON a.no_checkup = b.no_checkup\n" +
                        "INNER JOIN (\n" +
                        "SELECT\n" +
                        "a.id_detail_checkup,\n" +
                        "a.id_rawat_inap,\n" +
                        "b.id_tempat_tidur,\n" +
                        "b.id_ruangan,\n" +
                        "b.nama_tempat_tidur,\n" +
                        "c.no_ruangan,\n" +
                        "c.nama_ruangan,\n" +
                        "d.id_kelas_ruangan\n" +
                        "FROM it_simrs_rawat_inap a\n" +
                        "INNER JOIN mt_simrs_ruangan_tempat_tidur b ON a.id_ruangan = b.id_tempat_tidur\n" +
                        "INNER JOIN mt_simrs_ruangan c ON b.id_ruangan = c.id_ruangan\n" +
                        "INNER JOIN im_simrs_kelas_ruangan d ON c.id_kelas_ruangan = d.id_kelas_ruangan \n" + khsusus +
                        ") c ON b.id_detail_checkup = c.id_detail_checkup\n" +
                        "INNER JOIN it_simrs_order_gizi d ON c.id_rawat_inap = d.id_rawat_inap\n" +
                        "WHERE d.flag = :flag \n" +condition;
            }

            List<Object[]> results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                    .setParameter("flag", flag)
                    .list();

            if (results.size() > 0) {
                for (Object[] obj : results) {
                    RawatInap rawatInap = new RawatInap();
                    rawatInap.setNoCheckup(obj[0] == null ? "" : obj[0].toString());
                    rawatInap.setNamaPasien(obj[1] == null ? "" : obj[1].toString());
                    rawatInap.setIdPasien(obj[2] == null ? "" : obj[2].toString());

                    String jk = "";
                    if (obj[3] != null) {
                        if ("P".equalsIgnoreCase(obj[3].toString())) {
                            jk = "Perempuan";
                        } else if ("L".equalsIgnoreCase(obj[3].toString())) {
                            jk = "Laki-Laki";
                        }
                    }
                    rawatInap.setJenisKelamin(jk);

                    if (obj[4] != null && !"".equalsIgnoreCase(obj[4].toString())) {
                        String tglLahir = new SimpleDateFormat("dd-MM-yyyy").format((Date) obj[4]);
                        rawatInap.setTglLahir(tglLahir);
                        rawatInap.setUmur(CommonUtil.calculateAge((java.sql.Date) obj[4], true));
                    }

                    rawatInap.setIdDetailCheckup(obj[5] == null ? "" : obj[5].toString());
                    rawatInap.setIdPelayanan(obj[6] == null ? "" : obj[6].toString());
                    rawatInap.setNamaRangan(obj[7] == null ? "" : obj[7].toString());
                    rawatInap.setBentukGizi(obj[8] == null ? "" : obj[8].toString());
                    rawatInap.setWaktu(obj[9] == null ? "" : obj[9].toString());
                    rawatInap.setKeterangan(obj[10] == null ? "" : obj[10].toString());
                    rawatInap.setIdOrderGizi(obj[11] == null ? "" : obj[11].toString());
                    rawatInap.setTglOrder(obj[12] == null ? null : (Timestamp) obj[12]);
                    rawatInap.setApproveFlag(obj[13] == null ? "" : obj[13].toString());
                    rawatInap.setDiterimaFlag(obj[14] == null ? "" : obj[14].toString());

                    if (obj[11] != null) {
                        rawatInap.setJenisDiet(getJenisDiet(obj[11].toString()));
                    }
                    if (obj[2] != null) {
                        rawatInap.setAlergi(getAlergi(obj[2].toString()));
                    }
                    rawatInap.setNamaDiagnosa(getDiagnosa(obj[5].toString()));
                    if("RI".equalsIgnoreCase(bean.getTipePelayanan())){
                        rawatInap.setNoRuangan(obj[15] == null ? "" : obj[15].toString());
                    }
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

    public String getDiagnosa(String id) {
        String res = "";
        String SQL = "SELECT\n" +
                "id_diagnosa,\n" +
                "keterangan_diagnosa,\n" +
                "jenis_diagnosa\n" +
                "FROM it_simrs_diagnosa_rawat\n" +
                "WHERE flag = 'Y'\n" +
                "AND id_detail_checkup = :id";
        List<Object[]> results = new ArrayList<>();
        results = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", id)
                .list();
        if (results.size() > 0) {
            for (Object[] obj : results) {
                if(!"".equalsIgnoreCase(res)){
                    if(obj[1] != null){
                        res = res +", ["+obj[0].toString()+"]-"+obj[1].toString();
                    }
                }else{
                    if(obj[1] != null){
                        res = "["+obj[0].toString()+"]-"+obj[1].toString();
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
                "WHERE waktu LIKE :ket AND flag = 'Y' \n" +
                "AND diterima_flag NOT LIKE 'R' \n" +
                "AND approve_flag NOT LIKE 'N' \n"+
                condi;

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
