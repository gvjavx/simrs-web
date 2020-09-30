package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsHeaderObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 30/09/20.
 */
public class HeaderObatDao extends GenericDao<ImSimrsHeaderObatEntity, String>{

    @Override
    protected Class<ImSimrsHeaderObatEntity> getEntityClass() {
        return ImSimrsHeaderObatEntity.class;
    }

    @Override
    public List<ImSimrsHeaderObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsHeaderObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_obat") != null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("nama_obat") != null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String) mapCriteria.get("nama_obat") + "%"));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("id_pabrik") != null) {
                criteria.add(Restrictions.eq("idPabrik", (String) mapCriteria.get("id_pabrik")));
            }

            if (mapCriteria.get("lembar_per_box") != null) {
                criteria.add(Restrictions.eq("lembarPerBox", (BigInteger) mapCriteria.get("lembar_per_box")));
            }

            if (mapCriteria.get("biji_per_lembar") != null) {
                criteria.add(Restrictions.eq("bijiPerLembar", (BigInteger) mapCriteria.get("biji_per_lembar")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }

            if (mapCriteria.get("asc") != null) {
                criteria.addOrder(Order.asc("createdDate"));
            } else if (mapCriteria.get("desc") != null) {
                criteria.addOrder(Order.desc("createdDate"));
            } else if (mapCriteria.get("exp") != null) {
                criteria.addOrder(Order.asc("expiredDate"));
            } else {
                criteria.addOrder(Order.asc("idObat"));
            }

            // limit
            if (mapCriteria.get("limit") != null){
                criteria.setMaxResults(Integer.valueOf(mapCriteria.get("limit").toString()));
            }
        }


        List<ImSimrsHeaderObatEntity> results = criteria.list();
        return results;
    }


    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
