package com.neurix.simrs.transaksi.transfusi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transfusi.model.ItSimrsTranfusiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 08/02/20.
 */
public class TranfusiDao extends GenericDao<ItSimrsTranfusiEntity, String> {
    @Override
    protected Class<ItSimrsTranfusiEntity> getEntityClass() {
        return ItSimrsTranfusiEntity.class;
    }

    @Override
    public List<ItSimrsTranfusiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTranfusiEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));

        List<ItSimrsTranfusiEntity> tranfusiEntities = criteria.list();
        return tranfusiEntities;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_tranfusi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
