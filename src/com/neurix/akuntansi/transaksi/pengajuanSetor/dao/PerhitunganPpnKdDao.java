package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItAkunPerhitunganPpnKdEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetor;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PengajuanSetorDetail;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PerhitunganPpnKd;
import com.neurix.common.constant.CommonConstant;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PerhitunganPpnKdDao extends GenericDao<ItAkunPerhitunganPpnKdEntity, String> {

    @Override
    protected Class<ItAkunPerhitunganPpnKdEntity> getEntityClass() {
        return ItAkunPerhitunganPpnKdEntity.class;
    }

    @Override
    public List<ItAkunPerhitunganPpnKdEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public BigDecimal getPpnMasukanYangTelahDikreditkan(PengajuanSetor search){
        BigDecimal total = new BigDecimal(0);
        String query="select \n" +
                "\tsum(jumlah_ppn_masukan_b2) as jumlah_ppn_masukan_b2\n" +
                "from \n" +
                "\tit_akun_pengajuan_setor\n" +
                "WHERE\n" +
                "\tflag='Y'\n" +
                "\tAND cancel_flag='N'\n" +
                "\tAND approval_flag='Y'\n" +
                "\tAND tahun='"+search.getTahun()+"'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }

    public BigDecimal getPenyerahanYangTerutangPpn(PengajuanSetor search){
        BigDecimal total = new BigDecimal(0);
        String tahunSebelumnya = String.valueOf(Integer.parseInt(search.getTahun())-1);
        String query="SELECT\n" +
                "\tjumlah_kredit\n" +
                "FROM\n" +
                "\tit_akun_jurnal_detail jd\n" +
                "\tLEFT JOIN it_akun_jurnal j ON jd.no_jurnal = j.no_jurnal\n" +
                "WHERE\t\n" +
                "\trekening_id='"+ CommonConstant.REKENING_ID_PENDAPATAN_RJ +"'\n" +
                "\tAND divisi_id='"+CommonConstant.KODERING_FARMASI_RJ+"'\n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND tanggal_jurnal >='"+tahunSebelumnya+"-12-01'\n" +
                "\tAND tanggal_jurnal < '"+search.getTahun()+"-"+search.getBulan()+"-01'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }

    public BigDecimal getPenyerahanTidakTerutang(PengajuanSetor search){
        BigDecimal total = new BigDecimal(0);
        String tahunSebelumnya = String.valueOf(Integer.parseInt(search.getTahun())-1);
        String query="SELECT\n" +
                "\tjumlah_kredit\n" +
                "FROM\n" +
                "\tit_akun_jurnal_detail jd\n" +
                "\tLEFT JOIN it_akun_jurnal j ON jd.no_jurnal = j.no_jurnal\n" +
                "WHERE\t\n" +
                "\trekening_id='"+ CommonConstant.REKENING_ID_PENDAPATAN_RI +"'\n" +
                "\tAND divisi_id='"+CommonConstant.KODERING_FARMASI_RI+"'\n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND tanggal_jurnal >='"+tahunSebelumnya+"-12-01'\n" +
                "\tAND tanggal_jurnal < '"+search.getTahun()+"-"+search.getBulan()+"-01'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perhitungan_ppn_kd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PPK"+sId;
    }

    public List<PerhitunganPpnKd> getDataSearchHome(String bulan1, String tahun1, String bulan2, String tahun2){
        List<PerhitunganPpnKd> listOfResult = new ArrayList<PerhitunganPpnKd>();
        List<Object[]> results = new ArrayList<Object[]>();

        String query = "select\n" +
                "\tbulan,\n" +
                "\ttahun,\n" +
                "\tcancel_flag,\n" +
                "\tapproval_flag\n" +
                "from\n" +
                "\tit_akun_perhitungan_ppn_kd\n" +
                "WHERE bulan >= '"+bulan1+"'\n" +
                "AND bulan <= '"+bulan2+"'\n" +
                "AND tahun >= '"+tahun1+"'\n" +
                "AND tahun <= '"+tahun2+"'\n" +
                "AND flag = 'Y'\n" +
                "group by\n" +
                "\tbulan,\n" +
                "\ttahun,\n" +
                "\tcancel_flag,\n" +
                "\tapproval_flag\n";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PerhitunganPpnKd result  = new PerhitunganPpnKd();
            result.setBulan((String) row[0]);
            result.setTahun((String) row[1]);
            result.setCancelFlag((String) row[2]);
            result.setApprovalFlag((String) row[3]);

            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ItAkunPerhitunganPpnKdEntity> getPerhitunganPpnKdList(PerhitunganPpnKd perhitunganPpnKd,String tipe) throws HibernateException {
        List<ItAkunPerhitunganPpnKdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganPpnKdEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tipe", tipe))
                .add(Restrictions.eq("tahun", perhitunganPpnKd.getTahun()))
                .add(Restrictions.eq("bulan", perhitunganPpnKd.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }

    public List<ItAkunPerhitunganPpnKdEntity> getAllPerhitunganPpnKdList(PerhitunganPpnKd perhitunganPpnKd) throws HibernateException {
        List<ItAkunPerhitunganPpnKdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganPpnKdEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", perhitunganPpnKd.getTahun()))
                .add(Restrictions.eq("bulan", perhitunganPpnKd.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItAkunPerhitunganPpnKdEntity> getListUntukValidasi(PerhitunganPpnKd perhitunganPpnKd) throws HibernateException {
        List<ItAkunPerhitunganPpnKdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganPpnKdEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("cancelFlag", "N"))
                .add(Restrictions.eq("tahun", perhitunganPpnKd.getTahun()))
                .add(Restrictions.eq("bulan", perhitunganPpnKd.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

    public List<ItAkunPerhitunganPpnKdEntity> getForModalPosting(String bulan ,String tahun,String tipe) throws HibernateException {
        List<ItAkunPerhitunganPpnKdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganPpnKdEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tipe", tipe))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("bulan", bulan))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();
        return results;
    }

    public BigDecimal getJasaRs(PengajuanSetor search){
        BigDecimal total = new BigDecimal(0);
        String periodeSebelumnya = CommonUtil.periodeBulanSebelumnya(search.getBulan(),search.getTahun());
        String query="SELECT\n" +
                "\tjumlah_kredit\n" +
                "FROM\n" +
                "\tit_akun_jurnal_detail jd\n" +
                "\tLEFT JOIN it_akun_jurnal j ON jd.no_jurnal = j.no_jurnal\n" +
                "WHERE\t\n" +
                "\trekening_id='"+ CommonConstant.REKENING_ID_PENDAPATAN_RI +"'\n" +
                "\tAND divisi_id='"+CommonConstant.KODERING_INSTALASI_RI+"'\n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND tanggal_jurnal >='"+periodeSebelumnya+"-01'\n" +
                "\tAND tanggal_jurnal < '"+search.getTahun()+"-"+search.getBulan()+"-01'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }
    public BigDecimal getObatRi(PengajuanSetor search){
        BigDecimal total = new BigDecimal(0);
        String periodeSebelumnya = CommonUtil.periodeBulanSebelumnya(search.getBulan(),search.getTahun());
        String query="SELECT\n" +
                "\tjumlah_kredit\n" +
                "FROM\n" +
                "\tit_akun_jurnal_detail jd\n" +
                "\tLEFT JOIN it_akun_jurnal j ON jd.no_jurnal = j.no_jurnal\n" +
                "WHERE\t\n" +
                "\trekening_id='"+ CommonConstant.REKENING_ID_PENDAPATAN_RI +"'\n" +
                "\tAND divisi_id='"+CommonConstant.KODERING_FARMASI_RI+"'\n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND tanggal_jurnal >='"+periodeSebelumnya+"-01'\n" +
                "\tAND tanggal_jurnal < '"+search.getTahun()+"-"+search.getBulan()+"-01'";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            total = BigDecimal.valueOf(Double.parseDouble(results.toString()));
        }else{
            total = BigDecimal.ZERO;
        }
        return total;
    }
}
