package com.neurix.hris.transaksi.pendapatanDokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterDetailEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;

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

    public String getNextIdDetailPendapatanDokter() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_detail_pendapatan_dokter')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PDRD"+sId;
    }
}