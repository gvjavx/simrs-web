package com.neurix.hris.master.payrollTunjanganJabatanStruktural.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollTunjanganJabatanStruktural.model.ImmPayrollTunjanganJabatanStrukturalEntity;
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
public class PayrollTunjanganJabatanStrukturalDao extends GenericDao<ImmPayrollTunjanganJabatanStrukturalEntity, String> {

    @Override
    protected Class<ImmPayrollTunjanganJabatanStrukturalEntity>  getEntityClass() {
        return ImmPayrollTunjanganJabatanStrukturalEntity.class;
    }

    @Override
    public List<ImmPayrollTunjanganJabatanStrukturalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImmPayrollTunjanganJabatanStrukturalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tunjJabatanStrukturalId")!=null) {
                criteria.add(Restrictions.eq("tunjJabatanStrukturalId", (String) mapCriteria.get("tunjJabatanStrukturalId")));
            }

            if (mapCriteria.get("positionId")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("positionId")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }

            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("positionName")!=null) {
                criteria.add(Restrictions.eq("positionName", (String) mapCriteria.get("positionName")));
            }
            if (mapCriteria.get("branchName")!=null) {
                criteria.add(Restrictions.eq("branchName", (String) mapCriteria.get("branchName")));
            }

        }

        // Order by
        criteria.addOrder(Order.desc("tunjJabatanStrukturalId"));
        List<ImmPayrollTunjanganJabatanStrukturalEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTunjanganJabatanStruktural() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_jabatan_struktural')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "TJS" + sId;
    }

    public List<ImmPayrollTunjanganJabatanStrukturalEntity> getData2(String positionId, String branchId) throws HibernateException {
        List<ImmPayrollTunjanganJabatanStrukturalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImmPayrollTunjanganJabatanStrukturalEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}