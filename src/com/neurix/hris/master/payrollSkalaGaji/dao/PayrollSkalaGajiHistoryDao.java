package com.neurix.hris.master.payrollSkalaGaji.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class PayrollSkalaGajiHistoryDao extends GenericDao<ImPayrollSkalaGajiHistoryEntity, String> {
    @Override
    protected Class<ImPayrollSkalaGajiHistoryEntity>  getEntityClass() {
        return ImPayrollSkalaGajiHistoryEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiId", (String) mapCriteria.get("skala_gaji_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
            if (mapCriteria.get("point")!=null) {
                criteria.add(Restrictions.eq("point",mapCriteria.get("point")));
            }

            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiId"));

        List<ImPayrollSkalaGajiHistoryEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSkalaGajiHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PYNH" + sId;
    }
}