
package com.neurix.hris.master.smkCheckList.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkCheckList.model.ImSmkCheckListEntity;
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
public class SmkCheckListDao extends GenericDao<ImSmkCheckListEntity, String> {

    @Override
    protected Class<ImSmkCheckListEntity> getEntityClass() {
        return ImSmkCheckListEntity.class;
    }

    @Override
    public List<ImSmkCheckListEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkCheckListEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("checkListId")!=null) {
                criteria.add(Restrictions.eq("checkListId", (String) mapCriteria.get("checkListId")));
            }
            if (mapCriteria.get("checkListName")!=null) {
                criteria.add(Restrictions.ilike("checkListName", "%" + (String)mapCriteria.get("checkListName") + "%"));
            }
            if (mapCriteria.get("bobot")!=null) {
                criteria.add(Restrictions.eq("bobot", mapCriteria.get("bobot")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String)mapCriteria.get("branchId")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("checkListId"));

        List<ImSmkCheckListEntity> results = criteria.list();

        return results;
    }

    public String getNextSmkCheckListId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_check_list')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "CL"+sId;
    }

    public List<ImSmkCheckListEntity> getListSmkCheckList(String term) throws HibernateException {

        List<ImSmkCheckListEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkCheckListEntity.class)
                .add(Restrictions.ilike("smkCheckListName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("smkCheckListId"))
                .list();

        return results;
    }

}
