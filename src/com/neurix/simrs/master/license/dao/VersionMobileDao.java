package com.neurix.simrs.master.license.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.license.model.ImVersionMobileEntity;
import com.neurix.simrs.master.license.model.ImVersionZebraEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VersionMobileDao extends GenericDao<ImVersionMobileEntity, String> {
    @Override
    protected Class<ImVersionMobileEntity> getEntityClass() {
        return ImVersionMobileEntity.class;
    }

    @Override
    public List<ImVersionMobileEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImVersionMobileEntity.class);


        if (mapCriteria != null) {
            if (mapCriteria.get("id_version_mobile") != null) {
                criteria.add(Restrictions.eq("idVersionMobile", mapCriteria.get("idVersionMobile")));
            }
            if (mapCriteria.get("nama_version") != null) {
                criteria.add(Restrictions.eq("namaVersion", mapCriteria.get("nama_version")));
            }
            if (mapCriteria.get("os") != null) {
                criteria.add(Restrictions.eq("os", mapCriteria.get("os")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        List<ImVersionMobileEntity> results = criteria.list();
        return results;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_version_mobile')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LCM" + sId;
    }
}
