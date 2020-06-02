package com.neurix.hris.master.golongan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.golongan.model.Golongan;
import com.neurix.hris.master.golongan.model.ImGolonganEntity;
import com.neurix.hris.master.golongan.model.ImGolonganHistoryEntity;
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
public class GolonganHistoryDao extends GenericDao<ImGolonganHistoryEntity, String> {

    @Override
    protected Class<ImGolonganHistoryEntity> getEntityClass() {
        return ImGolonganHistoryEntity.class;
    }

    @Override
    public List<ImGolonganHistoryEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImGolonganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }
            if (mapCriteria.get("golongan_name")!=null) {
                criteria.add(Restrictions.ilike("golonganName", "%" + (String)mapCriteria.get("golongan_name") + "%"));
            }
            if (mapCriteria.get("grade_level")!=null) {
                criteria.add(Restrictions.eq("level", (Integer) mapCriteria.get("grade_level")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.desc("golonganId"));
        List<ImGolonganHistoryEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextGolonganHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_golongan_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }
}
