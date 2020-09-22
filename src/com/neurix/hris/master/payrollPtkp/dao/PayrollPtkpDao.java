package com.neurix.hris.master.payrollPtkp.dao;

import com.neurix.akuntansi.master.mappingJurnal.model.ImMappingJurnalEntity;
import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollPtkp.model.ImHrisPayrollPtkpEntity;
import com.neurix.hris.master.payrollPtkp.model.ImHrisPayrollPtkpHistoryEntity;
import com.neurix.hris.master.tipePayroll.model.ImHrisTipePayrollHistoryEntity;
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
public class PayrollPtkpDao extends GenericDao<ImHrisPayrollPtkpEntity, String> {

    @Override
    protected Class<ImHrisPayrollPtkpEntity>  getEntityClass() {
        return ImHrisPayrollPtkpEntity.class;
    }

    @Override
    public List<ImHrisPayrollPtkpEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollPtkpEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("ptkp_id")!=null) {
                criteria.add(Restrictions.eq("idPtkp", (String) mapCriteria.get("ptkp_id")));
            }

            if (mapCriteria.get("status_keluarga")!=null) {
                criteria.add(Restrictions.eq("statusKeluarga", (String) mapCriteria.get("status_keluarga")));
            }

            if (mapCriteria.get("jumlah_tanggungan")!=null) {
                criteria.add(Restrictions.eq("jumlahTanggungan", (Integer) mapCriteria.get("jumlah_tanggungan")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (BigDecimal) mapCriteria.get("nilai")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("idPtkp"));
        List<ImHrisPayrollPtkpEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextIdPtkp() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_ptkp')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "PTKP" + sId;
    }

    public String getNextPayrollPtkpHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_ptkp_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "HPTKP"+sId;
    }

    public void addAndSaveHistory(ImHrisPayrollPtkpHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public List<ImHrisPayrollPtkpEntity> getData2(String idPtkp) throws HibernateException {
        List<ImHrisPayrollPtkpEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollPtkpEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public List<ImHrisPayrollPtkpEntity> checkData(String statusKeluarga, Integer jumlahTanggungan) throws HibernateException {

        List<ImHrisPayrollPtkpEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImHrisPayrollPtkpEntity.class)
                .add(Restrictions.eq("statusKeluarga", statusKeluarga))
                .add(Restrictions.eq("jumlahTanggungan", jumlahTanggungan))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("idPtkp"))
                .list();

        return results;
    }
}