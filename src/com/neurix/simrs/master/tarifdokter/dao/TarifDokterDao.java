package com.neurix.simrs.master.tarifdokter.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.tarifdokter.model.ImSimrsTarifDokterEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class TarifDokterDao extends GenericDao<ImSimrsTarifDokterEntity, String> {

    @Override
    protected Class<ImSimrsTarifDokterEntity> getEntityClass() {
        return ImSimrsTarifDokterEntity.class;
    }

    @Override
    public List<ImSimrsTarifDokterEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsTarifDokterEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_tarif_dokter") != null)
                criteria.add(Restrictions.eq("idTarifDokter", mapCriteria.get("id_tarif_dokter")));
            if (mapCriteria.get("id_dokter") != null)
                criteria.add(Restrictions.eq("idDokter", mapCriteria.get("id_dokter").toString()));
            if (mapCriteria.get("id_jenis_pasien") != null)
                criteria.add(Restrictions.eq("idJenisPasien",mapCriteria.get("id_jenis_pasien")));

        List<ImSimrsTarifDokterEntity> result = criteria.list();
        return result;
    }
}
