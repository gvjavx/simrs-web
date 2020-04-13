package com.neurix.simrs.transaksi.fisioterapi.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.fisioterapi.model.ItSimrsFisioterapiEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FisioterapiDao extends GenericDao<ItSimrsFisioterapiEntity, String> {

    @Override
    protected Class<ItSimrsFisioterapiEntity> getEntityClass() {
        return ItSimrsFisioterapiEntity.class;
    }

    @Override
    public List<ItSimrsFisioterapiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItSimrsFisioterapiEntity.class);

        if (mapCriteria!=null) {
            if (mapCriteria.get("id_fisioterapi")!=null) {
                criteria.add(Restrictions.eq("idFisioterapi", (String) mapCriteria.get("id_fisioterapi")));
            }
            if (mapCriteria.get("id_detail_checkup")!=null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("keterangan")!=null) {
                criteria.add(Restrictions.eq("keterangan", (String) mapCriteria.get("keterangan")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idFisioterapi"));

        List<ItSimrsFisioterapiEntity> results = criteria.list();
        return results;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_fisioterapi')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}