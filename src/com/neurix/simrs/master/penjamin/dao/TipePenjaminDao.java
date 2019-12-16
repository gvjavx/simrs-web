package com.neurix.simrs.master.penjamin.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.penjamin.model.ImSimrsTipePenjaminEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TipePenjaminDao extends GenericDao<ImSimrsTipePenjaminEntity, String>{
    @Override
    protected Class<ImSimrsTipePenjaminEntity> getEntityClass() {
        return ImSimrsTipePenjaminEntity.class;
    }

    @Override
    public List<ImSimrsTipePenjaminEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTipePenjaminEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_tipe_penjamin") != null)
                criteria.add(Restrictions.eq("idTipePenjamin", mapCriteria.get("id_tipe_penjamin").toString()));

        List<ImSimrsTipePenjaminEntity> result = criteria.list();
        return result;
    }
}
