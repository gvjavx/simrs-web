package com.neurix.hris.master.pelatihanJabatan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.pelatihanJabatan.model.ImPelatihanJabatanEntitiy;
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
public class PelatihanJabatanDao extends GenericDao<ImPelatihanJabatanEntitiy, String> {

    @Override
    protected Class<ImPelatihanJabatanEntitiy> getEntityClass() {
        return ImPelatihanJabatanEntitiy.class;
    }

    @Override
    public List<ImPelatihanJabatanEntitiy> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPelatihanJabatanEntitiy.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kelompok_id")!=null) {
                criteria.add(Restrictions.eq("pelatihanId", (String) mapCriteria.get("kelompok_id")));
            }
            if (mapCriteria.get("kelompok_name")!=null) {
                criteria.add(Restrictions.ilike("pelatihanName", "%" + (String)mapCriteria.get("kelompok_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pelatihanId"));

        List<ImPelatihanJabatanEntitiy> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPelatihanJabatanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pelatihan_jabatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());
        return "PJ"+sId;
    }


    public List<ImPelatihanJabatanEntitiy> getListPelatihanJabatan(String term) throws HibernateException {

        List<ImPelatihanJabatanEntitiy> results = this.sessionFactory.getCurrentSession().createCriteria(ImPelatihanJabatanEntitiy.class)
                .add(Restrictions.ilike("pelatihanName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("pelatihanId"))
                .list();

        return results;
    }
    
}
