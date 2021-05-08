
package com.neurix.hris.transaksi.sppd.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.sppd.model.ImSppdEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.security.Timestamp;
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
public class NotifDao extends GenericDao<ImSppdEntity, String> {

    @Override
    protected Class<ImSppdEntity> getEntityClass() {
        return ImSppdEntity.class;
    }

    @Override
    public List<ImSppdEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSppdEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("sppd_id")!=null) {
                criteria.add(Restrictions.eq("sppdId", (String) mapCriteria.get("sppd_id")));
            }

            if (mapCriteria.get("dinas")!=null) {
                criteria.add(Restrictions.eq("dinas", (String) mapCriteria.get("dinas")));
            }

            if (mapCriteria.get("divisi_id")!=null) {
                criteria.add(Restrictions.eq("divisiId", (String) mapCriteria.get("divisi_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("no_surat")!=null) {
                criteria.add(Restrictions.ilike("noSurat", "%" + (String)mapCriteria.get("no_surat") + "%"));
            }



            if (mapCriteria.get("tanggal_sppd_berangkat")!=null && mapCriteria.get("tanggal_sppd_kembali")!=null) {
                criteria.add(Restrictions.ge("tanggalSppdBerangkat", (Timestamp) mapCriteria.get("tanggal_sppd_berangkat")));
                criteria.add(Restrictions.lt("tanggalSppdKembali", (Timestamp) mapCriteria.get("tanggal_sppd_kembali")));
            }


        }

        // Order by
        criteria.addOrder(Order.asc("sppdId"));

        List<ImSppdEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextSppdId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());

        return sId;
    }

    public String getNextSppdHistoryId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_sppd_history')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "H"+sId;
    }

    public List<ImSppdEntity> getListSppd(String term) throws HibernateException {

        List<ImSppdEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSppdEntity.class)
                .add(Restrictions.ilike("sppdName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("sppdId"))
                .list();

        return results;
    }

}
