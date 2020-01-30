package com.neurix.simrs.transaksi.hargaobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.hargaobat.model.MtSimrsHargaObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 23/01/20.
 */
public class HargaObatDao extends GenericDao<MtSimrsHargaObatEntity, String> {

    @Override
    protected Class<MtSimrsHargaObatEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<MtSimrsHargaObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsHargaObatEntity.class);
        if (mapCriteria.get("id_obat") != null){
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        }
        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<MtSimrsHargaObatEntity> results = criteria.list();
        return results;
    }
}
