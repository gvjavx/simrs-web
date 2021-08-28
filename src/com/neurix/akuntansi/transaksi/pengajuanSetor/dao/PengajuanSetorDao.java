package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorDetailEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.common.constant.CommonConstant;
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
        String query = "SELECT \n" +
                "\tjd.master_id,\n" +
                "\tp.nama_pegawai,\n" +
                "\tjd.jumlah_kredit,\n" +
                "\tjd.no_nota,\n" +
                "\tj.keterangan\n" +
                "FROM \n" +
                "\tit_akun_jurnal j\n" +
                "\tLEFT JOIN it_akun_jurnal_detail jd ON j.no_jurnal=jd.no_jurnal\n" +
                "\tLEFT JOIN im_hris_pegawai p ON jd.master_id = p.nip\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail ps ON ps.transaksi_id = jd.no_nota\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor s ON ps.pengajuan_setor_id = s.pengajuan_setor_id\n" +
                "WHERE\n" +
                "\tj.keterangan ilike 'Pembayaran payrol%'\n" +
                "\tAND j.branch_id='"+search.getBranchId()+"'\n" +
                "\tAND j.tanggal_jurnal >='"+search.getStTanggalDari()+"'\n" +
                "\tAND j.tanggal_jurnal <'"+search.getStTanggalSelesai()+"'\n" +
                "\tAND j.registered_flag='Y'\n" +
                "\tAND jd.nomor_rekening ='"+CommonConstant.REKENING_PPH21+"'\n" +
                "\tAND (s.cancel_flag='Y' OR ps.pengajuan_setor_detail_id IS NULL)";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Payroll");
            data.setNote(row[4].toString());
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());

            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPhKsoDokter (PengajuanSetor search){

        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "\tjd.master_id,\n" +
                "\td.nama_dokter,\n" +
                "\tjd.jumlah_kredit,\n" +
                "\tjd.no_nota,\n" +
                "\tj.keterangan\n" +
                "FROM \n" +
                "\tit_akun_jurnal j\n" +
                "\tLEFT JOIN it_akun_jurnal_detail jd ON j.no_jurnal=jd.no_jurnal\n" +
                "\tLEFT JOIN im_simrs_dokter d ON d.kodering = jd.master_id\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail ps ON ps.transaksi_id = jd.no_nota\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor s ON ps.pengajuan_setor_id = s.pengajuan_setor_id\n" +
                "WHERE\n" +
                "\tj.keterangan ilike 'Pendapatan dokter%'\n" +
                "\tAND j.branch_id='"+search.getBranchId()+"'\n" +
                "\tAND j.tanggal_jurnal >='"+search.getStTanggalDari()+"'\n" +
                "\tAND j.tanggal_jurnal <'"+search.getStTanggalSelesai()+"'\n" +
                "\tAND j.registered_flag='Y'\n" +
                "\tAND (s.cancel_flag='Y' OR ps.pengajuan_setor_detail_id IS NULL)"+
                "\tAND jd.nomor_rekening ='"+CommonConstant.REKENING_PPH21+"'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Dokter KSO");
            data.setNote(row[4].toString());
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());

            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPPhPengajuan (PengajuanSetor search){
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "\tjd.master_id,\n" +
                "\tm.nama,\n" +
                "\tjd.jumlah_kredit,\n" +
                "\tjd.no_nota,\n" +
                "\tj.keterangan\n" +
                "FROM \n" +
                "\tit_akun_jurnal j\n" +
                "\tLEFT JOIN it_akun_jurnal_detail jd ON j.no_jurnal=jd.no_jurnal\n" +
                "\tLEFT JOIN im_akun_master m ON m.nomor_master = jd.master_id\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail ps ON ps.transaksi_id = jd.no_nota\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor s ON ps.pengajuan_setor_id = s.pengajuan_setor_id\n" +
                "WHERE\n" +
                "\tj.keterangan NOT ILIKE 'Pendapatan dokter%'\n" +
                "\tAND j.keterangan NOT ILIKE 'Pembayaran payrol%'\n" +
                "\tAND j.branch_id='"+search.getBranchId()+"'\n" +
                "\tAND j.tanggal_jurnal >='"+search.getStTanggalDari()+"'\n" +
                "\tAND j.tanggal_jurnal <'"+search.getStTanggalSelesai()+"'\n" +
                "\tAND j.registered_flag='Y'\n" +
                "\tAND (s.cancel_flag='Y' OR ps.pengajuan_setor_detail_id IS NULL)"+
                "\tAND jd.nomor_rekening = '"+ CommonConstant.REKENING_PPH21 +"'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Pengajuan Biaya PPH21");
            data.setNote(row[4].toString());
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());

            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            listOfResult.add(data);
        }
        return listOfResult;
    }

    public List<PengajuanSetorDetail> listPengajuanJasa (PengajuanSetor search){
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "\tjd.master_id,\n" +
                "\tm.nama,\n" +
                "\tjd.jumlah_kredit,\n" +
                "\tjd.no_nota,\n" +
                "\tj.keterangan\n" +
                "FROM \n" +
                "\tit_akun_jurnal j\n" +
                "\tLEFT JOIN it_akun_jurnal_detail jd ON j.no_jurnal = jd.no_jurnal\n" +
                "\tLEFT JOIN im_akun_master m ON m.nomor_master = jd.master_id\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail ps ON ps.transaksi_id = jd.no_nota\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor s ON ps.pengajuan_setor_id = s.pengajuan_setor_id\n" +
                "\tLEFT JOIN it_akun_pendaftaran_jasa js ON js.no_jurnal = j.no_jurnal\n" +
                "WHERE j.branch_id='"+search.getBranchId()+"'\n" +
                "\tAND j.tanggal_jurnal >='"+search.getStTanggalDari()+"'\n" +
                "\tAND j.tanggal_jurnal <'"+search.getStTanggalSelesai()+"'\n" +
                "\tAND j.registered_flag='Y'\n" +
                "\tAND (s.cancel_flag='Y' OR ps.pengajuan_setor_detail_id IS NULL)\n"+
                "\tAND jd.nomor_rekening = '"+ CommonConstant.REKENING_PPH21 +"'\n" +
                "GROUP BY jd.master_id,\n" +
                "\tm.nama,\n" +
                "\tjd.jumlah_kredit,\n" +
                "\tjd.no_nota,\n" +
                "\tj.keterangan";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTipe("Pengajuan Biaya PPH21");
            data.setNote(row[4].toString());
            data.setPersonId(row[0].toString());
            data.setNama(row[1].toString());

            if (row[2]!=null){
                data.setJumlah(BigDecimal.valueOf(Double.parseDouble(row[2].toString())));
            }else{
                data.setJumlah(BigDecimal.ZERO);
            }
            data.setTransaksiId(row[3].toString());

            listOfResult.add(data);
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
                "  AND j.tanggal_jurnal >= '"+search.getStTanggalDari()+"' \n" +
                "  AND j.tanggal_jurnal < '"+search.getStTanggalSelesai()+"' \n" +
                "  AND jd.nomor_rekening = '"+CommonConstant.REKENING_ID_PPN_KELUARAN+"' \n" +
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
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT \n" +
                "  jd.no_nota, \n" +
                "  j.sumber, \n" +
                "  jd.jumlah_debit, \n" +
                "  j.keterangan \n" +
                "FROM \n" +
                "  it_akun_jurnal j \n" +
                "  LEFT JOIN it_akun_jurnal_detail jd ON jd.no_jurnal = j.no_jurnal \n" +
                "  LEFT JOIN it_akun_pengajuan_setor_detail sd ON jd.no_nota = sd.transaksi_id \n" +
                "  LEFT JOIN it_akun_pengajuan_setor s ON s.pengajuan_setor_id = sd.pengajuan_setor_id \n" +
                "WHERE \n" +
                "  j.branch_id = '"+search.getBranchId()+"' \n" +
                "  AND j.registered_flag = 'Y' \n" +
                "  AND j.tanggal_jurnal >= '"+search.getStTanggalDari()+"' \n" +
                "  AND j.tanggal_jurnal < '"+search.getStTanggalSelesai()+"' \n" +
                "  AND jd.nomor_rekening = '"+CommonConstant.REKENING_ID_PPN_MASUKAN+"' \n" +
                "  AND (\n" +
                "    ( s.cancel_flag = 'Y' AND s.tipe_pengajuan_setor<>'PPH21' ) \n" +
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

    public List<ItPengajuanSetorEntity> getListPengajuanSetorByBulanDanTahun(String bulan,String tahun) throws HibernateException {
        List<ItPengajuanSetorEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("cancelFlag", "N"))
                .add(Restrictions.ne("branchId", CommonConstant.BRANCH_KP))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("tipePengajuanSetor", "PPN"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorId"))
                .list();
        return results;
    }
    public List<ItPengajuanSetorEntity> getListPengajuanSetorByBulanTahunDanBranch(String bulan,String tahun,String branchId) throws HibernateException {
        List<ItPengajuanSetorEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("cancelFlag", "N"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("tipePengajuanSetor", "PPN"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorId"))
                .list();
        return results;
    }

    public List<ItPengajuanSetorEntity> getListPengajuanSetorForBilling(String bulan,String tahun) throws HibernateException {
        List<ItPengajuanSetorEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("cancelFlag", "N"))
                .add(Restrictions.eq("approvalFlag", "Y"))
                .add(Restrictions.eq("tipePengajuanSetor", "PPN"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorId"))
                .list();
        return results;
    }

    public List<ItPengajuanSetorEntity> getListPengajuanSetorPpnByBulanDanTahunForPosting(String bulan,String tahun) throws HibernateException {
        List<ItPengajuanSetorEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorEntity.class)
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", bulan))
                .add(Restrictions.eq("cancelFlag", "N"))
                .add(Restrictions.eq("tipePengajuanSetor", "PPN"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorId"))
                .list();
        return results;
    }
}
