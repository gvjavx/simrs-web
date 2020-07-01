package com.neurix.simrs.master.askep.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.askep.model.ImSimrsDetailAsuhanKeperawatanEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DetailAsuhanKeperawatanDao extends GenericDao<ImSimrsDetailAsuhanKeperawatanEntity, String> {

    @Override
    protected Class<ImSimrsDetailAsuhanKeperawatanEntity> getEntityClass() {
        return ImSimrsDetailAsuhanKeperawatanEntity.class;
    }

    @Override
    public List<ImSimrsDetailAsuhanKeperawatanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDetailAsuhanKeperawatanEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_detail_askep")!=null) {
                criteria.add(Restrictions.eq("idDetailAsuhanKeperawatan", (String) mapCriteria.get("id_detail_askep")));
            }
            if (mapCriteria.get("id_diagnosa_askep")!=null) {
                criteria.add(Restrictions.eq("idDiagnosaAsuhanKeperawatan", (String) mapCriteria.get("id_diagnosa_askep")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idDetailAsuhanKeperawatan"));

        List<ImSimrsDetailAsuhanKeperawatanEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_id_detail_askep')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}