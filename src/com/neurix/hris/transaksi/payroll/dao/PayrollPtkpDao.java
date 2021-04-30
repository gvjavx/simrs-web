
package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollPtkpEntity;
import com.neurix.hris.transaksi.payroll.model.ImPayrollPtkpEntity;
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
public class PayrollPtkpDao extends GenericDao<ImPayrollPtkpEntity, String> {

    @Override
    protected Class<ImPayrollPtkpEntity>  getEntityClass() {
        return ImPayrollPtkpEntity.class;
    }

    @Override
    public List<ImPayrollPtkpEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollPtkpEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("faktor_keluarga_id")!=null) {
                criteria.add(Restrictions.eq("faktorKeluargaId", (String) mapCriteria.get("faktor_keluarga_id")));
            }

            if (mapCriteria.get("status_keluarga")!=null) {
                criteria.add(Restrictions.eq("statusKeluarga", (String) mapCriteria.get("status_keluarga")));
            }

            if (mapCriteria.get("jumlah_anak")!=null) {
                criteria.add(Restrictions.eq("jumlahAnak", (String) mapCriteria.get("jumlah_anak")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }
            criteria.add(Restrictions.eq("flag", "Y"));


        }

        // Order by
        criteria.addOrder(Order.desc("umk_id"));

        List<ImPayrollPtkpEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextFaktorKeluarga() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gasji')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "SG" + sId;
    }

    public List<ImPayrollPtkpEntity> getDataPtkp(String statusKeluarga, int jumlahTanggungan) throws HibernateException {
        List<ImPayrollPtkpEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollPtkpEntity.class)
                .add(Restrictions.eq("statusKeluarga", statusKeluarga))
                .add(Restrictions.eq("jumlahTanggungan", jumlahTanggungan))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
