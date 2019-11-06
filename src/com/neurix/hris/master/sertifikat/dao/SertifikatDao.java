package com.neurix.hris.master.sertifikat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.sertifikat.model.ImSertifikatEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SertifikatDao extends GenericDao<ImSertifikatEntity, String> {

    @Override
    protected Class<ImSertifikatEntity> getEntityClass() {
        return ImSertifikatEntity.class;
    }

    @Override
    public List<ImSertifikatEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public String getNextSertifikat() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sertifikat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return "SF"+sId;
    }

    public List<ImSertifikatEntity> getAllData(String nip) throws HibernateException {
        List<ImSertifikatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSertifikatEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.desc("tanggalPengesahan"))
                .list();
        return results;
    }

    public List<ImSertifikatEntity> getData(String nip) throws HibernateException {
        List<ImSertifikatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSertifikatEntity.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}