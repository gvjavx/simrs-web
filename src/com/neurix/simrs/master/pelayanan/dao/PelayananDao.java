package com.neurix.simrs.master.pelayanan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pelayanan.model.ImSimrsPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class PelayananDao extends GenericDao {
    @Override
    protected Class getEntityClass() {
        return ImSimrsPelayananEntity.class;
    }

    @Override
    public List getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPelayananEntity.class);

        if (mapCriteria != null){

            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }

        }

        List<ImSimrsPelayananEntity> result = criteria.list();
        return result;
    }
}
