package com.neurix.simrs.transaksi.history.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.history.model.ItSimrsHistoryPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HistoryPelayananDao extends GenericDao<ItSimrsHistoryPelayananEntity, String> {
    @Override
    protected Class<ItSimrsHistoryPelayananEntity> getEntityClass() {
        return ItSimrsHistoryPelayananEntity.class;
    }

    @Override
    public List<ItSimrsHistoryPelayananEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHistoryPelayananEntity.class);

        if (mapCriteria != null)
            if (mapCriteria.get("id_detail_checkup") != null)
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));


        List<ItSimrsHistoryPelayananEntity> result = criteria.list();
        return result;
    }
}
