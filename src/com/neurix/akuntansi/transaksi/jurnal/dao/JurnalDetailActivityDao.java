package com.neurix.akuntansi.transaksi.jurnal.dao;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailActivityEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalDetailActivityPendingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
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
public class JurnalDetailActivityDao extends GenericDao<ItJurnalDetailActivityEntity, String> {

    @Override
    protected Class<ItJurnalDetailActivityEntity> getEntityClass() {
        return ItJurnalDetailActivityEntity.class;
    }

    @Override
    public List<ItJurnalDetailActivityEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailActivityEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jurnal_detail_activity_id")!=null) {
                criteria.add(Restrictions.eq("jurnalDetailActivityId", (String) mapCriteria.get("jurnal_detail_activity_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("jurnalDetailActivityId"));
        List<ItJurnalDetailActivityEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJurnalActivityId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jurnal_activity')");
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return tahun+bulan+sId;
    }
    public void addAndSavePending(ItJurnalDetailActivityPendingEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
