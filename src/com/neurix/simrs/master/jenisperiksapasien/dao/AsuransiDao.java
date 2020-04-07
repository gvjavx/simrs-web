package com.neurix.simrs.master.jenisperiksapasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImSimrsAsuransiEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import java.util.Map;

public class AsuransiDao extends GenericDao<ImSimrsAsuransiEntity, String> {

    @Override
    protected Class<ImSimrsAsuransiEntity> getEntityClass() {
        return ImSimrsAsuransiEntity.class;
    }

    @Override
    public List<ImSimrsAsuransiEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsAsuransiEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_asuransi") != null) {
                criteria.add(Restrictions.eq("idAsuransi", mapCriteria.get("id_asuransi").toString()));
            }
            if (mapCriteria.get("nama_asuransi") != null) {
                criteria.add(Restrictions.eq("namaAsuransi", mapCriteria.get("nama_asuransi").toString()));
            }
            if(mapCriteria.get("flag") != null){
                criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));
            }
        }
        List<ImSimrsAsuransiEntity> result = criteria.list();
        return result;
    }
}
