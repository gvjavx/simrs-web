package com.neurix.hris.master.payrollSkalaGajiPensiunDplk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiPensiunDplk.model.ImPayrollSkalaGajiPensiunDplkEntity;
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
public class PayrollSkalaGajiPensiunDplkDao extends GenericDao<ImPayrollSkalaGajiPensiunDplkEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiPensiunDplkEntity> getEntityClass() {
        return ImPayrollSkalaGajiPensiunDplkEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiPensiunDplkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunDplkEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skalaGajiPensiunId")!=null) {
                criteria.add(Restrictions.eq("skalaGajiPensiunId", (String) mapCriteria.get("skalaGajiPensiunId")));
            }
            if (mapCriteria.get("golonganId")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golonganId")));
            }

            criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiPensiunId"));

        List<ImPayrollSkalaGajiPensiunDplkEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiPensiun() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_pensiun_dplk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGP"+sId;
    }

    public List<ImPayrollSkalaGajiPensiunDplkEntity> getSkalaGajiPensiunDplk(String golonganId, int poin) throws HibernateException {
        List<ImPayrollSkalaGajiPensiunDplkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunDplkEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("poin", poin))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }


}