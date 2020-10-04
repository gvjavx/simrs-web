package com.neurix.simrs.master.ruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.ruangan.model.MtSimrsRuanganTempatTidurEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TempatTidurDao extends GenericDao<MtSimrsRuanganTempatTidurEntity, String> {

    @Override
    protected Class<MtSimrsRuanganTempatTidurEntity> getEntityClass() {
        return MtSimrsRuanganTempatTidurEntity.class;
    }

    @Override
    public List<MtSimrsRuanganTempatTidurEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsRuanganTempatTidurEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_tempat_tidur") != null) {
                criteria.add(Restrictions.eq("idTempatTidur", (String) mapCriteria.get("id_tempat_tidur")));
            }
            if (mapCriteria.get("nama_ruangan") != null) {
                criteria.add(Restrictions.ilike("nama_tempat_tidur", "%" + (String) mapCriteria.get("nama_tempat_tidur") + "%"));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
            if (mapCriteria.get("status") != null) {
                criteria.add(Restrictions.eq("status", (String) mapCriteria.get("status")));
            }
        }
        criteria.addOrder(Order.asc("idTempatTidur"));
        List<MtSimrsRuanganTempatTidurEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_tempat_tidur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "RTT"+sId;
    }
}