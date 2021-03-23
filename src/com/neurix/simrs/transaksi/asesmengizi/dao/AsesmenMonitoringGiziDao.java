package com.neurix.simrs.transaksi.asesmengizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.asesmengizi.model.ItSimrsAsesmenMonitoringGiziEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AsesmenMonitoringGiziDao extends GenericDao<ItSimrsAsesmenMonitoringGiziEntity, String> {

    @Override
    protected Class<ItSimrsAsesmenMonitoringGiziEntity> getEntityClass() {
        return ItSimrsAsesmenMonitoringGiziEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenMonitoringGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenMonitoringGiziEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_monitoring_gizi")!=null) {
                criteria.add(Restrictions.eq("idMonitoringGizi", (String) mapCriteria.get("id_monitoring_gizi")));
            }
            if (mapCriteria.get("no_checkup")!=null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idMonitoringGizi"));
        List<ItSimrsAsesmenMonitoringGiziEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen_gizi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "MOG"+sId;
    }
}