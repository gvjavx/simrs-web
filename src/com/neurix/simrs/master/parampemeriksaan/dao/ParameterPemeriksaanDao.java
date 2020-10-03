package com.neurix.simrs.master.parampemeriksaan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.parampemeriksaan.model.ImSimrsParameterPemeriksaanEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParameterPemeriksaanDao extends GenericDao<ImSimrsParameterPemeriksaanEntity, String> {

    @Override
    protected Class<ImSimrsParameterPemeriksaanEntity> getEntityClass() {
        return ImSimrsParameterPemeriksaanEntity.class;
    }

    @Override
    public List<ImSimrsParameterPemeriksaanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsParameterPemeriksaanEntity.class);
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_parameter_pemeriksaan")!=null) {
                criteria.add(Restrictions.eq("idParameterPemeriksaan", (String) mapCriteria.get("id_parameter_pemeriksaan")));
            }
            if (mapCriteria.get("id_pelayanan")!=null) {
                criteria.add(Restrictions.eq("idPelayanan", (String) mapCriteria.get("id_pelayanan")));
            }
            if (mapCriteria.get("nama_pemeriksaan")!=null) {
                criteria.add(Restrictions.ilike("namaPemeriksaan", "%" + (String)mapCriteria.get("nama_pemeriksaan") + "%"));
            }
            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String)mapCriteria.get("flag")));
            }
        }
        criteria.addOrder(Order.asc("idParameterPemeriksaan"));
        List<ImSimrsParameterPemeriksaanEntity> results = criteria.list();
        return results;
    }

    public String getNextId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_perameter_pemeriksaan')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return "LDB" + sId;
    }
}