package com.neurix.hris.master.medicalBiayaKacamata.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.master.medicalBiayaKacamata.model.ImMedicalBiayaKacamataEntity;
import com.neurix.hris.master.medicalBiayaKacamata.model.MedicalBiayaKacamata;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gondok
 * Date: 06/09/17
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public class MedicalBiayaKacamataDao extends GenericDao<ImMedicalBiayaKacamataEntity, String> {

    @Override
    protected Class<ImMedicalBiayaKacamataEntity> getEntityClass() {
        return ImMedicalBiayaKacamataEntity.class;
    }

    @Override
    public List<ImMedicalBiayaKacamataEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ImMedicalBiayaKacamataEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("biaya_kacamata_id")!=null) {
                criteria.add(Restrictions.eq("biayaKacamataId", (String) mapCriteria.get("biaya_kacamata_id")));
            }

            if (mapCriteria.get("branch_id")!=null) {
                criteria.add(Restrictions.eq("unitId", (String) mapCriteria.get("branch_id")));
            }

            if (mapCriteria.get("golongan_id")!=null) {
                criteria.add(Restrictions.eq("golonganId", (String) mapCriteria.get("golongan_id")));
            }

            if (mapCriteria.get("flag")!=null) {
                criteria.add(Restrictions.eq("flag", (String) mapCriteria.get("flag")));
            }
        }

        // Order by
        criteria.addOrder(Order.asc("biayaKacamataId"));

        List<ImMedicalBiayaKacamataEntity> results = criteria.list();

        return results;
    }

    // Generate surrogate id from postgre
    public String getNextBiayaId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_medical_biaya_kacamata')");
        Iterator<BigInteger> iter = query.list().iterator();
        String sId = String.format("%02d", iter.next());

        return "BK" + sId;
    }

    public List<ImMedicalBiayaKacamataEntity> getBiayaKacamata(String branchId, String golonganId) throws HibernateException {
        List<ImMedicalBiayaKacamataEntity> results = this.sessionFactory.getCurrentSession().createCriteria(ImMedicalBiayaKacamataEntity.class)
                .add(Restrictions.eq("unitId", branchId))
                .add(Restrictions.eq("golonganId", golonganId))
                .add(Restrictions.eq("flag", "Y"))
                .list();
        return results;
    }
}
