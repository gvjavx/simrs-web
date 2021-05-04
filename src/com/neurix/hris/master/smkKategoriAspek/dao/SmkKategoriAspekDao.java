
package com.neurix.hris.master.smkKategoriAspek.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.smkKategoriAspek.model.ImSmkKategoriAspekEntity;
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
public class SmkKategoriAspekDao extends GenericDao<ImSmkKategoriAspekEntity, String> {

    @Override
    protected Class<ImSmkKategoriAspekEntity> getEntityClass() {
        return ImSmkKategoriAspekEntity.class;
    }

    @Override
    public List<ImSmkKategoriAspekEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSmkKategoriAspekEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("kategoriAspekId")!=null) {
                criteria.add(Restrictions.eq("kategoriAspekId", (String) mapCriteria.get("kategoriAspekId")));
            }
            if (mapCriteria.get("kategoriName")!=null) {
                criteria.add(Restrictions.ilike("kategoriName", "%" + (String)mapCriteria.get("kategoriName") + "%"));
            }
            if (mapCriteria.get("tipeAspekId")!=null) {
                criteria.add(Restrictions.eq("tipeAspekId", (String)mapCriteria.get("tipeAspekId")));
            }
            if (mapCriteria.get("bobot")!=null) {
                criteria.add(Restrictions.eq("bobot", mapCriteria.get("bobot")));
            }
            if (mapCriteria.get("branchId")!=null) {
                criteria.add(Restrictions.eq("branchId", (String)mapCriteria.get("branchId")));
            }


        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("kategoriAspekId"));

        List<ImSmkKategoriAspekEntity> results = criteria.list();

        return results;
    }

    public String getNextSmkKategoriId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_smk_kategori_aspek')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "KT"+sId;
    }

    public List<ImSmkKategoriAspekEntity> getListSmkKategori(String term) throws HibernateException {

        List<ImSmkKategoriAspekEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkKategoriAspekEntity.class)
                .add(Restrictions.ilike("smkKategoriName",term))
                .add(Restrictions.eq("flag", "Y"))
                .addOrder(Order.asc("smkKategoriId"))
                .list();

        return results;
    }

    public List<ImSmkKategoriAspekEntity> getDataKategori(String tipeAspek, String branchId) throws HibernateException {
        List<ImSmkKategoriAspekEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSmkKategoriAspekEntity.class)
                .add(Restrictions.eq("tipeAspekId", tipeAspek))
                .add(Restrictions.eq("branchId", branchId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }

}
