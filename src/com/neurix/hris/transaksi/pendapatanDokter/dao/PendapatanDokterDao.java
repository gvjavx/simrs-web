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
            if (mapCriteria.get("no_nota") != null){
                criteria.add(Restrictions.eq("noNota", (String) mapCriteria.get("no_nota")));
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

    public List<Object[]> getDataPendapatanPphLebih(String pendapatanId){
        List<Object[]> result = new ArrayList<Object[]>();

        String query = "SELECT\n" +
                "\ta.dpp_pph_21_komulatif,\n" +
                "\ta.dpp_pph_21,\n" +
                "\ta.tarif,\n" +
                "\ta.pph_dipungut,\n" +
                "\ta.pph_lebih,\n" +
                "\ta.pph_final,\n" +
                "\tb.dpp_pph_50 AS pph21Lebih,\n" +
                "\tb.dpp_pph_21_komulatif AS komulatifLebih,\n" +
                "\tb.tarif AS tarifLebih,\n" +
                "\tb.pph_dipungut AS pphDipungutLebih\n" +
                "\tFROM\n" +
                "\t(SELECT * FROM it_hris_pendapatan_dokter) a LEFT JOIN\n" +
                "\t(SELECT * FROM it_hris_pendapatan_dokter_pph_lebih) b ON b.pendapatan_dokter_id = a.pendapatan_dokter_id\n" +
                "\tWHERE\n" +
                "\ta.pendapatan_dokter_id = '"+pendapatanId+"'";

        result = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        return result;
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
                "\tdokter.nama_dokter,\n" +
                "\tkso.kodering AS kode_jabatan\n" +
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

    // Sigit 2021-03-16, Menhitung Pendapatan dokter kso konect dengan master dan riwayat tindakan
    public List<Object[]> getDataPendapatanKSO(String unit, String bulan, String tahun, String dokterId){
        List<Object[]> results = new ArrayList<Object[]>();

        int month = Integer.parseInt(bulan);
        String paramTglStart = tahun+"-"+month+"-01";
        int monthEnd = Integer.parseInt(bulan)+1;
        String paramTglEnd = tahun+"-"+monthEnd+"-01";

        // Fahmi 2021-07-29, Perubahan Query karena ada beberapa kesalah saat comparing data.
        // Mencari data tindakan
//        String SQLTindakan = "SELECT \n" +
//                "jda.jumlah as bruto,\n" +
//                "dkso.jenis_kso,\n" +
//                "dkso.master_id,\n" +
//                "dkso.persen_kso,\n" +
//                "dkso.persen_ks,\n" +
//                "dksot.persen_kso AS persen_kso_tindakan,\n" +
//                "d.nama_dokter\n" +
//                "FROM it_akun_jurnal_detail_activity jda\n" +
//                "INNER JOIN im_simrs_dokter_kso dkso ON dkso.nip = jda.person_id\n" +
//                "INNER JOIN im_simrs_dokter_kso_tindakan dksot ON dksot.dokter_kso_id = dkso.dokter_kso_id AND dksot.tindakan_id = jda.activity_id\n" +
//                "INNER JOIN it_simrs_riwayat_tindakan rt ON rt.id_tindakan = dksot.tindakan_id AND rt.jenis_pasien = dkso.master_id AND rt.keterangan = dkso.jenis_kso\n" +
//                "INNER JOIN it_akun_jurnal_detail jd ON jd.jurnal_detail_id = jda.jurnal_detail_id\n" +
//                "INNER JOIN it_akun_jurnal j ON j.no_jurnal = jd.no_jurnal\n" +
//                "INNER JOIN it_simrs_tindakan_rawat tr ON tr.id_tindakan = rt.id_tindakan AND tr.id_detail_checkup = rt.id_detail_checkup\n" +
//                "INNER JOIN im_simrs_tindakan t ON t.id_tindakan = tr.id_tindakan\n" +
//                "INNER JOIN im_simrs_dokter d ON d.id_dokter = jda.person_id\n" +
//                "WHERE jda.person_id = '"+dokterId+"'\n" +
//                "AND j.branch_id = '"+unit+"'\n" +
//                "AND j.tanggal_jurnal >= '"+paramTglStart+"'\n" +
//                "AND j.tanggal_jurnal < '"+paramTglEnd+"'\n";

        String SQLTindakan = "SELECT \n" +
                "jda.jumlah as bruto,\n" +
                "dkso.jenis_kso,\n" +
                "dkso.master_id,\n" +
                "dkso.persen_kso,\n" +
                "dkso.persen_ks,\n" +
                "dksot.persen_kso AS persen_kso_tindakan,\n" +
                "d.nama_dokter,\n" +
                "pp.position_id\n"+
                "FROM it_akun_jurnal_detail_activity jda\n" +
                "INNER JOIN im_simrs_dokter_kso dkso ON dkso.nip = jda.person_id AND dkso.flag = 'Y' \n" +
                "INNER JOIN im_simrs_dokter_kso_tindakan dksot ON dksot.dokter_kso_id = dkso.dokter_kso_id AND dksot.flag = 'Y' \n" +
                "INNER JOIN it_simrs_riwayat_tindakan rt ON rt.id_tindakan = jda.activity_id AND rt.jenis_pasien = dkso.master_id AND rt.keterangan = dkso.jenis_kso\n" +
                "INNER JOIN it_akun_jurnal_detail jd ON jd.jurnal_detail_id = jda.jurnal_detail_id\n" +
                "INNER JOIN it_akun_jurnal j ON j.no_jurnal = jd.no_jurnal\n" +
                "INNER JOIN it_simrs_tindakan_rawat tr ON tr.id_tindakan_rawat = rt.id_tindakan AND tr.id_tindakan = dksot.tindakan_id AND tr.id_detail_checkup = rt.id_detail_checkup\n" +
                "INNER JOIN im_simrs_tindakan t ON t.id_tindakan = tr.id_tindakan\n" +
                "INNER JOIN im_simrs_dokter d ON d.id_dokter = jda.person_id\n" +
                "INNER JOIN it_hris_pegawai_position pp ON pp.nip = d.id_dokter\n"+
                "WHERE jda.person_id = '"+dokterId+"'\n" +
                "AND j.branch_id = '"+unit+"'\n" +
                "AND j.tanggal_jurnal >= '"+paramTglStart+"'\n" +
                "AND j.tanggal_jurnal < '"+paramTglEnd+"'\n";

        // End Fahmi


        List<Object[]> listTindakan = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQLTindakan)
                .list();

        results.addAll(listTindakan);
        // END

        // mencari selain tindakan (ruangan, obat)
        String SQLSelainTindakan = "SELECT \n" +
                "jda.jumlah as bruto,\n" +
                "dkso.jenis_kso,\n" +
                "dkso.master_id,\n" +
                "dkso.persen_kso,\n" +
                "dkso.persen_ks,\n" +
                "dkso.persen_kso AS persen_kso_tindakan,\n" +
                "d.nama_dokter\n" +
                "FROM it_akun_jurnal_detail_activity jda\n" +
                "INNER JOIN (SELECT * FROM im_simrs_dokter_kso WHERE jenis_kso != 'tindakan') dkso ON dkso.nip = jda.person_id\n" +
                "INNER JOIN it_simrs_riwayat_tindakan rt ON rt.jenis_pasien = dkso.master_id AND rt.keterangan = dkso.jenis_kso\n" +
                "INNER JOIN it_akun_jurnal_detail jd ON jd.jurnal_detail_id = jda.jurnal_detail_id\n" +
                "INNER JOIN it_akun_jurnal j ON j.no_jurnal = jd.no_jurnal\n" +
                "INNER JOIN im_simrs_dokter d ON d.id_dokter = jda.person_id\n" +
                "WHERE jda.person_id = '"+dokterId+"'\n" +
                "AND j.branch_id = '"+unit+"'\n" +
                "AND j.tanggal_jurnal >= '"+paramTglStart+"'\n" +
                "AND j.tanggal_jurnal < '"+paramTglEnd+"'";


        List<Object[]> listSelainTindakan = this.sessionFactory.getCurrentSession()
                .createSQLQuery(SQLSelainTindakan)
                .list();

        results.addAll(listSelainTindakan);
        // END

        return results;
    }

    public String getLevel(String dokterId, String bulan, String tahun){
        List<Object[]> pendapatanDokterEntityList = new ArrayList<>();
        String level = "";
        int month = Integer.parseInt(bulan) - 1;
        if (month < 10)
            bulan = "0"+month;

        String query = "select  \n" +
                "\t\tbranch_id,\n" +
                "\t\tlevel\n" +
                "                from  \n" +
                "                it_hris_pendapatan_dokter \n" +
                "                where \n" +
                "                bulan='"+bulan+"' and  \n" +
                "                tahun='"+tahun+"' and  \n" +
                "                dokter_id='"+dokterId+"'";
        pendapatanDokterEntityList = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        if (pendapatanDokterEntityList != null){
            for (Object[] entity : pendapatanDokterEntityList){
                if (entity != null){
                    level = String.valueOf(entity[1]);
                }
            }
        }

        return level;
    }

    public Double getPphKomulatif(String dokterId, String bulan, String tahun){
        List<Object[]> pendapatanDokterEntityList = new ArrayList<>();
        Double pphKomulatif = 0.0;
        int month = Integer.parseInt(bulan) - 1;
        if (month < 10)
            bulan = "0"+month;

        String query = "select  \n" +
                "                sum(dpp_pph_21_komulatif) AS pph_komulatif_last,\n" +
                "                branch_id  \n" +
                "                from  \n" +
                "                it_hris_pendapatan_dokter \n" +
                "                where \n" +
                "                bulan='"+bulan+"' and  \n" +
                "                tahun='"+tahun+"' and  \n" +
                "                dokter_id='"+dokterId+"'\n" +
                "                group by branch_id";

        pendapatanDokterEntityList = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();
        if (pendapatanDokterEntityList != null){
            for (Object[] entity : pendapatanDokterEntityList){
                if (entity != null){
                    BigDecimal komulatif = (BigDecimal) entity[0];
                    pphKomulatif = komulatif.doubleValue();
                }
                else {
                    pphKomulatif = 0.0;
                }

            }
        }
        return pphKomulatif;
    }

    public List<Object[]> getPendapatanDokter(String unit, String bulan, String tahun, String dokterId) throws HibernateException {
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

//        String sqlQuery = "SELECT\n" +
//                " \n" +
//                "                jurnal.tanggal_jurnal, \n" +
//                "                jurnalDetail.jurnal_detail_id, \n" +
//                "                jurnalDetail.divisi_id, \n" +
//                "                detailActivity.activity_id, \n" +
//                "                detailActivity.person_id AS dokter_id,\n" +
//                "                dokter.nama_dokter, \n" +
//                "                detailActivity.no_trans AS no_reg,\n" +
//                "                pelayanan.tipe_pelayanan AS jenis_rawat,\n" +
//                "                detailActivity.tipe AS kdjnspas,\n" +
//                "                checkup.created_date AS tanggal,\n" +
//                "                jurnal.keterangan AS keterangan,\n" +
//                "                checkup.tarif_bpjs AS tarif_inacbg, \n" +
//                "                detailActivity.jumlah AS bruto,\n" +
//                "                kso.jenis_kso,\n" +
//                "                kso.master_id,\n" +
//                "                kso.persen_kso,\n" +
//                "                ksoTindakan.persen_kso AS persen_kso_tindakan,\n" +
//                "                headerCheckup.id_pasien,\n" +
//                "                headerCheckup.nama AS nama_pasien,\n" +
//                "                kso.persen_ks,\n" +
//                "                pelayanan.id_pelayanan AS id_poli,\n" +
//                "                pelayanan.nama_pelayanan AS nama_poli,\n" +
//                "                tindakan.nama_tindakan AS activity_name,\n" +
//                "                kso.kodering AS kode_jabatan\n" +
//                "                FROM \n" +
//                "                (SELECT * FROM it_akun_jurnal) jurnal LEFT JOIN  \n" +
//                "                (SELECT * FROM it_akun_jurnal_detail) jurnalDetail ON jurnalDetail.no_jurnal = jurnal.no_jurnal LEFT JOIN  \n" +
//                "                (SELECT * FROM it_akun_jurnal_detail_activity) detailActivity ON detailActivity.jurnal_detail_id = jurnalDetail.jurnal_detail_id LEFT JOIN \n" +
//                "                (SELECT * FROM im_simrs_dokter) dokter ON dokter.id_dokter = detailActivity.person_id LEFT JOIN\n" +
//                "                (SELECT * FROM it_simrs_header_detail_checkup) checkup ON checkup.id_detail_checkup = detailActivity.no_trans LEFT JOIN\n" +
//                "                (SELECT * FROM im_simrs_pelayanan) pelayanan ON pelayanan.id_pelayanan = checkup.id_pelayanan LEFT JOIN\n" +
//                "                (SELECT * FROM im_simrs_dokter_kso) kso ON kso.nip = dokter.id_dokter LEFT JOIN\n" +
//                "                (SELECT * FROM im_simrs_dokter_kso_tindakan) ksoTindakan ON ksoTindakan.dokter_kso_id = kso.dokter_kso_id LEFT JOIN\n" +
//                "                (SELECT * FROM it_simrs_header_checkup) headerCheckup ON headerCheckup.no_checkup = checkup.no_checkup LEFT JOIN\n" +
//                "                (SELECT * FROM it_simrs_riwayat_tindakan) tindakan ON tindakan.id_tindakan = detailActivity.activity_id\n" +
//                "                WHERE \n" +
//                "                jurnal.branch_id = '"+unit+"' AND \n" +
//                "                jurnal.tanggal_jurnal >= '"+paramTglStart+"' AND  \n" +
//                "                jurnal.tanggal_jurnal < '"+paramTglEnd+"' AND  \n" +
//                "                detailActivity.person_id = '"+dokterId+"'";

        // Fahmi 2021-07-29, Update query, karena query yang sebelumnya memunculkan data tidak akurat.
//        String sqlQuery = "SELECT\n" +
//                " \n" +
//                "                  \n" +
//                "                                jurnal.tanggal_jurnal,  \n" +
//                "                                jurnalDetail.jurnal_detail_id,  \n" +
//                "                                jurnalDetail.divisi_id,  \n" +
//                "                                detailActivity.activity_id,  \n" +
//                "                                detailActivity.person_id AS dokter_id, \n" +
//                "                                dokter.nama_dokter,  \n" +
//                "                                detailActivity.no_trans AS no_reg, \n" +
//                "                                pelayanan.tipe_pelayanan AS jenis_rawat, \n" +
//                "                                detailActivity.tipe AS kdjnspas, \n" +
//                "                                checkup.created_date AS tanggal, \n" +
//                "                                jurnal.keterangan AS keterangan, \n" +
//                "                                checkup.tarif_bpjs AS tarif_inacbg,  \n" +
//                "                                detailActivity.jumlah AS bruto, \n" +
//                "                                kso.jenis_kso, \n" +
//                "                                kso.master_id, \n" +
//                "                                kso.persen_kso, \n" +
//                "                                ksoTindakan.persen_kso AS persen_kso_tindakan, \n" +
//                "                                headerCheckup.id_pasien, \n" +
//                "                                headerCheckup.nama AS nama_pasien, \n" +
//                "                                kso.persen_ks, \n" +
//                "                                pelayanan.id_pelayanan AS id_poli, \n" +
//                "                                pelayanan.nama_pelayanan AS nama_poli,\n" +
//                "                                tindakan.nama_tindakan AS activity_name,\n" +
//                "                                kso.kodering AS kode_jabatan \n" +
//                "                                FROM  \n" +
//                "                                (SELECT * FROM it_akun_jurnal) jurnal LEFT JOIN   \n" +
//                "                                (SELECT * FROM it_akun_jurnal_detail) jurnalDetail ON jurnalDetail.no_jurnal = jurnal.no_jurnal LEFT JOIN   \n" +
//                "                                (SELECT * FROM it_akun_jurnal_detail_activity) detailActivity ON detailActivity.jurnal_detail_id = jurnalDetail.jurnal_detail_id LEFT JOIN  \n" +
//                "                                (SELECT * FROM im_simrs_dokter) dokter ON dokter.id_dokter = detailActivity.person_id LEFT JOIN \n" +
//                "                                (SELECT * FROM it_simrs_header_detail_checkup) checkup ON checkup.id_detail_checkup = detailActivity.no_trans LEFT JOIN \n" +
//                "                                (SELECT * FROM im_simrs_pelayanan) pelayanan ON pelayanan.id_pelayanan = checkup.id_pelayanan LEFT JOIN \n" +
//                "                                (SELECT * FROM im_simrs_dokter_kso) kso ON kso.nip = dokter.id_dokter LEFT JOIN \n" +
//                "                                (SELECT * FROM im_simrs_dokter_kso_tindakan) ksoTindakan ON ksoTindakan.dokter_kso_id = kso.dokter_kso_id LEFT JOIN \n" +
//                "                                (SELECT * FROM it_simrs_header_checkup) headerCheckup ON headerCheckup.no_checkup = checkup.no_checkup LEFT JOIN\n" +
//                "                                (SELECT id_tindakan, nama_tindakan FROM it_simrs_riwayat_tindakan GROUP BY id_tindakan, nama_tindakan) tindakan ON tindakan.id_tindakan = detailActivity.activity_id \n" +
//                "                                WHERE  \n" +
//                "                                jurnal.branch_id = '"+unit+"' AND  \n" +
//                "                                jurnal.tanggal_jurnal >= '"+paramTglStart+"' AND   \n" +
//                "                                jurnal.tanggal_jurnal < '"+paramTglEnd+"' AND   \n" +
//                "                                detailActivity.person_id = '"+dokterId+"'";
        String sqlQuery = "SELECT\n" +
                "    j.tanggal_jurnal,\n" +
                "    jd.jurnal_detail_id,\n" +
                "    jd.divisi_id,\n" +
                "    jda.activity_id,\n" +
                "    jda.person_id AS dokter_id,\n" +
                "    d.nama_dokter,\n" +
                "    jda.no_trans AS no_req,\n" +
                "    hdp.tipe_pelayanan AS jenis_rawat,\n" +
                "    jda.tipe AS kdjnspas,\n" +
                "    checkup.created_date AS tanggal,\n" +
                "    j.keterangan AS keterangan,\n" +
                "    checkup.tarif_bpjs AS tarif_inacbg,\n" +
                "    jda.jumlah AS bruto,\n" +
                "    dkso.jenis_kso,\n" +
                "    dkso.master_id,\n" +
                "    dkso.persen_kso,\n" +
                "    dksot.persen_kso AS persen_kso_tindakan,\n" +
                "    headerCheckup.id_pasien,\n" +
                "    headerCheckup.nama AS nama_pasien,\n" +
                "    dkso.persen_ks,\n" +
                "    pelayanan.id_pelayanan AS id_poli,\n" +
                "    hdp.nama_pelayanan AS nama_poli,\n" +
                "    rt.nama_tindakan AS activity_name,\n" +
                "    dkso.kodering AS kode_jabatan\n" +
                "FROM it_akun_jurnal_detail_activity jda\n" +
                "         INNER JOIN im_simrs_dokter_kso dkso ON dkso.nip = jda.person_id AND dkso.flag = 'Y'\n" +
                "         INNER JOIN im_simrs_dokter_kso_tindakan dksot ON dksot.dokter_kso_id = dkso.dokter_kso_id AND dksot.flag = 'Y'\n" +
                "         INNER JOIN it_simrs_riwayat_tindakan rt ON rt.id_tindakan = jda.activity_id AND rt.jenis_pasien = dkso.master_id AND rt.keterangan = dkso.jenis_kso AND rt.id_detail_checkup = jda.no_trans\n" +
                "         INNER JOIN it_akun_jurnal_detail jd ON jd.jurnal_detail_id = jda.jurnal_detail_id\n" +
                "         INNER JOIN it_akun_jurnal j ON j.no_jurnal = jd.no_jurnal\n" +
                "         INNER JOIN it_simrs_tindakan_rawat tr ON tr.id_tindakan_rawat = rt.id_tindakan AND  tr.id_tindakan = dksot.tindakan_id AND tr.id_detail_checkup = rt.id_detail_checkup\n" +
                "         INNER JOIN im_simrs_tindakan t ON t.id_tindakan = tr.id_tindakan\n" +
                "         INNER JOIN im_simrs_dokter d ON d.id_dokter = jda.person_id\n" +
                "         INNER JOIN it_hris_pegawai_position pp ON pp.nip = d.id_dokter\n" +
                "         INNER JOIN it_simrs_header_detail_checkup AS checkup ON checkup.id_detail_checkup = jda.no_trans\n" +
                "         INNER JOIN it_simrs_header_checkup AS headerCheckup ON headerCheckup.no_checkup = checkup.no_checkup\n" +
                "         INNER JOIN im_simrs_pelayanan AS pelayanan ON pelayanan.id_pelayanan = checkup.id_pelayanan\n" +
                "         INNER JOIN im_simrs_header_pelayanan AS hdp ON hdp.id_header_pelayanan = pelayanan.id_header_pelayanan\n"+
                "WHERE\n" +
                "        j.branch_id = '"+unit+"' AND\n" +
                "        j.tanggal_jurnal >= '"+paramTglStart+"' AND\n" +
                "        j.tanggal_jurnal < '"+paramTglEnd+"' AND\n" +
                "        jda.person_id = '"+dokterId+"';";
        // End Fahmi

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(sqlQuery)
                .list();

        return results;
    }

    public List<ItHrisPendapatanDokterEntity> getDataPendapatanDokter(String branchId, String bulan, String tahun) throws HibernateException {
        List<ItHrisPendapatanDokterEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPendapatanDokterEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
