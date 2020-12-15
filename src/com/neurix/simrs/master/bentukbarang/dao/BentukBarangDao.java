package com.neurix.simrs.master.bentukbarang.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.bentukbarang.model.ImSimrsBentukBarangEntity;
import com.neurix.simrs.master.jenisobat.model.ImSimrsJenisObatEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by reza on 13/07/20.
 */
public class BentukBarangDao extends GenericDao<ImSimrsBentukBarangEntity, String> {
    @Override
    protected Class<ImSimrsBentukBarangEntity> getEntityClass() {
        return ImSimrsBentukBarangEntity.class;
    }

    @Override
    public List<ImSimrsBentukBarangEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsBentukBarangEntity.class);
        if (mapCriteria.get("id_bentuk") != null)
            criteria.add(Restrictions.eq("idBentuk", mapCriteria.get("id_bentuk").toString()));
        if (mapCriteria.get("bentuk") != null)
            criteria.add(Restrictions.eq("bentuk", mapCriteria.get("bentuk").toString()));
        if (mapCriteria.get("flag") != null)
            criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));

        return criteria.list();
    }
    public List<ImSimrsBentukBarangEntity> getBentukBarang(String idBentuk ) throws HibernateException {
        List<ImSimrsBentukBarangEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsBentukBarangEntity.class)
//                .add(Restrictions.ilike("bentuk", bentuk))
                .add(Restrictions.eq("idBentuk", idBentuk))
                .add(Restrictions.eq("flag", "Y"))
                .list();
//        ne (not equal / tidak samadengan)
        return results;
    }
}
