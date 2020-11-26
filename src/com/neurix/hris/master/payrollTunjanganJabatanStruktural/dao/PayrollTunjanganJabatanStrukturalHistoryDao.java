package com.neurix.hris.master.payrollTunjanganJabatanStruktural.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganJabatanStrukturalEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganJabatanStrukturalHistoryEntity;
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
public class PayrollTunjanganJabatanStrukturalHistoryDao extends GenericDao<ImPayrollTunjanganJabatanStrukturalHistoryEntity, String> {

    @Override
    protected Class<ImPayrollTunjanganJabatanStrukturalHistoryEntity>  getEntityClass() {
        return ImPayrollTunjanganJabatanStrukturalHistoryEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganJabatanStrukturalHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganJabatanStrukturalHistoryEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tunjJabStrukturId")!=null) {
                criteria.add(Restrictions.eq("tunjJabStrukturId", (String) mapCriteria.get("tunjJabStrukturId")));
            }

            if (mapCriteria.get("kelompokId")!=null) {
                criteria.add(Restrictions.eq("kelompokId", (String) mapCriteria.get("kelompokId")));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.desc("tunjJabStrukturId"));
        List<ImPayrollTunjanganJabatanStrukturalHistoryEntity> results = criteria.list();
        return results;
    }

    public String getNextTunjanganJabatanStrukturalIdHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_jabatan_struktural_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "TJSH" + sId;
    }
}