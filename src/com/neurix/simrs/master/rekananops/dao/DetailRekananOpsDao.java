package com.neurix.simrs.master.rekananops.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsDetailRekananOpsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailRekananOpsDao extends GenericDao<ImSimrsDetailRekananOpsEntity, String> {

    @Override
    protected Class<ImSimrsDetailRekananOpsEntity> getEntityClass() {
        return ImSimrsDetailRekananOpsEntity.class;
    }

    @Override
    public List<ImSimrsDetailRekananOpsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_detail_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idDetailRekananOps", mapCriteria.get("id_detail_rekanan_ops").toString()));
            }

            if (mapCriteria.get("id_rekanan_ops") != null) {
                criteria.add(Restrictions.eq("idRekananOps", mapCriteria.get("id_rekanan_ops").toString()));
            }

            if (mapCriteria.get("is_bpjs") != null) {
                criteria.add(Restrictions.eq("isBpjs", mapCriteria.get("is_bpjs").toString()));
            }

            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }
        criteria.addOrder(Order.desc("createdDate"));
        List<ImSimrsDetailRekananOpsEntity> results = criteria.list();
        return results;
    }
    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_rekanan_ops')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "DRK"+sId;
    }

    public List<ImSimrsDetailRekananOpsEntity> getDetailRekananOps(String idRekananOps, String branchId ) throws HibernateException {
        List<ImSimrsDetailRekananOpsEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailRekananOpsEntity.class)
                .add(Restrictions.eq("idRekananOps", idRekananOps))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)

        return results;
    }
}
