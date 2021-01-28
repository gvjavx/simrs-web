package com.neurix.simrs.master.license.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.license.model.ImLicenseZebraEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LicenseZebraDao extends GenericDao<ImLicenseZebraEntity, String> {


    @Override
    protected Class<ImLicenseZebraEntity> getEntityClass() {
        return ImLicenseZebraEntity.class;
    }

    @Override
    public List<ImLicenseZebraEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImLicenseZebraEntity.class);


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

        List<ImLicenseZebraEntity> results = criteria.list();
        return results;
    }

    public String getNextLicenseId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_license_zebra')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LCZ" + sId;
    }

    public List<ImLicenseZebraEntity> getDeviceId(String deviceId) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImLicenseZebraEntity.class);
        criteria.add(Restrictions.ilike("deviceId", deviceId));
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImLicenseZebraEntity> listOfResult = criteria.list();
        return listOfResult;
    }
}
