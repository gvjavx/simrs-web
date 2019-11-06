package com.neurix.hris.transaksi.lembur.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.lembur.model.JamLemburEntity;
import com.neurix.hris.transaksi.lembur.model.LemburEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class JamLemburDao extends GenericDao<JamLemburEntity, String> {

    @Override
    protected Class<JamLemburEntity> getEntityClass() {
        return JamLemburEntity.class;
    }

    @Override
    public List<JamLemburEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(JamLemburEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("jam_lembur_id")!=null) {
                criteria.add(Restrictions.ilike("jamLemburId","%" + (String) mapCriteria.get("jam_lembur_id")+ "%"));
            }
            if (mapCriteria.get("tipe_hari")!=null) {
                criteria.add(Restrictions.eq("tipeHari",(String) mapCriteria.get("tipe_hari")));
            }
            if (mapCriteria.get("jam_lembur")!=null) {
                criteria.add(Restrictions.eq("jamLembur",mapCriteria.get("jam_lembur")));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("jamLemburId"));

        List<JamLemburEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextJamLemburId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_hris_jam_lembur')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%04d", iter.next());

        return "JL"+sId;
    }

    public List<JamLemburEntity> getListJamLembur(String term) throws HibernateException {

        List<JamLemburEntity> results = this.sessionFactory.getCurrentSession().createCriteria(JamLemburEntity.class)
                .add(Restrictions.ilike("jamLemburId",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("kodeAlat"))
                .list();

        return results;
    }
}
