package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 16/06/20.
 */
public class ResepOnlineDao extends GenericDao<ItSimrsResepOnlineEntity, String> {
    @Override
    protected Class<ItSimrsResepOnlineEntity> getEntityClass() {
        return ItSimrsResepOnlineEntity.class;
    }

    @Override
    public List<ItSimrsResepOnlineEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsResepOnlineEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_transaksi_online") != null)
            criteria.add(Restrictions.eq("idTransaksiOnline", mapCriteria.get("id_transaksi_online").toString()));
        if (mapCriteria.get("id_dokter") != null)
            criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_resep_online')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
