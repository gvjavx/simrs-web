package com.neurix.akuntansi.transaksi.pengajuanBiaya.dao;

import com.neurix.akuntansi.transaksi.pengajuanBiaya.model.ItPengajuanBiayaDetailEntity;
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
public class PengajuanBiayaDetailDao extends GenericDao<ItPengajuanBiayaDetailEntity, String> {

    @Override
    protected Class<ItPengajuanBiayaDetailEntity> getEntityClass() {
        return ItPengajuanBiayaDetailEntity.class;
    }

    @Override
    public List<ItPengajuanBiayaDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria.get("pengajuan_biaya_id")!=null) {
            criteria.add(Restrictions.eq("pengajuanBiayaId", (String) mapCriteria.get("pengajuan_biaya_id")));
        }
        if (mapCriteria.get("pengajuan_biaya_detail_id")!=null) {
            criteria.add(Restrictions.eq("pengajuanBiayaDetailId", (String) mapCriteria.get("pengajuan_biaya_detail_id")));
        }
        if (mapCriteria.get("branch_id")!=null) {
            criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
        }
        if (mapCriteria.get("divisi_id")!=null) {
            criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
        }
        if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
            criteria.add(Restrictions.between("tanggal",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
        }
        else {
            if (mapCriteria.get("tanggal_dari")!=null) {
                criteria.add(Restrictions.ge("tanggal",mapCriteria.get("tanggal_dari")));
            }
            if (mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal_selesai")));
            }
        }

        if (mapCriteria.get("tanggal_dari_realisasi")!=null && mapCriteria.get("tanggal_selesai_realisasi")!=null) {
            criteria.add(Restrictions.between("tanggalRealisasi",mapCriteria.get("tanggal_dari_realisasi"),mapCriteria.get("tanggal_selesai_realisasi")));
        }
        else {
            if (mapCriteria.get("tanggal_dari_realisasi")!=null) {
                criteria.add(Restrictions.ge("tanggalRealisasi",mapCriteria.get("tanggal_dari_realisasi")));
            }
            if (mapCriteria.get("tanggal_selesai_realisasi")!=null) {
                criteria.add(Restrictions.le("tanggalRealisasi",mapCriteria.get("tanggal_selesai_realisasi")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("pengajuanBiayaDetailId"));
        List<ItPengajuanBiayaDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanBiayaDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_biaya_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PBD"+sId;
    }


    public List<ItPengajuanBiayaDetailEntity> getListMasihMengajukan(String branchId, String divisiId) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("divisiId", divisiId))
                .add(Restrictions.isNull("closed"))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getByPengajuanBiayaId(String id) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("pengajuanBiayaId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getDetailPengajuanForRk(String id) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.eq("pengajuanBiayaId", id))
                .add(Restrictions.eq("approvalKeuanganKpFlag", "Y"))
                .add(Restrictions.eq("statusKeuangan", "KP"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }
    public List<ItPengajuanBiayaDetailEntity> getListPengajuanBiayaDetailForKasKeluar(String id) throws HibernateException {
        List<ItPengajuanBiayaDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanBiayaDetailEntity.class)
                .add(Restrictions.ilike("pengajuanBiayaDetailId", "%"+id+"%"))
                .add(Restrictions.eq("approvalKeuanganFlag", "Y"))
                .add(Restrictions.eq("diterimaFlag", "Y"))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("pengajuanBiayaDetailId"))
                .list();
        return results;
    }

    public List<ItPengajuanBiayaDetailEntity> getPengajuanBiayaMenggantung(String branchId) {
        List<ItPengajuanBiayaDetailEntity> listOfResult = new ArrayList<ItPengajuanBiayaDetailEntity>();
        List<Object[]> results = new ArrayList<Object[]>();

            String query = "select\n" +
                    "\tbd.*\n" +
                    "from\n" +
                    "\tit_akun_pengajuan_biaya_detail bd\n" +
                    "\tLEFT JOIN it_akun_jurnal j ON bd.pengajuan_biaya_detail_id = j.pengajuan_biaya_id\n" +
                    "WHERE \n" +
                    "\tbd.branch_id = '"+branchId+"'\n" +
                    "\tAND bd.diterima_flag='Y'\n" +
                    "\tAND j.pengajuan_biaya_id IS NULL\n" +
                    "\tAND bd.tanggal_realisasi <= now()\n" +
                    "\tAND closed='Y'";

            results = this.sessionFactory.getCurrentSession()
                    .createSQLQuery(query)
                    .list();

            for (Object[] row : results) {
                ItPengajuanBiayaDetailEntity pengajuanBiayaDetailEntity = new ItPengajuanBiayaDetailEntity();
                pengajuanBiayaDetailEntity.setPengajuanBiayaDetailId((String) row[0]);
                pengajuanBiayaDetailEntity.setKeperluan((String) row[6]);
                pengajuanBiayaDetailEntity.setTanggalRealisasi((Date) row[40]);
                pengajuanBiayaDetailEntity.setDivisiId((String) row[3]);
                listOfResult.add(pengajuanBiayaDetailEntity);
            }
        return listOfResult;
    }

    public BigDecimal getBudgetTerpakaiPadaPengajuan (String branchId,String divisiId,String bulan , String tahun , String noBudgeting){
        BigDecimal total = new BigDecimal(0);
        String periode = bulan+"-"+tahun;
        String query="SELECT \n" +
                "\tsum(jumlah)\n" +
                "FROM\n" +
                "\tit_akun_pengajuan_biaya_detail\n" +
                "WHERE\n" +
                "\tflag='Y'\n" +
                "\tAND branch_id='"+branchId+"'\n" +
                "\tAND divisi_id='"+divisiId+"'\n" +
                "\tAND no_budgeting ilike '%"+noBudgeting+"'\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) = to_date('"+periode+"','MM-YYYY') \n" +
                "\tAND closed='Y'\n" +
                "\tAND approval_keuangan_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
    public BigDecimal getBudgetTerpakaiPadaPengajuanSdBulanIni (String branchId,String divisiId,String bulan , String tahun , String noBudgeting){
        BigDecimal total = new BigDecimal(0);
        Integer tahunSekarang = Integer.valueOf(tahun);
        String periode = bulan+"-"+tahun;
        String periodeSebelumnya = "12-"+String.valueOf(tahunSekarang-1);
        String query="SELECT \n" +
                "\tsum(jumlah)\n" +
                "FROM\n" +
                "\tit_akun_pengajuan_biaya_detail\n" +
                "WHERE\n" +
                "\tflag='Y'\n" +
                "\tAND branch_id='"+branchId+"'\n" +
                "\tAND divisi_id='"+divisiId+"'\n" +
                "\tAND no_budgeting ilike '%"+noBudgeting+"'\n" +
                "\tAND closed='Y'\n" +
                "\tAND approval_keuangan_flag='Y'\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) <= to_date('"+periode+"','MM-YYYY')\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) > to_date('"+periodeSebelumnya+"','MM-YYYY')";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public BigDecimal getPengadaanTerpakaiPadaPengajuan (String branchId,String divisiId,String bulan , String tahun , String idPengadaan){
        BigDecimal total = new BigDecimal(0);
        String periode = bulan+"-"+tahun;
        String query="SELECT \n" +
                "\tsum(jumlah)\n" +
                "FROM\n" +
                "\tit_akun_pengajuan_biaya_detail\n" +
                "WHERE\n" +
                "\tflag='Y'\n" +
                "\tAND branch_id='"+branchId+"'\n" +
                "\tAND divisi_id='"+divisiId+"'\n" +
                "\tAND keperluan_id = '"+idPengadaan+"'\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) = to_date('"+periode+"','MM-YYYY') \n" +
                "\tAND closed='Y'\n" +
                "\tAND approval_keuangan_flag='Y'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }

    public BigDecimal getPengadaanTerpakaiPadaPengajuanSdBulanIni (String branchId,String divisiId,String bulan , String tahun , String idPengadaan){
        BigDecimal total = new BigDecimal(0);
        Integer tahunSekarang = Integer.valueOf(tahun);
        String periode = bulan+"-"+tahun;
        String periodeSebelumnya = "12-"+String.valueOf(tahunSekarang-1);
        String query="SELECT \n" +
                "\tsum(jumlah)\n" +
                "FROM\n" +
                "\tit_akun_pengajuan_biaya_detail\n" +
                "WHERE\n" +
                "\tflag='Y'\n" +
                "\tAND branch_id='"+branchId+"'\n" +
                "\tAND divisi_id='"+divisiId+"'\n" +
                "\tAND keperluan_id = '"+idPengadaan+"'\n" +
                "\tAND closed='Y'\n" +
                "\tAND approval_keuangan_flag='Y'\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) <= to_date('"+periode+"','MM-YYYY')\n" +
                "\tAND cast(date_trunc('month', tanggal_realisasi) as date) > to_date('"+periodeSebelumnya+"','MM-YYYY')";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.valueOf(0);
        }
        return total;
    }
}
