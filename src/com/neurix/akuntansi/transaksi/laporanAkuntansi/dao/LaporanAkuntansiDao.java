
package com.neurix.akuntansi.transaksi.laporanAkuntansi.dao;

import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKas;
import com.neurix.akuntansi.master.settingReportArusKas.model.AkunSettingReportKeuanganArusKasDetail;
import com.neurix.akuntansi.transaksi.budgeting.model.Budgeting;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingDetail;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgetingPengadaan;
import com.neurix.akuntansi.transaksi.budgeting.model.BudgettingDTO;
import com.neurix.akuntansi.transaksi.laporanAkuntansi.model.*;
import com.neurix.akuntansi.master.settingReportKeuanganKonsol.model.AkunSettingReportKeuanganKonsolDetail;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
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
                "\t  kr.rekening_id,\n" +
                "      b.nomor_rekening as kode_rekening, \n" +
                "      b.no_nota as noNota, " +
                "      b.no_jurnal as noJurnal, " +
                "      a.registered_date as tglJurnal, \n" +
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
                "\t  INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = b.nomor_rekening\n" +
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
                "   and namamaster is not null\n "+
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
            data.setNoJurnal((String) row[3]);
            data.setTglJurnal((Date) row[4]);
            data.setMataUang((String) row[5]);
            data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setMasterId((String) row[7]);
            if ((String) row[8]!=null){
                data.setNamaMaster((String) row[8]);
            }else{
                data.setNamaMaster("");
            }
