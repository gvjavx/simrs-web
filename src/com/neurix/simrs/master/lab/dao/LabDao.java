package com.neurix.simrs.master.lab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.lab.model.ImSimrsLabEntity;
import com.neurix.simrs.master.lab.model.Lab;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LabDao extends GenericDao<ImSimrsLabEntity, String> {
    @Override
    protected Class<ImSimrsLabEntity> getEntityClass() {
        return ImSimrsLabEntity.class;
    }

    @Override
    public List<ImSimrsLabEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_lab")!=null) {
                criteria.add(Restrictions.eq("idLab", (String) mapCriteria.get("id_lab")));
            }
            if (mapCriteria.get("nama_lab")!=null) {
                criteria.add(Restrictions.ilike("namaLab", "%" + (String)mapCriteria.get("nama_lab") + "%"));
            }
            if (mapCriteria.get("id_operator_lab")!=null) {
                criteria.add(Restrictions.eq("idOperatorLab", (String) mapCriteria.get("id_operator_lab")));
            }
            if (mapCriteria.get("id_dokter")!=null) {
                criteria.add(Restrictions.eq("idDokter", (String) mapCriteria.get("id_dokter")));
            }
            if (mapCriteria.get("id_kategori_lab")!=null) {
                criteria.add(Restrictions.eq("idKategoriLab", (String) mapCriteria.get("id_kategori_lab")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idLab"));

        List<ImSimrsLabEntity> results = criteria.list();

        return results;
    }

    public List<ImSimrsLabEntity> getDataLab(String namaLab) throws HibernateException {
        List<ImSimrsLabEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsLabEntity.class)
                .add(Restrictions.eq("namaLab", namaLab))
                .add(Restrictions.eq("flag", "Y"))
                .list();

        return results;
    }

    public String getNextLabId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_lab')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "LAB" + sId;
    }
}