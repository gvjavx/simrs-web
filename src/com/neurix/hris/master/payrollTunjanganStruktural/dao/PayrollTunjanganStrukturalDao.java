
package com.neurix.hris.master.payrollTunjanganStruktural.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollTunjanganStruktural.model.ImPayrollTunjanganStrukturalEntity;
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
public class PayrollTunjanganStrukturalDao extends GenericDao<ImPayrollTunjanganStrukturalEntity, String> {


    @Override
    protected Class<ImPayrollTunjanganStrukturalEntity>  getEntityClass() {
        return ImPayrollTunjanganStrukturalEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganStrukturalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrukturalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("struktural_id")!=null) {
                criteria.add(Restrictions.eq("tunjStrukturId", (String) mapCriteria.get("struktural_id")));
            }

            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }
            
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
        }

        // Order by
        criteria.addOrder(Order.desc("tunjStrukturId"));

        List<ImPayrollTunjanganStrukturalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStruktural() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_struktural')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "TSR" + sId;
    }

    public List<ImPayrollTunjanganStrukturalEntity> getData(String branchId, String golonganId){
        List<ImPayrollTunjanganStrukturalEntity> listOfResult = new ArrayList<ImPayrollTunjanganStrukturalEntity>();

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
            ImPayrollTunjanganStrukturalEntity result  = new ImPayrollTunjanganStrukturalEntity();
            result.setNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPayrollTunjanganStrukturalEntity> getData2(String golonganId, String tahun) throws HibernateException {
        List<ImPayrollTunjanganStrukturalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrukturalEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
