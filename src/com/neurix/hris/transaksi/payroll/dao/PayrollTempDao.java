/*
 * Copyright (c) GO-MEDSYS(TM) 2020 created by MGI
 */

package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItHrisPayrollTempEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PayrollTempDao extends GenericDao<ItHrisPayrollTempEntity, String> {

    @Override
    protected Class<ItHrisPayrollTempEntity> getEntityClass() {
        return ItHrisPayrollTempEntity.class;
    }

    @Override
    public List<ItHrisPayrollTempEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollTempEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("payroll_temp_id") != null) {
                criteria.add(Restrictions.eq("payrollTempId", (String) mapCriteria.get("payroll_temp_id")));
            }

            if (mapCriteria.get("payroll_header_id") != null) {
                criteria.add(Restrictions.eq("payrollHeaderId", (String) mapCriteria.get("payroll_header_id")));
            }

            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }

            if (mapCriteria.get("bulan") != null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun") != null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

            if (mapCriteria.get("tipe_payroll") != null) {
                criteria.add(Restrictions.eq("tipePayroll", (String) mapCriteria.get("tipe_payroll")));
            }

            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollTempId"));

        List<ItHrisPayrollTempEntity> results = criteria.list();

        return results;
    }

    //updated by ferdi, 01-12-2020, to generated pph temp seq
    public String getNextPayrollTempId(String bulan, String tahun) throws HibernateException {
        String[] per = tahun.split("");

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_temp')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        String hasil = "PYRTEM" + bulan + per[2] + per[3] + sId;

        return hasil;
    }


    public ItHrisPayrollTempEntity getDataView(String payrollTempId) throws HibernateException {
        List<ItHrisPayrollTempEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPayrollTempEntity.class)
                .add(Restrictions.eq("payrollTempId", payrollTempId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        ItHrisPayrollTempEntity resultItem = results.get(0);

        return resultItem;
    }

}