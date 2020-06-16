package com.neurix.simrs.master.kurir.dao;

import com.neurix.simrs.master.kurir.model.ImSimrsKurirEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gondok
 * Tuesday, 16/06/20 10:39
 */
public class KurirDao extends GenericDao<ImSimrsKurirEntity, String> {
    @Override
    protected Class<ImSimrsKurirEntity> getEntityClass() {
        return ImSimrsKurirEntity.class;
    }

    @Override
    public List<ImSimrsKurirEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKurirEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_kurir") != null) {
                criteria.add(Restrictions.eq("idKurir", mapCriteria.get("id_kurir").toString()));
            }
            if (mapCriteria.get("nama") != null) {
                criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
            }
            if (mapCriteria.get("no_polisi") != null) {
                criteria.add(Restrictions.eq("noPolisi", mapCriteria.get("no_polisi").toString()));
            }
            if (mapCriteria.get("no_ktp") != null) {
                criteria.add(Restrictions.eq("noKtp", mapCriteria.get("no_ktp").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
            if (mapCriteria.get("branch_id") != null) {
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("password") != null) {
                criteria.add(Restrictions.eq("password", mapCriteria.get("password").toString()));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsKurirEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public String getNextIdPasien() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kurir')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%04d", iter.next());
        return sId;
    }
}
