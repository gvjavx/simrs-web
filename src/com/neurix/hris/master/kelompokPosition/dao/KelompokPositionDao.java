package com.neurix.hris.master.kelompokPosition.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionEntity;
import com.neurix.hris.master.kelompokPosition.model.ImKelompokPositionHistoryEntity;
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
public class KelompokPositionDao extends GenericDao<ImKelompokPositionEntity, String> {

    @Override
    protected Class<ImKelompokPositionEntity> getEntityClass() {
        return ImKelompokPositionEntity.class;
    }

    @Override
    public List<ImKelompokPositionEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKelompokPositionEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kelompok_id")!=null) {
                criteria.add(Restrictions.eq("kelompokId", (String) mapCriteria.get("kelompok_id")));
            }
            if (mapCriteria.get("kelompok_name")!=null) {
                criteria.add(Restrictions.ilike("kelompokName", "%" + (String)mapCriteria.get("kelompok_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("kelompokId"));

        List<ImKelompokPositionEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKelompokPositionId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kelompok_position')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "KL"+sId;
    }

    public String getNextKelompokPositionHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kelompok_position_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "HK"+sId;
    }

    public List<ImKelompokPositionEntity> getListKelompokPosition(String term) throws HibernateException {

        List<ImKelompokPositionEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKelompokPositionEntity.class)
                .add(Restrictions.ilike("kelompokName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kelompokId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImKelompokPositionHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
