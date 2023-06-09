package com.neurix.akuntansi.transaksi.jurnal.dao.pending;

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
public class JurnalDetailPendingDao extends GenericDao<ItJurnalDetailPendingEntity, String> {

    @Override
    protected Class<ItJurnalDetailPendingEntity> getEntityClass() {
        return ItJurnalDetailPendingEntity.class;
    }

    @Override
    public List<ItJurnalDetailPendingEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItJurnalDetailPendingEntity.class);

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

        List<ItJurnalDetailPendingEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJurnalDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jurnal_detail')");
        Date date = new Date(new java.util.Date().getTime());
        DateFormat dfTahun = new SimpleDateFormat("yy");
        DateFormat dfBulan = new SimpleDateFormat("MM");
        String tahun = dfTahun.format(date);
        String bulan = dfBulan.format(date);
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JD"+tahun+bulan+sId;
    }
}
