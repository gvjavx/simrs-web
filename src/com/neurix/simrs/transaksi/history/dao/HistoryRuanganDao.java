package com.neurix.simrs.transaksi.history.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.history.model.ItSimrsHistoryRuanganEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 08/11/2019.
 */
public class HistoryRuanganDao extends GenericDao<ItSimrsHistoryRuanganEntity, String> {
    @Override
    protected Class<ItSimrsHistoryRuanganEntity> getEntityClass() {
        return ItSimrsHistoryRuanganEntity.class;
    }

    @Override
    public List<ItSimrsHistoryRuanganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsHistoryRuanganEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_rawat_inap") != null)
                criteria.add(Restrictions.eq("idRawatInap", mapCriteria.get("id_rawat_inap").toString()));
            if (mapCriteria.get("id_ruangan") != null)
                criteria.add(Restrictions.eq("idRuangan", mapCriteria.get("id_ruangan").toString()));

        List<ItSimrsHistoryRuanganEntity> result = criteria.list();
        return result;
    }
}
