package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsPemesananResepOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 22/06/20.
 */
public class PemesananResepOnlineDao extends GenericDao<ItSimrsPemesananResepOnlineEntity, String> {

    @Override
    protected Class<ItSimrsPemesananResepOnlineEntity> getEntityClass() {
        return ItSimrsPemesananResepOnlineEntity.class;
    }

    @Override
    public List<ItSimrsPemesananResepOnlineEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPemesananResepOnlineEntity.class);
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_transaksi_online") != null)
            criteria.add(Restrictions.eq("idTransaksiOnline", mapCriteria.get("id_transaksi_online").toString()));

        return criteria.list();
    }
}
