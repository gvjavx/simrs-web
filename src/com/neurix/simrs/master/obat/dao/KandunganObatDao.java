package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsKandunganObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 08/07/20.
 */
public class KandunganObatDao extends GenericDao<ImSimrsKandunganObatEntity, String> {

    @Override
    protected Class<ImSimrsKandunganObatEntity> getEntityClass() {
        return ImSimrsKandunganObatEntity.class;
    }

    @Override
    public List<ImSimrsKandunganObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKandunganObatEntity.class);

        if (mapCriteria.get("id_kandungan") != null)
            criteria.add(Restrictions.eq("idKandungan", mapCriteria.get("id_kandungan").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kandungan_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

    public List<ImSimrsKandunganObatEntity> checkData(String kandungan) throws HibernateException {

        List<ImSimrsKandunganObatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKandunganObatEntity.class)
                .add(Restrictions.ilike("kandungan", kandungan))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("idKandungan"))
                .list();

        return results;
    }
}
