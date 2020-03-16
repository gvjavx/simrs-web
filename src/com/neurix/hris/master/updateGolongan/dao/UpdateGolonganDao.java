package com.neurix.hris.master.updateGolongan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.updateGolongan.model.ImtUpdateGolonganEntity;
import com.neurix.hris.master.updateGolongan.model.ImtUpdateGolonganHistoryEntity;
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
public class UpdateGolonganDao extends GenericDao<ImtUpdateGolonganEntity, String> {

    @Override
    protected Class<ImtUpdateGolonganEntity> getEntityClass() {
        return ImtUpdateGolonganEntity.class;
    }

    @Override
    public List<ImtUpdateGolonganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtUpdateGolonganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("update_golongan_id")!=null) {
                criteria.add(Restrictions.eq("updateGolonganId", (String) mapCriteria.get("update_golongan_id")));
            }

            if (mapCriteria.get("periode")!=null) {
                criteria.add(Restrictions.eq("periode", (String) mapCriteria.get("periode")));
            }

            if (mapCriteria.get("approval_flag")!=null) {
                criteria.add(Restrictions.eq("approvalFlag", (String) mapCriteria.get("approval_flag")));
            }



        }

        // Order by
        criteria.addOrder(Order.asc("updateGolonganId"));

        List<ImtUpdateGolonganEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_update_golongan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "MG"+sId;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImtUpdateGolonganEntity> getListGolongan(String term) throws HibernateException {

        List<ImtUpdateGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtUpdateGolonganEntity.class)
                .add(Restrictions.ilike("golonganName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();

        return results;
    }

    public List<ImtUpdateGolonganEntity> getListGolonganById(String id) throws HibernateException {

        List<ImtUpdateGolonganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImtUpdateGolonganEntity.class)
                .add(Restrictions.eq("updateGolonganId", id))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("golonganId"))
                .list();

        return results;
    }
    public void addAndSaveHistory(ImtUpdateGolonganHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);

    }

}
