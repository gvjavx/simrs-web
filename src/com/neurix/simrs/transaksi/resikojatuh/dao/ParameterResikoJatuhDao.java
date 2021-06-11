package com.neurix.simrs.transaksi.resikojatuh.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.resikojatuh.model.ImSimrsParameterResikoJatuhEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 05/02/20.
 */
public class ParameterResikoJatuhDao extends GenericDao<ImSimrsParameterResikoJatuhEntity, String> {
    @Override
    protected Class<ImSimrsParameterResikoJatuhEntity> getEntityClass() {
        return ImSimrsParameterResikoJatuhEntity.class;
    }

    @Override
    public List<ImSimrsParameterResikoJatuhEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterResikoJatuhEntity.class);

        if (mapCriteria.get("id_parameter") != null){
            criteria.add(Restrictions.eq("idParameter", mapCriteria.get("id_parameter").toString()));
        }
        if (mapCriteria.get("id_kategori") != null){
            criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
        }

        List<ImSimrsParameterResikoJatuhEntity> result = criteria.list();
        return result;
    }
}
