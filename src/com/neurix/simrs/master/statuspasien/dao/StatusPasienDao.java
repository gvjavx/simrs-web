package com.neurix.simrs.master.statuspasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import com.neurix.simrs.master.statuspasien.model.ImSimrsStatusPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class StatusPasienDao extends GenericDao<ImSimrsStatusPasienEntity, String> {
    @Override
    protected Class<ImSimrsStatusPasienEntity> getEntityClass() {
        return ImSimrsStatusPasienEntity.class;
    }

    @Override
    public List<ImSimrsStatusPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsStatusPasienEntity.class);
        if (mapCriteria != null)
            if (mapCriteria.get("id_status_pasien") != null)
                criteria.add(Restrictions.eq("idStatusPasien", mapCriteria.get("id_status_pasien").toString()));

        List<ImSimrsStatusPasienEntity> result = criteria.list();
        return result;
    }
}
