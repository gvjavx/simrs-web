package com.neurix.simrs.transaksi.bataltelemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 23/07/20.
 */
public class BatalTelemedicDao extends GenericDao<ItSimrsBatalTelemedicEntity, String> {

    @Override
    protected Class<ItSimrsBatalTelemedicEntity> getEntityClass() {
        return ItSimrsBatalTelemedicEntity.class;
    }

    @Override
    public List<ItSimrsBatalTelemedicEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsBatalTelemedicEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_antrian_telemedic") != null)
            criteria.add(Restrictions.eq("idAntrianTelemedic", mapCriteria.get("id_antrian_telemedic").toString()));
        if (mapCriteria.get("id_dokter_batal") != null)
            criteria.add(Restrictions.eq("idDokterBatal", mapCriteria.get("id_dokter_batal").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_batal_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());
        return sId;
    }
}
