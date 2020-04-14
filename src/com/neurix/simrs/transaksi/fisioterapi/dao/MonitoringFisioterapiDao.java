package com.neurix.simrs.transaksi.fisioterapi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsMonitoringFisioterapiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonitoringFisioterapiDao extends GenericDao<ItSimrsMonitoringFisioterapiEntity, String> {

    @Override
    protected Class<ItSimrsMonitoringFisioterapiEntity> getEntityClass() {
        return ItSimrsMonitoringFisioterapiEntity.class;
    }

    @Override
    public List<ItSimrsMonitoringFisioterapiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonitoringFisioterapiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_monitoring_fisioterapi")!=null) {
                criteria.add(Restrictions.eq("idMonitoringFisioterapi", (String) mapCriteria.get("id_monitoring_fisioterapi")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idMonitoringFisioterapi"));

        List<ItSimrsMonitoringFisioterapiEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mon_fisioterapi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}