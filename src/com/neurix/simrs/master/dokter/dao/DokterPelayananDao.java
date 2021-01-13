package com.neurix.simrs.master.dokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dokter.model.ImSimrsDokterPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DokterPelayananDao extends GenericDao<ImSimrsDokterPelayananEntity, String> {
    @Override
    protected Class<ImSimrsDokterPelayananEntity> getEntityClass() {
        return ImSimrsDokterPelayananEntity.class;
    }

    @Override
    public List<ImSimrsDokterPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterPelayananEntity.class);
        if (mapCriteria.get("id_dokter") != null){
            criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
        }
        if (mapCriteria.get("id_pelayanan") != null){
            criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
        }
        criteria.add(Restrictions.eq("flag", "Y"));
        List<ImSimrsDokterPelayananEntity> result = criteria.list();
        return result;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dokter_pelayanan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "SDP"+sId;
    }
}
