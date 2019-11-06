package com.neurix.hris.master.rsKerjasama.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaEntity;
import com.neurix.hris.master.rsKerjasama.model.ImRsKerjasamaHistoryEntity;
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
public class RsKerjasamaDao extends GenericDao<ImRsKerjasamaEntity, String> {

    @Override
    protected Class<ImRsKerjasamaEntity> getEntityClass() {
        return ImRsKerjasamaEntity.class;
    }

    @Override
    public List<ImRsKerjasamaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImRsKerjasamaEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("rs_id")!=null) {
                criteria.add(Restrictions.eq("rsId", (String) mapCriteria.get("rs_id")));
            }

            if (mapCriteria.get("kode_rs")!=null) {
                criteria.add(Restrictions.ilike("kodeRs", "%" + (String) mapCriteria.get("kode_rs") + "%"));
            }

            if (mapCriteria.get("rs_name")!=null) {
                criteria.add(Restrictions.ilike("rsName", "%" + (String)mapCriteria.get("rs_name") + "%"));
            }

            if (mapCriteria.get("alamat_rs")!=null) {
                criteria.add(Restrictions.ilike("alamatRs", "%" + (String)mapCriteria.get("alamat_rs") + "%"));
            }

            if (mapCriteria.get("tipe_rs")!=null) {
                criteria.add(Restrictions.ilike("tipeRs", "%" + (String)mapCriteria.get("tipe_rs") + "%"));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("rsId"));

        List<ImRsKerjasamaEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextRsKerjasamaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rs_kerjasama')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "RS"+sId;
    }
    public String getNextRsKerjasamaHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rs_kerjasama_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "HRS"+sId;
    }

    public List<ImRsKerjasamaEntity> getListRsKerjasama(String term) throws HibernateException {

        List<ImRsKerjasamaEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImRsKerjasamaEntity.class)
                .add(Restrictions.ilike("rsName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("rsId"))
                .list();

        return results;
    }

    public void addAndSaveHistory(ImRsKerjasamaHistoryEntity entity) throws HibernateException {
        this.sessionFactory.getCurrentSession().save(entity);
    }
}
