package com.neurix.hris.master.dokterKso.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.dokterKso.model.ImSimrsDokterKso;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DokterKsoDao extends GenericDao<ImSimrsDokterKso, String> {

    @Override
    protected Class<ImSimrsDokterKso> getEntityClass() {
        return ImSimrsDokterKso.class;
    }

    @Override
    public List<ImSimrsDokterKso> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterKso.class);

        if (mapCriteria != null){
            if (mapCriteria.get("dokter_kso_id") != null){
                criteria.add(Restrictions.eq("dokterKsoId", (String) mapCriteria.get("dokter_kso_id")));
            }
            if (mapCriteria.get("nip") != null){
                criteria.add(Restrictions.eq("nip", (String) mapCriteria.get("nip")));
            }
            if (mapCriteria.get("master_id") != null){
                criteria.add(Restrictions.eq("masterId", (String) mapCriteria.get("master_id")));
            }
            if (mapCriteria.get("branch_id") != null){
                criteria.add(Restrictions.eq("branchId", (String) mapCriteria.get("branch_id")));
            }
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }

        criteria.addOrder(Order.asc("dokterKsoId"));
        List<ImSimrsDokterKso> results = criteria.list();
        return results;
    }

    public String getNextDokterKsoId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dokter_kso')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "DK" + sId;
    }

    public String getNextKodering() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_kodering')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%01d", iter.next());

        return sId;
    }

    public List<ImSimrsDokterKso> getDataDokterKso(String nip) throws HibernateException {
        List<ImSimrsDokterKso> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsDokterKso.class)
                .add(Restrictions.eq("nip", nip))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextDokterKsoTindakanId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_dokter_kso_tindakan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "DKT" + sId;
    }
}