package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.ItHrisAbsensiOnCallEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class AbsensiOnCallDao extends GenericDao<ItHrisAbsensiOnCallEntity, String> {

    @Override
    protected Class<ItHrisAbsensiOnCallEntity> getEntityClass() {
        return ItHrisAbsensiOnCallEntity.class;
    }

    @Override
    public List<ItHrisAbsensiOnCallEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisAbsensiOnCallEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("absensi_on_call_id")!=null) {
                criteria.add(Restrictions.eq("absensiOnCallId", (String) mapCriteria.get("absensi_on_call_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("absensiOnCallId"));

        List<ItHrisAbsensiOnCallEntity> results = criteria.list();

        return results;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_absensi_on_call')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "AOC"+sId;
    }

    public List<ItHrisAbsensiOnCallEntity> getByTanggalAndNip(String nip, Date tanggal) throws HibernateException {
        List<ItHrisAbsensiOnCallEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisAbsensiOnCallEntity.class)
                .add(Restrictions.eq("tanggal",tanggal))
                .add(Restrictions.eq("nip",nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("jamMasuk"))
                .list();
        return results;
    }
}
