package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsHeaderDetailCheckupLogEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HeaderCheckupLogDao extends GenericDao<ItSimrsHeaderDetailCheckupLogEntity, String> {

    @Override
    protected Class<ItSimrsHeaderDetailCheckupLogEntity> getEntityClass() {
        return ItSimrsHeaderDetailCheckupLogEntity.class;
    }

    @Override
    public List<ItSimrsHeaderDetailCheckupLogEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHeaderDetailCheckupLogEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", mapCriteria.get("no_checkup").toString()));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }
        }

        List<ItSimrsHeaderDetailCheckupLogEntity> result = criteria.list();
        return result;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_header_checkup_log')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
