package com.neurix.hris.transaksi.absensi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.absensi.model.ImMesinAbsensiEntity;
import com.neurix.hris.transaksi.absensi.model.ImMesinAbsensiHistoryEntity;
import com.neurix.hris.transaksi.absensi.model.MesinAbsensiEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class MesinDao extends GenericDao<ImMesinAbsensiEntity, String> {

    @Override
    protected Class<ImMesinAbsensiEntity> getEntityClass() {
        return ImMesinAbsensiEntity.class;
    }

    @Override
    public List<ImMesinAbsensiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMesinAbsensiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("mesin_id") != null) {
                criteria.add(Restrictions.eq("mesinId", (String) mapCriteria.get("mesin_id")));
            }
            if (mapCriteria.get("mesin_name") != null) {
                criteria.add(Restrictions.eq("mesinName", (String) mapCriteria.get("mesin_name")));
            }
            if (mapCriteria.get("mesin_sn") != null) {
                criteria.add(Restrictions.eq("mesinSn", (String) mapCriteria.get("mesin_sn")));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("mesinId"));

        List<ImMesinAbsensiEntity> results = criteria.list();

        return results;
    }

    public void addAndSaveHistory(ImMesinAbsensiHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

    public String getNextMesinAbsensiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master_mesin_absensi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "MA" + sId;
    }

    public String getNextMesinAbsensiIdHistory() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_master_mesin_absensi_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "MH" + sId;
    }
}
