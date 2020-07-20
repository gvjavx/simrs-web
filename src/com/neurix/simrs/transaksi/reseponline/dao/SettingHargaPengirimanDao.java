package com.neurix.simrs.transaksi.reseponline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.reseponline.model.ImSimrsSettingHargaPengirimanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 16/07/20.
 */
public class SettingHargaPengirimanDao extends GenericDao<ImSimrsSettingHargaPengirimanEntity, String> {

    @Override
    protected Class<ImSimrsSettingHargaPengirimanEntity> getEntityClass() {
        return ImSimrsSettingHargaPengirimanEntity.class;
    }

    @Override
    public List<ImSimrsSettingHargaPengirimanEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsSettingHargaPengirimanEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("tipe") != null)
            criteria.add(Restrictions.eq("tipe", mapCriteria.get("tipe").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branchId").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_setting_harga_pengiriman')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }

}
