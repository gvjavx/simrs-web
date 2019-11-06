
package com.neurix.hris.master.smkSkalaPointPrestasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkSkalaPointPrestasi.model.ImSmkSkalaPointPrestasiEntity;
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
public class SmkSkalaPointPrestasiDao extends GenericDao<ImSmkSkalaPointPrestasiEntity, String> {

    @Override
    protected Class<ImSmkSkalaPointPrestasiEntity> getEntityClass() {
        return ImSmkSkalaPointPrestasiEntity.class;
    }

    @Override
    public List<ImSmkSkalaPointPrestasiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkSkalaPointPrestasiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pointPrestasiId")!=null) {
                criteria.add(Restrictions.eq("pointPrestasiId", (String) mapCriteria.get("pointPrestasiId")));
            }
            if (mapCriteria.get("point")!=null) {
                criteria.add(Restrictions.eq("point", mapCriteria.get("point")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }
            if (mapCriteria.get("nilaiAtas")!=null) {
                criteria.add(Restrictions.eq("nilaiAtas", mapCriteria.get("nilaiAtas")));
            }
            if (mapCriteria.get("nilaiBawah")!=null) {
                criteria.add(Restrictions.eq("nilaiBawah", mapCriteria.get("nilaiBawah")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pointPrestasiId"));

        List<ImSmkSkalaPointPrestasiEntity> results = criteria.list();

        return results;
    }


    public List<ImSmkSkalaPointPrestasiEntity> getByCriteriaPrestasi(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkSkalaPointPrestasiEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("point_prestasi_id")!=null) {
                criteria.add(Restrictions.le("nilaiBawah",mapCriteria.get("point_prestasi_id")));
                criteria.add(Restrictions.ge("nilaiAtas",mapCriteria.get("point_prestasi_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));

        // Order by
        criteria.addOrder(Order.asc("pointPrestasiId"));
        List<ImSmkSkalaPointPrestasiEntity> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextSmkSkalaPointPrestasiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_skala_point_prestasi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "PP"+sId;
    }

}
