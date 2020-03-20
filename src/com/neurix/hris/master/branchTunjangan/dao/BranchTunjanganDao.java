
package com.neurix.hris.master.branchTunjangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.branchTunjangan.model.ImBranchTunjanganEntity;
import com.neurix.hris.master.branchTunjangan.model.ImBranchTunjanganHistoryEntity;
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
public class BranchTunjanganDao extends GenericDao<ImBranchTunjanganEntity, String> {

    @Override
    protected Class<ImBranchTunjanganEntity> getEntityClass() {
        return ImBranchTunjanganEntity.class;
    }

    @Override
    public List<ImBranchTunjanganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBranchTunjanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("branch_tunjangan_id")!=null) {
                criteria.add(Restrictions.eq("branchTunjanganId", (String) mapCriteria.get("branch_tunjangan_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("tunjangan_id")!=null) {
                criteria.add(Restrictions.eq("tunjanganId", (String) mapCriteria.get("tunjangan_id")));
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("branchTunjanganId"));

        List<ImBranchTunjanganEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBranchTunjanganId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_branch_tunjangan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "BT"+sId;
    }

    public String getNextBranchTunjanganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_branch_tunjangan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "H"+sId;
    }

    public List<ImBranchTunjanganEntity> getListBranchTunjangan(String term) throws HibernateException {

        List<ImBranchTunjanganEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBranchTunjanganEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("branchTunjanganId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImBranchTunjanganHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }

}
