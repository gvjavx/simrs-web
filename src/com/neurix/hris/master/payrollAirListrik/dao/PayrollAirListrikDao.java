package com.neurix.hris.master.payrollAirListrik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollAirListrik.model.ImPayrollAirListrikEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class PayrollAirListrikDao extends GenericDao<ImPayrollAirListrikEntity, String> {

    @Override
    protected Class<ImPayrollAirListrikEntity>  getEntityClass() {
        return ImPayrollAirListrikEntity.class;
    }

    @Override
    public List<ImPayrollAirListrikEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollAirListrikEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("air_listrik_id")!=null) {
                criteria.add(Restrictions.eq("tunjAirListrikId", (String) mapCriteria.get("air_listrik_id")));
            }

            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("tunjAirListrikId"));
        List<ImPayrollAirListrikEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextAirListrik() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_air_listrik')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "SG" + sId;
    }

    public List<ImPayrollAirListrikEntity> getData2(String golonganId) throws HibernateException {
        List<ImPayrollAirListrikEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollAirListrikEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}