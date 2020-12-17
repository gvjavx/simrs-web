package com.neurix.akuntansi.master.parameterbudgeting.dao;

import com.neurix.akuntansi.master.parameterbudgeting.model.ImAkunKategoriParameterBudgetingEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class KategoriParameterBudgetingDao extends GenericDao<ImAkunKategoriParameterBudgetingEntity, String>{
    @Override
    protected Class<ImAkunKategoriParameterBudgetingEntity> getEntityClass() {
        return ImAkunKategoriParameterBudgetingEntity.class;
    }

    @Override
    public List<ImAkunKategoriParameterBudgetingEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImAkunKategoriParameterBudgetingEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("id_jenis_budgeting") != null){
            criteria.add(Restrictions.eq("idJenisBudgeting", mapCriteria.get("id_jenis_budgeting").toString()));
        }
        return criteria.list();
    }
}
