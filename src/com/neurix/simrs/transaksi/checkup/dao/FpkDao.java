package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsCheckupAlergiEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSImrsFpkEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 23/12/2019.
 */
public class FpkDao extends GenericDao<ItSImrsFpkEntity, String> {
    @Override
    protected Class<ItSImrsFpkEntity> getEntityClass() {
        return ItSImrsFpkEntity.class;
    }

    @Override
    public List<ItSImrsFpkEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSImrsFpkEntity.class);
        if (mapCriteria.get("id_fpk") != null){
            criteria.add(Restrictions.eq("idFpk", mapCriteria.get("id_fpk").toString()));
        }
        if (mapCriteria.get("no_sep") != null){
            criteria.add(Restrictions.eq("noSep", mapCriteria.get("no_sep").toString()));
        }
        if(mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        List<ItSImrsFpkEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_fpk')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
