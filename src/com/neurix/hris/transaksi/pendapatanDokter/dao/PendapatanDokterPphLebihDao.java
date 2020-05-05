package com.neurix.hris.transaksi.pendapatanDokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.pendapatanDokter.model.ItHrisPendapatanDokterPphLebihEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PendapatanDokterPphLebihDao extends GenericDao<ItHrisPendapatanDokterPphLebihEntity, String> {

    @Override
    protected Class<ItHrisPendapatanDokterPphLebihEntity> getEntityClass() {
        return ItHrisPendapatanDokterPphLebihEntity.class;
    }

    @Override
    public List<ItHrisPendapatanDokterPphLebihEntity> getByCriteria(Map mapCriteria) {

        return null;
    }

    public String getNextIdPendapatanDokterPphLebih() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_pendapatan_dokter_pph_lebih')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%06d", iter.next());

        return "PPL"+sId;
    }
}