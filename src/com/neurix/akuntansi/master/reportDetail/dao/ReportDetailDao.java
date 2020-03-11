package com.neurix.akuntansi.master.reportDetail.dao;

import com.neurix.akuntansi.master.reportDetail.model.ImReportDetailEntity;
import com.neurix.common.dao.GenericDao;
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
public class ReportDetailDao extends GenericDao<ImReportDetailEntity, String> {

    @Override
    protected Class<ImReportDetailEntity> getEntityClass() {
        return ImReportDetailEntity.class;
    }

    @Override
    public List<ImReportDetailEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImReportDetailEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("report_detail_id")!=null) {
                criteria.add(Restrictions.eq("reportDetailId", (String) mapCriteria.get("report_detail_id")));
            }
            if (mapCriteria.get("report_id")!=null) {
                criteria.add(Restrictions.eq("reportId", (String) mapCriteria.get("report_id")));
            }
            if (mapCriteria.get("tipe_laporan")!=null) {
                criteria.add(Restrictions.eq("tipeLaporan", (String) mapCriteria.get("tipe_laporan")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("reportDetailId"));

        List<ImReportDetailEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextReportDetailId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_report_detail')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "RD"+sId;
    }

    public List<ImReportDetailEntity> getReportDetailByReportIdNRekeningId(String reportId,String rekeningId) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImReportDetailEntity.class);
        criteria.add(Restrictions.eq("reportId", reportId));
        criteria.add(Restrictions.eq("rekeningId", rekeningId));
        // Order by
        criteria.addOrder(Order.asc("reportDetailId"));
        List<ImReportDetailEntity> results = criteria.list();
        return results;
    }
    public List<ImReportDetailEntity> getReportDetailByReportId(String reportId) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImReportDetailEntity.class);
        criteria.add(Restrictions.eq("reportId", reportId));
        // Order by
        criteria.addOrder(Order.asc("reportDetailId"));
        List<ImReportDetailEntity> results = criteria.list();
        return results;
    }
    public List<ImReportDetailEntity> getReportDetailByReportIdAndTipeLaporan(String reportId,String tipeLaporan) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImReportDetailEntity.class);
        criteria.add(Restrictions.eq("reportId", reportId));
        criteria.add(Restrictions.eq("tipeLaporan", tipeLaporan));
        criteria.addOrder(Order.asc("reportDetailId"));
        List<ImReportDetailEntity> results = criteria.list();
        return results;
    }
}
