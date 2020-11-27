package com.neurix.hris.master.payrollParamBpjs.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollParamBpjs.model.ImHrisPayrollParamBpjsEntity;
import com.neurix.hris.master.payrollParamBpjs.model.ImHrisPayrollParamBpjsHistoryEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigDecimal;
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
public class PayrollParamBpjsDao extends GenericDao<ImHrisPayrollParamBpjsEntity, String> {

    @Override
    protected Class<ImHrisPayrollParamBpjsEntity>  getEntityClass() {
        return ImHrisPayrollParamBpjsEntity.class;
    }

    @Override
    public List<ImHrisPayrollParamBpjsEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollParamBpjsEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("payroll_param_bpjs_id")!=null) {
                criteria.add(Restrictions.eq("payrollParamBpjsId", (String) mapCriteria.get("payroll_param_bpjs_id")));
            }

            if (mapCriteria.get("flag_gapok")!=null) {
                criteria.add(Restrictions.eq("flagGapok", (String) mapCriteria.get("flag_gapok")));
            }

            if (mapCriteria.get("flag_sankhus")!=null) {
                criteria.add(Restrictions.eq("flagSankhus", (String) mapCriteria.get("flag_sankhus")));
            }

            if (mapCriteria.get("nilaflag_peralihan_gapok")!=null) {
                criteria.add(Restrictions.eq("flagPeralihanGapok", (String) mapCriteria.get("flag_peralihan_gapok")));
            }
            if (mapCriteria.get("flag_peralihan_sankhus")!=null) {
                criteria.add(Restrictions.eq("flagPeralihanSankhus", (String) mapCriteria.get("flag_peralihan_sankhus")));
            }
            if (mapCriteria.get("flag_peralihan_tunjangan")!=null) {
                criteria.add(Restrictions.eq("flagPeralihanTunjangan", (String) mapCriteria.get("flag_peralihan_tunjangan")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("payrollParamBpjsId"));
        List<ImHrisPayrollParamBpjsEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextIdBpjs() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_param_bpjs')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "BPJS" + sId;
    }

    public String getNextPayrollParamBpjsHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_param_bpjs_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "HBPJS"+sId;
    }

    public void addAndSaveHistory(ImHrisPayrollParamBpjsHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisPayrollParamBpjsEntity> getData2(String idPtkp) throws HibernateException {
        List<ImHrisPayrollParamBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollParamBpjsEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImHrisPayrollParamBpjsEntity> checkData(String flagGapok, String flagSankhus) throws HibernateException {

        List<ImHrisPayrollParamBpjsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollParamBpjsEntity.class)
                .add(Restrictions.eq("flagGapok", flagGapok))
                .add(Restrictions.eq("flagSankhus", flagSankhus))
//                .add(Restrictions.eq("flagPeralihanGapok", jumlahTanggungan))
//                .add(Restrictions.eq("flagPeralihanSankhus", jumlahTanggungan))
//                .add(Restrictions.eq("flagPeralihanTunjangan", jumlahTanggungan))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("payrollParamBpjsId"))
                .list();

        return results;
    }
}