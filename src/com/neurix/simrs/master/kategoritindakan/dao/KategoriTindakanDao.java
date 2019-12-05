package com.neurix.simrs.master.kategoritindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.kategoritindakan.model.ImSimrsKategoriTindakanEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class KategoriTindakanDao extends GenericDao<ImSimrsKategoriTindakanEntity, String> {
    @Override
    protected Class<ImSimrsKategoriTindakanEntity> getEntityClass() {
        return ImSimrsKategoriTindakanEntity.class;
    }

    @Override
    public List<ImSimrsKategoriTindakanEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriTindakan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }

        List<ImSimrsKategoriTindakanEntity> result = criteria.list();
        return result;
    }
}
