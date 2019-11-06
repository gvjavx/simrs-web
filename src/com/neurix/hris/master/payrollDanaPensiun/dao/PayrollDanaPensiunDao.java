package com.neurix.hris.master.payrollDanaPensiun.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollDanaPensiun.model.ImPayrollDanaPensiunEntity;
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
public class PayrollDanaPensiunDao extends GenericDao<ImPayrollDanaPensiunEntity, String> {


    @Override
    protected Class<ImPayrollDanaPensiunEntity> getEntityClass() {
        return ImPayrollDanaPensiunEntity.class;
    }

    @Override
    public List<ImPayrollDanaPensiunEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollDanaPensiunEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("dana_pensiun_id")!=null) {
                criteria.add(Restrictions.eq("danaPensiunId", (String) mapCriteria.get("dana_pensiun_id")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("dana_pensiun")!=null) {
                criteria.add(Restrictions.ilike("danaPensiun", "%" + (String) mapCriteria.get("dana_pensiun") + "%"));
            }

        }
        // Order by
        criteria.addOrder(Order.desc("danaPensiunId"));

        List<ImPayrollDanaPensiunEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextDanaPensiunId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dana_pensiun')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "DP"+sId;
    }

    public String getNextDanaPensiunHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dana_pensiun_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "H"+sId;
    }

    public List<ImPayrollDanaPensiunEntity> getListDanaPensiun(String term) throws HibernateException {

        List<ImPayrollDanaPensiunEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollDanaPensiunEntity.class)
                .add(Restrictions.eq("danaPensiunId", term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("danaPensiunId"))
                .list();
        return results;
    }

}
