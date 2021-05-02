package com.neurix.hris.master.golonganDapen.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.golonganDapen.model.ImGolonganDapenEntity;
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
public class GolonganDapenDao extends GenericDao<ImGolonganDapenEntity, String> {

    @Override
    protected Class<ImGolonganDapenEntity> getEntityClass() {
        return ImGolonganDapenEntity.class;
    }

    @Override
    public List<ImGolonganDapenEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImGolonganDapenEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("golongan_dapen_id")!=null) {
                criteria.add(Restrictions.eq("golonganDapenId", (String) mapCriteria.get("golongan_dapen_id")));
            }
            if (mapCriteria.get("golongan_dapen_name")!=null) {
                criteria.add(Restrictions.ilike("golonganDapenName", "%" + (String)mapCriteria.get("golongan_dapen_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("createdDate"));

        List<ImGolonganDapenEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganDapenId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "G"+sId;
    }

    public List<ImGolonganDapenEntity> getListGolongan(String term) throws HibernateException {

        List<ImGolonganDapenEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganDapenEntity.class)
                .add(Restrictions.ilike("golonganDapenName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganDapenId"))
                .list();

        return results;
    }
    public List<ImGolonganDapenEntity> getGolonganById(String golonganDapenId) throws HibernateException {

        List<ImGolonganDapenEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImGolonganDapenEntity.class)
                .add(Restrictions.ilike("golonganDapenId",golonganDapenId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganDapenId"))
                .list();
        return results;
    }

}
