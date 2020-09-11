package com.neurix.simrs.transaksi.permintaanvendor.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.permintaanvendor.model.ImUserVendorEntity;
import com.neurix.simrs.transaksi.permintaanvendor.model.MtSimrsPermintaanVendorEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 03/09/20.
 */
public class UserVendorDao extends GenericDao<ImUserVendorEntity, String> {
    @Override
    protected Class<ImUserVendorEntity> getEntityClass() {
        return ImUserVendorEntity.class;
    }

    @Override
    public List<ImUserVendorEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImUserVendorEntity.class);
        if (mapCriteria.get("id") != null) {
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("user_id") != null) {
            criteria.add(Restrictions.eq("userId", mapCriteria.get("user_id").toString()));
        }
        if (mapCriteria.get("id_vendor") != null) {
            criteria.add(Restrictions.eq("idVendor", mapCriteria.get("id_vendor").toString()));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_user_vendor')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
