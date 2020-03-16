package com.neurix.hris.master.ijin.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.ijin.model.ImIjinEntity;
import com.neurix.hris.master.ijin.model.ImIjinHistoryEntity;
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
public class IjinDao extends GenericDao<ImIjinEntity, String> {

    @Override
    protected Class<ImIjinEntity> getEntityClass() {
        return ImIjinEntity.class;
    }

    @Override
    public List<ImIjinEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImIjinEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("ijin_id")!=null) {
                criteria.add(Restrictions.eq("ijinId", (String) mapCriteria.get("ijin_id")));
            }
            if (mapCriteria.get("ijin_name")!=null) {
                criteria.add(Restrictions.ilike("ijinName", "%" + (String)mapCriteria.get("ijin_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("ijinId"));

        List<ImIjinEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextIjinId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "IJ"+sId;
    }

    public String getNextIjinHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_ijin_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "HI"+sId;
    }

    public List<ImIjinEntity> getListIjin(String term) throws HibernateException {

        List<ImIjinEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImIjinEntity.class)
                .add(Restrictions.ilike("ijinName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("ijinId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImIjinHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    //get list tipe
    public List<ImIjinEntity> getListLamaIjin(String term) throws HibernateException {

        List<ImIjinEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImIjinEntity.class)
                .add(Restrictions.eq("ijinId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("createdDate"))
                .setMaxResults(1)
                .list();

        return results;
    }

    public List<ImIjinEntity> getListIjinId(String term) throws HibernateException {

        List<ImIjinEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImIjinEntity.class)
                .add(Restrictions.ilike("ijinId", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("ijinId"))
                .list();
        return results;
    }
}
