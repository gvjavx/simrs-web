package com.neurix.simrs.transaksi.psikososial.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.psikososial.model.ItSimrsDataPsikososialEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import sun.net.www.content.text.Generic;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/02/20.
 */
public class PsikososialDao extends GenericDao<ItSimrsDataPsikososialEntity, String> {

    @Override
    protected Class<ItSimrsDataPsikososialEntity> getEntityClass() {
        return ItSimrsDataPsikososialEntity.class;
    }

    @Override
    public List<ItSimrsDataPsikososialEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDataPsikososialEntity.class);

        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("no_checkup") != null){
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
        }

        List<ItSimrsDataPsikososialEntity> results = criteria.list();

        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_data_psikososial')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
