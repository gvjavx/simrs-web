package com.neurix.hris.master.perjalananDinas.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasEntity;
import com.neurix.hris.master.perjalananDinas.model.ImPerjalananDinasHistoryEntity;
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
public class PerjalananDinasDao extends GenericDao<ImPerjalananDinasEntity, String> {

    @Override
    protected Class<ImPerjalananDinasEntity> getEntityClass() {
        return ImPerjalananDinasEntity.class;
    }

    @Override
    public List<ImPerjalananDinasEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("perjalananDinas_id")!=null) {
                criteria.add(Restrictions.eq("perjalananDinasId", (String) mapCriteria.get("perjalananDinas_id")));
            }

            if (mapCriteria.get("biaya_dinas_id")!=null) {
                criteria.add(Restrictions.eq("biayaDinasId", (String) mapCriteria.get("biaya_dinas_id")));
            }

            if (mapCriteria.get("dinas")!=null) {
                criteria.add(Restrictions.eq("dinas", (String) mapCriteria.get("dinas")));
            }

            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("perjalananDinasId"));

        List<ImPerjalananDinasEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextPerjalananDinasId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perjalanan_dinas')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "PD"+sId;
    }

    public String getNextPerjalananDinasHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perjalanan_dinas_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HP"+sId;
    }

    public List<ImPerjalananDinasEntity> getListPerjalananDinas(String term) throws HibernateException {
        List<ImPerjalananDinasEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("perjalananDinasId"))
                .list();
        return results;
    }

    public List<ImPerjalananDinasEntity> getListPerjalananDinasSppd(String kelompokId, String golonganId, String tipeBiaya, String dinas) throws HibernateException {
        List<ImPerjalananDinasEntity> results = null;
        if(kelompokId.equalsIgnoreCase("KL07")){
            results = this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class)
                    .add(Restrictions.eq("flag", "Y"))
                    .add(Restrictions.eq("kelompokId", kelompokId))
                    .add(Restrictions.eq("golonganId", golonganId))
                    .add(Restrictions.eq("biayaDinasId", tipeBiaya))
                    .add(Restrictions.eq("dinas", dinas))
                    .addOrder(Order.asc("perjalananDinasId"))
                    .list();
        }else{
            results = this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class)
                    .add(Restrictions.eq("flag", "Y"))
                    .add(Restrictions.eq("kelompokId", kelompokId))
                    .add(Restrictions.eq("biayaDinasId", tipeBiaya))
                    .add(Restrictions.eq("dinas", dinas))
                    .addOrder(Order.asc("perjalananDinasId"))
                    .list();
        }
        return results;
    }

    public List<ImPerjalananDinasEntity> getListBiaya(String golonganId, String tipeBiaya, String dinas) throws HibernateException {
        List<ImPerjalananDinasEntity> results = null;
        results = this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("biayaDinasId", tipeBiaya))
                .add(Restrictions.eq("dinas", dinas))
                .addOrder(Order.asc("perjalananDinasId"))
                .list();

        return results;
    }

    public List<ImPerjalananDinasEntity> getListBiaya(String kelompok, String golonganId, String tipeBiaya, String dinas) throws HibernateException {
        List<ImPerjalananDinasEntity> results = null;
        results = this.sessionFactory.getCurrentSession().createCriteria(ImPerjalananDinasEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("kelompokId", kelompok))
                .add(Restrictions.eq("biayaDinasId", tipeBiaya))
                .add(Restrictions.eq("dinas", dinas))
                .addOrder(Order.asc("perjalananDinasId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImPerjalananDinasHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}