
package com.neurix.hris.master.smkSkalaNilaiItem.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkSkalaNilaiItem.model.ImSmkSkalaNilaiItemEntity;
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
public class SmkSkalaNilaiItemDao extends GenericDao<ImSmkSkalaNilaiItemEntity, String> {

    @Override
    protected Class<ImSmkSkalaNilaiItemEntity> getEntityClass() {
        return ImSmkSkalaNilaiItemEntity.class;
    }

    @Override
    public List<ImSmkSkalaNilaiItemEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkSkalaNilaiItemEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skalaNilaiItemId")!=null) {
                criteria.add(Restrictions.eq("skalaNilaiItemId", (String) mapCriteria.get("skalaNilaiItemId")));
            }

            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }

            if (mapCriteria.get("nilaiAtas")!=null) {
                criteria.add(Restrictions.eq("nilaiAtas", mapCriteria.get("nilaiAtas")));
            }

            if (mapCriteria.get("persenBatasAtas")!=null) {
                criteria.add(Restrictions.eq("persenBatasAtas", mapCriteria.get("persenBatasAtas")));
            }
            if (mapCriteria.get("persenBatasBawah")!=null) {
                criteria.add(Restrictions.eq("persenBatasBawah", mapCriteria.get("persenBatasBawah")));
            }

            if (mapCriteria.get("nilaiBawah")!=null) {
                criteria.add(Restrictions.eq("nilaiBawah", mapCriteria.get("nilaiBawah")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("skalaNilaiItemId"));

        List<ImSmkSkalaNilaiItemEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkSkalaNilaiItemId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_skala_nilai_item')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "SNI"+sId;
    }

}
