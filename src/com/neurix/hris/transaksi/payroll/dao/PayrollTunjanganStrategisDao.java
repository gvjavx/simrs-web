package com.neurix.hris.transaksi.payroll.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.payroll.model.ImPayrollTunjanganStrategisEntity;
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
public class PayrollTunjanganStrategisDao extends GenericDao<ImPayrollTunjanganStrategisEntity, String> {

    @Override
    protected Class<ImPayrollTunjanganStrategisEntity>  getEntityClass() {
        return ImPayrollTunjanganStrategisEntity.class;
    }

    @Override
    public List<ImPayrollTunjanganStrategisEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrategisEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("tunj_strategis_id") != null) {
                criteria.add(Restrictions.eq("tunjStrategisId", (String) mapCriteria.get("tunj_strategis_id")));
            }
            if (mapCriteria.get("position_id") != null) {
                criteria.add(Restrictions.eq("positionId", (String) mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("golongan_id") != null){
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        // Order by
        criteria.addOrder(Order.desc("tunjStrategisId"));

        List<ImPayrollTunjanganStrategisEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextTunjStrategis() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_payroll_skala_gaji')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "SG" + sId;
    }

    public List<ImPayrollTunjanganStrategisEntity> getData2(String positionId) throws HibernateException {
        List<ImPayrollTunjanganStrategisEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrategisEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
    public List<ImPayrollTunjanganStrategisEntity> getDataTunjStrategisById(String positionId, String golonganId) throws HibernateException {
        List<ImPayrollTunjanganStrategisEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrategisEntity.class)
                .add(Restrictions.eq("positionId", positionId))
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

    public List<ImPayrollTunjanganStrategisEntity> getListPosition(String term, String golonganId) throws HibernateException {

        List<ImPayrollTunjanganStrategisEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPayrollTunjanganStrategisEntity.class)
                .add(Restrictions.eq("positionId",term))
                .add(Restrictions.eq("golonganId",golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("positionId"))
                .list();

        return results;
    }
}
