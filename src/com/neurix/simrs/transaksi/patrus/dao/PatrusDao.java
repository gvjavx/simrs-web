package com.neurix.simrs.transaksi.patrus.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.patrus.model.ItSImrsPatrusEntity;
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
public class PatrusDao extends GenericDao<ItSImrsPatrusEntity, String> {

    @Override
    protected Class<ItSImrsPatrusEntity> getEntityClass() {
        return ItSImrsPatrusEntity.class;
    }

    @Override
    public List<ItSImrsPatrusEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(ItSImrsPatrusEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("no_checkup") != null)
            criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));

        List<ItSImrsPatrusEntity> patrusEntities = criteria.list();
        return patrusEntities;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_patrus')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
