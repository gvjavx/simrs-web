package com.neurix.simrs.master.rekammedis.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.rekammedis.model.ImSimrsRekamMedisPelayananEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RekamMedisPelayananDao extends GenericDao<ImSimrsRekamMedisPelayananEntity, String> {

    @Override
    protected Class<ImSimrsRekamMedisPelayananEntity> getEntityClass() {
        return ImSimrsRekamMedisPelayananEntity.class;
    }

    @Override
    public List<ImSimrsRekamMedisPelayananEntity> getByCriteria(Map mapCriteria) {

        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsRekamMedisPelayananEntity.class);

        // Get Collection and sorting
        if (mapCriteria != null) {
            if (mapCriteria.get("id_rekam_medis_pelayanan") != null) {
                criteria.add(Restrictions.eq("idRekamMedisPelayanan", (String) mapCriteria.get("id_rekam_medis_pelayanan")));
            }
            if (mapCriteria.get("id_rekam_medis_pasien") != null) {
                criteria.add(Restrictions.eq("idRekamMedisPasien", (String) mapCriteria.get("id_rekam_medis_pasien")));
            }
            if (mapCriteria.get("tipe_pelayanan") != null) {
                criteria.add(Restrictions.eq("tipePelayanan", (String) mapCriteria.get("tipe_pelayanan")));
            }
            criteria.add(Restrictions.eq("flag", "Y"));
        }

        // Order by
        criteria.addOrder(Order.asc("urutan"));

        List<ImSimrsRekamMedisPelayananEntity> results = criteria.list();
        return results;
    }

    public String getNextIdSeq() {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_rm_pelayanan')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}