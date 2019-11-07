package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsTindakanEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TindakanDao extends GenericDao<ImSimrsTindakanEntity,String> {
    @Override
    protected Class<ImSimrsTindakanEntity> getEntityClass() {
        return ImSimrsTindakanEntity.class;
    }

    @Override
    public List<ImSimrsTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_tindakan") != null)
                criteria.add(Restrictions.eq("idTindakan", mapCriteria.get("id_tindakan").toString()));

        List<ImSimrsTindakanEntity> result = criteria.list();
        return result;
    }
}
