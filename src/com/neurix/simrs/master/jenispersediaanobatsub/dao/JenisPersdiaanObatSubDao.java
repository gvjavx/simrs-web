package com.neurix.simrs.master.jenispersediaanobatsub.dao;

import com.neurix.common.dao.GenericDao;

import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JenisPersdiaanObatSubDao extends GenericDao<ImSimrsJenisPersediaanObatSubEntity, String>{

    @Override
    protected Class<ImSimrsJenisPersediaanObatSubEntity> getEntityClass() {
        return ImSimrsJenisPersediaanObatSubEntity.class;
    }

    @Override
    public List<ImSimrsJenisPersediaanObatSubEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisPersediaanObatSubEntity.class);
        if (mapCriteria.get("id") != null){
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        }
        if (mapCriteria.get("nama") != null){
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));
        }
        if (mapCriteria.get("id_jenis_obat") != null){
            criteria.add(Restrictions.ilike("idJenisObat", "%" + mapCriteria.get("id_jenis_obat").toString() + "%"));
        }
        if (mapCriteria.get("flag") != null) {
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
        }
        criteria.addOrder(Order.asc("id"));
        List<ImSimrsJenisPersediaanObatSubEntity> listOfResult = criteria.list();
        return listOfResult;
    }

    public String getNextId() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_jenis_persediaan_obat_sub')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());

        return "JPS"+sId;
    }

    public List<ImSimrsJenisPersediaanObatSubEntity> getJenisPersediaanObatsub(String nama ) throws HibernateException {
        List<ImSimrsJenisPersediaanObatSubEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsJenisPersediaanObatSubEntity.class)
                .add(Restrictions.ilike("nama", nama))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }
}
