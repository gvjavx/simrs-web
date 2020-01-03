package com.neurix.simrs.master.kategorilab.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategorilab.model.ImSimrsKategoriLabEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class KategoriLabDao extends GenericDao<ImSimrsKategoriLabEntity,String> {

    @Override
    protected Class<ImSimrsKategoriLabEntity> getEntityClass() {
        return ImSimrsKategoriLabEntity.class;
    }

    @Override
    public List<ImSimrsKategoriLabEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriLabEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_kategori_lab")!=null) {
                criteria.add(Restrictions.eq("idKategoriLab", (String) mapCriteria.get("id_kategori_lab")));
            }
            if (mapCriteria.get("nama_kategori")!=null) {
                criteria.add(Restrictions.ilike("namaKategori", "%" + (String)mapCriteria.get("nama_kategori") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idKategoriLab"));

        List<ImSimrsKategoriLabEntity> results = criteria.list();

        return results;

    }
}