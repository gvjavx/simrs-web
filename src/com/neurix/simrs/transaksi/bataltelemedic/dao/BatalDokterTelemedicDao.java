package com.neurix.simrs.transaksi.bataltelemedic.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsBatalTelemedicEntity;
import com.neurix.simrs.transaksi.bataltelemedic.model.ItSimrsDokterBatalTelemedicEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 23/07/20.
 */
public class BatalDokterTelemedicDao extends GenericDao<ItSimrsDokterBatalTelemedicEntity, String> {

    @Override
    protected Class<ItSimrsDokterBatalTelemedicEntity> getEntityClass() {
        return ItSimrsDokterBatalTelemedicEntity.class;
    }

    @Override
    public List<ItSimrsDokterBatalTelemedicEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsDokterBatalTelemedicEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_dokter") != null)
            criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
        if (mapCriteria.get("id_pelayanan") != null)
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        if (mapCriteria.get("batal_date") != null)
            criteria.add(Restrictions.eq("batalDate", (Date) mapCriteria.get("batal_date")));

        return criteria.list();
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_batal_dokter_telemedic')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
