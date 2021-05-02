package com.neurix.hris.transaksi.notifikasi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.notif.model.ItNotifikasiEntity;
import com.neurix.hris.transaksi.notifikasi.model.ItNotifikasiFcmEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class NotifikasiFcmDao extends GenericDao<ItNotifikasiFcmEntity, String> {


    @Override
    protected Class<ItNotifikasiFcmEntity> getEntityClass() {
        return ItNotifikasiFcmEntity.class;
    }

    @Override
    public List<ItNotifikasiFcmEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItNotifikasiFcmEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("user_id")!=null) {
                criteria.add(Restrictions.eq("userId", (String) mapCriteria.get("user_id").toString()));
            }
        }

        List<ItNotifikasiFcmEntity> results = criteria.list();

        return results;
    }
}
