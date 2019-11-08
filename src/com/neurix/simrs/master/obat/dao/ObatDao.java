package com.neurix.simrs.master.obat.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.obat.model.ImSimrsObatEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class ObatDao extends GenericDao<ImSimrsObatEntity, String> {
    @Override
    protected Class<ImSimrsObatEntity> getEntityClass() {
        return ImSimrsObatEntity.class;
    }

    @Override
    public List<ImSimrsObatEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsObatEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_obat")!=null) {
                criteria.add(Restrictions.eq("idObat", (String) mapCriteria.get("id_obat")));
            }
            if (mapCriteria.get("nama_jenis_obat")!=null) {
                criteria.add(Restrictions.ilike("namaObat", "%" + (String)mapCriteria.get("nama_jenis_obat") + "%"));
            }
            if (mapCriteria.get("id_jenis_obat")!=null) {
                criteria.add(Restrictions.eq("idJenisObat", (String) mapCriteria.get("id_jenis_obat")));
            }
            if (mapCriteria.get("harga")!=null) {
                criteria.add(Restrictions.eq("harga", (Long) mapCriteria.get("harga")));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idObat"));

        List<ImSimrsObatEntity> results = criteria.list();

        return results;
    }
}