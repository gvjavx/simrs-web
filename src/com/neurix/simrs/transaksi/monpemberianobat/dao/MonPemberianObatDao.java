package com.neurix.simrs.transaksi.monpemberianobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.monpemberianobat.model.ItSimrsMonPemberianObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/02/20.
 */
public class MonPemberianObatDao extends GenericDao<ItSimrsMonPemberianObatEntity, String>{
    @Override
    protected Class<ItSimrsMonPemberianObatEntity> getEntityClass() {
        return ItSimrsMonPemberianObatEntity.class;
    }

    @Override
    public List<ItSimrsMonPemberianObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMonPemberianObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            mapCriteria.get(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        List<ItSimrsMonPemberianObatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_mon_pemberian_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
