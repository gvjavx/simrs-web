package com.neurix.akuntansi.transaksi.jurnal.dao;

import com.neurix.akuntansi.transaksi.jurnal.model.ItJurnalEntity;
import com.neurix.akuntansi.transaksi.jurnal.model.JurnalDetail;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
        String sId = String.format("%07d", iter.next());

        return "J"+tahun+bulan+sId;
    }
}
