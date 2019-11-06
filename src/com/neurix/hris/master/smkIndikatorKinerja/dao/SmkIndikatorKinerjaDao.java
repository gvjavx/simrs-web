
package com.neurix.hris.master.smkIndikatorKinerja.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkIndikatorKinerja.model.ImSmkIndikatorKinerjaEntity;
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
public class SmkIndikatorKinerjaDao extends GenericDao<ImSmkIndikatorKinerjaEntity, String> {

    @Override
    protected Class<ImSmkIndikatorKinerjaEntity> getEntityClass() {
        return ImSmkIndikatorKinerjaEntity.class;
    }

    @Override
    public List<ImSmkIndikatorKinerjaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkIndikatorKinerjaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("smkIndikatorKinerja_id")!=null) {
                criteria.add(Restrictions.eq("indikatorKinerjaId", (String) mapCriteria.get("smkIndikatorKinerja_id")));
            }
            if (mapCriteria.get("position_id")!=null) {
                criteria.add(Restrictions.eq("positionId", (String)mapCriteria.get("position_id")));
            }
            if (mapCriteria.get("satuan_target")!=null) {
                criteria.add(Restrictions.eq("satuanTarget", (String)mapCriteria.get("satuan_target")));
            }
            if (mapCriteria.get("bobot")!=null) {
                criteria.add(Restrictions.ilike("bobot", "%" + (Long)mapCriteria.get("bobot") + "%"));
            }
            if (mapCriteria.get("target")!=null) {
                criteria.add(Restrictions.ilike("target", "%" + (Long)mapCriteria.get("target") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("indikatorKinerjaId"));
        List<ImSmkIndikatorKinerjaEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkIndikatorKinerjaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_indikator_kinerja')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "IK"+sId;
    }

    public String getNextSmkIndikatorKinerjaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smkIndikatorKinerja_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImSmkIndikatorKinerjaEntity> getListSmkIndikatorKinerja(String term) throws HibernateException {

        List<ImSmkIndikatorKinerjaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkIndikatorKinerjaEntity.class)
                .add(Restrictions.ilike("smkIndikatorKinerjaName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("smkIndikatorKinerjaId"))
                .list();

        return results;
    }

}
