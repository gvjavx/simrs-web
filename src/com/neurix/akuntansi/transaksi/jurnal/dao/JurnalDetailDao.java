package com.neurix.akuntansi.transaksi.jurnal.dao;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailPendingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class JurnalDetailDao extends GenericDao<ItJurnalDetailEntity, String> {

    @Override
    protected Class<ItJurnalDetailEntity> getEntityClass() {
        return ItJurnalDetailEntity.class;
    }

    @Override
    public List<ItJurnalDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rekening_id")!=null) {
                criteria.add(Restrictions.eq("rekeningId", (String) mapCriteria.get("rekening_id")));
            }
            if (mapCriteria.get("no_nota")!=null) {
                criteria.add(Restrictions.eq("noNota", (String) mapCriteria.get("no_nota")));
            }
            if (mapCriteria.get("master_id")!=null) {
                criteria.add(Restrictions.eq("masterId", (String) mapCriteria.get("master_id")));
            }
            if (mapCriteria.get("no_jurnal")!=null) {
                criteria.add(Restrictions.eq("noJurnal", (String) mapCriteria.get("no_jurnal")));
            }
            if (mapCriteria.get("jurnal_detail_id")!=null) {
                criteria.add(Restrictions.eq("jurnalDetailId", (String) mapCriteria.get("jurnal_detail_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("jurnalDetailId"));

        List<ItJurnalDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJurnalDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jurnal_detail')");
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JD"+tahun+bulan+sId;
    }

    public List<ItJurnalDetailEntity> getListJurnalDetailDuplicate(String rekeningId, String noNota, String masterId, BigDecimal jumlahDebit,BigDecimal jumlahKredit){
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailEntity.class);
        criteria.add(Restrictions.eq("rekeningId", rekeningId));
        criteria.add(Restrictions.eq("noNota", noNota));
        criteria.add(Restrictions.eq("masterId", masterId));
        criteria.add(Restrictions.eq("jumlahDebit", jumlahDebit));
        criteria.add(Restrictions.eq("jumlahKredit", jumlahKredit));

        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("jurnalDetailId"));

        List<ItJurnalDetailEntity> results = criteria.list();

        return results;
    }

    public List<ItJurnalDetailEntity> getListJurnalDetailByTipeJurnalId(String id) throws HibernateException {

        List<ItJurnalDetailEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailEntity.class)
                .add(Restrictions.eq("tipeJurnalId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jurnalDetailId"))
                .list();
        return results;
    }
    public void addAndSavePending(ItJurnalDetailPendingEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
