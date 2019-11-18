package com.neurix.simrs.transaksi.antrianonline.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antrianonline.model.ItSimrsAntianOnlineEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AntrianOnlineDao extends GenericDao<ItSimrsAntianOnlineEntity, String> {
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

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_antrian_online')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }


}