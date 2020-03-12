package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.MtSimrsPaketEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class PaketDao extends GenericDao<MtSimrsPaketEntity, String> {

    @Override
    protected Class<MtSimrsPaketEntity> getEntityClass() {
        return MtSimrsPaketEntity.class;
    }

    @Override
    public List<MtSimrsPaketEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsPaketEntity.class);

        if (mapCriteria.get("id_paket") != null)
            criteria.add(Restrictions.eq("idPaket", mapCriteria.get("id_paket").toString()));
        if (mapCriteria.get("id_perusahaan") != null)
            criteria.add(Restrictions.eq("idPerusahaan", mapCriteria.get("id_perusahaan").toString()));
        if (mapCriteria.get("id_kelas_paket") != null)
            criteria.add(Restrictions.eq("idKelasPaket", mapCriteria.get("id_kelas_paket").toString()));

        return criteria.list();
    }
}
