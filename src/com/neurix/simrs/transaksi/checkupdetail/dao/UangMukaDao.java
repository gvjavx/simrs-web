package com.neurix.simrs.transaksi.checkupdetail.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkupdetail.model.ItSimrsUangMukaPendaftaranEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 25/02/20.
 */
public class UangMukaDao extends GenericDao<ItSimrsUangMukaPendaftaranEntity, String> {

    @Override
    protected Class<ItSimrsUangMukaPendaftaranEntity> getEntityClass() {
        return ItSimrsUangMukaPendaftaranEntity.class;
    }

    @Override
    public List<ItSimrsUangMukaPendaftaranEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsUangMukaPendaftaranEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id") != null) {
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }

            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
            }

            if (mapCriteria.get("status_bayar") != null) {
                criteria.add(Restrictions.eq("statusBayar", mapCriteria.get("status_bayar")));
            }

            if (mapCriteria.get("nota_not_null") != null) {
                criteria.add(Restrictions.isNotNull("noNota"));
            }
        }

        List<ItSimrsUangMukaPendaftaranEntity> results = criteria.list();
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_uang_muka')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
