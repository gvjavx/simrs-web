package com.neurix.simrs.transaksi.plankegiatanrawat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.plankegiatanrawat.model.ItSimrsPlanKegiatanRawatEntity;
import com.neurix.simrs.transaksi.transaksitindakanbpjs.model.ItSimrsTindakanBpjsEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 06/03/20.
 */
public class PlanKegiatanRawatDao extends GenericDao<ItSimrsPlanKegiatanRawatEntity, String> {
    @Override
    protected Class<ItSimrsPlanKegiatanRawatEntity> getEntityClass() {
        return ItSimrsPlanKegiatanRawatEntity.class;
    }

    @Override
    public List<ItSimrsPlanKegiatanRawatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPlanKegiatanRawatEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_detail_checkup") != null)
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));

        List<ItSimrsPlanKegiatanRawatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_plan_kegiatan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
