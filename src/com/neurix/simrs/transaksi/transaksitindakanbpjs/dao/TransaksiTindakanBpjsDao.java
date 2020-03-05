package com.neurix.simrs.transaksi.transaksitindakanbpjs.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.transaksitindakanbpjs.model.ItSimrsTindakanBpjsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/03/20.
 */
public class TransaksiTindakanBpjsDao extends GenericDao<ItSimrsTindakanBpjsEntity, String>{
    @Override
    protected Class<ItSimrsTindakanBpjsEntity> getEntityClass() {
        return ItSimrsTindakanBpjsEntity.class;
    }

    @Override
    public List<ItSimrsTindakanBpjsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTindakanBpjsEntity.class);
        if (mapCriteria.get("id_tindakan_bpjs") != null)
            criteria.add(Restrictions.eq("idTindakanBpjs", mapCriteria.get("id_tindakan_bpjs").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        List<ItSimrsTindakanBpjsEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_tindakan_bpjs')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
