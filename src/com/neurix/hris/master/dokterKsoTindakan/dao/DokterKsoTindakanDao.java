package com.neurix.hris.master.dokterKsoTindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.dokterKsoTindakan.model.ImSimrsDokterKsoTindakan;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class DokterKsoTindakanDao extends GenericDao<ImSimrsDokterKsoTindakan, String> {
    @Override
    protected Class<ImSimrsDokterKsoTindakan> getEntityClass() {
        return ImSimrsDokterKsoTindakan.class;
    }

    @Override
    public List<ImSimrsDokterKsoTindakan> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterKsoTindakan.class);

        if (mapCriteria != null){
            if (mapCriteria.get("dokter_kso_tindakan_id") != null){
                criteria.add(Restrictions.eq("dokterKsoTindakanId", (String) mapCriteria.get("dokter_kso_tindakan_id")));
            }
            if (mapCriteria.get("dokter_kso_id") != null){
                criteria.add(Restrictions.eq("dokterKsoId", (String) mapCriteria.get("dokter_kso_id")));
            }
            if (mapCriteria.get("tindakan_id") != null){
                criteria.add(Restrictions.eq("tindakanId", (String) mapCriteria.get("tindakan_id")));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        criteria.addOrder(Order.asc("dokterKsoTindakanId"));
        List<ImSimrsDokterKsoTindakan> results = criteria.list();
        return results;
    }
}