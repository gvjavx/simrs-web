package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
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
public class PengajuanSetorDao extends GenericDao<ItPengajuanSetorEntity, String> {

    @Override
    protected Class<ItPengajuanSetorEntity> getEntityClass() {
        return ItPengajuanSetorEntity.class;
    }

    @Override
    public List<ItPengajuanSetorEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengajuan_setor_id")!=null) {
                criteria.add(Restrictions.eq("pengajuanSetorId", (String) mapCriteria.get("pengajuan_setor_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.ilike("keterangan", "%"+(String) mapCriteria.get("keterangan")+"%"));
            }
            if (mapCriteria.get("tipe_pengajuan_setor")!=null) {
                criteria.add(Restrictions.eq("tipePengajuanSetor", (String) mapCriteria.get("tipe_pengajuan_setor")));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("registeredDate",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("registeredDate",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("registeredDate",mapCriteria.get("tanggal_selesai")));
                }
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanSetorId"));
        List<ItPengajuanSetorEntity> results = criteria.list();

        return results;
    }

    public List<PengajuanSetorDetail> listPPhPayroll (PengajuanSetor search){

        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tp.nip,\n" +
                "\tp.nama,\n" +
                "\tp.position_id,\n" +
                "\tp.pph_gaji,\n" +
                "\tp.flag_payroll,\n" +
                "\tp.flag_thr,\n" +
                "\tp.flag_cuti_tahunan,\n" +
                "\tp.flag_jasprod,\n" +
                "\tp.flag_cuti_panjang,\n" +
                "\tp.flag_pensiun,\n" +
                "\tp.flag_insentif,\n" +
                "\tp.flag_jubileum,\n" +
                "\tp.payroll_id,\n" +
                "\tp.bulan,\n" +
                "\tp.tahun \n" +
                "FROM\n" +
                "\tit_hris_payroll p\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail sd ON p.payroll_id=sd.transaksi_id\n" +
                "\t\tLEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id=sd.pengajuan_setor_id\n" +
                "WHERE\n" +
                "\tp.tahun='"+search.getTahun()+"'\n" +
                "\tAND p.bulan IN ("+search.getBulan()+")\n" +
                "\tAND p.pph_gaji <> 0\n" +
                "\tAND p.branch_id='"+search.getBranchId()+"'\n" +
                "\tAND (s.cancel_flag='Y' OR sd.pengajuan_setor_detail_id IS NULL)\n" +
                "\tAND p.approval_flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            String periode = row[13].toString()+"/"+row[14].toString();
            data.setTipe("Payroll");
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());
            data.setPositionId(row[2].toString());

            if (row[3]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[3].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }

            if ("Y".equalsIgnoreCase(row[4].toString())){
                data.setNote("PPh Gaji "+periode);
            }else if ("Y".equalsIgnoreCase(row[5].toString())){
                data.setNote("PPh THR "+periode);
            }else if ("Y".equalsIgnoreCase(row[6].toString())){
                data.setNote("PPh Cuti Tahunan "+periode);
            }else if ("Y".equalsIgnoreCase(row[7].toString())){
                data.setNote("PPh Jasa Operasional "+periode);
            }else if ("Y".equalsIgnoreCase(row[8].toString())){
                data.setNote("PPh Cuti Panjang "+periode);
            }else if ("Y".equalsIgnoreCase(row[9].toString())){
                data.setNote("PPh Pensiun "+periode);
            }else if ("Y".equalsIgnoreCase(row[10].toString())){
                data.setNote("PPh Insentif "+periode);
            }else if ("Y".equalsIgnoreCase(row[11].toString())){
                data.setNote("PPh Penghargaan Masa Kerja "+periode);
            }
            data.setTransaksiId(row[12].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPhKsoDokter (PengajuanSetor search){

        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "                pd.dokter_id, \n" +
                "                pd.dokter_name, \n" +
                "                pd.pph_final,\n" +
                "                pd.pendapatan_dokter_id, \n" +
                "                pd.bulan, \n" +
                "                pd.tahun  \n" +
                "                FROM \n" +
                "                it_hris_pendapatan_dokter pd\n" +
                "                LEFT JOIN it_akun_pengajuan_setor_detail sd ON pd.pendapatan_dokter_id=sd.transaksi_id \n" +
                "\t\tLEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id=sd.pengajuan_setor_id\n" +
                "                WHERE \n" +
                "                pd.tahun='"+search.getTahun()+"' \n" +
                "                AND pd.bulan IN ("+search.getBulan()+") \n" +
                "                AND pd.pph_final <> 0 \n" +
                "                AND pd.branch_id='"+search.getBranchId()+"' \n" +
                "\tAND (s.cancel_flag='Y' OR sd.pengajuan_setor_detail_id IS NULL)\n" +
                "                AND pd.approval_flag='Y'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            String periode = row[4].toString()+"/"+row[5].toString();
            data.setTipe("Dokter KSO");
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());

            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }

            data.setNote("Dokter KSO "+periode);

            data.setTransaksiId(row[3].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPhPengajuan (PengajuanSetor search){
        String tanggalAwal = search.getTahun()+"-01-01";
        String tanggalAkhir = search.getTahun()+"-"+search.getBulanAsli()+"-01";

        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "                pbd.divisi_id, \n" +
                "                pbd.keperluan, \n" +
                "                pbd.pph,\n" +
                "                pbd.pengajuan_biaya_detail_id, \n" +
                "                pbd.tanggal,\n" +
                "\t\t\t\tpbd.status_keuangan,\n" +
                "\t\t\t\tpbd.approval_keuangan_kp_flag,\n" +
                "\t\t\t\tpbd.approval_keuangan_flag\n" +
                "\t\t\t\tFROM \n" +
                "                it_akun_pengajuan_biaya_detail pbd\n" +
                "                LEFT JOIN it_akun_pengajuan_setor_detail sd ON pbd.pengajuan_biaya_detail_id=sd.transaksi_id \n" +
                "\t\tLEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id=sd.pengajuan_setor_id\n" +
                "                WHERE \n" +
                "                pbd.tanggal<='"+tanggalAkhir+"'\n" +
                "                AND pbd.tanggal >='"+tanggalAwal+"'\n" +
                "                AND pbd.pph <> 0 \n" +
                "                \t\t\t\tAND (tipe_pengajuan_setor <> 'PPH21' OR (tipe_pengajuan_setor = 'PPH21' AND cancel_flag='Y') OR ( pengajuan_setor_detail_id IS NULL)) \n " +
                "                AND pbd.branch_id='"+search.getBranchId()+"' \n" +
                "\t\t\t\tAND pbd.status_keuangan IS NOT NULL";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Pengajuan Biaya PPH21");
            data.setPositionId(row[0].toString());
            data.setNote(row[1].toString());
            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            String status = row[5].toString();
            String approvalKeuanganKpFlag = row[6].toString();
            String approvalKeuanganFlag = row[7].toString();

            if ("KP".equalsIgnoreCase(status)&&"Y".equalsIgnoreCase(approvalKeuanganKpFlag)){
                listOfResult.add(data);
            }else if ("A".equalsIgnoreCase(approvalKeuanganFlag)){
                listOfResult.add(data);
            }

        }
        return listOfResult;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanSetorId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_setor')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PS"+sId;
    }

    public List<PengajuanSetorDetail> listPPnKeluaran (PengajuanSetor search){
        String tanggalAwal = search.getTahun()+"-01-01";
        String tanggalAkhir = search.getTahun()+"-"+search.getBulanAsli()+"-01";
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  j.no_jurnal, \n" +
                "  j.sumber, \n" +
                "  jd.jumlah_kredit, \n" +
                "  j.keterangan \n" +
                "FROM \n" +
                "  it_akun_jurnal j \n" +
                "  LEFT JOIN it_akun_jurnal_detail jd ON jd.no_jurnal = j.no_jurnal \n" +
                "  LEFT JOIN it_akun_pengajuan_setor_detail sd ON j.no_jurnal = sd.transaksi_id \n" +
                "  LEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id = sd.pengajuan_setor_id \n" +
                "WHERE \n" +
                "  j.branch_id = '"+search.getBranchId()+"' \n" +
                "  AND j.registered_flag = 'Y' \n" +
                "  AND j.tanggal_jurnal >= '"+tanggalAwal+"' \n" +
                "  AND j.tanggal_jurnal <= '"+tanggalAkhir+"' \n" +
                "  AND jd.rekening_id = '00199' \n" +
                "  AND (\n" +
                "    s.cancel_flag = 'Y' \n" +
                "    OR sd.pengajuan_setor_detail_id IS NULL\n" +
                "  )\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("PPN Keluaran");
            data.setTransaksiId(row[0].toString());
            data.setPersonId(row[1].toString());
            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }

            data.setNote(row[3].toString());


            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPnMasukan (PengajuanSetor search){
        String tanggalAwal = search.getTahun()+"-01-01";
        String tanggalAkhir = search.getTahun()+"-"+search.getBulanAsli()+"-01";
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  j.no_jurnal, \n" +
                "  j.sumber, \n" +
                "  jd.jumlah_debit, \n" +
                "  j.keterangan \n" +
                "FROM \n" +
                "  it_akun_jurnal j \n" +
                "  LEFT JOIN it_akun_jurnal_detail jd ON jd.no_jurnal = j.no_jurnal \n" +
                "  LEFT JOIN it_akun_pengajuan_setor_detail sd ON j.no_jurnal = sd.transaksi_id \n" +
                "  LEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id = sd.pengajuan_setor_id \n" +
                "WHERE \n" +
                "  j.branch_id = '"+search.getBranchId()+"' \n" +
                "  AND j.registered_flag = 'Y' \n" +
                "  AND j.tanggal_jurnal >= '"+tanggalAwal+"' \n" +
                "  AND j.tanggal_jurnal <= '"+tanggalAkhir+"' \n" +
                "  AND jd.rekening_id = '00198' \n" +
                "  AND (\n" +
                "    s.cancel_flag = 'Y' \n" +
                "    OR sd.pengajuan_setor_detail_id IS NULL\n" +
                "  )\n";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("PPN Masukan");
            data.setTransaksiId(row[0].toString());
            data.setPersonId(row[1].toString());
            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }

            data.setNote(row[3].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPnPengajuan (PengajuanSetor search){
        String tanggalAwal = search.getTahun()+"-01-01";
        String tanggalAkhir = search.getTahun()+"-"+search.getBulanAsli()+"-01";

        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "                pbd.divisi_id, \n" +
                "                pbd.keperluan, \n" +
                "                pbd.ppn,\n" +
                "                pbd.pengajuan_biaya_detail_id, \n" +
                "                pbd.tanggal,\n" +
                "\t\t\t\tpbd.status_keuangan,\n" +
                "\t\t\t\tpbd.approval_keuangan_kp_flag,\n" +
                "\t\t\t\tpbd.approval_keuangan_flag\n" +
                "\t\t\t\tFROM \n" +
                "                it_akun_pengajuan_biaya_detail pbd\n" +
                "                LEFT JOIN it_akun_pengajuan_setor_detail sd ON pbd.pengajuan_biaya_detail_id=sd.transaksi_id \n" +
                "\t\tLEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id=sd.pengajuan_setor_id\n" +
                "                WHERE \n" +
                "                pbd.tanggal<='"+tanggalAkhir+"'\n" +
                "                AND pbd.tanggal >='"+tanggalAwal+"'\n" +
                "                AND pbd.pph <> 0 \n" +
                "                \t\t\t\tAND (tipe_pengajuan_setor <> 'PPN' OR (tipe_pengajuan_setor = 'PPN' AND cancel_flag='Y') OR (pengajuan_setor_detail_id IS NULL)) \n " +
                "                AND pbd.branch_id='"+search.getBranchId()+"' \n" +
                "\t\t\t\tAND pbd.status_keuangan IS NOT NULL";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Pengajuan Biaya PPN");
            data.setPositionId(row[0].toString());
            data.setNote(row[1].toString());
            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            String status = row[5].toString();
            String approvalKeuanganKpFlag = row[6].toString();
            String approvalKeuanganFlag = row[7].toString();

            if ("KP".equalsIgnoreCase(status)&&"Y".equalsIgnoreCase(approvalKeuanganKpFlag)){
                listOfResult.add(data);
            }else if ("A".equalsIgnoreCase(approvalKeuanganFlag)){
                listOfResult.add(data);
            }

        }
        return listOfResult;
    }
}
