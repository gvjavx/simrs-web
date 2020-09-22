package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItAkunPerhitunganKembaliUnitEntity;
import com.neurix.akuntansi.transaksi.pengajuanSetor.model.PerhitunganKembaliUnit;
import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
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
public class PerhitunganKembaliUnitDao extends GenericDao<ItAkunPerhitunganKembaliUnitEntity, String> {

    @Override
    protected Class<ItAkunPerhitunganKembaliUnitEntity> getEntityClass() {
        return ItAkunPerhitunganKembaliUnitEntity.class;
    }

    @Override
    public List<ItAkunPerhitunganKembaliUnitEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perhitungan_kembali_unit')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PKU"+sId;
    }

    public ItAkunPerhitunganKembaliUnitEntity getPerhitunganKembaliPadaBulanSebelumnya(PerhitunganKembaliUnit search) throws HibernateException {
        String bulanSebelumnya = CommonUtil.bulanSebelumnya(search.getBulan());

        ItAkunPerhitunganKembaliUnitEntity result = new ItAkunPerhitunganKembaliUnitEntity();
        List<ItAkunPerhitunganKembaliUnitEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganKembaliUnitEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", search.getTahun()))
                .add(Restrictions.eq("bulan", bulanSebelumnya))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        if (results.size()!=0){
            for (ItAkunPerhitunganKembaliUnitEntity perhitunganKembaliEntity : results){
                result.setPerhitunganPmYmhDibuku(perhitunganKembaliEntity.getPerhitunganPmYmhDibuku());
                result.setPerhitunganPmSudahDibuku(perhitunganKembaliEntity.getPerhitunganPmSudahDibuku());
            }
        }else{
            //inisialisasi
            result.setPerhitunganPmYmhDibuku(BigDecimal.ZERO);
            result.setPerhitunganPmSudahDibuku(BigDecimal.ZERO);
        }

        return result;
    }

    public List<ItAkunPerhitunganKembaliUnitEntity> getPerhitunganKembaliPadaBulan(PerhitunganKembaliUnit search) throws HibernateException {
        List<ItAkunPerhitunganKembaliUnitEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganKembaliUnitEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", search.getTahun()))
                .add(Restrictions.eq("bulan", search.getBulan()))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public List<ItAkunPerhitunganKembaliUnitEntity> getPerhitunganKembaliPadaBulanDanUnit(PerhitunganKembaliUnit search) throws HibernateException {
        List<ItAkunPerhitunganKembaliUnitEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItAkunPerhitunganKembaliUnitEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("tahun", search.getTahun()))
                .add(Restrictions.eq("bulan", search.getBulan()))
                .add(Restrictions.eq("branchId", search.getBranchId()))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }
}
