
package com.neurix.hris.master.department.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.department.model.ImDepartmentEntity;
import com.neurix.hris.master.department.model.ImDepartmentHistoryEntity;
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
public class DepartmentDao extends GenericDao<ImDepartmentEntity, String> {

    @Override
    protected Class<ImDepartmentEntity> getEntityClass() {
        return ImDepartmentEntity.class;
    }

    @Override
    public List<ImDepartmentEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImDepartmentEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("department_id")!=null) {
                criteria.add(Restrictions.eq("departmentId", (String) mapCriteria.get("department_id")));
            }
            if (mapCriteria.get("department_name")!=null) {
                criteria.add(Restrictions.ilike("departmentName", "%" + (String)mapCriteria.get("department_name") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("departmentId"));

        List<ImDepartmentEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextDepartmentId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_department')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "D"+sId;
    }

    public String getNextDepartmentHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_department_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImDepartmentEntity> getListDepartment(String term) throws HibernateException {

        List<ImDepartmentEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImDepartmentEntity.class)
                .add(Restrictions.ilike("departmentName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("departmentId"))
                .list();

        return results;
    }

    public List<ImDepartmentEntity> getListKodering(String term) throws HibernateException {

        List<ImDepartmentEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImDepartmentEntity.class)
                .add(Restrictions.ilike("kodering",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("departmentId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImDepartmentHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

}
