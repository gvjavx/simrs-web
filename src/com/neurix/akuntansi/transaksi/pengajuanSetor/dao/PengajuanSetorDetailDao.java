package com.neurix.akuntansi.transaksi.pengajuanSetor.dao;

import com.neurix.akuntansi.transaksi.pengajuanSetor.model.ItPengajuanSetorDetailEntity;
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
public class PengajuanSetorDetailDao extends GenericDao<ItPengajuanSetorDetailEntity, String> {

    @Override
    protected Class<ItPengajuanSetorDetailEntity> getEntityClass() {
        return ItPengajuanSetorDetailEntity.class;
    }

    @Override
    public List<ItPengajuanSetorDetailEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    // Generate surrogate id from postgre
    public String getNextPengajuanSetorDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pengajuan_setor_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PSD"+sId;
    }

    public List<ItPengajuanSetorDetailEntity> getByPengajuanBiayaIdAndTipe(String id,String tipe) throws HibernateException {
        List<ItPengajuanSetorDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorDetailEntity.class)
                .add(Restrictions.eq("pengajuanSetorId", id))
                .add(Restrictions.eq("tipe", tipe))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorDetailId"))
                .list();
        return results;
    }

    public List<ItPengajuanSetorDetailEntity> getByPengajuanSetorId(String id) throws HibernateException {
        List<ItPengajuanSetorDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorDetailEntity.class)
                .add(Restrictions.eq("pengajuanSetorId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorDetailId"))
                .list();
        return results;
    }
    public List<ItPengajuanSetorDetailEntity> getByPengajuanSetorIdAndTipe(String id,String tipe) throws HibernateException {
        List<ItPengajuanSetorDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPengajuanSetorDetailEntity.class)
                .add(Restrictions.eq("pengajuanSetorId", id))
                .add(Restrictions.eq("tipe", tipe))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pengajuanSetorDetailId"))
                .list();
        return results;
    }

    public List<PengajuanSetorDetail> cekApakahSudahDibayarkan(String transaksiId,String tipe) throws HibernateException {
        List<PengajuanSetorDetail> listOfResult = new ArrayList<>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "SELECT\n" +
                "\tsd.transaksi_id,\n" +
                "\ts.tipe_pengajuan_setor\n" +
                "FROM\n" +
                "\tit_akun_pengajuan_setor s\n" +
                "\tLEFT JOIN it_akun_pengajuan_setor_detail sd ON s.pengajuan_setor_id = sd.pengajuan_setor_id\n" +
                "WHERE\n" +
                "\tsd.transaksi_id='"+transaksiId+"'\n" +
                "\tAND s.tipe_pengajuan_setor = '"+tipe+"'";
        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            PengajuanSetorDetail data= new PengajuanSetorDetail();
            data.setTransaksiId(row[0].toString());
            data.setTipe(row[1].toString());
            listOfResult.add(data);
        }
        return listOfResult;
    }
}
