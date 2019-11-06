
package com.neurix.hris.master.payrollJabatanStruktural.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollJabatanStruktural.model.ImPayrollJabatanStrukturalEntity;
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
public class PayrollJabatanStrukturalDao extends GenericDao<ImPayrollJabatanStrukturalEntity, String> {


    @Override
    protected Class<ImPayrollJabatanStrukturalEntity>  getEntityClass() {
        return ImPayrollJabatanStrukturalEntity.class;
    }

    @Override
    public List<ImPayrollJabatanStrukturalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollJabatanStrukturalEntity.class);

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
        }

        // Order by
        criteria.addOrder(Order.desc("tunjStrukturId"));

        List<ImPayrollJabatanStrukturalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextStruktural() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_struktural')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "TSR" + sId;
    }

    public List<ImPayrollJabatanStrukturalEntity> getData(String branchId, String golonganId){
        List<ImPayrollJabatanStrukturalEntity> listOfResult = new ArrayList<ImPayrollJabatanStrukturalEntity>();

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
            ImPayrollJabatanStrukturalEntity result  = new ImPayrollJabatanStrukturalEntity();
            result.setNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPayrollJabatanStrukturalEntity> getData2(String golonganId) throws HibernateException {
        List<ImPayrollJabatanStrukturalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollJabatanStrukturalEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}
