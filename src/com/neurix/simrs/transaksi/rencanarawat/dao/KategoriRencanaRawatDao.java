package com.neurix.simrs.transaksi.rencanarawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rencanarawat.model.ImSimrsKategoriRencanaRawatEntity;
import com.neurix.simrs.transaksi.rencanarawat.model.ItSimrsRencanaRawatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 07/02/20.
 */
public class KategoriRencanaRawatDao extends GenericDao<ImSimrsKategoriRencanaRawatEntity, String> {

    @Override
    protected Class<ImSimrsKategoriRencanaRawatEntity> getEntityClass() {
        return ImSimrsKategoriRencanaRawatEntity.class;
    }

    @Override
    public List<ImSimrsKategoriRencanaRawatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriRencanaRawatEntity.class);
        if (mapCriteria.get("id_kategori") != null){
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        }

        List<ImSimrsKategoriRencanaRawatEntity> results = criteria.list();
        return results;
    }
}
