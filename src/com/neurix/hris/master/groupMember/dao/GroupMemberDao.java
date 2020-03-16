package com.neurix.hris.master.groupMember.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.groupMember.model.ImtHrisGroupMember;
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
 * Created by thinkpad on 19/03/2018.
 */
public class GroupMemberDao extends GenericDao<ImtHrisGroupMember,String> {
    @Override
    protected Class<ImtHrisGroupMember> getEntityClass() {
        return ImtHrisGroupMember.class;
    }

    @Override
    public List<ImtHrisGroupMember> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImtHrisGroupMember.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("group_id")!=null) {
                criteria.add(Restrictions.eq("groupId", (String) mapCriteria.get("group_id")));
            }
            if (mapCriteria.get("group_member_id")!=null) {
                criteria.add(Restrictions.eq("groupMemberId", (String) mapCriteria.get("group_member_id")));
            }
            if (mapCriteria.get("nip")!=null) {
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("groupMemberId"));

        List<ImtHrisGroupMember> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextGroupMemberId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_group_member')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "GM" + sId;
    }
}
