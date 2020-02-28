package com.neurix.hris.master.golonganPkwt.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.golonganPkwt.model.ImGolonganPkwtEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class GolonganPkwtDao extends GenericDao<ImGolonganPkwtEntity, String> {

    @Override
    protected Class<ImGolonganPkwtEntity> getEntityClass() {
        return ImGolonganPkwtEntity.class;
    }

    @Override
    public List<ImGolonganPkwtEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImGolonganPkwtEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("golongan_pkwt_id")!=null) {
                criteria.add(Restrictions.eq("golonganPkwtId", (String) mapCriteria.get("golongan_pkwt_id")));
            }
            if (mapCriteria.get("golongan_pkwt_name")!=null) {
                criteria.add(Restrictions.ilike("golonganPkwtName", "%" + (String)mapCriteria.get("golongan_pkwt_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("golonganPkwtId"));

        List<ImGolonganPkwtEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganPkwtId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "G"+sId;
    }

    public List<ImGolonganPkwtEntity> getListGolongan(String term) throws HibernateException {

        List<ImGolonganPkwtEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganPkwtEntity.class)
                .add(Restrictions.ilike("golonganPkwtName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganPkwtId"))
                .list();

        return results;
    }
    public List<ImGolonganPkwtEntity> getGolonganById(String golonganPkwtId) throws HibernateException {

        List<ImGolonganPkwtEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganPkwtEntity.class)
                .add(Restrictions.ilike("golonganPkwtId",golonganPkwtId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganPkwtId"))
                .list();
        return results;
    }

}
