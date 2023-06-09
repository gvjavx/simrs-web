package com.neurix.hris.master.payrollSkalaGajiPkwt.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtEntity;
import com.neurix.hris.master.payrollSkalaGajiPkwt.model.ImPayrollSkalaGajiPkwtHistoryEntity;
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
public class PayrollSkalaGajiPkwtHistoryDao extends GenericDao<ImPayrollSkalaGajiPkwtHistoryEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiPkwtHistoryEntity> getEntityClass() {
        return ImPayrollSkalaGajiPkwtHistoryEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiPkwtHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPkwtEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_pkwt_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiPkwtId", (String) mapCriteria.get("skala_gaji_pkwt_id")));
            }
            if (mapCriteria.get("golongan_pkwt_id")!=null) {
                criteria.add(Restrictions.eq("golonganPkwtId", (String) mapCriteria.get("golongan_pkwt_id")));
            }

            criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
        }
        // Order by
        criteria.addOrder(Order.desc("createdDate"));

        List<ImPayrollSkalaGajiPkwtHistoryEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiPkwtHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_pkwt_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGPKH"+sId;
    }


}