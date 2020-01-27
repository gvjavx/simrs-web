package com.neurix.hris.master.payrollSkalaGajiPensiunDapenbun.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiPensiunDapenbun.model.ImPayrollSkalaGajiPensiunRniEntity;
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
public class PayrollSkalaGajiPensiunRniDao extends GenericDao<ImPayrollSkalaGajiPensiunRniEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiPensiunRniEntity> getEntityClass() {
        return ImPayrollSkalaGajiPensiunRniEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiPensiunRniEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunRniEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_pensiun_rni_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiPensiunRniId", (String) mapCriteria.get("skala_gaji_pensiun_rni_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiPensiunRniId"));

        List<ImPayrollSkalaGajiPensiunRniEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiPensiun() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_pensiun_rni')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return "SGP"+sId;
    }

    public List<ImPayrollSkalaGajiPensiunRniEntity> getSkalaGajiPensiunRni(String golonganId, int poin) throws HibernateException {
        List<ImPayrollSkalaGajiPensiunRniEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiPensiunRniEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("poin", poin))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }


}