//            data.setMasterGrp(row[8].toString());
            data.setKurs(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            data.setNamaRekening((String) row[10]);
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<Aging> getLawanAging(String branchId, String noJurnal,String noNota){
        List<Aging> listOfResult = new ArrayList<>();
        String tipeWhere = "";
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select * from it_akun_jurnal_detail detail\n" +
                "join it_akun_jurnal jurnal on jurnal.no_jurnal = detail.no_jurnal\n" +
                "where no_nota = :noNota and detail.no_jurnal != :noJurnal and jurnal.branch_id in ( :branchId )";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("noNota", noNota)
                .setParameter("noJurnal", noJurnal)
                .setParameter("branchId", branchId)
                .list();

        for (Object[] row : results) {
            Aging data= new Aging();
            data.setNoJurnal((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setMasterId((String) row[2]);
            data.setNoNota((String) row[3]);
            data.setTotal(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
//            data.setMasterId((String) row[6]);
            data.setMataUang((String) row[23]);
            data.setKurs(BigDecimal.valueOf(Double.parseDouble(row[24].toString())));
            data.setTglJurnal((Date) row[33]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<NeracaSaldoDTO> getListNeracaSaldo (String reportId,String periode,String branchId){
        List<NeracaSaldoDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  distinct\n" +
                "  a.grup as grup,\n" +
                "  a.nama_kode_rekening as namaRekening,\n" +
                "  a.kode_rekening as kodeRekening,\n" +
                "  c.jumlah_debit as lastDebit,\n" +
                "  c.jumlah_kredit as lastKredit,\n" +
                "  c.saldo as lastSaldo,\n" +
                "  c.posisi as lastPosisi,\n" +
                "  b.jumlah_debit as jumlahDebit,\n" +
                "  b.jumlah_kredit as jumlahKredit,\n" +
                "  (\n" +
                "    (\n" +
                "      CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "    ) + (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    )\n" +
                "  ) as currDebit,\n" +
                "  (\n" +
                "    (\n" +
                "      CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "    ) + (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    )\n" +
                "  ) as currKredit,\n" +
                "  (\n" +
                "    CASE WHEN c.posisi = 'K' THEN abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) ELSE abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) END\n" +
                "  ) as currSaldo,\n" +
                "  (\n" +
                "    CASE WHEN c.posisi = 'K' THEN CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) > 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) < 0 THEN 'D' ELSE NULL END ELSE CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) < 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) > 0 THEN 'D' ELSE NULL END END\n" +
                "  ) as currPosisi\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      rek.*,\n" +
                "      kr.nama_kode_rekening as grup\n" +
                "    FROM\n" +
                "      (\n" +
                "        select\n" +
                "          kode.kode_rekening,\n" +
                "          kode.rekening_id,\n" +
                "          kode.nama_kode_rekening,\n" +
                "          LEFT(kode_rekening, 1) as grup_id\n" +
                "        from\n" +
                "          im_akun_kode_rekening kode\n" +
                "          INNER JOIN im_akun_report_detail rd ON rd.rekening_id = kode.rekening_id\n" +
                "        where\n" +
                "          rd.report_id = '"+reportId+"'\n" +
                "\tand rd.flag='Y'\n" +
                "        group by\n" +
                "          kode_rekening,\n" +
                "          kode.nama_kode_rekening,\n" +
                "          kode.rekening_id\n" +
                "        order by\n" +
                "          kode_rekening\n" +
                "      ) rek\n" +
                "      INNER JOIN im_akun_kode_rekening kr ON rek.grup_id = kr.kode_rekening\n" +
                "  ) a\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      rekening_id_parent as rekening_id_parent,\n" +
                "      sum(jumlah_debit) as jumlah_debit,\n" +
                "      sum(jumlah_kredit) as jumlah_kredit\n" +
                "    from\n" +
                "      (\n" +
                "        select\n" +
                "          y.nomor_rekening as rekening_id,\n" +
                "          rek.rekening_id_parent as rekening_id_parent,\n" +
                "\t\t  (y.jumlah_debit * x.kurs) as jumlah_debit,\n" +
                "          (y.jumlah_kredit * x.kurs) as jumlah_kredit,\n" +
                "          x.created_date as created_date,\n" +
                "          x.registered_flag as registered_flag,\n" +
                "          x.branch_id\n" +
                "        from\n" +
                "          it_akun_jurnal x\n" +
                "          INNER JOIN it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "          INNER JOIN (\n" +
                "            SELECT\n" +
                "              rekening_id_parent,\n" +
                "              rekening_id\n" +
                "            FROM\n" +
                "              im_akun_kode_rekening kr\n" +
                "              INNER JOIN (\n" +
                "                SELECT\n" +
                "                  kode_rekening as kode_rekening_parent,\n" +
                "                  rd.rekening_id as rekening_id_parent\n" +
                "                FROM\n" +
                "                  im_akun_report_detail rd\n" +
                "                  INNER JOIN im_akun_kode_rekening kr ON rd.rekening_id = kr.rekening_id\n" +
                "                WHERE\n" +
                "                  report_id = '"+reportId+"'\n" +
                "\t\tand rd.flag='Y'\n" +
                "              ) a ON kr.kode_rekening ilike CONCAT(a.kode_rekening_parent, '%')\n" +
                "          ) rek ON rek.kode_rekening = y.rekening_id\n" +
                "        where\n" +
                "          to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "          and x.registered_flag = 'Y'\n" +
                "          and x.flag = 'Y'\n" +
                "          AND y.rekening_id IN (rek.kode_rekening)\n" +
                "\t\t  and branch_id IN ("+branchId+")\n" +
                "      ) k\n" +
                "    group by\n" +
                "      rekening_id_parent\n" +
                "  ) b ON b.rekening_id_parent = a.rekening_id\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      *\n" +
                "    from\n" +
                "      it_akun_saldo_akhir\n" +
                "    where\n" +
                "      to_date(periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "      and branch_id IN ("+branchId+")\n" +
                "      and rekening_id IN (\n" +
                "        SELECT\n" +
                "          rekening_id\n" +
                "        FROM\n" +
                "          im_akun_report_detail\n" +
                "        WHERE\n" +
                "          report_id = '"+reportId+"'\n" +
                "\tand flag='Y'\n" +
                "      )\n" +
                "  ) c ON a.rekening_id = c.rekening_id\n" +
                "order by\n" +
                "  a.kode_rekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            NeracaSaldoDTO data= new NeracaSaldoDTO();
            data.setGrup((String) row[0]);
            data.setNamarekening((String) row[1]);
            data.setKoderekening((String) row[2]);
            if (row[3]!=null){
                data.setLastdebit(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }
            if (row[4]!=null) {
                data.setLastkredit(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null) {
                data.setLastsaldo(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[7]!=null) {
                data.setJumlahdebit(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            if (row[8]!=null) {
                data.setJumlahkredit(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            }
            if (row[9]!=null) {
                data.setCurrdebit(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            }
            if (row[10]!=null) {
                data.setCurrkredit(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
            }
            if (row[11]!=null) {
                data.setCurrsaldo(BigDecimal.valueOf(Double.parseDouble(row[11].toString())));
            }
            data.setLastposisi((String) row[6]);
            data.setCurrposisi((String) row[12]);

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<NeracaSaldoDTO> getListNeracaMutasi (String reportId,String periode,String branchId){
        List<NeracaSaldoDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT\n" +
                "  a.grup as grup,\n" +
                "  a.kode_rekening as kodeRekening,\n" +
                "  nama_kode_rekening as namaRekening,\n" +
                "  c.jumlah_debit as lastDebit,\n" +
                "  c.jumlah_kredit as lastKredit,\n" +
                "  b.jumlah_debit as jumlahDebit,\n" +
                "  b.jumlah_kredit as jumlahKredit,\n" +
                "  (\n" +
                "    (\n" +
                "      CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END\n" +
                "    ) + (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    )\n" +
                "  ) as currDebit,\n" +
                "  (\n" +
                "    (\n" +
                "      CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END\n" +
                "    ) + (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    )\n" +
                "  ) as currKredit,\n" +
                "  (\n" +
                "    CASE WHEN c.posisi = 'K' THEN abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) ELSE abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) END\n" +
                "  ) as currSaldo,\n" +
                "  (\n" +
                "    CASE WHEN c.posisi = 'K' THEN CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) > 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) < 0 THEN 'D' ELSE NULL END ELSE CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) < 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) > 0 THEN 'D' ELSE NULL END END\n" +
                "  ) as currPosisi\n" +
                "FROM\n" +
                "  (SELECT rek.*,kr.nama_kode_rekening as grup FROM (\n" +
                "  select\n" +
                "\t  kode.kode_rekening,\n" +
                "\t  kode.rekening_id,\n" +
                "      kode.nama_kode_rekening,\n" +
                "\t  LEFT(kode_rekening, 1) as grup_id\n" +
                "    from\n" +
                "      im_akun_kode_rekening kode INNER JOIN\n" +
                "\t  im_akun_report_detail rd ON rd.rekening_id=kode.rekening_id\n" +
                "    where\n" +
                "      rd.report_id='"+reportId+"'\n" +
                "\tand rd.flag='Y'\n" +
                "    group by\n" +
                "      kode_rekening,\n" +
                "      kode.nama_kode_rekening,\n" +
                "\t  kode.rekening_id\n" +
                "    order by\n" +
                "      kode_rekening\n" +
                "  ) rek\n" +
                "   INNER JOIN im_akun_kode_rekening kr ON rek.grup_id=kr.kode_rekening\n" +
                "  ) a\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      rekening_id_parent as rekening_id_parent,\n" +
                "      sum(jumlah_debit) as jumlah_debit,\n" +
                "      sum(jumlah_kredit) as jumlah_kredit\n" +
                "    from\n" +
                "      (\n" +
                "        select\n" +
                "          y.rekening_id as rekening_id,\n" +
                "          rek.rekening_id_parent as rekening_id_parent,\n" +
                "\t\t  (y.jumlah_debit * x.kurs) as jumlah_debit,\n" +
                "          (y.jumlah_kredit * x.kurs) as jumlah_kredit,\n" +
                "          x.created_date as created_date,\n" +
                "          x.registered_flag as registered_flag\n" +
                "        from\n" +
                "          it_akun_jurnal x\n" +
                "          INNER JOIN it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "          INNER JOIN (\n" +
                "            SELECT\n" +
                "              rekening_id_parent,\n" +
                "              rekening_id\n" +
                "            FROM\n" +
                "              im_akun_kode_rekening kr\n" +
                "              INNER JOIN (\n" +
                "                SELECT\n" +
                "                  kode_rekening as kode_rekening_parent,\n" +
                "                  rd.rekening_id as rekening_id_parent\n" +
                "                FROM\n" +
                "                  im_akun_report_detail rd\n" +
                "                  INNER JOIN im_akun_kode_rekening kr ON rd.rekening_id = kr.rekening_id\n" +
                "                WHERE\n" +
                "                  report_id = '"+reportId+"'\n" +
                "              ) a ON kr.kode_rekening ilike CONCAT(a.kode_rekening_parent, '%')\n" +
                "          ) rek ON rek.rekening_id = y.rekening_id\n" +
                "        where\n" +
                "          to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "          and x.registered_flag = 'Y'\n" +
                "          and x.flag = 'Y'\n" +
                "          AND y.rekening_id IN (rek.rekening_id)\n" +
                "\tAND x.branch_id IN ("+branchId+")\n" +
                "      ) k\n" +
                "    group by\n" +
                "      rekening_id_parent\n" +
                "  ) b ON b.rekening_id_parent = a.rekening_id\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      rekening_id,\n" +
                "      jumlah_debit,\n" +
                "      jumlah_kredit,\n" +
                "      saldo,\n" +
                "      posisi\n" +
                "    from\n" +
                "      it_akun_saldo_akhir\n" +
                "    where\n" +
                "      to_date(periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month' and branch_id IN ("+branchId+")\n" +
                "  ) c ON a.rekening_id = c.rekening_id\n" +
                "ORDER BY a.kode_rekening asc";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            NeracaSaldoDTO data= new NeracaSaldoDTO();
            data.setGrup((String) row[0]);
            data.setKoderekening((String) row[1]);
            data.setNamarekening((String) row[2]);
            if (row[3]!=null){
                data.setLastdebit(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }
            if (row[4]!=null) {
                data.setLastkredit(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null) {
                data.setJumlahdebit(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]!=null) {
                data.setJumlahkredit(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null) {
                data.setCurrdebit(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            if (row[8]!=null) {
                data.setCurrkredit(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            }
            if (row[9]!=null) {
                data.setCurrsaldo(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            }
            data.setCurrposisi((String) row[10]);

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<IkhtisarBukuBesarDTO> getListIkhtisarBukuBesarPerBukuBantu (String reportId,String periode,String branchId){
        List<IkhtisarBukuBesarDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  distinct a.nama_kode_rekening as namaRekening,\n" +
                "  a.kode_rekening as koderekening,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE c.jumlah_debit END as lastdebit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE c.jumlah_kredit END as lastkredit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE c.saldo END as lastsaldo,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN NULL ELSE c.posisi END as lastposisi,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE b.jumlah_debit END as jumlahdebit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE b.jumlah_kredit END as jumlahkredit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE (\n" +
                "    CASE WHEN c.jumlah_debit IS NULL THEN 0 ELSE c.jumlah_debit END + CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "  ) END as currdebit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE (\n" +
                "    CASE WHEN c.jumlah_kredit IS NULL THEN 0 ELSE c.jumlah_kredit END + CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "  ) END as currkredit,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN 0 ELSE (\n" +
                "    CASE WHEN c.posisi = 'K' THEN abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) ELSE abs(\n" +
                "      (\n" +
                "        (\n" +
                "          CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "        ) + (\n" +
                "          CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "        )\n" +
                "      ) - (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) END\n" +
                "  ) END as currsaldo,\n" +
                "  CASE WHEN length(a.kode_rekening) - length(\n" +
                "    replace(a.kode_rekening, '.', '')\n" +
                "  ) / length('.') = 1 THEN NULL ELSE (\n" +
                "    CASE WHEN c.posisi = 'K' THEN CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) > 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "    ) < 0 THEN 'D' ELSE NULL END ELSE CASE WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) < 0 THEN 'K' WHEN (\n" +
                "      (\n" +
                "        CASE WHEN c.saldo IS NULL THEN 0 ELSE c.saldo END\n" +
                "      ) + (\n" +
                "        CASE WHEN b.jumlah_debit IS NULL THEN 0 ELSE b.jumlah_debit END\n" +
                "      )\n" +
                "    ) - (\n" +
                "      CASE WHEN b.jumlah_kredit IS NULL THEN 0 ELSE b.jumlah_kredit END\n" +
                "    ) > 0 THEN 'D' ELSE NULL END END\n" +
                "  ) END as currposisi\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      rek.*\n" +
                "    FROM\n" +
                "      (\n" +
                "        select\n" +
                "          kode.kode_rekening,\n" +
                "          kode.rekening_id,\n" +
                "          kode.nama_kode_rekening\n" +
                "        from\n" +
                "          im_akun_kode_rekening kode\n" +
                "          INNER JOIN im_akun_report_detail rd ON rd.rekening_id = kode.rekening_id\n" +
                "        where\n" +
                "          rd.report_id = '"+reportId+"'\n" +
                "          AND rd.flag = 'Y'\n" +
                "        group by\n" +
                "          kode_rekening,\n" +
                "          kode.nama_kode_rekening,\n" +
                "          kode.rekening_id\n" +
                "        order by\n" +
                "          kode_rekening\n" +
                "      ) rek\n" +
                "  ) a\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      jurnal.*,\n" +
                "      kr.rekening_id\n" +
                "    from\n" +
                "      (\n" +
                "        select\n" +
                "          concat(\n" +
                "            split_part(kr.kode_rekening, '.', 1),\n" +
                "            '.',\n" +
                "            split_part(kr.kode_rekening, '.', 2),\n" +
                "            '.',\n" +
                "            split_part(kr.kode_rekening, '.', 3)\n" +
                "          ) AS kode_rekening,\n" +
                "          sum(y.jumlah_debit * x.kurs) as jumlah_debit,\n" +
                "          sum(y.jumlah_kredit * x.kurs) as jumlah_kredit\n" +
                "\t\t  from\n" +
                "          it_akun_jurnal x\n" +
                "          inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = y.rekening_id\n" +
                "        where\n" +
                "          to_char(registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "      \t\tand x.branch_id IN ("+branchId+")\n" +
                "        group by\n" +
                "          concat(\n" +
                "            split_part(kr.kode_rekening, '.', 1),\n" +
                "            '.',\n" +
                "            split_part(kr.kode_rekening, '.', 2),\n" +
                "            '.',\n" +
                "            split_part(kr.kode_rekening, '.', 3)\n" +
                "          )\n" +
                "      ) jurnal\n" +
                "      INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "  ) b ON b.rekening_id = a.rekening_id\n" +
                "  LEFT OUTER JOIN (\n" +
                "    select\n" +
                "      rekening_id,\n" +
                "      jumlah_debit,\n" +
                "      jumlah_kredit,\n" +
                "      saldo,\n" +
                "      posisi\n" +
                "    from\n" +
                "      it_akun_saldo_akhir\n" +
                "    where\n" +
                "      to_date(periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "      and branch_id IN ("+branchId+")\n" +
                "  ) c ON a.rekening_id = c.rekening_id\n" +
                "order by\n" +
                "  a.kode_rekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            IkhtisarBukuBesarDTO data= new IkhtisarBukuBesarDTO();
            data.setNamarekening((String) row[0]);
            data.setKoderekening((String) row[1]);
            if (row[2]!=null){
                data.setLastdebit(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }
            if (row[3]!=null) {
                data.setLastkredit(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }
            if (row[4]!=null) {
                data.setLastsaldo(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[6]!=null) {
                data.setJumlahdebit(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null) {
                data.setJumlahkredit(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            if (row[8]!=null) {
                data.setCurrdebit(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            }
            if (row[9]!=null) {
                data.setCurrkredit(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            }
            if (row[10]!=null) {
                data.setCurrsaldo(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
            }
            data.setLastposisi((String) row[5]);
            data.setCurrposisi((String) row[11]);

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public BigDecimal getSaldoAwal ( String branchId,String kodeRekening , String masterId,String periode){
        String whereMaster = "";
        if (masterId!=null){
            if (!masterId.equalsIgnoreCase("")){
                whereMaster = "\t and a.master_id = '"+masterId+"' \n";
            }
        }
        BigDecimal total = new BigDecimal(0);
        String query="select debit - kredit as saldo \n" +
                "from ( select\n" +
                "    (CASE WHEN sum(a.jumlah_debit) IS NULL THEN 0.0 ELSE sum(a.jumlah_debit) END) as debit,\n" +
                "    (CASE WHEN sum(a.jumlah_kredit) IS NULL THEN 0.0 ELSE sum(a.jumlah_kredit) END) as kredit\n" +
                "from\n" +
                "\tit_akun_saldo_akhir a " +
                "\t left join im_akun_kode_rekening kr ON a.rekening_id = kr.rekening_id \n" +
                "where\n" +
                "          to_date(a.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "    and kr.kode_rekening = '"+kodeRekening+"'\n" +
               whereMaster+
                "    and a.branch_id IN ( "+branchId+") ) a ";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = (BigDecimal) results;
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }

    public BigDecimal getSaldoAwalByTanggal ( String branchId,String kodeRekening,String masterId,String periode,String tanggalAwal){
        String whereMaster = "";
        String whereMaster2 = "";
        String[] tanggal = tanggalAwal.split("-");
        Date tanggalAwalPertama = CommonUtil.convertStringToDate(tanggal[2]+"-"+tanggal[1]+"-01");
        Date dtTanggalAwal = CommonUtil.convertStringToDate(tanggalAwal);
        if (masterId!=null){
            if (!masterId.equalsIgnoreCase("")){
                whereMaster = "\t and x.master_id='"+masterId+"' \n";
                whereMaster2 = "\t and master_id='"+masterId+"' \n";
            }
        }
        BigDecimal total = new BigDecimal(0);
        String query="SELECT\n" +
                "  (debitBulanLalu + debitTangalSekarang ) - (kreditBulanLalu + kreditTangalSekarang) as saldo\n" +
                "FROM\n" +
                "  (\n" +
                "    select\n" +
                "      (\n" +
                "        CASE WHEN sum(sa.jumlah_debit) IS NULL THEN 0.0 ELSE sum(sa.jumlah_debit) END\n" +
                "      ) as debitBulanLalu,\n" +
                "      (\n" +
                "        CASE WHEN sum(sa.jumlah_kredit) IS NULL THEN 0.0 ELSE sum(sa.jumlah_kredit) END\n" +
                "      ) as kreditBulanLalu,\n" +
                "      (\n" +
                "        CASE WHEN sum(a.jumlah_debit) IS NULL THEN 0.0 ELSE sum(a.jumlah_debit) END\n" +
                "      ) as debitTangalSekarang,\n" +
                "      (\n" +
                "        CASE WHEN sum(a.jumlah_kredit) IS NULL THEN 0.0 ELSE sum(a.jumlah_kredit) END\n" +
                "      ) as kreditTangalSekarang\n" +
                "    FROM\n" +
                "      (\n" +
                "        select\n" +
                "          *\n" +
                "        from\n" +
                "          im_akun_kode_rekening\n" +
                "        where\n" +
                "          kode_rekening = '"+kodeRekening+"' \n" +
                "      ) rk\n" +
                "      LEFT JOIN (\n" +
                "        select\n" +
                "          *\n" +
                "        from\n" +
                "          it_akun_saldo_akhir_detail\n" +
                "        where\n" +
                "          to_date(periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                whereMaster2+
                "      ) sa ON rk.kode_rekening = sa.kode_rekening\n" +
                "      LEFT JOIN (\n" +
                "        select\n" +
                "          nomor_rekening,\n" +
                "          jumlah_debit,\n" +
                "          jumlah_kredit\n" +
                "        from\n" +
                "          it_akun_jurnal_detail x\n" +
                "          LEFT JOIN it_akun_jurnal y ON y.no_jurnal = x.no_jurnal\n" +
                "        WHERE\n" +
                "          y.registered_date < '"+tanggalAwalPertama+"'\n" +
                "          AND y.registered_date >= '"+dtTanggalAwal+"'\n" +
                "          and y.registered_flag = 'Y'\n" +
                "          and y.branch_id IN ("+branchId+")\n" +
                whereMaster+
                "      ) a ON a.nomor_rekening = rk.kode_rekening\n" +
                "  ) a";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = (BigDecimal) results;
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }

    public List<KartuBukuBesarPerBukuBantuDTO> getListKartuBukuBesarPerBukuBantu (String reportId,String periode,String branchId,String kodeRekening,String nomorMaster,String tipe,Date tanggalDari , Date tanggalSampai){
        List<KartuBukuBesarPerBukuBantuDTO> listOfResult = new ArrayList<>();
        String whereKodeRekening ="";
        String whereNomorMaster="";
        String wherePeriode="";

        if ("P".equalsIgnoreCase(tipe)){
            wherePeriode= "\t and to_char(x.registered_date, 'MM-yyyy') = '"+periode+"' \n";
        }else if ("T".equalsIgnoreCase(tipe)){
            wherePeriode= "\t and x.registered_date between '"+tanggalDari+"' and '"+tanggalSampai+"' \n";
        }

        if (kodeRekening!=null){
            if (!kodeRekening.equalsIgnoreCase("")){
                whereKodeRekening = "\t and rek.kode_rekening = '"+kodeRekening+"' \n";
            }
        }

        if (nomorMaster!=null){
            if (!nomorMaster.equalsIgnoreCase("")){
                whereNomorMaster = "\t and y.master_id = '"+nomorMaster+"' \n";
            }
        }

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "    x.registered_date,\n" +
                "    x.no_jurnal,\n" +
                "    x.spum_id,\n" +
                "    x.keterangan as keterangan,\n" +
                "    rek.kode_rekening,\n" +
                "    rek.nama_kode_rekening,\n" +
                "    y.master_id as nomorMaster,\n" +
                "    z.nama as namaMaster,\n" +
                "    y.no_nota as nomor_nota,\n" +
                "    y.jumlah_debit as jumlahDebit,\n" +
                "    y.jumlah_kredit as jumlahKredit\n" +
                "from\n" +
                "    it_akun_jurnal x\n" +
                "    left outer join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal\n" +
                "    left outer join im_akun_master z on y.master_id = z.nomor_master\n" +
                "    left outer join im_akun_kode_rekening rek on y.nomor_rekening = rek.kode_rekening\n" +
                "    left outer join im_akun_mata_uang mu ON (x.mata_uang_id = mu.mata_uang_id)\n" +
                "where\n" +
                "    lower(mu.kode_mata_uang) = 'rph'\n" +
                "    and x.registered_flag = 'Y'\n" +
                whereKodeRekening+
                whereNomorMaster+
                wherePeriode+
                "    and x.branch_id IN ("+branchId+")";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        /*List<Object[]> resultQuery = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .setParameter("periode", periode)
                .setParameter("tanggalDari", tanggalDari)
                .setParameter("tanggalSampai", tanggalSampai)
                .setParameter("kodeRekening", kodeRekening)
                .setParameter("kodeRekening", kodeRekening)
                .list();*/

        for (Object[] row : results) {
            KartuBukuBesarPerBukuBantuDTO data= new KartuBukuBesarPerBukuBantuDTO();
            data.setTanggal_jurnal((Date) row[0]);
            data.setNo_jurnal((String) row[1]);
            data.setSpum_id((String) row[2]);
            data.setKeterangan((String) row[3]);
            data.setKode_rekening((String) row[4]);
            data.setNama_kode_rekening((String) row[5]);
            data.setNomormaster((String) row[6]);
            data.setNamamaster((String) row[7]);
            data.setNomor_nota((String) row[8]);
            if (row[9]!=null){
                data.setJumlahdebit(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            }
            if (row[10]!=null) {
                data.setJumlahkredit(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
            }

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
                "\t  inner join it_akun_jurnal_detail_activity z on y.jurnal_detail_id = z.jurnal_detail_id " +
                "join im_akun_kode_rekening kodering on kodering.kode_rekening = y.nomor_rekening\n" +
                "    where\n" +
                "      to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "      and x.registered_flag = 'Y'\n" +
                "      and x.branch_id IN ("+unit+")\n" +
                "      AND kodering.rekening_id IN (\n" +
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
                "\t  inner join it_akun_jurnal_detail_activity z on y.jurnal_detail_id = z.jurnal_detail_id " +
                "join im_akun_kode_rekening kodering on kodering.kode_rekening = y.nomor_rekening\n" +
                "    where\n" +
                "      to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "      and x.registered_flag = 'Y'\n" +
                "      and x.branch_id IN ("+unit+")\n" +
                "      AND kodering.rekening_id IN (\n" +
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
                "      kr.rekening_id, \n" +
                "      b.nomor_rekening as kode_rekening, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.registered_date as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.pasien_id as masterId, \n" +
                "      d.nama as namaMaster, \n" +
                "      f.nilai_kurs as kurs, \n" +
                "      kr.nama_kode_rekening " +
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
                "      INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = b.nomor_rekening \n" +
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
            data.setNamaRekening((String) row[9]);
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<Aging> getAgingPegawai(String branchId, String periode,String masterId,String reportId,String tipeLaporan){
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
                "      kr.rekening_id, \n" +
                "      b.nomor_rekening as kode_rekening, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.registered_date as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.master_id as masterId, \n" +
                "      d.nama_pegawai as namaMaster, \n" +
                "      f.nilai_kurs as kurs, \n" +
                "      kr.nama_kode_rekening " +
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
                "      join im_hris_pegawai d on b.master_id = d.nip \n" +
                "      INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = b.nomor_rekening \n" +
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
            data.setNamaRekening((String) row[9]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<Aging> getAgingDokter(String branchId, String periode,String masterId,String reportId,String tipeLaporan){
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
                "      kr.rekening_id, \n" +
                "      b.nomor_rekening as kode_rekening, \n" +
                "      b.no_nota as noNota, \n" +
                "      a.registered_date as tglJurnal, \n" +
                "      c.kode_mata_uang as mataUang, \n" +
                "      (b.jumlah_debit - b.jumlah_kredit) as total, \n" +
                "      b.master_id as masterId, \n" +
                "      d.nama_dokter as namaMaster, \n" +
                "      f.nilai_kurs as kurs, \n" +
                "      kr.nama_kode_rekening " +
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
                "      inner join it_akun_jurnal_detail b on b.no_jurnal = a.no_jurnal " +
                "             join it_akun_jurnal_detail_activity z on b.jurnal_detail_id = z.jurnal_detail_id\n" +
                "      inner join im_akun_mata_uang c on a.mata_uang_id = c.mata_uang_id \n" +
                "      inner join im_simrs_dokter d on z.person_id = d.id_dokter \n" +
                "      INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = b.nomor_rekening \n" +
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
            data.setNamaRekening((String) row[9]);
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

    public List<AkunSettingReportKeuanganKonsolDetail> getAllDataLaporanAkuntansiKonsol(String periode, String branchId1, String branchId2, String branchId3, String branchId4, String branchId5, String branchId6, String branchId7, String branchIdAll){
        List<AkunSettingReportKeuanganKonsolDetail> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT a.namarekening,\n" +
                "  a.koderekening, \n" +
                "  a.konsolid, \n" +
                "  a.operator, \n" +
                "  --unit 1\n" +
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1, \n" +
                "  a.jumlahlastdebitunit1 - a.jumlahlastkreditunit1 as lastsaldounit1, \n" +
                "  (\n" +
                "    a.jumlahdebitunit1 + a.jumlahlastdebitunit1\n" +
                "  ) - (\n" +
                "    a.jumlahkreditunit1 + a.jumlahlastkreditunit1\n" +
                "  ) as cursaldounit1, \n" +
                "  --unit 2\n" +
                "  a.jumlahdebitunit2 - a.jumlahkreditunit2 as saldounit2, \n" +
                "  a.jumlahlastdebitunit2 - a.jumlahlastkreditunit2 as lastsaldounit2, \n" +
                "  (\n" +
                "    a.jumlahdebitunit2 + a.jumlahlastdebitunit2\n" +
                "  ) - (\n" +
                "    a.jumlahkreditunit2 + a.jumlahlastkreditunit2\n" +
                "  ) as cursaldounit2, \n" +
                "  --unit 3\n" +
                "  a.jumlahdebitunit3 - a.jumlahkreditunit3 as saldounit3, \n" +
                "  a.jumlahlastdebitunit3 - a.jumlahlastkreditunit3 as lastsaldounit3, \n" +
                "  (\n" +
                "    a.jumlahdebitunit3 + a.jumlahlastdebitunit3\n" +
                "  ) - (\n" +
                "    a.jumlahkreditunit3 + a.jumlahlastkreditunit3\n" +
                "  ) as cursaldounit3, \n" +
                "  --unit 4\n" +
                "  a.jumlahdebitunit4 - a.jumlahkreditunit4 as saldounit4, \n" +
                "  a.jumlahlastdebitunit4 - a.jumlahlastkreditunit4 as lastsaldounit4, \n" +
                "  ( a.jumlahdebitunit4 + a.jumlahlastdebitunit4 ) - ( a.jumlahkreditunit4 + a.jumlahlastkreditunit4 ) as cursaldounit4, \n" +
                "  --unit 5\n" +
                "  a.jumlahdebitunit5 - a.jumlahkreditunit5 as saldounit5, \n" +
                "  a.jumlahlastdebitunit5 - a.jumlahlastkreditunit5 as lastsaldounit5, \n" +
                "  ( a.jumlahdebitunit5 + a.jumlahlastdebitunit5 ) - ( a.jumlahkreditunit5 + a.jumlahlastkreditunit5 ) as cursaldounit5, \n" +
                "  --unit 6\n" +
                "  a.jumlahdebitunit6 - a.jumlahkreditunit6 as saldounit6, \n" +
                "  a.jumlahlastdebitunit6 - a.jumlahlastkreditunit6 as lastsaldounit6, \n" +
                "  ( a.jumlahdebitunit6 + a.jumlahlastdebitunit6 ) - ( a.jumlahkreditunit6 + a.jumlahlastkreditunit6 ) as cursaldounit6, \n" +
                "  --unit 7\n" +
                "  a.jumlahdebitunit7 - a.jumlahkreditunit7 as saldounit7, \n" +
                "  a.jumlahlastdebitunit7 - a.jumlahlastkreditunit7 as lastsaldounit7, \n" +
                "  ( a.jumlahdebitunit7 + a.jumlahlastdebitunit7 ) - ( a.jumlahkreditunit7 + a.jumlahlastkreditunit7 ) as cursaldounit7, \n" +
                "  --unit all\n" +
                "  a.jumlahdebitunitAll - a.jumlahkreditunitAll as saldounitAll, \n" +
                "  a.jumlahlastdebitunitAll - a.jumlahlastkreditunitAll as lastsaldounitAll, \n" +
                "  (\n" +
                "    a.jumlahdebitunitAll + a.jumlahlastdebitunitAll\n" +
                "  ) - (\n" +
                "    a.jumlahkreditunitAll + a.jumlahlastkreditunitAll\n" +
                "  ) as cursaldounitAll\n" +
                "FROM (SELECT distinct a.konsolid,\n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "      --unit 1\n" +
                "                      CASE\n" +
                "                        WHEN junit1.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit1.jumlah_debit END                                          as jumlahdebitunit1,\n" +
                "                      CASE\n" +
                "                        WHEN junit1.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit1.jumlah_kredit END                                         as jumlahkreditunit1,\n" +
                "      CASE WHEN sa1.jumlah_debit IS NULL THEN 0 ELSE sa1.jumlah_debit END as jumlahlastdebitunit1, \n" +
                "      CASE WHEN sa1.jumlah_kredit IS NULL THEN 0 ELSE sa1.jumlah_kredit END as jumlahlastkreditunit1, \n" +
                "      -- unit 2\n" +
                "                      CASE\n" +
                "                        WHEN junit2.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit2.jumlah_debit END                                          as jumlahdebitunit2,\n" +
                "                      CASE\n" +
                "                        WHEN junit2.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit2.jumlah_kredit END                                         as jumlahkreditunit2,\n" +
                "      CASE WHEN sa2.jumlah_debit IS NULL THEN 0 ELSE sa2.jumlah_debit END as jumlahlastdebitunit2, \n" +
                "      CASE WHEN sa2.jumlah_kredit IS NULL THEN 0 ELSE sa2.jumlah_kredit END as jumlahlastkreditunit2, \n" +
                "      -- unit 3\n" +
                "                      CASE\n" +
                "                        WHEN junit3.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit3.jumlah_debit END                                          as jumlahdebitunit3,\n" +
                "                      CASE\n" +
                "                        WHEN junit3.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit3.jumlah_kredit END                                         as jumlahkreditunit3,\n" +
                "      CASE WHEN sa3.jumlah_debit IS NULL THEN 0 ELSE sa3.jumlah_debit END as jumlahlastdebitunit3, \n" +
                "      CASE WHEN sa3.jumlah_kredit IS NULL THEN 0 ELSE sa3.jumlah_kredit END as jumlahlastkreditunit3, \n" +
                "      -- unit 4\n" +
                "                      CASE\n" +
                "                        WHEN junit4.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit4.jumlah_debit END                                          as jumlahdebitunit4,\n" +
                "                      CASE\n" +
                "                        WHEN junit4.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit4.jumlah_kredit END                                         as jumlahkreditunit4,\n" +
                "      CASE WHEN sa4.jumlah_debit IS NULL THEN 0 ELSE sa4.jumlah_debit END as jumlahlastdebitunit4, \n" +
                "      CASE WHEN sa4.jumlah_kredit IS NULL THEN 0 ELSE sa4.jumlah_kredit END as jumlahlastkreditunit4,\n" +
                "          -- unit 5\n" +
                "                      CASE\n" +
                "                        WHEN junit5.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit5.jumlah_debit END                                          as jumlahdebitunit5,\n" +
                "                      CASE\n" +
                "                        WHEN junit5.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit5.jumlah_kredit END                                         as jumlahkreditunit5,\n" +
                "      CASE WHEN sa5.jumlah_debit IS NULL THEN 0 ELSE sa5.jumlah_debit END as jumlahlastdebitunit5, \n" +
                "      CASE WHEN sa5.jumlah_kredit IS NULL THEN 0 ELSE sa5.jumlah_kredit END as jumlahlastkreditunit5,\n" +
                "          -- unit 6\n" +
                "                      CASE\n" +
                "                        WHEN junit6.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit6.jumlah_debit END                                          as jumlahdebitunit6,\n" +
                "                      CASE\n" +
                "                        WHEN junit6.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit6.jumlah_kredit END                                         as jumlahkreditunit6,\n" +
                "      CASE WHEN sa6.jumlah_debit IS NULL THEN 0 ELSE sa6.jumlah_debit END as jumlahlastdebitunit6, \n" +
                "      CASE WHEN sa6.jumlah_kredit IS NULL THEN 0 ELSE sa6.jumlah_kredit END as jumlahlastkreditunit6,\n" +
                "          -- unit 7\n" +
                "                      CASE\n" +
                "                        WHEN junit7.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junit7.jumlah_debit END                                          as jumlahdebitunit7,\n" +
                "                      CASE\n" +
                "                        WHEN junit7.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junit7.jumlah_kredit END                                         as jumlahkreditunit7,\n" +
                "      CASE WHEN sa7.jumlah_debit IS NULL THEN 0 ELSE sa7.jumlah_debit END as jumlahlastdebitunit7, \n" +
                "      CASE WHEN sa7.jumlah_kredit IS NULL THEN 0 ELSE sa7.jumlah_kredit END as jumlahlastkreditunit7,\n" +
                "      --total\n" +
                "                      CASE\n" +
                "                        WHEN junitAll.jumlah_debit IS NULL THEN 0\n" +
                "                        ELSE junitAll.jumlah_debit END                                        as jumlahdebitunitAll,\n" +
                "                      CASE\n" +
                "                        WHEN junitAll.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE junitAll.jumlah_kredit END                                       as jumlahkreditunitAll,\n" +
                "      CASE WHEN saAll.jumlah_debit IS NULL THEN 0 ELSE saAll.jumlah_debit END as jumlahlastdebitunitAll, \n" +
                "                      CASE\n" +
                "                        WHEN saAll.jumlah_kredit IS NULL THEN 0\n" +
                "                        ELSE saAll.jumlah_kredit END                                          as jumlahlastkreditunitAll\n" +
                "      FROM (select setting_report_konsol_id AS konsolid,\n" +
                "          operator AS operator, \n" +
                "          kode.kode_rekening, \n" +
                "          kode.rekening_id, \n" +
                "          kode.nama_kode_rekening \n" +
                "            from im_akun_kode_rekening kode\n" +
                "          INNER JOIN im_akun_setting_report_keuangan_konsol_detail rd ON rd.rekening_id = kode.rekening_id \n" +
                "            where rd.flag = 'Y'\n" +
                "            group by setting_report_konsol_id,\n" +
                "          operator, \n" +
                "          kode_rekening, \n" +
                "          kode.nama_kode_rekening, \n" +
                "          kode.rekening_id \n" +
                "            order by kode_rekening) a\n" +
                "\n" +
                "\n" +
                "      LEFT OUTER JOIN (\n" +
                "        --UNIT 1\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit1\n" +
                "               ON junit1.kode_rekening = a.kode_rekening\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa1\n" +
                "               ON sa1.kode_rekening = a.kode_rekening\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             -- UNIT 2\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId2+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit2\n" +
                "               ON junit2.kode_rekening = a.kode_rekening\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId2+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa2\n" +
                "               ON sa2.rekening_id = a.rekening_id\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             -- UNIT 3\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId3+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit3\n" +
                "               ON junit3.kode_rekening = a.kode_rekening\n" +
                "\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId3+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa3\n" +
                "               ON sa3.rekening_id = a.rekening_id\n" +
                "\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             ------------------------------------------ UNIT 4 ---------------------------------\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId4+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit4\n" +
                "               ON junit4.kode_rekening = a.kode_rekening\n" +
                "\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId4+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa4\n" +
                "               ON sa4.rekening_id = a.rekening_id\n" +
                "\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             ------------------------------------------ UNIT 5 ---------------------------------\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId5+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit5\n" +
                "               ON junit5.kode_rekening = a.kode_rekening\n" +
                "\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId5+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa5\n" +
                "               ON sa5.rekening_id = a.rekening_id\n" +
                "\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             ------------------------------------------ UNIT 6 ---------------------------------\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId6+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit6\n" +
                "               ON junit6.kode_rekening = a.kode_rekening\n" +
                "\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId6+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa6\n" +
                "               ON sa6.rekening_id = a.rekening_id\n" +
                "--------------------------------------------------------------------------------------------------\n" +
                "      LEFT OUTER JOIN (\n" +
                "                             ------------------------------------------ UNIT 7 ---------------------------------\n" +
                "                             select jurnal.*\n" +
                "                             from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                   from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                   where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id = '"+branchId7+"' \n" +
                "                                   group by kr.kode_rekening) jurnal\n" +
                "                                    INNER JOIN im_akun_kode_rekening kr\n" +
                "                                      ON kr.kode_rekening = jurnal.kode_rekening) junit7\n" +
                "               ON junit7.kode_rekening = a.kode_rekening\n" +
                "\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening\n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+branchId7+"' \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening) sa7\n" +
                "               ON sa7.rekening_id = a.rekening_id\n" +
                "--------------------------------------------------------------------------------------------------\n" +
                "               -- Total\n" +
                "             LEFT OUTER JOIN (select jurnal.*\n" +
                "                              from (select kr.kode_rekening              AS kode_rekening,\n" +
                "              sum(y.jumlah_debit * x.kurs) as jumlah_debit, \n" +
                "              sum(y.jumlah_kredit * x.kurs) as jumlah_kredit \n" +
                "                                    from it_akun_jurnal x\n" +
                "              inner join it_akun_jurnal_detail y on x.no_jurnal = y.no_jurnal \n" +
                "                                           INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening\n" +
                "                                    where to_char(x.registered_date, 'MM-yyyy') = '"+periode+"'\n" +
                "              and x.branch_id IN ("+branchIdAll+") \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr\n" +
                "                                       ON kr.kode_rekening = jurnal.kode_rekening) junitall\n" +
                "               ON junitall.kode_rekening = a.kode_rekening\n" +
                "             LEFT OUTER JOIN (select jurnal.*, kr.rekening_id\n" +
                "                              from (select kr.kode_rekening     AS kode_rekening,\n" +
                "              sum(x.jumlah_debit) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "                                    from it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "                                    where to_date(x.periode, 'MM-yyyy') =\n" +
                "                                          to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id IN ("+branchIdAll+") \n" +
                "                                    group by kr.kode_rekening) jurnal\n" +
                "                                     INNER JOIN im_akun_kode_rekening kr\n" +
                "                                       ON kr.kode_rekening = jurnal.kode_rekening) saall\n" +
                "               ON saall.rekening_id = a.rekening_id\n" +
                "      order by a.kode_rekening) a\n" +
                "ORDER BY a.koderekening";
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
            //unit 5
            data.setSaldoUnit5(BigDecimal.valueOf(Double.parseDouble(row[16].toString())));
            data.setLastSaldoUnit5(BigDecimal.valueOf(Double.parseDouble(row[17].toString())));
            data.setCurSaldoUnit5(BigDecimal.valueOf(Double.parseDouble(row[18].toString())));
            //unit 6
            data.setSaldoUnit6(BigDecimal.valueOf(Double.parseDouble(row[19].toString())));
            data.setLastSaldoUnit6(BigDecimal.valueOf(Double.parseDouble(row[20].toString())));
            data.setCurSaldoUnit6(BigDecimal.valueOf(Double.parseDouble(row[21].toString())));
            //unit 7
            data.setSaldoUnit7(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            data.setLastSaldoUnit7(BigDecimal.valueOf(Double.parseDouble(row[23].toString())));
            data.setCurSaldoUnit7(BigDecimal.valueOf(Double.parseDouble(row[24].toString())));
            //unit All
            data.setSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[25].toString())));
            data.setLastSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[26].toString())));
            data.setCurSaldoUnitAll(BigDecimal.valueOf(Double.parseDouble(row[27].toString())));
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
                "  (a.jumlahdebitunit1+a.jumlahlastdebitunit1) - (a.jumlahkreditunit1+a.jumlahlastkreditunit1) as cursaldounit1,\n" +
                "  a.jumlahsaldo1tahunlalu,\n" +
                "  a.jumlahsaldo2tahunlalu\n" +
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
                "\t  CASE WHEN sa1.jumlah_kredit IS NULL THEN 0 ELSE sa1.jumlah_kredit END as jumlahlastkreditunit1,\n" +
                "\t  CASE WHEN sst1.jumlah_saldo IS NULL THEN 0 ELSE sst1.jumlah_saldo END as jumlahsaldo1tahunlalu,\n" +
                "\t  CASE WHEN sdt1.jumlah_saldo IS NULL THEN 0 ELSE sdt1.jumlah_saldo END as jumlahsaldo2tahunlalu\n" +
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
                "              INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.nomor_rekening \n" +
                "            where \n" +
                "              to_char(x.registered_date, 'MM-yyyy') = '"+periode+"' \n" +
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
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit-x.jumlah_kredit) as jumlah_saldo\n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 year'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sst1 ON sst1.rekening_id = a.rekening_id \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.rekening_id \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit-x.jumlah_kredit) as jumlah_saldo\n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.rekening_id = x.rekening_id \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '2 year'\n" +
                "              and x.branch_id = '"+branchId1+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sdt1 ON sdt1.rekening_id = a.rekening_id\n" +
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
            data.setSaldoUnit11TahunLalu(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setSaldoUnit12TahunLalu(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<AkunSettingReportKeuanganArusKasDetail> getArusKas(String unit,String periode){
        List<AkunSettingReportKeuanganArusKasDetail> listOfResult = new ArrayList<>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  a.namarekening, \n" +
                "  a.koderekening, \n" +
                "  a.aruskasid, \n" +
                "  a.operator, \n" +
                "  a.jumlahdebitunit1 - a.jumlahkreditunit1 as saldounit1 ,\n" +
                "  a.jumlahlastdebitunit1 - a.jumlahlastkreditunit1 as lastsaldounit1,\n" +
                "  (a.jumlahdebitunit1+a.jumlahlastdebitunit1) - (a.jumlahkreditunit1+a.jumlahlastkreditunit1) as cursaldounit1,\n" +
                "  a.jumlahsaldo1tahunlalu,\n" +
                "  a.jumlahsaldo2tahunlalu\n" +
                "FROM \n" +
                "  (\n" +
                "    SELECT \n" +
                "      distinct a.aruskasid, \n" +
                "      a.operator, \n" +
                "      a.nama_kode_rekening as namaRekening, \n" +
                "      a.kode_rekening as koderekening, \n" +
                "\t  CASE WHEN junit1.jumlah_debit IS NULL THEN 0 ELSE junit1.jumlah_debit END as jumlahdebitunit1, \n" +
                "\t  CASE WHEN junit1.jumlah_kredit IS NULL THEN 0 ELSE junit1.jumlah_kredit END as jumlahkreditunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_debit IS NULL THEN 0 ELSE sa1.jumlah_debit END as jumlahlastdebitunit1,\n" +
                "\t  CASE WHEN sa1.jumlah_kredit IS NULL THEN 0 ELSE sa1.jumlah_kredit END as jumlahlastkreditunit1,\n" +
                "\t  CASE WHEN sst1.jumlah_saldo IS NULL THEN 0 ELSE sst1.jumlah_saldo END as jumlahsaldo1tahunlalu,\n" +
                "\t  CASE WHEN sdt1.jumlah_saldo IS NULL THEN 0 ELSE sdt1.jumlah_saldo END as jumlahsaldo2tahunlalu\n" +
                "    FROM \n" +
                "      (\n" +
                "        select \n" +
                "          setting_report_arus_kas_id AS aruskasid, \n" +
                "          operator AS operator, \n" +
                "          kode.kode_rekening, \n" +
                "          kode.rekening_id, \n" +
                "          kode.nama_kode_rekening \n" +
                "        from \n" +
                "          im_akun_kode_rekening kode \n" +
                "          INNER JOIN im_akun_setting_report_arus_kas_detail rd ON rd.kode_rekening = kode.kode_rekening \n" +
                "        where \n" +
                "          rd.flag = 'Y' \n" +
                "        group by \n" +
                "          setting_report_arus_kas_id, \n" +
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
                "              INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = y.kode_rekening \n" +
                "            where \n" +
                "              to_char(x.registered_date, 'MM-yyyy') = '"+periode+"' \n" +
                "              and x.branch_id = '"+unit+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) junit1 ON junit1.kode_rekening = a.kode_rekening \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.kode_rekening \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit ) as jumlah_debit, \n" +
                "              sum(x.jumlah_kredit) as jumlah_kredit \n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 month'\n" +
                "              and x.branch_id = '"+unit+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sa1 ON sa1.kode_rekening = a.kode_rekening \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.kode_rekening \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit-x.jumlah_kredit) as jumlah_saldo\n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '1 year'\n" +
                "              and x.branch_id = '"+unit+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sst1 ON sst1.kode_rekening = a.kode_rekening \n" +
                "\t  LEFT OUTER JOIN (\n" +
                "        select \n" +
                "          jurnal.*, \n" +
                "          kr.kode_rekening \n" +
                "        from \n" +
                "          (\n" +
                "            select \n" +
                "              kr.kode_rekening AS kode_rekening, \n" +
                "              sum(x.jumlah_debit-x.jumlah_kredit) as jumlah_saldo\n" +
                "            from \n" +
                "              it_akun_saldo_akhir x\n" +
                "              INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = x.kode_rekening \n" +
                "            where \n" +
                "              to_date(x.periode, 'MM-yyyy') = to_date('"+periode+"', 'MM-yyyy') - Interval '2 year'\n" +
                "              and x.branch_id = '"+unit+"' \n" +
                "            group by \n" +
                "              kr.kode_rekening\n" +
                "          ) jurnal \n" +
                "          INNER JOIN im_akun_kode_rekening kr ON kr.kode_rekening = jurnal.kode_rekening\n" +
                "      ) sdt1 ON sdt1.kode_rekening = a.kode_rekening\n" +
                "    order by \n" +
                "      a.kode_rekening\n" +
                "  ) a \n" +
                "ORDER BY \n" +
                "  a.koderekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            AkunSettingReportKeuanganArusKasDetail data= new AkunSettingReportKeuanganArusKasDetail();
            data.setNamaRekening((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setSettingReportArusKasId((String) row[2]);
            data.setOperator((String) row[3]);
            data.setSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            data.setLastSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            data.setCurSaldoUnit1(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setSaldoUnit11TahunLalu(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setSaldoUnit12TahunLalu(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<BudgettingDTO> getBudgetting(String unit, String tahun,String status){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total,\n" +
                "\tbgtd.qty,\n" +
                "\tbgtd.nilai,\n" +
                "\tbgtd.sub_total\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<BudgettingDTO> getBudgettingPerSemester(String unit, String tahun,String status){
        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total,\n" +
                "\tbgtd.qty,\n" +
                "\tbgtd.nilai,\n" +
                "\tbgtd.sub_total,\n" +
                "\tbgtd.tipe\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening,tipe";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }else{
                data.setNilaiTotal(BigDecimal.ZERO);
            }
            if (row[5]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }else{
                data.setQty(BigDecimal.ZERO);
            }
            if (row[6]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }else{
                data.setNilai(BigDecimal.ZERO);
            }
            if (row[7]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }else{
                data.setSubTotal(BigDecimal.ZERO);
            }
            data.setTipe((String) row[8]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<BudgettingDTO> getBudgettingPerQuartal(String unit, String tahun,String status){
        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total,\n" +
                "\tbgtd.qty,\n" +
                "\tbgtd.nilai,\n" +
                "\tbgtd.sub_total,\n" +
                "\tbgtd.tipe\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening,tipe";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            data.setTipe((String) row[8]);

            //generate zero
            data.setSubTotalQ1(BigDecimal.ZERO);
            data.setSubTotalQ2(BigDecimal.ZERO);
            data.setSubTotalQ3(BigDecimal.ZERO);
            data.setSubTotalQ4(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<BudgettingDTO> getBudgettingPerBulan(String unit, String tahun,String status){
        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total,\n" +
                "\tbgtd.qty,\n" +
                "\tbgtd.nilai,\n" +
                "\tbgtd.sub_total,\n" +
                "\tbgtd.tipe\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\ttipe\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening,tipe";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            data.setTipe((String) row[8]);

            //generate zero value
            data.setSubTotalJanuari(BigDecimal.ZERO);
            data.setSubTotalFebruari(BigDecimal.ZERO);
            data.setSubTotalMaret(BigDecimal.ZERO);
            data.setSubTotalApril(BigDecimal.ZERO);
            data.setSubTotalMei(BigDecimal.ZERO);
            data.setSubTotalJuni(BigDecimal.ZERO);
            data.setSubTotalJuli(BigDecimal.ZERO);
            data.setSubTotalAgustus(BigDecimal.ZERO);
            data.setSubTotalSeptember(BigDecimal.ZERO);
            data.setSubTotalOktober(BigDecimal.ZERO);
            data.setSubTotalNovember(BigDecimal.ZERO);
            data.setSubTotalDesember(BigDecimal.ZERO);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<BudgettingDTO> getBudgettingComparingPerTahun(String unit, String status,String tahun,String tahunLalu,String tahunLalu2,String statusTahunLalu,String status2TahunLalu){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total,\n" +
                "\tbgtd.qty,\n" +
                "\tbgtd.nilai,\n" +
                "\tbgtd.sub_total\n," +
                "\tbgt.tahun \n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\t( branch_id='"+unit+"' AND tahun='"+tahun+"' AND status='"+status+"') OR\n" +
                "\t( branch_id='"+unit+"' AND tahun='"+tahunLalu+"' AND status='"+statusTahunLalu+"') OR\n" +
                "\t( branch_id='"+unit+"' AND tahun='"+tahunLalu2+"' AND status='"+status2TahunLalu+"')\n" +
                "ORDER BY\n" +
                "\ttahun DESC ,kode_rekening ASC";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setNilaiTotalTahunLalu(BigDecimal.ZERO);
            data.setNilaiTotal2TahunLalu(BigDecimal.ZERO);

            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }
            if (row[5]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[5].toString())));
            }
            if (row[6]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            }
            if (row[7]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            }
            data.setTahun((String) row[8]);
            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<BudgettingDTO> getBudgettingComparingRealisasi(String unit, String status,String tahun){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting,\n" +
                "\tbgt.nilai_total\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            if (row[4]!=null){
                data.setNilaiTotal(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }else{
                data.setNilaiTotal(BigDecimal.ZERO);
            }

            data.setNilaiTotalRealisasi(totalRealisasi(unit,tahun,data.getKodeRekening(),data.getNoBudgetting()));

            listOfResult.add(data);
        }
        return listOfResult;
    }



    public BigDecimal totalRealisasi(String branchId,String tahun,String kodeRekening,String noBudgetting){
        BigDecimal total = new BigDecimal(0);
        String query="SELECT\n" +
                "\tsum(jumlah_debit) as jumlah_debit\n" +
                "FROM\n" +
                "\tit_akun_jurnal j LEFT JOIN\n" +
                "\tit_akun_jurnal_detail jd ON j.no_jurnal=jd.no_jurnal LEFT JOIN\n" +
                "\tim_akun_kode_rekening kr ON jd.nomor_rekening=kr.kode_rekening\n" +
                "WHERE\n" +
                "\tkr.kode_rekening ilike '"+kodeRekening+"%'\n" +
                "\tAND j.sumber_dana_id ilike '"+noBudgetting+"%' \n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND jd.flag='Y'\n" +
                "\tAND j.flag='Y'\n" +
                "\tAND kr.flag='Y'\n" +
                "\tAND j.branch_id='"+branchId+"'\n" +
                "\tAND to_char(j.registered_date, 'yyyy') = '"+tahun+"'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public List<BudgettingDTO> getBudgettingMentah(String unit, String status,String tahun){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\trkg.nama_kode_rekening as grup,\n" +
                "\trk.kode_rekening,\n" +
                "\trk.nama_kode_rekening,\n" +
                "\tbgt.no_budgeting\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( select * from im_akun_kode_rekening where flag ='Y' ) rk ON bgt.rekening_id = rk.rekening_id \n" +
                "\tLEFT JOIN \n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\tavg(nilai) as nilai,\n" +
                "\t\t\tsum(qty) as qty,\n" +
                "\t\t\tsum(sub_total) as sub_total\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_akun_kode_rekening rkg ON rkg.kode_rekening = split_part(rk.kode_rekening, '.', 1)\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"'\n" +
                "ORDER BY\n" +
                "\tkode_rekening";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setGrup((String) row[0]);
            data.setKodeRekening((String) row[1]);
            data.setKodeRekeningName((String) row[2]);
            data.setNoBudgetting((String) row[3]);
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<BudgettingDTO> getDivisiDariBudgetting(String unit, String status,String tahun){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT \n" +
                "\tbgtd.divisi_id,\n" +
                "\tp.position_name\n" +
                "FROM\n" +
                "\tit_akun_budgeting bgt LEFT JOIN\n" +
                "\t( \n" +
                "\t\tSELECT \n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\tdivisi_id\n" +
                "\t\tFROM \n" +
                "\t\t\tit_akun_budgeting_detail \n" +
                "\t\tWHERE \n" +
                "\t\t\tflag='Y'\n" +
                "\t\tgroup by\n" +
                "\t\t\tno_budgeting,\n" +
                "\t\t\tdivisi_id\n" +
                "\t) bgtd ON bgt.no_budgeting = bgtd.no_budgeting\n" +
                "\tLEFT JOIN im_position p ON p.kodering = bgtd.divisi_id\n" +
                "WHERE\n" +
                "\tbranch_id='"+unit+"' AND\n" +
                "\ttahun='"+tahun+"' AND\n" +
                "\tstatus='"+status+"' AND\n" +
                "\tdivisi_id IS NOT NULL\n" +
                "ORDER BY\n" +
                "\tdivisi_id";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            data.setDivisiId((String) row[0]);
            data.setDivisi((String) row[1]);
            if (!data.getDivisiId().equalsIgnoreCase("INVS")){
                listOfResult.add(data);
            }
        }
        return listOfResult;
    }
    public List<BudgettingDTO> getBudgettingPerDivisi(String unit, String status,String tahun,String divisiId,String noBudgetting){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  sum(bgtd.qty) as qty, \n" +
                "  avg(bgtd.nilai) as nilai, \n" +
                "  sum(bgtd.sub_total) as sub_total \n" +
                "FROM \n" +
                "  it_akun_budgeting bgt \n" +
                "  LEFT JOIN (\n" +
                "    SELECT \n" +
                "      no_budgeting, \n" +
                "      avg(nilai) as nilai, \n" +
                "      sum(qty) as qty, \n" +
                "      sum(sub_total) as sub_total, \n" +
                "      divisi_id \n" +
                "    FROM \n" +
                "      it_akun_budgeting_detail \n" +
                "    WHERE \n" +
                "      flag = 'Y' \n" +
                "    group by \n" +
                "      no_budgeting, \n" +
                "      divisi_id\n" +
                "  ) bgtd ON bgt.no_budgeting = bgtd.no_budgeting \n" +
                "WHERE \n" +
                "  branch_id = '"+unit+"' \n" +
                "  AND tahun = '"+tahun+"' \n" +
                "  AND status = '"+status+"' \n" +
                "  AND bgtd.no_budgeting = '"+noBudgetting+"' \n" +
                "  AND bgtd.divisi_id = '"+divisiId+"'\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            if (row[0]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[0].toString())));
            }else{
                data.setQty(BigDecimal.ZERO);
            }
            if (row[1]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[1].toString())));
            }else{
                data.setNilai(BigDecimal.ZERO);
            }
            if (row[2]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setSubTotal(BigDecimal.ZERO);
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<BudgettingDTO> getBudgettingPerDivisi(String unit, String status,String tahun,String divisiId,String noBudgetting,String bulan,String tipe){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "";

        if (tipe.equalsIgnoreCase("S")){
            query = "SELECT \n" +
                    "  sum(bgtd.qty) as qty, \n" +
                    "  avg(bgtd.nilai) as nilai, \n" +
                    "  sum(bgtd.sub_total) as sub_total \n" +
                    "FROM \n" +
                    "  it_akun_budgeting bgt \n" +
                    "  LEFT JOIN (\n" +
                    "    SELECT \n" +
                    "      no_budgeting, \n" +
                    "      avg(nilai) as nilai, \n" +
                    "      sum(qty) as qty, \n" +
                    "      sum(sub_total) as sub_total, \n" +
                    "      divisi_id \n" +
                    "    FROM \n" +
                    "      it_akun_budgeting_detail \n" +
                    "    WHERE \n" +
                    "      flag = 'Y' AND \n" +
                    "      tipe ilike '"+bulan+"' \n" +
                    "    group by \n" +
                    "      no_budgeting, \n" +
                    "      divisi_id\n" +
                    "  ) bgtd ON bgt.no_budgeting = bgtd.no_budgeting \n" +
                    "WHERE \n" +
                    "  branch_id = '"+unit+"' \n" +
                    "  AND tahun = '"+tahun+"' \n" +
                    "  AND status = '"+status+"' \n" +
                    "  AND bgtd.no_budgeting = '"+noBudgetting+"' \n" +
                    "  AND bgtd.divisi_id = '"+divisiId+"'\n";
        }else{
            query = "SELECT \n" +
                    "  sum(bgtd.qty) as qty, \n" +
                    "  avg(bgtd.nilai) as nilai, \n" +
                    "  sum(bgtd.sub_total) as sub_total \n" +
                    "FROM \n" +
                    "  it_akun_budgeting bgt \n" +
                    "  LEFT JOIN (\n" +
                    "    SELECT \n" +
                    "      no_budgeting, \n" +
                    "      avg(nilai) as nilai, \n" +
                    "      sum(qty) as qty, \n" +
                    "      sum(sub_total) as sub_total, \n" +
                    "      divisi_id \n" +
                    "    FROM \n" +
                    "      it_akun_budgeting_detail \n" +
                    "    WHERE \n" +
                    "      flag = 'Y' AND \n" +
                    "      tipe in ("+bulan+") \n" +
                    "    group by \n" +
                    "      no_budgeting, \n" +
                    "      divisi_id\n" +
                    "  ) bgtd ON bgt.no_budgeting = bgtd.no_budgeting \n" +
                    "WHERE \n" +
                    "  branch_id = '"+unit+"' \n" +
                    "  AND tahun = '"+tahun+"' \n" +
                    "  AND status = '"+status+"' \n" +
                    "  AND bgtd.no_budgeting = '"+noBudgetting+"' \n" +
                    "  AND bgtd.divisi_id = '"+divisiId+"'\n";
        }

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            if (row[0]!=null){
                data.setQty(BigDecimal.valueOf(Double.parseDouble(row[0].toString())));
            }else{
                data.setQty(BigDecimal.ZERO);
            }
            if (row[1]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[1].toString())));
            }else{
                data.setNilai(BigDecimal.ZERO);
            }
            if (row[2]!=null){
                data.setSubTotal(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setSubTotal(BigDecimal.ZERO);
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }
    public List<Budgeting> getNoBudgetByDivisi(String unit, String status, String tahun, String divisiId){

        List<Budgeting> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  bgt.no_budgeting," +
                "  bgt.branch_id, \n" +
                "  kr.nama_kode_rekening \n" +
                "FROM \n" +
                "  it_akun_budgeting bgt \n" +
                "  LEFT JOIN (\n" +
                "    SELECT \n" +
                "      no_budgeting, \n" +
                "      avg(nilai) as nilai, \n" +
                "      sum(qty) as qty, \n" +
                "      sum(sub_total) as sub_total, \n" +
                "      divisi_id \n" +
                "    FROM \n" +
                "      it_akun_budgeting_detail \n" +
                "    WHERE \n" +
                "      flag = 'Y' \n" +
                "    group by \n" +
                "      no_budgeting, \n" +
                "      divisi_id\n" +
                "  ) bgtd ON bgt.no_budgeting = bgtd.no_budgeting \n" +
                " LEFT JOIN im_akun_kode_rekening kr ON kr.rekening_id = bgt.rekening_id \n"+
                "WHERE \n" +
                "  branch_id = '"+unit+"' \n" +
                "  AND tahun = '"+tahun+"' \n" +
                "  AND status = '"+status+"' \n" +
                "  AND bgtd.divisi_id = '"+divisiId+"' \n" +
                "  AND approve_flag = 'Y' \n" +
                "  AND nilai is not null\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Budgeting data= new Budgeting();
            data.setNoBudgeting((String) row[0]);
            data.setBranchId((String) row[1]);
            data.setNamaKodeRekening((String) row[2]);
            listOfResult.add(data);
        }

        return listOfResult;
    }

    public List<Budgeting> getInvestasiByDivisi(String unit, String status, String tahun, String divisiId){

        List<Budgeting> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  bgt.no_budgeting, \n" +
                "  bgt.branch_id,\n" +
                "  bgtd.nama_pengadaan\n" +
                "FROM \n" +
                "  it_akun_budgeting bgt \n" +
                "  LEFT JOIN (\n" +
                "    SELECT \n" +
                "      bd.no_budgeting, \n" +
                "      avg(bd.nilai) as nilai, \n" +
                "      sum(bd.qty) as qty, \n" +
                "      sum(bd.sub_total) as sub_total, \n" +
                "      bd.divisi_id,\n" +
                "\t  nama_pengadaan\n" +
                "    FROM \n" +
                "      it_akun_budgeting_detail bd\n" +
                "\t  LEFT JOIN it_akun_budgeting_pengadaan bp ON bp.id_budgeting_detail = bd.id_budgeting_detail\n" +
                "    WHERE \n" +
                "      bd.flag = 'Y' \n" +
                "    group by \n" +
                "      bd.no_budgeting, \n" +
                "      bd.divisi_id,\n" +
                "\t  nama_pengadaan\n" +
                "  ) bgtd ON bgt.no_budgeting = bgtd.no_budgeting \n" +
                "WHERE \n" +
                "  branch_id = '"+unit+"' \n" +
                "  AND tahun = '"+tahun+"' \n" +
                "  AND status = '"+status+"' \n" +
                "  AND bgtd.divisi_id = 'INVS' \n" +
                "  AND nilai is not null\n" +
                "\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Budgeting data= new Budgeting();
            data.setNoBudgeting((String) row[0]);
            data.setBranchId((String) row[1]);
            data.setNamaPengadaan((String) row[2]);
            listOfResult.add(data);
        }

        return listOfResult;
    }

    public List<BudgetingPengadaan> getInvestasiByNoBudgeting(String noBudgetting){

        List<BudgetingPengadaan> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tid_pengadaan,\n" +
                "\tnama_pengadaan," +
                "\tno_kontrak," +
                "\tnilai_kontrak,\n" +
                "\tnama_kontrak\n" +
                "FROM\n" +
                "\tit_akun_budgeting_detail bd LEFT JOIN\n" +
                "\tit_akun_budgeting_pengadaan bp ON bp.no_budgeting_detail = bd.no_budgeting_detail\n" +
                "WHERE\n" +
                "\tno_budgeting='"+noBudgetting+"' AND no_kontrak IS NOT NULL AND bp.pembayaran NOT ILIKE '%Lunas%'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgetingPengadaan data= new BudgetingPengadaan();
            data.setIdPengadaan((String) row[0]);
            data.setNamPengadaan((String) row[4]);
//            data.setNamPengadaan((String) row[1]);
//            data.setNamPengadaan((String) row[0]+" | "+(String) row[1]);
            data.setNoKontrak((String) row[2]);
            if (row[3]!=null){
                data.setNilaiKontrak(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }else{
                data.setNilaiKontrak(BigDecimal.ZERO);
            }
            listOfResult.add(data);
        }

        return listOfResult;
    }
    public List<BudgettingDTO> getBudgettingPengadaan(String idPengadaan){

        List<BudgettingDTO> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tno_kontrak,nilai_kontrak,nilai_adendum_1,nilai_adendum_2,nilai_adendum_3\n" +
                "from\n" +
                "\tit_akun_budgeting_pengadaan\n" +
                "where\n" +
                "\tid_pengadaan='"+idPengadaan+"'\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            BudgettingDTO data= new BudgettingDTO();
            if (row[1]!=null){
                data.setNilai(BigDecimal.valueOf(Double.parseDouble(row[1].toString())));
            }else{
                data.setNilai(BigDecimal.ZERO);
            }
            if (row[2]!=null){
                data.setNilaiAdendum1(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setNilaiAdendum1(BigDecimal.ZERO);
            }
            if (row[3]!=null){
                data.setNilaiAdendum2(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }else{
                data.setNilaiAdendum2(BigDecimal.ZERO);
            }
            if (row[4]!=null){
                data.setNilaiAdendum3(BigDecimal.valueOf(Double.parseDouble(row[4].toString())));
            }else{
                data.setNilaiAdendum3(BigDecimal.ZERO);
            }
            listOfResult.add(data);
        }
        return listOfResult;
    }

}
