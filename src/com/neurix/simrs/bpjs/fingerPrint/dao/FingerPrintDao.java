package com.neurix.simrs.bpjs.fingerPrint.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.bpjs.fingerPrint.model.ImFingerPrintEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FingerPrintDao extends GenericDao<ImFingerPrintEntity, String> {
    @Override
    protected Class<ImFingerPrintEntity> getEntityClass() {
        return ImFingerPrintEntity.class;
    }

    @Override
    public List<ImFingerPrintEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImFingerPrintEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_tindakan")!=null) {
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idTindakan"));

        List<ImFingerPrintEntity> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextTindakanBpjsId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_simrs_tindakan_bpjs')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TDB" + sId;
    }
}