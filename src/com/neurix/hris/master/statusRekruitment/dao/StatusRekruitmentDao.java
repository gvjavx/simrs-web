
package com.neurix.hris.master.statusRekruitment.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.statusRekruitment.model.ImStatusRekruitmentEntity;
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
public class StatusRekruitmentDao extends GenericDao<ImStatusRekruitmentEntity, String> {

    @Override
    protected Class<ImStatusRekruitmentEntity> getEntityClass() {
        return ImStatusRekruitmentEntity.class;
    }

    @Override
    public List<ImStatusRekruitmentEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImStatusRekruitmentEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("statusRekruitment_id")!=null) {
                criteria.add(Restrictions.eq("statusRekruitmentId", (String) mapCriteria.get("statusRekruitment_id")));
            }
            if (mapCriteria.get("statusRekruitment_name")!=null) {
                criteria.add(Restrictions.ilike("statusRekruitmentName", "%" + (String)mapCriteria.get("statusRekruitment_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("statusRekruitmentId"));

        List<ImStatusRekruitmentEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStatusRekruitmentId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_statusRekruitment')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "B"+sId;
    }


    public List<ImStatusRekruitmentEntity> getListStatusRekruitment(String term) throws HibernateException {

        List<ImStatusRekruitmentEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStatusRekruitmentEntity.class)
                .add(Restrictions.ilike("statusRekruitmentName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("statusRekruitmentId"))
                .list();

        return results;
    }

    public List<ImStatusRekruitmentEntity> getListStatusRekruitment(BigInteger id) throws HibernateException {

        List<ImStatusRekruitmentEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImStatusRekruitmentEntity.class)
                .add(Restrictions.eq("statusRekruitmentId",id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("statusRekruitmentId"))
                .list();

        return results;
    }


}
