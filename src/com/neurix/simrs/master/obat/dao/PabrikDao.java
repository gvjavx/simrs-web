package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsPabrikObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PabrikDao extends GenericDao<ImSimrsPabrikObatEntity, String>{

    @Override
    protected Class<ImSimrsPabrikObatEntity> getEntityClass() {
        return ImSimrsPabrikObatEntity.class;
    }

    @Override
    public List<ImSimrsPabrikObatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPabrikObatEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id") != null) {
                criteria.add(Restrictions.eq("id", (String) mapCriteria.get("id")));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + (String) mapCriteria.get("nama") + "%"));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        List<ImSimrsPabrikObatEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pabrik_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
