package com.neurix.simrs.master.license.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.license.model.ImVersionEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VersionDao extends GenericDao<ImVersionEntity, String> {

    @Override
    protected Class<ImVersionEntity> getEntityClass() {
        return ImVersionEntity.class;
    }

    @Override
    public List<ImVersionEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImVersionEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_version") != null) {
                criteria.add(Restrictions.eq("idVersion", mapCriteria.get("id_version")));
            }
            if (mapCriteria.get("version_name") != null) {
                criteria.add(Restrictions.ilike("version_name", "%" + mapCriteria.get("version_name") + "%"));
            }
            if (mapCriteria.get("tipe") != null) {
                criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe")));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        List<ImVersionEntity> results = criteria.list();
        return results;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_version_zebra')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "VSZ" + sId;
    }
}
