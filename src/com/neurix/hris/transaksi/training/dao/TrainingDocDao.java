package com.neurix.hris.transaksi.training.dao;

import com.neurix.common.dao.GenericDao;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisBuktiPengobatanEntity;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisMedicalRecordEntity;
import com.neurix.hris.transaksi.medicalrecord.model.ItHrisPengobatanEntity;
import com.neurix.hris.transaksi.training.model.ItHrisTrainingDocEntity;
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
 * Created by thinkpad on 19/03/2018.
 */
public class TrainingDocDao extends GenericDao<ItHrisTrainingDocEntity,String> {
    @Override
    protected Class<ItHrisTrainingDocEntity> getEntityClass() {
        return null;
    }

    @Override
    public List<ItHrisTrainingDocEntity> getByCriteria(Map mapCriteria) {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(ItHrisTrainingDocEntity.class);

        // Get Collection and sorting
        if (mapCriteria!=null) {
            if (mapCriteria.get("training_doc_id")!=null) {
                criteria.add(Restrictions.eq("trainingDocId", (String) mapCriteria.get("training_doc_id")));
            }
            if (mapCriteria.get("training_person_id")!=null) {
                criteria.add(Restrictions.ilike("trainingPersonId", "%" + (String)mapCriteria.get("training_person_id") + "%"));
            }
        }

        criteria.add(Restrictions.eq("flag", mapCriteria.get("flag")));

        // Order by
        criteria.addOrder(Order.asc("trainingDocId"));
        List<ItHrisTrainingDocEntity> results = criteria.list();
        return results;
    }

    // Generate surrogate id from postgre
    public String getNextDocId() throws HibernateException {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery("select nextval ('seq_training_doc')");
        Iterator<BigInteger> iter=query.list().iterator();
        String sId = String.format("%03d", iter.next());
        return sId;
    }

}
