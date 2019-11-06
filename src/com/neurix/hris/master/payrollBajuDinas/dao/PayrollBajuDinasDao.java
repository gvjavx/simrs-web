package com.neurix.hris.master.payrollBajuDinas.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollBajuDinas.model.ImPayrollBajuDinasEntity;
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
public class PayrollBajuDinasDao extends GenericDao<ImPayrollBajuDinasEntity, String> {

    @Override
    protected Class<ImPayrollBajuDinasEntity> getEntityClass() {
        return ImPayrollBajuDinasEntity.class;
    }

    @Override
    public List<ImPayrollBajuDinasEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBajuDinasEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pakaian_dinas_id")!=null) {
                criteria.add(Restrictions.eq("pakaianDinasId", (String) mapCriteria.get("pakaian_dinas_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId",(String)mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("gender")!=null) {
                criteria.add(Restrictions.eq("gender",(String)mapCriteria.get("gender")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pakaianDinasId"));

        List<ImPayrollBajuDinasEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBajuDinasId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_baju_dinas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "PKD"+sId;
    }


    public List<ImPayrollBajuDinasEntity> getListBajuDinas(String branchId, String gender) throws HibernateException {

        List<ImPayrollBajuDinasEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollBajuDinasEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("gender", gender))
                .add(Restrictions.eq("flag", "Y"))
                .setMaxResults(1)
                .list();
        return results;
    }
}
