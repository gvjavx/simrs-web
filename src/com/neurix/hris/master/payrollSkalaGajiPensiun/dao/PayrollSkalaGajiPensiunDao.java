package com.neurix.hris.master.payrollSkalaGajiPensiun.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGajiPensiun.model.ImPayrollSkalaGajiPensiunEntity;
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
public class PayrollSkalaGajiPensiunDao extends GenericDao<ImPayrollSkalaGajiPensiunEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiPensiunEntity> getEntityClass() {
        return ImPayrollSkalaGajiPensiunEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiPensiunEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_pensiun_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiPensiunId", (String) mapCriteria.get("skala_gaji_pensiun_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiPensiunId"));

        List<ImPayrollSkalaGajiPensiunEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiPensiun() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_pensiun')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGP"+sId;
    }

    public List<ImPayrollSkalaGajiPensiunEntity> getSkalaGajiPensiun(String golonganId, int poin) throws HibernateException {
        List<ImPayrollSkalaGajiPensiunEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("poin", poin))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }


}