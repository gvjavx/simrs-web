package com.neurix.hris.transaksi.medicalrecord.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisBuktiPengobatanEntity;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisMedicalRecordEntity;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisPengobatanEntity;
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
 * Created by thinkpad on 19/03/2018.
 */
public class PengobatanDao extends GenericDao<ItHrisPengobatanEntity,String> {
    @Override
    protected Class<ItHrisPengobatanEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisPengobatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisPengobatanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("pengobatan_id")!=null) {
                criteria.add(Restrictions.eq("pengobatanId", (String) mapCriteria.get("pengobatan_id")));
            }
            if (mapCriteria.get("medical_record_id")!=null) {
                criteria.add(Restrictions.ilike("medicalRecordId", "%" + (String)mapCriteria.get("medical_record_id") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("pengobatanId"));
        List<ItHrisPengobatanEntity> results = criteria.list();
        return results;
    }



    // Generate surrogate id from postgre
    public String getNextPengobatanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_it_pengobatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

    public List<ItHrisPengobatanEntity> getPengobatanByMedicalid(String medicalId) throws HibernateException {
        List<ItHrisPengobatanEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ItHrisPengobatanEntity.class)
                .add(Restrictions.eq("flag", "Y"))
                .add(Restrictions.eq("medicalRecordId", medicalId))
                .addOrder(Order.desc("createdDate"))
                .list();
        return results;
    }

}
