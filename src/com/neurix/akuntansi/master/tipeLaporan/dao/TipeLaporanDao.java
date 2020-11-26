package com.neurix.akuntansi.master.tipeLaporan.dao;

import com.neurix.akuntansi.master.tipeLaporan.model.ImAkunTipeLaporanEntity;
import com.neurix.common.dao.GenericDao;
import com.neurix.akuntansi.master.tipeLaporan.model.ImAkunTipeLaporanEntity;
import com.neurix.akuntansi.master.tipeLaporan.model.ImAkunTipeLaporanHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
public class TipeLaporanDao extends GenericDao<ImAkunTipeLaporanEntity, String> {

    @Override
    protected Class<ImAkunTipeLaporanEntity> getEntityClass() {
        return ImAkunTipeLaporanEntity.class;
    }

    @Override
    public List<ImAkunTipeLaporanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImAkunTipeLaporanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tipe_laporan_id")!=null) {
                criteria.add(Restrictions.eq("tipeLaporanId", (String) mapCriteria.get("tipe_laporan_id")));
            }
            if (mapCriteria.get("tipe_laporan_name")!=null) {
                criteria.add(Restrictions.ilike("tipeLaporanName", "%" + (String)mapCriteria.get("tipe_laporan_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("tipeLaporanId"));
        List<ImAkunTipeLaporanEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTipeLaporanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_laporan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "TL"+sId;
    }

    public String getNextTipeLaporanHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tipe_laporan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HT"+sId;
    }

    public void addAndSaveHistory(ImAkunTipeLaporanHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImAkunTipeLaporanEntity> checkData(String tipeLaporanName) throws HibernateException {

        List<ImAkunTipeLaporanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImAkunTipeLaporanEntity.class)
                .add(Restrictions.eq("tipeLaporanName", tipeLaporanName))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("tipeLaporanId"))
                .list();

        return results;
    }
}
