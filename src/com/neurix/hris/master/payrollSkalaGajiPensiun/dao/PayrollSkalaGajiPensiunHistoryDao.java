package com.neurix.hris.master.payrollSkalaGajiPensiun.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.ImPayrollSkalaGajiPensiunHistoryEntity;
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
public class PayrollSkalaGajiPensiunHistoryDao extends GenericDao<ImPayrollSkalaGajiPensiunHistoryEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiPensiunHistoryEntity> getEntityClass() {
        return ImPayrollSkalaGajiPensiunHistoryEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiPensiunHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunHistoryEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skalaGajiPensiunId")!=null) {
                criteria.add(Restrictions.eq("skalaGajiPensiunRniId", (String) mapCriteria.get("skalaGajiPensiunId")));
            }
            if (mapCriteria.get("golonganId")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golonganId")));
            }

            criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiPensiunRniId"));

        List<ImPayrollSkalaGajiPensiunHistoryEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiPensiunHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_pensiun_rni_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGPH"+sId;
    }

}