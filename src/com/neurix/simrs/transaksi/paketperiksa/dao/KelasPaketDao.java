package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ImSimrsKelasPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class KelasPaketDao extends GenericDao<ImSimrsKelasPaketEntity, String> {

    @Override
    protected Class<ImSimrsKelasPaketEntity> getEntityClass() {
        return ImSimrsKelasPaketEntity.class;
    }

    @Override
    public List<ImSimrsKelasPaketEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasPaketEntity.class);

        if (mapCriteria.get("id_kelas_paket") != null)
            criteria.add(Restrictions.eq("idKelasPaket", mapCriteria.get("id_kelas_paket").toString()));

        return criteria.list();
    }
}
