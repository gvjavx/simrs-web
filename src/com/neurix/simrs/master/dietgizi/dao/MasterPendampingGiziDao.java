package com.neurix.simrs.master.dietgizi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.dietgizi.model.ImSimrsPendampingGiziEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MasterPendampingGiziDao extends GenericDao<ImSimrsPendampingGiziEntity, String> {

    @Override
    protected Class<ImSimrsPendampingGiziEntity> getEntityClass() {
        return ImSimrsPendampingGiziEntity.class;
    }

    public List<ImSimrsPendampingGiziEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsPendampingGiziEntity.class);
        if (mapCriteria != null){
            if (mapCriteria.get("id_pendamping_gizi") != null){
                criteria.add(Restrictions.eq("idPendampingGizi", mapCriteria.get("id_pendamping_gizi").toString()));
            }
            if (mapCriteria.get("nama") != null){
                criteria.add(Restrictions.eq("nama", mapCriteria.get("nama").toString()));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", mapCriteria.get("branch_id").toString()));
            }
            if (mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }else{
                criteria.add(Restrictions.eq("flag", "Y"));
            }
        }
        List<ImSimrsPendampingGiziEntity> result = criteria.list();
        return result;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_im_pendampinng_gizi')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "MPG"+sId;
    }
}
