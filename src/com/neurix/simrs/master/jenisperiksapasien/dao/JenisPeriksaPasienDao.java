package com.neurix.simrs.master.jenisperiksapasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.jenisperiksapasien.model.ImJenisPeriksaPasienEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by Toshiba on 07/11/2019.
 */
public class JenisPeriksaPasienDao extends GenericDao<ImJenisPeriksaPasienEntity, String> {
    @Override
    protected Class<ImJenisPeriksaPasienEntity> getEntityClass() {
        return ImJenisPeriksaPasienEntity.class;
    }

    @Override
    public List<ImJenisPeriksaPasienEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImJenisPeriksaPasienEntity.class);
        if (mapCriteria != null) {
            if (mapCriteria.get("id_jenis_periksa_pasien") != null) {
                criteria.add(Restrictions.eq("idJenisPeriksaPasien", mapCriteria.get("id_jenis_periksa_pasien").toString()));
            }
            if (mapCriteria.get("except_bpjs") != null) {
                criteria.add(Restrictions.ne("idJenisPeriksaPasien", mapCriteria.get("except_bpjs").toString()));
            }
        }

        List<ImJenisPeriksaPasienEntity> result = criteria.list();
        return result;
    }
}
