package com.neurix.hris.transaksi.pendapatanDokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterEntity;
import com.neurix.hris.transaksi.pendapatanDokter.model.PendapatanDokter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class PendapatanDokterDao extends GenericDao<ItHrisPendapatanDokterEntity, String> {
    @Override
    protected Class<ItHrisPendapatanDokterEntity> getEntityClass() {
        return ItHrisPendapatanDokterEntity.class;
    }

    @Override
    public List<ItHrisPendapatanDokterEntity> getByCriteria(Map mapCriteria) throws HibernateException{
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisPendapatanDokterEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("pendapatan_dokter_id") != null){
                criteria.add(Restrictions.eq("pendapatanDokterId", (String) mapCriteria.get("pendapatan_dokter_id")));
            }
            if (mapCriteria.get("dokter_id") != null){
                criteria.add(Restrictions.eq("dokterId", (String) mapCriteria.get("dokter_id")));
            }
            if (mapCriteria.get("dokter_name") != null){
                criteria.add(Restrictions.ilike("dokterName", (String) mapCriteria.get("dokter_name")));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("bulan") != null){
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }
            if (mapCriteria.get("tahun") != null){
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
//        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("branchId"));
        List<ItHrisPendapatanDokterEntity> results = criteria.list();
        return results;
    }

    public String getNextIdPendapatanDokter() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pendapatan_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PDR"+sId;
    }

    public List<Object[]> getDataPendapatan(String unit, String bulan, String tahun, String dokterId){
        List<Object[]> results = new ArrayList<Object[]>();

        int month = Integer.parseInt(bulan);
        String paramTglStart = tahun+"-"+month+"-01";
        int monthEnd = Integer.parseInt(bulan)+1;
        String paramTglEnd = tahun+"-"+monthEnd+"-01";

        String query = "SELECT\n" +
                "\tdetailActivity.jumlah AS bruto,\n" +
                "\tkso.jenis_kso,\n" +
                "\tkso.master_id,\n" +
                "\tkso.persen_kso,\n" +
                "\tkso.persen_ks,\n" +
                "\tksoTindakan.persen_kso AS persen_kso_tindakan,\n" +
                "\tdokter.nama_dokter\n" +
                "\tFROM\n" +
                "\t\t(SELECT * FROM it_akun_jurnal) jurnal LEFT JOIN\n" +
                "\t\t(SELECT * FROM it_akun_jurnal_detail) jurnalDetail ON jurnalDetail.no_jurnal = jurnal.no_jurnal LEFT JOIN \n" +
                "\t\t(SELECT * FROM it_akun_jurnal_detail_activity) detailActivity ON detailActivity.jurnal_detail_id = jurnalDetail.jurnal_detail_id LEFT JOIN\n" +
                "\t\t(SELECT * FROM im_simrs_dokter_kso) kso ON kso.nip = detailActivity.person_id LEFT JOIN\n" +
                "\t\t(SELECT * FROM im_simrs_dokter_kso_tindakan) ksoTindakan ON ksoTindakan.dokter_kso_id = kso.dokter_kso_id LEFT JOIN\n" +
                "\t\t(SELECT * FROM im_simrs_dokter) dokter ON dokter.id_dokter = detailActivity.person_id \n" +
                "\tWHERE \n" +
                "\t\tjurnal.branch_id = '"+unit+"' AND\n" +
                "\t\tjurnal.tanggal_jurnal >= '"+paramTglStart+"' AND\n" +
                "\t\tjurnal.tanggal_jurnal < '"+paramTglEnd+"' AND\n" +
                "\t\tdetailActivity.person_id = '"+dokterId+"'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        return results;
    }

    public Double getPphKomulatif(String dokterId, String bulan, String tahun){
        List<Object[]> pendapatanDokterEntityList = new ArrayList<>();
        Double pphKomulatif = 0.0;
        int month = Integer.parseInt(bulan) - 1;
        if (month < 10)
            bulan = "0"+month;

        String query = "select \n" +
                "\tsum(dpp_pph_21_komulatif) AS pendapatan_dokter \n" +
                "\tfrom \n" +
                "\t\tit_hris_pendapatan_dokter\n" +
                "\twhere\n" +
                "\t\tbulan='"+bulan+"' and \n" +
                "\t\ttahun='"+tahun+"' and \n" +
                "\t\tdokter_id='"+dokterId+"' ";
        pendapatanDokterEntityList = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        if (pendapatanDokterEntityList != null){
            for (Object[] entity : pendapatanDokterEntityList){
                if (entity != null)
                    pphKomulatif = (Double) entity[0];
                else
                    pphKomulatif = 0.0;

            }
        }
        return pphKomulatif;
    }

    public List<Object[]> getPendapatanDokter(String unit, String bulan, String tahun) throws HibernateException {
        List<ItHrisPendapatanDokterEntity> listOfResults = new ArrayList<ItHrisPendapatanDokterEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        int month = Integer.parseInt(bulan);
        String paramTglStart = tahun+"-"+month+"-01";
        int monthEnd = Integer.parseInt(bulan)+1;
        String paramTglEnd = tahun+"-"+monthEnd+"-01";

//        String query = "SELECT DISTINCT\n" +
//                "\tjurnal.tanggal_jurnal,\n" +
//                "\tjurnalDetail.jurnal_detail_id,\n" +
//                "\tjurnalDetail.divisi_id,\n" +
//                "\tdetailActivity.activity_id,\n" +
//                "\tdetailActivity.person_id,\n" +
//                "\tdetailActivity.jumlah,\n" +
//                "\tdokter.nama_dokter\n" +
//                "FROM\n" +
//                "\t(SELECT * FROM it_akun_jurnal) jurnal LEFT JOIN \n" +
//                "\t(SELECT * FROM it_akun_jurnal_detail) jurnalDetail ON jurnalDetail.no_jurnal = jurnal.no_jurnal LEFT JOIN \n" +
//                "\t(SELECT * FROM it_akun_jurnal_detail_activity) detailActivity ON detailActivity.jurnal_detail_id = jurnalDetail.jurnal_detail_id LEFT JOIN\n" +
//                "\t(SELECT * FROM im_simrs_dokter) dokter ON dokter.id_dokter = detailActivity.person_id\n" +
//                "WHERE\n" +
//                "\tjurnal.branch_id = '"+unit+"' AND\n" +
//                "\tjurnal.tanggal_jurnal >= '"+paramTglStart+"' AND \n" +
//                "\tjurnal.tanggal_jurnal < '"+paramTglEnd+"' AND\n" +
//                "\tdetailActivity.person_id IS NOT NULL";

        String sqlQuery = "SELECT DISTINCT\n" +
                " \n" +
                "                jurnal.tanggal_jurnal, \n" +
                "                jurnalDetail.jurnal_detail_id, \n" +
                "                jurnalDetail.divisi_id, \n" +
                "                detailActivity.activity_id, \n" +
                "                detailActivity.person_id AS dokter_id,\n" +
                "                dokter.nama_dokter, \n" +
                "                detailActivity.no_trans AS no_reg,\n" +
                "                pelayanan.tipe_pelayanan AS jenis_rawat,\n" +
                "                detailActivity.tipe AS kdjnspas,\n" +
                "                checkup.created_date AS tanggal,\n" +
                "                jurnal.keterangan AS keterangan,\n" +
                "                checkup.tarif_bpjs AS tarif_inacbg, \n" +
                "                detailActivity.jumlah AS bruto,\n" +
                "                kso.jenis_kso,\n" +
                "                kso.master_id,\n" +
                "                kso.persen_kso,\n" +
                "                ksoTindakan.persen_kso AS persen_kso_tindakan,\n" +
                "                headerCheckup.id_pasien,\n" +
                "                headerCheckup.nama AS nama_pasien,\n" +
                "                kso.persen_ks,\n" +
                "                pelayanan.id_pelayanan AS id_poli,\n" +
                "                pelayanan.nama_pelayanan AS nama_poli,\n" +
                "                tindakan.nama_tindakan AS activity_name\n" +
                "                FROM \n" +
                "                (SELECT * FROM it_akun_jurnal) jurnal LEFT JOIN  \n" +
                "                (SELECT * FROM it_akun_jurnal_detail) jurnalDetail ON jurnalDetail.no_jurnal = jurnal.no_jurnal LEFT JOIN  \n" +
                "                (SELECT * FROM it_akun_jurnal_detail_activity) detailActivity ON detailActivity.jurnal_detail_id = jurnalDetail.jurnal_detail_id LEFT JOIN \n" +
                "                (SELECT * FROM im_simrs_dokter) dokter ON dokter.id_dokter = detailActivity.person_id LEFT JOIN\n" +
                "                (SELECT * FROM it_simrs_header_detail_checkup) checkup ON checkup.id_detail_checkup = detailActivity.no_trans LEFT JOIN\n" +
                "                (SELECT * FROM im_simrs_pelayanan) pelayanan ON pelayanan.id_pelayanan = checkup.id_pelayanan LEFT JOIN\n" +
                "                (SELECT * FROM im_simrs_dokter_kso) kso ON kso.nip = dokter.id_dokter LEFT JOIN\n" +
                "                (SELECT * FROM im_simrs_dokter_kso_tindakan) ksoTindakan ON ksoTindakan.dokter_kso_id = kso.dokter_kso_id LEFT JOIN\n" +
                "                (SELECT * FROM it_simrs_header_checkup) headerCheckup ON headerCheckup.no_checkup = checkup.no_checkup LEFT JOIN\n" +
                "                (SELECT * FROM it_simrs_riwayat_tindakan) tindakan ON tindakan.id_tindakan = detailActivity.activity_id\n" +
                "                WHERE \n" +
                "                jurnal.branch_id = '"+unit+"' AND \n" +
                "                jurnal.tanggal_jurnal >= '"+paramTglStart+"' AND  \n" +
                "                jurnal.tanggal_jurnal < '"+paramTglEnd+"' AND  \n" +
                "                detailActivity.person_id IS NOT NULL";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(sqlQuery)
                .list();

//        for (Object[] row : results){
//            ItHrisPendapatanDokterEntity itHrisPendapatanDokterEntity = new ItHrisPendapatanDokterEntity();
//            if (row[0] != null)
//                itHrisPendapatanDokterEntity.setTanggalJurnal((Date) row[0]);
//            itHrisPendapatanDokterEntity.setJurnalDetailId((String) row[1]);
//            itHrisPendapatanDokterEntity.setDivisiId((String) row[2]);
//            itHrisPendapatanDokterEntity.setActivityId((String) row[3]);
//            itHrisPendapatanDokterEntity.setPersonId((String) row[4]);
//            itHrisPendapatanDokterEntity.setJumlahBulanIni((Double) row[5]);
//            listOfResults.add(itHrisPendapatanDokterEntity);
//        }

        return results;
    }
}