package com.neurix.simrs.master.dietgizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsDietGizi;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import java.math.BigInteger;
import org.hibernate.Query;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

public class DietGiziDao extends GenericDao<ImSimrsDietGizi, String> {

    @Override
    protected Class<ImSimrsDietGizi> getEntityClass() {
        return ImSimrsDietGizi.class;
    }

    public List<ImSimrsDietGizi> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDietGizi.class);
        if (mapCriteria != null){

            if (mapCriteria.get("id_diet_gizi") != null){
                criteria.add(Restrictions.eq("idDietGizi", mapCriteria.get("id_diet_gizi").toString()));
            }
            if (mapCriteria.get("nama_diet_gizi") != null){
                criteria.add(Restrictions.eq("namaDietGizi", mapCriteria.get("nama_diet_gizi").toString()));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        }
        List<ImSimrsDietGizi> result = criteria.list();
        return result;
    }
    public List<ImSimrsDietGizi> getDietGizi(String namaDietGizi ) throws HibernateException {
        List<ImSimrsDietGizi> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDietGizi.class)
                .add(Restrictions.eq("namaDietGizi", namaDietGizi))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_diet_gizi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "DGZ"+sId;
    }
}
