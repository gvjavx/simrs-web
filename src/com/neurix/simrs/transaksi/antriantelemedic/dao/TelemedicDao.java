package com.neurix.simrs.transaksi.antriantelemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 08/06/20.
 */
public class TelemedicDao extends GenericDao<ItSimrsAntrianTelemedicEntity, String> {

    @Override
    protected Class<ItSimrsAntrianTelemedicEntity> getEntityClass() {
        return ItSimrsAntrianTelemedicEntity.class;
    }

    @Override
    public List<ItSimrsAntrianTelemedicEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAntrianTelemedicEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));
        if (mapCriteria.get("id_pelayanan") != null)
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        if (mapCriteria.get("id_dokter") != null)
            criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
        if (mapCriteria.get("status") != null)
            criteria.add(Restrictions.eq("status", mapCriteria.get("status").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_antrian_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
