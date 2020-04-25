
package com.neurix.akuntansi.transaksi.laporanAkuntansi.dao;

import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.Aging;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.ItLaporanAkuntansiEntity;
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
                "      f.nilai_kurs as kurs\n" +
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
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1, \n" +
                "  a.jumlahdebitunit2 - a.jumlahkreditunit2 as saldounit2, \n" +
                "  a.jumlahdebitunit3 - a.jumlahkreditunit3 as saldounit3, \n" +
                "  a.jumlahdebitunit4 - a.jumlahkreditunit4 as saldounit4, \n" +
                "  a.jumlahdebitunitall - a.jumlahkreditunitall as saldounitall \n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.konsolid, \n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "      junit1.jumlah_debit as jumlahdebitunit1, \n" +
                "      junit1.jumlah_kredit as jumlahkreditunit1, \n" +
                "      junit2.jumlah_debit as jumlahdebitunit2, \n" +
                "      junit2.jumlah_kredit as jumlahkreditunit2, \n" +
                "      junit3.jumlah_debit as jumlahdebitunit3, \n" +
                "      junit3.jumlah_kredit as jumlahkreditunit3, \n" +
                "      junit4.jumlah_debit as jumlahdebitunit4, \n" +
                "      junit4.jumlah_kredit as jumlahkreditunit4, \n" +
                "      junitall.jumlah_debit as jumlahdebitunitall, \n" +
                "      junitall.jumlah_kredit as jumlahkreditunitall \n" +
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
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit1 ON junit1.rekening_id = a.rekening_id -- UNIT 2\n" +
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
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId2+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit2 ON junit2.rekening_id = a.rekening_id -- UNIT 3\n" +
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
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId3+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit3 ON junit3.rekening_id = a.rekening_id -- UNIT 4\n" +
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
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId4+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit4 ON junit4.rekening_id = a.rekening_id -- Total\n" +
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
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id IN (\n" +
                "                "+branchIdAll+"\n" +
                "              ) \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junitall ON junitall.rekening_id = a.rekening_id \n" +
                "    order by \n" +
                "      a.kode_rekening\n" +
                "  ) a \n" +
                "ORDER BY \n" +
                "  a.koderekening\n\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            AkunSettingReportKeuanganKonsolDetail data= new AkunSettingReportKeuanganKonsolDetail();
            data.setNamaRekening((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setSettingReportKonsolId((String) row[2]);
            data.setOperator((String) row[3]);
            if (row[4]==null){
                data.setSaldoUnit1(BigDecimal.ZERO);
            }else{
                data.setSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]==null){
                data.setSaldoUnit2(BigDecimal.ZERO);
            }else{
                data.setSaldoUnit2(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]==null){
                data.setSaldoUnit3(BigDecimal.ZERO);
            }else{
                data.setSaldoUnit3(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]==null){
                data.setSaldoUnit4(BigDecimal.ZERO);
            }else{
                data.setSaldoUnit4(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            if (row[8]==null){
                data.setSaldoUnitAll(BigDecimal.ZERO);
            }else{
                data.setSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            }
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
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1\n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.konsolid, \n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "      junit1.jumlah_debit as jumlahdebitunit1, \n" +
                "      junit1.jumlah_kredit as jumlahkreditunit1\n" +
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
                "              kr.kode_rekening AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_jurnal x \n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id \n" +
                "            where \n" +
                "              to_char(x.created_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit1 ON junit1.rekening_id = a.rekening_id \n" +
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
            if (row[4]==null){
                data.setSaldoUnit1(BigDecimal.ZERO);
            }else{
                data.setSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }
}
