package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.transaksi.payroll.model.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Timestamp;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PayrollDao extends GenericDao<ItPayrollEntity, String> {

    @Override
    protected Class<ItPayrollEntity> getEntityClass() {
        return ItPayrollEntity.class;
    }

    @Override
    public List<ItPayrollEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_id")!=null) {
                criteria.add(Restrictions.eq("payrollId", (String) mapCriteria.get("payroll_id")));
            }

            if (mapCriteria.get("bulan")!=null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollId"));

        List<ItPayrollEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPayrollId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = bulan + per[2] + per[3];

        return "PYR" + hasil + sId;
    }

    public List<ItPayrollEntity> getDataEdit(String branchId, String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT DISTINCT \n" +
                "  pegawai.nip,\n" +
                "  pegawai.nama_pegawai,\n" +
                "  posisi.branch_id,\n" +
                "  branch.branch_name,\n" +
                "  position.department_id,\n" +
                "  department.department_name,\n" +
                "  posisi.position_id,\n" +
                "  position.position_name,\n" +
                "  pegawai.golongan_id,\n" +
                "  golongan.grade_level,\n" +
                "  position.kelompok_id,\n" +
                "  pegawai.point,\n" +
                "  pegawai.status_keluarga,\n" +
                "  pegawai.jumlah_anak,\n" +
                "  branch.multifikator,\n" +
                "  pegawai.zakat_profesi,\n" +
                "  pegawai.jenis_kelamin,\n" +
                "  pegawai.dana_pensiun, \n" +
                "  pegawai.tanggal_aktif, \n" +
                "  pegawai.tanggal_pensiun, \n" +
                "  pegawai.tipe_pegawai, \n" +
                "  pegawai.struktur_gaji, \n" +
                "  pegawai.gaji, \n" +
                "  tipePegawai.tipe_pegawai_name, \n" +
                "  position.kelompok_id,\n" +
                "  pegawai.status_giling,\n" +
                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
                "  posisi.pjs_flag,\n" +
                "  pegawai.npwp,\n" +
                "  pegawai.status_pegawai, \n" +
                "  pegawai.golongan_dapen, \n" +
                "  pegawai.golongan_dapen_nusindo,  \n" +
                "  pegawai.poin_lebih," +
                "  branch.umr, \n" +
                "  pegawai.golongan_dapen_id, \n"+
                "  pegawai.masa_kerja_gol, \n"+
                "  pegawai.tgl_akhir_kontrak, \n"+
                "  posisi.profesi_id,\n" +
                "  pegawai.gaji\n"+
                "   FROM im_hris_pegawai pegawai\n" +
                "LEFT JOIN it_hris_pegawai_position posisi\n" +
                "  ON posisi.nip = pegawai.nip\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = posisi.branch_id\n" +
                "LEFT JOIN im_position position\n" +
                "  ON position.position_id = posisi.position_id\n" +
                "LEFT JOIN im_hris_department department\n" +
                "  ON department.department_id = position.department_id\n" +
                "LEFT JOIN im_hris_golongan golongan\n" +
                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "WHERE pegawai.flag = 'Y'\n" +
                "AND posisi.flag = 'Y'\n" +
                "AND posisi.branch_id = '"+branchId+"'\n" +
                strWhere+"\n" +
                "ORDER BY position.kelompok_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setBranchName((String) row[3]);
            result.setDepartmentId((String) row[4]);
            result.setDepartmentName((String) row[5]);
            result.setPositionId((String) row[6]);
            result.setPositionName((String) row[7]);
            result.setGolonganId((String) row[8]);
            Integer level = (Integer) row[9];
            result.setGolonganName(String.valueOf(level));
            result.setKelompokId((String) row[10]);
            result.setPoint(Integer.parseInt(row[11].toString()));
            result.setStatusKeluarga((String) row[12]);
            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
            if (row[14]!=null){
                result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
            }
            result.setFlagZakat((String) row[15]);
            result.setGender((String) row[16]);
            result.setDanaPensiun((String) row[17]);
            result.setTanggalAktif((Date) row[18]);
            result.setTanggalPensiun((Date) row[19]);
            result.setTipePegawai((String) row[20]);
            result.setStrukturGaji((String) row[21]);
            if (row[22]!=null){
                result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            }
            result.setTipePegawaiName((String) row[23]);
            result.setKelompokId((String) row[24]);
            result.setStatusGiling((String) row[25]);
            result.setDanaPensiunName((String) row[26]);
            result.setFlagPjs((String) row[27]);
            result.setNpwp((String) row[28]);
            result.setStatusPegawai((String) row[29]);
            result.setGolonganDapen((String) row[30]);
            result.setGolonganDapenNusindo((String) row[31]);
            result.setPointLebih(Integer.parseInt(row[32].toString()));
            if (row[33]!=null){
                result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[33].toString())));
            }
            result.setGolonganDapenId((String) row[34]);
            result.setMasaKerjaGol((Integer)row[35]);
            if (row[36]!=null){
                result.setTanggalAkhirKontrak((Date)row[36]);
            }
            if (row[37]!=null){
                result.setProfesiId((String) row[37]);
            }
            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[38].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataEditRapel(String branchId, String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.nama_pegawai,\n" +
                "  posisi.branch_id,\n" +
                "  branch.branch_name,\n" +
                "  position.department_id,\n" +
                "  department.department_name,\n" +
                "  posisi.position_id,\n" +
                "  position.position_name,\n" +
                "  pegawai.golongan_id,\n" +
                "  golongan.golongan_name,\n" +
                "  position.kelompok_id,\n" +
                "  pegawai.point,\n" +
                "  pegawai.status_keluarga,\n" +
                "  pegawai.jumlah_anak,\n" +
                "  branch.multifikator,\n" +
                "  pegawai.zakat_profesi,\n" +
                "  pegawai.jenis_kelamin,\n" +
                "  pegawai.dana_pensiun, \n" +
                "  pegawai.tanggal_aktif, \n" +
                "  pegawai.tanggal_pensiun, \n" +
                "  pegawai.tipe_pegawai, \n" +
                "  pegawai.struktur_gaji, \n" +
                "  pegawai.gaji, \n" +
                "  tipePegawai.tipe_pegawai_name, \n" +
                "  position.kelompok_id,\n" +
                "  pegawai.status_giling,\n" +
                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
                "  posisi.pjs_flag,\n" +
                "  pegawai.npwp,\n" +
                "  pegawai.status_pegawai, \n" +
                "  pegawai.golongan_dapen, \n" +
                "  pegawai.golongan_dapen_nusindo,  \n" +
                "  pegawai.poin_lebih," +
                "  branch.umr \n" +
                "   FROM im_hris_pegawai pegawai\n" +
                "LEFT JOIN it_hris_pegawai_position posisi\n" +
                "  ON posisi.nip = pegawai.nip\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = posisi.branch_id\n" +
                "LEFT JOIN im_position position\n" +
                "  ON position.position_id = posisi.position_id\n" +
                "LEFT JOIN im_hris_department department\n" +
                "  ON department.department_id = position.department_id\n" +
                "LEFT JOIN im_hris_golongan golongan\n" +
                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "WHERE posisi.flag = 'Y'\n" +
                "AND posisi.branch_id = '"+branchId+"'\n" +
                strWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setBranchName((String) row[3]);
            result.setDepartmentId((String) row[4]);
            result.setDepartmentName((String) row[5]);
            result.setPositionId((String) row[6]);
            result.setPositionName((String) row[7]);
            result.setGolonganId((String) row[8]);
            result.setGolonganName((String) row[9]);
            result.setKelompokId((String) row[10]);
            result.setPoint(Integer.parseInt(row[11].toString()));
            result.setStatusKeluarga((String) row[12]);
            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
            result.setFlagZakat((String) row[15]);
            result.setGender((String) row[16]);
            result.setDanaPensiun((String) row[17]);
            result.setTanggalAktif((Date) row[18]);
            result.setTanggalPensiun((Date) row[19]);
            result.setTipePegawai((String) row[20]);
            result.setStrukturGaji((String) row[21]);
            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            result.setTipePegawaiName((String) row[23]);
            result.setKelompokId((String) row[24]);
            result.setStatusGiling((String) row[25]);
            result.setDanaPensiunName((String) row[26]);
            result.setFlagPjs((String) row[27]);
            result.setNpwp((String) row[28]);
            result.setStatusPegawai((String) row[29]);
            result.setGolonganDapen((String) row[30]);
            result.setGolonganDapenNusindo((String) row[31]);
            result.setPointLebih(Integer.parseInt(row[32].toString()));
            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[33].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataAnggotaJasprod(String branchId, String periode){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.nama_pegawai,\n" +
                "  smk.branch_id,\n" +
                "  branch.branch_name,\n" +
                "  position.department_id,\n" +
                "  department.department_name,\n" +
                "  smk.position_id,\n" +
                "  position.position_name,\n" +
                "  historyJabatan.golongan_id,\n" +
                "  golongan.golongan_name,\n" +
                "  position.kelompok_id,\n" +
                "  historyJabatan.point,\n" +
                "  pegawai.status_keluarga,\n" +
                "  pegawai.jumlah_anak,\n" +
                "  branch.multifikator,\n" +
                "  pegawai.zakat_profesi,\n" +
                "  pegawai.jenis_kelamin,\n" +
                "  pegawai.dana_pensiun, \n" +
                "  pegawai.tanggal_aktif, \n" +
                "  pegawai.tanggal_pensiun, \n" +
                "  pegawai.tipe_pegawai, \n" +
                "  pegawai.struktur_gaji, \n" +
                "  pegawai.gaji, \n" +
                "  tipePegawai.tipe_pegawai_name, \n" +
                "  position.kelompok_id,\n" +
                "  pegawai.status_giling,\n" +
                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
                "  pegawai.npwp,\n" +
                "  pegawai.status_pegawai, \n" +
                "  pegawai.golongan_dapen, \n" +
                "  pegawai.golongan_dapen_nusindo,  \n" +
                "  pegawai.poin_lebih,  \n" +
                "  branch.umr,\n" +
                "  historyJabatan.poin_lebih\n" +
                "from\n" +
                "  it_hris_smk_evaluasi_pegawai smk \n" +
                "LEFT JOIN im_hris_pegawai pegawai \n" +
                "  ON pegawai.nip = smk.nip\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = smk.branch_id\n" +
                "LEFT JOIN im_position position\n" +
                "  ON position.position_id = smk.position_id\n" +
                "LEFT JOIN imt_hris_history_smk_golongan historyJabatan\n" +
                "  ON historyJabatan.nip = smk.nip and historyJabatan.branch_id = smk.branch_id \n" +
                "LEFT JOIN im_hris_department department\n" +
                "  ON department.department_id = position.department_id\n" +
                "LEFT JOIN im_hris_golongan golongan\n" +
                "  ON golongan.golongan_id = historyJabatan.golongan_id\n" +
                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "WHERE \n" +
                "smk.flag = 'Y'\n" +
                "AND smk.branch_id = '"+branchId+"'\n" +
                "and smk.periode = '"+periode+"'\n" +
                "and historyJabatan.tahun = '"+periode+"'\n" +
                "and pegawai.nip is not null \n" +
                "order by \n" +
                "pegawai.nama_pegawai";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setBranchName((String) row[3]);
            result.setDepartmentId((String) row[4]);
            result.setDepartmentName((String) row[5]);
            result.setPositionId((String) row[6]);
            result.setPositionName((String) row[7]);
            result.setGolonganId((String) row[8]);
            result.setGolonganName((String) row[9]);
            result.setKelompokId((String) row[10]);
            result.setPoint(Integer.parseInt(row[11].toString()));
            result.setStatusKeluarga((String) row[12]);
            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
            result.setFlagZakat((String) row[15]);
            result.setGender((String) row[16]);
            result.setDanaPensiun((String) row[17]);
            result.setTanggalAktif((Date) row[18]);
            result.setTanggalPensiun((Date) row[19]);
            result.setTipePegawai((String) row[20]);
            result.setStrukturGaji((String) row[21]);
            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            result.setTipePegawaiName((String) row[23]);
            result.setKelompokId((String) row[24]);
            result.setStatusGiling((String) row[25]);
            result.setDanaPensiunName((String) row[26]);
            //result.setFlagPjs((String) row[27]);
            result.setNpwp((String) row[27]);
            result.setStatusPegawai((String) row[28]);
            result.setGolonganDapen((String) row[29]);
            result.setGolonganDapenNusindo((String) row[30]);
            result.setPointLebih(Integer.parseInt(row[31].toString()));
            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[32].toString())));
            result.setPointLebih(Integer.parseInt(row[33].toString()));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataSearchHome(String branchId, String bulan1, String tahun1, String bulan2, String tahun2, String tipe){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "and payroll.flag_payroll = 'Y' ";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "and payroll.flag_thr = 'Y' ";
        }else if(tipe.equalsIgnoreCase("CP")){
            tipeWhere = "and payroll.flag_cuti_panjang = 'Y' ";
        }else if(tipe.equalsIgnoreCase("CT")){
            tipeWhere = "and payroll.flag_cuti_tahunan= 'Y' ";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "and payroll.flag_jasprod = 'Y' ";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "and payroll.flag_jubileum = 'Y' ";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "and payroll.flag_pensiun= 'Y' ";
        }else if(tipe.equalsIgnoreCase("IN")){
            tipeWhere = "and payroll.flag_insentif= 'Y' ";
        }

        String query = "SELECT\n" +
                "  payroll.bulan,\n" +
                "  payroll.tahun,\n" +
                "  COUNT(payroll.nip),\n" +
                "  branch.branch_name,\n" +
                "  SUM(payroll.gaji_bersih) as gaji_bersih,\n" +
                "  SUM(payroll.total_a) as gaji_kotor,\n" +
                "  payroll.approval_flag,\n" +
                "  payroll.approval_unit_flag,\n" +
                "  payroll.approval_date,\n" +
                "  payroll.branch_id,\n" +
                "  payroll.flag_payroll,\n" +
                "  payroll.flag_thr,\n" +
                "  payroll.flag_cuti_tahunan,\n" +
                "  payroll.flag_cuti_panjang,\n" +
                "  payroll.flag_jasprod,\n" +
                "  payroll.flag_jubileum,\n" +
                "  payroll.flag_pensiun,\n" +
                "  payroll.flag_insentif\n" +
                "FROM it_hris_payroll payroll\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = payroll.branch_id\n" +
                "WHERE payroll.bulan >= '"+bulan1+"'\n" +
                "AND payroll.bulan <= '"+bulan2+"'\n" +
                "AND payroll.tahun >= '"+tahun1+"'\n" +
                "AND payroll.tahun <= '"+tahun2+"'\n" +
                "AND payroll.flag = 'Y'\n" +
                "AND payroll.branch_id = '"+branchId+"'\n" +
                tipeWhere +
                "GROUP BY payroll.bulan,\n" +
                "         payroll.tahun,\n" +
                "         branch.branch_name,\n" +
                "         payroll.approval_flag,\n" +
                "         payroll.approval_unit_flag,\n" +
                "         payroll.approval_date," +
                "         payroll.branch_id,\n" +
                "         payroll.flag_payroll,\n" +
                "         payroll.flag_thr,\n" +
                "         payroll.flag_cuti_tahunan,\n" +
                "         payroll.flag_cuti_panjang,\n" +
                "         payroll.flag_jasprod,\n" +
                "         payroll.flag_jubileum,\n" +
                "         payroll.flag_pensiun,\n" +
                "         payroll.flag_insentif\n" +
                "order by approval_date desc";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setBulan((String) row[0]);
            result.setTahun((String) row[1]);
            result.setJumlahPegawai(Integer.valueOf(row[2].toString()));
            result.setBranchName((String) row[3]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setApprovalFlag((String) row[6]);
            result.setApprovalUnitFlag((String) row[7]);
            result.setApprovalDate((java.sql.Timestamp) row[8]);
            result.setBranchId((String) row[9]);
            result.setFlagPayroll((String) row[10]);
            result.setFlagThr((String) row[11]);
            result.setFlagCutiTahunan((String) row[12]);
            result.setFlagCutiPanjang((String) row[13]);
            result.setFlagJasprod((String) row[14]);
            result.setFlagJubileum((String) row[15]);
            result.setFlagPensiun((String) row[16]);
            result.setFlagInsentif((String) row[17]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // retrieve data insentif dari database
    public List<ItPayrollInsentifEntity> getDataInsentif(String branchId){
        List<ItPayrollInsentifEntity> listOfResult = new ArrayList<ItPayrollInsentifEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tinsentif.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tbranch.branch_name,\n" +
                "\tsum(insentif.jumlah_insentif) as nilai_insentif,\n" +
                "\tsum(insentif.jumlah_pph) as nilai_pph\n" +
                "from\n" +
                "\tit_hris_payroll_insentif insentif\n" +
                "\tleft join it_hris_pegawai_position pegawaiPosition on pegawaiPosition.nip = insentif.nip\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = insentif.nip\n" +
                "\tleft join im_branches branch on branch.branch_id = pegawaiPosition.branch_id\n" +
                "where\n" +
                "\tpegawaiPosition.branch_id = '"+branchId+"'\n" +
                "group by\n" +
                "\tinsentif.nip,\n" +
                "\tpegawai.nama_pegawai,\n" +
                "\tbranch.branch_name";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollInsentifEntity result  = new ItPayrollInsentifEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setBranchName((String) row[2]);
            result.setJumlahInsentif(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[4].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataViewAndEdit(String bulan, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.*,\n" +
                "\tpph.*,\n" +
                "\tpegawai.*\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id \n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip and pegawai.flag = 'Y'\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"' \n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\t";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setNip((String) row[77]);
            result.setNama((String) row[78]);
            result.setTanggalAktif((Date) row[99]);
            result.setPositionId((String) row[5]);
            result.setPositionName((String) row[6]);
            result.setGolonganId((String) row[7]);
            result.setGolonganName((String) row[8]);
            result.setDepartmentId((String) row[9]);
            result.setDepartmentName((String) row[10]);
            result.setBranchId((String) row[11]);
            result.setBranchName((String) row[12]);
            result.setPoint(Integer.parseInt(row[13].toString()));
            result.setStatusKeluarga((String) row[14]);
            result.setJumlahAnak(Integer.valueOf(row[15].toString()));
            result.setMultifikator((String) row[19]);


            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setBranchName((String) row[3]);
            result.setApprovalFlag((String) row[5]);
            result.setApprovalDate((java.sql.Timestamp) row[6]);
            result.setBranchId((String) row[7]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> cekPensiun(String nip){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpensiun.pensiun_id,\n" +
                "\tpensiun.created_date,\n" +
                "\tpensiun.netto_pensiun\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tinner join it_hris_payroll_pensiun pensiun on pensiun.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.nip = '"+nip+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand pensiun.flag = 'Y'\n" +
                "\nand payroll.approval_flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setApprovalDate((java.sql.Timestamp) row[4]);
            result.setNettoPensiun(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> cekJubileum(String nip){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tjubileum.jubileum_id,\n" +
                "\tjubileum.created_date\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tinner join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.nip = '"+nip+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand jubileum.flag = 'Y'\n" +
                "\tand payroll.approval_flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setTanggalAktif(CommonUtil.convertTimestampToDate((java.sql.Timestamp) row[4]));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> cekTotalPphTahun(String nip, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tcase\n" +
                "\t\twhen sum(pph_gaji) is null then 0 else sum(pph_gaji) end as pphTahun  \n" +
                "from\n" +
                "\tit_hris_payroll\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand tahun = '"+tahun+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPphTahun(BigDecimal.valueOf(Double.valueOf(row.toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> cekTotalBrutoTahun(String nip, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tcase\n" +
                "\t\twhen sum(pph.bruto / 12) is null then 0 else sum(pph.bruto / 12) end\n" +
                "\tas hasil\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand tahun = '"+tahun+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPphTahun(BigDecimal.valueOf(Double.valueOf(row.toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    // mengambil data history lembur (filter msh static)
    public List<ItPayrollEntity> cekHistoryLembur(String nip, String tanggal1, String tanggal2){
        tanggal1 = "01-"+tanggal1;
        tanggal2 = "01-"+tanggal2;
        Date dTanggal1 = CommonUtil.convertStringToDate(tanggal1);
        Date dTanggal2 = CommonUtil.convertStringToDate(tanggal2);
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tabsensi.absensi_pegawai_id,\n" +
                "\tabsensi.nip,\n" +
                "\tabsensi.tanggal,\n" +
                "\tabsensi.jenis_lembur,\n" +
                "\tabsensi.lama_lembur,\n" +
                "\tabsensi.jam_lembur,\n" +
                "\tabsensi.biaya_lembur,\n" +
                "\tpayroll.gaji_golongan,\n" +
                "\tpayroll.tunjangan_umk,\n" +
                "\tpayroll.tunjangan_peralihan,\n" +
                "\tpegawai.tipe_pegawai,\n" +
                "\tfaktorLembur.faktor\n" +
                "\t\n" +
                "from\n" +
                "\tit_hris_absensi_pegawai absensi\n" +
                "\tleft join it_hris_payroll payroll on payroll.nip = absensi.nip and payroll.flag_payroll = 'Y' \n" +
                "\tand cast(NULLIF(payroll.bulan, '') AS Integer) = EXTRACT(Month FROM absensi.tanggal)\n" +
                "\tand cast(NULLIF(payroll.tahun, '') AS Integer) = EXTRACT(Year FROM absensi.tanggal)\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = absensi.nip \n" +
                "\tleft join im_hris_pengali_faktor_lembur faktorLembur on faktorLembur.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "where\n" +
                "\tabsensi.lembur = 'Y'\n" +
                "\tand absensi.nip ='"+nip+"'\n" +
                "\tand absensi.tanggal >= '"+dTanggal1+"'\n" +
                "\tand absensi.tanggal <= '"+dTanggal2+"'\n" +
                "order by\n" +
                "\tabsensi.tanggal\n" +
                "\t";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setAbsensiId((String) row[0]);
            result.setNip((String) row[1]);
            result.setTanggal((Date) row[2]);
            result.setJamLembur(Double.valueOf(row[5].toString()));
            result.setBiayaLemburLama(BigDecimal.valueOf(Double.valueOf(row[6].toString())));

            if(row[7] != null){
                result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            }else{
                result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(0)));
            }

            if(row[8] != null){
                result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            }else{
                result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(0)));
            }

            if(row[9] != null){
                result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            }else{
                result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(0)));
            }

            result.setTipePegawai((String) row[10]);
            result.setFaktorKali(BigDecimal.valueOf(Double.valueOf(row[11].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> cekApprove(String branchId, String bulan, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\t*\n" +
                "from\n" +
                "\tit_hris_payroll\n" +
                "where\n" +
                "\tbulan = '"+bulan+"'\n" +
                "\tand tahun = '"+tahun+"'\n" +
                "\tand approval_flag is null\n" +
                "\tand branch_id = '"+branchId+"' limit 1";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getLastPayroll(String nip, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollEntity> getPakaianDinasByNipAndTahun(String nip, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.ge("tunjanganBajuDinas",0))
                .list();

        return results;
    }

    public List<ItPayrollEntity> getLastPayroll(String nip, String bulan, String tahun, String branchId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .list();

        return results;
    }
    public List<ItPayrollEntity> getTunjanganPeralihan(String nip, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollEntity> getLastPayroll(String nip) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public List<ItPayrollEntity> getAllPayroll(String nip) throws HibernateException {
//        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("flag", "Y"))
//                .add(Restrictions.eq("flagPayroll", "Y"))
//                .add(Restrictions.eq("approvalFlag", "Y"))
//                .addOrder(Order.desc("createdDate"))
//                .list();

        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewMobile(String nip, String branchId, String bulan, String tahun, String payrollId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
//                .add(Restrictions.eq("nip", nip))
//                .add(Restrictions.eq("branchId", branchId))
//                .add(Restrictions.eq("bulan", bulan))
//                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("payrollId", payrollId))
                .list();
        return results;
    }

    /*public List<ItPayrollEntity> getDataPayrollBulan(String bulan1, String tahun1, String bulan2, String tahun2, String unit) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("branchId", unit))
                .add(Restrictions.gt("bulan", bulan1))
                .add(Restrictions.le("bulan", bulan2))
                .add(Restrictions.gt("tahun", tahun1))
                .add(Restrictions.le("tahun", tahun2))
                .list();
        return results;
    }*/

    public List<ItPayrollEntity> getDataView(String branchId, String bulan, String tahun, String tipe) throws HibernateException {
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "flagPayroll";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "flagThr";
        }else if(tipe.equalsIgnoreCase("CT")){
            tipeWhere = "flagCutiTahunan";
        }else if(tipe.equalsIgnoreCase("CP")){
            tipeWhere = "flagCutiPanjang";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "flagJasprod";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "flagJubileum";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "flagPensiun";
        }else if(tipe.equalsIgnoreCase("IN")){
            tipeWhere = "flagInsentif";
        }
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq(tipeWhere, "Y"))
                .createCriteria("imPosition")
                .addOrder(Order.asc("kelompokId"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewPensiun(String branchId, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagPensiun", "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewJubileum(String branchId, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagJubileum", "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewTanpaBulan(String nip, String branchId, String tahun, String tipe) throws HibernateException {
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "flagPayroll";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "flagThr";
        }else if(tipe.equalsIgnoreCase("PD")){
            tipeWhere = "flagPendidikan";
        }else if(tipe.equalsIgnoreCase("R")){
            tipeWhere = "flagRapel";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "flagJasprod";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "flagJubileum";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "flagPensiun";
        }else if(tipe.equalsIgnoreCase("IN")){
            tipeWhere = "flagInsentif";
        }

        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq(tipeWhere, "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewBefore(String branchId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataView(String nip, String branchId, String bulan, String tahun, String tipe) throws HibernateException {
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "flagPayroll";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "flagThr";
        }else if(tipe.equalsIgnoreCase("PD")){
            tipeWhere = "flagPendidikan";
        }else if(tipe.equalsIgnoreCase("R")){
            tipeWhere = "flagRapel";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "flagJasprod";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "flagJubileum";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "flagPensiun";
        }
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq(tipeWhere, "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewPensiun(String nip, String branchId, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagPensiun", "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataViewJubileum(String nip, String branchId, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagJubileum", "Y"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataRapel(String nip, String branchId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flagRapel", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .setMaxResults(1)
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getFirstPayroll(String nip, String branchId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("branchId", branchId))
                .addOrder(Order.asc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }

    // cek berapa bulan, rapel
    public List<ItPayrollEntity> getBulanRapel(String nip, String tahun, String[] bulan) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.in("bulan", bulan))
                //.add(Restrictions.ne("bulan", bulan))
                .addOrder(Order.asc("bulan"))
                .list();
        return results;
    }

    // cek rapel selain bulan
    public List<ItPayrollEntity> getBulanRapelLain(String nip, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagPayroll", "N"))
                .add(Restrictions.eq("flagJasprod", "N"))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .addOrder(Order.asc("bulan"))
                .list();
        return results;
    }

    // Get history gaji perbulan
    public List<ItPayrollEntity> getPayrollByNipAndBulan(String nip, String bulan, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flagPayroll", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .addOrder(Order.asc("bulan"))
                .list();
        return results;
    }

    public void approvePayroll(String branchId, String bulan, String tahun, String statusApprove, String tipe){
        String id = CommonUtil.userIdLogin();
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "and flag_payroll = 'Y' ";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "and flag_thr = 'Y' ";
        }else if(tipe.equalsIgnoreCase("PD")){
            tipeWhere = "and flag_pendidikan = 'Y' ";
        }else if(tipe.equalsIgnoreCase("R")){
            tipeWhere = "and flag_rapel= 'Y' ";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "and flag_jasprod = 'Y' ";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "and flag_jubileum = 'Y' ";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "and flag_pensiun= 'Y' ";
        }

        String query = "UPDATE it_hris_payroll\n" +
                "SET approval_id = '"+id+"',\n" +
                "    approval_date = now(),\n" +
                "    approval_flag = '"+statusApprove+"'\n" +
                "WHERE bulan = '"+bulan+"'\n" +
                "AND tahun = '"+tahun+"'\n" +
                tipeWhere+
                "AND branch_id = '"+branchId+"'\n" +
                "AND flag = 'Y'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .executeUpdate();
    }

    public void approvePayrollUnit(String branchId, String bulan, String tahun, String statusApprove, String tipe){
        String id = CommonUtil.userIdLogin();
        String tipeWhere = "";
        if(tipe.equalsIgnoreCase("PR")){
            tipeWhere = "and flag_payroll = 'Y' ";
        }else if(tipe.equalsIgnoreCase("T")){
            tipeWhere = "and flag_thr = 'Y' ";
        }else if(tipe.equalsIgnoreCase("PD")){
            tipeWhere = "and flag_pendidikan = 'Y' ";
        }else if(tipe.equalsIgnoreCase("R")){
            tipeWhere = "and flag_rapel= 'Y' ";
        }else if(tipe.equalsIgnoreCase("JP")){
            tipeWhere = "and flag_jasprod = 'Y' ";
        }else if(tipe.equalsIgnoreCase("JB")){
            tipeWhere = "and flag_jubileum = 'Y' ";
        }else if(tipe.equalsIgnoreCase("PN")){
            tipeWhere = "and flag_pensiun= 'Y' ";
        }

        String query = "UPDATE it_hris_payroll\n" +
                "SET approval_unit_id = '"+id+"',\n" +
                "    approval_unit_date = now(),\n" +
                "    approval_unit_flag = '"+statusApprove+"'\n" +
                "WHERE bulan = '"+bulan+"'\n" +
                "AND tahun = '"+tahun+"'\n" +
                tipeWhere+
                "AND branch_id = '"+branchId+"'\n" +
                "AND flag = 'Y'";

        this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .executeUpdate();
    }

    public List<ItPayrollEntity> getListBayarUpah(String nip, String bulanAwal, String bulanAkhir, String tahunAwal, String tahunAkhir){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_Id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.position_name,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tupahHarian.tanggal,\n" +
                "\tpegawai.gaji,\n" +
                "\tCASE WHEN payroll.approval_flag IS NULL THEN 'B' ELSE payroll.approval_flag END" +
                "\n" +
                "from \n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_upah_harian upahHarian on upahHarian.payroll_id = payroll.payroll_id and upahHarian.flag = 'Y'\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.nip = '"+nip+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "and upahHarian is not null" +
                "\t";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setTanggal((Date) row[6]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setApprovalFlag((String) row[8]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollBulan(String bulan1, String tahun1, String unit, String statusPegawai){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.*,\n" +
                "\tpegawai.point,\n " +
                "\tpegawai.jenis_kelamin " +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[1]);
            result.setGolonganName((String) row[8]);
            if (row[91]!=null){
                result.setPoint((Integer) row[91]);
                result.setGender((String) row[92]);
            }else{
                result.setPoint((Integer) row[91]);
                result.setGender((String) row[92]);
            }
            result.setNip((String) row[3]);
            result.setNama((String) row[4]);
            result.setStatusKeluarga((String) row[14]);
            result.setJumlahAnak((Integer) row[15]);
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[35].toString())));

            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[72].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[76].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollPotonganDinas(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.*,\n" +
                "\tpegawai.point\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_payroll = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[1]);
            result.setGolonganName((String) row[8]);
            result.setPoint((Integer) row[91]);
            result.setNip((String) row[3]);
            result.setNama((String) row[4]);
            result.setStatusKeluarga((String) row[14]);
            result.setJumlahAnak((Integer) row[15]);

            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[41].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[40].toString())));
            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[42].toString())));
            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[43].toString())));

            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[77].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollPotonganLainLain(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.*,\n" +
                "\tpegawai.point\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_payroll = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[1]);
            result.setNip((String) row[3]);
            result.setNama((String) row[4]);

            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[44].toString())));
            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[45].toString())));
            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[46].toString())));
            result.setSP(BigDecimal.valueOf(Double.valueOf(row[47].toString())));
            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[48].toString())));
            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[49].toString())));
            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[51].toString())));
            result.setZakat(BigDecimal.valueOf(Double.valueOf(row[50].toString())));
            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[78].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollPenghasilanKaryawan(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.*,\n" +
                "\tpegawai.point\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_payroll = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[1]);
            result.setGolonganName((String) row[8]);
            result.setPoint((Integer) row[87]);
            result.setNip((String) row[3]);
            result.setNama((String) row[4]);
            result.setStatusKeluarga((String) row[14]);
            result.setJumlahAnak((Integer) row[15]);

            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[72].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[76].toString())));
            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[77].toString())));
            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[78].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollPendidikan(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpendidikan.*,\n" +
                "\tpegawai.point,\n" +
                "\tpayroll.pph_gaji,\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_pendidikan pendidikan on pendidikan.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_pendidikan = 'Y'\n" +
                "\tand pendidikan.pendidikan_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setBulan((Integer) row[23] + "");
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setPoint((Integer) row[26]);
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[28].toString())));

            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<PayrollRapel> getDataPayrollRapel(String bulan1, String tahun1, String unit, String status){
        List<PayrollRapel> listOfResult = new ArrayList<PayrollRapel>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\trapel.*,\n" +
                "\tpegawai.point\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand pegawai.status_pegawai = '"+status+"' " +
                "\tand rapel.flag = 'Y'\n "+
                "\tand payroll.flag_rapel = 'Y' ";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapel result  = new PayrollRapel();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);


            if(row[6] != null){
                result.setJumlahBulan((Integer) row[33]);
                result.setTunjanganStrukturalLamaNilai(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
                result.setTunjanganJabatanStrukturalLamaNilai(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
                result.setTunjanganStrategisLamaNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
                result.setTunjanganAirListrikLamaNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
                result.setTunjanganUmkLamaNilai(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
                result.setGajiGolonganLamaNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
                result.setTunjanganPerumahanLamaNilai(BigDecimal.valueOf(Double.valueOf(row[21].toString())));

                result.setGajiGolonganBaruNilai(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
                result.setTunjanganStrukturalBaruNilai(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
                result.setTunjanganUmkBaruNilai(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
                result.setTunjanganJabatanStrukturalBaruNilai(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
                result.setTunjanganStrategisBaruNilai(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
                result.setTunjanganAirListrikBaruNilai(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
                result.setTunjanganPerumahanBaruNilai(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
                result.setGolonganIdLama((String) row[29]);
                result.setPointLama((String) row[30]);
                result.setGolonganIdBaru((String) row[31]);
                result.setPointBaru((String) row[32]);

                result.setGajiGolonganNilai(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
                result.setTunjanganUmkNilai(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
                result.setTunjanganStrukturalNilai(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
                result.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
                result.setTunjanganAirListrikNilai(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
                result.setTunjanganPerumahanNilai(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
                result.setTunjanganStrategisNilai(BigDecimal.valueOf(Double.valueOf(row[40].toString())));

                result.setTotalRapelInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[41].toString())));
                result.setTotalRapelThrNilai(BigDecimal.valueOf(Double.valueOf(row[42].toString())));
                result.setTotalRapelPendidikanNilai(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[44].toString())));

                result.setTotalRapelBulanNilai(BigDecimal.valueOf(Double.valueOf(row[45].toString())));
                result.setTotalRapelFinalNilai(BigDecimal.valueOf(Double.valueOf(row[46].toString())));
                result.setTotalRapelLemburNilai(BigDecimal.valueOf(Double.valueOf(row[47].toString())));

                result.setPphRapelNilai(BigDecimal.valueOf(Double.valueOf(row[50].toString())));
                result.setPphRapelPribadiNilai(BigDecimal.valueOf(Double.valueOf(row[51].toString())));
                result.setTunjanganPphNilai(BigDecimal.valueOf(Double.valueOf(row[52].toString())));
                result.setRapelBersihNilai(BigDecimal.valueOf(Double.valueOf(row[53].toString())));

                result.setPoint((Integer) row[54]);

                result.setTotalRapelNilai(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            }else{
                result.setTunjanganStrukturalLamaNilai(BigDecimal.valueOf(0));
                result.setTunjanganJabatanStrukturalLamaNilai(BigDecimal.valueOf(0));
                result.setTunjanganStrategisLamaNilai(BigDecimal.valueOf(0));
                result.setTunjanganAirListrikLamaNilai(BigDecimal.valueOf(0));
                result.setTunjanganUmkLamaNilai(BigDecimal.valueOf(0));
                result.setGajiGolonganLamaNilai(BigDecimal.valueOf(0));
                result.setTunjanganPerumahanLamaNilai(BigDecimal.valueOf(0));

                result.setGajiGolonganBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganStrukturalBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganUmkBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganJabatanStrukturalBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganStrategisBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganAirListrikBaruNilai(BigDecimal.valueOf(0));
                result.setTunjanganPerumahanBaruNilai(BigDecimal.valueOf(0));

                result.setGajiGolonganNilai(BigDecimal.valueOf(0));
                result.setTunjanganUmkNilai(BigDecimal.valueOf(0));
                result.setTunjanganStrukturalNilai(BigDecimal.valueOf(0));
                result.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(0));
                result.setTunjanganAirListrikNilai(BigDecimal.valueOf(0));
                result.setTunjanganPerumahanNilai(BigDecimal.valueOf(0));
                result.setTunjanganStrategisNilai(BigDecimal.valueOf(0));

                result.setTotalRapelInsentifNilai(BigDecimal.valueOf(0));
                result.setTotalRapelThrNilai(BigDecimal.valueOf(0));
                result.setTotalRapelPendidikanNilai(BigDecimal.valueOf(0));
                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(0));
                result.setTotalRapelLemburNilai(BigDecimal.valueOf(0));

                result.setTotalRapelBulanNilai(BigDecimal.valueOf(0));
                result.setTotalRapelFinalNilai(BigDecimal.valueOf(0));

                result.setPphRapelNilai(BigDecimal.valueOf(Double.valueOf(0)));
                result.setPphRapelPribadiNilai(BigDecimal.valueOf(Double.valueOf(0)));
                result.setTunjanganPphNilai(BigDecimal.valueOf(Double.valueOf(0)));
                result.setRapelBersihNilai(BigDecimal.valueOf(Double.valueOf(0)));
                //result.setPoint((Integer) row[47]);

                result.setTotalRapelNilai(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Rapel THR
    public List<PayrollRapelThr> getDataPayrollRapelThr(String bulan1, String tahun1, String unit, String status){
        List<PayrollRapelThr> listOfResult = new ArrayList<PayrollRapelThr>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\trapelThr.payroll_thr_id,\n" +
                "\trapelThr.gaji_golongan_baru,\n" +
                "\trapelThr.tunjangan_umk_baru,\n" +
                "\trapelThr.tunjangan_peralihan_baru,\n" +
                "\trapelThr.tunjangan_struktural_baru,\n" +
                "\trapelThr.tunjangan_strategis as tunjangan_strategis_baru,\n" +
                "\trapelThr.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
                "\trapelThr.selisih_gaji_golongan,\n" +
                "\trapelThr.selisih_tunjangan_umk,\n" +
                "\trapelThr.selisih_tunjangan_peralihan,\n" +
                "\trapelThr.selisih_tunjangan_struktural,\n" +
                "\trapelThr.selisih_tunjangan_jabatan_struktural,\n" +
                "\trapelThr.selisih_tunjangan_strategis,\n" +
                "\trapelThr.total_selisih_thr,\n" +
                "\tthr.*,\n" +
                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
                "\tpayrollLama.point as point_baru\n" +
                "\t\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel_thr rapelThr on rapelThr.payroll_rapel_id = rapel.rapel_id\n" +
                "\tleft join it_hris_payroll_thr thr on thr.thr_id = rapelThr.payroll_thr_id\n" +
                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = thr.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_rapel = 'Y'\n" +
                "\tand pegawai.status_pegawai = '"+status+"'\n" +
                "\tand rapelThr.payroll_thr_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapelThr result  = new PayrollRapelThr();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);

            result.setRapelId((String) row[7]);
            result.setPayrollThrId((String) row[8]);

            result.setGolonganNameLama((String) row[40]);
            result.setPointLama((Integer) row[41]);

            if(row[8] != null){
                result.setThrGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
                result.setThrUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
                result.setThrPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
                result.setThrStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
                result.setThrStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
                result.setThrJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));

                result.setThrGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
                result.setThrUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
                result.setThrPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
                result.setThrStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
                result.setThrJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
                result.setThrStrategisNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[21].toString())));

                result.setThrPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
                result.setThrUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
                result.setThrStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
                result.setThrJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
                result.setThrStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
                result.setThrGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
            }else{
                result.setThrGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
                result.setThrUmkNilaiBaru(BigDecimal.valueOf(0));
                result.setThrPeralihanNilaiBaru(BigDecimal.valueOf(0));
                result.setThrStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setThrStrategisNilaiBaru(BigDecimal.valueOf(0));
                result.setThrJabStrukturalNilaiBaru(BigDecimal.valueOf(0));

                result.setThrGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setThrUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setThrPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setThrStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setThrJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setThrStrategisNilaiSelisihBaru(BigDecimal.valueOf(0));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));

                result.setThrUmkNilaiLama(BigDecimal.valueOf(0));
                result.setThrPeralihanNilaiLama(BigDecimal.valueOf(0));
                result.setThrStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setThrJabStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setThrStrategisNilaiLama(BigDecimal.valueOf(0));
                result.setThrGajiGolonganNilaiLama(BigDecimal.valueOf(0));
            }


            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Rapel Jubileum
    public List<PayrollRapelJubileum> getDataPayrollRapelJubileum(String bulan1, String tahun1, String unit){
        List<PayrollRapelJubileum> listOfResult = new ArrayList<PayrollRapelJubileum>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tposisi.position_name,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\trapelJubileum.payroll_jubileum_id,\n" +
                "\trapelJubileum.gaji_golongan_baru,\n" +
                "\trapelJubileum.tunjangan_umk_baru,\n" +
                "\trapelJubileum.tunjangan_struktural_baru,\n" +
                "\trapelJubileum.tunjangan_peralihan_baru,\n" +
                "\trapelJubileum.tunjangan_jabatan_struktural_baru,\n" +
                "\trapelJubileum.selisih_gaji_golongan,\n" +
                "\trapelJubileum.selisih_tunjangan_umk,\n" +
                "\trapelJubileum.selisih_tunjangan_struktural,\n" +
                "\trapelJubileum.selisih_tunjangan_jabatan_struktural,\n" +
                "\trapelJubileum.selisih_tunjangan_peralihan,\n" +
                "\trapelJubileum.total_selisih_jubileum,\n" +
                "\trapelJubileum.total_rapel_jubileum,\n" +
                "\tjubileum.gaji_golongan as gaji_golongan_lama,\n" +
                "\tjubileum.tunjangan_umk as tunjangan_umk_lama,\n" +
                "\tjubileum.tunjangan_struktural as tunjangan_struktural_lama,\n" +
                "\tjubileum.tunjangan_peralihan as tunjangan_peralihan_lama,\n" +
                "\tjubileum.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_lama,\n" +
                "\tjubileum.total_jubileum as total_jubileum_lama,\n" +
                "\tjubileum.grand_total as total_rapel_jubileum_lama,\n" +
                "\tjubileum.tanggal_jubileum,\n" +
                "\tgolonganBaru.golongan_name as golongan_nama_baru,\n" +
                "\tpayroll.point as point_baru,\n" +
                "\tgolonganLama.golongan_name as golongan_nama_lama,\n" +
                "\tpayrollLama.point as point_lama\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel_jubileum rapelJubileum on rapelJubileum.payroll_rapel_id = rapel.rapel_id\n" +
                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.jubileum_id = rapelJubileum.payroll_jubileum_id\n" +
                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
                "\tleft join im_hris_golongan golonganBaru on golonganBaru.golongan_id = payroll.golongan_id\n" +
                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = jubileum.payroll_id\n" +
                "\tleft join im_hris_golongan golonganLama on golonganLama.golongan_id = payrollLama.golongan_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_rapel = 'Y'\n" +
                "\tand rapelJubileum.payroll_jubileum_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapelJubileum result  = new PayrollRapelJubileum();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setPositionName((String) row[2]);
            result.setNama((String) row[3]);
            result.setGolonganName((String) row[4]);
            result.setStatusKeluarga((String) row[5]);
            result.setJumlahAnak((Integer) row[6]);
            result.setPoint((Integer) row[7]);

            result.setRapelId((String) row[8]);
            result.setPayrollJubileumId((String) row[9]);

            if(row[9] != null){
                result.setJubileumGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
                result.setJubileumUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
                result.setJubileumStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
                result.setJubileumPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
                result.setJubileumJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));

                result.setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
                result.setJubileumUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
                result.setJubileumStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
                result.setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
                result.setJubileumPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[21].toString())));

                result.setJubileumGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
                result.setJubileumUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
                result.setJubileumStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
                result.setJubileumPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
                result.setJubileumJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[26].toString())));

                result.setTotalRapelNilaiSelisihLama(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
                result.setTotalRapelJubileumLamaNilai(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
                result.setTanggalJubileum((Date)row[29]);

                result.setGolonganName((String)row[30]);
                result.setPoint((Integer) row[31]);

                result.setGolonganNameLama((String)row[32]);
                result.setPointLama((Integer) row[33]);
            }else{
                result.setJubileumGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
                result.setJubileumUmkNilaiBaru(BigDecimal.valueOf(0));
                result.setJubileumStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setJubileumPeralihanNilaiBaru(BigDecimal.valueOf(0));
                result.setJubileumJabStrukturalNilaiBaru(BigDecimal.valueOf(0));

                result.setJubileumGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setJubileumUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setJubileumStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setJubileumJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setJubileumPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setTotalRapelJubileumNilai(BigDecimal.valueOf(0));

                result.setJubileumGajiGolonganNilaiLama(BigDecimal.valueOf(0));
                result.setJubileumUmkNilaiLama(BigDecimal.valueOf(0));
                result.setJubileumStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setJubileumPeralihanNilaiLama(BigDecimal.valueOf(0));
                result.setJubileumJabStrukturalNilaiLama(BigDecimal.valueOf(0));

                result.setTotalRapelNilaiSelisihLama(BigDecimal.valueOf(0));
                result.setTotalRapelJubileumLamaNilai(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Rapel Pendidikan
    public List<PayrollRapelPendidikan> getDataPayrollRapelPendidikan(String bulan1, String tahun1, String unit, String status){
        List<PayrollRapelPendidikan> listOfResult = new ArrayList<PayrollRapelPendidikan>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\trapelPendidikan.payroll_pendidikan_id,\n" +
                "\trapelPendidikan.gaji_golongan_baru,\n" +
                "\trapelPendidikan.tunjangan_umk_baru,\n" +
                "\trapelPendidikan.tunjangan_peralihan_baru,\n" +
                "\trapelPendidikan.tunjangan_struktural_baru,\n" +
                "\trapelPendidikan.tunjangan_strategis as tunjangan_strategis_baru,\n" +
                "\trapelPendidikan.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
                "\trapelPendidikan.tunjangan_air_listrik as tunjangan_air_listrik_baru,\n" +
                "\trapelPendidikan.selisih_gaji_dasar,\n" +
                "\trapelPendidikan.selisih_tunjangan_umk,\n" +
                "\trapelPendidikan.selisih_tunjangan_peralihan,\n" +
                "\trapelPendidikan.selisih_tunjangan_struktural,\n" +
                "\trapelPendidikan.selisih_tunjangan_jabatan_struktural,\n" +
                "\trapelPendidikan.selisih_tunjangan_strategis,\n" +
                "\trapelPendidikan.selisih_tunjangan_air_listrik,\n" +
                "\trapelPendidikan.total_selisih_pendidikan,\n" +
                "\tpendidikan.*,\n" +
                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
                "\tpayrollLama.point as point_baru\n" +
                "\t\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel_pendidikan rapelPendidikan on rapelPendidikan.payroll_rapel_id = rapel.rapel_id\n" +
                "\tleft join it_hris_payroll_pendidikan pendidikan on pendidikan.pendidikan_id = rapelPendidikan.payroll_pendidikan_id\n" +
                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = pendidikan.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_rapel = 'Y'\n" +
                "\tand pegawai.status_pegawai = '"+status+"'\n" +
                "\tand rapelPendidikan.payroll_pendidikan_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapelPendidikan result  = new PayrollRapelPendidikan();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);
            result.setGolonganNameLama((String) row[44]);
            result.setPointLama((Integer) row[45]);

            result.setRapelId((String) row[7]);
            result.setPayrollPendidikanId((String) row[8]);

            if(row[8] != null){
                result.setPendidikanGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
                result.setPendidikanUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
                result.setPendidikanPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
                result.setPendidikanStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
                result.setPendidikanStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
                result.setPendidikanJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
                if(row[15] != null){
                    result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
                }else{
                    result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(0));
                }

                result.setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
                result.setPendidikanUmkNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
                result.setPendidikanPeralihanNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
                result.setPendidikanStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
                result.setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
                result.setPendidikanStrategisNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
                result.setPendidikanAirListrikNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[22].toString())));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(Double.valueOf(row[23].toString())));

                result.setPendidikanUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
                result.setPendidikanPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[43].toString())));
                result.setPendidikanStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
                result.setPendidikanJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
                result.setPendidikanStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
                result.setPendidikanAirListrikNilaiLama(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
                result.setPendidikanGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[39].toString())));
            }else{
                result.setPendidikanGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanUmkNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanPeralihanNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanStrategisNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setPendidikanAirListrikNilaiBaru(BigDecimal.valueOf(0));

                result.setPendidikanGajiGolonganNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanUmkNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanPeralihanNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanJabStrukturalNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanStrategisNilaiSelisihBaru(BigDecimal.valueOf(0));
                result.setPendidikanAirListrikNilaiSelisihBaru(BigDecimal.valueOf(0));

                result.setTotalRapelNilaiSelisihBaru(BigDecimal.valueOf(0));

                result.setPendidikanPeralihanNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanUmkNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanJabStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanStrategisNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanGajiGolonganNilaiLama(BigDecimal.valueOf(0));
                result.setPendidikanAirListrikNilaiLama(BigDecimal.valueOf(0));
            }
            listOfResult.add(result);
        }
        return listOfResult;
    }

    // Rapel Insentif
    public List<PayrollRapelInsentif> getDataPayrollRapelInsentif(String bulan1, String tahun1, String unit, String status){
        List<PayrollRapelInsentif> listOfResult = new ArrayList<PayrollRapelInsentif>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\trapelinsentif.nip,\n" +
                "\trapelinsentif.payroll_insentif_id,\n" +
                "\trapelinsentif.gaji_golongan_baru,\n" +
                "\trapelinsentif.tunjangan_umk_baru,\n" +
                "\trapelinsentif.tunjangan_struktural_baru,\n" +
                "\trapelinsentif.tunjangan_strategis_baru,\n" +
                "\trapelinsentif.tunjangan_peralihan_baru,\n" +
                "\trapelinsentif.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural_baru,\n" +
                "\trapelinsentif.bruto_baru,\n" +
                "\trapelinsentif.potongan_insentif,\n" +
                "\trapelinsentif.potongan_insentif_individu_baru,\n" +
                "\trapelinsentif.masa_kerja,\n" +
                "\trapelinsentif.smk_standart,\n" +
                "\trapelinsentif.smk_huruf,\n" +
                "\trapelinsentif.smk_angka,\n" +
                "\trapelinsentif.jumlah_insentif_baru,\n" +
                "\trapelinsentif.jumlah_insentif_lama,\n" +
                "\trapelinsentif.total_rapel_insentif,\n" +
                "\tinsentif.gaji_golongan,\n" +
                "\tinsentif.tunjangan_umk,\n" +
                "\tinsentif.tunjangan_struktural,\n" +
                "\tinsentif.tunjangan_peralihan,\n" +
                "\tinsentif.tunjangan_jab_struktural,\n" +
                "\tinsentif.tunjangan_strategis,\n" +
                "\tinsentif.jumlah_bruto,\n" +
                "\tinsentif.potongan_insentif_individu,\n" +
                "\tpayrollLama.golongan_name as golongan_name_lama,\n" +
                "\tpayrollLama.point as point_baru\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel_insentif rapelinsentif on rapelinsentif.payroll_rapel_id = rapel.rapel_id\n" +
                "\tleft join it_hris_payroll_insentif insentif on insentif.insentif_id = rapelinsentif.payroll_insentif_id\n" +
                "\tleft join it_hris_payroll payrollLama on payrollLama.payroll_id = insentif.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_rapel = 'Y'\n" +
                "\tand pegawai.status_pegawai = '"+status+"'\n" +
                "\tand rapelInsentif.payroll_insentif_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapelInsentif result  = new PayrollRapelInsentif();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);

            result.setGolonganNameLama((String) row[34]);
            result.setPointLama((Integer) row[35]);

            result.setRapelId((String) row[7]);
            result.setNip((String) row[8]);
            result.setPayrollInsentifId((String) row[9]);

            if(row[9] != null){
                result.setInsentifGajiGolonganNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
                result.setInsentifUmkNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
                result.setInsentifStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
                result.setInsentifStrategisNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
                result.setInsentifPeralihanNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
                result.setInsentifJabStrukturalNilaiBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
                result.setBrutoInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
                result.setPotonganInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
                result.setPotonganInsentifIndividuNilai(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
                result.setMasaKerja((Integer) row[19]);
                result.setSmkStandart(row[20].toString());
                result.setSmkStandartNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
                result.setSmkHuruf((String) row[21]);
                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
                result.setInsentifYangDiterimaBaruNilai(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
                result.setInsentifYangDiterimaLamaNilai(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
                result.setInsentifYangDiterimaSelisihNilai(BigDecimal.valueOf(Double.valueOf(row[25].toString())));

                result.setInsentifGajiGolonganNilaiLama(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
                result.setInsentifUmkNilaiLama(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
                result.setInsentifStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
                result.setInsentifPeralihanNilaiLama(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
                result.setInsentifJabStrukturalNilaiLama(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
                result.setInsentifStrategisNilaiLama(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
                result.setBrutoInsentifLamaNilai(BigDecimal.valueOf(Double.valueOf(row[32].toString())));
                result.setPotonganInsentifIndividuLamaNilai(BigDecimal.valueOf(Double.valueOf(row[33].toString())));
            }else{
                result.setInsentifGajiGolonganNilaiBaru(BigDecimal.valueOf(0));
                result.setInsentifUmkNilaiBaru(BigDecimal.valueOf(0));
                result.setInsentifStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setInsentifStrategisNilaiBaru(BigDecimal.valueOf(0));
                result.setInsentifPeralihanNilaiBaru(BigDecimal.valueOf(0));
                result.setInsentifJabStrukturalNilaiBaru(BigDecimal.valueOf(0));
                result.setBrutoInsentifNilai(BigDecimal.valueOf(0));
                result.setPotonganInsentifNilai(BigDecimal.valueOf(0));
                result.setPotonganInsentifIndividuNilai(BigDecimal.valueOf(0));
                result.setMasaKerja(0);
                result.setSmkStandart("0");
                result.setSmkHuruf("");
                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(0)));
                result.setInsentifYangDiterimaBaruNilai(BigDecimal.valueOf(0));
                result.setInsentifYangDiterimaLamaNilai(BigDecimal.valueOf(0));
                result.setInsentifYangDiterimaSelisihNilai(BigDecimal.valueOf(0));

                result.setInsentifGajiGolonganNilaiLama(BigDecimal.valueOf(0));
                result.setInsentifUmkNilaiLama(BigDecimal.valueOf(0));
                result.setInsentifStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setInsentifPeralihanNilaiLama(BigDecimal.valueOf(0));
                result.setInsentifJabStrukturalNilaiLama(BigDecimal.valueOf(0));
                result.setInsentifStrategisNilaiLama(BigDecimal.valueOf(0));
                result.setBrutoInsentifLamaNilai(BigDecimal.valueOf(0));
                result.setPotonganInsentifIndividuLamaNilai(BigDecimal.valueOf(0));
                result.setSmkAngkaNilai(BigDecimal.valueOf(Double.valueOf(0)));
                result.setSmkStandartNilai(BigDecimal.valueOf(Double.valueOf(0)));
            }
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<PayrollRapelLembur> getDataPayrollRapelLembur(String bulan1, String tahun1, String unit, String status){
        List<PayrollRapelLembur> listOfResult = new ArrayList<PayrollRapelLembur>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\tsum(rapelLembur.jam_lembur) as jam_lembur,\n" +
                "\tsum(rapelLembur.biaya_lembur_lama) as biaya_lembur_lama,\n" +
                "\trapelLembur.gaji_golongan_baru,\n" +
                "\trapelLembur.tunjangan_umk_baru,\n" +
                "\trapelLembur.tunjangan_peralihan_baru,\n" +
                "\trapelLembur.faktor_pengali,\n" +
                "\tsum(rapelLembur.biaya_lembur_baru) as biaya_lembur_baru,\n" +
                "\tsum(rapelLembur.selisih_biaya_lembur_baru) as selisih_biaya_lembur_baru\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel_lembur rapelLembur on rapelLembur.rapel_id = rapel.rapel_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_rapel = 'Y' \n" +
                "\tand pegawai.status_pegawai = '"+status+"'\n" +
                "group by\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\trapel.rapel_id,\n" +
                "\trapelLembur.gaji_golongan_baru,\n" +
                "\trapelLembur.tunjangan_umk_baru,\n" +
                "\trapelLembur.tunjangan_peralihan_baru,\n" +
                "\trapelLembur.faktor_pengali";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollRapelLembur result  = new PayrollRapelLembur();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);
            result.setRapelId((String) row[7]);

            if(row[8] != null){
                result.setJamLembur(Double.valueOf(row[8].toString()));
                result.setBiayaLemburLama(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
                result.setGajiGolonganBaru(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
                result.setTunjanganUmkBaru(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
                result.setTunjanganPeralihanBaru(BigDecimal.valueOf(Double.valueOf(row[12].toString())));

                result.setFaktorPengali(Double.valueOf(row[13].toString()));
                result.setBiayaLemburBaru(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
                result.setSelisihBiayaLemburBaru(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            }else{
                result.setJamLembur(Double.valueOf(0));
                result.setBiayaLemburLama(BigDecimal.valueOf(0));
                result.setGajiGolonganBaru(BigDecimal.valueOf(0));
                result.setTunjanganUmkBaru(BigDecimal.valueOf(0));
                result.setTunjanganPeralihanBaru(BigDecimal.valueOf(0));

                result.setFaktorPengali(Double.valueOf(0));
                result.setBiayaLemburBaru(BigDecimal.valueOf(0));
                result.setSelisihBiayaLemburBaru(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollThr(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tthr.*,\n" +
                "\tpegawai.point,\n" +
                "\tpayroll.pph_gaji,\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_thr thr on thr.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_thr = 'Y'\n" +
                "\tand thr.thr_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setBulan((Integer) row[21] + "");
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
            result.setPoint((Integer) row[24]);
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[26].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollJasprod(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\tpayroll.pph_gaji,\n" +
                "\tpayroll.gaji_bersih,\n" +
                "\tjasprod.*\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_jasprod jasprod on jasprod.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_jasprod = 'Y'\n" +
                "\tand jasprod.jasprod_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setNilaiSmk(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setMasaKerja((Integer) row[23]);
            result.setGajiMasaKerja(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setFaktorKali(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setPersenSmk(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
            result.setGajiMasaKerjaFaktorSmk(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
            result.setGajiMasaKerjaFaktor(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
            result.setTambahan(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
            result.setBrutoJasprod(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
            result.setSelisihTotalGajiSmkFaktor(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
            result.setJumlahPersenSmk(Double.valueOf(row[32].toString()));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[33].toString())));

            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[35].toString())));
            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[36].toString())));
            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[37].toString())));
            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[38].toString())));
            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[39].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataPayrollInsentif(String bulan1, String tahun1, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpegawai.point,\n" +
                "\tpayroll.pph_gaji,\n" +
                "\tpayroll.gaji_bersih,\n" +
                "\tinsentif.*\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join it_hris_payroll_insentif insentif on insentif.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand payroll.bulan = '"+bulan1+"'\n" +
                "\tand payroll.tahun = '"+tahun1+"'\n" +
                "\tand payroll.flag_insentif = 'Y'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand insentif.insentif_id is not null";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setPayrollId((String) row[0]);
            result.setNip((String) row[1]);
            result.setNama((String) row[2]);
            result.setGolonganName((String) row[3]);
            result.setStatusKeluarga((String) row[4]);
            result.setJumlahAnak((Integer) row[5]);
            result.setPoint((Integer) row[6]);
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[8].toString())));

            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setMasaKerja((Integer) row[26]);
            result.setPotonganInsentif(Double.valueOf(row[27].toString()));
            result.setPotonganInsentifIndividu(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
            result.setSmkStandart(Double.valueOf(row[29].toString()));
            result.setSmkHuruf((String) row[30]);
            result.setSmkAngka(Double.valueOf(row[31].toString()));
            result.setTahun((String) row[32]);
            result.setInsentifDiterima(BigDecimal.valueOf(Double.valueOf(row[34].toString())));
            result.setPotPphLain(BigDecimal.valueOf(Double.valueOf(row[35].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportDanaPensiunSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.point,\n" +
                "\tdapen.dana_pensiun,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpayroll.gaji_pensiun as phdp,\n" +
                "\tpayroll.iuran_pensiun,\n" +
                "\tCASE \n" +
                "\t\tWHEN dapen.dana_pensiun_id = 'DP01' THEN payroll.gaji_pensiun * 12 / 100\n" +
                "\t\tWHEN dapen.dana_pensiun_id = 'DP02' THEN payroll.gaji_pensiun * 16.09 / 100\n" +
                "\t\tWHEN dapen.dana_pensiun_id = 'DP03' THEN payroll.gaji_pensiun * 13.13 / 100\n" +
                "\tELSE \n" +
                "\t\t0 \n" +
                "\tEND as iuran_perusahaan\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.dana_pensiun is not null\n" +
                "\tand pegawai.dana_pensiun != ''\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand flag_payroll = 'Y'\n" +
                "order by\n" +
                "\tpegawai.dana_pensiun";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            BigDecimal jumlahIuranPensiun = new BigDecimal(0);
            jumlahIuranPensiun = BigDecimal.valueOf(Double.valueOf(row[8].toString())).add(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setGolonganName((String) row[2]);
            result.setPoint((Integer) row[3]);
            result.setDanaPensiunName((String) row[4]);
            result.setStatusKeluarga((String) row[5]);
            result.setJumlahAnak((Integer) row[6]);
            result.setGajiPensiunBpjs(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setIuranPensiunPerusahaan(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setIuranPensiunJumlah(jumlahIuranPensiun);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportBpjsSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tpayroll.golongan_name,\n" +
                "\tpayroll.point,\n" +
                "\tdapen.dana_pensiun,\n" +
                "\tpayroll.status_keluarga,\n" +
                "\tpayroll.jumlah_anak,\n" +
                "\tpayroll.gaji_pensiun_bpjs as gaji_bpjs,\n" +
                "\tpayroll.iuran_bpjs_tk,\n" +
                "\tpayroll.iuran_bpjs_pensiun,\n" +
                "\tpayroll.iuran_bpjs_kesehatan,\n" +
                "\tpayroll.iuran_bpjs_tk + payroll.iuran_bpjs_pensiun + payroll.iuran_bpjs_kesehatan as jumlah\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand flag_payroll = 'Y'\n" +
                "order by\n" +
                "\tpegawai.dana_pensiun";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            BigDecimal jumlahIuranPensiun = new BigDecimal(0);
            jumlahIuranPensiun = BigDecimal.valueOf(Double.valueOf(row[8].toString())).add(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setGolonganName((String) row[2]);
            result.setPoint((Integer) row[3]);
            result.setDanaPensiunName((String) row[4]);
            result.setStatusKeluarga((String) row[5]);
            result.setJumlahAnak((Integer) row[6]);
            result.setGajiPensiunBpjs(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setIuranPensiunJumlah(BigDecimal.valueOf(Double.valueOf(row[11].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportRekapGajiSys(String bulan, String tahun, String unit, String statusPegawai,
                                                          String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (posisiPegawai.nip) as jumlah_orang,\n" +
                "\tsum(payroll.gaji_golongan) as gaji_dasar,\n" +
                "\tsum(payroll.tunjangan_umk) as tunjangan_umk,\n" +
                "\tsum(payroll.tunjangan_struktural) as tunjangan_struktural,\n" +
                "\tsum(payroll.tunjangan_peralihan) as tunjangan_peralihan,\n" +
                "\tsum(payroll.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
                "\tsum(payroll.tunjangan_strategis) as tunjangan_strategis,\n" +
                "\tsum(payroll.kompensasi) as tunjangan_kompensasi,\n" +
                "\tsum(payroll.tunjangan_transport) as tunjangan_transport,\n" +
                "\tsum(payroll.tunjangan_air_listrik) as tunjangan_air_listrik,\n" +
                "\tsum(payroll.tunjangan_pengobatan) as tunjangan_pengobatan,\n" +
                "\tsum(payroll.tunjangan_perumahan) as tunjangan_perumahan,\n" +
                "\tsum(payroll.tunjangan_pph) as tunjangan_pph,\n" +
                "\tsum(payroll.tunjangan_lembur) as tunjangan_lembur,\n" +
                "\tsum(payroll.tunjangan_lain) as tunjangan_lain,\n" +
                "\tsum(payroll.total_a) as penghasilan_kotor\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.nip = posisiPegawai.nip\n" +
                "where\n" +
                "\tposisiPegawai.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\tand posisiPegawai.flag ='Y'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand " + strWhere +
                "\ngroup by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "\torder by \n posisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[17].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    //update posisi mengambil di tabel payroll, bukan tabel it+_pegawai_position
    public List<ItPayrollEntity> searchReportRekapGajiSysV2(String bulan, String tahun, String unit, String statusPegawai,
                                                          String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (payroll.nip) as jumlah_orang,\n" +
                "\tsum(payroll.gaji_golongan) as gaji_dasar,\n" +
                "\tsum(payroll.tunjangan_umk) as tunjangan_umk,\n" +
                "\tsum(payroll.tunjangan_struktural) as tunjangan_struktural,\n" +
                "\tsum(payroll.tunjangan_peralihan) as tunjangan_peralihan,\n" +
                "\tsum(payroll.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
                "\tsum(payroll.tunjangan_strategis) as tunjangan_strategis,\n" +
                "\tsum(payroll.kompensasi) as tunjangan_kompensasi,\n" +
                "\tsum(payroll.tunjangan_transport) as tunjangan_transport,\n" +
                "\tsum(payroll.tunjangan_air_listrik) as tunjangan_air_listrik,\n" +
                "\tsum(payroll.tunjangan_pengobatan) as tunjangan_pengobatan,\n" +
                "\tsum(payroll.tunjangan_perumahan) as tunjangan_perumahan,\n" +
                "\tsum(payroll.tunjangan_pph) as tunjangan_pph,\n" +
                "\tsum(payroll.tunjangan_lembur) as tunjangan_lembur,\n" +
                "\tsum(payroll.tunjangan_lain) as tunjangan_lain,\n" +
                "\tsum(payroll.total_a) as penghasilan_kotor\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\tand payroll.flag_payroll ='Y'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand " + strWhere +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "\torder by \n" +
                " posisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[17].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportRekapThr(String bulan, String tahun, String unit, String statusPegawai,
                                                          String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (payroll.nip) as jumlah_orang,\n" +
                "\tsum(thr.gaji_golongan) as gaji_dasar,\n" +
                "\tsum(thr.tunjangan_umk) as tunjangan_umk,\n" +
                "\tsum(thr.tunjangan_struktural) as tunjangan_struktural,\n" +
                "\tsum(thr.tunjangan_peralihan) as tunjangan_peralihan,\n" +
                "\tsum(thr.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
                "\tsum(thr.tunjangan_strategis) as tunjangan_strategis,\n" +
                "\tsum(thr.tunjangan_pph) as tunjangan_pph,\n" +
                "\tsum(payroll.pph_gaji) as pot_pph,\n" +
                "\tsum(thr.thr_bersih) as penghasilan_kotor,\n" +
                "\tsum(payroll.gaji_bersih) as thr_bersih\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join it_hris_payroll_thr thr on thr.payroll_id = payroll.payroll_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_thr = 'Y'\n" +
                "\tand  " + strWhere +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "\torder by\n" +
                " posisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setPotPph(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[12].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportRekapJasprod(String bulan, String tahun, String unit, String statusPegawai,
                                                          String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (payroll.nip) as jumlah_orang,\n" +
                "\tsum(jasprod.gaji_golongan) as gaji_dasar,\n" +
                "\tsum(jasprod.tunjangan_umk) as tunjangan_umk,\n" +
                "\tsum(jasprod.tunjangan_struktural) as tunjangan_struktural,\n" +
                "\tsum(jasprod.tunjangan_peralihan) as tunjangan_peralihan,\n" +
                "\tsum(jasprod.tunjangan_jabatan_struktural) as tunjangan_jabatan_struktural,\n" +
                "\tsum(jasprod.tunjangan_strategis) as tunjangan_strategis,\n" +
                "\tsum(jasprod.gaji_bruto) as gaji_bruto,\n" +
                "\tsum(jasprod.gaji_masa_kerja) as gaji_masaKerja,\n" +
                "\tsum(jasprod.nilai_smk) as nilai_smk,\n" +
                "\tsum(jasprod.gaji_masa_kerja_faktor_smk) as perhitungan,\n" +
                "\tsum(jasprod.gaji_masa_kerja_faktor) as gaji_faktorNormal,\n" +
                "\tsum(jasprod.tambahan) as tambahan,\n" +
                "\tsum(jasprod.bruto) as bruto,\n" +
                "\tsum(jasprod.pph_jasprod) as pot_pph,\n" +
                "\tsum(jasprod.pot_koperasi) as pot_koperasi,\n" +
                "\tsum(jasprod.pot_taliasih) as pot_taliasih,\n" +
                "\tsum(jasprod.pot_lain) as pot_lain,\n" +
                "\tsum(jasprod.final_jasprod) as final_jasprod\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join it_hris_payroll_jasprod jasprod on jasprod.payroll_id = payroll.payroll_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_jasprod = 'Y'\n" +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "\torder by\n" +
                " posisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setGajiMasaKerja(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setNilaiSmk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setGajiMasaKerjaFaktorSmk(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setGajiMasaKerjaFaktor(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setTambahan(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setBrutoJasprod(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setPotPph(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[20].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportRekapInsentif(String bulan, String tahun, String unit, String statusPegawai,
                                                          String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (payroll.nip) as jumlah_orang,\n" +
                "\tsum(insentif.gaji_golongan) as gaji_dasar,\n" +
                "\tsum(insentif.tunjangan_umk) as tunjangan_umk,\n" +
                "\tsum(insentif.tunjangan_struktural) as tunjangan_struktural,\n" +
                "\tsum(insentif.tunjangan_peralihan) as tunjangan_peralihan,\n" +
                "\tsum(insentif.tunjangan_jab_struktural) as tunjangan_jabatan_struktural,\n" +
                "\tsum(insentif.tunjangan_strategis) as tunjangan_strategis,\n" +
                "\tsum(insentif.jumlah_bruto) as gaji_bruto,\n" +
                "\tsum(insentif.potongan_insentif_individu) as pot_insentif_individu,\n" +
                "\tsum(insentif.insentif_yang_diterima) as insentif_diterima,\n" +
                "\tsum(insentif.jumlah_pph) as jumlah_pph,\n" +
                "\tsum(insentif.jumlah_insentif) as netto\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join it_hris_payroll_insentif insentif on insentif.payroll_id = payroll.payroll_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_insentif = 'Y'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand posisi.bagian_id not in ('PB023', 'PB010')\n" +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "\torder by\n" +
                " posisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[9].toString())));

            result.setPotonganInsentifIndividu(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setInsentifDiterima(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[13].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportRekapPotonganSys(String bulan, String tahun, String unit, String statusPegawai,
                                                              String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (posisiPegawai.nip) as jumlah_orang,\n" +
                "\tsum(payroll.pph_gaji) as pph_gaji,\n" +
                "\tsum(payroll.pph_pengobatan) as pph_pengobatan,\n" +
                "\tsum(payroll.pph_gaji + payroll.pph_pengobatan) as jumlah_pph,\n" +
                "\tsum(payroll.iuran_pensiun) as iuran_pensiun,\n" +
                "\tsum(payroll.iuran_bpjs_tk) as Astek,\n" +
                "\tsum(payroll.iuran_bpjs_kesehatan) as Kesehatan,\n" +
                "\tsum(payroll.iuran_bpjs_pensiun) as Pensiun,\n" +
                "\tsum(payroll.uang_muka_lainnya) as um_lainlain,\n" +
                "\tsum(payroll.kekurangan_bpjs_tk) as Kekurangan_bpjs,\n" +
                "\tsum(total_b) as J_pot_dinas,\n" +
                "\tsum(payroll.pengobatan) as Biaya_obat,\n" +
                "\tsum(payroll.koperasi) as koperasi,\n" +
                "\tsum(payroll.dansos) as dansos,\n" +
                "\tsum(payroll.sp) as sp,\n" +
                "\tsum(payroll.bazis) as bazis,\n" +
                "\tsum(payroll.bapor) as bapor,\n" +
                "\tsum(payroll.lain_lain) as lain_lain,\n" +
                "\tsum(payroll.total_c) as jumlah_pot_lain,\n" +
                "\tsum(payroll.gaji_bersih) as jumlah_bersih\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_pegawai_position posisiPegawai on posisiPegawai.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = posisiPegawai.nip\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "\tleft join it_hris_payroll payroll on payroll.nip = posisiPegawai.nip\n" +
                "where\n" +
                "\tposisiPegawai.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\tand posisiPegawai.flag ='Y'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand " + strWhere +
                "\ngroup by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tposisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setSP(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[21].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    //rekap potongan v2, mengambil posisi pegawai melalui tabel it_payroll bukan melalui table it_biodata dan it_pegawai_position
    public List<ItPayrollEntity> searchReportRekapPotonganSysV2(String bulan, String tahun, String unit, String statusPegawai,
                                                              String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian,\n" +
                "\tcount (payroll.nip) as jumlah_orang,\n" +
                "\tsum(payroll.pph_gaji) as pph_gaji,\n" +
                "\tsum(payroll.pph_pengobatan) as pph_pengobatan,\n" +
                "\tsum(payroll.pph_gaji + payroll.pph_pengobatan) as jumlah_pph,\n" +
                "\tsum(payroll.iuran_pensiun) as iuran_pensiun,\n" +
                "\tsum(payroll.iuran_bpjs_tk) as Astek,\n" +
                "\tsum(payroll.iuran_bpjs_kesehatan) as Kesehatan,\n" +
                "\tsum(payroll.iuran_bpjs_pensiun) as Pensiun,\n" +
                "\tsum(payroll.uang_muka_lainnya) as um_lainlain,\n" +
                "\tsum(payroll.kekurangan_bpjs_tk) as Kekurangan_bpjs,\n" +
                "\tsum(total_b) as J_pot_dinas,\n" +
                "\tsum(payroll.pengobatan) as Biaya_obat,\n" +
                "\tsum(payroll.koperasi) as koperasi,\n" +
                "\tsum(payroll.dansos) as dansos,\n" +
                "\tsum(payroll.sp) as sp,\n" +
                "\tsum(payroll.bazis) as bazis,\n" +
                "\tsum(payroll.bapor) as bapor,\n" +
                "\tsum(payroll.lain_lain) as lain_lain,\n" +
                "\tsum(payroll.total_c) as jumlah_pot_lain,\n" +
                "\tsum(payroll.gaji_bersih) as jumlah_bersih\n" +
                "from\n" +
                "\tim_position posisi\n" +
                "\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n" +
                "where\n" +
                "\tpayroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.status_pegawai = '"+statusPegawai+"'\n" +
                "\tand pegawai.flag ='Y'\n" +
                "\tand payroll.flag_payroll ='Y'\n" +
                "\tand payroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand " + strWhere +
                "group by\n" +
                "\tposisi.bagian_id,\n" +
                "\tbagian.nama_bagian\n" +
                "order by\n" +
                "\tposisi.bagian_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNamaBagian((String) row[1]);
            result.setJumlahPegawai(Integer.parseInt(row[2].toString()));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setJumlahPph(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTotalB(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setKoperasi(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setDansos(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setSP(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setBazis(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setBapor(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setLainLain(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setTotalC(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[21].toString())));


            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportTransferGajiSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tcase \n" +
                "\t\twhen pegawai.nama_bank is null then '-'\n" +
                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.nama_bank\n" +
                "\tend as nama_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.cabang_bank\n" +
                "\tend as cabang_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.no_rek_bank\n" +
                "\tend as no_rek_bank ,\n" +
                "\t\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "order by\n" +
                "\tpegawai.nama_bank";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setNamaBank((String) row[2]);
            result.setCabangBank((String) row[3]);
            result.setNoRek((String) row[4]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportTransferPendidikanSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tcase \n" +
                "\t\twhen pegawai.nama_bank is null then '-'\n" +
                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.nama_bank\n" +
                "\tend as nama_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.cabang_bank\n" +
                "\tend as cabang_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.no_rek_bank\n" +
                "\tend as no_rek_bank ,\n" +
                "\t\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand payroll.flag_pendidikan = 'Y'\n" +
                "order by\n" +
                "\tpegawai.nama_bank";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setNamaBank((String) row[2]);
            result.setCabangBank((String) row[3]);
            result.setNoRek((String) row[4]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportTransferThrSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tcase \n" +
                "\t\twhen pegawai.nama_bank is null then '-'\n" +
                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.nama_bank\n" +
                "\tend as nama_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.cabang_bank\n" +
                "\tend as cabang_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.no_rek_bank\n" +
                "\tend as no_rek_bank ,\n" +
                "\t\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand payroll.flag_thr = 'Y'\n" +
                "order by\n" +
                "\tpegawai.nama_bank";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setNamaBank((String) row[2]);
            result.setCabangBank((String) row[3]);
            result.setNoRek((String) row[4]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportTransferJasprodSys(String bulan, String tahun, String unit){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.nama,\n" +
                "\tcase \n" +
                "\t\twhen pegawai.nama_bank is null then '-'\n" +
                "\t\twhen pegawai.nama_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.nama_bank\n" +
                "\tend as nama_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.cabang_bank is null then '-'\n" +
                "\t\twhen pegawai.cabang_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.cabang_bank\n" +
                "\tend as cabang_bank ,\n" +
                "\n" +
                "\tcase \n" +
                "\t\twhen pegawai.no_rek_bank is null then '-'\n" +
                "\t\twhen pegawai.no_rek_bank = '' then '-'\n" +
                "\telse\n" +
                "\t\tpegawai.no_rek_bank\n" +
                "\tend as no_rek_bank ,\n" +
                "\t\n" +
                "\tpayroll.gaji_bersih\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n" +
                "\tleft join im_hris_payroll_dana_pensiun dapen on dapen.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.branch_id = '"+unit+"'\n" +
                "\tand pegawai.flag = 'Y'\n" +
                "\tand payroll.flag_jasprod = 'Y'\n" +
                "order by\n" +
                "\tpegawai.nama_bank";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();

            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setNamaBank((String) row[2]);
            result.setCabangBank((String) row[3]);
            result.setNoRek((String) row[4]);
            result.setGajiBersih(BigDecimal.valueOf(Double.valueOf(row[5].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getJumlahKotor(String nip, String bulan, String tahun, String branchId){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\t\n" +
                "payroll.payroll_id,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tpayroll.total_a as gaji,\n" +
                "\tpayroll.pph_gaji as pph,\n" +
                "\tpayroll.total_thr,\n" +
                "\tpayroll.total_pendidikan,\n" +
                "\tpayroll.total_jasprod,\n" +
                "\tpayroll.total_insentif,\n" +
                "\tpayroll.flag_thr,\n" +
                "\tpayroll.flag_pendidikan,\n" +
                "\tpayroll.flag_jasprod,\n " +
                "\tpayroll.tunjangan_baju_dinas,\n" +
                "\tpayroll.flag_payroll,\n" +
                "\tpayroll.flag_insentif,\n" +
                "\tpayroll.pph_pengobatan," +
                "\tpayroll.flag_jubileum,\n" +
                "\tpayroll.flag_rapel,\n" +
                "\tpayroll.total_jubileum,\n" +
                "\tpayroll.flag_rapel,\n" +
                "\tjubileum.pph_jubileum,\n" +
                "\tpayroll.total_rapel,\n" +
                "\tpayroll.tunjangan_pengobatan,\n" +
                "\tpayroll.pengobatan,\n" +
                "\tcase when rapel.total_rapel_bulan is null then 0 else rapel.total_rapel_bulan end\n," +
                "\tcase when rapel.total_rapel_thr is null then 0 else rapel.total_rapel_thr end ,\n" +
                "\tcase when rapel.total_rapel_pendidikan is null then 0 else rapel.total_rapel_pendidikan end ,\n" +
                "\tcase when rapel.total_rapel_insentif is null then 0 else rapel.total_rapel_insentif end ,\n" +
                "\tcase when rapel.total_rapel_jubileum is null then 0 else rapel.total_rapel_jubileum end,\n" +
                "\tcase when rapel.pph_rapel is null then 0 else rapel.pph_rapel end,\n" +
                "\tcase when rapel.pph_rapel_pribadi is null then 0 else rapel.pph_rapel_pribadi end,\n" +
                "\tcase when rapel.tunjangan_pph is null then 0 else rapel.tunjangan_pph end\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_rapel rapel on rapel.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.bulan "+bulan+" \n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.nip = '"+nip+"'\n" +
                "\tand payroll.branch_id = '"+branchId+"'\n" +
                "\tand payroll.flag_pensiun = 'N'\n" +
                "\t--and payroll.flag_rapel = 'N'\n" +
                "\tand payroll.flag_jubileum = 'N'" ;


        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setFlagThr((String) row[9]);
            result.setFlagPendidikan((String) row[10]);
            result.setFlagJasprod((String) row[11]);
            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setFlagPayroll((String) row[13]);
            result.setFlagInsentif((String) row[14]);
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setFlagJubileum((String) row[16]);
            result.setFlagRapel((String) row[17]);
            if(row[18] != null){
                result.setTotalJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            }else{
                result.setTotalJubileum(BigDecimal.valueOf(Double.valueOf(0)));
            }
            result.setTotalRapel(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
            result.setPengobatan(BigDecimal.valueOf(Double.valueOf(row[23].toString())));
            result.setTotalRapelBulan(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setTotalRapelThr(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setTotalRapelPendidikan(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
            result.setTotalRapelInsentif(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
            result.setTotalRapelJubileum(BigDecimal.valueOf(Double.valueOf(row[28].toString())));
            result.setPphGajiRapel(BigDecimal.valueOf(Double.valueOf(row[29].toString())));
            result.setPphGajiRapelPribadi(BigDecimal.valueOf(Double.valueOf(row[30].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[31].toString())));
            listOfResult.add(result);
        }

        return listOfResult;
    }

    public List<ItPayrollEntity> getJumlahKotorKabid(String nip, String bulan, String tahun, String branchId){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\t\n" +
                "payroll.payroll_id,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tpayroll.total_a as gaji,\n" +
                "\tpayroll.pph_gaji as pph,\n" +
                "\tpayroll.total_thr,\n" +
                "\tpayroll.total_pendidikan,\n" +
                "\tpayroll.total_jasprod,\n" +
                "\tpayroll.total_insentif,\n" +
                "\tpayroll.flag_thr,\n" +
                "\tpayroll.flag_pendidikan,\n" +
                "\tpayroll.flag_jasprod,\n " +
                "\tpayroll.tunjangan_baju_dinas,\n" +
                "\tpayroll.flag_payroll,\n" +
                "\tpayroll.flag_insentif,\n" +
                "\tpayroll.pph_pengobatan," +
                "\tpayroll.flag_jubileum,\n" +
                "\tpayroll.flag_rapel,\n" +
                "\tpayroll.total_jubileum,\n" +
                "\tpayroll.total_rapel,\n" +
                "\tjubileum.pph_jubileum\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.bulan "+bulan+" \n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.nip = '"+nip+"'\n" +
                "\tand payroll.branch_id = '"+branchId+"'\n" +
                "\tand payroll.flag_pensiun = 'N'\n" +
                "\tand payroll.flag_insentif = 'N'\n" +
                "\tand payroll.flag_jasprod = 'N'\n" +
                "\tand payroll.flag_jubileum = 'N'" ;


        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setFlagThr((String) row[9]);
            result.setFlagPendidikan((String) row[10]);
            result.setFlagJasprod((String) row[11]);
            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setFlagPayroll((String) row[13]);
            result.setFlagInsentif((String) row[14]);
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setFlagJubileum((String) row[16]);
            result.setFlagRapel((String) row[17]);
            if(row[18] != null){
                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            }else{
                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(0)));
            }
            listOfResult.add(result);
        }

        return listOfResult;
    }

    public List<ItPayrollEntity> getJumlahKotorKabidTanpaPayroll(String nip, String bulan, String tahun, String branchId){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\t\n" +
                "payroll.payroll_id,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tpayroll.total_a as gaji,\n" +
                "\tpayroll.pph_gaji as pph,\n" +
                "\tpayroll.total_thr,\n" +
                "\tpayroll.total_pendidikan,\n" +
                "\tpayroll.total_jasprod,\n" +
                "\tpayroll.total_insentif,\n" +
                "\tpayroll.flag_thr,\n" +
                "\tpayroll.flag_pendidikan,\n" +
                "\tpayroll.flag_jasprod,\n " +
                "\tpayroll.tunjangan_baju_dinas,\n" +
                "\tpayroll.flag_payroll,\n" +
                "\tpayroll.flag_insentif,\n" +
                "\tpayroll.pph_pengobatan," +
                "\tpayroll.flag_jubileum,\n" +
                "\tpayroll.flag_rapel,\n" +
                "\tpayroll.total_jubileum,\n" +
                "\tpayroll.total_rapel,\n" +
                "\tjubileum.pph_jubileum\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.bulan "+bulan+" \n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.nip = '"+nip+"'\n" +
                "\tand payroll.branch_id = '"+branchId+"'\n" +
                "\tand payroll.flag_payroll = 'N'\n" +
                "\tand payroll.flag_pendidikan = 'N'\n" +
                "\tand payroll.flag_thr = 'N'";


        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            if(row[4] != null){
                result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            }else{
                result.setPphGaji(BigDecimal.valueOf(Double.valueOf(0)));
            }
            result.setTotalThr(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTotalPendidikan(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setTotalJasProd(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTotalInsentif(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setFlagThr((String) row[9]);
            result.setFlagPendidikan((String) row[10]);
            result.setFlagJasprod((String) row[11]);
            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setFlagPayroll((String) row[13]);
            result.setFlagInsentif((String) row[14]);
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setFlagJubileum((String) row[16]);
            result.setFlagRapel((String) row[17]);
            if(row[18] != null){
                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            }else{
                result.setPphJubileum(BigDecimal.valueOf(Double.valueOf(0)));
            }
            listOfResult.add(result);
        }

        return listOfResult;
    }

    public List<ItPayrollEntity> getTunjanganAsumsi(String nip, String bulan, String tahun, String branchId){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\t\n" +
                "payroll.payroll_id,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tpph.asumsi_thr,\n" +
                "\tpph.asumsi_pendidikan,\n" +
                "\tpph.asumsi_jasprod,\n" +
                "\tpph.pph_id\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.bulan = '"+bulan+"' \n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.nip = '"+nip+"'\n" +
                "\tand payroll.branch_id = '"+branchId+"'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setAsumsiThr(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setAsumsiPendidikan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setAsumsiJasprod(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setPphId((String) row[6]);

            listOfResult.add(result);
        }

        return listOfResult;
    }

    // cek berapa bulan pernah menjabat bukan sebagain direktur dan komisaris di tahun tertentu
    public List<ItPayrollEntity> kalkulasiBulanBukanDirektur(String nip, String tahun, String strBulan){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        if(!strBulan.equalsIgnoreCase("")){
            strBulan = " and payroll.bulan in " + strBulan + "\n";
        }
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.gaji_golongan,\n" +
                "\tpayroll.tunjangan_umk,\n" +
                "\tpayroll.tunjangan_struktural,\n" +
                "\tpayroll.tunjangan_peralihan,\n" +
                "\tpayroll.tunjangan_jabatan_struktural,\n" +
                "\tpayroll.tunjangan_strategis,\n" +
                "\tpayroll.kompensasi,\n" +
                "\tpayroll.tunjangan_transport,\n" +
                "\tpayroll.tunjangan_air_listrik,\n" +
                "\tpayroll.tunjangan_perumahan,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
                "where\n" +
                "\tpayroll.nip ='"+nip+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" + strBulan +
                "\tand posisi.kelompok_id NOT IN ('KL00', 'KL01')";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setKelompokId((String) row[11]);
            listOfResult.add(result);
        }

        return listOfResult;
    }

    /*// digunakan oleh THR
    // cek berapa bulan menjabat bukan
    public List<ItPayrollEntity> kalkulasiBulanBukanDirektur(String nip, String tahun){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.payroll_id,\n" +
                "\tpayroll.gaji_golongan,\n" +
                "\tpayroll.tunjangan_umk,\n" +
                "\tpayroll.tunjangan_struktural,\n" +
                "\tpayroll.tunjangan_peralihan,\n" +
                "\tpayroll.tunjangan_jabatan_struktural,\n" +
                "\tpayroll.tunjangan_strategis,\n" +
                "\tpayroll.kompensasi,\n" +
                "\tpayroll.tunjangan_transport,\n" +
                "\tpayroll.tunjangan_air_listrik,\n" +
                "\tpayroll.tunjangan_perumahan,\n" +
                "\tposisi.kelompok_id\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join im_position posisi on posisi.position_id = payroll.position_id\n" +
                "where\n" +
                "\tpayroll.nip ='"+nip+"'\n" +
                "\tand payroll.flag_payroll = 'Y'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand posisi.kelompok_id NOT IN ('KL00', 'KL01')";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setPayrollId((String) row[0]);
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setKelompokId((String) row[11]);
            listOfResult.add(result);
        }

        return listOfResult;
    } */

    public List<Payroll> getPphDetail(String tahun, String nip){
        List<Payroll> listOfResult = new ArrayList<Payroll>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tpayroll.nip,\n" +
                "\tpayroll.bulan,\n" +
                "\tpayroll.tahun,\n" +
                "\tpayroll.flag_payroll,\n" +
                "\tpayroll.flag_thr,\n" +
                "\tpayroll.flag_pendidikan,\n" +
                "\tpayroll.flag_insentif,\n" +
                "\tpayroll.flag_jasprod,\n" +
                "\tpayroll.flag_rapel,\n" +
                "\tpayroll.total_a,\n" +
                "\tpayroll.pph_gaji,\n" +
                "\tpph.pph_gaji,\n" +
                "\tpayroll.total_insentif,\n" +
                "\tpayroll.total_pendidikan,\n" +
                "\tpayroll.total_thr,\n" +
                "\tpayroll.total_jasprod,\n" +
                "\tpayroll.total_rapel,\n" +
                "\tpayroll.pph_pengobatan,\n" +
                "\tpayroll.flag_jubileum,\n" +
                "\tpayroll.total_jubileum,\n" +
                "\tjubileum.pph_jubileum\n" +
                "from\n" +
                "\tit_hris_payroll payroll\n" +
                "\tleft join it_hris_payroll_pph pph on pph.payroll_id = payroll.payroll_id\n" +
                "\tleft join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.nip = '"+nip+"'\n" +
                "\tand payroll.tahun = '"+tahun+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "order by\n" +
                "\tpayroll.flag_payroll desc,\n" +
                "\tpayroll.bulan asc";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Payroll result  = new Payroll();
            result.setNip((String) row[0]);
            result.setBulan((String) row[1]);
            result.setTahun((String) row[2]);
            result.setFlagPayroll((String) row[3]);
            result.setFlagThr((String) row[4]);
            result.setFlagPendidikan((String) row[5]);
            result.setFlagInsentif((String) row[6]);
            result.setFlagJasprod((String) row[7]);
            result.setFlagRapel((String) row[8]);

            result.setTotalANilai(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            if(row[10] != null){
                result.setPphGajiNilai(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            }else{
                result.setPphGajiNilai(BigDecimal.valueOf(0));
            }
            result.setTotalInsentifNilai(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTotalPendidikanNilai(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setTotalThrNilai(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTotalJasProdNilai(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTotalRapelNilai(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setPphPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[17].toString())));

            result.setFlagJubileum((String) row[18]);
            result.setTotalJubileumNilai(BigDecimal.valueOf(Double.valueOf(row[19].toString())));

            if(row[20] != null){
                result.setPotPphNilai(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            }else{
                result.setPotPphNilai(BigDecimal.valueOf(0));
            }

            listOfResult.add(result);
        }

        return listOfResult;
    }

    // digunakan oleh AddPayroll.jsp
    // untuk mengambil data insentif yang sudah dibayar pada bulan dan tahun insentif
    public List<Payroll> cekTunjanganInsentif(int bulanMulai, int tahun, String branchId){
        List<Payroll> listOfResult = new ArrayList<Payroll>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tinsentif.bulan_mulai,\n" +
                "\tinsentif.bulan_sampai,\n" +
                "\tinsentif.tahun_insentif,\n" +
                "\tpayroll.branch_id\n" +
                "from\n" +
                "\tit_hris_payroll_insentif insentif\n" +
                "\tleft join it_hris_payroll payroll on payroll.payroll_id = insentif.payroll_id\n" +
                "where\n" +
                "\tinsentif.tahun_insentif ='"+tahun+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand payroll.branch_id = '"+branchId+"'\n" +
                "\tand insentif.bulan_sampai > '"+bulanMulai+"'\n" +
                "group by\n" +
                "\tinsentif.bulan_mulai,\n" +
                "\tinsentif.bulan_sampai,\n" +
                "\tinsentif.tahun_insentif,\n" +
                "\tpayroll.branch_id";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Payroll result  = new Payroll();
            result.setBulanInsentifMulai((int) row[0]);
            result.setBulanInsentifSampai((int) row[1]);
            result.setInsentifTahun((int) row[2]);

            listOfResult.add(result);
        }

        return listOfResult;
    }

    public List<ItPayrollEntity> getTunjanganPeralihanForAbsensi(String nip, String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .list();

        return results;
    }

    // pengobatan selama 1 tahun
    public List<PayrollPph> getJumlahPengobatan(String tahun, String nip){
        List<PayrollPph> listOfResult = new ArrayList<PayrollPph>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select\n" +
                "\tcase when sum(pengobatan) is null then 0 else sum(pengobatan) end as jumlah_pengobatan,\n" +
                "\tcase when sum(pph_pengobatan) is null then 0 else sum(pph_pengobatan) end as pph_pengobatan\n" +
                "from\n" +
                "\tit_hris_payroll\n" +
                "where\n" +
                "\tnip = '"+nip+"'\n" +
                "\tand tahun = '"+tahun+"'\n" +
                "\tand flag_payroll = 'Y'\n" +
                "\tand approval_flag = 'Y'\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PayrollPph result  = new PayrollPph();
            result.setJumlahPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            result.setJumlahPphPengobatanNilai(BigDecimal.valueOf(Double.valueOf(row[1].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<PayrollEsptDTO> searchReportEspt(String tahun, String unit) {
        List<PayrollEsptDTO> listOfResult = new ArrayList<>();
        List<Object[]> results;
        final String query = "SELECT\n" +
                "\tMIN(p.bulan) AS masaperolehanawal,\n" +
                "\tMAX(p.bulan) AS masaperolehanakhir,\n" +
                "\tpeg.npwp AS npwp,\n" +
                "\tpeg.no_ktp AS noktp,\n" +
                "\tpeg.nama_pegawai AS nama,\n" +
                "\tpeg.alamat AS alamat,\n" +
                "\tpeg.jenis_kelamin AS jk,\n" +
                "\tpeg.status_keluarga AS status,\n" +
                "\tpeg.jumlah_anak AS jumlahanak,\t\n" +
                "\tpos.position_name AS namajabatan,\n" +
                "\tsum(p.gaji_golongan) as jumlah1gaji,\n" +
                "\tsum(p.tunjangan_pph) as jumlah2tunjpph,\n" +
                "\tsum(p.tunjangan_umk+p.tunjangan_jabatan_struktural+p.tunjangan_struktural+p.tunjangan_strategis+p.tunjangan_peralihan+p.tunjangan_lain+p.tunjangan_tambahan+p.tunjangan_lembur+p.pemondokan+p.komunikasi+p.total_rlab+p.tunjangan_dapen+p.tunjangan_bpjs_ks+p.tunjangan_bpjs_tk) as jumlah3tunjanganlemburlainnya,\n" +
                "\tsum(p.iuran_dapen_peg+p.iuran_bpjs_tk_kary+p.iuran_bpjs_ks_kary) as jumlah10iuranpensiunthtjht," +
                "\tp.nip \n" +
                "from\n" +
                "\tit_hris_payroll p\n" +
                "\tleft join it_hris_pegawai_position pp on p.nip = pp.nip\n" +
                "\tleft join im_position pos on pp.position_id = pos.position_id\n" +
                "\tleft join im_hris_pegawai peg on peg.nip = p.nip\n" +
                "where\n" +
                "\tpp.branch_id= '"+unit+"'\n" +
                "\tand peg.flag ='Y'\n" +
                "\tand pp.flag='Y'\n" +
                "\tand p.flag='Y'\n" +
                "\tand pos.flag='Y'\n" +
                "\tand p.approval_flag='Y'\n" +
                "\tand p.flag_payroll ='Y'\n" +
                "\tand p.tahun = '"+tahun+"'\n" +
                "group by\n" +
                "\tp.nip,\n" +
                "\tpeg.npwp,\n" +
                "\tpeg.no_ktp,\n" +
                "\tpeg.nama_pegawai,\n" +
                "\tpeg.alamat,\n" +
                "\tpeg.jenis_kelamin,\n" +
                "\tpeg.status_keluarga,\n" +
                "\tpeg.jumlah_anak,\n" +
                "\tpos.position_name,\n" +
                "\tpos.kelompok_id\n" +
                "order by\n" +
                "\tpos.kelompok_id\n" +
                "\t";
        results = (List<Object[]>)this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
        for (Object[] row : results) {
            PayrollEsptDTO result = new PayrollEsptDTO();
            result.setMasaPerolehanAwal((String)row[0]);
            result.setMasaPerolehanAkhir((String)row[1]);
            result.setNpwp((String)row[2]);
            result.setNik((String)row[3]);
            result.setNama((String)row[4]);
            result.setAlamat((String)row[5]);
            result.setJenisKelamin((String)row[6]);
            result.setStatusPtkp((String)row[7]);
            result.setJumlahTanggungan(String.valueOf(row[8]));
            result.setNamaJabatan((String)row[9]);
            result.setJumlah1(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setJumlah2(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setJumlah3(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setJumlah10(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setNip((String)row[14]);
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> searchReportTarikanPendapatanPPH(final String tahun, final String unit) {
        final List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        final String query = "select\n\tpayroll.nip,\n\tpayroll.gaji_golongan as gaji_dasar,\n\tpayroll.tunjangan_umk as tunjangan_umk,\n\tpayroll.tunjangan_struktural as tunjangan_struktural,\n\tpayroll.tunjangan_peralihan as tunjangan_peralihan,\n\tpayroll.tunjangan_jabatan_struktural as tunjangan_jabatan_struktural,\n\tpayroll.tunjangan_strategis as tunjangan_strategis,\n\tpayroll.kompensasi as tunjangan_kompensasi,\n\tpayroll.tunjangan_transport as tunjangan_transport,\n\tpayroll.tunjangan_air_listrik as tunjangan_air_listrik,\n\tpayroll.tunjangan_pengobatan as tunjangan_pengobatan,\n\tpayroll.tunjangan_perumahan as tunjangan_perumahan,\n\tpayroll.tunjangan_pph as tunjangan_pph,\n\tpayroll.tunjangan_lembur as tunjangan_lembur,\n\tpayroll.tunjangan_lain as tunjangan_lain,\n\tpayroll.total_a as penghasilan_kotor,\n\tpayroll.tunjangan_baju_dinas as tunjangan_baju_dinas,\n\tpayroll.iuran_pensiun as iuran_pensiun,\n\tpayroll.iuran_bpjs_tk as iuran_bpjs_tk,\n\tpayroll.iuran_bpjs_pensiun as iuran_bpjs_pensiun,\n\tpayroll.iuran_bpjs_kesehatan as iuran_bpjs_kesehatan,\n\tpayroll.uang_muka_lainnya as uang_muka_lainnya,\n\tpayroll.kekurangan_bpjs_tk as kekurangan_bpjs_tk,\n\tpayroll.bulan as bulan,\n\tpayroll.pph_gaji as pph,\n\tpayroll.pph_pengobatan as pph_obat,\n\tpayroll.tunjangan_pengobatan as tunj_obat,\n\tpayroll.tunjangan_lembur as lembur\nfrom\n\tim_position posisi\n\tleft join im_hris_position_bagian bagian on bagian.bagian_id = posisi.bagian_id\n\tleft join it_hris_payroll payroll on payroll.position_id = posisi.position_id\n\tleft join im_hris_pegawai pegawai on pegawai.nip = payroll.nip\n\tleft join it_hris_pegawai_position pegawai_position on pegawai.nip = pegawai_position.nip\nwhere\n\tpegawai_position.branch_id= '" + unit + "'\n" + "\tand pegawai.flag ='Y'\n" + "\tand payroll.flag_payroll ='Y'\n" + "\tand payroll.tahun = '" + tahun + "'\n" + "\tand payroll.flag_payroll = 'Y'\n" + "order by\n" + "\tpayroll.nip";
        results = (List<Object[]>)this.sessionFactory.getCurrentSession().createSQLQuery(query).list();
        for (final Object[] row : results) {
            final ItPayrollEntity result = new ItPayrollEntity();
            result.setNip((String)row[0]);
            result.setGajiGolongan(BigDecimal.valueOf(Double.valueOf(row[1].toString())));
            result.setTunjanganUmk(BigDecimal.valueOf(Double.valueOf(row[2].toString())));
            result.setTunjanganStruktural(BigDecimal.valueOf(Double.valueOf(row[3].toString())));
            result.setTunjanganPeralihan(BigDecimal.valueOf(Double.valueOf(row[4].toString())));
            result.setTunjanganJabatanStruktural(BigDecimal.valueOf(Double.valueOf(row[5].toString())));
            result.setTunjanganStrategis(BigDecimal.valueOf(Double.valueOf(row[6].toString())));
            result.setKompensasi(BigDecimal.valueOf(Double.valueOf(row[7].toString())));
            result.setTunjanganTransport(BigDecimal.valueOf(Double.valueOf(row[8].toString())));
            result.setTunjanganAirListrik(BigDecimal.valueOf(Double.valueOf(row[9].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[10].toString())));
            result.setTunjanganPerumahan(BigDecimal.valueOf(Double.valueOf(row[11].toString())));
            result.setTunjanganPph(BigDecimal.valueOf(Double.valueOf(row[12].toString())));
            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[13].toString())));
            result.setTunjanganLain(BigDecimal.valueOf(Double.valueOf(row[14].toString())));
            result.setTotalA(BigDecimal.valueOf(Double.valueOf(row[15].toString())));
            result.setTunjanganBajuDinas(BigDecimal.valueOf(Double.valueOf(row[16].toString())));
            result.setIuranPensiun(BigDecimal.valueOf(Double.valueOf(row[17].toString())));
            result.setIuranBpjsTk(BigDecimal.valueOf(Double.valueOf(row[18].toString())));
            result.setIuranBpjsPensiun(BigDecimal.valueOf(Double.valueOf(row[19].toString())));
            result.setIuranBpjsKesehatan(BigDecimal.valueOf(Double.valueOf(row[20].toString())));
            result.setUangMukaLainnya(BigDecimal.valueOf(Double.valueOf(row[21].toString())));
            result.setKekuranganBpjsTk(BigDecimal.valueOf(Double.valueOf(row[22].toString())));
            result.setBulan((String)row[23]);
            result.setPphGaji(BigDecimal.valueOf(Double.valueOf(row[24].toString())));
            result.setPphPengobatan(BigDecimal.valueOf(Double.valueOf(row[25].toString())));
            result.setTunjanganPengobatan(BigDecimal.valueOf(Double.valueOf(row[26].toString())));
            result.setTunjanganLembur(BigDecimal.valueOf(Double.valueOf(row[27].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItPayrollEntity> getDataEditPensiun(String branchId, String strWhere){
        List<ItPayrollEntity> listOfResult = new ArrayList<ItPayrollEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "  pegawai.nip,\n" +
                "  pegawai.nama_pegawai,\n" +
                "  pegawai.branch_id_terakhir,\n" +
                "  branch.branch_name,\n" +
                "  position.department_id,\n" +
                "  department.department_name,\n" +
                "  pegawai.position_id_terakhir,\n" +
                "  position.position_name,\n" +
                "  pegawai.golongan_id,\n" +
                "  golongan.golongan_name,\n" +
                "  position.kelompok_id,\n" +
                "  pegawai.point,\n" +
                "  pegawai.status_keluarga,\n" +
                "  pegawai.jumlah_anak,\n" +
                "  branch.multifikator,\n" +
                "  pegawai.zakat_profesi,\n" +
                "  pegawai.jenis_kelamin,\n" +
                "  pegawai.dana_pensiun, \n" +
                "  pegawai.tanggal_aktif, \n" +
                "  pegawai.tanggal_pensiun, \n" +
                "  pegawai.tipe_pegawai, \n" +
                "  pegawai.struktur_gaji, \n" +
                "  pegawai.gaji, \n" +
                "  tipePegawai.tipe_pegawai_name, \n" +
                "  position.kelompok_id,\n" +
                "  pegawai.status_giling,\n" +
                "  danaPensiun.dana_pensiun as nama_dana_pensiun,\n" +
                "  pegawai.npwp,\n" +
                "  pegawai.status_pegawai, \n" +
                "  pegawai.golongan_dapen, \n" +
                "  pegawai.golongan_dapen_nusindo,  \n" +
                "  pegawai.poin_lebih,  branch.umr \n" +
                "   FROM im_hris_pegawai pegawai\n" +
                "LEFT JOIN im_branches branch\n" +
                "  ON branch.branch_id = pegawai.branch_id_terakhir\n" +
                "LEFT JOIN im_position position\n" +
                "  ON position.position_id = pegawai.position_id_terakhir\n" +
                "LEFT JOIN im_hris_department department\n" +
                "  ON department.department_id = position.department_id\n" +
                "LEFT JOIN im_hris_golongan golongan\n" +
                "  ON golongan.golongan_id = pegawai.golongan_id\n" +
                "LEFT JOIN im_hris_tipe_pegawai tipePegawai\n" +
                "  ON tipePegawai.tipe_pegawai_id = pegawai.tipe_pegawai\n" +
                "LEFT JOIN im_hris_payroll_dana_pensiun danaPensiun\n" +
                "  ON danaPensiun.dana_pensiun_id = pegawai.dana_pensiun\n" +
                "WHERE \n" +
                "pegawai.flag = 'N'\n" +
                "AND pegawai.position_id_terakhir is not null\n" +
                "AND pegawai.branch_id_terakhir= '"+branchId+"'\n" +
                strWhere;

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ItPayrollEntity result  = new ItPayrollEntity();
            result.setNip((String) row[0]);
            result.setNama((String) row[1]);
            result.setBranchId((String) row[2]);
            result.setBranchName((String) row[3]);
            result.setDepartmentId((String) row[4]);
            result.setDepartmentName((String) row[5]);
            result.setPositionId((String) row[6]);
            result.setPositionName((String) row[7]);
            result.setGolonganId((String) row[8]);
            result.setGolonganName((String) row[9]);
            result.setKelompokId((String) row[10]);
            result.setPoint(Integer.parseInt(row[11].toString()));
            result.setStatusKeluarga((String) row[12]);
            result.setJumlahAnak(Integer.valueOf(row[13].toString()));
            result.setMultifikator(Double.valueOf(row[14].toString()).intValue() + "");
            result.setFlagZakat((String) row[15]);
            result.setGender((String) row[16]);
            result.setDanaPensiun((String) row[17]);
            result.setTanggalAktif((Date) row[18]);
            result.setTanggalPensiun((Date) row[19]);
            result.setTipePegawai((String) row[20]);
            result.setStrukturGaji((String) row[21]);
            result.setBiodataGaji(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            result.setTipePegawaiName((String) row[23]);
            result.setKelompokId((String) row[24]);
            result.setStatusGiling((String) row[25]);
            result.setDanaPensiunName((String) row[26]);
            result.setFlagPjs(("N"));
            result.setNpwp((String) row[27]);
            result.setStatusPegawai((String) row[28]);
            result.setGolonganDapen((String) row[29]);
            result.setGolonganDapenNusindo((String) row[30]);
            result.setPointLebih(Integer.parseInt(row[31].toString()));
            result.setUmr(BigDecimal.valueOf(Double.parseDouble(row[32].toString())));

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public BigDecimal totalLain(String tahun, String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(lain_lain) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand bulan<>'12'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public List<ItPayrollEntity> getDataPayrollByBulanBranchAndTahun(String branchId,String bulan,String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("branchId", branchId))
                .addOrder(Order.desc("payrollId"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataPayrollByBulan12Branch(String branchId,String tahun) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", "12"))
                .add(Restrictions.eq("branchId", branchId))
                .addOrder(Order.desc("payrollId"))
                .list();
        return results;
    }

    public List<ItPayrollEntity> getDataPayrollByBulanBranchApproveNull(String branchId) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.or(
                        Restrictions.isNull("approvalFlag"),
                        Restrictions.eq("approvalFlag", "N")
                ))
                .addOrder(Order.desc("payrollId"))
                .list();
        return results;
    }
    public List<ItPayrollEntity> getBonusDalam1Tahun(String branchId,String tahun,String tipeWhere) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq(tipeWhere, "Y"))
                .addOrder(Order.desc("payrollId"))
                .list();
        return results;
    }
    public List<ItPayrollEntity> getBonusDalam1TahunNip(String branchId,String tahun,String tipeWhere,String nip) throws HibernateException {
        List<ItPayrollEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq(tipeWhere, "Y"))
                .addOrder(Order.desc("payrollId"))
                .list();
        return results;
    }
    public BigDecimal getBruto11Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query=" SELECT " +
                "sum (gaji_golongan+\n" +
                "\t\t   tunjangan_umk+\n" +
                "\t\t   tunjangan_struktural+\n" +
                "\t\t   tunjangan_peralihan+\n" +
                "\t\t   tunjangan_jabatan_struktural+\n" +
                "\t\t   tunjangan_strategis+\n" +
                "\t\t   tunjangan_lain+\n" +
                "\t\t   tunjangan_tambahan+\n" +
                "\t\t   tunjangan_lembur+\n" +
                "\t\t   pemondokan+\n" +
                "\t\t   komunikasi+\n" +
                "\t\t   total_rlab+\n" +
                "\t\t   tunjangan_tambahan+\n" +
                "\t\t   tunjangan_dapen+\n" +
                "\t\t   tunjangan_bpjs_ks+\n" +
                "\t\t   tunjangan_bpjs_tk ) as jumlah\n" +
                "\tfrom it_hris_payroll" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand bulan<>'12'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
    public BigDecimal getPPhGaji11Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(pph_gaji\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand bulan<>'12'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
    public BigDecimal getPPhGaji12Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(pph_gaji\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public BigDecimal getPPhGajiBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(pph_gaji\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll<>'Y'\n" +
                "\t\tand flag_pensiun<>'Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public BigDecimal getTunjanganPPhGaji11Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(tunjangan_pph\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand bulan<>'12'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
    public BigDecimal getTunjanganPPhGajiBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(tunjangan_pph\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll<>'Y'\n" +
                "\t\tand flag_pensiun<>'Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public BigDecimal getTotalBonusSetahun(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(tambahan_lain\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll<>'Y'\n" +
                "\t\tand flag_pensiun<>'Y'\n" +
                "\t\tand approval_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
    public BigDecimal getIuran11Bulan(String tahun,String nip){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(iuran_dapen_peg+\n" +
                "\t\tiuran_bpjs_tk_kary+\n" +
                "\t\tiuran_bpjs_ks_kary\n" +
                "\t\t  ) as jumlah\n" +
                "\tfrom it_hris_payroll\n" +
                "\t\twhere nip = '"+nip+"'\n" +
                "\t\tand tahun='"+tahun+"'\n" +
                "\t\tand bulan<>'12'\n" +
                "\t\tand flag='Y'\n" +
                "\t\tand flag_payroll='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public List<ItPayrollEntity> getDetailPendapatanTidakTeratur(String nip,String tahun) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
        criteria.add(Restrictions.eq("nip", nip));
        criteria.add(Restrictions.eq("tahun", tahun));
        criteria.add(Restrictions.eq("approvalFlag", "Y"));
        criteria.add(Restrictions.ne("flagPayroll", "Y"));
        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.desc("payrollId"));

        List<ItPayrollEntity> results = criteria.list();

        return results;
    }
    public List<ItPayrollEntity> getDetailPPh11Bulan(String nip,String tahun) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
        criteria.add(Restrictions.eq("nip", nip));
        criteria.add(Restrictions.eq("tahun", tahun));
        criteria.add(Restrictions.eq("approvalFlag", "Y"));
//        criteria.add(Restrictions.eq("flagPayroll", "Y"));
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.add(Restrictions.ne("bulan", "12"));

        // Order by
        criteria.addOrder(Order.asc("payrollId"));

        List<ItPayrollEntity> results = criteria.list();

        return results;
    }
    public List<ItPayrollEntity> getPayrollBulan12(String nip,String tahun) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollEntity.class);
        criteria.add(Restrictions.eq("nip", nip));
        criteria.add(Restrictions.eq("tahun", tahun));
        criteria.add(Restrictions.eq("flagPayroll", "Y"));
        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.add(Restrictions.eq("bulan", "12"));

        // Order by
        criteria.addOrder(Order.asc("payrollId"));

        List<ItPayrollEntity> results = criteria.list();

        return results;
    }

    public List<Payroll> getDaftarGajiKaryawan(String bulan, String tahun,String branch){
        List<Payroll> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tpay.nip,\n" +
                "\tpay.nama,\n" +
                "\tpay.golongan_name,\n" +
                "\tpeg.no_rek_bank,\n" +
                "\tpay.status_keluarga,\n" +
                "\tpay.jumlah_anak,\n" +
                "\tpay.gaji_golongan,\n" +
                "\tpay.tunjangan_umk,\n" +
                "\tpay.tunjangan_jabatan_struktural,\n" +
                "\tpay.tunjangan_struktural,\n" +
                "\tpay.tunjangan_strategis,\n" +
                "\tpay.tunjangan_peralihan,\n" +
                "\tpay.tunjangan_lain,\n" +
                "\tpay.tunjangan_lembur,\n" +
                "\tpay.tunjangan_rumah,\n" +
                "\tpay.tunjangan_listrik,\n" +
                "\tpay.tunjangan_air,\n" +
                "\tpay.tunjangan_bbm,\n" +
                "\tpay.tunjangan_pph,\n" +
                "\tpay.tunjangan_dapen,\n" +
                "\tpay.tunjangan_bpjs_tk,\n" +
                "\tpay.tunjangan_bpjs_ks,\n" +
                "\tpay.tunjangan_sosial_lain,\n" +
                "\tpay.pph_gaji,\n" +
                "\tpay.iuran_ypks,\n" +
                "\tpay.iuran_dapen_peg,\n" +
                "\tpay.iuran_dapen_persh,\n" +
                "\tpay.iuran_bpjs_tk_pers,\n" +
                "\tpay.iuran_bpjs_tk_kary,\n" +
                "\tpay.iuran_bpjs_ks_pers,\n" +
                "\tpay.iuran_bpjs_ks_kary,\n" +
                "\tpay.total_potongan_lain,\n" +
                "\tpay.department_id,\n" +
                "\tpay.department_name\n" +
                "FROM\n" +
                "\tit_hris_payroll pay \n" +
                "\tLEFT JOIN im_hris_pegawai peg ON pay.nip = peg.nip \n" +
                "\tLEFT JOIN im_position pos ON pay.position_id = pos.position_id\n" +
                "WHERE\n" +
                "\tbulan='"+bulan+"' \n" +
                "\tAND tahun='"+tahun+"'\n" +
                "\tAND branch_id='"+branch+"'\n" +
                "\tAND approval_flag='Y'\n" +
                "\tAND flag_payroll='Y'\n" +
                "ORDER BY\n" +
                "\tpay.department_id ASC ,\n" +
                "\tpos.kelompok_id ASC";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            Payroll data= new Payroll();
            data.setNip((String) row[0]);
            data.setNama((String) row[1]);
            data.setGolonganName((String) row[2]);
            data.setNoRek((String) row[3]);
            data.setStatusKeluarga((String) row[4]+"/"+row[5].toString());
            data.setGajiGolonganNilai(BigDecimal.valueOf(Double.parseDouble(row[6].toString())));
            data.setTunjanganUmkNilai(BigDecimal.valueOf(Double.parseDouble(row[7].toString())));
            data.setTunjanganJabatanStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[8].toString())));
            data.setTunjanganStrukturalNilai(BigDecimal.valueOf(Double.parseDouble(row[9].toString())));
            data.setTunjanganStrategisNilai(BigDecimal.valueOf(Double.parseDouble(row[10].toString())));
            data.setTunjanganPeralihanNilai(BigDecimal.valueOf(Double.parseDouble(row[11].toString())));
            data.setTunjanganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[12].toString())));
            data.setTunjanganLemburNilai(BigDecimal.valueOf(Double.parseDouble(row[13].toString())));
            data.setTunjanganRumahNilai(BigDecimal.valueOf(Double.parseDouble(row[14].toString())));
            data.setTunjanganListrikNilai(BigDecimal.valueOf(Double.parseDouble(row[15].toString())));
            data.setTunjanganAirNilai(BigDecimal.valueOf(Double.parseDouble(row[16].toString())));
            data.setTunjanganBBMNilai(BigDecimal.valueOf(Double.parseDouble(row[17].toString())));
            data.setTunjanganPphNilai(BigDecimal.valueOf(Double.parseDouble(row[18].toString())));
            data.setTunjanganDapenNilai(BigDecimal.valueOf(Double.parseDouble(row[19].toString())));
            data.setTunjanganBpjsTkNilai(BigDecimal.valueOf(Double.parseDouble(row[20].toString())));
            data.setTunjanganBpjsKsNilai(BigDecimal.valueOf(Double.parseDouble(row[21].toString())));
            data.setTunjanganSosialLainNilai(BigDecimal.valueOf(Double.parseDouble(row[22].toString())));
            data.setPphGajiNilai(BigDecimal.valueOf(Double.parseDouble(row[23].toString())));
            data.setIuranYpksNilai(BigDecimal.valueOf(Double.parseDouble(row[24].toString())));
            data.setIuranDapenPegNilai(BigDecimal.valueOf(Double.parseDouble(row[25].toString())));
            data.setIuranDapenPershNilai(BigDecimal.valueOf(Double.parseDouble(row[26].toString())));
            data.setIuranBpjsTkPersNilai(BigDecimal.valueOf(Double.parseDouble(row[27].toString())));
            data.setIuranBpjsTkKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[28].toString())));
            data.setIuranBpjsKsPersNilai(BigDecimal.valueOf(Double.parseDouble(row[29].toString())));
            data.setIuranBpjsKsKaryNilai(BigDecimal.valueOf(Double.parseDouble(row[30].toString())));
            data.setTotalPotonganLainNilai(BigDecimal.valueOf(Double.parseDouble(row[31].toString())));
            data.setDepartmentId((String) row[32]);
            data.setDepartmentName((String) row[33]);
            listOfResult.add(data);
        }
        return listOfResult;
    }
}