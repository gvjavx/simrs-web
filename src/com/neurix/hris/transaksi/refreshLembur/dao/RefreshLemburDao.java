package com.neurix.hris.transaksi.refreshLembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.refreshLembur.model.ItHrisRefreshLemburEntity;
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
 * Created by Aji Noor on 22/07/2021
 */
public class RefreshLemburDao extends GenericDao<ItHrisRefreshLemburEntity, String> {

    @Override
    protected Class<ItHrisRefreshLemburEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisRefreshLemburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItHrisRefreshLemburEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("lembur_id") != null) {
                criteria.add(Restrictions.ilike("lemburId", "%" + (String) mapCriteria.get("lembur_id") + "%"));
            }
            if (mapCriteria.get("nip") != null) {
                criteria.add(Restrictions.ilike("nip", "%" + (String) mapCriteria.get("nip") + "%"));
            }

            if (mapCriteria.get("tanggal_dari") != null && mapCriteria.get("tanggal_selesai") != null) {
                criteria.add(Restrictions.between("tanggalAwal", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
                criteria.add(Restrictions.between("tanggalAkhir", mapCriteria.get("tanggal_dari"), mapCriteria.get("tanggal_selesai")));
            } else {
                if (mapCriteria.get("tanggal_dari") != null) {
                    criteria.add(Restrictions.ge("tanggalAwal", mapCriteria.get("tanggal_dari")));
                }
                if (mapCriteria.get("tanggal_selesai") != null) {
                    criteria.add(Restrictions.le("tanggalAkhir", mapCriteria.get("tanggal_selesai")));
                }
            }
        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.desc("lemburId"));

        List<ItHrisRefreshLemburEntity> results = criteria.list();

        return results;
    }

    public String getNextRefreshLemburId() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_refresh_lembur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", ((Iterator) iter).next());

        return "RL" + sId;
    }

    public String getNextGroupLemburId() throws HibernateException {

        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_group_lembur')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%06d", ((Iterator) iter).next());

        return "GL" + sId;
    }



}
