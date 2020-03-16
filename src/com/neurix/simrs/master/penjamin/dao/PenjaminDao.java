package com.neurix.simrs.master.penjamin.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.penjamin.model.ImSimrsPenjaminEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PenjaminDao extends GenericDao<ImSimrsPenjaminEntity, String> {
    @Override
    protected Class<ImSimrsPenjaminEntity> getEntityClass() {
        return ImSimrsPenjaminEntity.class;
    }

    @Override
    public List<ImSimrsPenjaminEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPenjaminEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_penjamin") != null)
                criteria.add(Restrictions.eq("idPenjamin", mapCriteria.get("id_penjamin").toString()));
            if (mapCriteria.get("id_tipe_penjamin") != null)
                criteria.add(Restrictions.eq("idTipePenjamin", mapCriteria.get("id_tipe_penjamin").toString()));

        List<ImSimrsPenjaminEntity> result = criteria.list();
        return result;
    }
}
