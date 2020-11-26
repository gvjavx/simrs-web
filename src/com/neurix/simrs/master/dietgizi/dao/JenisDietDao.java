package com.neurix.simrs.master.dietgizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsJenisDietEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class JenisDietDao extends GenericDao<ImSimrsJenisDietEntity, String> {

    @Override
    protected Class<ImSimrsJenisDietEntity> getEntityClass() {
        return ImSimrsJenisDietEntity.class;
    }

    public List<ImSimrsJenisDietEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisDietEntity.class);
        if (mapCriteria != null){

            if (mapCriteria.get("id_jenis_diet") != null){
                criteria.add(Restrictions.eq("idJenisDiet", mapCriteria.get("id_jenis_diet").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        }
        List<ImSimrsJenisDietEntity> result = criteria.list();
        return result;
    }
}
