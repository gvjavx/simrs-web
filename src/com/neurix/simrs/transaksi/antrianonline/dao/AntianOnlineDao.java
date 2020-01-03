package com.neurix.simrs.transaksi.antrianonline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class AntianOnlineDao extends GenericDao<ItSimrsAntianOnlineEntity, String> {
    @Override
    protected Class<ItSimrsAntianOnlineEntity> getEntityClass() {
        return ItSimrsAntianOnlineEntity.class;
    }

    @Override
    public List<ItSimrsAntianOnlineEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAntianOnlineEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_antrian_online")!=null) {
                criteria.add(Restrictions.eq("idAntrianOnline", (String) mapCriteria.get("id_antrian_online")));
            }
            if (mapCriteria.get("no_checkup_online")!=null) {
                criteria.add(Restrictions.eq("noCheckupOnline", (String) mapCriteria.get("no_checkup_online")));
            }
            if (mapCriteria.get("id_pelayanan")!=null) {
                criteria.add(Restrictions.eq("idPelayanan", (String) mapCriteria.get("id_pelayanan")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idAntrianOnline"));

        List<ItSimrsAntianOnlineEntity> results = criteria.list();
        return results;
    }
}