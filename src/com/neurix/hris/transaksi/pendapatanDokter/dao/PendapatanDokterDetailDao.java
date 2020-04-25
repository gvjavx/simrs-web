package com.neurix.hris.transaksi.pendapatanDokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PendapatanDokterDetailDao extends GenericDao<ItHrisPendapatanDokterDetailEntity, String> {
    @Override
    protected Class<ItHrisPendapatanDokterDetailEntity> getEntityClass() {
        return ItHrisPendapatanDokterDetailEntity.class;
    }

    @Override
    public List<ItHrisPendapatanDokterDetailEntity> getByCriteria(Map mapCriteria) {
        return null;
    }

    public List<ItHrisPendapatanDokterDetailEntity> getByPendapatanId(Map mapCriteria){
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisPendapatanDokterDetailEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("pendapatan_dokter_id") != null){
                criteria.add(Restrictions.eq("pendapatanDokterId", (String) mapCriteria.get("pendapatan_dokter_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
//        criteria.add(Restrictions.eq("flag", "Y"));
        List<ItHrisPendapatanDokterDetailEntity> results = criteria.list();
        return results;
    }

    public String getNextIdDetailPendapatanDokter() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_pendapatan_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PDRD"+sId;
    }
}