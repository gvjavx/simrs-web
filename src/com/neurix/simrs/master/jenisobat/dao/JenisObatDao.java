package com.neurix.simrs.master.jenisobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class JenisObatDao extends GenericDao<ImSimrsJenisObatEntity, String> {

    @Override
    protected Class<ImSimrsJenisObatEntity> getEntityClass() {
        return ImSimrsJenisObatEntity.class;
    }

    @Override
    public List<ImSimrsJenisObatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_jenis_obat")!=null) {
                criteria.add(Restrictions.eq("idJenisObat", (String) mapCriteria.get("id_jenis_obat")));
            }
            if (mapCriteria.get("group_name")!=null) {
                criteria.add(Restrictions.ilike("groupName", "%" + (String)mapCriteria.get("group_name") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idJenisObat"));

        List<ImSimrsJenisObatEntity> results = criteria.list();

        return results;
    }
}