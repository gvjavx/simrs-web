package com.neurix.simrs.transaksi.mpp.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.mpp.model.ItSimrsMppEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MppDao extends GenericDao<ItSimrsMppEntity, String> {

    @Override
    protected Class<ItSimrsMppEntity> getEntityClass() {
        return ItSimrsMppEntity.class;
    }

    @Override
    public List<ItSimrsMppEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsMppEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_mpp")!=null) {
                criteria.add(Restrictions.eq("idMpp", (String) mapCriteria.get("id_mpp")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
            if (mapCriteria.get("jenis")!=null) {
                criteria.add(Restrictions.eq("jenis", (String) mapCriteria.get("jenis")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idMpp"));

        List<ItSimrsMppEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_mpp')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}