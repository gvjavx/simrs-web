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
public class BuktiPengobatanDao extends GenericDao<ItHrisBuktiPengobatanEntity,String> {
    @Override
    protected Class<ItHrisBuktiPengobatanEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisBuktiPengobatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisBuktiPengobatanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("bukti_id")!=null) {
                criteria.add(Restrictions.eq("buktiId", (String) mapCriteria.get("bukti_id")));
            }
            if (mapCriteria.get("pengobatan_id")!=null) {
                criteria.add(Restrictions.ilike("pengobatanId", "%" + (String)mapCriteria.get("pengobatan_id") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("buktiId"));
        List<ItHrisBuktiPengobatanEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBuktiId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_bukti_pengobatan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

}
