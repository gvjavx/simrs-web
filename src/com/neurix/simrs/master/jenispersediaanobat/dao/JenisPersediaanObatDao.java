package com.neurix.simrs.master.jenispersediaanobat.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JenisPersediaanObatDao extends GenericDao<ImSimrsJenisPersediaanObatEntity, String>{

    @Override
    protected Class<ImSimrsJenisPersediaanObatEntity> getEntityClass() {
        return ImSimrsJenisPersediaanObatEntity.class;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisPersediaanObatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        return criteria.list();
    }

    public List<ImSimrsJenisPersediaanObatEntity> getJenisPersediaanObat(String nama ) throws HibernateException {
        List<ImSimrsJenisPersediaanObatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisPersediaanObatEntity.class)
                .add(Restrictions.ilike("nama", nama))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_persediaan_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "JPO"+sId;
    }
}
