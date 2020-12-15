package com.neurix.simrs.master.jenisobat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import com.neurix.simrs.master.rekananops.model.ImSimrsRekananOpsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JenisObatDao extends GenericDao<ImSimrsJenisObatEntity, String> {

    @Override
    protected Class<ImSimrsJenisObatEntity> getEntityClass() {
        return ImSimrsJenisObatEntity.class;
    }

    @Override
    public List<ImSimrsJenisObatEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisObatEntity.class);
        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_jenis_obat")!=null) {
                criteria.add(Restrictions.eq("idJenisObat", (String) mapCriteria.get("id_jenis_obat")));
            }
            if (mapCriteria.get("nama_jenis_obat")!=null) {
                criteria.add(Restrictions.ilike("namaJenisObat", "%" + (String)mapCriteria.get("nama_jenis_obat") + "%"));
            }

        }
        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        // Order by
        criteria.addOrder(Order.asc("idJenisObat"));

        List<ImSimrsJenisObatEntity> results = criteria.list();

        return results;
    }

    public List<ImSimrsJenisObatEntity> getJenisObat(String namaJenisObat ) throws HibernateException {
        List<ImSimrsJenisObatEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisObatEntity.class)
                .add(Restrictions.ilike("namaJenisObat", namaJenisObat))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_obat')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%05d", iter.next());

        return "JO"+sId;
    }
}