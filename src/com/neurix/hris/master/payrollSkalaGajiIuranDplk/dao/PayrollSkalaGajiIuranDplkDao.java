package com.neurix.hris.master.payrollSkalaGajiIuranDplk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGajiIuranDplk.model.ImPayrollSkalaGajiIuranDplkEntity;
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
public class PayrollSkalaGajiIuranDplkDao extends GenericDao<ImPayrollSkalaGajiIuranDplkEntity, String> {

    @Override
    protected Class<ImPayrollSkalaGajiIuranDplkEntity> getEntityClass() {
        return ImPayrollSkalaGajiIuranDplkEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiIuranDplkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiIuranDplkEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_iuran_dplk_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiIuranDplkId", (String) mapCriteria.get("skala_gaji_iuran_dplk_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiIuranDplkId"));

        List<ImPayrollSkalaGajiIuranDplkEntity> results = criteria.list();

        return results;
    }

    public String getNextSkalaGajiIuranDplk() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji_iuran_dplk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());
        return "SGID"+sId;
    }

    public List<ImPayrollSkalaGajiIuranDplkEntity> getSkalaGajiIuranDplk(String golonganId, int masaKerjaGol) throws HibernateException {
        List<ImPayrollSkalaGajiIuranDplkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiIuranDplkEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("masaKerjaGol", masaKerjaGol))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImPayrollSkalaGajiIuranDplkEntity> getSkalaGajiIuranDplkSimRs(String golonganId) throws HibernateException {
        List<ImPayrollSkalaGajiIuranDplkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiIuranDplkEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }


}