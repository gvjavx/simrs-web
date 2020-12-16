package com.neurix.simrs.master.hargaterakhir.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.hargaterakhir.model.MtSimrsHargaTerakhirEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HargaTerakhirDao extends GenericDao<MtSimrsHargaTerakhirEntity, String>{

    @Override
    protected Class<MtSimrsHargaTerakhirEntity> getEntityClass() {
        return MtSimrsHargaTerakhirEntity.class;
    }

    @Override
    public List<MtSimrsHargaTerakhirEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MtSimrsHargaTerakhirEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id") != null){
                criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
            }

            if (mapCriteria.get("id_obat") != null){
                criteria.add(Restrictions.eq("idObat", mapCriteria.get("id_obat").toString()));
            }

            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }

            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        return criteria.list();
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_harga_terakhir')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "HTR" + sId;
    }
}
