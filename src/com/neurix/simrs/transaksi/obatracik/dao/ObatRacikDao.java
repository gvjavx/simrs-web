package com.neurix.simrs.transaksi.obatracik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.obatpoli.model.MtSimrsObatPoliEntity;
import com.neurix.simrs.transaksi.obatracik.model.ItSimrsObatRacikEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ObatRacikDao extends GenericDao<ItSimrsObatRacikEntity, String> {

    @Override
    protected Class<ItSimrsObatRacikEntity> getEntityClass() {
        return ItSimrsObatRacikEntity.class;
    }

    @Override
    public List<ItSimrsObatRacikEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsObatRacikEntity.class);

        if (mapCriteria.get("id") != null) {
            criteria.add(Restrictions.eq("id", mapCriteria.get("id")));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        return criteria.list();
    }

    public String getNextSeq() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat_racik')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "IRO"+sId;
    }
}
