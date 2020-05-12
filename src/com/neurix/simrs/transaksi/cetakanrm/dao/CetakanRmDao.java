package com.neurix.simrs.transaksi.cetakanrm.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.cetakanrm.model.ItSimrsCetakanRmEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CetakanRmDao extends GenericDao<ItSimrsCetakanRmEntity, String> {

    @Override
    protected Class<ItSimrsCetakanRmEntity> getEntityClass() {
        return ItSimrsCetakanRmEntity.class;
    }

    @Override
    public List<ItSimrsCetakanRmEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsCetakanRmEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_cetakan_rm")!=null) {
                criteria.add(Restrictions.eq("idCetakanRm", (String) mapCriteria.get("id_cetakan_rm")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.desc("idCetakanRm"));

        List<ItSimrsCetakanRmEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_cetakan_rm')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}