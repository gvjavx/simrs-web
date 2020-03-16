package com.neurix.simrs.transaksi.paketperiksa.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.paketperiksa.model.ItSimrsPaketPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 12/03/20.
 */
public class PaketPasienDao extends GenericDao<ItSimrsPaketPasienEntity, String> {
    @Override
    protected Class<ItSimrsPaketPasienEntity> getEntityClass() {
        return ItSimrsPaketPasienEntity.class;
    }

    @Override
    public List<ItSimrsPaketPasienEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPaketPasienEntity.class);

        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id_paket", mapCriteria.get("id_paket").toString()));

        return criteria.list();
    }
}
