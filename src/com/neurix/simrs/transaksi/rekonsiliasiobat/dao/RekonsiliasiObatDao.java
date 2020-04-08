package com.neurix.simrs.transaksi.rekonsiliasiobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rekonsiliasiobat.model.ItSimrsRekonsiliasiObatEntity;
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
public class RekonsiliasiObatDao extends GenericDao<ItSimrsRekonsiliasiObatEntity, String> {
    @Override
    protected Class<ItSimrsRekonsiliasiObatEntity> getEntityClass() {
        return ItSimrsRekonsiliasiObatEntity.class;
    }

    @Override
    public List<ItSimrsRekonsiliasiObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRekonsiliasiObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));

        List<ItSimrsRekonsiliasiObatEntity> rekonsiliasiObatEntities = criteria.list();
        return rekonsiliasiObatEntities;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_rekonsiliasi_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
