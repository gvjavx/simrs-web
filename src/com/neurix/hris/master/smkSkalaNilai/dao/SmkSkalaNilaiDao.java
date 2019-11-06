
package com.neurix.hris.master.smkSkalaNilai.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkSkalaNilai.model.ImSmkSkalaNilaiEntity;
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
public class SmkSkalaNilaiDao extends GenericDao<ImSmkSkalaNilaiEntity, String> {

    @Override
    protected Class<ImSmkSkalaNilaiEntity> getEntityClass() {
        return ImSmkSkalaNilaiEntity.class;
    }

    @Override
    public List<ImSmkSkalaNilaiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkSkalaNilaiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("skalaNilaiId")!=null) {
                criteria.add(Restrictions.eq("skalaNilaiId", (String) mapCriteria.get("skalaNilaiId")));
            }
            if (mapCriteria.get("kodeSkala")!=null) {
                criteria.add(Restrictions.eq("kodeSkala", (String) mapCriteria.get("kodeSkala")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branchId")));
            }
            if (mapCriteria.get("skalaName")!=null) {
                criteria.add(Restrictions.ilike("skalaName", "%" + (String)mapCriteria.get("skalaName") + "%"));
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
        criteria.addOrder(Order.asc("skalaNilaiId"));

        List<ImSmkSkalaNilaiEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSmkSkalaNilaiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_skala_nilai')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return "SN"+sId;
    }

}
