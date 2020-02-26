package com.neurix.simrs.transaksi.skorrawatinap.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.skorrawatinap.model.ImSimrsKategoriSkorRanapEntity;
import com.neurix.simrs.transaksi.skorrawatinap.model.ItSimrsSkorRanapEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 10/02/20.
 */
public class KategoriSkorRanapDao extends GenericDao<ImSimrsKategoriSkorRanapEntity, String> {
    @Override
    protected Class<ImSimrsKategoriSkorRanapEntity> getEntityClass() {
        return ImSimrsKategoriSkorRanapEntity.class;
    }

    @Override
    public List<ImSimrsKategoriSkorRanapEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriSkorRanapEntity.class);

        if (mapCriteria.get("id_kategori") != null)
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        if (mapCriteria.get("head") != null)
            criteria.add(Restrictions.eq("head", mapCriteria.get("head").toString()));

        List<ImSimrsKategoriSkorRanapEntity> results = criteria.list();
        return results;
    }
}
