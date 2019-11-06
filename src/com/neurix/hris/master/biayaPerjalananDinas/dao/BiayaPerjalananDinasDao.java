package com.neurix.hris.master.biayaPerjalananDinas.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.biayaPerjalananDinas.model.ImBiayaPerjalananDinasEntity;
import com.neurix.hris.master.biayaPerjalananDinas.model.ImBiayaPerjalananDinasHistoryEntity;
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
public class BiayaPerjalananDinasDao extends GenericDao<ImBiayaPerjalananDinasEntity, String> {

    @Override
    protected Class<ImBiayaPerjalananDinasEntity> getEntityClass() {
        return ImBiayaPerjalananDinasEntity.class;
    }

    @Override
    public List<ImBiayaPerjalananDinasEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImBiayaPerjalananDinasEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("biaya_dinas_id")!=null) {
                criteria.add(Restrictions.eq("biayaId", (String) mapCriteria.get("biaya_dinas_id")));
            }
            if (mapCriteria.get("biaya_name")!=null) {
                criteria.add(Restrictions.ilike("biayaName", "%" + (String)mapCriteria.get("biaya_name") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("biayaId"));

        List<ImBiayaPerjalananDinasEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBiayaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_biaya_perjalanan_dinas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "BPD"+sId;
    }

    public String getNextBiayaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_biaya_perjalanan_dinas_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HBP"+sId;
    }

    public List<ImBiayaPerjalananDinasEntity> getListBiayaPerjalananDinas(String term) throws HibernateException {

        List<ImBiayaPerjalananDinasEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImBiayaPerjalananDinasEntity.class)
                .add(Restrictions.ilike("biayaName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("biayaId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImBiayaPerjalananDinasHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
