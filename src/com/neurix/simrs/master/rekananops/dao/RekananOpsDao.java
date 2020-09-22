package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RekananOpsDao extends GenericDao<ImSimrsRekananOpsEntity, String> {

    @Override
    protected Class<ImSimrsRekananOpsEntity> getEntityClass() {
        return ImSimrsRekananOpsEntity.class;
    }

    @Override
    public List<ImSimrsRekananOpsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekananOpsEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idRekananOps", mapCriteria.get("id_rekanan_ops").toString()));
            }

            if (mapCriteria.get("nomor_master") != null) {
                criteria.add(Restrictions.eq("nomorMaster", mapCriteria.get("nomor_master").toString()));
            }

            if (mapCriteria.get("is_bpjs") != null) {
                criteria.add(Restrictions.eq("isBpjs", mapCriteria.get("is_bpjs")));
            }

            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }

        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsRekananOpsEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
