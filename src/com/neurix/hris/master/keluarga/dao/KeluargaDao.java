
package com.neurix.hris.master.keluarga.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.keluarga.model.ImKeluargaEntity;

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
public class KeluargaDao extends GenericDao<ImKeluargaEntity, String> {

    @Override
    protected Class<ImKeluargaEntity> getEntityClass() {
        return ImKeluargaEntity.class;
    }

    @Override
    public List<ImKeluargaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImKeluargaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("keluarga_id")!=null) {
                criteria.add(Restrictions.eq("keluargaId", (String) mapCriteria.get("keluarga_id")));
            }
            if (mapCriteria.get("nip")!=null) {
            }

        }

        criteria.add(Restrictions.eq("nip", (String)mapCriteria.get("nip")));
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("keluargaId"));
        List<ImKeluargaEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextKeluargaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_keluarga')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "K"+sId;
    }

    public String getNextKeluargaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_keluarga_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImKeluargaEntity> getListKeluarga(String term) throws HibernateException {

        List<ImKeluargaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKeluargaEntity.class)
                .add(Restrictions.ilike("name",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("keluargaId"))
                .list();

        return results;
    }

    public List<ImKeluargaEntity> getListKeluargaById(String term, String id) throws HibernateException {

        List<ImKeluargaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImKeluargaEntity.class)
//                .add(Restrictions.ilike("name",term))
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("nip", id))
                .addOrder(Order.asc("keluargaId"))
                .list();

        return results;
    }


}
