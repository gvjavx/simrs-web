package com.neurix.hris.master.payrollSkalaGaji.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiEntity;
import com.neurix.hris.master.payrollSkalaGaji.model.ImPayrollSkalaGajiHistoryEntity;
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
public class PayrollSkalaGajiDao extends GenericDao<ImPayrollSkalaGajiEntity, String> {
    @Override
    protected Class<ImPayrollSkalaGajiEntity>  getEntityClass() {
        return ImPayrollSkalaGajiEntity.class;
    }

    @Override
    public List<ImPayrollSkalaGajiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skala_gaji_id")!=null) {
                criteria.add(Restrictions.eq("skalaGajiId", (String) mapCriteria.get("skala_gaji_id")));
            }
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("tahun")!=null) {
                criteria.add(Restrictions.eq("tahun", (String) mapCriteria.get("tahun")));
            }
            if (mapCriteria.get("point")!=null) {
                criteria.add(Restrictions.eq("point",mapCriteria.get("point")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }
        // Order by
        criteria.addOrder(Order.desc("skalaGajiId"));

        List<ImPayrollSkalaGajiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSkalaGaji() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "SG" + sId;
    }

    public List<ImPayrollSkalaGajiEntity> getData(String golonganId){
        List<ImPayrollSkalaGajiEntity> listOfResult = new ArrayList<ImPayrollSkalaGajiEntity>();

        List<Object[]> results = new ArrayList<Object[]>();
        String query = "select \n" +
                "\tnilai\n" +
                "from \n" +
                "\tim_hris_payroll_skala_gaji\n" +
                "where\n" +
                "\tgolongan_id = '"+golonganId+"'\n" +
                "\tand flag = 'Y'";

        results = this.sessionFactory.getCurrentSession()
                .createSQLQuery(query)
                .list();


        for (Object[] row : results) {
            ImPayrollSkalaGajiEntity result  = new ImPayrollSkalaGajiEntity();
            result.setNilai(BigDecimal.valueOf(Double.valueOf(row[0].toString())));
            listOfResult.add(result);
        }
        return listOfResult;
    }

    public List<ImPayrollSkalaGajiEntity> getData2(String golonganId, int point, String tahun) throws HibernateException {
        List<ImPayrollSkalaGajiEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollSkalaGajiEntity.class)
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("point", point))
                .add(Restrictions.eq("tahun", tahun))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}