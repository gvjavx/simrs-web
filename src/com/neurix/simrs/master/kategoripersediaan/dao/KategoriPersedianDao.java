package com.neurix.simrs.master.kategoripersediaan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategoripersediaan.model.ImSimrsKategoriPersediaanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 13/07/20.
 */
public class KategoriPersedianDao extends GenericDao<ImSimrsKategoriPersediaanEntity, String> {
    @Override
    protected Class<ImSimrsKategoriPersediaanEntity> getEntityClass() {
        return ImSimrsKategoriPersediaanEntity.class;
    }

    @Override
    public List<ImSimrsKategoriPersediaanEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriPersediaanEntity.class);
        if (mapCriteria.get("id_kategori_persediaan") != null)
            criteria.add(Restrictions.eq("idKategoriPersediaan", mapCriteria.get("id_kategori_persediaan").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kategori_persediaan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
