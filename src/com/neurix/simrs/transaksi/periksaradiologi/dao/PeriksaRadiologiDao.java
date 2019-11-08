package com.neurix.simrs.transaksi.periksaradiologi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.periksaradiologi.model.ItSimrsPeriksaRadiologiEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class PeriksaRadiologiDao extends GenericDao<ItSimrsPeriksaRadiologiEntity, String> {
    @Override
    protected Class<ItSimrsPeriksaRadiologiEntity> getEntityClass() {
        return ItSimrsPeriksaRadiologiEntity.class;
    }

    @Override
    public List<ItSimrsPeriksaRadiologiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPeriksaRadiologiEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_periksa_radiologi")!=null) {
                criteria.add(Restrictions.eq("idPeriksaRadiologi", (String) mapCriteria.get("id_periksa_radiologi")));
            }
            if (mapCriteria.get("id_dokter_radiologi")!=null) {
                criteria.add(Restrictions.eq("idDokterRadiologi", (String) mapCriteria.get("id_dokter_radiologi")));
            }
            if (mapCriteria.get("pemeriksaan")!=null) {
                criteria.add(Restrictions.eq("pemeriksaan", (String) mapCriteria.get("pemeriksaan")));
            }
            if (mapCriteria.get("kesimpulan")!=null) {
                criteria.add(Restrictions.eq("kesimpulan", (String) mapCriteria.get("kesimpulan")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idPeriksaRadiologi"));

        List<ItSimrsPeriksaRadiologiEntity> results = criteria.list();
        return results;
    }
}