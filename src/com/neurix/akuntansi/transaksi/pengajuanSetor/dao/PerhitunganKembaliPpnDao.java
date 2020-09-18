package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItAkunPpnPerhitunganKembaliEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PerhitunganKembaliPpn;
import com.neurix.common.dao.GenericDao;
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
public class PerhitunganKembaliPpnDao extends GenericDao<ItAkunPpnPerhitunganKembaliEntity, String> {

    @Override
    protected Class<ItAkunPpnPerhitunganKembaliEntity> getEntityClass() {
        return ItAkunPpnPerhitunganKembaliEntity.class;
    }

    @Override
    public List<ItAkunPpnPerhitunganKembaliEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perhitungan_kembali')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PK"+sId;
    }

    public ItAkunPpnPerhitunganKembaliEntity getPerhitunganKembaliPadaBulanLalu(PerhitunganKembaliPpn search) throws HibernateException {
        ItAkunPpnPerhitunganKembaliEntity result = new ItAkunPpnPerhitunganKembaliEntity();
        List<ItAkunPpnPerhitunganKembaliEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPpnPerhitunganKembaliEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", search.getTahun()))
                .add(Restrictions.eq("bulan", search.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        if (results.size()!=0){
            for (ItAkunPpnPerhitunganKembaliEntity perhitunganKembaliEntity : results){
                result.setPpnTelahDiperhitungkanKembali(perhitunganKembaliEntity.getPpnTelahDiperhitungkanKembali());
                result.setPpnHasilPerhitunganKembaliPpn(perhitunganKembaliEntity.getPpnHasilPerhitunganKembaliPpn());
            }
        }else{
            //inisialisasi
            result.setPpnTelahDiperhitungkanKembali(BigDecimal.ZERO);
            result.setPpnHasilPerhitunganKembaliPpn(BigDecimal.ZERO);
        }

        return result;
    }
    public List<ItAkunPpnPerhitunganKembaliEntity> getPerhitunganKembaliPadaBulan(PerhitunganKembaliPpn search) throws HibernateException {
        List<ItAkunPpnPerhitunganKembaliEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPpnPerhitunganKembaliEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", search.getTahun()))
                .add(Restrictions.eq("bulan", search.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public BigDecimal getPpnHasilPerhitunganKembali(String bulan,String tahun){
        BigDecimal result=BigDecimal.ZERO;
        String query = "select \n" +
                "  ppn_hasil_perhitungan_kembali_ppn \n" +
                "from \n" +
                "  it_akun_ppn_perhitungan_kembali \n" +
                "where \n" +
                "  bulan = '"+bulan+"' \n" +
                "  and tahun = '"+tahun+"' \n" +
                "  and flag = 'Y'\n";
        Object results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query).uniqueResult();
        if (results!=null){
            result = (BigDecimal) results;
        }

        return result;
    }
}
