package com.neurix.simrs.transaksi.icu.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.icu.model.ItSimrsDetailIcuEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailIcuDao extends GenericDao<ItSimrsDetailIcuEntity, String> {

    @Override
    protected Class<ItSimrsDetailIcuEntity> getEntityClass() {
        return ItSimrsDetailIcuEntity.class;
    }

    @Override
    public List<ItSimrsDetailIcuEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDetailIcuEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_detail_icu")!=null) {
                criteria.add(Restrictions.eq("idDetailIcu", (String) mapCriteria.get("id_detail_icu")));
            }
            if (mapCriteria.get("id_header_icu")!=null) {
                criteria.add(Restrictions.eq("idHeaderIcu", (String) mapCriteria.get("id_header_icu")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailChekcup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("created_date")!=null) {
                criteria.add(Restrictions.eq("createdDate", (Timestamp) mapCriteria.get("created_date")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idDetailIcu"));

        List<ItSimrsDetailIcuEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_icu')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}