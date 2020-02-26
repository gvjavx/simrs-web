package com.neurix.simrs.transaksi.rencanarawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/02/20.
 */
public class RencanaRawatDao extends GenericDao<ItSimrsRencanaRawatEntity, String> {

    @Override
    protected Class<ItSimrsRencanaRawatEntity> getEntityClass() {
        return ItSimrsRencanaRawatEntity.class;
    }

    @Override
    public List<ItSimrsRencanaRawatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRencanaRawatEntity.class);
        if (mapCriteria.get("id_rencana") != null){
            criteria.add(Restrictions.eq("idRencana", mapCriteria.get("id_rencana").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }

        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }

        criteria.addOrder(Order.asc("idRencana"));
        List<ItSimrsRencanaRawatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_rencana_rawat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
