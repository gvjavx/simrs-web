package com.neurix.simrs.master.diagnosa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.diagnosa.model.ImSimrsDiagnosaEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class DiagnosaDao extends GenericDao<ImSimrsDiagnosaEntity, String> {
    @Override
    protected Class<ImSimrsDiagnosaEntity> getEntityClass() {
        return ImSimrsDiagnosaEntity.class;
    }

    @Override
    public List<ImSimrsDiagnosaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDiagnosaEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_diagnosa") != null)
                criteria.add(Restrictions.eq("idDiagnosa", mapCriteria.get("id_diagnosa").toString()));

        List<ImSimrsDiagnosaEntity> result = criteria.list();
        return result;
    }
}
