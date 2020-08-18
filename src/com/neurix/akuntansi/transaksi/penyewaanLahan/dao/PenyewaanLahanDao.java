package com.neurix.akuntansi.transaksi.penyewaanLahan.dao;

import com.neurix.akuntansi.transaksi.penyewaanLahan.model.ItAkunPenyewaanLahanEntity;
import com.neurix.common.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class PenyewaanLahanDao extends GenericDao<ItAkunPenyewaanLahanEntity, String> {

    @Override
    protected Class<ItAkunPenyewaanLahanEntity> getEntityClass() {
        return ItAkunPenyewaanLahanEntity.class;
    }

    @Override
    public List<ItAkunPenyewaanLahanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItAkunPenyewaanLahanEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("penyewaan_lahan_id")!=null) {
                criteria.add(Restrictions.eq("penyewaanLahanId", (String) mapCriteria.get("penyewaan_lahan_id")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            if (mapCriteria.get("nama_penyewa")!=null) {
                criteria.add(Restrictions.ilike("namaPenyewa", "%" + (String)mapCriteria.get("nama_penyewa") + "%"));
            }
            if (mapCriteria.get("tanggal_dari")!=null && mapCriteria.get("tanggal_selesai")!=null) {
                criteria.add(Restrictions.between("registeredDate",mapCriteria.get("tanggal_dari"),mapCriteria.get("tanggal_selesai")));
            }
            else {
                if (mapCriteria.get("tanggal_dari")!=null) {
                    criteria.add(Restrictions.ge("registeredDate",mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai")!=null) {
                    criteria.add(Restrictions.le("registeredDate",mapCriteria.get("tanggal_selesai")));
                }
            }
            if (mapCriteria.get("tipe_pembayaran")!=null) {
                criteria.add(Restrictions.eq("tipePembayaran", (String) mapCriteria.get("tipe_pembayaran")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("penyewaanLahanId"));
        List<ItAkunPenyewaanLahanEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_penyewaan_lahan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%09d", iter.next());

        return "PL"+sId;
    }
}
