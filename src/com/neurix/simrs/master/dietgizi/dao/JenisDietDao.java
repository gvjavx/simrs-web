package com.neurix.simrs.master.dietgizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsJenisDietEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
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
            if (mapCriteria.get("nama_jenis_diet") != null){
                criteria.add(Restrictions.eq("namaJenisDiet", mapCriteria.get("nama_jenis_diet").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        List<ImSimrsJenisDietEntity> result = criteria.list();
        return result;
    }
    public List<ImSimrsJenisDietEntity> getJenisDiet(String namaJenisDiet ) throws HibernateException {
        List<ImSimrsJenisDietEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisDietEntity.class)
                .add(Restrictions.eq("namaJenisDiet", namaJenisDiet))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_diet')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "JD"+sId;
    }
}
