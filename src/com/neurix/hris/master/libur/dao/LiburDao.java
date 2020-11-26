package com.neurix.hris.master.libur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.common.util.CommonUtil;
import com.neurix.hris.master.libur.model.ImLiburEntity;
import com.neurix.hris.master.libur.model.ImLiburHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
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
public class LiburDao extends GenericDao<ImLiburEntity, String> {

    @Override
    protected Class<ImLiburEntity> getEntityClass() {
        return ImLiburEntity.class;
    }

    @Override
    public List<ImLiburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImLiburEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("libur_id")!=null) {
                criteria.add(Restrictions.ilike("liburId","%" + (String) mapCriteria.get("libur_id")+ "%"));
            }
            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.ilike("liburTahun", "%" + (String)mapCriteria.get("tahun") + "%"));
            }
            if (mapCriteria.get("tanggal")!=null) {
                criteria.add(Restrictions.eq("tanggal", mapCriteria.get("tanggal")));
            }
            if (mapCriteria.get("tipe_libur_id")!=null) {
                criteria.add(Restrictions.ilike("tipeLiburId", "%" + (String)mapCriteria.get("tipe_libur_id") + "%"));
            }

            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.ilike("liburKeterangan", "%" + (String)mapCriteria.get("keterangan") + "%"));
            }
//            if (mapCriteria.get("tanggal")!=null) {
//                criteria.add(Restrictions.le("tanggal",mapCriteria.get("tanggal")));
//            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("liburId"));

        List<ImLiburEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextLiburId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_libur')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "L"+sId;
    }

    public List<ImLiburEntity> getListLibur(Date term) throws HibernateException {
        String tanggal = CommonUtil.convertDateToString(term);
        Date date = CommonUtil.convertStringToDate(tanggal);
        Timestamp tanggalNew = new Timestamp(date.getTime());
        List<ImLiburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImLiburEntity.class)
                .add(Restrictions.eq("tanggal",tanggalNew))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("liburId"))
                .list();

        return results;
    }
    public List<ImLiburEntity> getLiburRange(Date tanggalAwal,Date tanggalAkhir) throws HibernateException {
        Timestamp tanggalAwalNew = new Timestamp(tanggalAwal.getTime());
        Timestamp tanggalAkhirNew = new Timestamp(tanggalAkhir.getTime());
        List<ImLiburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImLiburEntity.class)
                .add(Restrictions.ge("tanggal",tanggalAwalNew))
                .add(Restrictions.le("tanggal",tanggalAkhirNew))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("liburId"))
                .list();

        return results;
    }

    public String getNextLiburHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_libur_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "LH"+sId;
    }

    public List<ImLiburEntity> getLiburByDate(java.util.Date date) throws HibernateException {
        Timestamp tanggal = new Timestamp(date.getTime());
        List<ImLiburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImLiburEntity.class)
                .add(Restrictions.eq("tanggal",tanggal))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("liburId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImLiburHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
