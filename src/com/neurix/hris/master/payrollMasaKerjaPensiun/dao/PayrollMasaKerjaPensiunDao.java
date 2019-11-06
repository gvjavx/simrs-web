
package com.neurix.hris.master.payrollMasaKerjaPensiun.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.payrollMasaKerjaPensiun.model.ImPayrollMasaKerjaPensiunEntity;
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
public class PayrollMasaKerjaPensiunDao extends GenericDao<ImPayrollMasaKerjaPensiunEntity, String> {

    @Override
    protected Class<ImPayrollMasaKerjaPensiunEntity> getEntityClass() {
        return ImPayrollMasaKerjaPensiunEntity.class;
    }

    @Override
    public List<ImPayrollMasaKerjaPensiunEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollMasaKerjaPensiunEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("masaKerja_id")!=null) {
                criteria.add(Restrictions.eq("masaKerjaPensiunId", (String) mapCriteria.get("masaKerja_id")));
            }

            if (mapCriteria.get("tahun_dari")!=null) {
                criteria.add(Restrictions.ge("tahunDari", (int) mapCriteria.get("tahun_dari")));
            }

            if (mapCriteria.get("tahun_sampai")!=null) {
                criteria.add(Restrictions.le("tahunSampai", (int) mapCriteria.get("tahun_sampai")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.desc("masaKerjaPensiunId"));

        List<ImPayrollMasaKerjaPensiunEntity> results = criteria.list();

        return results;
    }

    public String getNextMasaKerjaPensiunId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_masa_kerja_pensiun')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "MKP"+sId;
    }

    public List<ImPayrollMasaKerjaPensiunEntity> getListMasaKerjaPensiun() throws HibernateException {
        List<ImPayrollMasaKerjaPensiunEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollMasaKerjaPensiunEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }


}
