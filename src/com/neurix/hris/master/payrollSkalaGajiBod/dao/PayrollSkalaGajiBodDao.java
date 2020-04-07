package com.neurix.hris.master.payrollSkalaGajiBod.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiBod.model.ImPayrollSkalaGajiBodEntity;
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
public class PayrollSkalaGajiBodDao extends GenericDao<ImPayrollSkalaGajiBodEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiBodEntity> getEntityClass() {
        return ImPayrollSkalaGajiBodEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiBodEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiBodEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_bod_id")!=null) {
                criteria.add(Restrictions.eq("payrollSkalaGajiBodId", (String) mapCriteria.get("skala_gaji_bod_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }
        // Order by
        criteria.addOrder(Order.desc("payrollSkalaGajiBodId"));

        List<ImPayrollSkalaGajiBodEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiBod() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_bod')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGB"+sId;
    }

    public List<ImPayrollSkalaGajiBodEntity> getSkalaGajiBod(String positionId,String tahun) throws HibernateException {
        List<ImPayrollSkalaGajiBodEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiBodEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}