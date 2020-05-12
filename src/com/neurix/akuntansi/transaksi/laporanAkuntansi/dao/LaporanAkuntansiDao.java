
package com.neurix.akuntansi.transaksi.laporanAkuntansi.dao;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ArusKasDTO;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.PendapatanDTO;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class LaporanAkuntansiDao extends GenericDao<ItLaporanAkuntansiEntity, String> {

    @Override
    protected Class<ItLaporanAkuntansiEntity> getEntityClass() {
        return ItLaporanAkuntansiEntity.class;
    }

    @Override
    public List<ItLaporanAkuntansiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItLaporanAkuntansiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("laporan_akuntansi_id")!=null) {
                criteria.add(Restrictions.eq("laporanAkuntansiId", (String) mapCriteria.get("laporan_akuntansi_id")));
            }
            if (mapCriteria.get("laporan_akuntansi_name")!=null) {
                criteria.add(Restrictions.ilike("laporanAkuntansiName", "%" + (String)mapCriteria.get("laporan_akuntansi_name") + "%"));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("laporanAkuntansiId"));
        List<ItLaporanAkuntansiEntity> results = criteria.list();
        return results;
    }

    public List<Aging> getAging(String branchId, String periode,String masterId,String reportId,String tipeLaporan){
        List<Aging> listOfResult = new ArrayList<>();
        String tipeWhere = "";
        if (!"".equalsIgnoreCase(masterId)){
            tipeWhere = "and masterId like '"+masterId+"'";
        }
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select distinct\n" +
                "  * \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "\t  kr.kode_rekening,\n" +
                "      b.rekening_id, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.tanggal_jurnal as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.master_id as masterId, \n" +
                "      d.nama as namaMaster, \n" +
                "      -- d.master_id as masterGrp, \n" +
                "      f.nilai_kurs as kurs, \n" +
                "      kr.nama_kode_rekening \n" +
                "    from \n" +
                "      (\n" +
                "\t\t-- mencari semua data di jurnal yang di joinkan dengan setting aging jurnal \n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal\n" +
                "        where \n" +
                "          flag = 'Y' \n" +
                "          and registered_flag = 'Y'\n" +
                "\t\t  and branch_id IN ("+branchId+")\n" +
                "      ) a \n" +
                "      inner join it_akun_jurnal_detail b on b.no_jurnal = a.no_jurnal \n" +
                "      inner join im_akun_mata_uang c on a.mata_uang_id = c.mata_uang_id \n" +
                "      left join im_akun_master d on b.master_id = d.nomor_master \n" +
                "\t  INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = b.rekening_id\n" +
                "      INNER JOIN (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          mt_akun_kurs \n" +
                "        where \n" +
                "          flag = 'A'\n" +
                "      ) f on f.mata_uang_id = a.mata_uang_id \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y'\n" +
                "  ) foo \n" +
                "where\n" +
                "  to_date(\n" +
                "    cast(tgljurnal as TEXT), \n" +
                "    'yyyy-MM'\n" +
                "  ) < (\n" +
                "    to_date('"+periode+"', 'yyyy-MM')+ Interval '1 month') " +
                "and rekening_id IN (\n" +
                "                select\n" +
                "                    rekening_id\n" +
                "                from\n" +
                "                    im_akun_report_detail\n" +
                "                where\n" +
                "                    report_id='"+reportId+"'\n" +
                "                       and tipe_laporan='"+tipeLaporan+"'" +
                "                       and flag='Y'"+
                "            )\n" +
                tipeWhere +
                "order by \n" +
                "  -- mastergrp, \n" +
                "  kode_rekening, \n" +
                "  masterId, \n" +
                "  tglJurnal, \n" +
                "  masterId asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Aging data= new Aging();
            data.setKodeRekening((String) row[0]);
            data.setRekeningId((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setTglJurnal((Date) row[3]);
            data.setMataUang((String) row[4]);
            data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setMasterId((String) row[6]);
            if ((String) row[7]!=null){
                data.setNamaMaster((String) row[7]);
            }else{
                data.setNamaMaster("");
            }
//            data.setMasterGrp(row[8].toString());
            data.setKurs(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setNamaRekening((String) row[9]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PendapatanDTO> getPendapatanPerActivityPerDokter(String reportId, String unit, String periode){
        List<PendapatanDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT \n" +
                "\ta.*,\n" +
                "\trt.nama_tindakan,\n" +
                "\tdt.nama_dokter,\n" +
                "\tdt.kodering,\n" +
                "\ttr.id_tindakan,\n" +
                "\tr.nama_ruangan,\n" +
                "\trt.keterangan as keterangantindakan\n" +
                "FROM\n" +
                " ( select\n" +
                "  \t  x.keterangan,\n" +
                "\t  z.person_id,\n" +
                "\t  z.activity_id,\n" +
                "      z.jumlah as biaya,\n" +
                "      z.no_trans\n" +
                "    from\n" +
                "      it_akun_jurnal x\n" +
                "      inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "\t  inner join it_akun_jurnal_detail_activity z on y.jurnal_detail_id = z.jurnal_detail_id\n" +
                "    where\n" +
                "      to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"'\n" +
                "      and x.registered_flag = 'Y'\n" +
                "      and x.branch_id IN ("+unit+")\n" +
                "      AND y.rekening_id IN (\n" +
                "        SELECT\n" +
                "          rekening_id\n" +
                "        FROM\n" +
                "          im_akun_report_detail\n" +
                "        WHERE\n" +
                "          report_id = '"+reportId+"'\n" +
                "          AND flag = 'Y'\n" +
                "      )\n" +
                " )a\n" +
                "LEFT JOIN it_simrs_riwayat_tindakan rt ON a.activity_id=rt.id_tindakan\n" +
                "LEFT JOIN im_simrs_dokter dt ON dt.id_dokter = a.person_id\n" +
                "LEFT JOIN it_simrs_tindakan_rawat tr ON a.activity_id=tr.id_tindakan_rawat\n" +
                "LEFT JOIN mt_simrs_ruangan r ON r.id_ruangan=a.activity_id\n" +
                "ORDER BY\n" +
                "id_tindakan,activity_id,no_trans";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PendapatanDTO data= new PendapatanDTO();
            data.setKeterangan((String) row[0]);
            data.setDokterId((String) row[1]);
            data.setBiaya(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            data.setNoTrans((String) row[4]);
            data.setDokterName((String) row[6]);
            data.setKoderingDokter((String) row[7]);
            if ("tindakan".equalsIgnoreCase((String) row[10])){
                data.setActivityId((String) row[8]);
                data.setActivityName((String) row[5]);
            }else if ("resep".equalsIgnoreCase((String) row[10])){
                data.setActivityId("RSP");
                data.setActivityName("Resep");
            }else if ("kamar".equalsIgnoreCase((String) row[10])){
                data.setActivityId((String) row[2]);
                data.setActivityName((String) row[9]);
            }

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PendapatanDTO> getPendapatanPerDokterPerActivity(String reportId, String unit, String periode){
        List<PendapatanDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT \n" +
                "\ta.*,\n" +
                "\trt.nama_tindakan,\n" +
                "\tdt.nama_dokter,\n" +
                "\tdt.kodering,\n" +
                "\ttr.id_tindakan,\n" +
                "\tr.nama_ruangan,\n" +
                "\trt.keterangan as keterangantindakan\n" +
                "FROM\n" +
                " ( select\n" +
                "  \t  x.keterangan,\n" +
                "\t  z.person_id,\n" +
                "\t  z.activity_id,\n" +
                "      z.jumlah as biaya,\n" +
                "      z.no_trans\n" +
                "    from\n" +
                "      it_akun_jurnal x\n" +
                "      inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "\t  inner join it_akun_jurnal_detail_activity z on y.jurnal_detail_id = z.jurnal_detail_id\n" +
                "    where\n" +
                "      to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"'\n" +
                "      and x.registered_flag = 'Y'\n" +
                "      and x.branch_id IN ("+unit+")\n" +
                "      AND y.rekening_id IN (\n" +
                "        SELECT\n" +
                "          rekening_id\n" +
                "        FROM\n" +
                "          im_akun_report_detail\n" +
                "        WHERE\n" +
                "          report_id = '"+reportId+"'\n" +
                "          AND flag = 'Y'\n" +
                "      )\n" +
                " )a\n" +
                "LEFT JOIN it_simrs_riwayat_tindakan rt ON a.activity_id=rt.id_tindakan\n" +
                "LEFT JOIN im_simrs_dokter dt ON dt.id_dokter = a.person_id\n" +
                "LEFT JOIN it_simrs_tindakan_rawat tr ON a.activity_id=tr.id_tindakan_rawat\n" +
                "LEFT JOIN mt_simrs_ruangan r ON r.id_ruangan=a.activity_id\n" +
                "ORDER BY\n" +
                "kodering,no_trans";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PendapatanDTO data= new PendapatanDTO();
            data.setKeterangan((String) row[0]);
            data.setDokterId((String) row[1]);
            data.setBiaya(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            data.setNoTrans((String) row[4]);
            data.setDokterName((String) row[6]);
            data.setKoderingDokter((String) row[7]);
            if ("tindakan".equalsIgnoreCase((String) row[10])){
                data.setActivityId((String) row[8]);
                data.setActivityName((String) row[5]);
            }else if ("resep".equalsIgnoreCase((String) row[10])){
                data.setActivityId("RSP");
                data.setActivityName("Resep");
            }else if ("kamar".equalsIgnoreCase((String) row[10])){
                data.setActivityId((String) row[2]);
                data.setActivityName((String) row[9]);
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<Aging> getAgingPasien(String branchId, String periode,String masterId,String reportId,String tipeLaporan){
        List<Aging> listOfResult = new ArrayList<>();
        String tipeWhere = "";
        if (!"".equalsIgnoreCase(masterId)){
            tipeWhere = "and masterId like '"+masterId+"'";
        }
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "  * \n" +
                "from \n" +
                "  (\n" +
                "    select \n" +
                "      kr.kode_rekening, \n" +
                "      b.rekening_id, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.tanggal_jurnal as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.pasien_id as masterId, \n" +
                "      d.nama as namaMaster, \n" +
                "      f.nilai_kurs as kurs \n" +
                "    from \n" +
                "      (\n" +
                "        -- mencari semua data di jurnal yang di joinkan dengan setting aging jurnal          \n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          it_akun_jurnal \n" +
                "        where \n" +
                "          flag = 'Y' \n" +
                "          and registered_flag = 'Y' \n" +
                "\t\t  and branch_id IN ("+branchId+")\n" +
                "      ) a \n" +
                "      inner join it_akun_jurnal_detail b on b.no_jurnal = a.no_jurnal \n" +
                "      inner join im_akun_mata_uang c on a.mata_uang_id = c.mata_uang_id \n" +
                "      inner join im_simrs_pasien d on b.pasien_id = d.id_pasien \n" +
                "      INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = b.rekening_id \n" +
                "      INNER JOIN (\n" +
                "        select \n" +
                "          * \n" +
                "        from \n" +
                "          mt_akun_kurs \n" +
                "        where \n" +
                "          flag = 'A'\n" +
                "      ) f on f.mata_uang_id = a.mata_uang_id \n" +
                "    where \n" +
                "      a.flag = 'Y' \n" +
                "      and a.registered_flag = 'Y'\n" +
                "  ) foo \n" +
                "where \n" +
                "  to_date(\n" +
                "    cast(tgljurnal as TEXT), \n" +
                "    'MM-yyyy'\n" +
                "  ) < (\n" +
                "    to_date('"+periode+"', 'MM-yyyy')+ Interval '1 month'\n" +
                "  ) \n" +
                "  and rekening_id IN (\n" +
                "    select \n" +
                "      rekening_id \n" +
                "    from \n" +
                "      im_akun_report_detail \n" +
                "    where \n" +
                "      report_id = '"+reportId+"'\n" +
                "       and tipe_laporan='"+tipeLaporan+"' \n" +
                "      and flag='Y'"+
                "  ) \n" +
                "  "+tipeWhere+" \n" +
                "order by \n" +
                "  masterId, \n" +
                "  tglJurnal, \n" +
                "  masterId asc\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Aging data= new Aging();
            data.setKodeRekening((String) row[0]);
            data.setRekeningId((String) row[1]);
            data.setNoNota((String) row[2]);
            data.setTglJurnal((Date) row[3]);
            data.setMataUang((String) row[4]);
            data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setMasterId((String) row[6]);
            data.setNamaMaster((String) row[7]);
//            data.setMasterGrp(row[8].toString());
            data.setKurs(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public String getLevelKodeRekening(String reportId){
        String result="";
        String query = "select level_kode_rekening from im_akun_report where report_id='"+reportId+"' limit 1";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = results.toString();
        }else {
            result=null;
        }
        return result;
    }

    // Generate surrogate id from postgre
    public String getNextLaporanAkuntansiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_laporan_akuntansi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "RPT"+sId;
    }

    public Integer searchReportIdExisting(String reportId){
        Integer result=0;
        String[] tabel = {"im_akun_report_detail","im_akun_setting_report_user"};

        for (int i=0 ; i<2 ; i++){
            String query = "SELECT\n" +
                    "\tCOUNT(report_id)\n" +
                    "FROM\n" +
                    "\t"+tabel[i]+"\n" +
                    "WHERE\n" +
                    "\tflag='Y' AND\n" +
                    "\treport_id='"+reportId+"'";
            Object results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query).uniqueResult();
            if (results!=null){
                result = result+Integer.parseInt(results.toString());
            }
        }
        return result;
    }

    public List<AkunSettingReportKeuanganKonsolDetail> getAllDataLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchIdAll){
        List<AkunSettingReportKeuanganKonsolDetail> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.namarekening, \n" +
                "  a.koderekening, \n" +
                "  a.konsolid, \n" +
                "  a.operator, \n" +
                "  --unit 1\n" +
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1 ,\n" +
                "  a.jumlahlastdebitunit1 - a.jumlahlastkreditunit1 as lastsaldounit1,\n" +
                "  (a.jumlahdebitunit1+a.jumlahlastdebitunit1) - (a.jumlahkreditunit1+a.jumlahlastkreditunit1) as cursaldounit1,\n" +
                "  --unit 2\n" +
                "  a.jumlahdebitunit2 - a.jumlahkreditunit2 as saldounit2 ,\n" +
                "  a.jumlahlastdebitunit2 - a.jumlahlastkreditunit2 as lastsaldounit2,\n" +
                "  (a.jumlahdebitunit2+a.jumlahlastdebitunit2) - (a.jumlahkreditunit2+a.jumlahlastkreditunit2) as cursaldounit2,\n" +
                "  --unit 3\n" +
                "  a.jumlahdebitunit3 - a.jumlahkreditunit3 as saldounit3 ,\n" +
                "  a.jumlahlastdebitunit3 - a.jumlahlastkreditunit3 as lastsaldounit3,\n" +
                "  (a.jumlahdebitunit3+a.jumlahlastdebitunit3) - (a.jumlahkreditunit3+a.jumlahlastkreditunit3) as cursaldounit3,\n" +
                "  --unit 4\n" +
                "  a.jumlahdebitunit4 - a.jumlahkreditunit4 as saldounit4 ,\n" +
                "  a.jumlahlastdebitunit4 - a.jumlahlastkreditunit4 as lastsaldounit4,\n" +
                "  (a.jumlahdebitunit4+a.jumlahlastdebitunit4) - (a.jumlahkreditunit4+a.jumlahlastkreditunit4) as cursaldounit4,\n" +
                "  --unit all\n" +
                "  a.jumlahdebitunitAll - a.jumlahkreditunitAll as saldounitAll ,\n" +
                "  a.jumlahlastdebitunitAll - a.jumlahlastkreditunitAll as lastsaldounitAll,\n" +
                "  (a.jumlahdebitunitAll+a.jumlahlastdebitunitAll) - (a.jumlahkreditunitAll+a.jumlahlastkreditunitAll) as cursaldounitAll\n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.konsolid, \n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "\t  --unit 1\n" +
                "\t  CASE WHEN junit1.jumlah_debit IS NULL THEN 0 ELSE junit1.jumlah_debit END as jumlahdebitunit1, \n" +
                "\t  CASE WHEN junit1.jumlah_kredit IS NULL THEN 0 ELSE junit1.jumlah_kredit END as jumlahkreditunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_debit IS NULL THEN 0 ELSE sa1.jumlah_debit END as jumlahlastdebitunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_kredit IS NULL THEN 0 ELSE sa1.jumlah_kredit END as jumlahlastkreditunit1,\n" +
                "\t  -- unit 2\n" +
                "\t  CASE WHEN junit2.jumlah_debit IS NULL THEN 0 ELSE junit2.jumlah_debit END as jumlahdebitunit2, \n" +
                "\t  CASE WHEN junit2.jumlah_kredit IS NULL THEN 0 ELSE junit2.jumlah_kredit END as jumlahkreditunit2,\n" +
                "\t  CASE WHEN sa2.jumlah_debit IS NULL THEN 0 ELSE sa2.jumlah_debit END as jumlahlastdebitunit2,\n" +
                "\t  CASE WHEN sa2.jumlah_kredit IS NULL THEN 0 ELSE sa2.jumlah_kredit END as jumlahlastkreditunit2,\n" +
                "\t  -- unit 3\n" +
                "\t  CASE WHEN junit3.jumlah_debit IS NULL THEN 0 ELSE junit3.jumlah_debit END as jumlahdebitunit3, \n" +
                "\t  CASE WHEN junit3.jumlah_kredit IS NULL THEN 0 ELSE junit3.jumlah_kredit END as jumlahkreditunit3,\n" +
                "\t  CASE WHEN sa3.jumlah_debit IS NULL THEN 0 ELSE sa3.jumlah_debit END as jumlahlastdebitunit3,\n" +
                "\t  CASE WHEN sa3.jumlah_kredit IS NULL THEN 0 ELSE sa3.jumlah_kredit END as jumlahlastkreditunit3,\n" +
                "\t  -- unit 4\n" +
                "\t  CASE WHEN junit4.jumlah_debit IS NULL THEN 0 ELSE junit4.jumlah_debit END as jumlahdebitunit4, \n" +
                "\t  CASE WHEN junit4.jumlah_kredit IS NULL THEN 0 ELSE junit4.jumlah_kredit END as jumlahkreditunit4,\n" +
                "\t  CASE WHEN sa4.jumlah_debit IS NULL THEN 0 ELSE sa4.jumlah_debit END as jumlahlastdebitunit4,\n" +
                "\t  CASE WHEN sa4.jumlah_kredit IS NULL THEN 0 ELSE sa4.jumlah_kredit END as jumlahlastkreditunit4,\n" +
                "\t  --total\n" +
                "\t  CASE WHEN junitAll.jumlah_debit IS NULL THEN 0 ELSE junitAll.jumlah_debit END as jumlahdebitunitAll, \n" +
                "\t  CASE WHEN junitAll.jumlah_kredit IS NULL THEN 0 ELSE junitAll.jumlah_kredit END as jumlahkreditunitAll,\n" +
                "\t  CASE WHEN saAll.jumlah_debit IS NULL THEN 0 ELSE saAll.jumlah_debit END as jumlahlastdebitunitAll,\n" +
                "\t  CASE WHEN saAll.jumlah_kredit IS NULL THEN 0 ELSE saAll.jumlah_kredit END as jumlahlastkreditunitAll\n" +
                "    FROM \n" +
                "      (\n" +
                "        select \n" +
                "          setting_report_konsol_id AS konsolid, \n" +
                "          operator AS operator, \n" +
                "          kode.kode_rekening, \n" +
                "          kode.rekening_id, \n" +
                "          kode.nama_kode_rekening \n" +
                "        from \n" +
                "          im_akun_kode_rekening kode \n" +
                "          INNER JOIN im_akun_setting_report_keuangan_konsol_detail rd ON rd.rekening_id = kode.rekening_id \n" +
                "        where \n" +
                "          rd.flag = 'Y' \n" +
                "        group by \n" +
                "          setting_report_konsol_id, \n" +
                "          operator, \n" +
                "          kode_rekening, \n" +
                "          kode.nama_kode_rekening, \n" +
                "          kode.rekening_id \n" +
                "        order by \n" +
                "          kode_rekening\n" +
                "      ) a \n" +
                "      LEFT OUTER JOIN (\n" +
                "\t  --unit 1\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit1 ON junit1.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa1 ON sa1.rekening_id = a.rekening_id \n" +
                "\t  -- UNIT 2\n" +
                "      LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId2+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit2 ON junit2.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId2+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa2 ON sa2.rekening_id = a.rekening_id \n" +
                "\t  -- UNIT 3\n" +
                "      LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId3+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit3 ON junit3.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId3+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa3 ON sa3.rekening_id = a.rekening_id \n" +
                "\t  -- UNIT 4\n" +
                "      LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId4+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit4 ON junit4.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId4+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa4 ON sa4.rekening_id = a.rekening_id \n" +
                "\t  -- Total\n" +
                "      LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id IN ("+branchIdAll+") \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junitall ON junitall.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id IN ("+branchIdAll+") \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) saall ON saall.rekening_id = a.rekening_id \n" +
                "    order by \n" +
                "      a.kode_rekening\n" +
                "  ) a \n" +
                "ORDER BY \n" +
                "  a.koderekening\n\n\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            AkunSettingReportKeuanganKonsolDetail data= new AkunSettingReportKeuanganKonsolDetail();
            data.setNamaRekening((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setSettingReportKonsolId((String) row[2]);
            data.setOperator((String) row[3]);
            //unit 1
            data.setSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setLastSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setCurSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            //unit 2
            data.setSaldoUnit2(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setLastSaldoUnit2(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setCurSaldoUnit2(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            //unit 3
            data.setSaldoUnit3(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
            data.setLastSaldoUnit3(BigDecimal.valueOf(Double.parseDouble(row[11].toString())));
            data.setCurSaldoUnit3(BigDecimal.valueOf(Double.parseDouble(row[12].toString())));
            //unit 4
            data.setSaldoUnit4(BigDecimal.valueOf(Double.parseDouble(row[13].toString())));
            data.setLastSaldoUnit4(BigDecimal.valueOf(Double.parseDouble(row[14].toString())));
            data.setCurSaldoUnit4(BigDecimal.valueOf(Double.parseDouble(row[15].toString())));
            //unit All
            data.setSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[16].toString())));
            data.setLastSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[17].toString())));
            data.setCurSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[18].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<AkunSettingReportKeuanganKonsolDetail> getDataLaporanAkuntansiKonsolUnit(String periode, String branchId1){
        List<AkunSettingReportKeuanganKonsolDetail> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.namarekening, \n" +
                "  a.koderekening, \n" +
                "  a.konsolid, \n" +
                "  a.operator, \n" +
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1 ,\n" +
                "  a.jumlahlastdebitunit1 - a.jumlahlastkreditunit1 as lastsaldounit1,\n" +
                "  (a.jumlahdebitunit1+a.jumlahlastdebitunit1) - (a.jumlahkreditunit1+a.jumlahlastkreditunit1) as cursaldounit1\n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.konsolid, \n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "\t  CASE WHEN junit1.jumlah_debit IS NULL THEN 0 ELSE junit1.jumlah_debit END as jumlahdebitunit1, \n" +
                "\t  CASE WHEN junit1.jumlah_kredit IS NULL THEN 0 ELSE junit1.jumlah_kredit END as jumlahkreditunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_debit IS NULL THEN 0 ELSE sa1.jumlah_debit END as jumlahlastdebitunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_kredit IS NULL THEN 0 ELSE sa1.jumlah_kredit END as jumlahlastkreditunit1\n" +
                "    FROM \n" +
                "      (\n" +
                "        select \n" +
                "          setting_report_konsol_id AS konsolid, \n" +
                "          operator AS operator, \n" +
                "          kode.kode_rekening, \n" +
                "          kode.rekening_id, \n" +
                "          kode.nama_kode_rekening \n" +
                "        from \n" +
                "          im_akun_kode_rekening kode \n" +
                "          INNER JOIN im_akun_setting_report_keuangan_konsol_detail rd ON rd.rekening_id = kode.rekening_id \n" +
                "        where \n" +
                "          rd.flag = 'Y' \n" +
                "        group by \n" +
                "          setting_report_konsol_id, \n" +
                "          operator, \n" +
                "          kode_rekening, \n" +
                "          kode.nama_kode_rekening, \n" +
                "          kode.rekening_id \n" +
                "        order by \n" +
                "          kode_rekening\n" +
                "      ) a \n" +
                "      LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.tanggal_jurnal, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit1 ON junit1.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa1 ON sa1.rekening_id = a.rekening_id \n" +
                "    order by \n" +
                "      a.kode_rekening\n" +
                "  ) a \n" +
                "ORDER BY \n" +
                "  a.koderekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            AkunSettingReportKeuanganKonsolDetail data= new AkunSettingReportKeuanganKonsolDetail();
            data.setNamaRekening((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setSettingReportKonsolId((String) row[2]);
            data.setOperator((String) row[3]);
            data.setSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setLastSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setCurSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<ArusKasDTO> getArusKas(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggalAwalBulan = splitTanggal[2]+"-"+splitTanggal[1]+"-01";
        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];
        String periodeBulan =splitTanggal[1]+"-"+splitTanggal[2];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.koderekening, \n" +
                "  a.grup, \n" +
                "  a.namarekening, \n" +
                "  a.penerimaan, \n" +
                "  a.pengeluaran, \n" +
                "  a.lastsaldobulan + a.lastsaldotanggal AS lastsaldo, \n" +
                "  a.lastsaldobulan + a.lastsaldotanggal + a.penerimaan - a.pengeluaran AS saldosekarang \n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.kode_rekening as kodeRekening, \n" +
                "      a.grup as grup, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as penerimaan, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as pengeluaran, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN d.jumlah_debit IS NULL \n" +
                "          OR d.jumlah_kredit IS NULL THEN 0 ELSE (d.jumlah_debit - d.jumlah_kredit) END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as lastSaldoBulan, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN e.jumlah_debit IS NULL \n" +
                "          OR e.jumlah_kredit IS NULL THEN 0 ELSE (e.jumlah_debit - e.jumlah_kredit) END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as lastSaldoTanggal \n" +
                "    FROM \n" +
                "      (\n" +
                "        SELECT \n" +
                "          x.*, \n" +
                "          y.nama_kode_rekening as grup \n" +
                "        FROM \n" +
                "          (\n" +
                "            select \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id, \n" +
                "              left(kode_rekening, 9) as grup_id \n" +
                "            from \n" +
                "              im_akun_kode_rekening \n" +
                "            where \n" +
                "              rekening_id IN (\n" +
                "                select \n" +
                "                  rekening_id \n" +
                "                from \n" +
                "                  im_akun_report_detail \n" +
                "                where \n" +
                "                  report_id = '"+reportId+"' \n" +
                "                  and flag = 'Y'\n" +
                "              ) \n" +
                "            group by \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id \n" +
                "            order by \n" +
                "              kode_rekening\n" +
                "          ) x \n" +
                "          INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "      ) a \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          y.rekening_id as rekening_id, \n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_jurnal x \n" +
                "          inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "          left outer join im_akun_mata_uang mu ON (x.mata_uang_id = mu.mata_uang_id) \n" +
                "          LEFT JOIN it_akun_jurnal_detail_pending jdp ON y.no_jurnal = jdp.no_jurnal \n" +
                "        where \n" +
                "          x.tanggal_jurnal = '"+tanggal+"' \n" +
                "          and x.registered_flag = 'Y' \n" +
                "          and x.branch_id IN ("+unit+") \n" +
                "          AND jdp.no_jurnal is null \n" +
                "        group by \n" +
                "          y.rekening_id\n" +
                "      ) c ON c.rekening_id = a.rekening_id \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          x.rekening_id as rekening_id, \n" +
                "          sum(x.jumlah_debit) as jumlah_debit, \n" +
                "          sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_saldo_akhir x \n" +
                "        where \n" +
                "          to_date(x.periode, 'MM-yyyy') = to_date('"+periodeBulan+"', 'MM-yyyy') - Interval '1 month' \n" +
                "          and x.branch_id IN ("+unit+") \n" +
                "        group by \n" +
                "          x.rekening_id\n" +
                "      ) d ON d.rekening_id = a.rekening_id \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          jd.rekening_id as rekening_id, \n" +
                "          sum(jd.jumlah_debit) as jumlah_debit, \n" +
                "          sum(jd.jumlah_kredit) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_jurnal_detail jd \n" +
                "          LEFT JOIN it_akun_jurnal j ON j.no_jurnal = jd.no_jurnal \n" +
                "        where \n" +
                "          j.branch_id IN ("+unit+") \n" +
                "          AND j.tanggal_jurnal >= '"+tanggalAwalBulan+"' \n" +
                "          AND j.tanggal_jurnal < '"+tanggal+"' \n" +
                "          AND j.registered_flag = 'Y' \n" +
                "        group by \n" +
                "          jd.rekening_id\n" +
                "      ) e ON e.rekening_id = a.rekening_id\n" +
                "  ) a \n" +
                "where \n" +
                "  a.lastsaldobulan + a.lastsaldotanggal + a.penerimaan - a.pengeluaran <> 0\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup((String) row[1]);
            data.setNamaRekening((String) row[2]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setLastSaldo(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));

            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<ArusKasDTO> getArusKasDetail(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggalAwalBulan = splitTanggal[2]+"-"+splitTanggal[1]+"-01";
        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];
        String periodeBulan =splitTanggal[1]+"-"+splitTanggal[2];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  a.koderekening,\n" +
                "  a.grup,\n" +
                "  a.namarekening,\n" +
                "  a.nojurnal,\n" +
                "  a.keterangan,\n" +
                "  a.tanggaljurnal,\n" +
                "  a.penerimaan,\n" +
                "  a.pengeluaran,\n" +
                "  a.lastsaldobulan+a.lastsaldotanggal AS lastsaldo,\n" +
                "  a.lastsaldobulan+a.lastsaldotanggal+a.penerimaan-a.pengeluaran AS saldosekarang \n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      distinct a.kode_rekening as kodeRekening,\n" +
                "      a.grup as grup,\n" +
                "      a.nama_kode_rekening as namaRekening,\n" +
                "\t  c.no_jurnal as nojurnal,\n" +
                "\t  c.keterangan as keterangan,\n" +
                "\t  c.tanggal_jurnal as tanggaljurnal,\n" +
                "\t  (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as penerimaan,\n" +
                "\t  (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as pengeluaran,\n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN d.jumlah_debit IS NULL\n" +
                "          OR d.jumlah_kredit IS NULL THEN 0 ELSE (d.jumlah_debit - d.jumlah_kredit) END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as lastSaldoBulan,\n" +
                "\t  (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN e.jumlah_debit IS NULL\n" +
                "          OR e.jumlah_kredit IS NULL THEN 0 ELSE (e.jumlah_debit - e.jumlah_kredit) END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as lastSaldoTanggal\n" +
                "    FROM\n" +
                "      (\n" +
                "        SELECT\n" +
                "          x.*,\n" +
                "          y.nama_kode_rekening as grup\n" +
                "        FROM\n" +
                "          (\n" +
                "            select\n" +
                "              kode_rekening,\n" +
                "              nama_kode_rekening,\n" +
                "              rekening_id,\n" +
                "              left(kode_rekening, 9) as grup_id\n" +
                "            from\n" +
                "              im_akun_kode_rekening\n" +
                "            where\n" +
                "              rekening_id IN (\n" +
                "                select\n" +
                "                  rekening_id\n" +
                "                from\n" +
                "                  im_akun_report_detail\n" +
                "                where\n" +
                "                  report_id = '"+reportId+"'\n" +
                "                  and flag = 'Y'\n" +
                "              )\n" +
                "            group by\n" +
                "              kode_rekening,\n" +
                "              nama_kode_rekening,\n" +
                "              rekening_id\n" +
                "            order by\n" +
                "              kode_rekening\n" +
                "          ) x\n" +
                "          INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "      ) a\n" +
                "      left outer JOIN (\n" +
                "        select\n" +
                "          y.rekening_id as rekening_id,\n" +
                "\t\t  x.no_jurnal as no_jurnal,\n" +
                "\t\t  x.tanggal_jurnal as tanggal_jurnal,\n" +
                "\t\t  x.keterangan as keterangan,\n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit,\n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit\n" +
                "        from\n" +
                "          it_akun_jurnal x\n" +
                "          inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "          left outer join im_akun_mata_uang mu ON (x.mata_uang_id = mu.mata_uang_id)\n" +
                "\t\t  LEFT JOIN it_akun_jurnal_detail_pending jdp ON y.no_jurnal = jdp.no_jurnal \n" +
                "        where\n" +
                "         x.tanggal_jurnal = '"+tanggal+"'\n" +
                "          and x.registered_flag = 'Y'\n" +
                "          and x.branch_id IN ("+unit+")\n" +
                "          AND jdp.no_jurnal is null \t\t  \n" +
                "        group by\n" +
                "          y.rekening_id,\n" +
                "\t\t  x.no_jurnal,\n" +
                "\t\t  x.tanggal_jurnal,\n" +
                "\t\t  x.keterangan\n" +
                "      ) c ON c.rekening_id = a.rekening_id\n" +
                "      left outer JOIN (\n" +
                "        select\n" +
                "\t\t  x.rekening_id as rekening_id,\n" +
                "          sum(x.jumlah_debit) as jumlah_debit,\n" +
                "          sum(x.jumlah_kredit) as jumlah_kredit\n" +
                "        from\n" +
                "         \tit_akun_saldo_akhir x\n" +
                "        where\n" +
                "          to_date(x.periode, 'MM-yyyy') = to_date('"+periodeBulan+"', 'MM-yyyy') - Interval '1 month'\n" +
                "          and x.branch_id IN ("+unit+")\n" +
                "        group by\n" +
                "          x.rekening_id\n" +
                "      ) d ON d.rekening_id = a.rekening_id\n" +
                "\t  left outer JOIN (\n" +
                "        select\n" +
                "\t\t  jd.rekening_id as rekening_id,\n" +
                "\t\t  sum(jd.jumlah_debit) as jumlah_debit,\n" +
                "          sum(jd.jumlah_kredit) as jumlah_kredit\n" +
                "        from\n" +
                "         \tit_akun_jurnal_detail jd\n" +
                "\t\t\tLEFT JOIN it_akun_jurnal j ON j.no_jurnal=jd.no_jurnal\n" +
                "        where\n" +
                "           j.branch_id IN ("+unit+")\n" +
                "\t  \t\tAND j.tanggal_jurnal>='"+tanggalAwalBulan+"'\n" +
                "\t  \t\tAND j.tanggal_jurnal<'"+tanggal+"'\n" +
                "\t\t  AND j.registered_flag='Y'\n" +
                "        group by\n" +
                "          jd.rekening_id\n" +
                "      ) e ON e.rekening_id = a.rekening_id\n" +
                "  ) a\n" +
                "where a.lastsaldobulan+a.lastsaldotanggal+a.penerimaan-a.pengeluaran <>0";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup((String) row[1]);
            data.setNamaRekening((String) row[2]);
            data.setNoJurnal((String) row[3]);
            data.setKeterangan((String) row[4]);
            data.setTanggalJurnal((Date) row[5]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setLastSaldo(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));

            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<ArusKasDTO> getArusKasBiayaPendingTanggalItu(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  a.koderekening,\n" +
                "  a.grup,\n" +
                "  a.namarekening,\n" +
                "  a.penerimaan,\n" +
                "  a.pengeluaran,\n" +
                "  a.penerimaan-a.pengeluaran AS saldosekarang\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      distinct a.kode_rekening as kodeRekening,\n" +
                "      a.grup as grup,\n" +
                "      a.nama_kode_rekening as namaRekening,\n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as penerimaan,\n" +
                "\t  (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as pengeluaran\n" +
                "    FROM\n" +
                "      (\n" +
                "        SELECT\n" +
                "          x.*,\n" +
                "          y.nama_kode_rekening as grup\n" +
                "        FROM\n" +
                "          (\n" +
                "            select\n" +
                "              kode_rekening,\n" +
                "              nama_kode_rekening,\n" +
                "              rekening_id,\n" +
                "              left(kode_rekening, 9) as grup_id\n" +
                "            from\n" +
                "              im_akun_kode_rekening\n" +
                "            where\n" +
                "              rekening_id IN (\n" +
                "                select\n" +
                "                  rekening_id\n" +
                "                from\n" +
                "                  im_akun_report_detail\n" +
                "                where\n" +
                "                  report_id = '"+reportId+"'\n" +
                "                  and flag = 'Y'\n" +
                "              )\n" +
                "            group by\n" +
                "              kode_rekening,\n" +
                "              nama_kode_rekening,\n" +
                "              rekening_id\n" +
                "            order by\n" +
                "              kode_rekening\n" +
                "          ) x\n" +
                "          INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "      ) a\n" +
                "      left outer JOIN (\n" +
                "        select\n" +
                "          y.rekening_id as rekening_id,\n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit,\n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit\n" +
                "        from\n" +
                "          it_akun_jurnal_pending x\n" +
                "          inner join it_akun_jurnal_detail_pending y on x.no_jurnal = y.no_jurnal\n" +
                "        where\n" +
                "          x.tanggal_jurnal = '"+tanggal+"'\n" +
                "          and x.registered_flag = 'Y'\n" +
                "          and x.branch_id IN ("+unit+")\n" +
                "        group by\n" +
                "          y.rekening_id\n" +
                "      ) c ON c.rekening_id = a.rekening_id\n" +
                "  ) a\n" +
                "where a.penerimaan-a.pengeluaran <>0";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup("KAS PENDING");
            data.setNamaRekening((String) row[2]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setLastSaldo(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<ArusKasDTO> getArusKasBiayaPendingDibayarTanggalItu(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  koderekening, \n" +
                "  grup, \n" +
                "  namarekening, \n" +
                "  sum(penerimaan) as penerimaan, \n" +
                "  sum(pengeluaran) as pengeluaran, \n" +
                "  sum(saldosekarang) as saldosekarang \n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      a.koderekening, \n" +
                "      a.grup, \n" +
                "      a.namarekening, \n" +
                "      a.penerimaan, \n" +
                "      a.pengeluaran, \n" +
                "      a.penerimaan - a.pengeluaran AS saldosekarang, \n" +
                "      a.no_jurnal_pembayaran \n" +
                "    FROM \n" +
                "      (\n" +
                "        SELECT \n" +
                "          distinct a.kode_rekening as kodeRekening, \n" +
                "          a.grup as grup, \n" +
                "          a.nama_kode_rekening as namaRekening, \n" +
                "          (\n" +
                "            CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "              CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "            ) ELSE NULL END\n" +
                "          ) as penerimaan, \n" +
                "          (\n" +
                "            CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "              CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "            ) ELSE NULL END\n" +
                "          ) as pengeluaran, \n" +
                "          d.no_jurnal as no_jurnal_pembayaran \n" +
                "        FROM \n" +
                "          (\n" +
                "            SELECT \n" +
                "              x.*, \n" +
                "              y.nama_kode_rekening as grup \n" +
                "            FROM \n" +
                "              (\n" +
                "                select \n" +
                "                  kode_rekening, \n" +
                "                  nama_kode_rekening, \n" +
                "                  rekening_id, \n" +
                "                  left(kode_rekening, 9) as grup_id \n" +
                "                from \n" +
                "                  im_akun_kode_rekening \n" +
                "                where \n" +
                "                  rekening_id IN (\n" +
                "                    select \n" +
                "                      rekening_id \n" +
                "                    from \n" +
                "                      im_akun_report_detail \n" +
                "                    where \n" +
                "                      report_id = '"+reportId+"' \n" +
                "                      and flag = 'Y'\n" +
                "                  ) \n" +
                "                group by \n" +
                "                  kode_rekening, \n" +
                "                  nama_kode_rekening, \n" +
                "                  rekening_id \n" +
                "                order by \n" +
                "                  kode_rekening\n" +
                "              ) x \n" +
                "              INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "          ) a \n" +
                "          left outer JOIN (\n" +
                "            select \n" +
                "              x.no_jurnal, \n" +
                "              y.rekening_id as rekening_id, \n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "            where \n" +
                "              x.tanggal_jurnal = '"+tanggal+"' \n" +
                "              and x.registered_flag = 'Y' \n" +
                "              and x.branch_id IN ("+unit+") \n" +
                "            group by \n" +
                "              y.rekening_id, \n" +
                "              x.no_jurnal\n" +
                "          ) c ON c.rekening_id = a.rekening_id \n" +
                "          left outer JOIN (\n" +
                "            select \n" +
                "              x.no_jurnal, \n" +
                "              y.rekening_id as rekening_id, \n" +
                "              sum(y.jumlah_debit) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal_pending x \n" +
                "              inner join it_akun_jurnal_detail_pending y on x.no_jurnal = y.no_jurnal \n" +
                "            where \n" +
                "              x.registered_flag = 'Y' \n" +
                "              and x.branch_id IN ("+unit+") \n" +
                "            group by \n" +
                "              y.rekening_id, \n" +
                "              x.no_jurnal\n" +
                "          ) d ON c.no_jurnal = d.no_jurnal\n" +
                "      ) a \n" +
                "    WHERE \n" +
                "      a.no_jurnal_pembayaran is not null\n" +
                "  ) hasil \n" +
                "where \n" +
                "  saldosekarang <> 0 \n" +
                "group by \n" +
                "  koderekening, \n" +
                "  grup, \n" +
                "  namarekening\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup("KAS/BANK PENDING DITERIMA");
            data.setNamaRekening((String) row[2]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setLastSaldo(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<ArusKasDTO> getArusKasDetailBiayaPendingTanggalItu(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.koderekening, \n" +
                "  a.grup, \n" +
                "  a.namarekening, \n" +
                "  a.nojurnal, \n" +
                "  a.keterangan, \n" +
                "  a.tanggaljurnal, \n" +
                "  a.penerimaan, \n" +
                "  a.pengeluaran, \n" +
                "  a.penerimaan - a.pengeluaran AS saldosekarang \n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.kode_rekening as kodeRekening, \n" +
                "      a.grup as grup, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      c.no_jurnal as nojurnal, \n" +
                "      c.keterangan as keterangan, \n" +
                "      c.tanggal_jurnal as tanggaljurnal, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as penerimaan, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as pengeluaran \n" +
                "    FROM \n" +
                "      (\n" +
                "        SELECT \n" +
                "          x.*, \n" +
                "          y.nama_kode_rekening as grup \n" +
                "        FROM \n" +
                "          (\n" +
                "            select \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id, \n" +
                "              left(kode_rekening, 9) as grup_id \n" +
                "            from \n" +
                "              im_akun_kode_rekening \n" +
                "            where \n" +
                "              rekening_id IN (\n" +
                "                select \n" +
                "                  rekening_id \n" +
                "                from \n" +
                "                  im_akun_report_detail \n" +
                "                where \n" +
                "                  report_id = '"+reportId+"' \n" +
                "                  and flag = 'Y'\n" +
                "              ) \n" +
                "            group by \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id \n" +
                "            order by \n" +
                "              kode_rekening\n" +
                "          ) x \n" +
                "          INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "      ) a \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          y.rekening_id as rekening_id, \n" +
                "          x.tanggal_jurnal, \n" +
                "          x.keterangan, \n" +
                "          x.no_jurnal, \n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_jurnal_pending x \n" +
                "          inner join it_akun_jurnal_detail_pending y on x.no_jurnal = y.no_jurnal \n" +
                "        where \n" +
                "          x.tanggal_jurnal = '"+tanggal+"' \n" +
                "          and x.registered_flag = 'Y' \n" +
                "          and x.branch_id IN ("+unit+") \n" +
                "        group by \n" +
                "          y.rekening_id, \n" +
                "          x.tanggal_jurnal, \n" +
                "          x.keterangan, \n" +
                "          x.no_jurnal\n" +
                "      ) c ON c.rekening_id = a.rekening_id\n" +
                "  ) a \n" +
                "where \n" +
                "  a.penerimaan - a.pengeluaran <> 0\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup("KAS PENDING");
            data.setNamaRekening((String) row[2]);
            data.setNoJurnal((String) row[3]);
            data.setKeterangan((String) row[4]);
            data.setTanggalJurnal((Date) row[5]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setLastSaldo(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<ArusKasDTO> getArusKasDetailBiayaPendingDibayarTanggalItu(String reportId, String unit, String periode){
        String[] splitTanggal = periode.split("-");

        String tanggal = splitTanggal[2]+"-"+splitTanggal[1]+"-"+splitTanggal[0];

        List<ArusKasDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.koderekening, \n" +
                "  a.grup, \n" +
                "  a.namarekening, \n" +
                "  a.nojurnal, \n" +
                "  a.tanggaljurnal, \n" +
                "  a.keterangan, \n" +
                "  a.penerimaan, \n" +
                "  a.pengeluaran, \n" +
                "  a.penerimaan - a.pengeluaran AS saldosekarang \n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.kode_rekening as kodeRekening, \n" +
                "      a.grup as grup, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      d.no_jurnal as nojurnal, \n" +
                "      d.tanggal_jurnal as tanggaljurnal, \n" +
                "      d.keterangan as keterangan, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as penerimaan, \n" +
                "      (\n" +
                "        CASE WHEN length(a.kode_rekening) = 12 THEN (\n" +
                "          CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "        ) ELSE NULL END\n" +
                "      ) as pengeluaran, \n" +
                "      d.no_jurnal as no_jurnal_pembayaran \n" +
                "    FROM \n" +
                "      (\n" +
                "        SELECT \n" +
                "          x.*, \n" +
                "          y.nama_kode_rekening as grup \n" +
                "        FROM \n" +
                "          (\n" +
                "            select \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id, \n" +
                "              left(kode_rekening, 9) as grup_id \n" +
                "            from \n" +
                "              im_akun_kode_rekening \n" +
                "            where \n" +
                "              rekening_id IN (\n" +
                "                select \n" +
                "                  rekening_id \n" +
                "                from \n" +
                "                  im_akun_report_detail \n" +
                "                where \n" +
                "                  report_id = '"+reportId+"' \n" +
                "                  and flag = 'Y'\n" +
                "              ) \n" +
                "            group by \n" +
                "              kode_rekening, \n" +
                "              nama_kode_rekening, \n" +
                "              rekening_id \n" +
                "            order by \n" +
                "              kode_rekening\n" +
                "          ) x \n" +
                "          INNER JOIN im_akun_kode_rekening y ON x.grup_id = y.kode_rekening\n" +
                "      ) a \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          x.no_jurnal, \n" +
                "          y.rekening_id as rekening_id, \n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_jurnal x \n" +
                "          inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "        where \n" +
                "          x.tanggal_jurnal = '"+tanggal+"' \n" +
                "          and x.registered_flag = 'Y' \n" +
                "          and x.branch_id IN ("+unit+") \n" +
                "        group by \n" +
                "          y.rekening_id, \n" +
                "          x.no_jurnal\n" +
                "      ) c ON c.rekening_id = a.rekening_id \n" +
                "      left outer JOIN (\n" +
                "        select \n" +
                "          x.no_jurnal, \n" +
                "          y.rekening_id as rekening_id, \n" +
                "          x.tanggal_jurnal, \n" +
                "          x.keterangan, \n" +
                "          sum(y.jumlah_debit) as jumlah_debit, \n" +
                "          sum(y.jumlah_kredit) as jumlah_kredit \n" +
                "        from \n" +
                "          it_akun_jurnal_pending x \n" +
                "          inner join it_akun_jurnal_detail_pending y on x.no_jurnal = y.no_jurnal \n" +
                "        where \n" +
                "          x.registered_flag = 'Y' \n" +
                "          and x.branch_id IN ("+unit+") \n" +
                "        group by \n" +
                "          y.rekening_id, \n" +
                "          x.no_jurnal, \n" +
                "          x.tanggal_jurnal, \n" +
                "          x.keterangan\n" +
                "      ) d ON c.no_jurnal = d.no_jurnal\n" +
                "  ) a \n" +
                "WHERE \n" +
                "  a.no_jurnal_pembayaran is not null \n" +
                "  and a.penerimaan - a.pengeluaran <> 0\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ArusKasDTO data= new ArusKasDTO();
            data.setKodeRekening((String) row[0]);
            data.setGrup("KAS PENDING DIBAYAR");
            data.setNamaRekening((String) row[2]);
            data.setNoJurnal((String) row[3]);
            data.setKeterangan((String) row[5]);
            data.setTanggalJurnal((Date) row[4]);
            data.setPenerimaan(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setPengeluaran(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setSaldoSekarang(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setLastSaldo(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }
}
