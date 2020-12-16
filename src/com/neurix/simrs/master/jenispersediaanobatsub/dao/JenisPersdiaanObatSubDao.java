package com.neurix.simrs.master.jenispersediaanobatsub.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenispersediaanobat.model.ImSimrsJenisPersediaanObatEntity;
import com.neurix.simrs.master.jenispersediaanobatsub.model.ImSimrsJenisPersediaanObatSubEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_jenis_obat") != null)
            criteria.add(Restrictions.eq("idJenisObat", mapCriteria.get("id_jenis_obat").toString()));
        if (mapCriteria.get("nama") != null)
            criteria.add(Restrictions.ilike("nama", "%" + mapCriteria.get("nama").toString() + "%"));

        return criteria.list();
    }
}
