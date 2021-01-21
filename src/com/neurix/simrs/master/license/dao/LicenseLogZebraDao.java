package com.neurix.simrs.master.license.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.license.model.ImLicenseZebraLogEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LicenseLogZebraDao extends GenericDao<ImLicenseZebraLogEntity, String> {


    @Override
    protected Class<ImLicenseZebraLogEntity> getEntityClass() {
        return ImLicenseZebraLogEntity.class;
    }

    @Override
    public List<ImLicenseZebraLogEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImLicenseZebraLogEntity.class);


        if (mapCriteria != null) {
            if (mapCriteria.get("license_id") != null) {
                criteria.add(Restrictions.eq("licenseId", mapCriteria.get("license_id")));
            }
            if (mapCriteria.get("license_key") != null) {
                criteria.add(Restrictions.eq("licenseKey", mapCriteria.get("license_key")));
            }
            if (mapCriteria.get("device_id") != null) {
                criteria.add(Restrictions.eq("deviceId", mapCriteria.get("device_id")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

//        criteria.addOrder(Order.asc("license_id"));
        List<ImLicenseZebraLogEntity> results = criteria.list();
        return results;
    }

    public String getNextLicenseLogId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_license_zebra_log')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LCL" + sId;
    }
}
