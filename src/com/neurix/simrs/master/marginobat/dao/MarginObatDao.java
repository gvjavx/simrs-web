package com.neurix.simrs.master.marginobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.marginobat.model.ImSimrsMarginObatEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 29/09/20.
 */
public class MarginObatDao extends GenericDao<ImSimrsMarginObatEntity, String>{

    @Override
    protected Class<ImSimrsMarginObatEntity> getEntityClass() {
        return ImSimrsMarginObatEntity.class;
    }

    @Override
    public List<ImSimrsMarginObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsMarginObatEntity.class);

        if (mapCriteria.get("id_margin_obat") != null){
            criteria.add(Restrictions.eq("idMarginObat", mapCriteria.get("id_margin_obat").toString()));
        }

        if (mapCriteria.get("id_obat") != null){
            criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
        }

        if (mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
        }

        List<ImSimrsMarginObatEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_margin_obat')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
