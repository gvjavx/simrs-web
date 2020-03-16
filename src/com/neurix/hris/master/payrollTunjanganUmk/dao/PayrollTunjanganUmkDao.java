package com.neurix.hris.master.payrollTunjanganUmk.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollTunjanganUmk.model.ImPayrollTunjanganUmkEntity;
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
public class PayrollTunjanganUmkDao extends GenericDao<ImPayrollTunjanganUmkEntity, String> {


    @Override
    protected Class<ImPayrollTunjanganUmkEntity>  getEntityClass() {
        return ImPayrollTunjanganUmkEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganUmkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganUmkEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("umk_id")!=null) {
                criteria.add(Restrictions.eq("umkId", (String) mapCriteria.get("umk_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
            
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.desc("umkId"));

        List<ImPayrollTunjanganUmkEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextUmk() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_umk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "UMK" + sId;
    }

    public List<ImPayrollTunjanganUmkEntity> getData(String branchId, String golonganId){
        List<ImPayrollTunjanganUmkEntity> listOfResult = new ArrayList<ImPayrollTunjanganUmkEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tnilai\n" +
                "from \n" +
                "\timt_hris_payroll_tunjangan_umk\n" +
                "where\n" +
                "\tbranch_id = '"+branchId+"'\n" +
                "\tand golongan_id = '"+golonganId+"'\n" +
                "\tand flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImPayrollTunjanganUmkEntity result  = new ImPayrollTunjanganUmkEntity();
            result.setNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPayrollTunjanganUmkEntity> getData2(String branchId, String golonganId, String tahun) throws HibernateException {
        List<ImPayrollTunjanganUmkEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganUmkEntity.class)
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
