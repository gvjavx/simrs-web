package com.neurix.simrs.transaksi.checkup.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsAsesmenEntity;
import com.neurix.simrs.transaksi.checkup.model.ItSimrsAsesmenEntity;
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
public class AsesmenDao extends GenericDao<ItSimrsAsesmenEntity, String> {
    @Override
    protected Class<ItSimrsAsesmenEntity> getEntityClass() {
        return ItSimrsAsesmenEntity.class;
    }

    @Override
    public List<ItSimrsAsesmenEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsAsesmenEntity.class);
        if (mapCriteria.get("id_asesmen") != null){
            criteria.add(Restrictions.eq("idAsesmen", mapCriteria.get("id_asesmen").toString()));
        }
        if (mapCriteria.get("id_detail_checkup") != null){
            criteria.add(Restrictions.eq("idDetailCheckup", mapCriteria.get("id_detail_checkup").toString()));
        }
        if(mapCriteria.get("flag") != null){
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        List<ItSimrsAsesmenEntity> list = criteria.list();
        return list;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_asesmen')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
