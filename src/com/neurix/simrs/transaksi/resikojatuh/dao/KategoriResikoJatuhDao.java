package com.neurix.simrs.transaksi.resikojatuh.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.resikojatuh.model.ImSimrsKategoriResikoJatuhEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class KategoriResikoJatuhDao extends GenericDao<ImSimrsKategoriResikoJatuhEntity, String> {

    @Override
    protected Class<ImSimrsKategoriResikoJatuhEntity> getEntityClass() {
        return ImSimrsKategoriResikoJatuhEntity.class;
    }

    @Override
    public List<ImSimrsKategoriResikoJatuhEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriResikoJatuhEntity.class);
        if (mapCriteria.get("id_kategori") != null){
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        }
        if (mapCriteria.get("tahun") != null){
            Integer tahun = (Integer) mapCriteria.get("tahun");
            criteria.add(Restrictions.sqlRestriction("umur_dari >= '"+tahun+"' AND umur_sampai <= '"+tahun+"'"));
        }

        List<ImSimrsKategoriResikoJatuhEntity> result = criteria.list();
        return result;
    }
}
