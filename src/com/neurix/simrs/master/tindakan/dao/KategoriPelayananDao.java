package com.neurix.simrs.master.tindakan.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tindakan.model.ImSimrsKategoriTindakanPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

public class KategoriPelayananDao extends GenericDao<ImSimrsKategoriTindakanPelayananEntity, String> {
    @Override
    protected Class<ImSimrsKategoriTindakanPelayananEntity> getEntityClass() {
        return ImSimrsKategoriTindakanPelayananEntity.class;
    }

    @Override
    public List<ImSimrsKategoriTindakanPelayananEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsKategoriTindakanPelayananEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_kategori_tindakan") != null) {
                criteria.add(Restrictions.eq("idKategoriPelayanan", mapCriteria.get("id_kategori_tindakan").toString()));
            }
            if (mapCriteria.get("id_kategori") != null) {
                criteria.add(Restrictions.eq("idKategori", mapCriteria.get("id_kategori").toString()));
            }
            if (mapCriteria.get("id_pelayanan") != null) {
                criteria.add(Restrictions.eq("idPelayanan", mapCriteria.get("id_pelayanan").toString()));
            }
            if (mapCriteria.get("flag") != null) {
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag").toString()));
            }
        }
        List<ImSimrsKategoriTindakanPelayananEntity> result = criteria.list();
        return result;
    }
}
