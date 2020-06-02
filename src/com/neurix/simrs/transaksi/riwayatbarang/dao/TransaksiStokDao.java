package com.neurix.simrs.transaksi.riwayatbarang.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsRiwayatBarangMasukEntity;
import com.neurix.simrs.transaksi.riwayatbarang.model.ItSimrsTransaksiStokEntity;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 14/05/20.
 */
public class TransaksiStokDao extends GenericDao<ItSimrsTransaksiStokEntity, String> {
    @Override
    protected Class<ItSimrsTransaksiStokEntity> getEntityClass() {
        return ItSimrsTransaksiStokEntity.class;
    }

    @Override
    public List<ItSimrsTransaksiStokEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsTransaksiStokEntity.class);

        if (mapCriteria != null){
            if (mapCriteria.get("id_transaksi") != null){
                criteria.add(Restrictions.eq("idTransaksi", mapCriteria.get("id_transaksi").toString()));
            }
            if (mapCriteria.get("id_barang") != null){
                criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_barang").toString()));
            }
            if (mapCriteria.get("id_pelayanan") != null){
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("tipe") != null){
                criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
            }
            if (mapCriteria.get("id_vendor") != null){
                criteria.add(Restrictions.eq("idVendor", mapCriteria.get("id_vendor").toString()));
            }

            if (mapCriteria.get("tahun") != null){
                criteria.add(Restrictions.sqlRestriction("EXTRACT(YEAR FROM registered_date) = ?", (Integer) mapCriteria.get("tahun"), Hibernate.INTEGER));
            }

            if (mapCriteria.get("bulan") != null){
                criteria.add(Restrictions.sqlRestriction("EXTRACT(MONTH FROM registered_date) = ?", (Integer) mapCriteria.get("bulan"), Hibernate.INTEGER));
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
