package com.neurix.simrs.master.kelasruangan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kelasruangan.model.ImSimrsKelasRuanganEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class KelasRuanganDao extends GenericDao<ImSimrsKelasRuanganEntity, String> {
    @Override
    protected Class<ImSimrsKelasRuanganEntity> getEntityClass() {
        return ImSimrsKelasRuanganEntity.class;
    }

    @Override
    public List<ImSimrsKelasRuanganEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKelasRuanganEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("id_kelas_ruangan")!=null) {
                criteria.add(Restrictions.eq("idKelasRuangan", (String) mapCriteria.get("id_kelas_ruangan")));
            }
            if (mapCriteria.get("nama_kelas_ruangan")!=null) {
                criteria.add(Restrictions.ilike("namaKelasRuangan", "%" + (String)mapCriteria.get("nama_kelas_ruangan") + "%"));
            }

        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("idKelasRuangan"));

        List<ImSimrsKelasRuanganEntity> results = criteria.list();

        return results;
    }
}