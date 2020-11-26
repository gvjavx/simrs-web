package com.neurix.simrs.transaksi.notifikasiadmin.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.notifikasiadmin.model.ItNotifikasiAdminTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/08/20.
 */
public class NotifikasiAdminTelemedicDao extends GenericDao<ItNotifikasiAdminTelemedicEntity, String> {

    @Override
    protected Class<ItNotifikasiAdminTelemedicEntity> getEntityClass() {
        return ItNotifikasiAdminTelemedicEntity.class;
    }

    @Override
    public List<ItNotifikasiAdminTelemedicEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItNotifikasiAdminTelemedicEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_item") != null)
            criteria.add(Restrictions.eq("idItem", mapCriteria.get("id_item").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("role_id") != null)
            criteria.add(Restrictions.eq("roleId", mapCriteria.get("role_id").toString()));
        if (mapCriteria.get("branch_id") != null)
            criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
        if (mapCriteria.get("user_id") != null)
            criteria.add(Restrictions.eq("userId", mapCriteria.get("user_id").toString()));

        criteria.addOrder(Order.desc("createdDate"));
        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_notifikasi_admin_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
