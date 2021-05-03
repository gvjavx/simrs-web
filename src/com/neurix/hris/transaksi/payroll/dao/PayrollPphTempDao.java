/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollPphTempEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PayrollPphTempDao extends GenericDao<ItHrisPayrollPphTempEntity, String> {

    @Override
    protected Class<ItHrisPayrollPphTempEntity> getEntityClass() {
        return ItHrisPayrollPphTempEntity.class;
    }

    @Override
    public List<ItHrisPayrollPphTempEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollPphTempEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("pph_temp_id") != null) {
                criteria.add(Restrictions.eq("pphTempId", (String) mapCriteria.get("pph_temp_id")));
            }

            if (mapCriteria.get("payroll_temp_id") != null) {
                criteria.add(Restrictions.eq("payrollTempId", (String) mapCriteria.get("payroll_temp_id")));
            }

            if (mapCriteria.get("bulan") != null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun") != null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("pphTempId"));

        List<ItHrisPayrollPphTempEntity> results = criteria.list();

        return results;
    }

    //updated by ferdi, 01-12-2020, to generated pph temp seq
    public String getNextPayrollPphTempId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_pph_temp')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = "PPHTEM" + bulan + per[2] + per[3] + sId;

        return hasil;
    }

    public ItHrisPayrollPphTempEntity getItemView(String payrollTempId) throws HibernateException {
        List<ItHrisPayrollPphTempEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollPphTempEntity.class)
                .add(Restrictions.eq("payrollTempId", payrollTempId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        ItHrisPayrollPphTempEntity resultItem = results.get(0);

        return resultItem;
    }

}