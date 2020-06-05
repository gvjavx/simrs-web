package com.neurix.akuntansi.transaksi.jurnal.dao;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalPendingEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class JurnalDao extends GenericDao<ItJurnalEntity, String> {

    @Override
    protected Class<ItJurnalEntity> getEntityClass() {
        return ItJurnalEntity.class;
    }

    @Override
    public List<ItJurnalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_jurnal_id")!=null) {
                criteria.add(Restrictions.eq("jurnalId", (String) mapCriteria.get("jurnal_id")));
            }
            if (mapCriteria.get("tipe_jurnal_name")!=null) {
                criteria.add(Restrictions.ilike("jurnalName", "%" + (String)mapCriteria.get("tipe_jurnal_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jurnalId"));
        List<ItJurnalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJurnalId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jurnal')");
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return tahun+bulan+sId;
    }
    public void addAndSavePending(ItJurnalPendingEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public BigDecimal getBudgetTerpakai (String branchId,String koderingPosisi,String bulan , String tahun , String rekeningId){
        BigDecimal total = new BigDecimal(0);
        String periode = bulan+"-"+tahun;
        String query="SELECT\n" +
                "\tsum(jumlah_debit)\n" +
                "FROM\n" +
                "\tit_akun_jurnal j LEFT JOIN\n" +
                "\tit_akun_jurnal_detail jd ON j.no_jurnal = jd.no_jurnal\n" +
                "WHERE\n" +
                "\trekening_id='"+rekeningId+"'\n" +
                "\tAND branch_id='"+branchId+"'\n" +
                "\tAND divisi_id='"+koderingPosisi+"'\n" +
                "\tAND registered_flag='Y'\n" +
                "\tAND j.flag='Y'\n" +
                "\tAND to_char(tanggal_jurnal, 'MM-yyyy') = '"+periode+"'";
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
