package com.neurix.hris.master.payrollTunjanganJabatanStruktural.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganJabatanStrukturalEntity;
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
public class PayrollTunjanganJabatanStrukturalDao extends GenericDao<ImPayrollTunjanganJabatanStrukturalEntity, String> {

    @Override
    protected Class<ImPayrollTunjanganJabatanStrukturalEntity>  getEntityClass() {
        return ImPayrollTunjanganJabatanStrukturalEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganJabatanStrukturalEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganJabatanStrukturalEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tunjJabatanStrukturalId")!=null) {
                criteria.add(Restrictions.eq("tunjJabatanStrukturalId", (String) mapCriteria.get("tunjJabatanStrukturalId")));
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
        List<ImPayrollTunjanganJabatanStrukturalEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTunjanganJabatanStruktural() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_tunjangan_jabatan_struktural')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "TJS" + sId;
    }

    public List<ImPayrollTunjanganJabatanStrukturalEntity> getData2(String kelompokId) throws HibernateException {
        List<ImPayrollTunjanganJabatanStrukturalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganJabatanStrukturalEntity.class)
                .add(Restrictions.eq("kelompokId", kelompokId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }
}