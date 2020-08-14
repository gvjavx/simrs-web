package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsBentukBarangEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 13/07/20.
 */
public class BentukBarangDao extends GenericDao<ImSimrsBentukBarangEntity, String> {
    @Override
    protected Class<ImSimrsBentukBarangEntity> getEntityClass() {
        return ImSimrsBentukBarangEntity.class;
    }

    @Override
    public List<ImSimrsBentukBarangEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsBentukBarangEntity.class);
        if (mapCriteria.get("id_bentuk") != null)
            criteria.add(Restrictions.eq("idBentuk", mapCriteria.get("id_bentuk").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }
}
