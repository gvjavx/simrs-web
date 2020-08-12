package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsKandunganObatDetailEntity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 08/07/20.
 */
public class KandunganObatDetailDao extends GenericDao<ImSimrsKandunganObatDetailEntity, String> {

    @Override
    protected Class<ImSimrsKandunganObatDetailEntity> getEntityClass() {
        return ImSimrsKandunganObatDetailEntity.class;
    }

    @Override
    public List<ImSimrsKandunganObatDetailEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKandunganObatDetailEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("id_obat") != null)
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        if (mapCriteria.get("id_kandungan") != null)
            criteria.add(Restrictions.eq("idKandungan", mapCriteria.get("id_kandungan").toString()));

        return criteria.list();
    }


    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kandungan_obat_detail')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
