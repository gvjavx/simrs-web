
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ItPayrollJubileumEntity;
import com.neurix.hris.transaksi.payroll.model.ItPayrollJubileumEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
public class PayrollJubileumDao extends GenericDao<ItPayrollJubileumEntity, String> {

    @Override
    protected Class<ItPayrollJubileumEntity> getEntityClass() {
        return ItPayrollJubileumEntity.class;
    }

    @Override
    public List<ItPayrollJubileumEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItPayrollJubileumEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_id")!=null) {
                criteria.add(Restrictions.eq("payrollId", (String) mapCriteria.get("payroll_id")));
            }

            if (mapCriteria.get("bulan")!=null) {
                criteria.add(Restrictions.eq("bulan", (String) mapCriteria.get("bulan")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payroll_id"));

        List<ItPayrollJubileumEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJubileumId(String tahun) throws HibernateException {
        String[] per = tahun.split("");
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jubileum')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JBL" + per[2] + per[3] + sId;
    }

    public List<ItPayrollJubileumEntity> getJubileum(String payrollId) throws HibernateException {
        List<ItPayrollJubileumEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItPayrollJubileumEntity.class)
                .add(Restrictions.eq("payrollId", payrollId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ItPayrollJubileumEntity> cekJubileum(String nip){
        List<ItPayrollJubileumEntity> listOfResult = new ArrayList<ItPayrollJubileumEntity>();
        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tpayroll.payroll_id\n" +
                "from \n" +
                "\tit_hris_payroll payroll\n" +
                "\tinner join it_hris_payroll_jubileum jubileum on jubileum.payroll_id = payroll.payroll_id\n" +
                "where\n" +
                "\tpayroll.nip = '"+nip+"'\n" +
                "\tand payroll.flag = 'Y'\n" +
                "\tand payroll.approval_flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();

        for (Object[] row : results) {
            ItPayrollJubileumEntity result  = new ItPayrollJubileumEntity();
            listOfResult.add(result);
        }
        return listOfResult;
    }
    
    
}
