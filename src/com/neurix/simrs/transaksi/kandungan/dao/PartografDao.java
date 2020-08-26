package com.neurix.simrs.transaksi.kandungan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.kandungan.model.ItSimrsPartografEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PartografDao extends GenericDao<ItSimrsPartografEntity, String> {

    @Override
    protected Class<ItSimrsPartografEntity> getEntityClass() {
        return ItSimrsPartografEntity.class;
    }

    @Override
    public List<ItSimrsPartografEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsPartografEntity.class);

        if (mapCriteria != null) {
            if (mapCriteria.get("id_partograf") != null) {
                criteria.add(Restrictions.eq("idPartograf", (String) mapCriteria.get("id_partograf")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
        }

        criteria.add(Restrictions.eq("flag", "Y"));
        criteria.addOrder(Order.asc("idPartograf"));

        List<ItSimrsPartografEntity> results = criteria.list();
        return results;
    }

    public Boolean cekData(String idDetailCheckup, String waktu){
        Boolean res = false;
        String SQL = "SELECT id_partograf\n" +
                "FROM  it_simrs_partograf \n" +
                "WHERE id_detail_checkup = :id\n" +
                "AND waktu = :waktu\n" +
                "AND CAST(created_date AS DATE) = CURRENT_DATE\n";
        List<Object[]> result = new ArrayList<>();
        result = this.sessionFactory.getCurrentSession().createSQLQuery(SQL)
                .setParameter("id", idDetailCheckup)
                .setParameter("waktu", waktu)
                .list();
        if(result.size() > 0){
            res = true;
        }
        return res;
    }

    public String getNextSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_partograf')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}