package com.neurix.simrs.transaksi.riwayatbarang.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsRiwayatBarangMasukEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/05/20.
 */
public class RiwayatBarangDao extends GenericDao<ItSimrsRiwayatBarangMasukEntity, String> {
    @Override
    protected Class<ItSimrsRiwayatBarangMasukEntity> getEntityClass() {
        return ItSimrsRiwayatBarangMasukEntity.class;
    }

    @Override
    public List<ItSimrsRiwayatBarangMasukEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsRiwayatBarangMasukEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("id_barang_masuk") != null){
                criteria.add(Restrictions.eq("idBarangMasuk", mapCriteria.get("id_barang_masuk").toString()));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
        }

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_riwayat_barang')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
