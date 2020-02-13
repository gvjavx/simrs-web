package com.neurix.simrs.transaksi.monvitalsign.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.monvitalsign.model.ItSimrsMonVitalSignEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/02/20.
 */
public class MonVitalSignDao extends GenericDao<ItSimrsMonVitalSignEntity, String>{
    @Override
    protected Class<ItSimrsMonVitalSignEntity> getEntityClass() {
        return ItSimrsMonVitalSignEntity.class;
    }

    @Override
    public List<ItSimrsMonVitalSignEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonVitalSignEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            mapCriteria.get(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        criteria.addOrder(Order.desc("createdDate"));
        List<ItSimrsMonVitalSignEntity> resuts = criteria.list();
        return resuts;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_mon_vital_sign')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
