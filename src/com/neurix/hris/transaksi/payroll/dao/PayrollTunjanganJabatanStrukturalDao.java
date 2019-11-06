package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganJabatanStrukturalEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
            if (mapCriteria.get("tunj_jab_struktur_id")!=null) {
                criteria.add(Restrictions.eq("tunjJabStrukturId", (String) mapCriteria.get("tunj_jab_struktur_id")));
            }

            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("positionId")));
            }

            if (mapCriteria.get("nilai")!=null) {
                criteria.add(Restrictions.eq("nilai", (String) mapCriteria.get("nilai")));
            }
            criteria.add(Restrictions.eq("flag", "Y"));


        }

        // Order by
        criteria.addOrder(Order.desc("tunj_jab_struktur_id"));

        List<ImPayrollTunjanganJabatanStrukturalEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSkalaGaji() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_paysroll_skala_gaji')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "SG" + sId;
    }

    public List<ImPayrollTunjanganJabatanStrukturalEntity> getData2(String positionId, String branchId) throws HibernateException {
        List<ImPayrollTunjanganJabatanStrukturalEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganJabatanStrukturalEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

}
