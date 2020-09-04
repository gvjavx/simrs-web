package com.neurix.simrs.transaksi.rekammedik.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.transaksi.rekammedik.model.ItSimrsStatusPengisianRekamMedisEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatusPengisianRekamMedisDao extends GenericDao<ItSimrsStatusPengisianRekamMedisEntity, String> {

    @Override
    protected Class<ItSimrsStatusPengisianRekamMedisEntity> getEntityClass() {
        return ItSimrsStatusPengisianRekamMedisEntity.class;
    }

    @Override
    public List<ItSimrsStatusPengisianRekamMedisEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ItSimrsStatusPengisianRekamMedisEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_status_pengisian_rekam_medis") != null) {
                criteria.add(Restrictions.eq("idStatusPengisianRekamMedis", (String) mapCriteria.get("id_status_pengisian_rekam_medis")));
            }
            if (mapCriteria.get("no_checkup") != null) {
                criteria.add(Restrictions.eq("noCheckup", (String) mapCriteria.get("no_checkup")));
            }
            if (mapCriteria.get("id_detail_checkup") != null) {
                criteria.add(Restrictions.eq("idDetailCheckup", (String) mapCriteria.get("id_detail_checkup")));
            }
            if (mapCriteria.get("id_pasien") != null) {
                criteria.add(Restrictions.eq("idPasien", (String) mapCriteria.get("id_pasien")));
            }
            if (mapCriteria.get("id_rekam_medis_pasien") != null) {
                criteria.add(Restrictions.eq("idRekamMedisPasien", (String) mapCriteria.get("id_rekam_medis_pasien")));
            }

            criteria.add(Restrictions.eq("flag", "Y"));
        }

        // Order by
        criteria.addOrder(Order.asc("idStatusPengisianRekamMedis"));

        List<ItSimrsStatusPengisianRekamMedisEntity> results = criteria.list();
        return results;
    }

    public String getNextIdSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_status_pengisiain_rm')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}