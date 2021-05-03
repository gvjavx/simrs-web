package com.neurix.simrs.master.pasien.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.simrs.master.pasien.model.ImSimrsUploadRekamMedicLamaEntity;
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
public class UploadRekamMedicLamaDao extends GenericDao<ImSimrsUploadRekamMedicLamaEntity, String>{
    @Override
    protected Class<ImSimrsUploadRekamMedicLamaEntity> getEntityClass() {
        return ImSimrsUploadRekamMedicLamaEntity.class;
    }

    @Override
    public List<ImSimrsUploadRekamMedicLamaEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(ImSimrsUploadRekamMedicLamaEntity.class);
        if (mapCriteria.get("id") != null)
            criteria.add(Restrictions.eq("id", mapCriteria.get("id").toString()));
        if (mapCriteria.get("head_id") != null)
            criteria.add(Restrictions.eq("headId", mapCriteria.get("head_id").toString()));
        List<ImSimrsUploadRekamMedicLamaEntity> uploadRekamMedicLamaEntities = criteria.list();
        return uploadRekamMedicLamaEntities;
    }

    public String getNextSeq(){
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('sq_upload_rekam_medic_lama')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%08d", iter.next());
        return sId;
    }
}
