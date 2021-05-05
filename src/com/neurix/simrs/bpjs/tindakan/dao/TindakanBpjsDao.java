package com.neurix.simrs.bpjs.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.bpjs.tindakan.model.ImSimrsTindakanBpjsEntity;
import com.neurix.simrs.bpjs.tindakan.model.TindakanBpjs;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TindakanBpjsDao extends GenericDao<ImSimrsTindakanBpjsEntity, String> {
    @Override
    protected Class<ImSimrsTindakanBpjsEntity> getEntityClass() {
        return ImSimrsTindakanBpjsEntity.class;
    }

    @Override
    public List<ImSimrsTindakanBpjsEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTindakanBpjsEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_tindakan")!=null) {
                criteria.add(Restrictions.eq("idTindakan", (String) mapCriteria.get("id_tindakan")));
            }
            if (mapCriteria.get("nama_jenis_tindakan")!=null) {
                criteria.add(Restrictions.ilike("namaTindakan", "%" + (String)mapCriteria.get("nama_jenis_tindakan") + "%"));
            }
            if (mapCriteria.get("id_jenis_tindakan")!=null) {
                criteria.add(Restrictions.eq("idJenisTindakan", (String) mapCriteria.get("id_jenis_tindakan")));
            }
            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idTindakan"));

        List<ImSimrsTindakanBpjsEntity> results = criteria.list();

        return results;
    }
    // Generate surrogate id from postgre
    public String getNextTindakanBpjsId() throws HibernateException {


        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_simrs_tindakan_bpjs')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%07d", iter.next());

        return "TDB" + sId;
    }
}