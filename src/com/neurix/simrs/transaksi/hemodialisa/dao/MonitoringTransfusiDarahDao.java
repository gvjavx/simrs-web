package com.neurix.simrs.transaksi.hemodialisa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.hemodialisa.model.ItSimrsMonitoringTransfusiDarahEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonitoringTransfusiDarahDao extends GenericDao<ItSimrsMonitoringTransfusiDarahEntity, String> {

    @Override
    protected Class<ItSimrsMonitoringTransfusiDarahEntity> getEntityClass() {
        return ItSimrsMonitoringTransfusiDarahEntity.class;
    }

    @Override
    public List<ItSimrsMonitoringTransfusiDarahEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonitoringTransfusiDarahEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_monitoring_transfusi_darah")!=null) {
                criteria.add(Restrictions.eq("idMonitoringTransfusiDarah", (String) mapCriteria.get("id_monitoring_transfusi_darah")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idMonitoringTransfusiDarah"));

        List<ItSimrsMonitoringTransfusiDarahEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_monitoring_transfusi_darah')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}