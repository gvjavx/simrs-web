package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSImrsRekamMedicLamaEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by reza on 24/02/20.
 */
public class RekamMedicLamaDao extends GenericDao<ImSImrsRekamMedicLamaEntity, String>{
    @Override
    protected Class<ImSImrsRekamMedicLamaEntity> getEntityClass() {
        return ImSImrsRekamMedicLamaEntity.class;
    }

    @Override
    public List<ImSImrsRekamMedicLamaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSImrsRekamMedicLamaEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("id_pasien") != null)
            criteria.add(Restrictions.eq("idPasien", mapCriteria.get("id_pasien").toString()));

        List<ImSImrsRekamMedicLamaEntity> rekamMedicLamaEntities = criteria.list();
        return rekamMedicLamaEntities;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_rekam_medic_lama')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
